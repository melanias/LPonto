<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="author" content="Phelipe Melanias">

        <!-- Bootstrap -->
        <link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/bootstrap/css/jasny-bootstrap.min.css" rel="stylesheet">

        <!-- Login -->
        <link href="${pageContext.request.contextPath}/css/login.css" rel="stylesheet">

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
          <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
        <![endif]-->

        <title>${title}</title>
    </head>
    <body>
        <c:if test="${not empty errors}">
        <div class="page-alert">
            <div class="alert alert-danger alert-fixed-top alert-dismissable">
                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                <c:forEach items="${errors}" var="error">
                <p><strong>${error.message}</strong></p>
                </c:forEach>
            </div>
        </div>
        </c:if>
        <div class="container">
            <!-- Form -->
            <form class="form-login" action="${linkTo[LoginController].login}" method="post">
                <fieldset>
                    <legend><strong>${title}</strong></legend>
                    <input type="text" name="cpf" value="${cpf}" class="form-control" placeholder="Informe seu CPF" alt="cpf">
                    <input type="password" name="senha" value="" class="form-control" placeholder="Informe sua senha">
                    <button class="btn btn-sm btn-primary" type="submit">Entrar</button>
                </fieldset>
            </form>
            <!-- Form -->
        </div>

        <!-- jQuery + Bootstrap -->
        <script src="${pageContext.request.contextPath}/js/jquery.js"></script>
        <script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/bootstrap/js/jasny-bootstrap.min.js"></script>

        <!-- JS -->
        <script type="text/javascript">
            $("input[value='']:first").focus();

            //CPF mask
            $("input[alt='cpf']").inputmask({
                mask: "999.999.999-99"
            });
        </script>
    </body>
</html>