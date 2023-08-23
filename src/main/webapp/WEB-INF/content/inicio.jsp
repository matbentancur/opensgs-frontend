<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%@ include file="/WEB-INF/jspf/layout/header.jspf" %>

<%@ include file="/WEB-INF/jspf/layout/anuncios.jspf" %>

<%@ include file="/WEB-INF/jspf/layout/documentos.jspf" %>

<s:url var="abiertas" action="%{actionName}" namespace="%{nameSpace}"/>
<s:url var="cerradas" action="%{actionName}" method="cerradas" namespace="%{nameSpace}"/>
<s:url var="proximas" action="%{actionName}" method="proximas" namespace="%{nameSpace}"/>

<ul class="nav nav-tabs">
    <li class="nav-item">
        <a class="nav-link link-dark active" aria-current="page" href="${abiertas}"><s:text name="OpenSGSBean.text.opened"/></a>
    </li>
    <li class="nav-item">
        <a class="nav-link link-dark" href="${cerradas}"><s:text name="OpenSGSBean.text.closed"/></a>
    </li>
    <li class="nav-item">
        <a class="nav-link link-dark" href="${proximas}"><s:text name="OpenSGSBean.text.coming"/></a>
    </li>
</ul>

<%@ include file="/WEB-INF/jspf/inicio/tableApplications.jspf" %>

<%--</s:action>--%>
<%@ include file="/WEB-INF/jspf/layout/footer.jspf" %>