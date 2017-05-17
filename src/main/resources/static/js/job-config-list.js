/**
 * Created by liyue on 2016/10/21.
 */
$(document).ready(function(){

    var currentRowIndex = -1;
    function getCurrentDataId() {
        var domTr = $(".data-table tbody").children().eq(currentRowIndex);
        return domTr.attr("data-id");
    }

    function attributeName2PropertyName(attributeName) {
        var wordArray = attributeName.split("-");
        $.each(wordArray,function (index, word) {
           if(index > 0){
               wordArray[index] = word.substring(0,1).toUpperCase() +  word.substring(1,word.length);
           }
        });
        return wordArray.join("");
    }

    function getDataFromCurrentRow() {
        var data = {};
        if(currentRowIndex < 0 ) return data;
        var domTbody = $(".data-table tbody");
        var domTr = domTbody.children().eq(currentRowIndex);
        domTr.each(function () {
            $.each(this.attributes,function () {
                if(this.specified && this.name.match("^data-")){
                    // var propertyName = attrbuteName2PropertyName(this.name.substring(5,this.name.length));
                    var propertyName = this.name;
                    var propertyValue = this.value;
                    data[propertyName] = propertyValue;
                }
            });
        });

        return data;
    }

    function setDataToCurrentRow(data) {
        var domTbody = $(".data-table tbody");
        var domTr = domTbody.children().eq(currentRowIndex);
        $.each(data,function (name, value) {
            domTr.attr(name,value);
        })
    }

    function setDataRowToModal() {
        if(currentRowIndex < 0 ) return;
        var data = getDataFromCurrentRow();
        var dataEditFormDom = $(".data-edit-form");
        $.each(data,function (name, value) {
            var field = dataEditFormDom.find("[name='" + name + "']");
            field.each(function () {
               $(this).val(value);
            });
        });

    }
    
    function removeRowFromTable() {
        var domTbody = $(".data-table tbody");
        if (currentRowIndex >= 0) {
            var domTr = domTbody.children().eq(currentRowIndex);
            if (domTbody.children().length > 1) {
                domTr.one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function () {
                    domTr.nextUntil().each(function () {
                        $(this).one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function () {
                            $(this).removeClass('animated slideInUp');
                        });
                        $(this).addClass('animated slideInUp');
                    });
                    $(this).removeClass('animated fadeOutLeft');

                    $(this).remove();
                });

                domTr.addClass('animated fadeOutLeft');
            }
        }
    }

    function deleteRow(id) {
        LoadingMask.show();
        $.ajax({
            type: "POST",
            url: contextPath + 'job-config/delete/' + id,
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        })
            .done(function (data) {
                var json = data;
                if (json.success === true) {
                    showAlert('', "删除成功！", 'success');
                    removeRowFromTable();
                } else {

                    showAlert('', json.message === undefined ? '删除失败！' : json.message, 'danger');
                }
            })

            .fail(function () {

                showAlert('', '删除失败！', 'danger');
            })
            .always(function () {
                LoadingMask.hide();
            });
    }

    function getDataFromModal() {
        var array = $(".data-edit-form").serializeArray();
        var json = {};

        $.each(array, function() {
            json[this.name] = this.value || '';
        });
        json["data-parser-config-name"] = $("#data-parser-id-select").find("option:selected").text();
        return json;
    }


    function updateCurrentRowView() {

        var domTbody = $(".data-table tbody");
        var domTr = domTbody.children().eq(currentRowIndex);
        domTr.children().eq(0).text(domTr.attr("data-job-label"));
        domTr.children().eq(1).text(domTr.attr("data-job-type"));
        domTr.children().eq(2).text(domTr.attr("data-parser-config-name"));
        domTr.children().eq(3).text(domTr.attr("data-remark"));
    }

    function updateData(data) {
        LoadingMask.show();
        var ajaxData = {};
        $.each(data,function (name,value) {
            ajaxData[attributeName2PropertyName(name.substring(5,name.length))] = value;
        });
        $.ajax({
            type: "POST",
            url: contextPath + 'job-config/update',
            data:JSON.stringify(ajaxData),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        })
            .done(function (result) {
                var json = result;
                if (json.success === true) {
                    $("#dataEditModal").modal('hide');
                    showAlert('', "保存成功！", 'success');
                    setDataToCurrentRow(data);
                    updateCurrentRowView();
                } else {

                    showAlert('', json.message === undefined ? '保存失败！' : json.message, 'danger');
                }
            })

            .fail(function () {

                showAlert('', '保存失败！', 'danger');
            })
            .always(function () {
                LoadingMask.hide();
            });
    }

    function addNewItemToTable(data) {
        var domTbody = $(".data-table tbody");

        var domTr=$("<tr/>",data);
        domTr.addClass("data-row");
        domTr.append("<td>" + data["data-job-label"] + "</td>");
        domTr.append("<td>" + data["data-job-type"] + "</td>");
        domTr.append("<td>" + data["data-parser-id"] + "</td>");
        domTr.append("<td>" + data["data-remark"] + "</td>");
        domTr.append('<td >' +
            '<div class="btn-group"><a class="btn btn-sm btn-default action-edit-row" href="javascript:void(0);">编辑</a>' +
            '<a class="btn btn-sm btn-danger action-delete-row" href="javascript:void(0)">删除</a>' +
            '</div></td>');


        domTbody.append(domTr);
        domTr.one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function () {
            $(this).removeClass('animated fadeInDown');
        });
        domTr.addClass('animated fadeInDown');
    }

    function createData(data) {
        LoadingMask.show();
        var ajaxData = {};
        $.each(data,function (name,value) {
            ajaxData[attributeName2PropertyName(name.substring(5,name.length))] = value;
        });
        $.ajax({
            type: "POST",
            url: contextPath + 'job-config/create',
            data:JSON.stringify(ajaxData),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        })
            .done(function (result) {
                var json = result;
                if (json.success === true) {
                    data["data-id"] = json.id;
                    $("#dataEditModal").modal('hide');
                    showAlert('', "保存成功！", 'success');
                    addNewItemToTable(data)
                } else {

                    showAlert('', json.message === undefined ? '保存失败！' : json.message, 'danger');
                }
            })

            .fail(function () {

                showAlert('', '保存失败！', 'danger');
            })
            .always(function () {
                LoadingMask.hide();
            });

    }

    function saveData() {
        var data = getDataFromModal();
        var id = data["data-id"];
        if($.isNumeric(id) && Math.floor(id) == id  && id >= 0){
            return updateData(data);
        }else{
            return createData(data);
        }
    }

    function resetModalData() {
        $(".data-edit-form").find("input,textarea").each(function () {
                $(this).val('');
        });

        $("#data-job-type-select").children().eq(0).each(function () {
            $(this).prop("selected",true);
        });

        $("#data-parser-id-select").children().eq(0).each(function () {
            $(this).prop("selected",true);
        });

    }


    //events handler starts here

    $("#btnDeleteConfirm").on("click",function(){
        if(currentRowIndex < 0) return ;
        var id = getCurrentDataId();
        deleteRow(id);
    });

    $(".data-table tbody").on("click",".action-delete-row",function(){
        currentRowIndex = $(".data-row").index($(this).parents("tr"));
        $("#confirmDeleteModal").modal('show');
    }).on("click",".action-edit-row",function () {

        currentRowIndex = $(".data-row").index($(this).parents("tr"));
        $("#dataEditModalLabel").text("编辑");
        resetModalData();
        setDataRowToModal();

        
        $("#dataEditModal").modal('show');
    });

    // $(".action-data-edit-ok").on("click",function () {
    //     saveData();
    //     // if(result){
    //     //     $("#dataEditModal").modal('hide');
    //     // }
    // });
    $(".data-edit-form").on("submit",function () {
        event.preventDefault();
        saveData();
    });

    $(".action-create-row").on("click",function () {
        currentRowIndex = -1;
        $("#dataEditModalLabel").text("新建");
        resetModalData();
        $("#dataEditModal").modal('show');

    });
});