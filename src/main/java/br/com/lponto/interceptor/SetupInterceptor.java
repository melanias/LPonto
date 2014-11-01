package br.com.lponto.interceptor;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import br.com.caelum.vraptor.Accepts;
import br.com.caelum.vraptor.AroundCall;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.controller.ControllerMethod;
import br.com.caelum.vraptor.interceptor.SimpleInterceptorStack;

import br.com.lponto.controller.SetupController;
import br.com.lponto.repository.FuncionarioRepository;

/**
 *
 * @author Phelipe Melanias
 */
@Intercepts(before={AuthInterceptor.class, AuthorizedInterceptor.class})
public class SetupInterceptor {

    @Inject private Result result;
    @Inject private HttpServletRequest request;
    @Inject private FuncionarioRepository funcionarioRepository;

    @Accepts
    public boolean accepts(ControllerMethod method) {
        return !funcionarioRepository.hasEmployees();
    }

    @AroundCall
    public void intercept(SimpleInterceptorStack stack) {
        if (request.getRequestURI().contains("/setup")) {
            stack.next();
        } else {
            result.redirectTo(SetupController.class).setup();
        }
    }
}