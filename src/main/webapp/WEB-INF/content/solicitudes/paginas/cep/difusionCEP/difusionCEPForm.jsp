<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%@ include file="/WEB-INF/jspf/layout/header.jspf" %>

<%@ include file="/WEB-INF/jspf/navigation/solicitudes/navigationPaginaSolicitudesForm.jspf" %>

<%@ include file="/WEB-INF/jspf/solicitudes/paginas/paginasHeader.jspf" %>

<%@ include file="/WEB-INF/jspf/layout/form/formHeader.jspf" %>

<s:form action="%{actionName}" method="post" validate="true" theme="simple">

    <%@ include file="/WEB-INF/jspf/opensgs/managedBean/form/formType.jspf" %>

    <%@ include file="/WEB-INF/jspf/solicitudes/paginas/form/formPaginaSolicitud.jspf" %>
    
    <%@ include file="/WEB-INF/jspf/solicitudes/paginas/textoInicial.jspf" %>

    <%@ include file="/WEB-INF/jspf/solicitudes/paginas/field/medios.jspf" %>
    
    <%@ include file="/WEB-INF/jspf/solicitudes/paginas/textoFinal.jspf" %>

    <%@ include file="/WEB-INF/jspf/field/buttonSave.jspf" %>

</s:form>

<%@ include file="/WEB-INF/jspf/layout/form/formFooter.jspf" %>

<%@ include file="/WEB-INF/jspf/solicitudes/paginas/paginasFooter.jspf" %>

<%@ include file="/WEB-INF/jspf/layout/footer.jspf" %>
