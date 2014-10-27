<%@ taglib prefix="t" tagdir="/WEB-INF/tags/" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<t:template title="${title}">
            <div class="page-header">
                <h1>
                    ${title}
                    <small>
                        <span class="glyphicon glyphicon-chevron-right"></span>
                        Gerenciamento de funcionários
                    </small>
                </h1>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading clearfix">
                    <a href="${linkTo[FuncionarioController].addForm}" class="btn btn-sm btn-primary pull-right" role="button" title="${addTitle}"><span class="glyphicon glyphicon-plus"></span></a>
                </div>
<c:choose>
    <c:when test="${not empty funcionarios}">
                <table class="table table-hover table-striped table-bordered table-condensed">
                    <thead>
                        <tr>
                            <th>Data de cadastro</th>
                            <th>CPF</th>
                            <th>Nome</th>
                            <th>Perfil</th>
                            <th>Status</th>
                            <th>Setor</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${funcionarios}" var="funcionario">
                        <tr>
                            <td><fmt:formatDate pattern="dd/MM/yyyy" value="${funcionario.data}" /></td>
                            <td>${funcionario.cpf}</td>
                            <td>${funcionario.nome}</td>
                            <td>${funcionario.perfil.role}</td>
                            <td>${funcionario.status.status}</td>
                            <td>${not empty funcionario.setor ? funcionario.setor.sigla : "-"}</td>
                            <td>
                                <a href="${linkTo[FuncionarioController].editForm(funcionario.id)}" role="button" title="${editTitle}"><span class="glyphicon glyphicon-pencil"></span></a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
    </c:when>
    <c:otherwise>
                <div class="panel-body">
                    <p>Nenhum funcionário cadastrado até o momento.</p>
                </div>
    </c:otherwise>
</c:choose>
            </div>
</t:template>