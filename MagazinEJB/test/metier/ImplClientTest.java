package metier;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.jboss.ejb3.annotation.TransactionTimeout;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.OngoingStubbing;

import bean.Client;

@RunWith(MockitoJUnitRunner.class)
public class ImplClientTest {

	@Mock
	private EntityManager em;
	@Mock
	private TypedQuery query;
	@Mock
	private ImplClient cr;

	private static final String EMAIL = "hasnaa@yaoo.com";
	private static final String PASSWORD = "hasnaa";
	
	@Before
	public void initDependencies() throws Exception {
	    cr = new ImplClient();

	    
	  }
	@Ignore
	public void testAjout() {
        Client expectedSillyTable = new Client();
		
		when(em.createNamedQuery("select c from Client c where c.email = :email")).thenReturn(query);
        when(query.getSingleResult()).thenReturn(expectedSillyTable);
        boolean returnedSillyTable = (cr.trouve(EMAIL)!=null);
        assertSame(true, returnedSillyTable);

		boolean b = cr.creer("hasnaa", "hasnaa", "hasnaa", EMAIL, "tel",
				"azerty",null,null)!=null;

		assertFalse(b);
	}

	@Ignore
	public void testTrouve() {
		fail("Not yet implemented");
	}
	@Test
	public void listClient(){
		List clients =new ArrayList();
		clients.add(new Client("hasnaa", "hasnaa", "hasnaa", EMAIL, "tel","azerty",null));
		
	    when(em.createNamedQuery("Client.verifMail", Client.class)).thenReturn(query);
	    when( query.setParameter("email", EMAIL)).thenReturn(query);
	    when(query.getResultList()).thenReturn(clients);
	    cr.setEm(em);
	    assertEquals("Should have one extra scifi book", cr.listClient(EMAIL).size(),clients.size());

	}
	/*
	public void isClient(){
		List clients =new ArrayList();
		
	    when(em.createNamedQuery("Client.verifMailPassword", Client.class)).thenReturn(query);
	    when(query.getResultList()).thenReturn(clients);
	    cr.setEm(em);
	    assertEquals(cr.isClient(EMAIL, PASSWORD),clients.size()!=0);

	}
	*/
	@Test
	public void isClient(){
		List clients =new ArrayList();
		
	    when(em.createNamedQuery("Client.verifMailPassword", Client.class)).thenReturn(query);
	    when( query.setParameter("email", EMAIL)).thenReturn(query);
	    when(query.setParameter("password", PASSWORD)).thenReturn(query);
	    when(query.getResultList()).thenReturn(clients);
	    cr.setEm(em);
	    assertEquals(cr.isClient(EMAIL, PASSWORD),clients.size()!=0);

	}

}
