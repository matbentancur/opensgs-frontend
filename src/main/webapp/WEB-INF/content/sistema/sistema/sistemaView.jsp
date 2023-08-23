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
    <th><s:text name="OpenSGSBean.attribute.url"/></th>
    <td><s:property value="dtOpenSGSManagedBean.url"/></td>
</tr>

<tr>
    <th><s:text name="OpenSGSBean.attribute.maintenance"/></th>
    <td><s:property value="dtOpenSGSManagedBean.mantenimiento"/></td>
</tr>

<tr>
    <th><s:text name="OpenSGSBean.attribute.maxUsers"/></th>
    <td><s:property value="dtOpenSGSManagedBean.cantidadMaximaUsuarios"/></td>
</tr>

<tr>
    <th><s:text name="OpenSGSBean.attribute.fileUploadMaxSize"/></th>
    <td><s:property value="dtOpenSGSManagedBean.fileUploadMaxSize"/></td>
</tr>

<tr>
    <th><s:text name="OpenSGSBean.attribute.supportEmail"/></th>
    <td><s:property value="dtOpenSGSManagedBean.correoSoporte"/></td>
</tr>

<tr>
    <th><s:text name="OpenSGSBean.attribute.filesPath"/></th>
    <td><s:property value="dtOpenSGSManagedBean.filesPath"/></td>
</tr>

<tr>
    <th><s:text name="OpenSGSBean.class.mailServer"/></th>
    <td><s:property value="dtOpenSGSManagedBean.dtServidorCorreo.nombre"/></td>
</tr>

<tr>
    <th><s:text name="OpenSGSBean.class.aplicationServer"/></th>
    <td><s:property value="dtOpenSGSManagedBean.dtServidorAplicaciones.nombre"/></td>
</tr>

<tr>
    <th><s:text name="OpenSGSBean.class.autenticationServer"/></th>
    <td><s:property value="dtOpenSGSManagedBean.dtServidorAutenticacion.nombre"/></td>
</tr>

<%@ include file="/WEB-INF/jspf/opensgs/bean/view/tableValuesEnd.jspf" %>

<%@ include file="/WEB-INF/jspf/opensgs/managedBean/view/tableValuesEnd.jspf" %>

<%@ include file="/WEB-INF/jspf/layout/table/tableFooter.jspf" %>

<%@ include file="/WEB-INF/jspf/layout/view/viewFooter.jspf" %>

<%@ include file="/WEB-INF/jspf/layout/footer.jspf" %>

