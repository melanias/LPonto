package br.com.lponto.repository;

import java.util.Date;
import java.util.List;

import br.com.lponto.bean.Funcionario;
import br.com.lponto.bean.Ponto;

/**
 *
 * @author Phelipe Melanias
 */
public interface PontoRepository extends Repository<Ponto, Long> {

    Ponto getLatestRecord(Funcionario funcionario);

    List<Ponto> getAllRecordsOf(Funcionario funcionario);
    List<Ponto> getAllRecordsOfTheDay(Date data, Funcionario funcionario);
}