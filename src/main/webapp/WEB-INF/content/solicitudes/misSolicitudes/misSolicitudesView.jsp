<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%@ include file="/WEB-INF/jspf/layout/header.jspf" %>

<%@ include file="/WEB-INF/jspf/navigation/solicitudes/navigationMisSolicitudesView.jspf" %>

<%@ include file="/WEB-INF/jspf/layout/view/viewHeader.jspf" %>

<%@ include file="/WEB-INF/jspf/layout/table/tableHeader.jspf" %>

<%@ include file="/WEB-INF/jspf/opensgs/bean/view/tableValuesStart.jspf" %>

<%@ include file="/WEB-INF/jspf/opensgs/managedBean/view/tableValuesStart.jspf" %>

<tr>
    <th><s:text name="OpenSGSBean.attribute.delivered"/></th>
    <td>
        <s:if test="%{dtOpenSGSManagedBean.entregado}">
            <s:text name="OpenSGSBean.text.yes"/>
        </s:if>
        <s:else>
            <s:text name="OpenSGSBean.text.no"/>
        </s:else>
    </td>
</tr>

<tr>
    <th><s:text name="OpenSGSBean.attribute.deliveredDate"/></th>
    <td><s:property value="dtOpenSGSManagedBean.entregadoFecha"/></td>
</tr>

<tr>
    <th><s:text name="OpenSGSBean.attribute.financed"/></th>
    <td>
        <s:if test="%{dtOpenSGSManagedBean.financiado}">
            <s:text name="OpenSGSBean.text.yes"/>
        </s:if>
        <s:else>
            <s:text name="OpenSGSBean.text.no"/>
        </s:else>
    </td>
</tr>

<tr>
    <th><s:text name="OpenSGSBean.attribute.financedDate"/></th>
    <td><s:property value="dtOpenSGSManagedBean.financiadoFecha"/></td>
</tr>

<tr>
    <th><s:text name="OpenSGSBean.attribute.completed"/></th>
    <td>
        <s:if test="%{dtOpenSGSManagedBean.cursado}">
            <s:text name="OpenSGSBean.text.yes"/>
        </s:if>
        <s:else>
            <s:text name="OpenSGSBean.text.no"/>
        </s:else>
    </td>
</tr>

<tr>
    <th><s:text name="OpenSGSBean.attribute.completedDate"/></th>
    <td><s:property value="dtOpenSGSManagedBean.cursadoFecha"/></td>
</tr>

<tr>
    <th><s:text name="OpenSGSBean.attribute.approved"/></th>
    <td>
        <s:if test="%{dtOpenSGSManagedBean.aprobado}">
            <s:text name="OpenSGSBean.text.yes"/>
        </s:if>
        <s:else>
            <s:text name="OpenSGSBean.text.no"/>
        </s:else>
    </td>
</tr>

<tr>
    <th><s:text name="OpenSGSBean.attribute.approvedDate"/></th>
    <td><s:property value="dtOpenSGSManagedBean.aprobadoFecha"/></td>
</tr>

<tr>
    <th><s:text name="OpenSGSBean.attribute.application"/></th>
    <td><s:property value="dtOpenSGSManagedBean.dtAplicacion.titulo"/></td>
</tr>

<tr>
    <th><s:text name="OpenSGSBean.attribute.user"/></th>
    <td><s:property value="dtOpenSGSManagedBean.dtUsuario.email"/></td>
</tr>

<%@ include file="/WEB-INF/jspf/opensgs/bean/view/tableValuesEnd.jspf" %>

<%@ include file="/WEB-INF/jspf/opensgs/managedBean/view/tableValuesEnd.jspf" %>

<%@ include file="/WEB-INF/jspf/layout/table/tableFooter.jspf" %>

<%@ include file="/WEB-INF/jspf/solicitudes/paginas/view/paginaSolicitudView.jspf" %>

<%@ include file="/WEB-INF/jspf/layout/view/viewFooter.jspf" %>

<%@ include file="/WEB-INF/jspf/layout/footer.jspf" %>

