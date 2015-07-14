package metier;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import bean.Client;
import bean.Evaluation;
import bean.Offre;
import bean.Produit;

@Stateful(name = "plainte")
public class ImplEvaluation implements EvaluationRemote {
	@PersistenceContext
	EntityManager em;
	@EJB
	ProduitRemote pr;
	@EJB
	ClientRemote cr;

	public Evaluation creer(int frais, int respectDelai, int credibilitClient,
			String description, long idClient, long idProd) {
		Client client = em.find(Client.class, idClient);
		Produit produit = em.find(Produit.class, idProd);
		Evaluation evaluation = new Evaluation(frais, respectDelai,
				credibilitClient, description, client, produit);
		em.persist(evaluation);
		em.flush();
		em.refresh(evaluation);
		return evaluation;
	}

	public List<Evaluation> evaluations() {
		Query query = this.em.createNamedQuery("Evaluation.evaluations",
				Evaluation.class);

		return query.getResultList();
	}

	@Override
	public List<Integer> listEvalFrais(int val) {
		Query query = this.em.createNamedQuery("Evaluation.evaluations.frais");
		query.setParameter("frais", val);
		return query.getResultList();
	}

	@Override
	public void removeByProduitId(long idProd) {
		List<Evaluation> list=listEvalByProduit(idProd);
		for(int i=0;i<list.size();i++){
			em.remove(trouve(list.get(i).getId()));
		}
		
	}

	public Evaluation trouve(long id) {
		return em.find(Evaluation.class, id);
	}

	@Override
	public List<Evaluation> listEvalByProduit(long idProd) {
		Produit produit = pr.trouve(idProd);
		Query query = em.createNamedQuery("Evaluation.evaluationsByProd",
				Evaluation.class);
		query.setParameter("produit", produit);
		return query.getResultList();
	}

	@Override
	public Long getEvalFraisByEtoile(long etoile) {
		Query query = em.createNamedQuery("Evaluation.evalFraisByEtoil");
		query.setParameter("frais",etoile);
		return (Long)query.getSingleResult();
	}

	@Override
	public Long getEvalDelaisByEtoile(long etoile) {
		Query query = em.createNamedQuery("Evaluation.evalDelaisByEtoil");
		query.setParameter("delais",etoile);
		return (Long)query.getSingleResult();
	}

	@Override
	public Long getEvalCredibiliteByEtoile(long etoile) {
		Query query = em.createNamedQuery("Evaluation.evalCredibiliteByEtoil");
		query.setParameter("credibilite",etoile);
		return (Long)query.getSingleResult();
	}

	@Override
	public void supprimer(long idClient, long idProduit) {
		em.remove(evalByProdClient(idClient,idProduit) );
		
	}

	@Override
	public Evaluation evalByProdClient(long idClient, long idProduit) {
		Query query = em.createNamedQuery("Evaluation.evalByProdAndClient");
		Client client=cr.trouve(idClient);
		Produit produit=pr.trouve(idProduit);
		query.setParameter("produit",produit);
		query.setParameter("client",client);
		return (Evaluation)query.getSingleResult();
	}

	@Override
	public List<Evaluation> listEvalByClient(long idClient) {
		Query query = em.createNamedQuery("Evaluation.evaluationsByClient",Evaluation.class);
		Client client=cr.trouve(idClient);
		query.setParameter("client", client);
		return query.getResultList();
	}

	@Override
	public List<Evaluation> listeEvaluationProprietaire(long idProprietaire) {
		Query query=em.createNamedQuery("Evaluation.eval.prod.proprietaire",Evaluation.class);
		Client client=cr.trouve(idProprietaire);
		query.setParameter("proprietaire", client);
		return query.getResultList();
	}

}
