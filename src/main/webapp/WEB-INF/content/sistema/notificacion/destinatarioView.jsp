<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%@ include file="/WEB-INF/jspf/layout/header.jspf" %>

<%@ include file="/WEB-INF/jspf/navigation/sistema/notificacion/navigationNotificacionDestinatarioVer.jspf" %>

<%@ include file="/WEB-INF/jspf/layout/view/viewHeader.jspf" %>

<%@ include file="/WEB-INF/jspf/layout/table/tableHeader.jspf" %>

<%@ include file="/WEB-INF/jspf/opensgs/bean/view/tableValuesStart.jspf" %>

<tr>
    <th><s:text name="OpenSGSBean.attribute.email"/></th>
    <td><s:property value="dtOpenSGSBean.email"/></td>
</tr>

<%@ include file="/WEB-INF/jspf/opensgs/bean/view/tableValuesEnd.jspf" %>

<%@ include file="/WEB-INF/jspf/layout/table/tableFooter.jspf" %>

<%@ include file="/WEB-INF/jspf/layout/view/viewFooter.jspf" %>

<%@ include file="/WEB-INF/jspf/layout/footer.jspf" %>

