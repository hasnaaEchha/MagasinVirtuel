package validation;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import metier.ClientRemote;
import metier.OffreRemote;
import metier.ProduitRemote;
import bean.Client;
import bean.Offre;
import bean.Produit;
import bean.SystemVariable;
import beanJSF.ClientBean;

@ManagedBean(name = "validerMise")
@SessionScoped
public class ValiderMise extends Validateur {
	@EJB
	OffreRemote or;
	@EJB
	ClientRemote cr;
	@EJB
	ProduitRemote pr;

	@Override
	public Object validerRequet(Object input) {
		if (!(input instanceof Offre))
			return successeur.validerRequet(input);
		Offre offre = (Offre) input;
		// verifier que le prix donne par le client est le meilleur
		if (offre.getPrix() < prixMeilleur(offre.getProduit().getId())||or.ordreDernierOffre(offre.getClient().getId(),offre.getProduit().getId())>=SystemVariable.getInstance().getMaxMise())
			offre.setPrix(0);
		else {
			long idClient = offre.getClient().getId(), idProduit = offre
					.getProduit().getId();
			if (or.trouver(idClient, idProduit) != null)
				or.update(idClient, idProduit, offre.getPrix());

			else
				creer(idClient, idProduit, offre.getPrix());
		}
		return offre;
	}

	public double prixMeilleur(long idProd) {
		return Double.valueOf(or.meilleurOffre(idProd));
	}

	public void creer(long idClient, long idProduit, double prix) {

		Client client = cr.trouve(idClient);
		Produit produit = pr.trouve(idProduit);
		/*
		 * essai
		 */

		Offre offre = or.creer(prix, new Date(), produit, client);

	}

}
