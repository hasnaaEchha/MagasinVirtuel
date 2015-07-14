package chart;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

public class ChartLinearStrategy implements ChartStrategy{
	private Map<String, Long> donne;
	private LineChartModel lineModel;
	private String sujet;
	public ChartLinearStrategy(Map<String, Long> donne) {
		super();
		this.donne = donne;
	}
	public LineChartModel getLineModel() {
        return lineModel;
    }
	
	@Override
	public LineChartModel modeliser() {
		createLineModels();
		return lineModel;
	}
	
	private void createLineModels() {
        lineModel =  initLinearModel();
        lineModel.setTitle("Linear Chart");
        lineModel.setLegendPosition("e");
        Axis xAxis = lineModel.getAxis(AxisType.X);
        xAxis.setMin(0);
        xAxis.setMax(10);
       
        Axis yAxis = lineModel.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(10);
         
    }
	 private LineChartModel initLinearModel() {
		 LineChartModel model = new LineChartModel();
	 
	        LineChartSeries series = new LineChartSeries();
	        series.setLabel(sujet);
	        Set cles = donne.keySet();
			Iterator it = cles.iterator();
			while (it.hasNext()){
				String  id=(String) it.next();
				series.set(id, donne.get(id));
			}
	       
	 /*
	        LineChartSeries series2 = new LineChartSeries();
	        series2.setLabel("Series 2");
	 
	        series2.set(1, 6);
	        series2.set(2, 3);
	        series2.set(3, 2);
	        series2.set(4, 7);
	        series2.set(5, 9);
	 
	      
	        model.addSeries(series2);*/
	        model.addSeries(series);
	         
	        return model;
	    }
}
