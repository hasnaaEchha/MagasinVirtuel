package services;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import metier.ClientRemote;
import metier.ProduitRemote;

public class Vendre extends HttpServlet{
	@EJB
	ProduitRemote pr;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String idString;
		idString=req.getParameter("client_id");
		long idClient=Long.parseLong(idString);
		idString=req.getParameter("produit_id");
		long idProd=Long.parseLong(idString);
		pr.achatProduit(idClient, idProd);
	
	}

}
