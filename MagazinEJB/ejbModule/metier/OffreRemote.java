package metier;
import java.util.Date;
import java.util.List;
import javax.ejb.Remote;
import bean.Client;
import bean.Offre;
import bean.Produit;

@Remote
public interface OffreRemote {
	public Offre creer(double prix,Date date, Produit produit, Client client);
	public Offre trouver(long idClient,long idProd);
	public Offre update(long idClient,long idProd,double prix);
	public List<Offre> listProduitOffres(long idProd);
	public List<Offre> listClientOffres(long idClient); 
	public double meilleurOffre(long idProd);
	public void supprimer(long idClient,long idProd);
	public List<Offre> listeOffreProprietaire(long idProprietaire);
	public List<Offre> offreAvecDateLimiteValide(Date date);
	public int ordreDernierOffre(long idClient,long idProd);
}
