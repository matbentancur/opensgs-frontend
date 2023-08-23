<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%@ include file="/WEB-INF/jspf/layout/header.jspf" %>

<%@ include file="/WEB-INF/jspf/navigation/navigationOpenSGSBeanView.jspf" %>

<%@ include file="/WEB-INF/jspf/layout/view/viewHeader.jspf" %>

<%@ include file="/WEB-INF/jspf/layout/table/tableHeader.jspf" %>

<%@ include file="/WEB-INF/jspf/opensgs/bean/view/tableValuesStart.jspf" %>

<%@ include file="/WEB-INF/jspf/opensgs/managedBean/view/tableValuesStart.jspf" %>

<tr>
    <th><s:text name="OpenSGSBean.attribute.documentType"/></th>
    <td><s:property value="dtOpenSGSManagedBean.documentoTipo"/></td>
</tr>

<tr>
    <th><s:text name="OpenSGSBean.attribute.document"/></th>
    <td><s:property value="dtOpenSGSManagedBean.documento"/></td>
</tr>

<tr>
    <th><s:text name="OpenSGSBean.attribute.email"/></th>
    <td><s:property value="dtOpenSGSManagedBean.email"/></td>
</tr>

<tr>
    <th><s:text name="OpenSGSBean.attribute.names"/></th>
    <td><s:property value="dtOpenSGSManagedBean.nombres"/></td>
</tr>

<tr>
    <th><s:text name="OpenSGSBean.attribute.lastnames"/></th>
    <td><s:property value="dtOpenSGSManagedBean.apellidos"/></td>
</tr>

<tr>
    <th><s:text name="OpenSGSBean.attribute.phone"/></th>
    <td><s:property value="dtOpenSGSManagedBean.telefono"/></td>
</tr>

<tr>
    <th><s:text name="OpenSGSBean.attribute.globalProfile"/></th>
    <td><s:property value="dtOpenSGSManagedBean.dtPerfil.nombre"/></td>
</tr>

<%@ include file="/WEB-INF/jspf/opensgs/bean/view/tableValuesEnd.jspf" %>

<%@ include file="/WEB-INF/jspf/opensgs/managedBean/view/tableValuesEnd.jspf" %>

<%@ include file="/WEB-INF/jspf/layout/table/tableFooter.jspf" %>

<%@ include file="/WEB-INF/jspf/layout/view/viewFooter.jspf" %>

<%@ include file="/WEB-INF/jspf/layout/footer.jspf" %>
