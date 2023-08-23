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
    <th><s:text name="OpenSGSBean.attribute.opening"/></th>
    <td><s:property value="dtOpenSGSManagedBean.apertura"/></td>
</tr>

<tr>
    <th><s:text name="OpenSGSBean.attribute.closing"/></th>
    <td><s:property value="dtOpenSGSManagedBean.cierre"/></td>
</tr>

<tr>
    <th><s:text name="OpenSGSBean.attribute.contact"/></th>
    <td><s:property value="dtOpenSGSManagedBean.contacto"/></td>
</tr>

<tr>
    <th><s:text name="OpenSGSBean.attribute.requestPerUser"/></th>
    <td><s:property value="dtOpenSGSManagedBean.solicitudesPorUsuario"/></td>
</tr>

<tr>
    <th><s:text name="OpenSGSBean.attribute.totalRequests"/></th>
    <td><s:property value="dtOpenSGSManagedBean.solicitudesTotal"/></td>
</tr>

<tr>
    <th><s:text name="OpenSGSBean.attribute.applicationTemplate"/></th>
    <td><s:property value="dtOpenSGSManagedBean.dtPlantillaAplicacion.nombre"/></td>
</tr>

<%@ include file="/WEB-INF/jspf/opensgs/bean/view/tableValuesEnd.jspf" %>

<%@ include file="/WEB-INF/jspf/opensgs/managedBean/view/tableValuesEnd.jspf" %>

<%@ include file="/WEB-INF/jspf/layout/table/tableFooter.jspf" %>

<%@ include file="/WEB-INF/jspf/layout/view/viewFooter.jspf" %>

<%@ include file="/WEB-INF/jspf/layout/footer.jspf" %>

