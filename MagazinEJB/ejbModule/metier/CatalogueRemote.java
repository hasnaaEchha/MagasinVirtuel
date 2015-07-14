package metier;

import java.util.List;

import javax.ejb.Remote;

import bean.Catalogue;
import bean.Categorie;
@Remote
public interface CatalogueRemote {
	public Catalogue creer(String nom);
	public Catalogue trouve();
	
}
