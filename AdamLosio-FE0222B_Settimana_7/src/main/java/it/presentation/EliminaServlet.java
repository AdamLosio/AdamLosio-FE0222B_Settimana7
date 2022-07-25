package it.presentation;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import it.business.RubricaEjbImpl;

//Elimina contatto per ID
public class EliminaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    @EJB
    RubricaEjbImpl rubrica;
    public EliminaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	rubrica.elimina(Long.valueOf(request.getParameter("Id")));
	
	request.getServletContext().getRequestDispatcher("/operazioneCompl.html").forward(request, response);
	}

}
