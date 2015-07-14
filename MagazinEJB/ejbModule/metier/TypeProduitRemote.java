package metier;

import java.util.List;

import javax.ejb.Remote;

import bean.TypeProduit;
@Remote
public interface TypeProduitRemote {
	public TypeProduit creer(String nom);
	public TypeProduit trouver(String nom);
	public List<TypeProduit> TypeProduits();
	public long nombreProdVenduByTypeProd(long idTypeProduit);
	public TypeProduit trouve(long id);
	public void supprimer(long id);
	}
