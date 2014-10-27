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

        <!-- Setup -->
        <link href="${pageContext.request.contextPath}/css/setup.css" rel="stylesheet">

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
          <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
        <![endif]-->

        <title>${title}</title>
    </head>
    <body>
        <div class="container">
            <div class="page-header">
                <h1>${title}</h1>
            </div>
            <form action="${linkTo[SetupController].setup}" class="form-horizontal" method="post" role="form">
                <fieldset>
                    <legend>Usuário administrador</legend>

                    <div class="form-group">
                        <div class="col-md-2<c:if test="${not empty errors.from('funcionario.cpf')}"> has-error</c:if>">
                            <label for="cpf">CPF</label>
                            <input type="text" name="funcionario.cpf" value="${funcionario.cpf}" class="form-control input-sm" id="cpf" alt="cpf">
                            <c:if test="${not empty errors.from('funcionario.cpf')}">
                                <span class="help-block">${errors.from('funcionario.cpf')}</span>
                            </c:if>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-md-4<c:if test="${not empty errors.from('funcionario.nome')}"> has-error</c:if>">
                            <label for="nome">Nome</label>
                            <input type="text" name="funcionario.nome" value="${funcionario.nome}" class="form-control input-sm" id="nome" maxlength="200">
                            <c:if test="${not empty errors.from('funcionario.nome')}">
                                <span class="help-block">${errors.from('funcionario.nome')}</span>
                            </c:if>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-md-2<c:if test="${not empty errors.from('funcionario.senha')}"> has-error</c:if>">
                            <label for="senha">Senha</label>
                            <input type="password" name="funcionario.senha" value="${funcionario.senha}" class="form-control input-sm" id="senha" maxlength="30">
                            <c:if test="${not empty errors.from('funcionario.senha')}">
                                <span class="help-block">${errors.from('funcionario.senha')}</span>
                            </c:if>
                        </div>

                        <div class="col-md-2<c:if test="${not empty errors.from('funcionario.checkPasswd')}"> has-error</c:if>">
                            <label for="checkPassword">Confirmar a senha</label>
                            <input type="password" name="funcionario.checkPasswd" value="${funcionario.checkPasswd}" class="form-control input-sm" id="checkPassword" maxlength="30">
                            <c:if test="${not empty errors.from('funcionario.checkPasswd')}">
                                <span class="help-block">${errors.from('funcionario.checkPasswd')}</span>
                            </c:if>
                        </div>
                    </div>

                    <button class="btn btn-sm btn-primary" type="submit">Salvar</button>
                </fieldset>
            </form>
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