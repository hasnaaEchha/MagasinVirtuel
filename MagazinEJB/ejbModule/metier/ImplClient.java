package metier;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import bean.Adresse;
import bean.Client;


@Stateless(name="client")
public class ImplClient implements  ClientRemote{
	@PersistenceContext(unitName = "magasin")
	private EntityManager em;
	@EJB
	AdresseRemote ar;
	@EJB
	ProduitRemote pr;
	public void setEm(EntityManager em) {
		this.em = em;
	}

	public EntityManager getEm() {
		return em;
	}
	public void remove(long id){
		pr.removeByIdClient(id);;
		getEm().remove(trouve(id));
		
	}
	public Client creer(String nom, String prenom, String userName,
			String email, String tel, String password, Adresse adresse,byte[] image) {
		
		Client client = new Client(nom, prenom, userName, email, tel, password, adresse, image);
		em.persist(client);
		client.setAdresse(adresse);
		em.flush();
		em.refresh(client);
		if(image!=null)
			System.out.println("implCLient non null");
		else System.out.println("implClient : null");
		return client;
	}

	@Override
	public Client trouve(String email) {
		
		Query query = this.em.createNamedQuery("Client.verifMail", Client.class);
		query.setParameter("email", email);
		if (query.getResultList().size() == 0)
			return null;
		Client client=(Client) query.getResultList().get(0);
		System.out.println(client.getId());
		return client;

	}

	public boolean isClient(String email, String password) {
		
		Query query = em.createNamedQuery("Client.verifMailPassword",
				Client.class);
		query.setParameter("email", email);
		query.setParameter("password", password);
		return query.getResultList().size() != 0;

	}

	@Override
	public List listClient(String email) {
		Query query = this.em
				.createNamedQuery("Client.verifMail", Client.class);

		query.setParameter("email", email);
		return query.getResultList();

	}

	@Override
	public Client update(long id, String nom, String prenom, String userName,
			String email, String tel, String password, Adresse adresse) {
		Client client = em.find(Client.class, id);
		System.out.println("le id est"+ id);
		client.setNom(nom);
		client.setEmail(email);
		client.setPrenom(prenom);
		client.setUserName(userName);
		client.setPassword(password);
		client.setTel(tel);
		
		em.flush();
		return client;
	}

	@Override
	public Client trouve(long id) {
		Client client = em.find(Client.class, id);
		return client;
	}
	public List<Client> allClient(){
		Query query = this.em
				.createNamedQuery("Client.listClient", Client.class);
		return query.getResultList();
	}
	public byte[] getImageById(long id){
		Client client=this.em.find(Client.class,id);
		
		return client.getImage();
	}

	@Override
	public Client trouveByUserName(String userName) {
		Query query=em.createNamedQuery("Client.clientByUserName",Client.class);
		query.setParameter("userName", userName);
		List<Client> list=query.getResultList();
		if(list.size()!=0)
			return list.get(0);
		return null;
	}

	@Override
	public void supprimer(long id) {
		em.remove(trouve(id));
		
	}
}
