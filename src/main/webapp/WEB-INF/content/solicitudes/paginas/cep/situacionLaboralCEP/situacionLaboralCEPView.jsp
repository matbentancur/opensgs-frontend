<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%@ include file="/WEB-INF/jspf/solicitudes/paginas/view/viewHeader.jspf" %>

<%@ include file="/WEB-INF/jspf/layout/table/tableHeader.jspf" %>

<tr>
    <th><s:text name="Pagina.attribute.situacion"/></th>
    <td><s:property value="dtOpenSGSManagedBean.situacion"/></td>
</tr>

<tr>
    <th><s:text name="Pagina.attribute.departamentoTrabaja"/></th>
    <td><s:property value="dtOpenSGSManagedBean.departamentoTrabaja"/></td>
</tr>

<tr>
    <th><s:text name="Pagina.attribute.lugarTrabajo"/></th>
    <td><s:property value="dtOpenSGSManagedBean.lugarTrabajo"/></td>
</tr>

<%@ include file="/WEB-INF/jspf/layout/table/tableFooter.jspf" %>


