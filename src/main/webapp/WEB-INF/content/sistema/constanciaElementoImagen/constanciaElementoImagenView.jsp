<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%@ include file="/WEB-INF/jspf/layout/header.jspf" %>

<%@ include file="/WEB-INF/jspf/navigation/navigationOpenSGSBeanView.jspf" %>

<%@ include file="/WEB-INF/jspf/layout/view/viewHeader.jspf" %>

<%@ include file="/WEB-INF/jspf/layout/table/tableHeader.jspf" %>

<%@ include file="/WEB-INF/jspf/opensgs/bean/view/tableValuesStart.jspf" %>

<%@ include file="/WEB-INF/jspf/opensgs/managedBean/view/tableValuesStart.jspf" %>

<%@ include file="/WEB-INF/jspf/sistema/constanciaElemento/view/tableValues.jspf" %>

<tr>
    <th><s:text name="OpenSGSBean.attribute.width"/></th>
    <td><s:property value="dtOpenSGSManagedBean.ancho"/></td>
</tr>

<tr>
    <th><s:text name="OpenSGSBean.attribute.height"/></th>
    <td><s:property value="dtOpenSGSManagedBean.alto"/></td>
</tr>

<tr>
    <th><s:text name="OpenSGSBean.attribute.fileName"/></th>
    <td><s:property value="dtOpenSGSManagedBean.dtArchivo.nombre"/></td>
</tr>

<%@ include file="/WEB-INF/jspf/opensgs/bean/view/tableValuesEnd.jspf" %>

<%@ include file="/WEB-INF/jspf/opensgs/managedBean/view/tableValuesEnd.jspf" %>

<%@ include file="/WEB-INF/jspf/layout/table/tableFooter.jspf" %>

<%@ include file="/WEB-INF/jspf/layout/view/viewFooter.jspf" %>

<%@ include file="/WEB-INF/jspf/layout/footer.jspf" %>

