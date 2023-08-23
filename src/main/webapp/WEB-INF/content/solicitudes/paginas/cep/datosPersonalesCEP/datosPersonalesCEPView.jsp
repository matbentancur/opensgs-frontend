<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%@ include file="/WEB-INF/jspf/solicitudes/paginas/view/viewHeader.jspf" %>

<%@ include file="/WEB-INF/jspf/layout/table/tableHeader.jspf" %>

<tr>
    <th><s:text name="OpenSGSBean.attribute.lastnames"/></th>
    <td><s:property value="dtOpenSGSManagedBean.apellidos"/></td>
</tr>

<tr>
    <th><s:text name="OpenSGSBean.attribute.names"/></th>
    <td><s:property value="dtOpenSGSManagedBean.nombres"/></td>
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
    <th><s:text name="Pagina.attribute.genero"/></th>
    <td><s:property value="dtOpenSGSManagedBean.genero"/></td>
</tr>

<tr>
    <th><s:text name="Pagina.attribute.fechaNacimiento"/></th>
    <td><s:property value="dtOpenSGSManagedBean.fechaNacimiento"/></td>
</tr>

<tr>
    <th><s:text name="Pagina.attribute.nacionalidad"/></th>
    <td><s:property value="dtOpenSGSManagedBean.nacionalidad"/></td>
</tr>

<tr>
    <th><s:text name="OpenSGSBean.attribute.phone"/></th>
    <td><s:property value="dtOpenSGSManagedBean.celular"/></td>
</tr>

<tr>
    <th><s:text name="Pagina.attribute.departamentoPaisResidencia"/></th>
    <td><s:property value="dtOpenSGSManagedBean.departamentoPaisResidencia"/></td>
</tr>

<%@ include file="/WEB-INF/jspf/layout/table/tableFooter.jspf" %>
