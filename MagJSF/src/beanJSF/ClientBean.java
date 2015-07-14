package beanJSF;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.primefaces.event.FlowEvent;
import org.primefaces.model.UploadedFile;

import bean.Adresse;
import bean.Client;
import bean.Offre;
import bean.Produit;
import services.Navigation;
import services.Supprimer;
import services.Validation;
import validation.Validateur;
import validation.ValiderClient;
import validation.ValiderMise;
import validation.ValiderPaiement;
import metier.AdresseRemote;
import metier.ClientRemote;
import metier.OffreRemote;
import metier.ProduitRemote;


@ManagedBean(name = "client")
@SessionScoped
public class ClientBean implements Serializable {

	@EJB
	ClientRemote cr;
	@EJB
	OffreRemote or;
	@EJB
	ProduitRemote pr;
	@EJB
	private AdresseRemote ar;
	private long id;
	@Pattern(regexp = "[A-Za-z]+", message = "ca ne peut pas etre un nom!!")
	private String nom;
	@Pattern(regexp = "[A-Za-z]+", message = "ca ne peut pas etre un prenom!!")
	private String prenom;
	@Pattern(regexp = "[A-Za-z0-1]+", message = "il doit etre une chaine de caractère!!")
	private String userName;
	@Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message = "verifier votre l'adresse email")
	private String email;

	private String tel;
	@Pattern(regexp = "(?=.*[0-9])(?=.*[a-z]).{6,20}", message = "doit"
			+ " contenir au moins un nombre une lettre miniscule, une majuscule et de taille entre 6 et20")
	private String password;
	private String confPassword;
	@ManagedProperty(value = "#{adresse}")
	private AdresseBean adresseBean;
	private UploadedFile file;
	private boolean loggedIn;
	private boolean loggedInAdmin;
	private boolean skip;
	@ManagedProperty(value = "#{navigation}")
	private Navigation navigation;
	@ManagedProperty(value="#{validation}")
	Validation validation;
	@ManagedProperty(value="#{supprimer}")
	Supprimer delete;

	private List<Client> allClient;

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public AdresseBean getAdresseBean() {
		return adresseBean;
	}

	public void setAdresseBean(AdresseBean adresseBean) {
		this.adresseBean = adresseBean;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Client> getAllClient() {
		this.allClient = cr.allClient();
		return allClient;
	}

	public void setAllClient(List<Client> allClient) {
		this.allClient = allClient;

	}

	public ClientBean(String nom, String prenom, String userName, String email,
			String tel, String password) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.userName = userName;
		this.email = email;
		this.tel = tel;
		this.password = password;
	}

	public ClientBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	// verifier que l'email donner vient juste de s'inscrire
	public boolean emailExist() {
		return cr.trouve(email) != null;

	}

	public String logout() {
		((HttpSession) FacesContext.getCurrentInstance().getExternalContext()

		.getSession(true)).invalidate();
		return navigation.toWelcome();
		
	}

	/*public void ajout() {
		FacesMessage msg;
		if (!emailExist()) {
			FacesContext context = FacesContext.getCurrentInstance();
			adresseBean = context.getApplication().evaluateExpressionGet(
					context, "#{adresse}", AdresseBean.class);
			Adresse adresse = ar.creer(adresseBean.getVille(),
					adresseBean.getAddr(), adresseBean.getCodePostale());
			cr.creer(nom, prenom, userName, email, tel, password, adresse);
			msg = new FacesMessage("Successful", "Welcome :" + userName);

		} else {
			msg = new FacesMessage("echec");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}*/

	

	public String validerOperationClient() {
		
		Client client;
		Adresse adresse;
		FacesContext context = FacesContext.getCurrentInstance();
		adresseBean = context.getApplication().evaluateExpressionGet(context,
				"#{adresse}", AdresseBean.class);
		
		if(tel!=null){
			adresse=ar.creer(adresseBean.getVille(), adresseBean.getAddr(),adresseBean.getCodePostale() );
			client	 = new Client(nom, prenom, userName, email, tel, password,adresse, file.getContents());
			System.out.println("client bean non null");
		}
		
		else{ 
			client	 = new Client(nom, prenom, userName, email, tel, password, null,null);
			System.out.println("client bean null");
		}
		/*
		 * pattern CoF
		 */
	
		validation.valider(client);
		/*
		 * pattern CoF
		 */
		if (client.getEmail() != null&&(client.getPassword()!=null||client.getUserName()!=null)) {
			loggedIn = true;
			id = client.getId();
			nom = client.getNom();
			prenom = client.getNom();
			tel = client.getTel();
			userName = client.getUserName();
			adresseBean.setVille(client.getAdresse().getVille()); adresseBean.setAddr(client.getAdresse().getAddr());adresseBean.setCodePostale(client.getAdresse().getCodePostale());
			context.addMessage(null, new FacesMessage("Successful",  "Your message: " + userName) );
			return navigation.toWelcome();
		}
		if(client.getPassword()==null)
			return "stay here";
        context.addMessage(null, new FacesMessage("Second Message", "Additional Message Detail"));
		return navigation.toLogin();
	}
	
	public String doLoginAdmin() {
		if (email != null && cr.isClient(email, password)&&cr.trouve(email).getUserName().equals("admin")) {
			loggedIn = true;
			loggedInAdmin=true;
			Client client = cr.trouve(email);
			id = client.getId();
			nom = client.getNom();
			prenom = client.getNom();
			tel = client.getTel();
			userName = client.getUserName();
			adresseBean = convAdresseToAdresseBean(client.getAdresse());

			return navigation.toAdminPage();
		}
		/*
		 * il faut recuperer toutes les attribut du clients et precisement l'id
		 * dans le clientBean
		 */

		return navigation.toLoginAdmin();
	}

	public String onFlowProcess(FlowEvent event) {
		if (skip) {
			skip = false; // reset in case user goes back
			return "confirm";
		} else {
			return event.getNewStep();
		}
	}

	public Navigation getNavigation() {
		return navigation;
	}

	public void setNavigation(Navigation navigation) {
		this.navigation = navigation;
	}

	public List<Produit> listProduitVente() {
		;
		// idClient
		return pr.listProduitByClient(1L);
	}

	/*
	 * retourn la liste du nom des produit mises par le client
	 */
	public List<Produit> listOffresNomProds() {

		// idClient
		List<Offre> listClientOffre = or.listClientOffres(id);

		List<Produit> listProduit = new ArrayList<Produit>();

		for (int i = 0; i < listClientOffre.size(); i++) {
			listProduit.add(listClientOffre.get(i).getProduit());
		}
		return listProduit;
	}

	public List<Produit> listProduitEnVente() {
		return pr.listProduitEnVente(id);
	}


	public Adresse convAdresseBeanToAdresse(AdresseBean adresseBean) {
		Adresse adresse;
		adresse = new Adresse(adresseBean.getVille(), adresseBean.getAddr(),
				adresseBean.getCodePostale());
		return adresse;
	}

	public AdresseBean convAdresseToAdresseBean(Adresse adresse) {
		AdresseBean adresseBean;
		adresseBean = new AdresseBean(adresse.getId(), adresse.getVille(),
				adresse.getAddr(), adresse.getCodePostale());
		return adresseBean;
	}

	/*
	 * 
	 */
	/*
	 * pour modifier son profil
	 * 
	 * /
	 * 
	 * 
	 * /* besoin encore du validation
	 */
	public String update() {
		Client client = cr.trouve(id);
		long idAddress = client.getAdresse().getId();
		FacesContext context = FacesContext.getCurrentInstance();
		adresseBean = context.getApplication().evaluateExpressionGet(context,
				"#{adresse}", AdresseBean.class);

		Adresse adresse = ar.update(idAddress, adresseBean.getVille(),
				adresseBean.getAddr(), adresseBean.getCodePostale());

		client = cr.update(id, nom, prenom, userName, email, tel, password,
				adresse);
		return navigation.toProfile();
	}

	/*
	 * 
	 */
	public String supprimerClient(long id) {
		System.out.println("le id est: "+id+"le nom du client est :"+cr.trouve(id)+getNom());
		delete.supprimer(cr.trouve(id));
		return navigation.toMesClientsPage();
		
	}

	public String getConfPassword() {
		return confPassword;
	}

	public void setConfPassword(String confPassword) {
		this.confPassword = confPassword;
	}

	public Validation getValidation() {
		return validation;
	}

	public void setValidation(Validation validation) {
		this.validation = validation;
	}
	public List<Produit> mesAchats(){
		return pr.produitAcheteByClient(id);
	}
	public double prixAchat(long idProduit)
	{
		return pr.prixVente(idProduit);
	}
	/*
	 * pour l'inscription
	 */
	/*public void creer() {

		if (!emailExist()) {
			
			FacesContext context = FacesContext.getCurrentInstance();
			adresseBean = context.getApplication().evaluateExpressionGet(
					context, "#{adresse}", AdresseBean.class);
			Adresse adresse = ar.creer(adresseBean.getVille(),
					adresseBean.getAddr(), adresseBean.getCodePostale());
			cr.creer(nom, prenom, userName, email, tel, password, adresse);
			FacesMessage msg = new FacesMessage("Successful", "Welcome :"
					+ userName);

			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}*/

	public boolean isLoggedInAdmin() {
		return loggedInAdmin;
	}

	public void setLoggedInAdmin(boolean loggedInAdmin) {
		this.loggedInAdmin = loggedInAdmin;
	}

	public Supprimer getDelete() {
		return delete;
	}

	public void setDelete(Supprimer delete) {
		this.delete = delete;
	}

}