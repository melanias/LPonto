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

import br.com.lponto.annotations.Authorized;
import br.com.lponto.bean.Funcionario;
import br.com.lponto.enumeration.Role;
import br.com.lponto.enumeration.Status;
import br.com.lponto.repository.FuncionarioRepository;
import br.com.lponto.repository.SetorRepository;
import br.com.lponto.util.Utilities;

/**
 *
 * @author Phelipe Melanias
 */
@Controller
@Authorized(Role.GESTOR)
public class FuncionarioController extends MainController {

    private final Environment environment;

    @Inject
    private SetorRepository setorRepository;

    @Inject
    private FuncionarioRepository funcionarioRepository;

    /**
     * @deprecated CDI eyes only
     */
    protected FuncionarioController() {
        this(null, null, null);
    }

    @Inject
    public FuncionarioController(Result result, Validator validator, Environment environment) {
        super(result, validator);
        this.environment = environment;
    }

    @Get("/funcionario")
    public void list() {
        result.include("title", "Funcionários");
        result.include("addTitle", "Novo funcionário");
        result.include("editTitle", "Editar funcionário");

        //Funcionários
        result.include("funcionarios", funcionarioRepository.listAllOrderByIdAsc());
    }

    @Get("/funcionario/add")
    public void addForm() {
        result.include("title", "Novo funcionário");

        //Enumerations
        result.include("roles", Role.getAll());
        result.include("status", Status.getAll());

        //Setores
        result.include("setores", setorRepository.listAllOrderedByField("nome"));
    }

    @Transactional
    @Post("/funcionario/add")
    public void add(@Valid Funcionario funcionario) {
        validator.onErrorRedirectTo(this).addForm();

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
        result.include("successMessage", "Funcionário cadastrado com sucesso.");

        result.redirectTo(this).list();
    }

    @Get("/funcionario/edit/{id}")
    public void editForm(Integer id) {
        result.include("title", "Editar funcionário");
        result.include("funcionario", funcionarioRepository.find(id));

        //Enumerations
        result.include("roles", Role.getAll());
        result.include("status", Status.getAll());

        //Setores
        result.include("setores", setorRepository.listAllOrderedByField("nome"));
    }

    @Transactional
    @Post("/funcionario/edit/{funcionario.id}")
    public void edit(@Valid Funcionario funcionario) {
        if (validator.hasErrors()) {
            //Enumerations
            result.include("roles", Role.getAll());
            result.include("status", Status.getAll());

            //Setores
            result.include("setores", setorRepository.listAllOrderedByField("nome"));
        }

        validator.onErrorUsePageOf(this).editForm(funcionario.getId());

        //Definir e criptografar a senha
        if (funcionario.getSenha() == null) {
            funcionario.setSenha(funcionarioRepository.find(funcionario.getId()).getSenha());
        } else {
            funcionario.setSenha(Utilities.sha512(funcionario.getSenha()));
        }

        //Salvar alterações
        funcionarioRepository.merge(funcionario);
        result.include("successMessage", "Funcionário atualizado com sucesso.");

        result.redirectTo(this).list();
    }
}