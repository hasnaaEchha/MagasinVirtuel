package chart.initFactory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import metier.EvaluationRemote;

@ManagedBean(name = "mapEtoile")
@SessionScoped
public class MapEtoile implements Serializable {
	@EJB
	EvaluationRemote er;
	long nombreMaxEtoile = 10L;
	private Map<String, Long> donnee = new HashMap<String, Long>();
	
	public Map<String, Long> getDonne(String nom) {
		
		if (nom.equals("Frais")) {
			for (long i = 0L; i <= nombreMaxEtoile; i++) {
				donnee.put(String.valueOf(i), er.getEvalFraisByEtoile(i));
			}
			return donnee;
		}
		if (nom.equals("Delais")) {
			for (long i = 0L; i <= nombreMaxEtoile; i++) {
				donnee.put(String.valueOf(i), er.getEvalDelaisByEtoile(i));
			}
			return donnee;
		}
		if (nom.equals("Credibilite")) {
			for (long i = 0L; i <= nombreMaxEtoile; i++) {
				donnee.put(String.valueOf(i), er.getEvalCredibiliteByEtoile(i));
			}
			return donnee;
		}
		return null;

	}

}
