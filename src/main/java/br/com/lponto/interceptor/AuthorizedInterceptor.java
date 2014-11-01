package br.com.lponto.interceptor;

import javax.inject.Inject;

import br.com.caelum.vraptor.Accepts;
import br.com.caelum.vraptor.AroundCall;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.controller.ControllerMethod;
import br.com.caelum.vraptor.interceptor.SimpleInterceptorStack;

import br.com.lponto.annotations.Authorized;
import br.com.lponto.controller.IndexController;
import br.com.lponto.enumeration.Role;
import br.com.lponto.session.EmployeeSession;

/**
 *
 * @author Phelipe Melanias
 */
@Intercepts
public class AuthorizedInterceptor {

    @Inject private Result result;
    @Inject private ControllerMethod method;

    @Inject private EmployeeSession employeeSession;

    @Accepts
    public boolean accepts(ControllerMethod method) {
        return method.containsAnnotation(Authorized.class)
                || method.getController().getType().isAnnotationPresent(Authorized.class);
    }

    @AroundCall
    public void intercept(SimpleInterceptorStack stack) {
        Authorized a = whereIsTheAnnotation(method);

        if (isAuthorized(a) || isAdministrator()) {
            stack.next();
        } else {
            result.redirectTo(IndexController.class).index();
        }
    }

    private boolean isAdministrator() {
        return employeeSession.getPerfil().equals(Role.ADMINISTRADOR);
    }

    private boolean isAuthorized(Authorized auth) {
        for (Role role : auth.value()) {
            if (role.equals(employeeSession.getPerfil())) {
                return true;
            }
        }

        return false;
    }

    private Authorized whereIsTheAnnotation(ControllerMethod method) {
        return method.containsAnnotation(Authorized.class) ? method.getMethod().getAnnotation(Authorized.class)
                                                           : method.getController().getType().getAnnotation(Authorized.class);
    }
}