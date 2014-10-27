package br.com.lponto.repository;

import br.com.lponto.bean.Funcionario;

/**
 *
 * @author Phelipe Melanias
 */
public interface FuncionarioRepository extends Repository<Funcionario, Integer> {

    boolean hasEmployees();
    
    boolean isUniqueCPF(Funcionario funcionario);
    boolean isUniqueName(Funcionario funcionario);

    Funcionario authenticate(String cpf, String senha);
}