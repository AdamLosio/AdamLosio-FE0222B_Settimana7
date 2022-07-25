package it.business;

import java.util.List;

import it.data.Contatto;
import jakarta.ejb.Remote;

@Remote
public interface RubricaEjbRemote {
	public void inserisci(Contatto e);
	public Contatto getContattoByCognome(String cognome);
	public List<Contatto> getAllContatti();
	public void elimina(Long id);
	public Contatto getContattoByID(Long id);
	public Contatto update(Contatto contatto);
	public Contatto getContattoByNumero(String numero);
}
