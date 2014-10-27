//package br.com.lponto.config;
//
//import javax.enterprise.context.RequestScoped;
//import javax.enterprise.event.Event;
//import javax.enterprise.inject.Specializes;
//import javax.servlet.FilterChain;
//
//import br.com.caelum.vraptor.Result;
//import br.com.caelum.vraptor.controller.DefaultControllerNotFoundHandler;
//import br.com.caelum.vraptor.controller.HttpMethod;
//import br.com.caelum.vraptor.events.ControllerNotFound;
//import br.com.caelum.vraptor.http.MutableRequest;
//import br.com.caelum.vraptor.http.MutableResponse;
//import br.com.caelum.vraptor.http.route.ControllerNotFoundException;
//import br.com.caelum.vraptor.http.route.Router;
//import br.com.caelum.vraptor.view.Results;
//
//@Specializes
//@RequestScoped
//public class CustomControllerNotFoundHandler extends DefaultControllerNotFoundHandler {
//    private final Router router;
//    private final Result result;
//
//    /**
//     * @deprecated CDI eyes only
//     */
//    public CustomControllerNotFoundHandler() {
//        this(null, null, null);
//    }
//
//    public CustomControllerNotFoundHandler(Router router, Result result, Event<ControllerNotFound> event) {
//        super(event);
//        this.router = router;
//        this.result = result;
//    }
//
//    @Override
//    public void couldntFind(FilterChain chain, MutableRequest request, MutableResponse response) {
//        try {
//            String uri = request.getRequestedUri();
//
//            if (uri.endsWith("/")) {
//                tryMovePermanentlyTo(request, uri.substring(0, uri.length()-1));
//            } else {
//                tryMovePermanentlyTo(request, uri + "/");
//            }
//        } catch (ControllerNotFoundException e) {
//            super.couldntFind(chain, request, response);
//        }
//    }
//
//    private void tryMovePermanentlyTo(MutableRequest request, String uri) {
//        router.parse(uri, HttpMethod.of(request), request);
//        result.use(Results.status()).movedPermanentlyTo(uri);
//    }
//}