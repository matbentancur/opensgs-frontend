<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%@ include file="/WEB-INF/jspf/solicitudes/paginas/view/viewHeader.jspf" %>

<%@ include file="/WEB-INF/jspf/layout/table/tableHeader.jspf" %>

<tr>
    <th><s:text name="Pagina.attribute.ultimoNivelEducativo"/></th>
    <td><s:property value="dtOpenSGSManagedBean.ultimoNivelEducativo"/></td>
</tr>

<tr>
    <th><s:text name="Pagina.attribute.tituloGrado"/></th>
    <td><s:property value="dtOpenSGSManagedBean.tituloGrado"/></td>
</tr>

<tr>
    <th><s:text name="Pagina.attribute.anioRecibio"/></th>
    <td><s:property value="dtOpenSGSManagedBean.anioRecibio"/></td>
</tr>

<tr>
    <th><s:text name="Pagina.attribute.institucionTitulo"/></th>
    <td><s:property value="dtOpenSGSManagedBean.institucionTitulo"/></td>
</tr>

<%@ include file="/WEB-INF/jspf/layout/table/tableFooter.jspf" %>
