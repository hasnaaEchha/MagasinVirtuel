package metier;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import bean.Catalogue;
import bean.Categorie;
import bean.TypeProduit;
@Stateless(name="typeProduit")
public class ImplTypeProduit implements TypeProduitRemote,TypeProduitLocal{
	@PersistenceContext(unitName="magasin")
	private EntityManager em;
	public TypeProduit creer(String nom){
		TypeProduit tp;
		if(trouver(nom)==null){
			tp=new TypeProduit(nom);
			em.persist(tp);
			return tp;

		}
		return null;
	}
	public TypeProduit trouver(String nom){
		Query query=this.em.createNamedQuery("TypeProduit.existe.nom",TypeProduit.class);
		query.setParameter("nom", nom);
		List<TypeProduit> tp=query.getResultList();
		if( tp.size()!=0)
			return tp.get(0); 
		return null;
	}
	
	public List<TypeProduit> TypeProduits(){
		Query query=this.em.createNamedQuery("TypeProduit.typeProduits",TypeProduit.class);
		
		return query.getResultList();
	}
	@Override
	public long nombreProdVenduByTypeProd(long idTypeProduit) {
		TypeProduit typeProduit=em.find(TypeProduit.class,idTypeProduit) ;
		Query query=this.em.createNamedQuery("Produit.nombreProdVenduByTypeProd");
		query.setParameter("typeProduit", typeProduit);
		return (Long)query.getSingleResult();
	}
	public TypeProduit trouve(long id){
		return em.find(TypeProduit.class, id);
	}
	@Override
	public void supprimer(long id) {
		em.remove(trouve(id));
		
	}
	
}
