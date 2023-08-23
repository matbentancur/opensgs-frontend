$(document).ready(function(){
    var elementDialogo = $("#dialogo");
    var elementDialogoUpload = $("#dialogo_upload");
    
    if (elementDialogo.length > 0){
        elementDialogo.find("input[type='submit']").click(function( event ) {
            elementDialogo.find('form').hide();
        });
        //STYLE SUBMIT BUTTON
        elementDialogo.find("input[type='submit']").parent().addClass("centrado");
        //HIDE FORM
        elementDialogo.find('form').hide();
    }
    
    if (elementDialogoUpload.length > 0){
        elementDialogoUpload.find("input[type='submit']").click(function( event ) {
            elementDialogoUpload.find('form').hide();
        });
        //STYLE SUBMIT BUTTON
        elementDialogoUpload.find("input[type='submit']").parent().addClass("centrado");
        //HIDE FORM
        elementDialogoUpload.find('form').hide();
    }
    //REMOVE FOCUS ON CLOSE BUTTON
    $(':focus').blur();
    return false;
});

$(window).ready(function(){
    $(".loadingAnidado").fadeOut("slow");
    $("#dialogo").find('form').show();
    $("#dialogo_upload").find('form').show();
    //ERRORS
    dialogErrors();
    return false;
});


function dialogErrors(){
    //ERRORS
    var elementDialogo = $("#dialogo");
    var elementDialogoUpload = $("#dialogo_upload");
    var lsMensajeError =  elementDialogo.find(".tdErrorMessage");
    if (lsMensajeError.length > 0) {
        if (elementDialogo.length > 0){
            elementDialogo.animate({ scrollTop: $(".tdErrorMessage:first").offset().top - elementDialogo.offset().top + elementDialogo.scrollTop() }, 1000, 'swing');
        }
        if (elementDialogoUpload.length > 0){
            elementDialogoUpload.animate({ scrollTop: $(".tdErrorMessage:first").offset().top - elementDialogoUpload.offset().top + elementDialogoUpload.scrollTop() }, 1000, 'swing');
        }
    }
    return false;
}