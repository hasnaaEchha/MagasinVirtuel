package services;

import java.io.IOException;
import java.io.InputStream;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import metier.ProduitRemote;

public class DisplayImage extends HttpServlet{
	@EJB
	ProduitRemote pr;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		byte[] inImage;
		String idString=req.getParameter("image_id");
		long id=Long.parseLong(idString);
		inImage=pr.getImageById(id);
		 int size = 0;
         resp.reset();
         resp.setContentType("image/png");
         ServletOutputStream outputStream=resp.getOutputStream();
             resp.getOutputStream().write(inImage);
             outputStream.close();
         
		
	}

}
