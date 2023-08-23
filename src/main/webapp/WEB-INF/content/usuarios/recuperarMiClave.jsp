<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%@ include file="/WEB-INF/jspf/layout/header.jspf" %>

<%@ include file="/WEB-INF/jspf/navigation/usuarios/navigationRecuperarMiClave.jspf" %>

<%@ include file="/WEB-INF/jspf/layout/form/formHeader.jspf" %>

<s:form action="%{actionName}" method="post" validate="true" theme="simple">

    <%@ include file="/WEB-INF/jspf/field/email.jspf" %>

    <%@ include file="/WEB-INF/jspf/field/captchaAnswer.jspf" %>

    <%@ include file="/WEB-INF/jspf/field/captchaAnswerImage.jspf" %>

    <%@ include file="/WEB-INF/jspf/field/buttonConfirm.jspf" %>

</s:form>

<%@ include file="/WEB-INF/jspf/layout/form/formFooter.jspf" %>

<%@ include file="/WEB-INF/jspf/layout/footer.jspf" %>
