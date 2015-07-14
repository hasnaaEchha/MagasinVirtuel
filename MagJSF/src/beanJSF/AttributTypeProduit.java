package beanJSF;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import metier.AttributTypeProduitRemote;

@ManagedBean(name="attributTypeProduit")
public class AttributTypeProduit implements Serializable{
	@EJB
	AttributTypeProduitRemote atp;
	private String nom;
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public void creer(){
		atp.creer(nom);
	}
	

}
