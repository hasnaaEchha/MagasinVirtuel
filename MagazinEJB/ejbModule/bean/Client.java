package bean;

import java.io.Serializable;
import java.util.Set;





import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@NamedQueries({             
		@NamedQuery(name = "Client.verifMailPassword", query = "select c from Client c where c.email = :email and c.password=:password"),
		@NamedQuery(name = "Client.listClient", query = "select c from Client c"),
		@NamedQuery(name = "Client.clientByUserName", query = "select c from Client c where c.userName =:userName"),
		@NamedQuery(name = "Client.verifMail", query = "select c from Client c where c.email = :email")
})
@Entity
@Table(name = "clients")
public class Client implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String nom;
	private String prenom;
	private String userName;
	private String email;
	private String tel;
	private String password;
	/*@OneToMany(mappedBy = "acheteur")
	private Set<Produit> listProduitsAchetes;*/
	@OneToMany(mappedBy = "proprietaire")
	private Set<Produit> listProduits;
	@OneToMany(mappedBy = "client")
	private Set<Offre> listOffres;
	@OneToOne
	@JoinColumn(name="client_adresse",referencedColumnName="id")
	private Adresse adresse;
	@Lob
	private byte[] image;
	public Set<Offre> getListOffres() {
		return listOffres;
	}

	public void setListOffres(Set<Offre> listOffres) {
		this.listOffres = listOffres;
	}

	

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}
	public Set<Produit> getListProduits() {
		return listProduits;
	}

	public void setListProduits(Set<Produit> listProduits) {
		this.listProduits = listProduits;
	}

	public long getId() {
		return id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public Client(String nom, String prenom, String userName, String email,
			String tel, String password,Adresse adresse) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.userName = userName;
		this.email = email;
		this.tel = tel;
		this.password = password;

	}
	

	public Client(String nom, String prenom, String userName, String email,
			String tel, String password, Adresse adresse, byte[] image) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.userName = userName;
		this.email = email;
		this.tel = tel;
		this.password = password;
		this.adresse = adresse;
		this.image = image;
	}

	public Client() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String toString() {
		return nom;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	
}
