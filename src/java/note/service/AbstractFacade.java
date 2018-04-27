package note.service;

import java.util.List;
import javax.persistence.EntityManager;
import note.Note;


public abstract class AbstractFacade<T> {

    private Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public void addNote(T entity) {
        getEntityManager().persist(entity);
    }
    
    public void edit(T entity) {
        getEntityManager().merge(entity);
    }

    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
    
    public String viewNotes (String email) {
        List<T> list = getEntityManager().createNamedQuery("Note.viewNotes").setParameter("email", email).getResultList();
        String result = "";
        result = list.stream().map((o) -> o.toString()).reduce(result, String::concat);
        return result;
    }
    
    public String searchNote(int id) {
        T note = (T) getEntityManager().createNamedQuery("Note.searchNote").setParameter("id", id).getSingleResult();
        return note.toString();
    }    
    
    
    public T existsNote(int id) {
        return (T) getEntityManager().createNamedQuery("Note.existsNote").setParameter("id", id).getSingleResult();
    }    
    
    
    public String countNotes(String email) {
        List<T> notes = getEntityManager().createNamedQuery("Note.countNotes").setParameter("email", email).getResultList();
        return "" + notes.size();
    }
    
    public String deleteNote (int id) {
       int rowsChanged = getEntityManager().createNamedQuery("Note.deleteNote").setParameter("id", id).executeUpdate();   
       if (rowsChanged == 1)return "Ok";
       return "No";
    }
    
    public String checkNoteOwner (int id, String email) {
        List<T> notes = getEntityManager().createNamedQuery("Note.checkNoteOwner").setParameter("id", id).setParameter("email", email).getResultList();  
        if (notes.size() == 1) return "Ok";
        return "No";
    }
    
    public String modifyNote(int id, String content){
       int rowsChanged = getEntityManager().createNamedQuery("Note.modifyNote").setParameter("id", id).setParameter("content", content).executeUpdate();   
       if (rowsChanged == 1) return "Ok";
       return "No";       
    }
         
}
