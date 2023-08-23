<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%@ include file="/WEB-INF/jspf/layout/header.jspf" %>

<%@ include file="/WEB-INF/jspf/navigation/solicitudes/navigationMisSolicitudesConstancias.jspf" %>

<%@ include file="/WEB-INF/jspf/layout/list/listHeader.jspf" %>

<!--TABLA-->
<%@ include file="/WEB-INF/jspf/layout/table/tableHeader.jspf" %>

<%@ include file="/WEB-INF/jspf/layout/table/tableHeadStart.jspf" %>

<!--COLUMNAS-->
<%@ include file="/WEB-INF/jspf/opensgs/bean/list/tableColumnsStart.jspf" %>

<th><s:text name="OpenSGSBean.attribute.name"/></th>
<th><s:text name="OpenSGSBean.attribute.certificateType"/></th>

<%@ include file="/WEB-INF/jspf/opensgs/bean/list/tableColumnsEnd.jspf" %>

<%@ include file="/WEB-INF/jspf/layout/table/tableHeadEnd.jspf" %>

<!--VALORES-->
<s:iterator value="listaDtConstancias" status="status">
    <%@ include file="/WEB-INF/jspf/layout/table/tableValuesStart.jspf" %>

    <td>
        <%@ include file="/WEB-INF/jspf/opensgs/bean/list/tableActionsHeader.jspf" %>
        <%@ include file="/WEB-INF/jspf/solicitudes/misSolicitudes/constancias/tableActions.jspf" %>
        <%@ include file="/WEB-INF/jspf/opensgs/bean/list/tableActionsFooter.jspf" %>
    </td>

    <%@ include file="/WEB-INF/jspf/opensgs/bean/list/tableValuesStart.jspf" %>

    <td><s:property value="nombre"/></td>
    <td><s:property value="constanciaTipo"/></td>

    <%@ include file="/WEB-INF/jspf/opensgs/bean/list/tableValuesEnd.jspf" %>

    <%@ include file="/WEB-INF/jspf/layout/table/tableValuesEnd.jspf" %>
</s:iterator>

<%@ include file="/WEB-INF/jspf/layout/table/tableFooter.jspf" %>

<%@ include file="/WEB-INF/jspf/layout/list/listFooter.jspf" %>

<%@ include file="/WEB-INF/jspf/layout/footer.jspf" %>