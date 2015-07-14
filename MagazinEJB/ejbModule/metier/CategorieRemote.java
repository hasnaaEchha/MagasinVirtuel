package metier;

import java.util.List;

import javax.ejb.Remote;

import bean.Categorie;

@Remote
public interface CategorieRemote {
	public Categorie creer(String nom,Categorie categorieParent);
	public Categorie trouver(String nom);
	public List<Categorie> categories();
	public long nombreProdVenduByCat(long idCategorie);
	public Categorie trouver(long id);
	public void supprimer(long id);
}
