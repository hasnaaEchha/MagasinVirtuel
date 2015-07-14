package beanJSF;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import bean.Categorie;
import metier.CategorieRemote;
@ManagedBean(name="categorie")
@SessionScoped
public class CategorieBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
	CategorieRemote cr;
	private String nom;
	private Categorie categorieParent=new Categorie();
	private String nomCategorieM;
	
	public Categorie getCategorieParent() {
		return categorieParent;
	}
	public void setCategorieParent(Categorie categorieParent) {
		this.categorieParent = categorieParent;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public String getNomCategorieM() {
		return nomCategorieM;
	}
	public void setNomCategorieM(String nomCategorieM) {
		this.nomCategorieM = nomCategorieM;
		categorieParent=cr.trouver(nomCategorieM);
	}
	
	/*
	 * il faut d'abord verifier le catalogue est deja creer si non envoyer un msg d'erreur ou bien le diriger vers la page de creation
	 * de catalogue
	 */
	public void creer(){
		
		cr.creer(nom,categorieParent);
	}
	

}
