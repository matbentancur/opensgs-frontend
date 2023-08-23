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
    <th><s:text name="OpenSGSBean.attribute.mime"/></th>
    <td><s:property value="dtOpenSGSManagedBean.mime"/></td>
</tr>

<tr>
    <th><s:text name="OpenSGSBean.attribute.extension"/></th>
    <td><s:property value="dtOpenSGSManagedBean.extension"/></td>
</tr>

<tr>
    <th><s:text name="OpenSGSBean.attribute.size"/></th>
    <td><s:property value="dtOpenSGSManagedBean.peso"/></td>
</tr>

<tr>
    <th><s:text name="OpenSGSBean.attribute.path"/></th>
    <td><s:property value="dtOpenSGSManagedBean.ubicacion"/></td>
</tr>

<tr>
    <th><s:text name="OpenSGSBean.attribute.fileScope"/></th>
    <td><s:property value="dtOpenSGSManagedBean.alcanceArchivo"/></td>
</tr>

<%@ include file="/WEB-INF/jspf/opensgs/bean/view/tableValuesEnd.jspf" %>

<%@ include file="/WEB-INF/jspf/opensgs/managedBean/view/tableValuesEnd.jspf" %>

<%@ include file="/WEB-INF/jspf/layout/table/tableFooter.jspf" %>

<%@ include file="/WEB-INF/jspf/layout/view/viewFooter.jspf" %>

<%@ include file="/WEB-INF/jspf/layout/footer.jspf" %>

