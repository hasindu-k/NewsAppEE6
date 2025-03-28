/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import java.util.Collections;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author nb
 */
@Stateless
public class NewsEntityFacade2 {
    @PersistenceContext(unitName = "NewsApp-ejbPU")
    private EntityManager em;

    //PostConstruct method
    @PostConstruct
    public void init(){
       System.out.println("Initializing NewsEntityFacade2"); 
    }
    public void create(NewsEntity2 newsEntity2) {
        em.persist(newsEntity2);
    }

    public void edit(NewsEntity2 newsEntity2) {
        em.merge(newsEntity2);
    }

    public void remove(NewsEntity2 newsEntity2) {
        em.remove(em.merge(newsEntity2));
    }

    public NewsEntity2 find(Object id) {
        return em.find(NewsEntity2.class, id);
    }

    public List<NewsEntity2> findAll() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(NewsEntity2.class));
        List<NewsEntity2> newsList = em.createQuery(cq).getResultList();
        
        Collections.sort(newsList, new NewsComparator());
        return newsList;
    }
    
    public List<NewsEntity2> findAllSortedDesc(){
        return findAll();
    }

    public List<NewsEntity2> findRange(int[] range) {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(NewsEntity2.class));
        Query q = em.createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        Root<NewsEntity2> rt = cq.from(NewsEntity2.class);
        cq.select(em.getCriteriaBuilder().count(rt));
        Query q = em.createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

}
