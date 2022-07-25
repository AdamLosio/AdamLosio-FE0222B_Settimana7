package it.business;

import it.data.Contatto;
import it.data.Telefono;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import openejb.shade.org.apache.xpath.operations.String;


@Stateless
@LocalBean
public class RubricaEjbImpl implements RubricaEjbRemote, RubricaEjbLocal {
	@PersistenceContext(name = "corsoEpicodeProgettoSettimana7")
	EntityManager em;

	public RubricaEjbImpl() {
		// TODO Auto-generated constructor stub
	}

	// Attraverso il persist possiamo inserire un nuovo contatto
	public void inserisci(Contatto e) {
		em.persist(e);
	}
	
	//Con la dynamic query ricerchiamo i contatti per cognome
	public Contatto getContattoByCognome(String cognome) {
		Contatto contatto = new Contatto();
		Query query = em.createQuery("SELECT c FROM Contatto c WHERE c.cognome like :cognome");
		query.setParameter("cognome", "%" + cognome + "%");
		contatto = (Contatto) query.getSingleResult();
		return contatto;
	}

	//Ricerchiamo il contatto per numero
	public Contatto getContattoByNumero(String numero) {
		Contatto contatto = new Contatto();
		Telefono numeroTel = new Telefono();
		numeroTel = em.find(Telefono.class, numero);
		contatto = numeroTel.getContatto();
		return contatto;
	}

	//Cerchiamo il contatto per ID
	public Contatto getContattoByID(Long id) {
		Contatto contatto = new Contatto();
		contatto = em.find(Contatto.class, id);
		return contatto;
	}

	//Selezionare tutti i contatti
	@SuppressWarnings("unchecked")
	public List<Contatto> getAllContatti() {
		Query queryCont = em.createQuery("SELECT c FROM Contatto c");
		List<Contatto> contatti = queryCont.getResultList();
		return contatti;
	}

	//Cancellare un contatto
	public void elimina(Long id) {
		Contatto contatto = new Contatto();
		contatto = getContattoByID(id);
		em.remove(contatto);
	}

	//Metodo per eliminare un numero del contatto
	public int eliminaNumPerContatto(Contatto contatto) {
		int eliminaNumero = 0;
		for (Telefono numero : contatto.getNumTelefoni()) {
			Query query = em.createQuery("DELETE FROM NumTelefono t where  t.numTelefono like :numero");
			query.setParameter("numero", numero.getNumTelefono());
			eliminaNumero = query.executeUpdate();
		}
		return eliminaNumero;

	}

	//Personalizza le info del contatto
	public Contatto update(Contatto contatto) {
		eliminaNumPerContatto(getContattoByID(contatto.getId()));
		return em.merge(contatto);
	}

}
