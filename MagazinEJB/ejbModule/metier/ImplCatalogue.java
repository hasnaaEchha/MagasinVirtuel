package metier;

import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import bean.Catalogue;

@Stateless(name = "catalogue")
public class ImplCatalogue implements CatalogueLocal, CatalogueRemote {
	@PersistenceContext(unitName = "magasin")
	private EntityManager em;

	public void setEm(EntityManager em) {
		this.em = em;
	}

	public EntityManager getEm() {
		return em;
	}
	
	@Override
	public Catalogue creer(String nom) {
		Catalogue catalogue;
		Query query=this.em.createNamedQuery("Catalogue.trouver",Catalogue.class);
		if(query.getResultList().size()==0)
		{	
			catalogue=Catalogue.getInstance(nom);
			
			em.persist(catalogue);
			return catalogue;
		}
		
		Catalogue cat = em.find( Catalogue.class, new Long(1) );
		cat.setNom(nom);
		em.flush();  // changes to cat are automatically detected and persisted
		catalogue=Catalogue.getInstance(nom);
		return catalogue;
		
	}
 public Catalogue trouve(){
	 Catalogue catalogue=em.find(Catalogue.class, 1L);
	 return catalogue;
 }
}
