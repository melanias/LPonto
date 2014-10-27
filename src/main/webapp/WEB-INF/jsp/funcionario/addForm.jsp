<%@ taglib prefix="t" tagdir="/WEB-INF/tags/" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:template title="${title}">
            <div class="page-header">
                <h1>
                    Funcionários
                    <small>
                        <span class="glyphicon glyphicon-chevron-right"></span>
                        ${title}
                    </small>
                </h1>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading clearfix">
                    <a href="${linkTo[FuncionarioController].list}" class="btn btn-sm btn-primary pull-right" role="button" title="Voltar" data-header="Voltar"><span class="glyphicon glyphicon-chevron-left"></span></a>
                </div>

                <div class="panel-body">
                    <form action="${linkTo[FuncionarioController].add}" class="form-horizontal" method="post" role="form">
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
                            <div class="col-md-6<c:if test="${not empty errors.from('funcionario.nome')}"> has-error</c:if>">
                                <label for="nome">Nome</label>
                                <input type="text" name="funcionario.nome" value="${funcionario.nome}" class="form-control input-sm" id="nome" maxlength="200">
                                <c:if test="${not empty errors.from('funcionario.nome')}">
                                    <span class="help-block">${errors.from('funcionario.nome')}</span>
                                </c:if>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-md-2<c:if test="${not empty errors.from('funcionario.setor')}"> has-error</c:if>">
                                <label for="setor">Setor</label>
                                <select name="funcionario.setor.id" class="form-control input-sm" id="setor">
                                    <c:if test="${empty setores}">
                                        <option value="">---</option>
                                    </c:if>
                                    <c:forEach items="${setores}" var="setor">
                                        <option value="${setor.id}"${setor.id == funcionario.setor.id ? " selected" : ""}>${setor.sigla}</option>
                                    </c:forEach>
                                </select>
                                <c:if test="${not empty errors.from('funcionario.setor')}">
                                    <span class="help-block">${errors.from('funcionario.setor')}</span>
                                </c:if>
                            </div>

                            <div class="col-md-2">
                                <label for="perfil">Perfil</label>
                                <select name="funcionario.perfil" class="form-control input-sm" id="perfil">
                                    <c:forEach items="${roles}" var="r">
                                        <option value="${r}"${(empty funcionario.perfil and r == 'NORMAL') or (r == funcionario.perfil) ? " selected" : ""}>${r.role}</option>
                                    </c:forEach>
                                </select>
                            </div>

                            <div class="col-md-2">
                                <label for="status">Situação</label>
                                <select name="funcionario.status" class="form-control input-sm" id="status">
                                    <c:forEach items="${status}" var="s">
                                        <option value="${s}"${(empty funcionario.status and s == 'ATIVO') or (s == funcionario.status) ? " selected" : ""}>${s.status}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-md-3<c:if test="${not empty errors.from('funcionario.senha')}"> has-error</c:if>">
                                <label for="senha">Senha</label>
                                <input type="password" name="funcionario.senha" value="${funcionario.senha}" class="form-control input-sm" id="senha" maxlength="30">
                                <c:if test="${not empty errors.from('funcionario.senha')}">
                                    <span class="help-block">${errors.from('funcionario.senha')}</span>
                                </c:if>
                            </div>

                            <div class="col-md-3<c:if test="${not empty errors.from('funcionario.checkPasswd')}"> has-error</c:if>">
                                <label for="checkPassword">Confirmar a senha</label>
                                <input type="password" name="funcionario.checkPasswd" value="${funcionario.checkPasswd}" class="form-control input-sm" id="checkPassword" maxlength="30">
                                <c:if test="${not empty errors.from('funcionario.checkPasswd')}">
                                    <span class="help-block">${errors.from('funcionario.checkPasswd')}</span>
                                </c:if>
                            </div>
                        </div>

                        <a href="${linkTo[FuncionarioController].list}" class="btn btn-sm btn-default" role="button">Cancelar</a>
                        <button class="btn btn-sm btn-primary" type="submit"<c:if test="${empty setores}"> disabled</c:if>>Salvar</button>
                    </form>
                </div>
            </div>
</t:template>