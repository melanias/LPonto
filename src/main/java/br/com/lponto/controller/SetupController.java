package br.com.lponto.controller;

import java.util.Date;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.environment.Environment;
import br.com.caelum.vraptor.validator.Validator;

import br.com.lponto.bean.Funcionario;
import br.com.lponto.repository.FuncionarioRepository;
import br.com.lponto.util.Utilities;

/**
 *
 * @author Phelipe Melanias
 */
@Controller
public class SetupController extends MainController {

    private final Environment environment;

    @Inject
    private FuncionarioRepository funcionarioRepository;

    /**
     * @deprecated CDI eyes only
     */
    protected SetupController() {
        this(null, null, null);
    }

    @Inject
    public SetupController(Result result, Validator validator,Environment environment) {
        super(result, validator);
        this.environment = environment;
    }

    @Get("/setup")
    public void setup() {
        //Verificar se realmente é necessário executar o setup
        if (funcionarioRepository.hasEmployees()) {
            result.notFound();
        }

        result.include("title", "Setup");
    }

    @Transactional
    @Post("/setup")
    public void setup(@Valid Funcionario funcionario) {
        validator.onErrorRedirectTo(this).setup();

        //Definir data de cadastro
        funcionario.setData(new Date());

        //Definir e criptografar a senha
        if (funcionario.getSenha() == null) {
            funcionario.setSenha(Utilities.sha512(environment.get("passwd_default")));
        } else {
            funcionario.setSenha(Utilities.sha512(funcionario.getSenha()));
        }

        //Salvar funcionário
        funcionarioRepository.persist(funcionario);

        result.redirectTo(LoginController.class).loginForm();
    }
}