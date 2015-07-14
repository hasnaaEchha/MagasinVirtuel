package chart.initFactory;

import java.io.Serializable;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.hibernate.validator.cfg.context.ReturnValueTarget;

import com.sun.org.glassfish.gmbal.ManagedAttribute;
@ManagedBean(name="factoryInit")
@SessionScoped
public class FactoryInit implements Serializable{
private String nom;
@ManagedProperty(value="#{mapEtoile}")
private MapEtoile mapEtoile;
@ManagedProperty(value="#{mapId}")
private MapId mapId;

public Map<String,Long> getDonne(){
	if(nom.equals("Frais")||nom.equals("Delais")||nom.equals("Credibilite"))
		return mapEtoile.getDonne(nom);
	if(nom.equals("CatPlusVendu")||nom.equals("TypeProdPlusVendu"))
		return mapId.getDonne(nom);
	return null;
}
public String getNom() {
	return nom;
}
public void setNom(String nom) {
	this.nom = nom;
}
public MapEtoile getMapEtoile() {
	return mapEtoile;
}
public void setMapEtoile(MapEtoile mapEtoile) {
	this.mapEtoile = mapEtoile;
}
public MapId getMapId() {
	return mapId;
}
public void setMapId(MapId mapId) {
	this.mapId = mapId;
}
}
