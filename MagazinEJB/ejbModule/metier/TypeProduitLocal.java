package metier;

import java.io.InputStream;
import java.util.List;

import javax.ejb.Local;

import bean.TypeProduit;
@Local
public interface TypeProduitLocal {
	public TypeProduit creer(String nom);
	public TypeProduit trouver(String nom);
	public List<TypeProduit> TypeProduits();
	
}
