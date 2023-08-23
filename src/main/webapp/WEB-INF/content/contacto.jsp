<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<s:url var="preguntaFrecuente" action="PreguntaFrecuenteAction" method="preguntasSistema" namespace="/sistema"/>

<s:url var="soporte" action="SoporteAction" method="input" namespace="/sistema"/>

<%@ include file="/WEB-INF/jspf/layout/header.jspf" %>

<%@ include file="/WEB-INF/jspf/navigation/navigationContacto.jspf" %>

<div class="row row-cols-1 row-cols-md-2 g-4">

    <div class="col">
        <a class="link-dark" href="${preguntaFrecuente}">
            <div class="card text-center">
                <i class="bi bi-question-circle"></i>
                <div class="card-body">
                    <h5 class="card-title">Preguntas Frecuentes</h5>
                    <p class="card-text">Revise las preguntas frecuentes para encontrar una posible solución.</p>
                </div>
            </div>
        </a>
    </div>

    <div class="col">
        <a class="link-dark" href="${soporte}">
            <div class="card text-center">
                <i class="bi bi-headset"></i>
                <div class="card-body">
                    <h5 class="card-title">Soporte</h5>
                    <p class="card-text">Complete el formulario de contacto e indique los inconvenientes.</p>
                </div>
            </div>
        </a>
    </div>

</div>

<%@ include file="/WEB-INF/jspf/layout/footer.jspf" %>