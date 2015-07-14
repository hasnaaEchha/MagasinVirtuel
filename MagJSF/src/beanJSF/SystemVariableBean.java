package beanJSF;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import bean.SystemVariable;
import metier.SystemVariableRemote;

@ManagedBean(name = "sysVar")
@SessionScoped
public class SystemVariableBean implements Serializable{
	@EJB
	SystemVariableRemote svr;
	private long id;
	private double fraisService;
	private int timer;
	private int maxMise;
	private SystemVariable systemVariable;
	@PostConstruct
	public void init(){
		svr.creer(10, 10, 20);
		systemVariable=SystemVariable.getInstance();
	}
	public long getId() {
		
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public double getFraisService() {
		this.fraisService=systemVariable.getFraisService();
		return fraisService;
	}
	public void setFraisService(double fraisService) {
		this.fraisService = fraisService;
	}
	public int getTimer() {
		this.timer=systemVariable.getTimer();
		return timer;
	}
	public void setTimer(int timer) {
		this.timer = timer;
	}
	public int getMaxMise() {
		this.maxMise=systemVariable.getMaxMise();
		return maxMise;
	}
	public void setMaxMise(int maxMise) {
		this.maxMise = maxMise;
	}
	public SystemVariableBean(long id, double fraisService, int timer,
			int maxMise) {
		super();
		this.id = id;
		this.fraisService = fraisService;
		this.timer = timer;
		this.maxMise = maxMise;
	}
	public SystemVariableBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public void update(){
		svr.creer(timer, maxMise, fraisService);
	}
	

}
