package supprission;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import metier.ClientRemote;
import bean.Client;
import bean.Produit;
@ManagedBean(name = "supprimerClient")
@SessionScoped
public class SupprimerClient extends Delete{
	@EJB
	ClientRemote cr;
	@Override
	public Object supprimer(Object input) {
		System.out.println("client");
		if ((input instanceof Client)) {
			Client client = (Client) input;
			cr.supprimer(client.getId());
			return client;
		}
		return successeur.supprimer(input);

	}

}
