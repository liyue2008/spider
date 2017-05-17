/**
 * Created by liyue on 2017/4/17.
 */
var serial = -1;
var listElements;

function getItemValues(tBodyDom,itemType) {
    // public final static int ITEM_TYPE_DOM = 1;
    // public final static int ITEM_TYPE_URL = 2;
    // public final static int ITEM_TYPE_PAGE_NUM = 3;
    var items = [];
    tBodyDom.find(".item-row").each(function (index) {
        items[index] = {
           id : $(this).attr("id"),
           parserConfigId : $(this).attr("parser-config-id"),
           columnTitle : $(this).attr("column-title"),
           selector : $(this).attr("selector"),
           domIndex : $(this).attr("dom-index"),
           separator: $(this).attr("separator"),
           attribute : $(this).attr("attribute"),
           pattern : $(this).attr("pattern"),
           patternIndex : $(this).attr("pattern-index"),
           subConfigId : $(this).attr("sub-config-id"),
           itemType: itemType,
           itemIndex: index,
           remark : $(this).attr("remark")
       };
    });

    return items;
}
function getPageConfigItem() {
    var pageItemInputDom = $('#pageItemInput');
    var pageItem = {
        id: pageItemInputDom.attr("d-id"),
        parserConfigId: pageItemInputDom.attr("d-parser-config-id"),
        columnTitle: 'page config item',
        selector: pageItemInputDom.attr('d-selector'),
        attribute: '_TEXT',
        domIndex: -1,
        pattern: pageItemInputDom.attr('d-pattern'),
        patternIndex: pageItemInputDom.attr('d-pattern-index'),
        itemType: 3,
        itemIndex: 0
    };
    return pageItem;
}
$(document).ready(function(){

    $(".action-test-list-selector").on("click",function(){
        testListSelector(serial,$(".list-selector").val(),$(".parser-type:checked").val());
    });

    $(".action-test-page-selector").on("click",function(){
        var pageItem = getPageConfigItem();
        testPageSelector(serial,$(".parser-type:checked").val(),pageItem);
    });

    $(".action-test-url").on("click",function(){
        testUrl($("#test-url-input").val(),$("#user-agent-input").val(),$("#referrer-input").val(),$("#timeout-input").val());
    });

    $(".list-selector-preview-index").on("click","li",function () {
        var index = $(".list-selector-preview-index li").index($(this));
        if(typeof index !== 'undefined') {
            onListPreviewIndexClicked(index);
        }

    });

    $(".action-preview-items").on("click",function () {
        var parserConfigItems = getItemValues($(".table-item tbody"),1);
        testParserConfigItems(serial,parserConfigItems);
    });

    $(".action-preview-url-items").on("click",function () {
        var parserConfigItems = getItemValues($(".table-url-item tbody"),2);
        testParserConfigUrlItems(serial,$(".parser-type:checked").val(),parserConfigItems);
    });

    function onListPreviewIndexClicked(clickIndex) {
        var liDoms = $(".list-selector-preview-index li");
        liDoms.each(function (index) {
            if(index === clickIndex){
                if(!($(this).hasClass("active"))){
                    $(this).addClass("active")
                }
            }else{
                if(($(this).hasClass("active"))){
                    $(this).removeClass("active")
                }
            }
        });
        var codeHtml ="";
        if(typeof listElements !== 'undefined' && typeof listElements[clickIndex] !== 'undefined'){
            codeHtml = listElements[clickIndex];

        }

        $(".list-selector-preview").text(codeHtml);
        hljs.initHighlighting.called = false;
        hljs.initHighlighting();
    }

    function setPreviewIndex() {
        var count = 0;
        var ulDom = $(".list-selector-preview-index");
        ulDom.empty();
        if(typeof listElements !== 'undefined'){
            count = listElements.length;
            $.each(listElements,function (index, value) {
                ulDom.append('<li><a href="javascript:void(0);">' + (index + 1 ) + '</a></li>');
            });

        }

        $(".list-element-count").text(count);
        onListPreviewIndexClicked(0);



    }

    function setPreviewTable(results, columnTitles) {
        $(".result-count").text(results.length);
        var tHeadTrDom = $(".table-preview thead tr");
        var tBodyDom = $(".table-preview tbody");
        tHeadTrDom.empty();
        tBodyDom.empty();

        $.each(columnTitles,function(index,value){
            tHeadTrDom.append("<th>" + value + "</th>");
        });

        $.each(results,function (i, row) {
            var domTr=$("<tr/>",{
                "class" : "preview-row"
            });
            $.each(row,function (j, cell) {
                domTr.append("<td>" + cell + "</td>");
            });
            tBodyDom.append(domTr);
        });

    }

    function setUrlPreviewList(urls) {
        var countSpanDom = $(".url-result-count");
        var urlResultListUlDom = $(".url-result-list");



        countSpanDom.text(urls.length);
        urlResultListUlDom.empty();
        $.each(urls,function (index, value) {
            urlResultListUlDom.append('<li class="list-group-item"><a href="' + value + '">' + value + '</a> </li>')
        })

    }

    function testParserConfigItems(serial,parserConfigItems) {
        var loadingDom = $(".test-parser-config-items-loading");
        var successDom = $(".test-parser-config-items-success");
        var failedDom = $(".test-parser-config-items-failed");
        if(!successDom.hasClass("hidden")) successDom.addClass("hidden");
        if(!failedDom.hasClass("hidden")) failedDom.addClass("hidden");
        if(loadingDom.hasClass("hidden")) loadingDom.removeClass("hidden");

        $.ajax({
            type:'POST',
            url: contextPath + 'preview/test-parser-config-items/' + serial,
            data:JSON.stringify(parserConfigItems),
            contentType:"application/json; charset=utf-8",
            dataType: "json"
        })
            .done(function (data) {
                var json = data;
                if(json.success === true){

                    serial = json.serial;
                    var results = json.results;
                    var columnTitles = json.columnTitles;
                    setPreviewTable(results,columnTitles);

                    if(successDom.hasClass("hidden")) successDom.removeClass("hidden");
                }else{
                    if(failedDom.hasClass("hidden")) failedDom.removeClass("hidden");

                    showAlert('',json.message,'danger');
                }
            })

            .fail(function () {
                if(failedDom.hasClass("hidden")) failedDom.removeClass("hidden");
                showAlert('','操作失败！','danger');
            })
            .always(function(){
                setPreviewIndex();
                if(!loadingDom.hasClass("hidden")) loadingDom.addClass("hidden");

            });
    }
    
    function testParserConfigUrlItems(serial,parserType,parserConfigUrlItems) {
        var loadingDom = $(".test-parser-config-url-items-loading");
        var successDom = $(".test-parser-config-url-items-success");
        var failedDom = $(".test-parser-config-url-items-failed");
        if(!successDom.hasClass("hidden")) successDom.addClass("hidden");
        if(!failedDom.hasClass("hidden")) failedDom.addClass("hidden");
        if(loadingDom.hasClass("hidden")) loadingDom.removeClass("hidden");

        $.ajax({
            type:'POST',
            url: contextPath + 'preview/test-parser-config-url-items/' + serial + '/' + parserType,
            data:JSON.stringify(parserConfigUrlItems),
            contentType:"application/json; charset=utf-8",
            dataType: "json"
        })
            .done(function (data) {
                var json = data;
                if(json.success === true){

                    serial = json.serial;
                    var urls = json.urls;
                    setUrlPreviewList(urls);

                    if(successDom.hasClass("hidden")) successDom.removeClass("hidden");
                }else{
                    if(failedDom.hasClass("hidden")) failedDom.removeClass("hidden");

                    showAlert('',json.message,'danger');
                }
            })

            .fail(function () {
                if(failedDom.hasClass("hidden")) failedDom.removeClass("hidden");

                showAlert('','操作失败！','danger');
            })
            .always(function(){
                setPreviewIndex();
                if(!loadingDom.hasClass("hidden")) loadingDom.addClass("hidden");

            });
    }
    
    function setPreviewItemsButtonStat(success) {
        var previewItemsButton = $('.action-preview-items');
        if(success){
            if(previewItemsButton.hasClass("disabled")) previewItemsButton.removeClass("disabled");

        }else{
            if(!previewItemsButton.hasClass("disabled")) previewItemsButton.addClass("disabled");

        }
    }

    function testListSelector(serial,selector,parserType) {
        var loadingDom = $(".test-list-selector-loading");
        var successDom = $(".test-list-selector-success");
        var failedDom = $(".test-list-selector-failed");
        if(!successDom.hasClass("hidden")) successDom.addClass("hidden");
        if(!failedDom.hasClass("hidden")) failedDom.addClass("hidden");
        if(loadingDom.hasClass("hidden")) loadingDom.removeClass("hidden");
        
        listElements = undefined;

        $.ajax({
            type:'POST',
            url: contextPath + 'preview/test-list-selector',
            data:{
                parserConfigType:parserType,
                serial:serial,
                listSelector:selector
            },
            dataType: "json"
        })
            .done(function (data) {
                var json = data;
                if(json.success === true){

                    serial = json.serial;
                    listElements = json.elements;
                    setPreviewItemsButtonStat(true);
                    if(successDom.hasClass("hidden")) successDom.removeClass("hidden");
                }else{
                    if(failedDom.hasClass("hidden")) failedDom.removeClass("hidden");
                    setPreviewItemsButtonStat(false);

                    showAlert('',json.message===undefined?'操作失败！':json.message,'danger');
                }
            })

            .fail(function () {
                if(failedDom.hasClass("hidden")) failedDom.removeClass("hidden");
                setPreviewItemsButtonStat(false);

                showAlert('','操作失败！','danger');
            })
            .always(function(){
                setPreviewIndex();
                if(!loadingDom.hasClass("hidden")) loadingDom.addClass("hidden");

            });
    }

    function testPageSelector(serial,parserType,pageItem) {
        var loadingDom = $(".test-page-selector-loading");
        var successDom = $(".test-page-selector-success");
        var failedDom = $(".test-page-selector-failed");
        if(!successDom.hasClass("hidden")) successDom.addClass("hidden");
        if(!failedDom.hasClass("hidden")) failedDom.addClass("hidden");
        if(loadingDom.hasClass("hidden")) loadingDom.removeClass("hidden");
        listElements = undefined;

        $.ajax({
            type:'POST',
            url: contextPath + 'preview/test-parser-config-page-item/' + serial + '/' + parserType,
            data:JSON.stringify(pageItem),
            contentType:"application/json; charset=utf-8",
            dataType: "json"
        })
            .done(function (data) {
                var json = data;
                if(json.success === true){

                     $(".test-page-selector-result").text(json.page);

                    if(successDom.hasClass("hidden")) successDom.removeClass("hidden");
                }else{
                    if(failedDom.hasClass("hidden")) failedDom.removeClass("hidden");

                    showAlert('',json.message,'danger');
                }
            })

            .fail(function () {
                if(failedDom.hasClass("hidden")) failedDom.removeClass("hidden");
                showAlert('',json.message===undefined?'操作失败！':json.message,'danger');

            })
            .always(function(){
                setPreviewIndex();
                if(!loadingDom.hasClass("hidden")) loadingDom.addClass("hidden");

            });
    }

    function setPreviewButtonStat(success) {
        var btnTestListSelectorDom = $(".action-test-list-selector");
        var btnTestPageSelectorDom = $(".action-test-page-selector");
        var btnPreviewUrlSelectorDom = $(".action-preview-url-items");
        var isPageItemEnabled = $("#enablePageItemCheck").is(":checked");
        if(success){
            if(btnTestListSelectorDom.hasClass("disabled")) btnTestListSelectorDom.removeClass("disabled");

            if(isPageItemEnabled && btnTestPageSelectorDom.hasClass("disabled")) btnTestPageSelectorDom.removeClass("disabled");
            if(btnPreviewUrlSelectorDom.hasClass("disabled")) btnPreviewUrlSelectorDom.removeClass("disabled");

        }else{
            if(!btnTestListSelectorDom.hasClass("disabled")) btnTestListSelectorDom.addClass("disabled");
            if(!btnTestPageSelectorDom.hasClass("disabled")) btnTestPageSelectorDom.addClass("disabled");
            if(!btnPreviewUrlSelectorDom.hasClass("disabled")) btnPreviewUrlSelectorDom.addClass("disabled");

        }
    }



    function testUrl(url,userAgent,referrer,timeout){
        var loadingDom = $(".test-url-loading");
        var successDom = $(".test-url-success");
        var failedDom = $(".test-url-failed");
        if(!successDom.hasClass("hidden")) successDom.addClass("hidden");
        if(!failedDom.hasClass("hidden")) failedDom.addClass("hidden");
        if(loadingDom.hasClass("hidden")) loadingDom.removeClass("hidden");


        $.ajax({
            type:'POST',
            url: contextPath + 'preview/test-url',
            data:{
                url:url,
                serial:serial,
                userAgent:userAgent,
                referrer:referrer,
                timeout:timeout
            },
            dataType: "json"
        })
            .done(function (data) {
                var json = data;
                if(json.success === true){

                    serial = json.serial;
                    if(successDom.hasClass("hidden")) successDom.removeClass("hidden");
                    setPreviewButtonStat(true);
                }else{
                    if(failedDom.hasClass("hidden")) failedDom.removeClass("hidden");
                    setPreviewButtonStat(false);
                    showAlert('',json.message===undefined?'操作失败！':json.message,'danger');

                }
            })

            .fail(function () {
                if(failedDom.hasClass("hidden")) failedDom.removeClass("hidden");
                setPreviewButtonStat(false);
                showAlert('','操作失败！','danger');

            })
            .always(function(){
                if(!loadingDom.hasClass("hidden")) loadingDom.addClass("hidden");

            });
    }
});