package services;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import supprission.SupprimerCategorie;
import supprission.SupprimerClient;
import supprission.SupprimerEvaluation;
import supprission.SupprimerOffre;
import supprission.SupprimerProduit;
import supprission.SupprimerTypeProduit;

@ManagedBean(name = "supprimer")
@SessionScoped
public class Supprimer {
	public Supprimer() {
		super();
		// TODO Auto-generated constructor stub
	}

	@ManagedProperty(value = "#{supprimerOffre}")
	SupprimerOffre suppr1;
	@ManagedProperty(value = "#{supprimerEvaluation}")
	SupprimerEvaluation suppr2;
	@ManagedProperty(value = "#{supprimerProduit}")
	SupprimerProduit suppr3;
	@ManagedProperty(value = "#{supprimerClient}")
	SupprimerClient suppr4;
	@ManagedProperty(value = "#{supprimerCategorie}")
	SupprimerCategorie suppr5;
	@ManagedProperty(value = "#{supprimerTypeProduit}")
	SupprimerTypeProduit suppr6;

	public Object supprimer(Object input) {

		suppr1.SetSuccesseur(suppr2);
		suppr2.SetSuccesseur(suppr3);// validateur2.SetSuccesseur(validateur3);
		suppr3.SetSuccesseur(suppr4);
		suppr4.SetSuccesseur(suppr5);
		suppr5.SetSuccesseur(suppr6);

		return suppr1.supprimer(input);

	}

	public SupprimerOffre getSuppr1() {
		return suppr1;
	}

	public void setSuppr1(SupprimerOffre suppr1) {
		this.suppr1 = suppr1;
	}

	public SupprimerEvaluation getSuppr2() {
		return suppr2;
	}

	public void setSuppr2(SupprimerEvaluation suppr2) {
		this.suppr2 = suppr2;
	}

	public SupprimerProduit getSuppr3() {
		return suppr3;
	}

	public void setSuppr3(SupprimerProduit suppr3) {
		this.suppr3 = suppr3;
	}

	public SupprimerClient getSuppr4() {
		return suppr4;
	}

	public void setSuppr4(SupprimerClient suppr4) {
		this.suppr4 = suppr4;
	}

	public SupprimerCategorie getSuppr5() {
		return suppr5;
	}

	public void setSuppr5(SupprimerCategorie suppr5) {
		this.suppr5 = suppr5;
	}

	public SupprimerTypeProduit getSuppr6() {
		return suppr6;
	}

	public void setSuppr6(SupprimerTypeProduit suppr6) {
		this.suppr6 = suppr6;
	}


}
