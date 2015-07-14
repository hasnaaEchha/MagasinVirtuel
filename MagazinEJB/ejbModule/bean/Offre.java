package bean;
import java.io.Serializable;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.joda.time.DateTime;
@NamedQueries({
	@NamedQuery(name ="Offre.trouver", query = "select o from Offre o where o.produit =:prod and o.client=:client"),
	@NamedQuery(name ="Offre.listOffres.prod", query = "select o from Offre o where o.produit =:prod"),
	@NamedQuery(name ="Offre.listOffres.prod.proprietaire", query = "select o from Offre o where o.produit.proprietaire =:proprietaire"),
	@NamedQuery(name ="Offre.listOffres.client", query = "select o from Offre o where o.client =:client"),
	@NamedQuery(name ="Offre.listProdVendu.client", query = "select o.produit from Offre o where o.client =:client and o.produit.vendu=true"),
	@NamedQuery(name ="Offre.date", query = "select o from Offre o where o.produit.dateLimite <=:date"),
	@NamedQuery(name ="Offre.prixVente.Prod", query = "select prix from Offre o where o.produit =:produit and o.produit.vendu=true"),
	@NamedQuery(name ="Offre.meilleurPrix", query = "select max(o.prix) from Offre o where o.produit =:produit")
	})
@Entity
@Table(name="offres")
public class Offre implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private double prix;
	private int ordre;
	@Temporal(TemporalType.DATE) 
	@Column(name = "DATE_FIELD")
	private Date date;
	@ManyToOne(optional=false)
	@JoinColumn(name="produit_offres",referencedColumnName="id")
	private Produit produit;
	@ManyToOne(optional=false)
	@JoinColumn(name="client_offres",referencedColumnName="id")
	private Client client;
	
	public double getPrix() {
		return prix;
	}
	public void setPrix(double prix) {
		this.prix = prix;
	}
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	public Produit getProduit() {
		return produit;
	}
	public void setProduit(Produit produit) {
		this.produit = produit;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public long getId() {
		return id;
	}
	public Offre() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Offre(double prix,Date date, Produit produit, Client client) {
		super();
		this.prix = prix;
		this.date = date;
		this.produit = produit;
		this.client = client;	
	}
	public int getOrdre() {
		return ordre;
	}
	public void setOrdre(int ordre) {
		this.ordre = ordre;
	}
	

}
