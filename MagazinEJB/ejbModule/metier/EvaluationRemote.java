package metier;

import java.util.List;

import javax.ejb.Remote;

import bean.Client;
import bean.Evaluation;
import bean.Produit;

@Remote
public interface EvaluationRemote {
	public Evaluation creer(int frais, int respectDelai, int credibilitClient,String description,
			long idClient,long idProd);
	public List<Evaluation> evaluations();
	public List<Integer> listEvalFrais(int val);
	public void removeByProduitId(long idProd);
	public List<Evaluation> listEvalByProduit(long idProd);
	public List<Evaluation> listEvalByClient(long idClient);
	public Evaluation trouve (long id);
	public Long getEvalFraisByEtoile(long etoile);
	public Long getEvalDelaisByEtoile(long etoile);
	public Long getEvalCredibiliteByEtoile(long etoile);
	public Evaluation evalByProdClient(long idClient,long idProduit);
	public void supprimer(long idClient,long idProduit);
	public List<Evaluation>listeEvaluationProprietaire(long idProprietaire);
}
