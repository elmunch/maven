/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author sharki
 */
public class BaseDAO<T> {
  
    private Class<T> entityClass;
    private EntityManager em;
    
    public BaseDAO(Class<T> entityClass, EntityManager em) {
        this.entityClass = entityClass;
        this.em = em;
    }

    /**
     * @return the entityClass
     */
    public Class<T> getEntityClass() {
        return entityClass;
    }

    /**
     * @param entityClass the entityClass to set
     */
    public void setEntityClass(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    /**
     * @return the em
     */
    public EntityManager getEm() {
        return em;
    }

    /**
     * @param em the em to set
     */
    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void saveInstance(T instance) {
        em.persist(instance);
    }

    public void updateInstance(T instance) {
        em.merge(instance);
    }

    public void saveAllInstances(List<T> t) throws Exception {
        for (T t1 : t) {
            saveInstance(t1);
        }
    }

    public void updateAllInstances(List<T> t) throws Exception {
        for (T t1 : t) {
            updateInstance(t1);
        }
    }

    public void deleteInstance(T t) throws Exception {
        em.remove(em.merge(t));
    }

    public T findByID(int id) throws Exception {
        return em.find(entityClass, (long) id);
    }

    public T findByID(Object id) throws Exception {
        return em.find(entityClass, id);
    }

    public List<T> executeQuery(String query) throws Exception {
        return em.createQuery(query, entityClass).getResultList();
    }

    public List<T> executeQuery(String query, int start, int limit) throws Exception {
        return em.createQuery(query, entityClass).setFirstResult(start).setMaxResults(limit).getResultList();
    }

    public T executeQueryReturnUniqueResult(String query) throws Exception {
        return em.createQuery(query, entityClass).getSingleResult();
    }

    public Integer executeQueryReturnIntResult(String query) throws Exception {
        Object x = em.createQuery(query).getSingleResult();
        if (x instanceof Integer) {
            return (Integer) x;
        }
        if (x instanceof BigInteger) {
            return ((BigInteger) x).intValue();
        }
        return null;
    }

    public List<T> executeNativeQuery(String query) throws Exception {
        return em.createNativeQuery(query, entityClass).getResultList();
    }

    public List<T> executeNativeQuery(String query, int start, int limit) throws Exception {
        return em.createNativeQuery(query, entityClass).setFirstResult(start).setMaxResults(limit).getResultList();
    }

    public void executeUpdateNativeQuery(String query) throws Exception {
        em.createNativeQuery(query).executeUpdate();
    }

    public T executeNativeQueryReturnUniqueResult(String query) throws Exception {
        List x = em.createNativeQuery(query, entityClass).getResultList();
        if (x.isEmpty()) {
            return null;
        } else {
            return (T) x.get(0);
        }
    }

    public Integer executeNativeQueryReturnIntResult(String query) throws Exception {
        Object x = em.createNativeQuery(query).getSingleResult();
        if (x instanceof Integer) {
            return (Integer) x;
        }
        if (x instanceof BigInteger) {
            return ((BigInteger) x).intValue();
        }
        if (x instanceof BigDecimal) {
            return ((BigDecimal) x).intValue();
        }
        return null;
    }
    
    public List<T> executeNativeQueryReturnIntResult(String tableName, String columnName, Object value) throws Exception {
        StringBuilder stringBuilder = new StringBuilder("select * from ");
        return em.createNativeQuery(stringBuilder.append(tableName).append(" where ").append(columnName).append(" = ?").toString(), entityClass).setParameter(0, value).getResultList();
    }
}
