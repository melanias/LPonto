package br.com.lponto.repository.impl;

import br.com.lponto.bean.Setor;
import br.com.lponto.repository.GenericRepository;
import br.com.lponto.repository.SetorRepository;

/**
 *
 * @author Phelipe Melanias
 */
public class SetorRepositoryImpl extends GenericRepository<Setor, Integer> implements SetorRepository {

    public SetorRepositoryImpl() {
        super(Setor.class);
    }

    @Override
    public boolean isUniqueSector(Setor setor) {
        return (setor.getId() == null) ? em.createQuery("FROM Setor s WHERE lower(s.nome) = ?1")
                                           .setParameter(1, setor.getNome().toLowerCase())
                                           .getResultList().isEmpty()
                                       : em.createQuery("FROM Setor s WHERE s.id <> ?1 AND lower(s.nome) = ?2")
                                           .setParameter(1, setor.getId())
                                           .setParameter(2, setor.getNome().toLowerCase())
                                           .getResultList().isEmpty();
    }

    @Override
    public boolean isUniqueAbbreviation(Setor setor) {
        return (setor.getId() == null) ? em.createQuery("FROM Setor s WHERE s.sigla = ?1")
                                           .setParameter(1, setor.getSigla())
                                           .getResultList().isEmpty()
                                       : em.createQuery("FROM Setor s WHERE s.id <> ?1 AND s.sigla = ?2")
                                           .setParameter(1, setor.getId())
                                           .setParameter(2, setor.getSigla())
                                           .getResultList().isEmpty();
    }
}