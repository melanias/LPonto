package br.com.lponto.repository;

import br.com.lponto.bean.Funcionario;
import br.com.lponto.bean.Ponto;

/**
 *
 * @author Phelipe Melanias
 */
public interface PontoRepository extends Repository<Ponto, Long> {

    Ponto getLatestRecord(Funcionario funcionario);
}