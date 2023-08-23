<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%@ include file="/WEB-INF/jspf/layout/header.jspf" %>

<%@ include file="/WEB-INF/jspf/navigation/solicitudes/navigationMisSolicitudes.jspf" %>

<%@ include file="/WEB-INF/jspf/layout/list/listHeader.jspf" %>

<%@ include file="/WEB-INF/jspf/solicitudes/misSolicitudes/list/menuActions.jspf" %>

<!--TABLA-->
<%@ include file="/WEB-INF/jspf/layout/table/tableHeader.jspf" %>

<%@ include file="/WEB-INF/jspf/layout/table/tableHeadStart.jspf" %>

<!--COLUMNAS-->
<%@ include file="/WEB-INF/jspf/opensgs/bean/list/tableColumnsStart.jspf" %>

<th><s:text name="OpenSGSBean.attribute.delivered"/></th>
<th><s:text name="OpenSGSBean.attribute.financed"/></th>
<th><s:text name="OpenSGSBean.attribute.completed"/></th>
<th><s:text name="OpenSGSBean.attribute.approved"/></th>
<th><s:text name="OpenSGSBean.attribute.application"/></th>

<%@ include file="/WEB-INF/jspf/layout/table/tableHeadEnd.jspf" %>

<!--VALORES-->
<s:iterator value="listaDtSolicitudes" status="status">

    <%@ include file="/WEB-INF/jspf/layout/table/tableValuesStart.jspf" %>

    <td>
        <%@ include file="/WEB-INF/jspf/opensgs/bean/list/tableActionsHeader.jspf" %>
        <%@ include file="/WEB-INF/jspf/solicitudes/misSolicitudes/list/tableActions.jspf" %>
        <%@ include file="/WEB-INF/jspf/opensgs/bean/list/tableActionsFooter.jspf" %>
    </td>

    <%@ include file="/WEB-INF/jspf/opensgs/bean/list/tableValuesStart.jspf" %>
   
    <td><s:if test="%{entregado}"><s:text name="OpenSGSBean.text.yes"/></s:if><s:else><s:text name="OpenSGSBean.text.no"/></s:else></td>
    <td><s:if test="%{financiado}"><s:text name="OpenSGSBean.text.yes"/></s:if><s:else><s:text name="OpenSGSBean.text.no"/></s:else></td>
    <td><s:if test="%{cursado}"><s:text name="OpenSGSBean.text.yes"/></s:if><s:else><s:text name="OpenSGSBean.text.no"/></s:else></td>
    <td><s:if test="%{aprobado}"><s:text name="OpenSGSBean.text.yes"/></s:if><s:else><s:text name="OpenSGSBean.text.no"/></s:else></td>
    <td><s:property value="dtAplicacion.titulo"/></td>

    <%@ include file="/WEB-INF/jspf/layout/table/tableValuesEnd.jspf" %>

</s:iterator>

<%@ include file="/WEB-INF/jspf/layout/table/tableFooter.jspf" %>

<%@ include file="/WEB-INF/jspf/layout/list/listFooter.jspf" %>

<%@ include file="/WEB-INF/jspf/layout/footer.jspf" %>