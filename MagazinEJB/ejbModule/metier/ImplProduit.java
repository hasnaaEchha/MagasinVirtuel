package metier;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.primefaces.model.UploadedFile;

import com.sun.org.apache.bcel.internal.generic.GETSTATIC;

import bean.Categorie;
import bean.Client;
import bean.Offre;
import bean.Produit;
import bean.TypeProduit;

@Stateless(name = "produit")
public class ImplProduit implements ProduitRemote {
	@PersistenceContext
	private EntityManager em;
	@EJB
	ClientRemote cr;
	@EJB
	EvaluationRemote er;
	private long id;

	@Override
	public Produit creer(String nom, String marque, Date dateLimite,
			double prixMin, boolean vendu, byte[] image,
			TypeProduit typeProduit, Categorie categorie, Client proprietaire) {
		Produit prod;

		if (proprietaire != null) {
			proprietaire = cr.trouve(proprietaire.getEmail());
			prod = new Produit(nom, marque, dateLimite, prixMin, false, image,
					typeProduit, categorie, proprietaire);
			/*
			 * pour
			 */
			em.persist(prod);
			em.flush();
			em.refresh(prod);

			return prod;

		}
		return null;
	}

	public void remove(long id) {
		er.removeByProduitId(id);
		em.remove(trouve(id));
	}

	public Produit trouve(long id) {
		return em.find(Produit.class, id);
	}

	@Override
	public Produit ajoutImage(byte[] img, long id) {
		Produit produit = em.find(Produit.class, id);
		if (produit != null) {
			produit.setImage(img);
			em.flush();
		}
		return produit;
	}

	public byte[] getImageById(long id) {
		Produit produit = this.em.find(Produit.class, id);

		return produit.getImage();
	}

	/*
	 * public Client trouver(String email){ Query
	 * query=this.em.createNamedQuery("Produit.existe.email"Produit.class);
	 * query.setParameter("email",email); List<Produit>
	 * tp=query.getResultList(); if( tp.size()!=0) return tp.get(0); return
	 * null;
	 * 
	 * }
	 */
	public List<Produit> produits() {
		Query query = this.em.createNamedQuery("Produit.produits",
				Produit.class);

		return query.getResultList();
	}

	public List<Produit> listProduitByClient(long idClient) {
		Query query = this.em.createNamedQuery("Produit.prodByClient",
				Produit.class);
		Client client = em.find(Client.class, idClient);
		query.setParameter("client", client);
		return query.getResultList();
	}

	/*
	 * obtenir la liste des produits mis en ventre par un client
	 */
	@Override
	public List<Produit> listProduitEnVente(long idClient) {
		Query query = em.createNamedQuery("Produit.prodByClient.enVente",
				Produit.class);
		Client client = em.find(Client.class, idClient);
		query.setParameter("client", client);
		return query.getResultList();
	}

	public List<Produit> produitsVendu() {
		Query query = em.createNamedQuery("Produit.produits.vendu",
				Produit.class);
		return query.getResultList();

	}

	@Override
	public void removeByIdClient(long idClient) {
		List<Produit> list = listProduitByClient(idClient);
		for (int i = 0; i < list.size(); i++) {
			remove(list.get(i).getId());
		}

	}

	@Override
	public void achatProduit(long idClient, long idProd) {
		Client client = em.find(Client.class, idClient);
		Produit prod = em.find(Produit.class, idProd);
		prod.setVendu(true);
		em.flush();
	}

	public List<Produit> listProdByTypeAndCat(long idCat, long idTypeProd) {
		Query query = em.createNamedQuery(
				"Produit.nombreProdVenduByCatAndTypeProd", Produit.class);
		query.setParameter("categorie", em.find(Categorie.class, idCat));
		query.setParameter("typeProduit",
				em.find(TypeProduit.class, idTypeProd));
		return query.getResultList();
	}

	@Override
	public List<Produit> produitAcheteByClient(long idClient) {
		Client client = cr.trouve(idClient);
		Query query = em.createNamedQuery("Offre.listProdVendu.client",
				Produit.class);
		query.setParameter("client", client);
		return query.getResultList();
	}
	public double prixVente(long idProduit) {
		Produit produit = trouve(idProduit);
		Query query = em.createNamedQuery("Offre.prixVente.Prod");
		query.setParameter("produit", produit);
		if (query.getSingleResult() != null)
			return (Double) query.getSingleResult();
		return 0;
	}
	@Override
	public void supprimer(long id) {
		em.remove(trouve(id));
	}
	@Override
	public List<Produit> listProdByTypeProd(long idTypeProd) {
		Query query = em.createNamedQuery("Produit.prodByTypeProduit",
				Produit.class);
		query.setParameter("typeProduit",
				em.find(TypeProduit.class, idTypeProd));
		return query.getResultList();
	}
	@Override
	public List<Produit> listProdByCat(long idCat) {
		Query query = em.createNamedQuery("Produit.prodByCat", Produit.class);
		query.setParameter("categorie", em.find(Categorie.class, idCat));

		return query.getResultList();

	}
}
