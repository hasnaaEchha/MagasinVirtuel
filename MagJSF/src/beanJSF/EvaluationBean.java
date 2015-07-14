package beanJSF;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import metier.EvaluationRemote;

import org.primefaces.event.RateEvent;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.HorizontalBarChartModel;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

import services.Navigation;
import services.Supprimer;
import bean.Adresse;
import bean.Client;
import bean.Evaluation;
import bean.Produit;

@ManagedBean(name = "evaluation")
@SessionScoped
public class EvaluationBean implements Serializable {
	@EJB
	EvaluationRemote er;
	private long id;
	private int frais;
	private int respectDelaiLivraison;
	private int credibiliteVendeur;
	private String description;
	private Client client;
	private Produit produit;
	private BarChartModel barModel;
	private HorizontalBarChartModel horizontalBarModel;
	private List<Evaluation> listEvaluation;
	@ManagedProperty(value = "#{navigation}")
	private Navigation navigation;
	@ManagedProperty(value="#{supprimer}")
	Supprimer delete;

	public int getFrais() {
		return frais;
	}

	public void setFrais(int frais) {
		this.frais = frais;
	}

	public int getRespectDelaiLivraison() {
		return respectDelaiLivraison;
	}

	public void setRespectDelaiLivraison(int respectDelaiLivraison) {
		this.respectDelaiLivraison = respectDelaiLivraison;
	}

	public int getCredibiliteVendeur() {
		return credibiliteVendeur;
	}

	public void setCredibiliteVendeur(int credibiliteVendeur) {
		this.credibiliteVendeur = credibiliteVendeur;
	}

	public void handleRate(RateEvent rateEvent) {
		int rate = (Integer) rateEvent.getRating();
		// Add facesmessageation recuperer sa valeur

		/*
		 * pour chaque sujet de l'evalu
		 */
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<Evaluation> getListEvaluation() {
		listEvaluation = listEvaluations();
		return listEvaluation;
	}

	public void setListEvaluation(List<Evaluation> listEvaluation) {
		this.listEvaluation = listEvaluation;
	}

	public String save() {
		FacesContext context = FacesContext.getCurrentInstance();
		ClientBean clientBean = context.getApplication().evaluateExpressionGet(
				context, "#{client}", ClientBean.class);
		Map<String ,String> params=FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String idProduitString=params.get("prod_id");
		long idProd=Long.parseLong(idProduitString);
		er.creer(frais, respectDelaiLivraison, credibiliteVendeur, description,
				clientBean.getId(), idProd);
		return navigation.toWelcome();

	}
	
	public List<Evaluation> listEvaluations() {

		return er.evaluations();
	}

	public BarChartModel getBarModel() {
		barModel=new BarChartModel();
		ChartSeries frais = new ChartSeries();
		
		frais.setLabel("Boys");
		for (int i = 1; i <= 10; i++)
			frais.set(i,er.listEvalFrais(i).size());
		barModel.addSeries(frais);
        
		return barModel;
	}
	public void setBarModel(BarChartModel barModel) {
		this.barModel = barModel;
	}

	public HorizontalBarChartModel getHorizontalBarModel() {
		return horizontalBarModel;
	}

	public void setHorizontalBarModel(HorizontalBarChartModel horizontalBarModel) {
		this.horizontalBarModel = horizontalBarModel;
	}
	public String supprimerEvaluation(long id) {
		
		delete.supprimer(er.trouve(id));
		return navigation.toEvaluations();
		
	}



	public Supprimer getDelete() {
		return delete;
	}

	public void setDelete(Supprimer delete) {
		this.delete = delete;
	}

	public Navigation getNavigation() {
		return navigation;
	}

	public void setNavigation(Navigation navigation) {
		this.navigation = navigation;
	}
}
