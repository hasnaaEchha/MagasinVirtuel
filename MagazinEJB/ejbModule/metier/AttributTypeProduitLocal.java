package metier;

import javax.ejb.Local;

import bean.AttributTypeProduit;

@Local

public interface AttributTypeProduitLocal {
	public AttributTypeProduit creer(String nom);
	public AttributTypeProduit trouver(String nom);
}
