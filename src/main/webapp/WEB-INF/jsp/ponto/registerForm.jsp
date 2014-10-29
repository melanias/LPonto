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
                <img src="${not empty pointSession.ponto ? pointSession.ponto.encodeBase64 : ''}" data-src="holder.js/320x240/auto/text:Foto capturada" class="img-thumbnail img-responsive">
                <br><br>
                <button class="btn btn-lg btn-success" type="submit">${not empty pointSession.ponto ? 'Confirmar' : 'Registrar'}</button>
            </form>
    </jsp:body>
</t:template>