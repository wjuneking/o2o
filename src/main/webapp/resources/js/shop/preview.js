$(function () {
    var productid = getQueryString("productId");
    getproduct();


    function getproduct() {
        $.ajax({
            url: "/o2o/shopadmin/getproductbyid?productId=" + productid,
            type: "get",
            dataType: "json",
            success: function (data) {
                if (data.success) {
                    createproduct(data.product);
                } else {
                    $.toast(1, 2, 3);
                }
            }
        });

    }


    function createproduct(data) {
        var html = '';
        html += '<ul class="ui-row" >'
            + '<li class="ui-col ui-col-33">'
            + '<div class="ui-grid-halve-img" id="divMain" style="overflow:hidden;">'
            + ' <i class="ui-subscript ui-subscript-red">最新</i>'
            + '<img src="'
            + data.imgAddr
            + '" alt="">'
            + '</li>'
            + ' <li class="ui-col ui-col-67">'
            + '<ul class="ui-row">'
            + '<li class="ui-col ui-col-100">'
            +   data.productName
            +'</li>'
            + ' <li class="ui-col ui-col-100">原价：'
            + data.normalPrice
            +'</li>'
            + '<li class="ui-col ui-col-67">现价：'
            +data.promotionPrice
            +'<li>'
            + '<li class="ui-col ui-col-33"><button class="ui-btn ui-btn-primary">购买</button><li>'
            + '</ul>'
            + '</li>'
            + '</ul>'
        $('.content-block').html(html);
    }
})
