package br.com.lponto.controller;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.Validator;
import br.com.lponto.bean.Ponto;
import br.com.lponto.business.ArquivoBusiness;
import br.com.lponto.session.EmployeeSession;
import br.com.lponto.session.PointSession;
import com.github.sarxos.webcam.Webcam;
import java.awt.image.BufferedImage;
import java.nio.ByteBuffer;
import javax.inject.Inject;

/**
 *
 * @author Phelipe Melanias
 */
@Controller
public class PontoController extends MainController {

    @Inject
    private PointSession pointSession;

    @Inject
    private ArquivoBusiness arquivoBusiness;

    /**
     * @deprecated CDI eyes only
     */
    protected PontoController() {
        this(null, null, null);
    }

    @Inject
    public PontoController(Result result, Validator validator, EmployeeSession employeeSession) {
        super(result, validator, employeeSession);
    }

    @Get("/ponto/register")
    public void registerForm() {
        result.include("title", "Registrar ponto");
    }

    @Post("/ponto/register")
    public void addRegister() {
        Webcam webcam = Webcam.getDefault();
        webcam.open();
        BufferedImage picture = webcam.getImage();
        webcam.close();

        Ponto ponto = new Ponto();
        ponto.setFile(arquivoBusiness.convertBufferedImageToByteArray(picture));
        ponto.setMimeType("image/png");

        pointSession.setPonto(ponto);
        result.redirectTo(this).registerForm();
    }

    @Post("/ponto/confirm")
    public void confirmRegister() {
        result.nothing();
    }
}

/*

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

*/