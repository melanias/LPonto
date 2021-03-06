<%@ taglib prefix="t" tagdir="/WEB-INF/tags/" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:template title="${title}">
    <jsp:body>
            <div class="page-header">
                <h1>
                    Ponto
                    <small>
                        <span class="glyphicon glyphicon-chevron-right"></span>
                        ${title}
                    </small>
                </h1>
            </div>
            <form action="${not empty pointSession.ponto ? linkTo[PontoController].confirmRegister : linkTo[PontoController].addRegister}" class="form-horizontal" method="post" role="form">
                <br><br>
                <div class="text-center">
                    <img src="${not empty pointSession.ponto ? pointSession.ponto.encodeBase64 : ''}" data-src="holder.js/320x240/auto/text:${empty webcam ? 'C�mera indispon�vel' : 'Imagem'}" class="img-thumbnail img-responsive">
                    <br><br>
                    <c:if test="${not empty pointSession.ponto}">
                    <a href="${linkTo[PontoController].cancelRegister}" class="btn btn-sm btn-danger" role="button">Cancelar</a>
                    </c:if>
                    <button class="btn btn-sm btn-success" type="submit"${empty webcam ? ' disabled' : ''}>${not empty pointSession.ponto ? 'Confirmar' : 'Registrar'}</button>

                    <c:if test="${not empty errors.from('webcam')}">
                    <p class="text-danger">${errors.from('webcam')}</p>
                    </c:if>
                </div>
            </form>
    </jsp:body>
</t:template>