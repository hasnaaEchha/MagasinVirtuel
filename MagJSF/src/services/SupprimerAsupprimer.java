package services;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import metier.ClientRemote;

public class SupprimerAsupprimer extends HttpServlet{
	@EJB
	ClientRemote cr;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String idString=req.getParameter("client_id");
		long id=Long.parseLong(idString);
		cr.remove(id);
		 
         
		
	}

}
