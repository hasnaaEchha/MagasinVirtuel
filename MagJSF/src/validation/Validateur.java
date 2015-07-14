package validation;

import java.util.List;
import java.util.Map;

import javax.ejb.EJB;

import metier.ClientLocal;

public abstract class Validateur {
	protected Validateur successeur;
	
	public void SetSuccesseur( Validateur successeur )
	{
		this.successeur = successeur;
	}
	abstract public Object validerRequet(Object input);

}
