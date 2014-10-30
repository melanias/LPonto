package br.com.lponto.repository.impl;

import java.sql.Date;
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
        Calendar calendar = Calendar.getInstance();

        //Formatar data atual
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Ponto> c = cb.createQuery(Ponto.class);

            Root<Ponto> root = c.from(Ponto.class);
            c.select(root);

            Path<Date> horario = root.get("horario");
            Path<Funcionario> employee = root.get("funcionario");

            Predicate p1 = cb.equal(horario, sdf.format(calendar.getTime()));
            Predicate p2 = cb.equal(employee, funcionario);

            List resultado = em.createQuery(c.where(p1).where(p2)).getResultList();

            if (resultado.isEmpty()) {
                return null;
            } else {
                return (Ponto) resultado.get(resultado.size()-1);
            }
        } catch (Exception e) {
            throw new RepositoryException(e);
        }
    }
}