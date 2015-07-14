package bean;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
@NamedQueries({
	@NamedQuery(name ="Categorie.existe.nom", query = "select cat from Categorie cat where cat.nom=:nom"),
	@NamedQuery(name ="Categorie.categories", query = "select cat from Categorie cat")
	

	})
@Entity
@Table(name="categories")
public class Categorie implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private String nom;
	@ManyToOne(optional = false)
	@JoinColumn(name = "catalogue_categorie_id", referencedColumnName = "id")
	private Catalogue catalogue;
	//self association
	@ManyToOne
    @JoinColumn(name="categorie_parent_id")
    private Categorie categorieParent;
    @OneToMany(mappedBy="categorieParent")
	private Set<Categorie> listCat;
    ///////
    @OneToMany(mappedBy="categorie")
    private Set<Produit> produits;
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public long getId() {
		return id;
	}
	
	public Set<Categorie> getListCat() {
		return listCat;
	}
	public void setListCat(Set<Categorie> listCat) {
		this.listCat = listCat;
	}
	
	public Catalogue getCatalogue() {
		return catalogue;
	}
	public void setCatalogue(Catalogue catalogue) {
		this.catalogue = catalogue;
	}
	
	public Categorie getCategorieParent() {
		return categorieParent;
	}
	public void setCategorieParent(Categorie categorieParent) {
		this.categorieParent = categorieParent;
	}
	public Set<Produit> getProduits() {
		return produits;
	}
	public void setProduits(Set<Produit> produits) {
		this.produits = produits;
	}
	public Categorie() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Categorie(String nom, Catalogue catalogue, Categorie categorieParent) {
		super();
		this.nom = nom;
		this.catalogue = catalogue;
		this.categorieParent = categorieParent;
	}
	public String toString(){
		return nom;
	}
	
}
