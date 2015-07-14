package metier;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import bean.AttributTypeProduit;
import bean.TypeProduit;
@Stateless(name="attributTypeProduit")
public class ImplAttributTypeProduit implements AttributTypeProduitRemote,AttributTypeProduitLocal{
@PersistenceContext
EntityManager em;
	@Override
	public AttributTypeProduit creer(String nom) {
		AttributTypeProduit atp;
		if(trouver(nom)==null){
			atp=new AttributTypeProduit(nom);
			em.persist(atp);
			return atp;

		}
		return null;
	}

	@Override
	public AttributTypeProduit trouver(String nom) {
		Query query=this.em.createNamedQuery("AttributTypeProduit.existe.nom",AttributTypeProduit.class);
		query.setParameter("nom", nom);
		List<AttributTypeProduit> atp=query.getResultList();
		if( atp.size()!=0)
			return atp.get(0); 
		return null;
	}

}
