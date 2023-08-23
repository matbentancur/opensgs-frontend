<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%@ include file="/WEB-INF/jspf/layout/header.jspf" %>

<%@ include file="/WEB-INF/jspf/navigation/navigationOpenSGSBeanView.jspf" %>

<%@ include file="/WEB-INF/jspf/layout/view/viewHeader.jspf" %>

<%@ include file="/WEB-INF/jspf/layout/table/tableHeader.jspf" %>

<%@ include file="/WEB-INF/jspf/opensgs/bean/view/tableValuesStart.jspf" %>

<%@ include file="/WEB-INF/jspf/opensgs/managedBean/view/tableValuesStart.jspf" %>

<%@ include file="/WEB-INF/jspf/sistema/servidor/view/tableValues.jspf" %>

<tr>
    <th><s:text name="OpenSGSBean.attribute.security"/></th>
    <td><s:property value="dtOpenSGSManagedBean.seguridad"/></td>
</tr>

<tr>
    <th><s:text name="OpenSGSBean.attribute.identification"/></th>
    <td><s:property value="dtOpenSGSManagedBean.identificacion"/></td>
</tr>

<tr>
    <th><s:text name="OpenSGSBean.attribute.fromEmail"/></th>
    <td><s:property value="dtOpenSGSManagedBean.desdeCorreo"/></td>
</tr>

<tr>
    <th><s:text name="OpenSGSBean.attribute.fromName"/></th>
    <td><s:property value="dtOpenSGSManagedBean.desdeNombre"/></td>
</tr>

<%@ include file="/WEB-INF/jspf/opensgs/bean/view/tableValuesEnd.jspf" %>

<%@ include file="/WEB-INF/jspf/opensgs/managedBean/view/tableValuesEnd.jspf" %>

<%@ include file="/WEB-INF/jspf/layout/table/tableFooter.jspf" %>

<%@ include file="/WEB-INF/jspf/layout/view/viewFooter.jspf" %>

<%@ include file="/WEB-INF/jspf/layout/footer.jspf" %>

