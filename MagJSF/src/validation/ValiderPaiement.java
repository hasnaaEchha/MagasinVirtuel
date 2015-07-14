package validation;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import bean.CartePaiement;
import bean.Client;
@ManagedBean(name="validerPaiement")
@SessionScoped
public class ValiderPaiement extends Validateur {
	
	@Override
	public Object validerRequet(Object input) {
		if(!(input instanceof CartePaiement))
			return successeur.validerRequet(input);
		return input;
	}


}
