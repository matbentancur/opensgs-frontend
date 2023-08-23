<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%@ include file="/WEB-INF/jspf/layout/header.jspf" %>

<%@ include file="/WEB-INF/jspf/navigation/navigationOpenSGSBeanList.jspf" %>

<%@ include file="/WEB-INF/jspf/layout/form/formHeader.jspf" %>

<s:form action="%{actionName}" method="post" validate="true" theme="simple">

    <%@ include file="/WEB-INF/jspf/opensgs/managedBean/form/formType.jspf" %>

    <%@ include file="/WEB-INF/jspf/field/name.jspf" %>

    <%@ include file="/WEB-INF/jspf/field/url.jspf" %>
    
    <%@ include file="/WEB-INF/jspf/field/maintenance.jspf" %>
    
    <%@ include file="/WEB-INF/jspf/field/maxUsers.jspf" %>
    
    <%@ include file="/WEB-INF/jspf/field/fileUploadMaxSize.jspf" %>
    
    <%@ include file="/WEB-INF/jspf/field/supportEmail.jspf" %>
    
    <%@ include file="/WEB-INF/jspf/field/filesPath.jspf" %>
    
    <%@ include file="/WEB-INF/jspf/field/mailServer.jspf" %>
    
    <%@ include file="/WEB-INF/jspf/field/aplicationServer.jspf" %>
    
    <%@ include file="/WEB-INF/jspf/field/authenticationServer.jspf" %>

    <%@ include file="/WEB-INF/jspf/field/buttonSave.jspf" %>

</s:form>

<%@ include file="/WEB-INF/jspf/layout/form/formFooter.jspf" %>

<%@ include file="/WEB-INF/jspf/layout/footer.jspf" %>