package services;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="navigation")
@SessionScoped
public class Navigation implements Serializable{

	private String login="login.xhtml";
	private String catalogue="gererCatalogue.xhtml?faces-redirect=true";
	private String profile="profil.xhtml";
	private String modifierProfile="modifierProfile.xhtml";
	private String mesProduits="votreMarche.xhtml?faces-redirect=true";
	private String mises="miseProduit.xhtml?faces-redirect=true";
	private String adminPage="admin/adCatalogue.xhtml?faces-redirect=true";
	private String produitVendu="produitsVendu.xhtml?faces-redirect=true";
	private String evaluations="evaluation.xhtml";
	private String mesClients="mesClients.xhtml";
	private String inscription="inscription.xhtml?faces-redirect=true";
	private String accueil="accueil.xhtml?faces-redirect=true";
	public String toLogin(){
		return login;
	}
	public String toAdminPage(){
		return adminPage;
	}
	public String toInscription(){
		return inscription;
	}
	public String toMesClientsPage(){
		return mesClients;
	}
	public String toEvaluations(){
		return evaluations;
	}
	public String toProduitsVendu(){
		return produitVendu;
	}
	public String toLoginAdmin(){
		return "/admin.xhtml";
	}
	public String toMesMises(){
		return mises;
	}
	public String toProfile(){
		return profile;
	}
	public String toMesProduits(){
		return mesProduits;
	}
	public String toModieferProfile(){
		return modifierProfile;
	}
	
	public String toPaiement(){
		return "payer";
	}
	public String toWelcome(){
		return accueil;
	}
	
	 public String redirectToWelcome() {
	        return "/accueil.xhtml?faces-redirect=true";
	    }
	 public String toCatalogue(){
		 return catalogue;
	 }
}
