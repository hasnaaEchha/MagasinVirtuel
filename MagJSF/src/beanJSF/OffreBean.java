package beanJSF;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import services.Navigation;
import services.Validation;
import metier.ClientRemote;
import metier.OffreRemote;
import metier.ProduitRemote;
import bean.Client;
import bean.Offre;
import bean.Produit;
//
/*
 * verifier que le prix donne est le meilleur 
 *
 */

@ManagedBean(name = "offre")
@SessionScoped
public class OffreBean implements Serializable {
	@EJB
	OffreRemote or;
	@EJB
	ClientRemote clr;
	@EJB
	ProduitRemote pr;
	private long id;
	private double prix;
	private int ordre;
	@ManagedProperty(value="#{validation}")
	Validation validation;
	@ManagedProperty(value = "#{navigation}")
	private Navigation navigation;
	/*
	 * ca va etre communique par l'URL
	 */
	
	private Produit produit;
	private ClientBean clientBean;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getPrix() {
		return prix;
	}
	public void setPrix(double prix) {
		this.prix = prix;
	}
	public String miser() {
		FacesContext context = FacesContext.getCurrentInstance();
		clientBean = context.getApplication().evaluateExpressionGet(context,
				"#{client}", ClientBean.class);
		long idClient = clientBean.getId();
		/*
		 * pour l'id du produit il va etre recuperer a partir de l'url
		 */
		// juste pour tester le fonctionnement 
		Map<String ,String> params=FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String idProdString=params.get("id_prod");
		long idProd=Long.parseLong(idProdString);
		System.out.println("le param est"+idProdString);
		produit=pr.trouve(idProd);
		System.out.println("le param est"+idProd);
		//
		Offre offre=new Offre(prix,new Date(), produit, clr.trouve(idClient));
		offre=(Offre)validation.valider(offre);
		if(idClient==offre.getClient().getId());//afficher un message indiquant qu c'est impossible de miser sur sa propre ffre
		return navigation.toMesMises();
	}
	public int getOrdre() {
		return ordre;
	}

	public void setOrdre(int ordre) {
		this.ordre = ordre;
	}

	public Validation getValidation() {
		return validation;
	}

	public void setValidation(Validation validation) {
		this.validation = validation;
	}

	public Navigation getNavigation() {
		return navigation;
	}

	public void setNavigation(Navigation navigation) {
		this.navigation = navigation;
	}

}
