<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%@ include file="/WEB-INF/jspf/layout/header.jspf" %>

<%@ include file="/WEB-INF/jspf/navigation/solicitudes/navigationPaginaSolicitudesForm.jspf" %>

<%@ include file="/WEB-INF/jspf/layout/form/formHeader.jspf" %>

<s:form action="%{actionName}" method="post" validate="true" theme="simple">

    <%@ include file="/WEB-INF/jspf/opensgs/managedBean/form/formType.jspf" %>
    
    <s:hidden name="aplicacionId"/>

    <div class="alert alert-dark" role="alert">
        <s:property value="textoAcuerdo"/>
    </div>

    <div class="form-check">
        <input class="form-check-input" type="checkbox" value="" id="agree">
        <label class="form-check-label" for="agree">
            <s:text name="OpenSGSBean.text.agree"/>
        </label>
    </div>

    <%@ include file="/WEB-INF/jspf/field/buttonNext.jspf" %>

</s:form>

<%@ include file="/WEB-INF/jspf/layout/form/formFooter.jspf" %>

<%@ include file="/WEB-INF/jspf/layout/footer.jspf" %>

<script>
    var checkboxAgree = document.getElementById('agree');
    var buttonNext = document.getElementById('SolicitudAction_0');
    buttonNext.disabled = true;
    checkboxAgree.onchange = function () {
        buttonNext.disabled = !this.checked;
    };
</script>


