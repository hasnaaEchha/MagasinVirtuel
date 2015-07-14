package supprission;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import metier.EvaluationRemote;
import metier.OffreRemote;
import bean.Client;
import bean.Evaluation;
import bean.Offre;
import bean.Produit;

@ManagedBean(name = "supprimerEvaluation")
@SessionScoped
public class SupprimerEvaluation extends Delete{
	@EJB
	EvaluationRemote er;

	@Override
	public Object supprimer(Object input) {
		System.out.println("evaluation");
		if ((input instanceof Evaluation)) {
			Evaluation evaluation = (Evaluation) input;
			System.out.println("clientIdest: "+evaluation.getClient().getId()+" product id: "+evaluation.getProduit().getId());
			er.supprimer(evaluation.getClient().getId(), evaluation.getProduit().getId());
			
			return evaluation;
		}
		if ((input instanceof Produit)) {
			Produit produit = (Produit) input;
			List<Evaluation> liste = er.listEvalByProduit(produit.getId());
			for (int i = 0; i < liste.size(); i++) {
				er.supprimer(liste.get(i).getClient().getId(), liste.get(i)
						.getProduit().getId());
			}
		}
		// les offres auquelle il a miser
		if ((input instanceof Client)) {
			Client client = (Client) input;
			// les offres auquelle il a miser
			List<Evaluation>liste = er.listEvalByClient(client.getId());
			for (int i = 0; i < liste.size(); i++) {
				
				er.supprimer(client.getId(), liste.get(i).getProduit().getId());

			}
			liste = er.listeEvaluationProprietaire(client.getId());
			for (int i = 0; i < liste.size(); i++) {
				
				er.supprimer(liste.get(i).getClient().getId(), liste.get(i).getProduit().getId());
			}
		}
		return successeur.supprimer(input);
	}
}
