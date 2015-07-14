package bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.primefaces.model.chart.LineChartModel;
@NamedQueries({
	@NamedQuery(name ="Evaluation.evaluations", query = "select eval from Evaluation eval"),
	@NamedQuery(name ="Evaluation.evaluations.frais", query = "select eval.frais from Evaluation eval where eval.frais=:frais"),
	@NamedQuery(name ="Evaluation.evaluationsByProd", query = "select eval from Evaluation eval where eval.produit=:produit"),
	@NamedQuery(name ="Evaluation.evaluationsByClient", query = "select eval from Evaluation eval where eval.client=:client"),
	@NamedQuery(name ="Evaluation.evalByProdAndClient", query = "select eval from Evaluation eval where eval.produit=:produit and eval.client=:client"),
	@NamedQuery(name ="Evaluation.evalFraisByEtoil", query = "select count(eval) from Evaluation eval where eval.frais=:frais"),
	@NamedQuery(name ="Evaluation.eval.prod.proprietaire", query = "select eval from Evaluation eval where eval.produit.proprietaire =:proprietaire"),
	@NamedQuery(name ="Evaluation.evalDelaisByEtoil", query = "select count(eval) from Evaluation eval where eval.respectDelai=:delais"),
	@NamedQuery(name ="Evaluation.evalCredibiliteByEtoil", query = "select count(eval) from Evaluation eval where eval.credibilitClient=:credibilite")
	})
@Entity
@Table(name="evaluations")
public class Evaluation implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private long frais;
	@Column(name="delais")
	private long respectDelai;
	@Column(name="credibilite")
	private long credibilitClient;
	private String description;
	 
	 
	@ManyToOne(optional=false)
	@JoinColumn(name = "evaluations_client", referencedColumnName = "id")
	private Client client;
	@ManyToOne(optional=false)
	@JoinColumn(name = "evaluations_produit", referencedColumnName = "id")
	private Produit produit;
	public long getFrais() {
		return frais;
	}
	
	public void setFrais(long frais) {
		this.frais = frais;
	}
	public long getRespectDelai() {
		return respectDelai;
	}
	public void setRespectDelai(long respectDelai) {
		this.respectDelai = respectDelai;
	}
	public long getCredibilitClient() {
		return credibilitClient;
	}
	public void setCredibilitClient(long credibilitClient) {
		this.credibilitClient = credibilitClient;
	}
	public long getId() {
		return id;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public Produit getProduit() {
		return produit;
	}
	public void setProduit(Produit produit) {
		this.produit = produit;
	}
	public Evaluation() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Evaluation(long frais, long respectDelai, long credibilitClient,String description,
			Client client, Produit produit) {
		super();
		this.frais = frais;
		this.respectDelai = respectDelai;
		this.credibilitClient = credibilitClient;
		this.description=description;
		this.client = client;
		this.produit = produit;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
}
