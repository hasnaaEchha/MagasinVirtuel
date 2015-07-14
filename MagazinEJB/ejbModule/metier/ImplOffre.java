package metier;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;







import javax.persistence.Query;

import bean.Client;
import bean.Offre;
import bean.Produit;
@Stateless(name="offre")
public class ImplOffre implements OffreRemote{
	@PersistenceContext(unitName = "magasin")
	private EntityManager em;
	@EJB	
	ClientRemote cr;
	@EJB
	ProduitRemote pr;
	@Override
	public Offre creer(double prix, Date date, Produit produit, Client client) {
		/*
		 * ca manque encore la validation des entre
		 * 1. ne son pas valide
		 * si validation false 
		 * 
		 * ==>return null;
		 *le produit et le client doivent etre non null et ca va etre gerer par le JPA
		 */
		date=new Date();
		client=cr.trouve(client.getEmail());
		/*********/
		produit=em.find(Produit.class,produit.getId());
		/****juste pour tester****/
		Offre offre=new Offre( prix, date,produit,client);
		em.persist(offre);
		offre.setOrdre(1);
		em.flush();
		em.refresh(offre);
		return offre;
	}
	@Override
	public Offre trouver(long idClient, long idProd) {
		Query query=em.createNamedQuery("Offre.trouver",Offre.class);
		Client client=em.find(Client.class, idClient);
		Produit produit=em.find(Produit.class, idProd);
		query.setParameter("prod", produit);
		query.setParameter("client", client);
		if(query.getResultList().size()>0){
			System.out.println("dan ImplOffre on a"+idProd);
			return (Offre) query.getSingleResult();
		}
		return null;
	}
	@Override
	public Offre update(long idClient, long idProd,double prix) {
		Offre offre=trouver(idClient,idProd);
		offre.setPrix(prix);
		offre.setOrdre(offre.getOrdre()+1);
		em.flush();
		return offre;
	}
	@Override
	public List<Offre> listProduitOffres(long idProd) {
		Produit produit=em.find(Produit.class, idProd);
		Query query=em.createNamedQuery("Offre.listOffres.prod",Offre.class);
		query.setParameter("prod",produit);
		return query.getResultList();
	}
	@Override
	public List<Offre> listClientOffres(long idClient) {
		
		Client client=em.find(Client.class, idClient);
		Query query=em.createNamedQuery("Offre.listOffres.client",Offre.class);
		query.setParameter("client",client);
		return query.getResultList();
	}
	public double meilleurOffre(long idProd){
		Query query=em.createNamedQuery("Offre.meilleurPrix");
		Produit produit=pr.trouve(idProd);
		query.setParameter("produit", produit);
		double meilleurOffre;
		if(query.getSingleResult()!=null)
		{ meilleurOffre=(Double)query.getSingleResult();
		/*System.out.println(list.size());
		if(list.size())
			return produit.getPrixMin()-0.00001; 
		else
		return (Double) list.get(0);*/
		System.out.println("la meilleur offre est"+meilleurOffre);
		return meilleurOffre;}
		else return 0;
	}
	@Override
	public void supprimer(long idClient,long idProd) {
		Offre offre=trouver(idClient, idProd);
		System.out.println("idClient: "+offre.getClient().getId()+"idProd"+offre.getProduit().getId() );
		em.remove(offre);
		
	}
	@Override
	public List<Offre> listeOffreProprietaire(long idProprietaire) {
		Query query=em.createNamedQuery("Offre.listOffres.prod.proprietaire",Offre.class);
		Client client=cr.trouve(idProprietaire);
		query.setParameter("proprietaire", client);
		return query.getResultList();
	}
public List<Offre> offreAvecDateLimiteValide(Date date){
	Query query=em.createNamedQuery("Offre.date",Offre.class);
	query.setParameter("date", date);
	return query.getResultList();
}
@Override
public int ordreDernierOffre(long idClient, long idProd) {
	Offre offre=trouver(idClient, idProd);
	if(offre!=null)
		return offre.getOrdre();
	return 0;
}

}
