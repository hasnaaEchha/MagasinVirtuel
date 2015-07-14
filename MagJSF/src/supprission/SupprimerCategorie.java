package supprission;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import metier.CategorieRemote;
import metier.ProduitRemote;
import bean.Categorie;
import bean.Produit;

@ManagedBean(name="supprimerCategorie")
@SessionScoped
public class SupprimerCategorie extends Delete {

	@EJB
	CategorieRemote cr;
	@Override
	public Object supprimer(Object input) {
		System.out.println("categorie");
		if ((input instanceof Categorie)) {
			Categorie categorie = (Categorie) input;
			cr.supprimer(categorie.getId());
			return categorie;
		}
		return successeur.supprimer(input);
	}

}
