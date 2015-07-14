package bean;

import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.nio.LongBuffer;
import java.sql.Blob;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hamcrest.Condition.Step;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
@NamedQueries({
	@NamedQuery(name ="Produit.produits", query = "select prod from Produit prod"),
	@NamedQuery(name ="Produit.imageById", query = "select prod.image from Produit prod where prod.id=:id"),
	@NamedQuery(name ="Produit.prodByClient", query = "select prod from Produit prod where prod.proprietaire=:client"),
	@NamedQuery(name ="Produit.prodByCat", query = "select prod from Produit prod where prod.categorie=:categorie "),
	@NamedQuery(name ="Produit.prodByTypeProduit", query = "select prod from Produit prod where prod.typeProduit=:typeProduit "),
	@NamedQuery(name ="Produit.produits.vendu", query = "select prod from Produit prod where prod.vendu= true"),
	@NamedQuery(name ="Produit.prodByClient.enVente", query = "select prod from Produit prod where prod.proprietaire=:client and vendu=false"),
	

	@NamedQuery(name ="Produit.nombreProdVenduByCat", query = "select count(prod) from Produit prod where vendu=1 and prod.categorie=:categorie"),
	@NamedQuery(name ="Produit.nombreProdVenduByTypeProd", query = "select count(prod) from Produit prod where vendu=1 and prod.typeProduit=:typeProduit"),
	@NamedQuery(name ="Produit.nombreProdVenduByCatAndTypeProd", query = "select prod from Produit prod where vendu=0 and prod.categorie=:categorie and prod.typeProduit=:typeProduit")
	})
@Entity
@Table(name="produits")
public class Produit implements Serializable{
	
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private String nom;
	private String marque;
	private boolean vendu;
	private Date dateLimite ;
	private double prixMin;
	private String description;
	@Transient
	private String idString;
	@Lob
	private byte[] image;
	@ManyToOne(optional = true)
	@JoinColumn(name = "type_produit_id", referencedColumnName = "id")
	private TypeProduit typeProduit;
	@ManyToOne(optional = false)
	@JoinColumn(name = "categorie_produit_id", referencedColumnName = "id")
	private Categorie categorie;
	@ManyToOne(optional = false)
	@JoinColumn(name = "client_produit_fk", referencedColumnName = "id")
	private Client proprietaire;
	@ManyToOne
	@JoinColumn(name = "client_produits_achetes", referencedColumnName = "id")
	private Client acheteur;
	
	public double getPrixMin() {
		return prixMin;
	}
	public void setPrixMin(double prixMin) {
		this.prixMin = prixMin;
	}
	public String getNom() {
		return nom;
	}
	public long getId() {
		return id;
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
	
	public Date getDateLimite() {
		return dateLimite;
	}
	public void setDateLimite(Date dateLimite) {
		this.dateLimite = dateLimite;
	}
	public boolean isVendu() {
		return vendu;
	}
	public void setVendu(boolean vendu) {
		this.vendu = vendu;
	}
	
	
	

	public TypeProduit getTypeProduit() {
		return typeProduit;
	}
	public void setTypeProduit(TypeProduit typtProduit) {
		this.typeProduit = typtProduit;
	}
	
	public Categorie getCategorie() {
		return categorie;
	}
	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}
	
	public Client getProprietaire() {
		return proprietaire;
	}
	public void setProprietaire(Client proprietaire) {
		this.proprietaire = proprietaire;
	}
	
	public String getIdString() {
		return String.valueOf(id);
	}
	
	
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	
	public Produit(String nom, String marque, Date dateLimite,
			double prixMin, boolean vendu, byte[] image, TypeProduit typeProduit,
			Categorie categorie, Client proprietaire) {
		super();
		this.nom = nom;
		this.marque = marque;
		this.vendu = vendu;
		this.dateLimite = dateLimite;
		this.prixMin = prixMin;
		this.image = image;
		this.typeProduit = typeProduit;
		this.categorie = categorie;
		this.proprietaire = proprietaire;
	}
	public Produit() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Produit(String nom, String marque, boolean vendu, Date dateLimite,String description,
			double prixMin, String idString, byte[] image,
			TypeProduit typeProduit, Categorie categorie, Client proprietaire) {
		super();
		this.nom = nom;
		this.marque = marque;
		this.vendu = vendu;
		this.dateLimite = dateLimite;
		this.description=description;
		this.prixMin = prixMin;
		this.idString = idString;
		this.image = image;
		this.typeProduit = typeProduit;
		this.categorie = categorie;
		this.proprietaire = proprietaire;
	}
	public String toString()
	{
		return nom;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
