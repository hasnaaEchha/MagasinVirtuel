package metier;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import bean.Catalogue;
import bean.Categorie;

@Stateless(name="categorie")
public class ImplCategorie implements CategorieLocal,CategorieRemote{
	@PersistenceContext
	EntityManager em;
	
	@Override
	public Categorie creer(String nom,Categorie categorieParent) {
		Categorie cat;
		Query query=this.em.createNamedQuery("Catalogue.trouver",Catalogue.class);
		
		if(trouver(nom)==null&&query.getResultList().size()!=0)
		/*
		 *  pour entrer dans ce bloc c'est necessaire qu'il y un catalogue dans notre bd et que ce nom du categorie n'existe pas
		 */
		{
			cat=new Categorie(nom, em.find(Catalogue.class, 1L),categorieParent);
			em.persist(cat);
			return cat;
		}
		return null;
	}
	@Override
	public Categorie trouver(String nom) {
		Query query=this.em.createNamedQuery("Categorie.existe.nom",Categorie.class);
		query.setParameter("nom", nom);
		List<Categorie> cat=query.getResultList();
		if( cat.size()!=0)
			return cat.get(0);
		return null;
	}
	public List<Categorie> categories(){
		Query query=this.em.createNamedQuery("Categorie.categories",Categorie.class);
		return query.getResultList();
	}
	@Override
	public long nombreProdVenduByCat(long idCategorie) {
		Categorie categorie=em.find(Categorie.class, idCategorie);
		Query query=this.em.createNamedQuery("Produit.nombreProdVenduByCat");
		query.setParameter("categorie", categorie);
		return (Long)query.getSingleResult();
	}
	@Override
	public Categorie trouver(long id) {
		
		return em.find(Categorie.class, id);
	}
	@Override
	public void supprimer(long id) {
			em.remove(trouver(id));;
		
	}
	
	
	
}
