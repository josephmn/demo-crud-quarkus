package commons;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;

import java.io.Serializable;
import java.util.List;

public abstract class GenericServiceImpl<T, ID extends Serializable> implements GenericServiceAPI<T, ID> {

    public Class<T> clazz;

    public GenericServiceImpl(Class<T> entityClass) {
        this.clazz = entityClass;
    }

    public abstract EntityManager getEm();

    @Override
    @Transactional
    public T save(T entity) {
        getEm().persist(entity);
        return entity;
    }

    @Override
    @Transactional
    public T update(T entity) {
        getEm().merge(entity);
        return entity;
    }

    @Override
    @Transactional
    public void delete(ID id) {
        var em = getEm();
        em.remove(em.find(clazz, id));
    }

    @Override
    @Transactional
    public T get(ID id) {
        var em = getEm();
        return em.find(clazz, id);
    }

    @Override
    @Transactional
    public List<T> getAll() {
        EntityManager em = getEm();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(clazz);
        Root<T> rootEntry = cq.from(clazz);
        CriteriaQuery<T> all = cq.select(rootEntry);
        TypedQuery<T> allQuery = em.createQuery(all);
        return allQuery.getResultList();
    }

}
