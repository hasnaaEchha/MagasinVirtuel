package beanJSF;

import java.io.Serializable;
import java.util.Set;

import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.persistence.Transient;


import bean.Categorie;
import metier.CatalogueRemote;

@ManagedBean(name = "cataloguei")
@SessionScoped
public class Catalogue implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8258256980938665267L;
	@EJB
	CatalogueRemote cr;
	private String nom;
	private Set<Categorie> listCategories;
	private static Catalogue instance = null;

	public void creer() {
		cr.creer(nom);
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

}
