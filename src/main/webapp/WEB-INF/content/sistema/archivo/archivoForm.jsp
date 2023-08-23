<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%@ include file="/WEB-INF/jspf/layout/header.jspf" %>

<%@ include file="/WEB-INF/jspf/navigation/navigationOpenSGSBeanForm.jspf" %>

<%@ include file="/WEB-INF/jspf/layout/form/formHeader.jspf" %>

<s:form action="%{actionName}" method="post" enctype="multipart/form-data" validate="true" theme="simple">

    <%@ include file="/WEB-INF/jspf/opensgs/managedBean/form/formType.jspf" %>

    <%@ include file="/WEB-INF/jspf/field/name.jspf" %>

    <%@ include file="/WEB-INF/jspf/field/title.jspf" %>

    <s:if test="aplicacionId == null" >
        <%@ include file="/WEB-INF/jspf/field/fileScope.jspf" %>
    </s:if>
    <s:else>
        <%@ include file="/WEB-INF/jspf/field/fileScopeHidden.jspf" %>
        <%@ include file="/WEB-INF/jspf/field/applicationId.jspf" %>
    </s:else>

    <%@ include file="/WEB-INF/jspf/field/upload.jspf" %>

    <%@ include file="/WEB-INF/jspf/field/buttonSave.jspf" %>

</s:form>

<%@ include file="/WEB-INF/jspf/layout/form/formFooter.jspf" %>

<%@ include file="/WEB-INF/jspf/layout/footer.jspf" %>
