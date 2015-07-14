package validation;

import javax.ejb.Remote;

import bean.Client;

@Remote
public interface ValidationRemote {
	public Object valider( Object input);
	
}
