<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%@ include file="/WEB-INF/jspf/layout/header.jspf" %>

<%@ include file="/WEB-INF/jspf/navigation/sistema/actividad/navigationActividadView.jspf" %>

<%@ include file="/WEB-INF/jspf/layout/view/viewHeader.jspf" %>

<%@ include file="/WEB-INF/jspf/layout/table/tableHeader.jspf" %>

<%@ include file="/WEB-INF/jspf/opensgs/bean/view/tableValuesStart.jspf" %>

<tr>
    <th><s:text name="OpenSGSBean.attribute.operation"/></th>
    <td><s:property value="dtOpenSGSBean.operacion"/></td>
</tr>

<tr>
    <th><s:text name="OpenSGSBean.attribute.element"/></th>
    <td><s:property value="dtOpenSGSBean.elemento"/></td>
</tr>

<tr>
    <th><s:text name="OpenSGSBean.attribute.identifier"/></th>
    <td><s:property value="dtOpenSGSBean.identificador"/></td>
</tr>

<tr>
    <th><s:text name="OpenSGSBean.attribute.description"/></th>
    <td><s:property value="dtOpenSGSBean.descripcion"/></td>
</tr>

<tr>
    <th><s:text name="OpenSGSBean.class.user"/></th>
    <td><s:property value="dtOpenSGSBean.dtUsuario.email"/></td>
</tr>

<%@ include file="/WEB-INF/jspf/opensgs/bean/view/tableValuesEnd.jspf" %>

<%@ include file="/WEB-INF/jspf/layout/table/tableFooter.jspf" %>

<%@ include file="/WEB-INF/jspf/layout/view/viewFooter.jspf" %>

<%@ include file="/WEB-INF/jspf/layout/footer.jspf" %>
