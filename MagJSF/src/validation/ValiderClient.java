package validation;
import java.io.Serializable;

import javax.ejb.EJB;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.jws.WebService;
import javax.persistence.Transient;

import metier.ClientRemote;
import bean.Client;
@ManagedBean(name="validerClient")
@SessionScoped
public class ValiderClient extends Validateur implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EJB
	ClientRemote cr;
	public Object validerRequet(Object input) {
		if(!(input instanceof Client))
			return successeur.validerRequet(input);
		Client reponse=(Client)input;
		String email=reponse.getEmail();
		String userName=reponse.getUserName();
		String tel=reponse.getTel();
		System.out.println(email);
		if(tel!=null)
		{//donc on va valider l'inscription
			if(userNameUnique(userName)&&!emailExiste(email))
				{
				Client client=ajout(reponse);
				reponse.setId(client.getId());
				reponse.setUserName(client.getUserName());
				reponse.setAdresse(client.getAdresse());
				reponse.setTel(client.getTel());
				reponse.setNom(client.getNom());
				reponse.setPrenom(client.getPrenom());
				return reponse;}
			reponse.setPassword(null);
			if(!userNameUnique(userName))
				reponse.setUserName(null);
			if(emailExiste(email))
				reponse.setEmail(null);
			if(reponse.getImage()!=null)
			System.out.println("validtaionClient: non null");
			else
				System.out.println("validation Client: null");
			return reponse;
		}
		
		// ici l'authentification//
		String password=reponse.getPassword();
		boolean clientExist=(emailExiste(email)&&passwordCorrect(email, password));
		Client client;
		if(clientExist)
		{	
			client=cr.trouve(email);
			reponse.setId(client.getId());
			reponse.setUserName(client.getUserName());
			reponse.setAdresse(client.getAdresse());
			reponse.setTel(client.getTel());
			reponse.setNom(client.getNom());
			System.out.println("le nom est"+client.getNom());
			reponse.setPrenom(client.getPrenom());
			
			
		}
		else{
			reponse.setEmail(null);		
			reponse.setPassword(null);
			}	
		return reponse;
		//authentification//
	}
	public boolean emailExiste(String email){
		boolean b=(cr.trouve(email)!=null);
		return b;
	}
	public boolean passwordCorrect(String email,String password){
		System.out.println(email+" " +password);
		return cr.isClient(email, password);
	}
	public boolean userNameUnique(String userName){
		boolean b=(cr.trouveByUserName(userName)==null);
		return b;
	}
	public Client ajout(Client reponse){
		return cr.creer(reponse.getNom(), reponse.getPrenom(), reponse.getUserName(), reponse.getEmail(), reponse.getTel(), reponse.getPassword(), reponse.getAdresse(), reponse.getImage());
	}
}
