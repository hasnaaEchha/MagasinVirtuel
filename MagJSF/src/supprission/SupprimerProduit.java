package supprission;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import metier.ProduitRemote;
import bean.Categorie;
import bean.Client;
import bean.Produit;
import bean.TypeProduit;

@ManagedBean(name = "supprimerProduit")
@SessionScoped
public class SupprimerProduit extends Delete {
	@EJB
	ProduitRemote pr;
	@Override
	public Object supprimer(Object input) {
		System.out.println("produit");
		if ((input instanceof Produit)) {
			Produit produit = (Produit) input;
			pr.supprimer(produit.getId());
			return produit;
		}
		if ((input instanceof Client)) {
			Client client=(Client)input;
			List<Produit> liste=pr.listProduitByClient(client.getId());
			for(int i=0;i<liste.size();i++){
				pr.supprimer(liste.get(i).getId());
			}
		}
		if ((input instanceof Categorie)) {
			Categorie categorie=(Categorie)input;
			List<Produit> liste=pr.listProdByCat(categorie.getId());
			for(int i=0;i<liste.size();i++){
				pr.supprimer(liste.get(i).getId());
			}
		}
		if ((input instanceof TypeProduit)) {
			TypeProduit typeProduit=(TypeProduit)input;
			List<Produit> liste=pr.listProdByTypeProd(typeProduit.getId());
			for(int i=0;i<liste.size();i++){
				pr.supprimer(liste.get(i).getId());
			}
		}
		return successeur.supprimer(input);
	}
}
