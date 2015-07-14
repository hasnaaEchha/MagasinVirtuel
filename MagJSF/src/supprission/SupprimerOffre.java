package supprission;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import metier.OffreRemote;
import bean.Client;
import bean.Offre;
import bean.Produit;

@ManagedBean(name = "supprimerOffre")
@SessionScoped
public class SupprimerOffre extends Delete {
	@EJB
	OffreRemote or;

	@Override
	public Object supprimer(Object input) {
		System.out.println("offre");
		if ((input instanceof Offre)) {
			
			Offre offre = (Offre) input;
			or.supprimer(offre.getClient().getId(), offre.getProduit().getId());
			return offre;
		}
		if ((input instanceof Produit)) {
			Produit produit = (Produit) input;
			List<Offre> liste = or.listProduitOffres(produit.getId());
			for (int i = 0; i < liste.size(); i++) {
				or.supprimer(liste.get(i).getClient().getId(), liste.get(i)
						.getProduit().getId());
			}

		}
		// les offres auquelle il a miser
		if ((input instanceof Client)) {

			Client client = (Client) input;
			// les offres auquelle il a miser
			List<Offre> liste = or.listClientOffres(client.getId());
			for (int i = 0; i < liste.size(); i++) {
				
				or.supprimer(client.getId(), liste.get(i).getProduit().getId());

			}
			liste = or.listeOffreProprietaire(client.getId());
			for (int i = 0; i < liste.size(); i++) {
				System.out.println(client.getId()
						+ "id produit:"+ liste.get(i).getProduit().getId());
				or.supprimer(liste.get(i).getClient().getId(), liste.get(i).getProduit().getId());
			}
		}
		return successeur.supprimer(input);
	}
}