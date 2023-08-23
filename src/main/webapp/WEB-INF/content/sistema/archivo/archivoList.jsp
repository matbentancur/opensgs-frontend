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
<th><s:text name="OpenSGSBean.attribute.mime"/></th>
<th><s:text name="OpenSGSBean.attribute.extension"/></th>
<th><s:text name="OpenSGSBean.attribute.size"/></th>
<th><s:text name="OpenSGSBean.attribute.path"/></th>
<th><s:text name="OpenSGSBean.attribute.fileScope"/></th>

<%@ include file="/WEB-INF/jspf/opensgs/bean/list/tableColumnsEnd.jspf" %>

<%@ include file="/WEB-INF/jspf/opensgs/managedBean/list/tableColumnsEnd.jspf" %>

<%@ include file="/WEB-INF/jspf/layout/table/tableHeadEnd.jspf" %>

<!--VALORES-->
<s:iterator value="listaDtOpenSGSManagedBeans" status="status">

    <%@ include file="/WEB-INF/jspf/sistema/archivo/list/listValues.jspf" %>

</s:iterator>

<s:iterator value="listaDtArchivos" status="status">

    <%@ include file="/WEB-INF/jspf/sistema/archivo/list/listValues.jspf" %>

</s:iterator>

<%@ include file="/WEB-INF/jspf/layout/table/tableFooter.jspf" %>

<%@ include file="/WEB-INF/jspf/layout/list/listFooter.jspf" %>

<%@ include file="/WEB-INF/jspf/layout/footer.jspf" %>