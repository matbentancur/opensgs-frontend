<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%@ include file="/WEB-INF/jspf/layout/header.jspf" %>

<%@ include file="/WEB-INF/jspf/navigation/navigationOpenSGSBeanView.jspf" %>

<%@ include file="/WEB-INF/jspf/layout/view/viewHeader.jspf" %>

<%@ include file="/WEB-INF/jspf/layout/table/tableHeader.jspf" %>

<%@ include file="/WEB-INF/jspf/opensgs/bean/view/tableValuesStart.jspf" %>

<%@ include file="/WEB-INF/jspf/opensgs/managedBean/view/tableValuesStart.jspf" %>

<tr>
    <th><s:text name="OpenSGSBean.attribute.name"/></th>
    <td><s:property value="dtOpenSGSManagedBean.nombre"/></td>
</tr>

<tr>
    <th><s:text name="OpenSGSBean.attribute.title"/></th>
    <td><s:property value="dtOpenSGSManagedBean.titulo"/></td>
</tr>

<tr>
    <th><s:text name="OpenSGSBean.attribute.initialText"/></th>
    <td><s:property value="dtOpenSGSManagedBean.textoInicial"/></td>
</tr>

<tr>
    <th><s:text name="OpenSGSBean.attribute.finalText"/></th>
    <td><s:property value="dtOpenSGSManagedBean.textoFinal"/></td>
</tr>

<tr>
    <th><s:text name="OpenSGSBean.attribute.page"/></th>
    <td><s:property value="dtOpenSGSManagedBean.pagina"/></td>
</tr>

<tr>
    <th><s:text name="OpenSGSBean.attribute.dataStructure"/></th>
    <td><s:property value="dtOpenSGSManagedBean.esctructuraDatos"/></td>
</tr>

<%@ include file="/WEB-INF/jspf/opensgs/bean/view/tableValuesEnd.jspf" %>

<%@ include file="/WEB-INF/jspf/opensgs/managedBean/view/tableValuesEnd.jspf" %>

<%@ include file="/WEB-INF/jspf/layout/table/tableFooter.jspf" %>

<%@ include file="/WEB-INF/jspf/layout/view/viewFooter.jspf" %>

<%@ include file="/WEB-INF/jspf/layout/footer.jspf" %>

