package bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
@NamedQueries({
	@NamedQuery(name = "SystemVariable.trouver", query = "select sysVar from SystemVariable sysVar")
	})
@Entity
@Table(name = "system_variables")
public class SystemVariable implements  Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private int timer;
	@Column(name="max_mise")
	private int maxMise;
	private double fraisService;
	@Transient
	private static SystemVariable instance = null;
	public int getTimer() {
		return timer;
	}
	public void setTimer(int timer) {
		this.timer = timer;
	}
	public int getMaxMise() {
		return maxMise;
	}
	public void setMaxMise(int maxMise) {
		this.maxMise = maxMise;
	}
	public double getFraisService() {
		return fraisService;
	}
	public void setFraisService(double fraisService) {
		this.fraisService = fraisService;
	}
	public long getId() {
		return id;
	}
	private SystemVariable(int timer, int maxMise, double fraisService) {
		super();
		this.timer = timer;
		this.maxMise = maxMise;
		this.fraisService = fraisService;
	}
	private SystemVariable() {
		super();
		// TODO Auto-generated constructor stub
	}
	public final static SystemVariable getInstance() {
		// Le "Double-Checked Singleton"/"Singleton doublement vérifié" permet
		// d'éviter un appel coûteux à synchronized,
		// une fois que l'instanciation est faite.
		if (SystemVariable.instance == null) {
			// Le mot-clé synchronized sur ce bloc empêche toute instanciation
			// multiple même par différents "threads".
			// Il est TRES important.
			synchronized (SystemVariable.class) {
				if (SystemVariable.instance == null) {
					instance = new SystemVariable();
				}
			}

		}
		return instance;
	}
	public final static SystemVariable getInstance(int timer, int maxMise, double fraisService) {
		// Le "Double-Checked Singleton"/"Singleton doublement vérifié" permet
		// d'éviter un appel coûteux à synchronized,
		// une fois que l'instanciation est faite.
		if (SystemVariable.instance == null) {
			// Le mot-clé synchronized sur ce bloc empêche toute instanciation
			// multiple même par différents "threads".
			// Il est TRES important.
			synchronized (SystemVariable.class) {
				if (SystemVariable.instance == null) {
					instance = new SystemVariable(timer, maxMise,fraisService);
				}
			}

		}
		else {
			instance.setTimer(timer);
			instance.setFraisService(fraisService);
			instance.setMaxMise(maxMise);
			}
		return instance;
	}


	
}
