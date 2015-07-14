package beanJSF;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.validation.constraints.Pattern;

import metier.AdresseRemote;
import bean.Client;

@ManagedBean(name = "adresse")
@SessionScoped
public class AdresseBean implements Serializable {
	@EJB
	private AdresseRemote ar;
	private long id;
	@Pattern(regexp = "[A-Za-z]+", message = "ca ne peut pas etre une ville!!")
	private String ville;
	private String addr;
	private int codePostale;
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public int getCodePostale() {
		return codePostale;
	}

	public void setCodePostale(int codePostale) {
		this.codePostale = codePostale;
	}

	public AdresseBean(long id, String ville, String addr, int codePostale) {
		super();
		this.id = id;
		this.ville = ville;
		this.addr = addr;
		this.codePostale = codePostale;
	}

	public AdresseBean() {
		super();
		// TODO Auto-generated constructor stub
	}

}
