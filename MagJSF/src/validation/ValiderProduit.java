package validation;

import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import bean.Categorie;
import bean.Client;
import bean.Produit;
import bean.TypeProduit;
import metier.CatalogueRemote;
import metier.CategorieRemote;
import metier.ClientRemote;
import metier.ProduitRemote;
import metier.TypeProduitRemote;

@ManagedBean(name = "validerProduit")
@SessionScoped
public class ValiderProduit extends Validateur {
	@EJB
	CatalogueRemote cr;

	@EJB
	ProduitRemote pr;
	@Override
	public Object validerRequet(Object input) {
		if(!(input instanceof Produit))
			return successeur.validerRequet(input);
		// verifier que le catalogue existe
		Produit reponse = (Produit) input,produit=new Produit();
		if(input instanceof Produit){
		if (!catalogueExiste())
			reponse.setNom(null);
		else
			{produit=pr.creer(reponse.getNom(), reponse.getMarque(),
					reponse.getDateLimite(), reponse.getPrixMin(), false,reponse.getImage(),
					reponse.getTypeProduit(), reponse.getCategorie(),
					reponse.getProprietaire());
			
			
			}
		return reponse;
		}
		return successeur.validerRequet(input);
	}

	public boolean catalogueExiste() {
		return cr.trouve() != null;

	}

}
