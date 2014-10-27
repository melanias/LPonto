<%@ taglib prefix="t" tagdir="/WEB-INF/tags/" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:template title="${title}">
            <div class="page-header">
                <h1>
                    ${title}
                    <small>
                        <span class="glyphicon glyphicon-chevron-right"></span>
                        Gerenciamento de setores
                    </small>
                </h1>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading clearfix">
                    <a href="${linkTo[SetorController].addForm}" class="btn btn-sm btn-primary pull-right" role="button" title="${addTitle}"><span class="glyphicon glyphicon-plus"></span></a>
                </div>
<c:choose>
    <c:when test="${not empty setores}">
                <table class="table table-hover table-striped table-bordered table-condensed">
                    <thead>
                        <tr>
                            <th>Setor</th>
                            <th>Sigla</th>
                            <th>E-mail</th>
                            <th>Telefone</th>
                            <th>Fax</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${setores}" var="setor">
                        <tr>
                            <td>${setor.nome}</td>
                            <td>${setor.sigla}</td>
                            <td><c:if test="${empty setor.email}">-</c:if>${setor.email}</td>
                            <td><c:if test="${empty setor.telefone}">-</c:if>${setor.telefone}</td>
                            <td><c:if test="${empty setor.fax}">-</c:if>${setor.fax}</td>
                            <td>
                                <a href="${linkTo[SetorController].editForm(setor.id)}" role="button" title="${editTitle}"><span class="glyphicon glyphicon-pencil"></span></a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
    </c:when>
    <c:otherwise>
                <div class="panel-body">
                    <p>Nenhum setor cadastrado até o momento.</p>
                </div>
    </c:otherwise>
</c:choose>
            </div>
</t:template>