package chart.initFactory;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import bean.Categorie;
import bean.TypeProduit;
import metier.CategorieRemote;
import metier.TypeProduitRemote;

@ManagedBean(name = "mapId")
@SessionScoped
public class MapId implements Serializable {
	@EJB
	CategorieRemote cr;
	@EJB
	TypeProduitRemote tpr;
	private Map<String, Long> donnee;

	public Map<String, Long> getDonne(String nom) {
		
		if (nom.equals("CatPlusVendu")) {
		List<Categorie> liste=cr.categories();
		Iterator it=liste.iterator();
		donnee = new HashMap<String, Long>();
		do{
			long id=((Categorie)it.next()).getId();
			String nomCat=cr.trouver(id).getNom();
			donnee.put(nomCat,cr.nombreProdVenduByCat(id));
		}while(it.hasNext());
	
		}
		else if (nom.equals("TypeProdPlusVendu")) {
			donnee = new HashMap<String, Long>();
			List<TypeProduit> liste=tpr.TypeProduits();
			Iterator it=liste.iterator();
			do{
				long id=((TypeProduit)it.next()).getId();
				String nomTypPro=tpr.trouve(id).getNom();
				donnee.put(nomTypPro,tpr.nombreProdVenduByTypeProd(id));
			}while(it.hasNext());
			}

		return donnee;
		

	}

}
