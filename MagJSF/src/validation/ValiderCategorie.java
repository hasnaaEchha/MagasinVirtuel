package validation;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import bean.Categorie;
import metier.CategorieRemote;

@ManagedBean(name = "validerCategorie")
@SessionScoped
public class ValiderCategorie extends Validateur {
	@EJB
	CategorieRemote catr;

	@Override
	public Object validerRequet(Object input) {
		// verifier que la categorie creer est unique
		Categorie categorie = (Categorie) input;
		if (categorieExiste(categorie.getNom()))
			categorie.setNom(null);
		else
			catr.creer(categorie.getNom(), null);

		return null;
	}

	public boolean categorieExiste(String nom) {
		return catr.trouver(nom) != null;
	}
	
}
