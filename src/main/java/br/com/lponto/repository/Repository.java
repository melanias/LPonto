package br.com.lponto.repository;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Phelipe Melanias
 */
public interface Repository<E, I extends Serializable> {

    E find(I id);

    E merge(E entity);

    void remove(E entity);

    void refresh(E entity);

    void persist(E entity);

    List<E> listAll();

    List<E> listAllOrderByIdAsc();

    List<E> listAllOrderByIdDesc();

    List<E> listAllOrderedByField(String field);
}