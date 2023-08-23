<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%@ include file="/WEB-INF/jspf/layout/header.jspf" %>

<%@ include file="/WEB-INF/jspf/navigation/navigationOpenSGSBeanForm.jspf" %>

<%@ include file="/WEB-INF/jspf/layout/form/formHeader.jspf" %>

<s:form action="%{actionName}" method="post" validate="true" theme="simple">

    <%@ include file="/WEB-INF/jspf/opensgs/managedBean/form/formType.jspf" %>

    <%@ include file="/WEB-INF/jspf/field/name.jspf" %>
    
    <%@ include file="/WEB-INF/jspf/field/title.jspf" %>

    <%@ include file="/WEB-INF/jspf/field/initialText.jspf" %>

    <%@ include file="/WEB-INF/jspf/field/finalText.jspf" %>
    
    <%@ include file="/WEB-INF/jspf/field/page.jspf" %>

    <%@ include file="/WEB-INF/jspf/field/dataStructure.jspf" %>
    
    <%@ include file="/WEB-INF/jspf/field/applicationTemplateId.jspf" %>

    <%@ include file="/WEB-INF/jspf/field/buttonSave.jspf" %>

</s:form>

<%@ include file="/WEB-INF/jspf/layout/form/formFooter.jspf" %>

<%@ include file="/WEB-INF/jspf/layout/footer.jspf" %>
