package supprission;

import javax.ejb.EJB;

import metier.ClientRemote;
import metier.TypeProduitRemote;
import bean.Client;
import bean.TypeProduit;

public class SupprimerTypeProduit extends Delete{

	@EJB
	TypeProduitRemote tpr;
	@Override
	public Object supprimer(Object input) {
		System.out.println("typeproduit");
		TypeProduit typeProduit=new TypeProduit();
		if ((input instanceof TypeProduit)) {
			typeProduit = (TypeProduit) input;
			tpr.supprimer(typeProduit.getId());
			
		}
		return typeProduit;

	}
}
