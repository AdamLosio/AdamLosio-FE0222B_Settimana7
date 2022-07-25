package it.presentation;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import it.business.RubricaEjbImpl;
import it.data.Contatto;
import it.data.Telefono;

//Inserisci contatto
public class InserisciServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    @EJB
    RubricaEjbImpl rubrica;
    public InserisciServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Contatto c=new Contatto();
		Telefono n1=new Telefono();
		Telefono n2=new Telefono();
		ArrayList<Telefono>numeri=new ArrayList<>(); 
		
			n1.setNumTelefono(request.getParameter("numtel1"));
			n2.setNumTelefono(request.getParameter("numtel2"));	
			c.setCognome(request.getParameter("cognome"));
			c.setNome(request.getParameter("nome"));
			c.setEmail(request.getParameter("email"));
			n1.setContatto(c);
			
			if(n2.getNumTelefono().equals("")) {
				
			}else {
				n2.setContatto(c);
				numeri.add(n2);
			}
			
			numeri.add(n1);
			c.setNumTelefoni(numeri);

				rubrica.inserisci(c);
				request.getServletContext().getRequestDispatcher("/operazioneCompl.html").forward(request, response);
	}

}
