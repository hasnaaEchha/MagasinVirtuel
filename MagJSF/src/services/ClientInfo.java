package services;

import java.io.Serializable;
import java.util.Map;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import bean.Client;
import metier.ClientRemote;
@ManagedBean(name="clientInfo")
@SessionScoped
public class ClientInfo implements Serializable{
	@EJB
	ClientRemote cr;
	private long idClient;
	private Client client;
	
	public long getIdClient() {
		return idClient;
	}
	public void setIdClient(long idClient) {
		this.idClient = idClient;
	}
	public void clientById() {
		Map<String ,String> params=FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String idClientString=params.get("client_id");
		idClient=Long.parseLong(idClientString);
		
		client=cr.trouve(idClient);
		
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	
	
	 

}
