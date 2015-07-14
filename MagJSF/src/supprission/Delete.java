package supprission;

import java.io.Serializable;

import validation.Validateur;

public abstract class Delete implements Serializable{
protected Delete successeur;
	
	public void SetSuccesseur( Delete successeur )
	{
		this.successeur = successeur;
	}
	abstract public Object supprimer(Object input);

}
