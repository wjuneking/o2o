$(function() {
    getlist();
    function getlist(e) {
        $.ajax({
            url : "/o2o/shopadmin/getshoplist",
            type : "get",
            dataType : "json",
            success : function(data) {
                if (data.success) {
                    handleList(data.shopList);
                    handleUser(data.user);
                }
            }
        });
    }
    function handleUser(data) {
        $('#user-name').text(data.name);
    }

    function handleList(data) {
        var html = '';
        data.map(function(item, index) {
            html +='<span class="ui-row"><div class="ui-col ui-col-25"><img src="'
                +item.shopImg+'"></div>'
                +'<div class="ui-col ui-col-25"  id="sstore">'
                + item.shopName + '</div><div class="ui-col ui-col-25"  id="sstore"'
                + shopStatus(item.enableStatus)
                + '</div><div class="ui-col ui-col-25"  id="sstore">'
                + goShop(item.enableStatus, item.shopId) + '</div></span>';


            /*html += '<div class="ui-row"><div class="ui-col ui-col-33">'
                + item.shopName + '</div><div class="ui-col ui-col-33">'
                + shopStatus(item.enableStatus)
                + '</div><div class="ui-col ui-col-33">'
                + goShop(item.enableStatus, item.shopId) + '</div></div>';*/

        });
        $('.shop-wrap').html(html);
    }

    function shopStatus(status) {
        if (status == 0) {
            return ' style="color:rgb(235, 235, 235);">审核中';
        } else if (status == -1) {
            return ' style="color:#f00;">店铺非法';
        } else if (status == 1) {
            return ' style="color:rgb(5, 253, 79);">已通过';
        }
    }

    function goShop(status, id) {
        if (status == 1) {
            return '<a target="_parent" href="/o2o/shopadmin/shopmanagement?shopId=' + id
                + '">进入</a>';
        } else {
            return '';
        }
    }
});
