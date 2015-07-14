package services;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import bean.Client;
import validation.*;

/*
 * a SUPPRIME
 */

@ManagedBean(name="validation")
@SessionScoped
public class Validation implements Serializable{
	@ManagedProperty(value="#{validerMise}")
	Validateur validateur1;
	@ManagedProperty(value="#{validerProduit}")
	Validateur validateur2;
	@ManagedProperty(value="#{validerPaiement}")
	Validateur validateur3;
	@ManagedProperty(value="#{validerClient}")
	Validateur validateur4;
	@ManagedProperty(value="#{validerCategorie}")
	Validateur validateur5;
	@ManagedProperty(value="#{validerTypeProduit}")
	Validateur validateur6;
	
	public Object valider(Object input){

		validateur3=new ValiderPaiement();
		
		
		validateur1.SetSuccesseur(validateur2);	
		validateur2.SetSuccesseur(validateur3);//validateur2.SetSuccesseur(validateur3);
		validateur3.SetSuccesseur(validateur4);
		validateur4.SetSuccesseur(validateur5);
		validateur5.SetSuccesseur(validateur6);
		return validateur1.validerRequet(input);
		
		
	}


	public Validateur getValidateur1() {
		return validateur1;
	}

	public void setValidateur1(Validateur validateur1) {
		this.validateur1 = validateur1;
	}

	public Validateur getValidateur2() {
		return validateur2;
	}

	public void setValidateur2(Validateur validateur2) {
		this.validateur2 = validateur2;
	}

	public Validateur getValidateur3() {
		return validateur3;
	}

	public void setValidateur3(Validateur validateur3) {
		this.validateur3 = validateur3;
	}
	public Validateur getValidateur4() {
		return validateur4;
	}

	public void setValidateur4(Validateur validateur4) {
		this.validateur4 = validateur4;
	}

	public Validateur getValidateur5() {
		return validateur5;
	}

	public void setValidateur5(Validateur validateur5) {
		this.validateur5 = validateur5;
	}

	public Validateur getValidateur6() {
		return validateur6;
	}

	public void setValidateur6(Validateur validateur6) {
		this.validateur6 = validateur6;
	}
}
