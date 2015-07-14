package metier;

import javax.ejb.Local;

import bean.Catalogue;
@Local
public interface CatalogueLocal {
	/*retourn le catalogue creer si pour la premiere fois on fait appel 
	si on a un catalogue dans la base des donnee  alors la on modifie le nom du catatlogue et on retourne le meme catalogue */
	public Catalogue creer(String name);
}
