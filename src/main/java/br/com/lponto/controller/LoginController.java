package br.com.lponto.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.SimpleMessage;
import br.com.caelum.vraptor.validator.Validator;

import br.com.lponto.bean.Funcionario;
import br.com.lponto.repository.FuncionarioRepository;
import br.com.lponto.session.EmployeeSession;

/**
 *
 * @author Phelipe Melanias
 */
@Controller
public class LoginController extends MainController {

    @Inject
    private FuncionarioRepository funcionarioRepository;

    /**
     * @deprecated CDI eyes only
     */
    protected LoginController() {
        this(null, null, null, null);
    }

    @Inject
    public LoginController(Result result, Validator validator, HttpServletRequest request, EmployeeSession employeeSession) {
        super(result, validator, request, employeeSession);
    }

    @Get("/login")
    public void loginForm() {
        if (employeeSession.isLoggedIn()) {
            result.redirectTo(IndexController.class).index();
        }

        result.include("title", "Acesso Restrito");
    }

    @Post("/login")
    public void login(final String cpf, final String senha) {
        //Validar CPF e Senha
        validator.addIf(cpf   == null, new SimpleMessage("cpf",   "Informe seu CPF."));
        validator.addIf(senha == null, new SimpleMessage("senha", "Informe sua senha."));
        validator.onErrorRedirectTo(this).loginForm();

        //Validar login
        Funcionario funcionario = funcionarioRepository.authenticate((cpf == null ? "" : cpf.trim()), (senha == null ? "" : senha.trim()));

        validator.addIf(funcionario == null, new SimpleMessage("login", "CPF e senha inválidos."));
        validator.onErrorRedirectTo(this).loginForm();

        //Efetuar login caso não tenha ocorrido algum erro
        employeeSession.login(funcionario);

        result.redirectTo(IndexController.class).index();
    }

    @Get("/logout")
    public void logout() {
        //Tive  que mudar para HttpServletRequest para
        //remover todas as sessões usadas pelo sistema
        request.getSession().invalidate();

        result.redirectTo(this).loginForm();
    }
}