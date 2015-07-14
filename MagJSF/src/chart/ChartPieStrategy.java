package chart;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.primefaces.model.chart.PieChartModel;

public class ChartPieStrategy implements ChartStrategy{
	private Map<String, Long> donne;
	private PieChartModel pieModel;
	private String sujet;
	
	@Override
	public Object modeliser() {
		
		createPieModel();
		return getPieModel();
		// TODO Auto-generated method stub
		
	}
	public PieChartModel getPieModel() {
        return pieModel;
    }
     
	
	 private void createPieModel() {
	        pieModel = new PieChartModel();
	        Set cles = donne.keySet();
			Iterator it = cles.iterator();
			while (it.hasNext()){
				String id=(String) it.next();
				
				pieModel.set(id, donne.get(id));
			}
	        pieModel.setTitle("Simple Pie");
	        pieModel.setLegendPosition("w");
	    }
	public ChartPieStrategy(Map<String, Long> donne,String sujet) {
		super();
		this.donne = donne;
		this.sujet=sujet;
		
	}

}
