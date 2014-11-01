package br.com.lponto.repository.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.lponto.bean.Funcionario;
import br.com.lponto.bean.Ponto;
import br.com.lponto.bean.Ponto_;
import br.com.lponto.repository.GenericRepository;
import br.com.lponto.repository.PontoRepository;
import br.com.lponto.repository.RepositoryException;

/**
 *
 * @author Phelipe Melanias
 */
public class PontoRepositoryImpl extends GenericRepository<Ponto, Long> implements PontoRepository {

    public PontoRepositoryImpl() {
        super(Ponto.class);
    }

    @Override
    public Ponto getLatestRecord(Funcionario funcionario) {
        Calendar cal = Calendar.getInstance();

        //Formatar data atual
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Ponto> c = cb.createQuery(Ponto.class);

            Root<Ponto> root = c.from(Ponto.class);
            c.select(root);

            Path<java.util.Date> horario = root.get(Ponto_.toDate);
            Path<Funcionario> employee = root.get(Ponto_.funcionario);

            Predicate p1 = cb.equal(horario, sdf.parse(sdf.format(cal.getTime())));
            Predicate p2 = cb.equal(employee, funcionario);

            List<Ponto> resultado = em.createQuery(c.where(p1, p2)).getResultList();

            if (resultado.isEmpty()) {
                return null;
            } else {
                return (Ponto) resultado.get(resultado.size()-1);
            }
        } catch (Exception e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public List<Ponto> getAllRecordsOf(Funcionario funcionario) {
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Ponto> c = cb.createQuery(Ponto.class);

            Root<Ponto> root = c.from(Ponto.class);
            c.select(root);

            return em.createQuery(c.where(cb.equal(root.get(Ponto_.funcionario), funcionario))).getResultList();
        } catch (Exception e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public List<Ponto> getAllRecordsOfTheDay(java.util.Date data, Funcionario funcionario) {
        if (data == null) {
            //Definir data
            Calendar cal = Calendar.getInstance();
            data = cal.getTime();
        }

        //Formatar data atual
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Ponto> c = cb.createQuery(Ponto.class);

            Root<Ponto> root = c.from(Ponto.class);
            c.select(root);

            Path<java.util.Date> horario = root.get(Ponto_.toDate);
            Path<Funcionario> employee = root.get(Ponto_.funcionario);

            Predicate p1 = cb.equal(horario, sdf.parse(sdf.format(data)));
            Predicate p2 = cb.equal(employee, funcionario);

            return em.createQuery(c.where(p1, p2)).getResultList();
        } catch (Exception e) {
            throw new RepositoryException(e);
        }
    }


}