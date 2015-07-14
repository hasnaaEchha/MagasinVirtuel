package bean;

import java.io.Serializable;
import java.util.List;
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
	@NamedQuery(name ="TypeProduit.existe.nom", query = "select tp from TypeProduit tp where tp.nom=:nom"),
	@NamedQuery(name ="TypeProduit.typeProduits", query = "select typProd from TypeProduit typProd")

	})
@Entity
@Table(name="types_produits")
public class TypeProduit implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private String nom;
	@ManyToMany
	@JoinTable(name = "type_produit_fk", 
			   joinColumns = {@JoinColumn (name = "type_id", referencedColumnName = "id")}, 
			   inverseJoinColumns = { @JoinColumn(name = "attribut_id", referencedColumnName = "id")})
	private List<AttributTypeProduit> attType;
	public long getId() {
		return id;
	}
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public List<AttributTypeProduit> getAttType() {
		return attType;
	}
	public void setAttType(List<AttributTypeProduit> attType) {
		this.attType = attType;
	}
	public TypeProduit( String nom) {
		super();
		
		this.nom = nom;
	}
	public TypeProduit() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String toString(){
		return nom;
	}
}
