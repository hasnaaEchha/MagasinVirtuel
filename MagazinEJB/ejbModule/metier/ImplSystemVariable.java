package metier;

import javax.ejb.Remote;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import bean.Catalogue;
import bean.SystemVariable;

@Stateful(name="systemVariable")
public class ImplSystemVariable implements SystemVariableRemote{
	@PersistenceContext(unitName="magasin")
	EntityManager em;
	@Override
	public SystemVariable creer(int timer, int maxMise, double fraisService) {
		SystemVariable systemVariable;
		Query query=this.em.createNamedQuery("SystemVariable.trouver",SystemVariable.class);
		if(query.getResultList().size()==0)
		{	
			systemVariable=SystemVariable.getInstance(timer, maxMise,fraisService);
			
			em.persist(systemVariable);
			return systemVariable;
		}
		
		SystemVariable sysVar = em.find( SystemVariable.class, new Long(1) );
		sysVar.setTimer(timer);
		sysVar.setFraisService(fraisService);
		sysVar.setMaxMise(maxMise);
		em.flush();  // changes to cat are automatically detected and persisted
		systemVariable=SystemVariable.getInstance(timer, maxMise,fraisService);
		return systemVariable;
		
	}
	

	@Override
	public SystemVariable updateTimer(int timer) {
		
		SystemVariable systemVariable;
		SystemVariable sysVar = em.find( SystemVariable.class, new Long(1) );
		sysVar.setTimer(timer);
		em.flush();  // changes to cat are automatically detected and persisted
		double fraisService=sysVar.getFraisService();
		int maxMise=sysVar.getMaxMise();
		systemVariable=SystemVariable.getInstance(timer, maxMise, fraisService);
		return systemVariable;
	}
	@Override
	public SystemVariable updateFraisService(double fraisService) {
		SystemVariable systemVariable;
		SystemVariable sysVar = em.find( SystemVariable.class, new Long(1) );
		sysVar.setFraisService(fraisService);
		em.flush();  // changes to cat are automatically detected and persisted
		int timer=sysVar.getTimer();
		int maxMise=sysVar.getMaxMise();
		systemVariable=SystemVariable.getInstance(timer, maxMise, fraisService);
		return systemVariable;
	}
	@Override
	public SystemVariable updateMaxMise(int maxMise) {
		
		SystemVariable systemVariable;
		SystemVariable sysVar = em.find( SystemVariable.class, new Long(1) );
		sysVar.setTimer(maxMise);
		em.flush();  // changes to cat are automatically detected and persisted
		double fraisService=sysVar.getFraisService();
		int timer=sysVar.getTimer();
		systemVariable=SystemVariable.getInstance(timer, maxMise, fraisService);
		return systemVariable;
	}
	

}
