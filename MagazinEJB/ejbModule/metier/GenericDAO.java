package metier;
import java.util.List;  
import javax.persistence.EntityManager;  
import javax.persistence.PersistenceContext;  
import javax.persistence.criteria.CriteriaQuery;  
public abstract class GenericDAO<T> {  
     private final static String UNIT_NAME = "magasin";  
     @PersistenceContext(unitName = UNIT_NAME)  
     private EntityManager em;  
     private Class<T> entityClass;  
     public GenericDAO(Class<T> entityClass) {  
          this.entityClass = entityClass;  
     }  
     public void save(T entity) {  
          em.persist(entity);  
          em.flush();  
     }  
     public void update(T entity) {  
          em.merge(entity);  
     }  
     public T find(Long entityID) {  
          return em.find(entityClass, entityID);  
     }  
     @SuppressWarnings({ "unchecked", "rawtypes" })  
     public List<T> findAll() {  
          CriteriaQuery cq = em.getCriteriaBuilder().createQuery();  
          cq.select(cq.from(entityClass));  
          return em.createQuery(cq).getResultList();  
     }  
}  