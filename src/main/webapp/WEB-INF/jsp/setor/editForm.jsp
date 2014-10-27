<%@ taglib prefix="t" tagdir="/WEB-INF/tags/" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:template title="${title}">
    <c:choose>
        <c:when test="${not empty setor}">
            <div class="page-header">
                <h1>
                    Setores
                    <small>
                        <span class="glyphicon glyphicon-chevron-right"></span>
                        ${title}
                    </small>
                </h1>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading clearfix">
                    <a href="${linkTo[SetorController].list}" class="btn btn-sm btn-primary pull-right" role="button" title="Voltar" data-header="Voltar"><span class="glyphicon glyphicon-chevron-left"></span></a>
                </div>

                <div class="panel-body">
                    <form action="${linkTo[SetorController].editForm(setor.id)}" class="form-horizontal" method="post" role="form">
                        <div class="form-group">
                            <div class="col-md-6<c:if test="${not empty errors.from('setor.nome')}"> has-error</c:if>">
                                <label for="nome">Nome</label>
                                <input type="text" name="setor.nome" value="${setor.nome}" class="form-control input-sm" id="nome" maxlength="150">
                                <c:if test="${not empty errors.from('setor.nome')}">
                                    <span class="help-block">${errors.from('setor.nome')}</span>
                                </c:if>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-md-2<c:if test="${not empty errors.from('setor.sigla')}"> has-error</c:if>">
                                <label for="sigla">Sigla</label>
                                <input type="text" name="setor.sigla" value="${setor.sigla}" class="form-control input-sm" id="sigla" maxlength="50">
                                <c:if test="${not empty errors.from('setor.sigla')}">
                                    <span class="help-block has-error">${errors.from('setor.sigla')}</span>
                                </c:if>
                            </div>

                            <div class="col-md-4">
                                <label for="email">E-mail</label>
                                <input type="email" name="setor.email" value="${setor.email}" class="form-control input-sm" id="email" maxlength="100">
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-md-2">
                                <label for="telefone">Telefone</label>
                                <input type="text" name="setor.telefone" value="${setor.telefone}" class="form-control input-sm" id="telefone" alt="fone">
                            </div>

                            <div class="col-md-2">
                                <label for="fax">Fax</label>
                                <input type="text" name="setor.fax" value="${setor.fax}" class="form-control input-sm" id="fax" alt="fone">
                            </div>
                        </div>

                        <a href="${linkTo[SetorController].list}" class="btn btn-sm btn-default" role="button">Cancelar</a>
                        <button class="btn btn-sm btn-primary" type="submit">Salvar</button>
                    </form>
                </div>
            </div>
        </c:when>
        <c:otherwise>
            <p>Este setor não existe ou foi excluído.</p>
        </c:otherwise>
    </c:choose>
</t:template>