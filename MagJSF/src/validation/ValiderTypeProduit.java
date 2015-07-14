package validation;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import bean.Produit;
import bean.TypeProduit;
import metier.TypeProduitRemote;

@ManagedBean(name = "validerTypeProduit")
@SessionScoped
public class ValiderTypeProduit extends Validateur{
	@EJB
	TypeProduitRemote tpr;
	@Override
	public Object validerRequet(Object input) {
		if(!(input instanceof TypeProduit))
			return successeur.validerRequet(input);
		// veridier que le typeproduit creer est unique
				TypeProduit typeProduit = (TypeProduit) input;
				if (typeProduitExiste(typeProduit.getNom()))
					typeProduit.setNom(null);
				else
					tpr.creer(typeProduit.getNom());
		return null;
	}
	public boolean typeProduitExiste(String nom) {
		return tpr.trouver(nom) != null;
	}
}
