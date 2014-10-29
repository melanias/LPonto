package br.com.lponto.repository.impl;

import br.com.lponto.bean.Ponto;
import br.com.lponto.repository.GenericRepository;
import br.com.lponto.repository.PontoRepository;

/**
 *
 * @author Phelipe Melanias
 */
public class PontoRepositoryImpl extends GenericRepository<Ponto, Long> implements PontoRepository {

    public PontoRepositoryImpl() {
        super(Ponto.class);
    }
}