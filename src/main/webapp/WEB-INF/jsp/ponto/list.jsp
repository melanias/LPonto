<%@ taglib prefix="t" tagdir="/WEB-INF/tags/" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<t:template title="${title}">
            <div class="page-header">
                <h1>
                    ${title}
                    <small>
                        <span class="glyphicon glyphicon-chevron-right"></span>
                        ${subTitle}
                    </small>
                </h1>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading clearfix">
                    <a href="${linkTo[PontoController].registerForm}" class="btn btn-sm btn-primary pull-right" role="button" title="${addTitle}"><span class="glyphicon glyphicon-plus"></span></a>
                </div>
<c:choose>
    <c:when test="${not empty records}">
                <table class="table table-hover table-striped table-bordered table-condensed">
                    <thead>
                        <tr>
                            <th style="width: 10%;">CPF</th>
                            <th>Nome</th>
                            <th style="width: 15%;">Horário</th>
                            <th style="width: 15%;">Tipo de Registro</th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${records}" var="record">
                        <tr>
                            <td>${record.funcionario.cpf}</td>
                            <td>
                                ${record.funcionario.nome}
                                &nbsp;
                                <a href="#" data-toggle="popover" data-html="true" data-placement="top" data-content="<img src='${record.encodeBase64}' width='160' height='120'>"><span class="glyphicon glyphicon-picture"></span></a>
                            </td>
                            <td><fmt:formatDate pattern="dd/MM/yyyy" value="${record.toDate}" /> - <fmt:formatDate pattern="HH:mm:ss" value="${record.toTime}" /></td>
                            <td>${record.tipo}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
    </c:when>
    <c:otherwise>
                <div class="panel-body">
                    <p>Nenhum registro de ponto até o momento.</p>
                </div>
    </c:otherwise>
</c:choose>
            </div>
</t:template>