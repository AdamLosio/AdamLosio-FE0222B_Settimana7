package it.presentation;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

import it.business.RubricaEjbImpl;
import it.data.Contatto;

//Info contatto
public class VisualizzaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    @EJB 
    RubricaEjbImpl rubrica;
    public VisualizzaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String azione="visualizza";
		
		List<Contatto> contatti= rubrica.getAllContatti();
		HttpSession session=request.getSession();
		session.setAttribute("contatti", contatti);
		session.setAttribute("azione", azione);
		request.getServletContext().getRequestDispatcher("/visualizza.jsp").forward(request, response);
		
	}

}
