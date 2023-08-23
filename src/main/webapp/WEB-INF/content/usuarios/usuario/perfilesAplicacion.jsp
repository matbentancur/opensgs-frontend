<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%@ include file="/WEB-INF/jspf/layout/header.jspf" %>

<%@ include file="/WEB-INF/jspf/navigation/navigationOpenSGSBeanChild.jspf" %>

<%@ include file="/WEB-INF/jspf/layout/form/formHeader.jspf" %>

<s:form action="%{actionName}!agregar" method="post" validate="true" theme="simple">

    <%@ include file="/WEB-INF/jspf/opensgs/managedBean/form/formType.jspf" %>
    
    <%@ include file="/WEB-INF/jspf/field/applicationProfiles.jspf" %>
    
    <%@ include file="/WEB-INF/jspf/field/activeApplications.jspf" %>

    <%@ include file="/WEB-INF/jspf/field/buttonAdd.jspf" %>

</s:form>

<%@ include file="/WEB-INF/jspf/layout/form/formFooter.jspf" %>

<%@ include file="/WEB-INF/jspf/layout/list/listHeader.jspf" %>

<!--TABLA-->
<%@ include file="/WEB-INF/jspf/layout/table/tableHeader.jspf" %>

<%@ include file="/WEB-INF/jspf/layout/table/tableHeadStart.jspf" %>

<!--COLUMNAS-->
<%@ include file="/WEB-INF/jspf/opensgs/bean/list/tableColumnsStart.jspf" %>

<th><s:text name="OpenSGSBean.attribute.profile"/></th>
<th><s:text name="OpenSGSBean.attribute.application"/></th>

<%@ include file="/WEB-INF/jspf/opensgs/bean/list/tableColumnsEnd.jspf" %>

<%@ include file="/WEB-INF/jspf/layout/table/tableHeadEnd.jspf" %>

<s:iterator value="dtUsuario.dtPerfilesAplicacion" status="status">

    <%@ include file="/WEB-INF/jspf/layout/table/tableValuesStart.jspf" %>

    <td>
        <%@ include file="/WEB-INF/jspf/opensgs/bean/list/tableActionsHeader.jspf" %>
        <%@ include file="/WEB-INF/jspf/usuarios/perfilAplicacion/list/tableActions.jspf" %>
        <%@ include file="/WEB-INF/jspf/opensgs/bean/list/tableActionsFooter.jspf" %>
    </td>

    <%@ include file="/WEB-INF/jspf/opensgs/bean/list/tableValuesStart.jspf" %>

    <td><s:property value="dtPerfil.nombre"/></td>
    <td><s:property value="dtAplicacion.titulo"/></td>

    <%@ include file="/WEB-INF/jspf/opensgs/bean/list/tableValuesEnd.jspf" %>

    <%@ include file="/WEB-INF/jspf/layout/table/tableValuesEnd.jspf" %>

</s:iterator>

<%@ include file="/WEB-INF/jspf/layout/table/tableFooter.jspf" %>

<%@ include file="/WEB-INF/jspf/layout/list/listFooter.jspf" %>

<%@ include file="/WEB-INF/jspf/layout/footer.jspf" %>