package bean;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.Table;
import javax.persistence.Transient;

import metier.CatalogueRemote;

@NamedQueries({
		@NamedQuery(name = "Catalogue.trouver", query = "select c from Catalogue c"),

		@NamedQuery(name = "Catalogue.nom", query = "select nom from Catalogue") ,
		@NamedQuery(name = "Catalogue.existe.nom", query = "select cat from Catalogue cat where cat.nom=:nom") })
@Entity
@Table(name = "catalogues")
public final class Catalogue implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6055477791568865446L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String nom;
	@OneToMany(mappedBy = "catalogue")
	private Set<Categorie> listCategories;

	@Transient
	private static Catalogue instance = null;

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Set<Categorie> getListCat() {
		return listCategories;
	}

	public void setListCat(Set<Categorie> listCat) {
		this.listCategories = listCat;
	}

	private Catalogue(String nom) {
		super();
		this.nom = nom;
		//n'essayer jamais de implementer le id sachant qu il est genere automatiquement  
		

	}

	private Catalogue() {
		super();
		// TODO Auto-generated constructor stub
	}

	public final static Catalogue getInstance() {
		// Le "Double-Checked Singleton"/"Singleton doublement vérifié" permet
		// d'éviter un appel coûteux à synchronized,
		// une fois que l'instanciation est faite.
		if (Catalogue.instance == null) {
			// Le mot-clé synchronized sur ce bloc empêche toute instanciation
			// multiple même par différents "threads".
			// Il est TRES important.
			synchronized (Catalogue.class) {
				if (Catalogue.instance == null) {
					instance = new Catalogue();
				}
			}

		}
		return instance;
	}

	

	public final static Catalogue getInstance(String nom) {
		// Le "Double-Checked Singleton"/"Singleton doublement vérifié" permet
		// d'éviter un appel coûteux à synchronized,
		// une fois que l'instanciation est faite.
		if (Catalogue.instance == null) {
			// Le mot-clé synchronized sur ce bloc empêche toute instanciation
			// multiple même par différents "threads".
			// Il est TRES important.
			synchronized (Catalogue.class) {
				if (Catalogue.instance == null) {
					instance = new Catalogue(nom);
				}
			}

		}
		return instance;
	}

	public void addCategorie(Categorie categorie) {
		instance.listCategories.add(categorie);
	}
}
