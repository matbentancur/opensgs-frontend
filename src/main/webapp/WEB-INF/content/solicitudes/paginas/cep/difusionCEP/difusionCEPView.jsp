<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%@ include file="/WEB-INF/jspf/solicitudes/paginas/view/viewHeader.jspf" %>

<%@ include file="/WEB-INF/jspf/layout/table/tableHeader.jspf" %>

<tr>
    <th><s:text name="Pagina.attribute.medios"/></th>
    <td>
        <s:iterator value="medios" status="status">
            <li><s:property/></li>
        </s:iterator>
    </td>
</tr>

<%@ include file="/WEB-INF/jspf/layout/table/tableFooter.jspf" %>
