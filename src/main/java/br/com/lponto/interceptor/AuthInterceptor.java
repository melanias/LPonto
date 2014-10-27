package br.com.lponto.interceptor;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import br.com.caelum.vraptor.Accepts;
import br.com.caelum.vraptor.AroundCall;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.controller.ControllerMethod;
import br.com.caelum.vraptor.interceptor.SimpleInterceptorStack;

import br.com.lponto.controller.LoginController;
import br.com.lponto.session.EmployeeSession;

/**
 *
 * @author Phelipe Melanias
 */
@Intercepts//(before = AuthorizedInterceptor.class)
public class AuthInterceptor {

    @Inject private Result result;
    @Inject private HttpServletRequest request;
    @Inject private EmployeeSession employeeSession;

    @Accepts
    public boolean accepts(ControllerMethod method) {
        return !employeeSession.isLoggedIn();
    }

    @AroundCall
    public void intercept(SimpleInterceptorStack stack) {
        String uri = request.getRequestURI();

        if (uri.contains("/setup") ||  uri.contains("/login")) {
            stack.next();
        } else {
            result.redirectTo(LoginController.class).loginForm();
        }
    }
}