<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%@ include file="/WEB-INF/jspf/layout/header.jspf" %>

<%@ include file="/WEB-INF/jspf/navigation/navigationOpenSGSBeanForm.jspf" %>

<%@ include file="/WEB-INF/jspf/layout/form/formHeader.jspf" %>

<s:form action="%{actionName}" method="post" validate="true" theme="simple">

    <%@ include file="/WEB-INF/jspf/opensgs/managedBean/form/formType.jspf" %>

    <%@ include file="/WEB-INF/jspf/field/name.jspf" %>

    <%@ include file="/WEB-INF/jspf/field/text.jspf" %>

    <%@ include file="/WEB-INF/jspf/field/start.jspf" %>

    <%@ include file="/WEB-INF/jspf/field/finish.jspf" %>

    <s:if test="aplicacionId == null" >
        <%@ include file="/WEB-INF/jspf/field/scope.jspf" %>
    </s:if>
    <s:else>
        <%@ include file="/WEB-INF/jspf/field/scopeHidden.jspf" %>
        <%@ include file="/WEB-INF/jspf/field/applicationId.jspf" %>
    </s:else>

    <%@ include file="/WEB-INF/jspf/field/buttonSave.jspf" %>

</s:form>

<%@ include file="/WEB-INF/jspf/layout/form/formFooter.jspf" %>

<%@ include file="/WEB-INF/jspf/layout/footer.jspf" %>
