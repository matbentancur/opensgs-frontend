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
    <th><s:text name="OpenSGSBean.attribute.operation"/></th>
    <td><s:property value="dtOpenSGSManagedBean.operacion"/></td>
</tr>

<tr>
    <th><s:text name="OpenSGSBean.attribute.element"/></th>
    <td><s:property value="dtOpenSGSManagedBean.elemento"/></td>
</tr>

<tr>
    <th><s:text name="OpenSGSBean.attribute.notificationDestination"/></th>
    <td><s:property value="dtOpenSGSManagedBean.destino"/></td>
</tr>

<tr>
    <th><s:text name="OpenSGSBean.attribute.scope"/></th>
    <td><s:property value="dtOpenSGSManagedBean.alcance"/></td>
</tr>

<tr>
    <th><s:text name="OpenSGSBean.attribute.mailTemplate"/></th>
    <td><s:property value="dtOpenSGSManagedBean.dtPlantillaCorreo.nombre"/></td>
</tr>

<%@ include file="/WEB-INF/jspf/opensgs/bean/view/tableValuesEnd.jspf" %>

<%@ include file="/WEB-INF/jspf/opensgs/managedBean/view/tableValuesEnd.jspf" %>

<%@ include file="/WEB-INF/jspf/layout/table/tableFooter.jspf" %>

<%@ include file="/WEB-INF/jspf/layout/view/viewFooter.jspf" %>

<%@ include file="/WEB-INF/jspf/layout/footer.jspf" %>

