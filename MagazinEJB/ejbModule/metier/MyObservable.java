package metier;

import java.util.ArrayList;
import java.util.List;

public class MyObservable {
	List<MyObserver> listObserver=new ArrayList<MyObserver>();
	public void addObserver(MyObserver ob){
		listObserver.add(ob);
	
	}
	public void notifyObservers(){
		for(int i=0;i<listObserver.size();i++){
			listObserver.get(i).update();
		}
	}
}
