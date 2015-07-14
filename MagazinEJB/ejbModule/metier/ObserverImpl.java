package metier;


import java.util.Date;
import java.util.List;


import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


import bean.Offre;
import bean.Produit;
import metier.OffreRemote;
@Stateless
public class ObserverImpl implements MyObserver{
	@PersistenceContext(unitName = "magasin")
	private EntityManager em;
	@EJB
	OffreRemote or;
	@EJB
	ProduitRemote pr;
	@Override
	public void update() {
		Produit produit;
		List<Produit> list=pr.produits();
		for(int i=0;i<list.size();i++){
			produit=list.get(i);
			if(produit.getDateLimite().compareTo(new Date())<=0){
			
			 produit=em.find(Produit.class,produit.getId());
			produit.setVendu(true);
			em.flush();
			}
			
			
		}
			
	}
	

}
