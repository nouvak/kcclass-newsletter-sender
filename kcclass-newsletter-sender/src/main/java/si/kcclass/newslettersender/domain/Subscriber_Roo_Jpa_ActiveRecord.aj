// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package si.kcclass.newslettersender.domain;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;
import si.kcclass.newslettersender.domain.Subscriber;

privileged aspect Subscriber_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager Subscriber.entityManager;
    
    public static final EntityManager Subscriber.entityManager() {
        EntityManager em = new Subscriber().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long Subscriber.countSubscribers() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Subscriber o", Long.class).getSingleResult();
    }
    
    public static List<Subscriber> Subscriber.findAllSubscribers() {
        return entityManager().createQuery("SELECT o FROM Subscriber o", Subscriber.class).getResultList();
    }
    
    public static Subscriber Subscriber.findSubscriber(Long id) {
        if (id == null) return null;
        return entityManager().find(Subscriber.class, id);
    }
    
    public static List<Subscriber> Subscriber.findSubscriberEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Subscriber o", Subscriber.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void Subscriber.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void Subscriber.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            Subscriber attached = Subscriber.findSubscriber(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void Subscriber.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void Subscriber.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public Subscriber Subscriber.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Subscriber merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}
