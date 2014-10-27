package br.com.lponto.repository;

import br.com.lponto.bean.Setor;

/**
 *
 * @author Phelipe Melanias
 */
public interface SetorRepository extends Repository<Setor, Integer> {

    boolean isUniqueSector(Setor setor);
    boolean isUniqueAbbreviation(Setor setor);
}