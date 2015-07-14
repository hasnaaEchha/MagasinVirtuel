package metier;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import org.primefaces.model.UploadedFile;

import bean.Categorie;
import bean.Client;
import bean.Offre;
import bean.Produit;
import bean.TypeProduit;

@Remote
public interface ProduitRemote {
	public Produit creer(String nom, String marque, Date dateLimite, double prixMin,boolean vendu,byte[]image,
			TypeProduit typeProduit, Categorie categorie, Client proprietaire);
	public Produit ajoutImage(byte[] img,long id);
	public List<Produit> produits();
	public byte[] getImageById(long id);
	public List<Produit> listProduitByClient(long idClient);
	public List<Produit> listProduitEnVente(long idClient);
	public List<Produit> produitsVendu();
	public void remove(long id);
	public Produit trouve(long id);
	public void removeByIdClient(long idClient);
	public void achatProduit(long idClient,long idProd);
	public List<Produit>listProdByTypeAndCat(long idCat,long idTypeProd);
	public List<Produit>listProdByTypeProd(long idTypeProd);
	public List<Produit>listProdByCat(long idCat);
	public List<Produit> produitAcheteByClient(long idClient);
	public double prixVente(long idProduit);
	public void supprimer(long id);
	
}
