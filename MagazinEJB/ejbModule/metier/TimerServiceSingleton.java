package metier;



import java.util.Observable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
@Singleton
public class TimerServiceSingleton extends MyObservable{
	@EJB
	MyObserver oi;
	@PostConstruct
	void init(){
		
		addObserver(oi);
	}
	@Schedule(second="*/1", minute="*",hour="*", persistent=false)
    public void doWork(){
		
		notifyObservers();
    }
}
