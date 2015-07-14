package metier;

import javax.ejb.Remote;

import bean.SystemVariable;

@Remote
public interface SystemVariableRemote {
 public SystemVariable creer(int timer, int maxMise, double fraisService);
 public SystemVariable updateTimer(int timer);
 public SystemVariable updateMaxMise(int maxMise);
 public SystemVariable updateFraisService(double fraisService);
 
 
}