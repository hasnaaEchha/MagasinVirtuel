package beanJSF;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import metier.TypeProduitRemote;

@ManagedBean(name = "typeProduit")
@SessionScoped
public class TypeProduit implements Serializable {
	@EJB
	TypeProduitRemote tpr;
	private String nom;

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	public void creer()
	{
		tpr.creer(nom);
	}

}
