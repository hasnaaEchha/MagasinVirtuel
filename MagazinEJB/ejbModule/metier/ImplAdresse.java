package metier;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import bean.Adresse;
import bean.Client;
@Stateful(name="adresse")
public class ImplAdresse implements AdresseRemote{
@PersistenceContext(unitName="magasin")
private EntityManager em;
	@Override
	public Adresse creer(String ville,String addr,int codePostale) {
		/*
		 * validation partie metier de l'adresse
		 */
		/*
		 * 
		 */
		Adresse adresse=new Adresse(ville, addr, codePostale);
		em.persist(adresse);
		em.flush();
		em.refresh(adresse);
		return adresse;
	}
	public Adresse trouve(long id){
		Adresse adresse =em.find(Adresse.class, id);
		return adresse;
	}
	@Override
	public Adresse update(long id, String ville, String addr, int codePostale) {
		Adresse adresse =em.find(Adresse.class, id);
		adresse.setAddr(addr);
		adresse.setCodePostale(codePostale);
		
		adresse.setVille(ville);
		em.flush();
		return adresse;
	}
}
