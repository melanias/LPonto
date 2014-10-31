<%@ tag description="Template Principal" pageEncoding="UTF-8" %>

<%@ attribute name="title" required="true" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

        <!-- DataTables -->
        <link href="${pageContext.request.contextPath}/datatables/dataTables.bootstrap.css" rel="stylesheet">

        <!-- App -->
        <link href="${pageContext.request.contextPath}/css/lponto.css" rel="stylesheet">

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
          <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
        <![endif]-->

        <title>${title}</title>
    </head>
    <body>
        <c:if test="${not empty successMessage}">
        <!-- Success Message -->
        <div class="page-alert">
            <div class="alert alert-success alert-fixed-bottom alert-dismissable">
                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                <p><strong>${successMessage}</strong></p>
            </div>
        </div>
        <!-- Success Message -->
        </c:if>

        <div class="navbar navbar-default navbar-fixed-top">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-responsive-collapse">
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="${linkTo[IndexController].index}">LPonto</a>
                </div>

                <div class="collapse navbar-collapse navbar-responsive-collapse">
                    <ul class="nav navbar-nav">
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">Cadastros&nbsp;&nbsp;<span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <c:if test="${workerSession.perfil == 'ADMINISTRADOR'}">
                                <li><a href="${linkTo[WorkerController].list}">Usu&aacute;rios</a></li>
                                <li class="divider"></li>
                                </c:if>
                                <li><a href="${linkTo[SetorController].list}">Setores</a></li>
                                <li><a href="${linkTo[FuncionarioController].list}">Funcion&aacute;rios</a></li>
                                <li class="divider"></li>
                                <li><a href="${linkTo[PontoController].registerForm}">Registro de Ponto</a></li>
                            </ul>
                        </li>

                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">Relat&oacute;rios&nbsp;&nbsp;<span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><a href="">Relat&oacute;rio 1</a></li>
                                <li><a href="">Relat&oacute;rio 2</a></li>
                                <li><a href="">Relat&oacute;rio 3</a></li>
                            </ul>
                        </li>
                    </ul>

                    <c:if test="${not empty employeeSession}">
                    <ul class="nav navbar-nav navbar-right">
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown"><span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;&nbsp;${employeeSession.firstAndLastName}&nbsp;&nbsp;<span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><a href="${linkTo[WorkerController].editAccount}"><span class="glyphicon glyphicon-edit"></span>&nbsp;Editar conta</a></li>
                                <li><a href="${linkTo[WorkerController].editPasswd}"><span class="glyphicon glyphicon-pencil"></span>&nbsp;Alterar senha</a></li>
                                <li class="divider"></li>
                                <li><a href="${linkTo[LoginController].logout}"><span class="glyphicon glyphicon-off"></span>&nbsp;Sair</a></li>
                            </ul>
                        </li>
                    </ul>
                    </c:if>
                </div>
            </div>
        </div>

        <!-- Container -->
        <div class="container">
            <jsp:doBody/>
        </div>
        <!-- Container -->

        <!-- jQuery -->
        <script src="${pageContext.request.contextPath}/js/jquery.js"></script>
        <script src="${pageContext.request.contextPath}/js/jquery-migrate.js"></script>

        <!-- Bootstrap -->
        <script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/bootstrap/js/jasny-bootstrap.min.js"></script>

        <!-- DataTables -->
        <script src="${pageContext.request.contextPath}/datatables/dataTables.min.js"></script>
        <script src="${pageContext.request.contextPath}/datatables/dataTables.bootstrap.js"></script>

        <!-- Holder.js -->
        <script src="${pageContext.request.contextPath}/js/holder.js"></script>

        <!-- meioMask -->
        <script src="${pageContext.request.contextPath}/js/meiomask.js"></script>

        <!-- Root URL -->
        <script type="text/javascript">
            const ROOTURL  = "${pageContext.request.contextPath}";
        </script>

        <!-- App JS -->
        <script src="${pageContext.request.contextPath}/js/lponto.js"></script>
    </body>
</html>
