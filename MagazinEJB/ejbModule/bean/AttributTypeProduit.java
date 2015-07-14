package bean;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
@NamedQueries({
	@NamedQuery(name ="AttributTypeProduit.existe.nom", query = "select atp from AttributTypeProduit atp where atp.nom=:nom")
	})
@Entity
@Table(name="attribut_type_produit")
public class AttributTypeProduit implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private String nom;
	@ManyToMany(mappedBy = "attType")
	private Set<TypeProduit> typeProds;
	public long getId() {
		return id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public Set<TypeProduit> getTypeProds() {
		return typeProds;
	}

	public void setTypeProds(Set<TypeProduit> typeProds) {
		this.typeProds = typeProds;
	}

	

	public AttributTypeProduit() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AttributTypeProduit(String nom) {
		super();
		this.nom = nom;
	}

}
