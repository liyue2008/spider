/**
 * Created by liyue on 2016/10/21.
 */
$(document).ready(function(){
    onItemRowChanged($(".table-item tbody"));
    onItemRowChanged($(".table-url-item tbody"));
    var currentRowIndex = -1;
    var currentTable=undefined;
    $(".action-new-item").on("click",function () {
        $("#itemEditModalLabel").text("新建解析器项目");
        currentRowIndex = -1;
        setModalItemType(1);

        resetItemFromValues();
        $("#subParserConfigSelect").prop("disabled",false);

        $("#itemEditModal").modal('show');
    });

    $(".action-new-url-item").on("click",function () {
        $("#itemEditModalLabel").text("新建递归URL解析项");
        currentRowIndex = -1;
        setModalItemType(2);

        resetItemFromValues();
        $("#subParserConfigSelect").prop("disabled",true);

        $("#itemEditModal").modal('show');
    });

    // public final static int ITEM_TYPE_DOM = 1;
    // public final static int ITEM_TYPE_URL = 2;
    // public final static int ITEM_TYPE_PAGE_NUM = 3;
    // $(".action-item-edit-OK").on("click",function () {
    //     var itemType = $("#itemTypeInput").val();
    //     var domTbody = (itemType === "1")?$(".table-item tbody"):$(".table-url-item tbody");
    //     if(currentRowIndex < 0){
    //         addNewItemToTable(domTbody);
    //
    //     }else{
    //         setItemValueToRow(domTbody);
    //     }
    //     onItemRowChanged(domTbody);
    // });
    $('input[name="radio-selector-source-page"]').on('change',function () {



        var useUrl = ($(this).val() === "url");
        var selectorInputDom = $('#pageSelectorInput');

        if(useUrl){
            if(!selectorInputDom.prop('disabled')) selectorInputDom.prop('disabled',true);
        }else{
            if(selectorInputDom.prop('disabled')) selectorInputDom.prop('disabled',false);


        }

    });

    $(".action-page-item-edit-OK").on("click",function () {
       setPageItemToInput();
    });

    $(".action-edit-page-item").on("click",function () {
        setPageItemToModal();
        $("#pageItemEditModal").modal('show');
    });

    $(".table-item,.table-url-item tbody").on("click",".action-row-move-up",function () {
        moveRowUp($(this).parents("tr"));

    }).on("click",".action-row-move-down",function () {
        moveRowDown($(this).parents("tr"));

    }).on("click",".action-row-delete",function () {
        currentTable = $(this).parents("tbody");
        currentRowIndex = currentTable.children(".item-row").index($(this).parents("tr"));
        $("#confirmDeleteModal").modal('show');

    }).on("click",".action-row-edit",function () {

        currentTable = $(this).parents("tbody");
        currentRowIndex = currentTable.children(".item-row").index($(this).parents("tr"));
        if($(this).parents("table").hasClass("table-item")){
            setModalItemType(1);
            $("#itemEditModalLabel").text("编辑解析器项目");
            $("#subParserConfigSelect").prop("disabled",false);

        }else{
            setModalItemType(2);

            $("#itemEditModalLabel").text("编辑递归URL解析项");
            $("#subParserConfigSelect").prop("disabled",true);

        }

        setItemValueToModal($(this).parents("tbody"));
        $("#itemEditModal").modal('show');
    });


    $(".action-delete").on("click",function () {
        deleteRow(currentTable);
    });

    $("#parser-config-form").on("submit",function () {
        saveParserConfig();
        event.preventDefault();
    });



    $("#enablePageItemCheck").on('change',function () {
        setPageItemButtonStat(this.checked);
    });

    function setAttributeInputStatus(value) {
        var attributeInputDom = $("#attributeInput");
        if (value === '_CUSTOM') {
            if (attributeInputDom.prop("disabled")) attributeInputDom.prop("disabled",false);
        } else {
            if (!attributeInputDom.prop("disabled")) attributeInputDom.prop("disabled",true);
        }
    }

    $("#attributeSelect").on('change',function () {
        setAttributeInputStatus($(this).val());
    });

    function setIndexInputStatus(value) {
        var separatorInput = $("#separatorInput");
        if (value === '-3') {
            if (separatorInput.prop("disabled")) separatorInput.prop("disabled",false);
        } else {
            if (!separatorInput.prop("disabled")) separatorInput.prop("disabled",true);
        }
    }

    $("#domIndexSelect").on('change',function () {
        setIndexInputStatus($(this).val());
    });

    function setPageItemButtonStat(enabled) {
        var buttonEdit=$(".action-edit-page-item");
        var buttonTest=$(".action-test-page-selector");
        if(enabled){
            if(buttonEdit.hasClass("disabled")) buttonEdit.removeClass("disabled");
            if(serial >= 0 && buttonTest.hasClass("disabled")) buttonTest.removeClass("disabled");

        }else{
            if(!buttonEdit.hasClass("disabled")) buttonEdit.addClass("disabled");
            if(!buttonTest.hasClass("disabled")) buttonTest.addClass("disabled");

        }
    }

    function setModalItemType(inputType) {
        $("#itemTypeInput").val(inputType);

    }

    function saveParserConfig() {
        var itemValues = getItemValues($(".table-item tbody"),1);
        var urlItemValues = getItemValues($(".table-url-item tbody"),2);
        var parserConfig = convertFormToJSON($("#parser-config-form"));
        parserConfig["itemList"] = itemValues;
        parserConfig["moreUrlItemList"] = urlItemValues;
        if($("#enablePageItemCheck").is(":checked")){
            var pageConfigItem = getPageConfigItem();
            parserConfig["pageNumItem"] = pageConfigItem;

        }
        // console.log(parserConfig);
        LoadingMask.show();
        $.ajax({
            type:'POST',
            url: contextPath + 'parser-config/save',
            data:JSON.stringify(parserConfig),
            contentType:"application/json; charset=utf-8",
            dataType: "json"
        })
            .done(function (data) {
                var json = data;
                if(json.success === true){

                    var parserConfigId = json.id;
                    $("#id").val(parserConfigId);
                    $(".item-row").each(function () {
                        $(this).attr("parser-config-id",parserConfigId);

                    });
                    showAlert('',"保存成功！",'success');
                }else{

                    showAlert('',json.message===undefined?'保存失败！':json.message,'danger');
                }
            })

            .fail(function () {

                showAlert('','保存失败！','danger');
            })
            .always(function(){
                LoadingMask.hide();
            });
    }

    function deleteRow(domTbody) {

        if (currentRowIndex >= 0) {
            var domTr = domTbody.children().eq(currentRowIndex);
            domTr.one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function () {
                domTr.nextUntil().each(function () {
                    $(this).one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function () {
                        $(this).removeClass('animated slideInUp');
                    });
                    $(this).addClass('animated slideInUp');
                });
                $(this).removeClass('animated fadeOutLeft');

                $(this).remove();
                onItemRowChanged(domTbody);
            });

            domTr.addClass('animated fadeOutLeft');
        } else {
            showAlert('', '删除失败！', 'danger');

        }
    }


    function moveRowUp(domTr) {
        var before = domTr.prev();
        domTr.one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function () {
            $(this).removeClass('animated slideOutUp');
            domTr.insertBefore(before);
            onItemRowChanged(domTr.parents("tbody"));

        });
        domTr.addClass('animated slideOutUp');

        before.addClass('animated slideOutDown');
        before.one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function () {
            $(this).removeClass('animated slideOutDown');
        });
    }


    function moveRowDown(domTr) {
        var after = domTr.next();
        domTr.one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function () {
            $(this).removeClass('animated slideOutDown');
            domTr.insertAfter(after);
            onItemRowChanged(domTr.parents("tbody"));

        });
        domTr.addClass('animated slideOutDown');

        after.addClass('animated slideOutUp');
        after.one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function () {
            $(this).removeClass('animated slideOutUp');
        });
    }

    function onItemRowChanged(domTbody) {

        //var domTbody = $(".table-item tbody");


        // if(domTbody.children().length === 1){
        //
        //     domTbody.find(".action-row-delete").addClass("disabled");
        // }else{
        //     domTbody.find(".action-row-delete").removeClass("disabled");
        // }

        domTbody.children().each(function (index) {
            if(index === 0 ){
                $(this).find('.action-row-move-up').addClass('disabled');
            }else{
                $(this).find('.action-row-move-up').removeClass('disabled');

            }

            if(index === domTbody.children().length - 1){
                $(this).find('.action-row-move-down').addClass('disabled');
            }else{
                $(this).find('.action-row-move-down').removeClass('disabled');

            }
        });

        // $(".exam-form").validator('update');

    }

    function addNewItemToTable(domTbody) {
        // var domTbody = $(".table-item tbody");

        var item = getItemValueFromModal();
        var domTr=$("<tr/>",{
            "parser-config-id":$("#id").val(),
            "class": "item-row",
            "column-title": item.columnTitle,
            "selector" : item.selector,
            "dom-index" : item.domIndex,
            "separator" : item.separator,
            "attribute" : item.attribute,
            "pattern" : item.pattern,
            "pattern-index" : item.patternIndex,
            "sub-config-id" : item.subConfigId,
            "remark" : item.remark
        });
        domTr.append("<td>" + item.columnTitle + "</td>");
        domTr.append("<td>" + item.selector + "</td>");
        domTr.append("<td>" + item.remark + "</td>");
        domTr.append('<td >' +
            '<div class="btn-group"><a class="btn btn-sm btn-default action-row-edit" href="javascript:void(0)"><span class="glyphicon glyphicon-edit"></span></a>' +
            '<a class="btn btn-sm btn-danger action-row-delete" href="javascript:void(0)"><span class="glyphicon glyphicon-trash"></span></a>' +
            '<a class="btn btn-sm btn-default action-row-move-up" href="javascript:void(0)"><span class="glyphicon glyphicon-arrow-up"></span></a>' +
            '<a class="btn btn-sm btn-default action-row-move-down" href="javascript:void(0)"><span class="glyphicon glyphicon-arrow-down"></span></a>' +
            '</div></td>');


        domTbody.append(domTr);
        domTr.one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function () {
            $(this).removeClass('animated fadeInDown');
        });
        domTr.addClass('animated fadeInDown');
    }

    function setItemValueToRow(domTbody) {
        var item = getItemValueFromModal();
        var domTr = domTbody.find(".item-row").eq(currentRowIndex);
        domTr.attr({
            "column-title": item.columnTitle,
            "selector" : item.selector,
            "dom-index" : item.domIndex,
            "separator" : item.separator,
            "attribute" : item.attribute,
            "pattern" : item.pattern,
            "pattern-index" : item.patternIndex,
            "sub-config-id" : item.subConfigId,
            "remark" : item.remark

        });
        domTr.children().eq(0).text(item.columnTitle);
        domTr.children().eq(1).text(item.selector);
        domTr.children().eq(2).text(item.remark);
    }

    
    function resetItemFromValues() {
        $("#columnTitleInput").val("");
        $("#selectorInput").val("");
        $("#domIndexSelect").val(-1);
        setIndexInputStatus(-1);
        $("#separatorInput").val("");
        $("#attributeSelect").val(($("#itemTypeInput").val()==="1")?"_TEXT":"_HREF");
        $("#attributeInput").val("");
        setAttributeInputStatus($("#attributeSelect").val());
        $("#patternInput").val("");
        $("#patternIndexInput").val("0");
        $("#remarkTextarea").val("");
        var subConfigSelectDom = $("#subParserConfigSelect");
        subConfigSelectDom.val("");
        // if(!subConfigSelectDom.prop("disabled"))subConfigSelectDom.prop("disabled",true);

    }

    function setItemValueToModal(domTbody) {

        var domTr = domTbody.find(".item-row").eq(currentRowIndex);

        $("#columnTitleInput").val(domTr.attr("column-title"));
        $("#selectorInput").val(domTr.attr("selector"));
        $("#domIndexSelect").val(domTr.attr("dom-index"));
        setIndexInputStatus(domTr.attr("dom-index"));
        $("#separatorInput").val(domTr.attr("separator"));
        var attributeSelectDom = $("#attributeSelect");
        attributeSelectDom.val("_CUSTOM");
        $("#attributeInput").val(domTr.attr("attribute"));
        $("#attributeSelect option").each(function () {
           if($(this).val() === domTr.attr("attribute")){
               attributeSelectDom.val(domTr.attr("attribute"));
               $("#attributeInput").val("");
                return false;
           }
        });
        setAttributeInputStatus($("#attributeSelect").val());

        $("#patternInput").val(domTr.attr("pattern"));
        $("#patternIndexInput").val(domTr.attr("pattern-index"));
        $("#remarkTextarea").val(domTr.attr("remark"));
        var subConfigSelectDom = $("#subParserConfigSelect");
        subConfigSelectDom.val(domTr.attr("sub-config-id"));


        // if(subConfigSelectDom.prop("disabled"))subConfigSelectDom.prop("disabled",false);

    }

    function setPageItemToModal() {
        var inputDom = $('#pageItemInput');
        var useUrl = (inputDom.attr("d-selector") === "_PAGE_NUM_URL");
        $('input[name="radio-selector-source-page"][value=' + (useUrl?'url':'page') + ']').prop('checked',true);
        var selectorInputDom = $('#pageSelectorInput');

        if(useUrl){
            if(!selectorInputDom.prop('disabled')) selectorInputDom.prop('disabled',true);
            selectorInputDom.val('');
        }else{
            if(selectorInputDom.prop('disabled')) selectorInputDom.prop('disabled',false);
            selectorInputDom.val(inputDom.attr('d-selector'));

        }

        $('#pagePatternInput').val(inputDom.attr('d-pattern'));
        $('#pagePatternIndexInput').val(inputDom.attr('d-pattern-index'));

    }

    function setPageItemToInput() {
        var inputDom = $('#pageItemInput');
        var selectorInputDom = $('#pageSelectorInput');

        if($('input[name="radio-selector-source-page"]:checked').val() === 'url'){
            inputDom.attr('d-selector','_PAGE_NUM_URL');
        }else{
            inputDom.attr('d-selector',selectorInputDom.val());
        }
        inputDom.attr('d-pattern',$('#pagePatternInput').val());
        inputDom.attr('d-pattern-index',$('#pagePatternIndexInput').val());

    }

    function getItemValueFromModal() {
        var attrSelectVal = $("#attributeSelect").val();
        var attrInputVal = $("#attributeInput").val();
        var attribute;
        if("_CUSTOM" === attrSelectVal){
            attribute = attrInputVal;
        }else{
            attribute = attrSelectVal;
        }
        return {
            columnTitle : $("#columnTitleInput").val(),
            selector : $("#selectorInput").val(),
            domIndex : $("#domIndexSelect").val(),
            separator: $("#separatorInput").val(),
            attribute : attribute,
            pattern : $("#patternInput").val(),
            patternIndex : $("#patternIndexInput").val(),
            subConfigId : $("#subParserConfigSelect").val(),
            remark : $("#remarkTextarea").val()
        };


    }



    function convertFormToJSON(form){
        var array = form.serializeArray();
        var json = {};

        $.each(array, function() {
            json[this.name] = this.value || '';
        });

        return json;
    }

    setPageItemButtonStat($("#enablePageItemCheck").is(":checked"));

    $('#itemEditForm').on('submit', function () {
        var itemType = $("#itemTypeInput").val();
        var domTbody = (itemType === "1")?$(".table-item tbody"):$(".table-url-item tbody");
        if(currentRowIndex < 0){
            addNewItemToTable(domTbody);

        }else{
            setItemValueToRow(domTbody);
        }
        onItemRowChanged(domTbody);
        $("#itemEditModal").modal('hide');
        event.preventDefault();
    });
});