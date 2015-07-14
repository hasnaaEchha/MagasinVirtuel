package beanJSF;

import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import services.Navigation;
import services.Supprimer;
import services.Validation;
import metier.CatalogueRemote;
import metier.CategorieRemote;
import metier.ProduitRemote;
import metier.TypeProduitRemote;
import bean.Categorie;
import bean.Offre;
import bean.Produit;

@ManagedBean(name = "catalogue")
@RequestScoped
public class CatalogueBean implements Serializable {

	@EJB
	private CategorieRemote cr;
	@EJB
	private CatalogueRemote catalr;
	@EJB
	private ProduitRemote pr;
	@EJB
	private TypeProduitRemote tpr;
	private String nom;
	
	private String categorie;
	private String typeProduit;
	private List<Produit> listProduits;
	private Map<String, String> categories;
	private Map<String, Map<String, String>> data;
	private Map<String, String> typesProduits;
	private List<Categorie> listeCategories;
	private List<bean.TypeProduit> listeTypesProduits;
	private List<Produit>listProdByTypeAndCat;
	@ManagedProperty(value = "#{navigation}")
	private Navigation navigation;

	@ManagedProperty(value="#{supprimer}")
	Supprimer delete;

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void creer() {
		catalr.creer(nom);
	}

	

	public void setListeCategories(List<Categorie> listeCategories) {
		this.listeCategories = listeCategories;
	}

	public List<Produit> getListProduits() {
		List<Produit> produits;
		 produits=pr.produits();
		 return produits;
		
	}

	public StreamedContent getImage() {
		Produit prod = listProduits.get(0);
		byte[] imageInByteArray = prod.getImage();
		return new DefaultStreamedContent(new ByteArrayInputStream(
				imageInByteArray), "image/png");
	}

	@PostConstruct
	public void init() {
		data = new HashMap<String, Map<String, String>>();
		categories = new HashMap<String, String>();
		Map<String, String> map = new HashMap<String, String>();
		for (int i = 0; i <getListeTypesProduits().size(); i++){
			System.out.println(getListeTypesProduits().get(i).getNom());
			map.put(getListeTypesProduits().get(i).getNom(),
					getListeTypesProduits().get(i).getNom());
		}
		
		for (int i = 0; i < getListeCategories().size(); i++) {
			System.out.println(getListeCategories().get(i).getNom());
			categories.put(getListeCategories().get(i).getNom(),
					getListeCategories().get(i).getNom());
			data.put(getListeCategories().get(i).getNom(), map);
			typesProduits=map;
		}

	}

	public String getCategorie() {
		return categorie;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

	public String getTypeProduit() {
		return typeProduit;
	}

	public void setTypeProduit(String typeProduit) {
		this.typeProduit = typeProduit;
	}

	public Map<String, String> getCategories() {
		return categories;
	}

	public void setCategories(Map<String, String> categories) {
		this.categories = categories;
	}

	public Map<String, String> getTypesProduits() {
		return typesProduits;
	}

	public void setTypesProduits(Map<String, String> typesProduits) {
		this.typesProduits = typesProduits;
	}

	public List<Produit> listProdByTypeAndCat() {
		bean.TypeProduit typeProd=tpr.trouver(typeProduit);
		Categorie cat=cr.trouver(categorie);
		Produit produit;
		List<Produit> listProd = getListProduits();
		listProd=pr.listProdByTypeAndCat(cat.getId(), typeProd.getId());
		/*for (int i = 0; i < listProd.size(); i++) {
			
			produit = listProd.get(i);
			System.out.println(produit.getNom());
			if ((produit.isVendu()) || (produit.getCategorie().getId() != cat.getId())
					|| (produit.getTypeProduit().getId() != typeProd.getId())){
				System.out.println(produit.getCategorie().getId()==cat.getId());
				System.out.println(produit.getTypeProduit().getId()==typeProd.getId());
				listProd.remove(i);
				continue;
			}
			System.out.println("non supprime"+(produit.getCategorie().getId()==cat.getId()));
			System.out.println(produit.getTypeProduit().getId()==typeProd.getId());
			
		}*/
		listProdByTypeAndCat=listProd;
		
		return listProdByTypeAndCat;
	}

	public List<Categorie> getListeCategories() {
		List<Categorie> categories = cr.categories();
		listeCategories = new ArrayList<Categorie>();
		for (Categorie c : categories) {

			if (c.getCategorieParent() == null)
				listeCategories.add(c);
		}
		return listeCategories;
	}

	public List<bean.TypeProduit> getListeTypesProduits() {
		List<bean.TypeProduit> typesProduits = tpr.TypeProduits();
		listeTypesProduits = new ArrayList<bean.TypeProduit>();
		for (bean.TypeProduit c : typesProduits) {

			listeTypesProduits.add(c);
		}
		return listeTypesProduits;
	}

	public List<Produit> getListProdByTypeAndCat() {
		return listProdByTypeAndCat;
	}
	public void displayLocation() {
		FacesMessage msg;
		if (typeProduit != null && categorie!= null)
			msg = new FacesMessage("Selected", typeProduit + " of " + categorie);
		else
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid",
					"type du produit n'est pas selectionne.");

		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	public void onSujetChange() {
		if (categorie != null && !categorie.equals(""))
			typesProduits = data.get(categorie);
		else
			typesProduits = new HashMap<String, String>();
	}
public String consulterCatalogue(){
	listProdByTypeAndCat();
	//navigation.toCatalogue();
	return "hello";
}
public String supprimerProduit(long id) {
	System.out.println("le id est:"+id+" le nom du prod est :"+pr.trouve(id)+getNom());
	delete.supprimer(pr.trouve(id));
	return navigation.toCatalogue();
	
}
public Navigation getNavigation() {
	return navigation;
}

public void setNavigation(Navigation navigation) {
	this.navigation = navigation;
}

public Supprimer getDelete() {
	return delete;
}

public void setDelete(Supprimer delete) {
	this.delete = delete;
}


}
