package beanJSF;

import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.validation.constraints.Future;
import javax.validation.constraints.Pattern;

import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import services.Navigation;
import services.Supprimer;
import services.Validation;
import validation.ValiderCategorie;
import bean.Categorie;
import bean.Client;
import bean.Offre;
import bean.Produit;
import bean.TypeProduit;
import metier.CategorieRemote;
import metier.ClientRemote;
import metier.OffreRemote;
import metier.ProduitRemote;
import metier.TypeProduitRemote;
///pour la gestion du catalogue
/*
 * verifier si on a pas encore créer un catalogue
 *  verifier si le type du produits ou la categorie existe deja
 *  validation du taille de l'image
 *  l'affichage des message indiquant la source de l'erreur
 *  
 */
@ManagedBean(name = "produit")
@SessionScoped
public class ProduitBean implements Serializable {

	@EJB
	ProduitRemote pr;
	@EJB
	CategorieRemote cr;
	@EJB
	TypeProduitRemote tpr;
	@EJB
	OffreRemote or;
	@EJB
	ClientRemote clr;
	private long id;
	@Pattern(regexp = "[A-Z a-z]+", message = "ca ne peut pas etre un nom!!") 
	private String nom;
	@Pattern(regexp = "[A-Z a-z0-1]+", message = "ca ne peut pas etre un nom!!")
	private String marque;
	@Future
	private Date dateLimite;
	private Date dateCreation;
	private String description;
	private double prixMin;
	private Categorie categorie;
	private Map<String, String> categories;
	private Map<String, String> typeProduits;
	private boolean vendu;
	private List<Produit> produitsVendu;
	private TypeProduit typeProduit;
	private String nomTypeProduit;
	private ClientBean proprietaire;
	private UploadedFile file;
	private String nomCategorie;
	private List<Map<String, String>> listOffres;
	@ManagedProperty(value="#{validation}")
	Validation validation;
	@ManagedProperty(value = "#{navigation}")
	private Navigation navigation;
	@ManagedProperty(value="#{supprimer}")
	Supprimer delete;
	private Produit produitUrl;
	public ProduitBean(long id, String nom, String marque, Categorie categorie,String description) {
		super();
		this.id = id;
		this.nom = nom;
		this.marque = marque;
		this.categorie = categorie;
		this.description=description;

	}

	public ProduitBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDateCreation() {
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");      
		    try {
		    	dateCreation = sdf.parse(sdf.format(new Date()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		
		this.dateCreation = dateCreation;
	}

	public Validation getValidation() {
		return validation;
	}

	public void setValidation(Validation validation) {
		this.validation = validation;
	}
		

	public String getNomTypeProduit() {
		return nomTypeProduit;
	}

	public void setNomTypeProduit(String nomTypeProduit) {
		this.nomTypeProduit = nomTypeProduit;
		typeProduit = tpr.trouver(nomTypeProduit);
	}

	public String getNomCategorie() {
		return nomCategorie;
	}

	public void setNomCategorie(String nomCategorie) {
		this.nomCategorie = nomCategorie;
		categorie = cr.trouver(nomCategorie);
	}

	public ProduitRemote getPr() {
		return pr;
	}

	public void setPr(ProduitRemote pr) {
		this.pr = pr;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	public double getPrixMin() {
		return prixMin;
	}

	public void setPrixMin(double prixMin) {
		this.prixMin = prixMin;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getMarque() {
		return marque;
	}

	public void setMarque(String marque) {
		this.marque = marque;
	}

	public boolean isVendu() {
		return vendu;
	}

	public void setVendu(boolean vendu) {
		this.vendu = vendu;
	}

	public Date getDateLimite() {
		
		return dateLimite;
	}

	public void setDateLimite(Date dateLimite) {
		DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");      
	    try {
	    	dateLimite = sdf.parse(sdf.format(dateLimite));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.dateLimite = dateLimite;
	}

	public TypeProduit getTypeProduit() {
		return typeProduit;
	}

	public void setTypeProduit(TypeProduit typeProduit) {
		this.typeProduit = typeProduit;
	}

	public Categorie getCategorie() {

		return categorie;
	}

	public void setCategorie(Categorie categorie) {

		this.categorie = categorie;
	}

	public Map<String, String> getCategories() {
		categories = new HashMap<String, String>();
		List<Categorie> cats = cr.categories();
		for (int i = 0; i < cats.size(); i++)
			categories.put(cats.get(i).toString(), cats.get(i).toString());
		return categories;
	}

	public Map<String, String> getTypeProduits() {
		typeProduits = new HashMap<String, String>();
		List<TypeProduit> typProd = tpr.TypeProduits();
		for (int i = 0; i < typProd.size(); i++)
			typeProduits.put(typProd.get(i).toString(), typProd.get(i)
					.toString());
		return typeProduits;
	}

	public void setCategories(Map<String, String> categories) {
		this.categories = categories;
	}

	public UploadedFile getFile() {

		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}
	public List<Produit> getProduitsVendu() {
		this.produitsVendu=pr.produitsVendu();
		return produitsVendu;
	}

	public void setProduitsVendu(List<Produit> produitsVendu) {
		this.produitsVendu = produitsVendu;
	}
	public String creer() {
		FacesContext context = FacesContext.getCurrentInstance();
		proprietaire = context.getApplication().evaluateExpressionGet(context,
				"#{client}", ClientBean.class);
		System.out.println(proprietaire.getId());
		Client client = clr.trouve(proprietaire.getId());
	//	Produit prod = pr.creer(nom, marque, dateLimite, prixMin, vendu,
		//		typeProduit, categorie, client);
		
		Produit prod=new Produit(nom, marque, dateLimite , prixMin,false,file.getContents(), typeProduit, categorie, client);
		validation.valider(prod);
		return navigation.toMesProduits();
		//if (file != null);
			//pr.ajoutImage(file.getContents(), prod.getId());
	}

	/*
	 * obtenir liste des offres faites pour un produit donnee
	 */
	public List<Map<String, String>> listOffres(long idProduit) {

		// idProd
		List<Offre> listProduitOffre = or.listProduitOffres(idProduit);

		List<Map<String, String>> listOffre = new ArrayList<Map<String, String>>();
		Map<String, String> msg;
		for (int i = 0; i < listProduitOffre.size(); i++) {
			msg = new HashMap<String, String>();

			msg.put("prix", String.valueOf(listProduitOffre.get(i).getPrix()));
			msg.put("userName", listProduitOffre.get(i).getClient()
					.getUserName());
			listOffre.add(msg);

		}
		return listOffre;
	}
public String achat(long prodId){
	FacesContext context = FacesContext.getCurrentInstance();
	ClientBean client = context.getApplication().evaluateExpressionGet(context,
			"#{client}", ClientBean.class);
	Long idClient=client.getId();
	/*
	 * pour test
	 */
	prodId=1L;
	return "client_id="+idClient+"&produit_id="+prodId;
}

public Navigation getNavigation() {
	return navigation;
}

public void setNavigation(Navigation navigation) {
	this.navigation = navigation;
}
public Offre getLastOffre(long idProduit){
	List<Offre> listProduitOffre = or.listProduitOffres(idProduit);
	if(listProduitOffre.size()>=1)
	return listProduitOffre.get(listProduitOffre.size()-1);
	return null;
	
}
public double meilleurOffre(long idProduit){
	Offre offre =getLastOffre(idProduit);
	return offre.getPrix();
}
public String supprimerProduit(long id) {
	
	delete.supprimer(pr.trouve(id));
	return navigation.toProduitsVendu();
	
}

public Supprimer getDelete() {
	return delete;
}

public void setDelete(Supprimer delete) {
	this.delete = delete;
}
public Produit urlIdProd() {
	Map<String ,String> params=FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
	String idProdString=params.get("prod_id");
	id=Long.parseLong(idProdString);
	produitUrl=pr.trouve(id);
	return produitUrl;
	
}
public Date wellFormedDate(Date date){
	DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");      
    try {
    	date = sdf.parse(sdf.format(date));
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    return date;
}
public Produit getProduitUrl() {
	return produitUrl;
}

public void setProduitUrl(Produit produitUrl) {
	this.produitUrl = produitUrl;
}

}
