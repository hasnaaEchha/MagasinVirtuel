package metier;



import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;

import bean.Adresse;
import bean.Client;
@Local
public interface ClientLocal {
public Client creer(String nom,String prenom,String userName,String email,String tel,String password,Adresse adresse);
public Client trouve(String email);
public Client trouve(long id );
public List listClient(String email);
public boolean isClient(String email,String password);
public Client trouveByUserName(String userName);
public Client update(long id, String nom,String prenom,String userName,String email,String tel,String password,Adresse adresse);
public List<Client>allClient();
public byte[] getImageById(long id);
public void remove(long id);

}
