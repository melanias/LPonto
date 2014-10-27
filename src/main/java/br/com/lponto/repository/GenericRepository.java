package br.com.lponto.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Phelipe Melanias
 */
public abstract class GenericRepository<E, I extends Serializable> implements Repository<E, I> {

    @Inject
    protected EntityManager em;

    private final Class<E> classe;

    public GenericRepository(Class<E> classe) {
        this.classe = classe;
    }

    @Override
    public E find(I id) {
        try {
            return em.find(classe, id);
        } catch (Exception e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public E merge(E entity) {
        try {
            return em.merge(entity);
        } catch (Exception e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public void remove(E entity) {
        try {
            em.remove(entity);
        } catch (Exception e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public void refresh(E entity) {
        try {
            em.refresh(entity);
        } catch (Exception e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public void persist(E entity) {
        try {
            em.persist(entity);
        } catch (Exception e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public List<E> listAll() {
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<E> c = cb.createQuery(classe);

            Root<E> root = c.from(classe);
            c.select(root);

            return em.createQuery(c).getResultList();
        } catch (Exception e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public List<E> listAllOrderByIdAsc() {
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<E> c = cb.createQuery(classe);

            Root<E> root = c.from(classe);

            return em.createQuery(c.orderBy(cb.asc(root.get("id")))).getResultList();
        } catch (Exception e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public List<E> listAllOrderByIdDesc() {
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<E> c = cb.createQuery(classe);

            Root<E> root = c.from(classe);

            return em.createQuery(c.orderBy(cb.desc(root.get("id")))).getResultList();
        } catch (Exception e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public List<E> listAllOrderedByField(String field) {
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<E> c = cb.createQuery(classe);

            Root<E> root = c.from(classe);

            return em.createQuery(c.orderBy(cb.asc(root.get(field)))).getResultList();
        } catch (Exception e) {
            throw new RepositoryException(e);
        }
    }
}