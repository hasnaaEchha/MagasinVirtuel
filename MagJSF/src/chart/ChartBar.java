package chart;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

public class ChartBar implements ChartStrategy {
	private Map<String, Long> donne;
	private BarChartModel barModel;
	private String sujet;

	@Override
	public BarChartModel modeliser() {
		createBarModel();
		return getBarModel();
	}

	public ChartBar(Map<String, Long> donne) {
		super();
		this.donne = donne;
	}

	private void createBarModel() {
		barModel = initBarModel();

		barModel.setTitle(sujet);
		barModel.setLegendPosition("ne");

		Axis xAxis = barModel.getAxis(AxisType.X);
		xAxis.setLabel("Gender");

		Axis yAxis = barModel.getAxis(AxisType.Y);
		yAxis.setLabel("Births");
		yAxis.setMin(0);
		yAxis.setMax(200);
	}

	public BarChartModel getBarModel() {
		return barModel;
	}

	private BarChartModel initBarModel() {
		BarChartModel model = new BarChartModel();

		ChartSeries chartSeries = new ChartSeries();
		Set cles = donne.keySet();
		Iterator it = cles.iterator();
		chartSeries.setLabel(sujet);
		do{
			String id=(String) it.next();
			chartSeries.set(id, donne.get(id));
		}while (it.hasNext());
		model.addSeries(chartSeries);

		return model;
	}

	public void setBarModel(BarChartModel barModel) {
		this.barModel = barModel;
	}

	// / reste bcp a faire mais je suis dans lebon chemain

}
