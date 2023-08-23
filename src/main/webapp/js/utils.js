//MOUSE OVER FIRST ELEMENT
$(window).load(function () {
    var form = $("form");
    if (form.attr("id") != "PrincipalValidationAction") {
        form.find('input[type=text],textarea,select').filter(':visible:first').focus();
    }
    return false;
});

function confirmarSalir() {
    return confirm('Sí sale sin guardar los datos, los mismos se perderan.\n¿Está seguro que desea salir?');
}

function confirmarEliminar() {
    return confirm('¿Está seguro que desea eliminar el elemento?');
}

function confirmarRestaurar() {
    return confirm('¿Está seguro que desea restaurar el elemento?');
}

function confirmarInvitar() {
    return confirm('¿Está seguro que desea enviar la invitación?');
}

function abrirDialogoWithParams(idDialogo, titulo, href, hrefParameter, publicar) {
    var dialogo = $('#' + idDialogo);
    dialogo.html($('.loadingAnidado').clone().show());
    var opt = {
        title: titulo,
        close: function (event, ui) {}
    };
    dialogo.dialog(opt);
    dialogo.on("dialogclose", function (event, ui) {
        dialogo.empty()
    });
    dialogo.load(href + '?' + hrefParameter);
    if (publicar != null) {
        dialogo.dialog('option', 'close', function (event, ui) {
            $.publish(publicar);
        });
        dialogo.dialog("option", "hide", {effect: "fade", duration: 250});
    }
    dialogo.dialog("open");
}

function abrirDialogo(idDialogo, titulo, href, publicar) {
    var dialogo = $('#' + idDialogo);
    dialogo.html($('.loadingAnidado').clone().show());
    var opt = {
        title: titulo,
        close: function (event, ui) {}
    };
    dialogo.dialog(opt);
    dialogo.on("dialogclose", function (event, ui) {
        dialogo.empty()
    });
    dialogo.load(href);
    if (publicar != null) {
        dialogo.dialog('option', 'close', function (event, ui) {
            $.publish(publicar);
        });
        dialogo.dialog("option", "hide", {effect: "fade", duration: 250});
    }
    dialogo.dialog("open");
}

function showElementByID(elementID) {
    var element = $("#" + elementID);
    element.parent().parent().show("slow");
    element.parent().parent().prev("tr[errorfor]").show("slow");
    return false;
}

function hideElementByID(elementID) {
    var element = $("#" + elementID);
    element.parent().parent().hide();
    element.parent().parent().prev("tr[errorfor]").hide();
    if (element.is("input[type='radio']")) {
        element.prop('checked', false);
    } else {
        element.val("");
    }
    element.trigger("change");
    return false;
}

function showElementByName(elementName) {
    var element = $("[name='" + elementName + "']");
    element.parent().parent().show("slow");
    element.parent().parent().prev("tr[errorfor]").show("slow");
    return false;
}

function hideElementByName(elementName) {
    var element = $("[name='" + elementName + "']");
    element.parent().parent().hide();
    element.parent().parent().prev("tr[errorfor]").hide();
    if (element.is("input[type='radio']")) {
        element.prop('checked', false);
        element.each(function () {
            var elementValue = $(this).val();
            $(this).val("");
            $(this).trigger("change");
            $(this).val(elementValue);
            return false;
        });
    } else {
        element.val("");
        element.trigger("change");
    }
    return false;
}

function addOnchangeElementByNameShowHideByID(elementName, dependentElementId, showValue, hideValue, changeOnLoad) {
    var element = $("[name='" + elementName + "']");
    element.change(function () {
        var valor = $(this).val();
        if (valor == showValue) {
            showElementByID(dependentElementId);
        } else if (valor == hideValue) {
            hideElementByID(dependentElementId);
        } else {
            hideElementByID(dependentElementId);
        }
    });
    if (changeOnLoad == true) {
        if (element.is("input[type='radio']")) {
            if (element.is("input[type='radio']:checked")) {
                $("[name='" + elementName + "']:checked").trigger("change");
            } else {
                hideElementByID(dependentElementId);
            }
        } else {
            element.trigger("change");
        }
    }
    return false;
}

function addOnchangeElementByNameShowHideByName(elementName, dependentElementName, showValue, hideValue, changeOnLoad) {
    var element = $("[name='" + elementName + "']");
    element.change(function () {
        var valor = $(this).val();
        if (valor == showValue) {
            showElementByName(dependentElementName);
        } else if (valor == hideValue) {
            hideElementByName(dependentElementName);
        } else {
            hideElementByName(dependentElementName);
        }
    });
    if (changeOnLoad == true) {
        if (element.is("input[type='radio']")) {
            if (element.is("input[type='radio']:checked")) {
                $("[name='" + elementName + "']:checked").trigger("change");
            } else {
                hideElementByName(dependentElementName);
            }
        } else {
            element.trigger("change");
        }
    }
    return false;
}

function addOnchangeElementByIDShowHideByID(elementID, dependentElementId, showValue, hideValue, changeOnLoad) {
    var element = $("#" + elementID);
    element.change(function () {
        var valor = $(this).val();
        if (valor == showValue) {
            showElementByID(dependentElementId);
        } else if (valor == hideValue) {
            hideElementByID(dependentElementId);
        } else {
            hideElementByID(dependentElementId);
        }
    });
    if (changeOnLoad == true) {
        element.trigger("change");
    }
    return false;
}

function mouseFocus(elementID) {
    $("#" + elementID).focus();
    return false;
}

function createDataTableByID(elementID) {
    
    $("#" + elementID).DataTable( {
        "lengthMenu": [[50, 100, 200, 300, 500, -1], [50, 100, 200, 300, 500, "Todos"]],
        "language": {
            "sProcessing":     "Procesando...",
            "sLengthMenu":     "Mostrar _MENU_ registros",
            "sZeroRecords":    "No se encontraron resultados",
            "sEmptyTable":     "Ningún dato disponible en esta tabla",
            "sInfo":           "Mostrando registros del _START_ al _END_ de un total de _TOTAL_ registros",
            "sInfoEmpty":      "Mostrando registros del 0 al 0 de un total de 0 registros",
            "sInfoFiltered":   "(filtrado de un total de _MAX_ registros)",
            "sInfoPostFix":    "",
            "sSearch":         "Buscar:",
            "sUrl":            "",
            "sInfoThousands":  ",",
            "sLoadingRecords": "Cargando...",
            "oPaginate": {
                "sFirst":    "Primero",
                "sLast":     "Último",
                "sNext":     "Siguiente",
                "sPrevious": "Anterior"
            },
            "oAria": {
                "sSortAscending":  ": Activar para ordenar la columna de manera ascendente",
                "sSortDescending": ": Activar para ordenar la columna de manera descendente"
            },
        },
    } );
    return false;
}

function createTinyRichText(elementID) {
    
    tinymce.init({
        mode: "exact",
        elements : elementID,
        theme: "modern",
        language: "es",
        plugins: "contextmenu image table link colorpicker textcolor paste",
        font_formats: "sans-serif",
        menubar: false,
        statusbar: false,
        toolbar: "undo redo | styleselect | bold italic | forecolor backcolor | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link | image", 
        branding: false,
    })
    return false;
}

function recargarPagina() {
    location.reload();
}

function createDateTimePickerByID(elementID) {

    $.datetimepicker.setLocale('es');
    
    $("#" + elementID).datetimepicker({
        format:	'd/m/Y H:i',
        step: 1
    });

    $("#" + elementID).attr("placeholder", "dd/mm/aaaa hh:mm");
    return false;
}

function createDateTimePickerByName(elementName) {

    $.datetimepicker.setLocale('es');
    
    $("[name='" + elementName + "']").datetimepicker({
        format:	'd/m/Y H:i',
        step: 1
    });

    $("[name='" + elementName + "']").attr("placeholder", "dd/mm/aaaa hh:mm");
    return false;
}

function createDatePickerByID(elementID) {

    $.datetimepicker.setLocale('es');
    
    $("#" + elementID).datetimepicker({
        format:	'd/m/Y',
        timepicker:false,
        step: 1
    });

    $("#" + elementID).attr("placeholder", "dd/mm/aaaa");
    return false;
}

function createDatePickerByName(elementName) {

    $.datetimepicker.setLocale('es');
    
    $("[name='" + elementName + "']").datetimepicker({
        format:	'd/m/Y',
        timepicker:false,
        step: 1
    });

    $("[name='" + elementName + "']").attr("placeholder", "dd/mm/aaaa");
    return false;
}
