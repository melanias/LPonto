package br.com.lponto.session;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.com.lponto.bean.Funcionario;
import br.com.lponto.enumeration.Role;

/**
 *
 * @author Phelipe Melanias
 */
@SessionScoped
@Named("employeeSession")
public class EmployeeSession implements Serializable {
    private static final long serialVersionUID = 1L;

    private Funcionario funcionario;


    //Login and logout actions
    public void logout() { this.funcionario = null; }
    public boolean isLoggedIn() { return funcionario != null; }
    public void login(Funcionario funcionario) { this.funcionario = funcionario; }

    //getters
    public Integer getId() { return funcionario.getId(); }
    public String getNome() { return funcionario.getNome(); }
    public Role getPerfil() { return funcionario.getPerfil(); }
    public String getFirstAndLastName() { return funcionario.getFirstAndLastName(); }
}