package metier;

import java.util.List;

import javax.ejb.Local;

import bean.Categorie;

@Local
public interface CategorieLocal {
	public Categorie creer(String nom,Categorie categorieParent);
	public Categorie trouver(String nom);
	public List<Categorie> categories();
}
