jQuery(function($){
    //Settings
    $(":text[value='']:first").focus();
    $("a[href='#']").on('click', function(e){ e.preventDefault(); });
    //Settings

    //Link ativo
    $('.navbar-nav a[href*="' + location.pathname.split("/")[2] + '"]').parent().addClass('active');

    //Tooltips
    $(document).tooltip({ selector: "a[title]", container: "body", placement: "left" });
    //Tooltips

    //Hide success messages when needed
    if ($(".page-alert").is(":visible")) {
        window.setTimeout(function(){ $(".page-alert").hide(); }, 5000);
    }
    //Hide success messages when needed

    //Input masks
    $("input[alt='cpf']:visible").inputmask({
        mask: "999.999.999-99"
    });

    $("input[alt='cnpj']").inputmask({
        mask: "99.999.999/9999-99"
    });

    $("input[alt='placa']").inputmask({
        mask: "aaa-9999"
    });

    $("input[alt='renavam']").inputmask({
        mask: "999999999?99"
    });

    $("input[alt='chassi']").inputmask({
        mask: "wwwwwwwwwwwwww?www"
    });

    $("input[alt='odometro']").inputmask({
        mask: "9?9999999"
    });

    $("input[alt='fone']").inputmask({
        mask: "(99) 9999-9999"
    });

    $("input[alt='ano']").inputmask({
        mask: "9999"
    });

    $("input[alt='data']").inputmask({
        mask: "99/99/9999"
    });

    $.mask.masks = $.extend($.mask.masks, {
        valor: { mask: "99,999.999.999", type: "reverse", defaultValue: "000" },
        contrato: { mask: '9999/99999', type: "reverse" }
    });
    $("input[alt='valor']").setMask();
    $("input[alt='contrato']").setMask();
    //Input masks

    //Modal
    var tempButton;

    $(document.body).on("click", "[data-toggle='modal']", function (e) {
        var remote = $(this).attr("href");
        var action = $(this).data("action");
        var controller = $(this).data("controller");
        var saveButton = $(".modal-footer button[id]");

        //Configurar o header
        $("#modalWindowLabel").html($(this).data("header"));

        //Configurar o body e o footer
        $("#modalWindow .modal-body").load(remote, function(r, s, x) {
            if (s === "error") {
                $("#modalWindow .modal-body").html("<h2>"+ x.status +"</h2><p>Ocorreu um erro: "+ x.statusText +"</p>");
            } else {
                var mform = $(r).filter(function() { return $(this).is("form"); }).html();

                if (typeof mform === "undefined") {
                    if (typeof tempButton === "undefined")
                    {
                        tempButton = saveButton.detach();
                    }
                } else {
                    if (tempButton)
                    {
                        tempButton.appendTo(".modal-footer");
                        tempButton = undefined;
                    }

                    //Masks
                    $("input[alt='uf']").inputmask({
                        mask: "aa"
                    });

                    $("input[alt='cep']").inputmask({
                        mask: "99999-999"
                    });

                    $("input[alt='numero']").inputmask({
                        mask: "9?99999999"
                    });

                    $("input[alt='cpf']").inputmask({
                        mask: "999.999.999-99"
                    });

                    $("input[alt='cnpj']").inputmask({
                        mask: "99.999.999/9999-99"
                    });

                    $("input[alt='fone']").inputmask({
                        mask: "(99) 9999-9999"
                    });

                    $("input[alt='data']").inputmask({
                        mask: "99/99/9999"
                    });

                    $("input[alt='contrato']").setMask();
                    //Masks

                    $("#modalWindow .modal-footer").find("button:last").data({
                        action: action,
                        controller: controller
                    });
                }
            }
        });

        //Exibir janela modal
        $("#modalWindow").modal({ backdrop: "static" });

        //Não acessar a URL do link
        e.preventDefault();
    });
    //Modal

    //ALL//
    $("#modalWindow").on("click", "#saveThis", function() {
        //Modal form
        var mForm = $(this).parent().prev().find("form");

        //Controller + action
        var controller = $(this).data("controller");
        var action     = $(this).data("action");

        //URL
        var fullurl = ROOTURL +"/"+ controller +"/"+ action;
        var refresh = ROOTURL +"/"+ controller +"/refresh";

        if (action === "addFile") {
            var fd = new FormData();
            fd.append("veiculoArquivo.veiculo.id", $("input:hidden").val());
            fd.append("arquivo", $("#arquivo")[0].files[0]);

            $.ajax({
                url: fullurl,
                type: 'POST',
                data: fd,
                async: false,
                cache: false,
                contentType: false,
                processData: false,
                success: function(response) {
                    if (response === "OK") {
                        alert("Arquivo adicionado com sucesso!");

                        //Fechar modal
                        $("#modalWindow").modal("hide");
                    } else {
                        var errors = new Array();
                        $.each(response, function(k, i) {
                            errors.push(i.message);
                        });

                        alert(errors.join("\n"));
                    }
                }
            });
        } else {
            $.ajax({
                type: "POST",
                url: fullurl,
                data: mForm.serialize(),
                dataType: "json",
                success: function(response) {
                    if (response === "OK") {
                        $.get(refresh, function(r, s) {
                            if (s === "error") {
                                alert("Ocorreu um erro ao carregar a lista.");
                            } else {
                                //Fechar modal
                                $("#modalWindow").modal("hide");

                                //Atualizar conteúdo
                                ($(".table").filter(":visible").length > 0) ? $(".table").replaceWith(r)
                                                                            : $(".panel-body").replaceWith(r);
                            }
                        });
                    } else {
                        var errors = new Array();
                        $.each(response, function(k, i) {
                            errors.push(i.message);
                        });

                        alert(errors.join("\n"));
                    }
                }
            });
        }
    });
    //ALL//

    //Contrato//
        $(document).on("click", "[data-action='addStandard']", function () {
            //Controller + action
            var controller = $(this).data("controller");
            var action     = $(this).data("action");

            //URL
            var fullurl = ROOTURL +"/"+ controller +"/"+ action;
            var refresh = ROOTURL +"/"+ controller +"/refresh";

            //Serialization
            var values  = $("#standardForm").serialize();
                values += "&contratoPadrao.padrao.nome="+ $("#padrao option:selected").text();

            $.ajax({
                type: "POST",
                url: fullurl,
                data: values,
                async: false,
                dataType: "json",
                success: function(response) {
                    if (response.toString() === "OK") {
                        $.get(refresh, function(r, s) {
                            if (s === "error") {
                                alert("Ocorreu um erro ao carregar a lista.");
                            } else {
                                $(".table").filter(":visible").length > 0 ? $(".table").replaceWith(r) :
                                                                            $(".panel-footer").replaceWith(r);
                            }
                        });

                        //Reset some fields
                        $("#valor").val("0,00");
                        $("#valorDiaria").val("0,00");
                        $("#valorComCondutor").val("0,00");
                        $("#padrao").val($("option:first", $("#padrao")).val());

                        //Se necessário, ativar botão de salvar o contrato
                        if ($("#clientes").is(":not(:disabled)") && $("[data-action='saveContract']").is(":disabled"))
                        {
                            $("[data-action='saveContract']").removeAttr("disabled");
                        }
                    } else {
                        var errors = new Array();
                        $.each(response, function(k, i) { errors.push(i.message); });

                        alert(errors.join("\n"));
                    }
                }
            });
        });

        $(document).on("click", "[data-action='addStandardToTheContract']", function () {
            alert('Adicionando ao contrato um novo standard...');
        });

        $(document).on("click", "[data-action='removeStandard']", function() {
            //Padrão que será removido
            var item  = $(this).parents("tr");
            var index = item.index();
            var lista = item.siblings().length + 1;

            if (index >= 0) {
                //Controller + action
                var controller = $(this).data("controller");
                var action     = $(this).data("action");

                //URL
                var fullurl = ROOTURL +"/"+ controller +"/"+ action;
                var refresh = ROOTURL +"/"+ controller +"/refresh";

                $.ajax({
                    type: "POST",
                    url: fullurl,
                    data: { index : index },
                    async: false,
                    dataType: "html",
                    success: function(response) {
                        if (response.toString() === "OK") {
                            $.get(refresh, function(r, s) {
                                if (s === "error") {
                                    alert("Ocorreu um erro ao carregar a lista.");
                                } else {
                                    $(".table").filter(":visible").length > 0 ? $(".table").replaceWith(r) :
                                                                                $(".panel-footer").replaceWith(r);
                                }
                            });

                            //Se necessário, desativar botão de salvar o contrato
                            if (lista === 1) {
                                $("[data-action='saveContract']").attr("disabled", "disabled");
                            }
                        } else {
                            alert("Não foi possível remover este padrão da lista.");
                        }
                    }
                });
            }
        });

        $(document).on("click", "[data-action='removeStandardFromContract']", function() {
            alert('Removendo um standard do contrato...');
        });

        $(document).on("click", "[data-action='saveContract']", function() {
            //Controller + action
            var controller = $(this).data("controller");
            var action = $(this).data("action");

            //URL
            var fullurl = ROOTURL + "/" + controller + "/" + action;
            var refresh = ROOTURL + "/" + controller + "/refresh";

            //Serialization
            var values  = $("#contractForm").serialize();

            $.ajax({
                type: "POST",
                url: fullurl,
                data: values,
                async: false,
                dataType: "json",
                success: function(response) {
                    if (response.toString() === "OK") {
                        $.get(refresh, function(r, s) {
                            if (s === "error") {
                                alert("Ocorreu um erro ao carregar a lista.");
                            } else {
                                $(".table").filter(":visible").length > 0 ? $(".table").replaceWith(r) :
                                                                            $(".panel-footer").replaceWith(r);
                            }
                        });

                        //contractForm
                        $("#contrato").val("");
                        $("#dataFinal").val("");
                        $("#observacao").val("");
                        $("#dataInicial").val("");
                        $("#interveniente").val("");
                        $("#clientes").val($("option:first", $("#clientes")).val());

                        //standardForm
                        $("#valor").val("0,00");
                        $("#valorDiaria").val("0,00");
                        $("#valorComCondutor").val("0,00");
                        $("#padrao").val($("option:first", $("#padrao")).val());

                        //Desativar botão de salvar contrato
                        $("[data-action='saveContract']").attr("disabled", "disabled");
                    } else {
                        var errors = new Array();
                        $.each(response, function(k, i) { errors.push(i.message); });

                        alert(errors.join("\n"));
                    }
                }
            });
        });

        $(document).on("click", "[data-action='saveEditedContract']", function() {
            alert('Salvando os dados que foram alterados no contrato...');
        });
    //Contrato//

    //Veículo//
    $(document).on("click", "[data-action='addVehicle']", function() {
        //Controller + action
        var controller = $(this).data("controller");
        var action     = $(this).data("action");

        //URL
        var fullurl = ROOTURL +"/"+ controller +"/"+ action;
        var refresh = ROOTURL +"/"+ controller +"/refresh";

        //Serialization
        var values  = $("form").serialize();
            values += "&veiculo.cor.nome="+ $("#cor option:selected").text();
            values += "&veiculo.modelo.nome="+ $("#modelo option:selected").text();

        $.ajax({
            type: "POST",
            url: fullurl,
            data: values,
            async: false,
            dataType: "json",
            success: function(response) {
                if (response.toString() === "OK") {
                    $.get(refresh, function(r, s) {
                        if (s === "error") {
                            alert("Ocorreu um erro ao carregar a lista.");
                        } else {
                            $(".table").filter(":visible").length > 0 ? $(".table").replaceWith(r) :
                                                                        $(".panel-footer").replaceWith(r);
                        }
                    });

                    //Reset some fields
                    $("#chassi").val("");
                    $("#renavam").val("");
                    $("#odometro").val("");
                    $("#anoModelo").val("");
                    $("#anoFabricacao").val("");
                    $("#placa").val("").focus();

                    //Ativar botão de salvar veículos se for necessário
                    if ($("[data-action='saveAll']").is(":disabled")) {
                        $("[data-action='saveAll']").removeAttr("disabled");
                    }
                } else {
                    var errors = new Array();
                    $.each(response, function(k, i) { errors.push(i.message); });

                    alert(errors.join("\n"));
                }
            }
        });
    });

    $(document).on("click", "[data-action='removeVehicle']", function() {
        //Veículo que será removido
        var item  = $(this).parents("tr");
        var index = item.index();
        var lista = item.siblings().length + 1;

        if (index >= 0) {
            //Controller + action
            var controller = $(this).data("controller");
            var action     = $(this).data("action");

            //URL
            var fullurl = ROOTURL +"/"+ controller +"/"+ action;
            var refresh = ROOTURL +"/"+ controller +"/refresh";

            $.ajax({
                type: "POST",
                url: fullurl,
                data: { index : index },
                async: false,
                dataType: "html",
                success: function(response) {
                    if (response.toString() === "OK") {
                        $.get(refresh, function(r, s) {
                            if (s === "error") {
                                alert("Ocorreu um erro ao carregar a lista.");
                            } else {
                                $(".table").filter(":visible").length > 0 ? $(".table").replaceWith(r) :
                                                                            $(".panel-footer").replaceWith(r);
                            }
                        });

                        //Desativar botão de salvar veículos se for necessário
                        if (lista === 1) {
                            $("[data-action='saveAll']").attr("disabled", "disabled");
                        }
                    } else {
                        alert("Não foi possível remover este veículo da lista.");
                    }
                }
            });
        }
    });

    $(document).on("click", "[data-action='deleteFile']", function() {
        //Remover tooltip
        $(this).tooltip("hide");

        var c = window.confirm("Deseja realmente remover este arquivo?");

        if (c) {
            //Arquivo que será removido
            var arquivo = $(this).parents("tr");
            var tabela = arquivo.parents("table");
            var lista = arquivo.siblings().length + 1;

            //Controller + action + id
            var controller = $(this).data("controller");
            var action     = $(this).data("action");
            var id         = $(this).data("fileId");

            //URL
            var fullurl = ROOTURL +"/"+ controller +"/"+ action;

            $.ajax({
                type: "POST",
                url: fullurl,
                data: { id : id },
                async: false,
                dataType: "json",
                success: function(response) {
                    if (response === "OK") {
                        //Remover arquivo da tabela
                        arquivo.remove();

                        //Remover a tabela se for necessário
                        if (lista === 1) {
                            tabela.remove();
                        }
                    } else {
                        var errors = new Array();
                        $.each(response, function(k, i) { errors.push(i.message); });

                        alert(errors.join("\n"));
                    }
                }
            });
        }
    });

    $(document).on("click", "[data-action='saveAll']", function() {
        //Controller + action
        var controller = $(this).data("controller");
        var action     = $(this).data("action");

        //URL
        var fullurl = ROOTURL +"/"+ controller +"/"+ action;
        var refresh = ROOTURL +"/"+ controller +"/refresh";

        $.ajax({
            type: "POST",
            url: fullurl,
            async: false,
            dataType: "json",
            success: function(response) {
                if (response.toString() === "OK") {
                    $.get(refresh, function(r, s) {
                        if (s === "error") {
                            alert("Ocorreu um erro ao carregar a lista.");
                        } else {
                            $(".table").filter(":visible").length > 0 ? $(".table").replaceWith(r) :
                                                                        $(".panel-footer").replaceWith(r);
                        }
                    });

                    //Reset fields
                    $("#chassi").val("");
                    $("#renavam").val("");
                    $("#odometro").val("");
                    $("#anoModelo").val("");
                    $("#anoFabricacao").val("");
                    $("#placa").val("").focus();
                    $("#cor").val($("option:first", $("#cor")).val());
                    $("#fuel").val($("option:first", $("#fuel")).val());
                    $("#modelo").val($("option:first", $("#modelo")).val());
                    $("#status").val($("option:first", $("#status")).val());
                    $("#restricao").val($("option:first", $("#restricao")).val());

                    //Desativar botão de salvar veículos
                    $("[data-action='saveAll']").attr("disabled", "disabled");
                } else {
                    var errors = new Array();
                    $.each(response, function(k, i) { errors.push(i.message); });

                    alert(errors.join("\n"));
                }
            }
        });
    });
    //Veículo//

    //Tipo de Pessoa
    $(document).on("change", "[name*='tipoPessoa']", function() {
        //Person type
        var type = $(this).val();

        //Sigla
        var sigla = $("#sigla");


        //Field e Label
        var doc = $("#documento");
        var nL  = $("label[for='nome']");
        var dL  = $("label[for='documento']");

        //Remove ALL
        doc.unbind().removeData("bs.inputmask");
        doc.val("").focus();

        if (type === 'PF') {
            dL.text("CPF");
            nL.text("Nome");

            //Desativar sigla
            sigla.attr("disabled", "disabled");

            //CPF mask
            doc.inputmask({ mask : "999.999.999-99" });
        } else {
            dL.text("CNPJ");
            nL.html("Raz&atilde;o Social");

            //Ativar sigla
            sigla.removeAttr("disabled");

            //CNPJ mask
            doc.inputmask({ mask: "99.999.999/9999-99" });
        }
    });
    //Tipo de Pessoa

    //DataTables//
    $("#datapaging").dataTable({
        "oLanguage": {
            "sInfo"        : "Mostrando de _START_ at&eacute; _END_ de _TOTAL_ registros",
            "sSearch"      : '<span class="input-group-addon"><span class="glyphicon glyphicon-search"></span></span>',
            //"sSearch"      : "Buscar:",
            "sInfoEmpty"   : "Mostrando de 0 at&eacute; 0 de 0 registros",
            "sProcessing"  : "Processando...",
            "sZeroRecords" : "Nenhum registro encontrado.",
            "sInfoPostFix" : "",
            "sInfoFiltered": "(filtrado de _MAX_ registros no total)",
            "oPaginate": {
                "sFirst":    "Primeiro",
                "sPrevious": "Anterior",
                "sNext":     "Seguinte",
                "sLast":     "&Uacute;ltimo"
            }
        },
        "aoColumnDefs": [{ bSortable: false, aTargets: [-1] }]
    });

    $("#datapaging_filter").addClass("input-group");
    $("#datapaging_filter input").unwrap();
    $("#datapaging_filter input").addClass("form-control input-sm");
    $("#datapaging_length label select").addClass("form-control input-sm");
    //DataTables//
});