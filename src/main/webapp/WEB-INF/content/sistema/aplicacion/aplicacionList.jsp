<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%@ include file="/WEB-INF/jspf/layout/header.jspf" %>

<%@ include file="/WEB-INF/jspf/navigation/navigationOpenSGSBeanList.jspf" %>

<%@ include file="/WEB-INF/jspf/layout/list/listHeader.jspf" %>

<%@ include file="/WEB-INF/jspf/opensgs/managedBean/list/menuActions.jspf" %>

<!--TABLA-->
<%@ include file="/WEB-INF/jspf/layout/table/tableHeader.jspf" %>

<%@ include file="/WEB-INF/jspf/layout/table/tableHeadStart.jspf" %>

<!--COLUMNAS-->
<%@ include file="/WEB-INF/jspf/opensgs/bean/list/tableColumnsStart.jspf" %>

<%@ include file="/WEB-INF/jspf/opensgs/managedBean/list/tableColumnsStart.jspf" %>

<th><s:text name="OpenSGSBean.attribute.name"/></th>
<th><s:text name="OpenSGSBean.attribute.title"/></th>
<th><s:text name="OpenSGSBean.attribute.opening"/></th>
<th><s:text name="OpenSGSBean.attribute.closing"/></th>
<th><s:text name="OpenSGSBean.attribute.contact"/></th>
<th><s:text name="OpenSGSBean.attribute.requestPerUser"/></th>
<th><s:text name="OpenSGSBean.attribute.totalRequests"/></th>
<th><s:text name="OpenSGSBean.attribute.applicationTemplate"/></th>

<%@ include file="/WEB-INF/jspf/opensgs/bean/list/tableColumnsEnd.jspf" %>

<%@ include file="/WEB-INF/jspf/opensgs/managedBean/list/tableColumnsEnd.jspf" %>

<%@ include file="/WEB-INF/jspf/layout/table/tableHeadEnd.jspf" %>

<!--VALORES-->
<s:iterator value="listaDtOpenSGSManagedBeans" status="status">

    <%@ include file="/WEB-INF/jspf/layout/table/tableValuesStart.jspf" %>

    <td>
        <%@ include file="/WEB-INF/jspf/opensgs/bean/list/tableActionsHeader.jspf" %>
        <%@ include file="/WEB-INF/jspf/opensgs/managedBean/list/tableActions.jspf" %>
        <%@ include file="/WEB-INF/jspf/sistema/aplicacion/list/tableActions.jspf" %>
        <%@ include file="/WEB-INF/jspf/opensgs/bean/list/tableActionsFooter.jspf" %>
    </td>

    <%@ include file="/WEB-INF/jspf/opensgs/bean/list/tableValuesStart.jspf" %>

    <%@ include file="/WEB-INF/jspf/opensgs/managedBean/list/tableValuesStart.jspf" %>
    
    <td><s:property value="nombre"/></td>
    <td><s:property value="titulo"/></td>
    <td><s:property value="apertura"/></td>
    <td><s:property value="cierre"/></td>
    <td><s:property value="contacto"/></td>
    <td><s:property value="solicitudesPorUsuario"/></td>
    <td><s:property value="solicitudesTotal"/></td>
    <td><s:property value="dtPlantillaAplicacion.nombre"/></td>

    <%@ include file="/WEB-INF/jspf/opensgs/bean/list/tableValuesEnd.jspf" %>

    <%@ include file="/WEB-INF/jspf/opensgs/managedBean/list/tableValuesEnd.jspf" %>

    <%@ include file="/WEB-INF/jspf/layout/table/tableValuesEnd.jspf" %>

</s:iterator>

<%@ include file="/WEB-INF/jspf/layout/table/tableFooter.jspf" %>

<%@ include file="/WEB-INF/jspf/layout/list/listFooter.jspf" %>

<%@ include file="/WEB-INF/jspf/layout/footer.jspf" %>