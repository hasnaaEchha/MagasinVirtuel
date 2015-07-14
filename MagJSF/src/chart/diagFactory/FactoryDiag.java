package chart.diagFactory;

import java.io.Serializable;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.LineChartModel;

import chart.ChartBar;
import chart.ChartLinearStrategy;
import chart.ChartPieStrategy;
import chart.ChartStrategy;
@ManagedBean(name="factoryDiag")
@SessionScoped
public class FactoryDiag implements Serializable{
	private ChartStrategy chartStrategy;

	public Object getDiag(String type,Map<String,Long> donne,String sujet){
		
			if(type.equals("Chart")){
			chartStrategy=new ChartBar(donne);
			
			}
			if(type.equals("Pie")){
				
				chartStrategy=new ChartPieStrategy(donne,sujet);
				
				}
			if(type.equals("Linear")){
				chartStrategy=new ChartLinearStrategy(donne);
				
				}
			return chartStrategy.modeliser();
	}
	
}
