package metier;



import java.util.List;

import javax.ejb.Remote;

import bean.Adresse;
import bean.Client;
@Remote
public interface ClientRemote {
public Client creer(String nom,String prenom,String userName,String email,String tel,String password,Adresse adresse,byte[]image);
public Client trouve(String email);
public Client trouve(long id );
public List listClient(String email);
public boolean isClient(String email,String password);
public Client trouveByUserName(String userName);
public Client update(long id, String nom,String prenom,String userName,String email,String tel,String password,Adresse adresse);
public List<Client>allClient();
public byte[] getImageById(long id);
public void remove(long id);
public void supprimer(long id);

}
