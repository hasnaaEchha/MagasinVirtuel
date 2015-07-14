package metier;

import javax.ejb.Remote;

import bean.Adresse;
import bean.Client;
@Remote
public interface AdresseRemote {
	public Adresse creer(String ville,String addr,int codePostale);
	public Adresse trouve(long id);
	public Adresse update(long id,String ville,String addr,int codePostale);
}
