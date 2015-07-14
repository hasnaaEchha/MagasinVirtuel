package metier;

import javax.ejb.Remote;

import bean.AttributTypeProduit;

@Remote
public interface AttributTypeProduitRemote {
	public AttributTypeProduit creer(String nom);
	public AttributTypeProduit trouver(String nom);
}
