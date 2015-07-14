package beanJSF;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;

import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.PieChartModel;

import chart.ChartBar;
import chart.ChartStrategy;
import chart.diagFactory.FactoryDiag;
import chart.initFactory.FactoryInit;

@ManagedBean(name = "chart")
@SessionScoped
public class ChartBean {
	

	private ChartStrategy chartStrategy;
	private Object diag;

	@ManagedProperty(value = "#{factoryInit}")
	private FactoryInit factoryInit;
	@ManagedProperty(value = "#{factoryDiag}")
	private FactoryDiag factoryDiag;
	
	private Object model;
	private String strategy;
	private String sujet;
	private Map<String, String> sujets;
	private Map<String, Map<String, String>> data = new HashMap<String, Map<String, String>>();
	private Map<String, String> strategies;

	public FactoryInit getFactoryInit() {
		return factoryInit;
	}

	public void setFactoryInit(FactoryInit factoryInit) {
		this.factoryInit = factoryInit;
	}
	public FactoryDiag getFactoryDiag() {
		return factoryDiag;
	}

	public void setFactoryDiag(FactoryDiag factoryDiag) {
		this.factoryDiag = factoryDiag;
	}

	public Object getModel() {
		return model;
	}

	public void setModel(Object model) {
		this.model = model;
	}

	public Map<String, Map<String, String>> getData() {
		return data;
	}

	public String getSujet() {
		return sujet;
	}

	public void setSujet(String sujet) {
		this.sujet = sujet;
	}

	public Map<String, String> getStrategies() {
		return strategies;
	}

	public void setStrategies(Map<String, String> strategies) {
		this.strategies = strategies;
	}

	public String getStrategy() {
		return strategy;
	}

	public void setStrategy(String strategy) {
		this.strategy = strategy;
	}

	public Map<String, String> getSujets() {
		return sujets;
	}

	public void setSujets(Map<String, String> sujets) {
		this.sujets = sujets;
	}

	@PostConstruct
	public void init() {

		sujets = new HashMap<String, String>();
		sujets.put("Frais", "Frais");
		sujets.put("Credibilite", "Credibilite");
		sujets.put("Delais", "Delais");
		sujets.put("Delais", "Delais");
		sujets.put("CatPlusVendu", "CatPlusVendu");
		sujets.put("TypeProdPlusVendu", "TypeProdPlusVendu");

		Map<String, String> map = new HashMap<String, String>();
		map.put("Chart", "Chart");
		map.put("Pie", "Pie");
		map.put("Linear", "Linear");
		data.put("Frais", map);

		map = new HashMap<String, String>();
		map.put("Chart", "Chart");
		map.put("Pie", "Pie");
		map.put("Linear", "Linear");
		data.put("Credibilite", map);

		map = new HashMap<String, String>();
		map.put("Chart", "Chart");
		map.put("Pie", "Pie");
		map.put("Linear", "Linear");
		data.put("Delais", map);
		
		map = new HashMap<String, String>();
		map.put("Chart", "Chart");
		map.put("Pie", "Pie");
		data.put("CatPlusVendu", map);
		
		map = new HashMap<String, String>();
		map.put("Chart", "Chart");
		map.put("Pie", "Pie");
		data.put("TypeProdPlusVendu", map);

	}

	public Map<String, Long> initialiser() {
		factoryInit.setNom(sujet);
		return factoryInit.getDonne();
	}

	public void modeliser() {
		diag = factoryDiag.getDiag(strategy, initialiser(),sujet);

	}

	public Object getDiag() {
		return diag;
	}

	public void setDiag(Object diag) {
		this.diag = diag;
	}

	
	
	public void onSujetChange() {
		if (sujet != null && !sujet.equals(""))
			strategies = data.get(sujet);
		else
			strategies = new HashMap<String, String>();
	}

	public void displayLocation() {
		FacesMessage msg;
		if (strategy != null && sujet != null)
			msg = new FacesMessage("Selected", strategy + " of " + sujet);
		else
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid",
					"type de diag n'est pas selectionne.");

		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	
}
