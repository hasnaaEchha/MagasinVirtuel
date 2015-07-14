package metier;

import java.util.Observable;

import javax.ejb.Remote;
@Remote
public interface MyObserver {
	public void update();
}
