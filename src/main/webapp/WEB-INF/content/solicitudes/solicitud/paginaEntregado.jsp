<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%@ include file="/WEB-INF/jspf/layout/header.jspf" %>

<s:url var="misSolicitudes" action="SolicitudAction" method="misSolicitudes" namespace="/solicitudes"/>

<div class="text-center">
    <a class="btn btn-dark" href="${misSolicitudes}" role="button"><s:text name="OpenSGSBean.text.close"/></a>
</div>

<%@ include file="/WEB-INF/jspf/layout/footer.jspf" %>

