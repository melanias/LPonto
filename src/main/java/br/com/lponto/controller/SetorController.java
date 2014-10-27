package br.com.lponto.controller;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.Validator;

import br.com.lponto.bean.Setor;
import br.com.lponto.repository.SetorRepository;

/**
 *
 * @author Phelipe Melanias
 */
@Controller
public class SetorController extends MainController {

    @Inject
    private SetorRepository setorRepository;

    /**
     * @deprecated CDI eyes only
     */
    protected SetorController() {
        this(null, null);
    }

    @Inject
    public SetorController(Result result, Validator validator) {
        super(result, validator);
    }

    @Get("/setor")
    public void list() {
        result.include("title", "Setores");
        result.include("addTitle", "Novo setor");
        result.include("editTitle", "Editar setor");

        //Setores
        result.include("setores", setorRepository.listAllOrderedByField("nome"));
    }

    @Get("/setor/add")
    public void addForm() {
        result.include("title", "Novo setor");
    }

    @Transactional
    @Post("/setor/add")
    public void add(@Valid Setor setor) {
        validator.onErrorRedirectTo(this).addForm();

        //Salvar setor
        setorRepository.persist(setor);
        result.include("successMessage", "Setor cadastrado com sucesso.");

        result.redirectTo(this).list();
    }

    @Get("/setor/edit/{id}")
    public void editForm(Integer id) {
        result.include("title", "Editar setor");
        result.include("setor", setorRepository.find(id));
    }

    @Transactional
    @Post("/setor/edit/{setor.id}")
    public void edit(@Valid Setor setor) {
        validator.onErrorUsePageOf(this).editForm(setor.getId());

        //Salvar alterações
        setorRepository.merge(setor);
        result.include("successMessage", "Setor atualizado com sucesso.");

        result.redirectTo(this).list();
    }
}