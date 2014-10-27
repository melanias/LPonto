package br.com.lponto.controller;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.Validator;

/**
 *
 * @author Phelipe Melanias
 */
@Controller
public class IndexController extends MainController {

    /**
     * @deprecated CDI eyes only
     */
    protected IndexController() {
        this(null, null);
    }

    @Inject
    public IndexController(Result result, Validator validator) {
        super(result, validator);
    }

    @Get("/")
    public void index() {
        result.include("title", "LPonto");
        result.include("subtitle", "Sistema de Controle de Ponto");
    }
}
