$(function () {
    var shopId = getQueryString('shopId');
    getlist();
    function getlist() {
        $.ajax({
            url : "/o2o/shopadmin/getproductcategorylist?shopId="+shopId,
            type : "get",
            dataType : "json",
            success : function(data) {
                if (data.redirect) {
                    window.location.href = data.url;
                } else {
                    handleList(data.productlist);
                }
            }
        });
    };
    function handleList(data) {
        var html = '';
        data.map(function(item, index) {
            html += '<div class="row row-product-category now"><div class="col-40">'
                + item.productCategoryName + '</div><div class="col-40">'
                + item.priority
                + '</div><div class="col-20">'
                +  '<a href="#" data-id="'+item.productCategoryId+'" class="delete button" >删除</a>'
                + '</div></div>';

        });
        $('.category-wrap').html(html);
    };
    $("#new").click(function () {
        temphtml='<div class="row row-product-category temp">' +
            '            <div class="col-40">' +
            '                <input  type="text" id="productCategoryName"/>' +
            '            </div>' +
            '            <div class="col-40">' +
            '                <input  type="text" id="priority"/>' +
            '            </div>' +
            '            <div class="col-20">' +
            '                <a  href="#"  class="delete button">删除</a>' +
            '            </div>' +
            '        </div>' +
            ''

        $('.category-wrap').append(temphtml);

    });
    $("#submit").click(function () {
        var temp=$(".temp");
        var productcategorylist=[];
        temp.map(function (index,item) {
            var tempobj={};
            tempobj.productCategoryName=$(item).find("#productCategoryName").val();
            tempobj.priority=$(item).find("#priority").val();
            if(tempobj.productCategoryName&&tempobj.priority){
                productcategorylist.push(tempobj);
            }
        })
        $.ajax({
            url:"/o2o/shopadmin/batchAddProductCategory",
            type: "POST",
            data:JSON.stringify(productcategorylist),
            contentType:"application/json",
            success:function (data) {
                if (data.success){
                    $.toast("添加成功");
                    getlist();
                } else {
                    $.toast("添加失败");
                }
            }
        })
    });
    $('.category-wrap').on('click', '.row-product-category.temp .delete', function(e) {
            console.log($(this).parent().parent());
            $(this).parent().parent().remove();
        });
    $('.category-wrap').on('click', '.row-product-category.now .delete', function(e) {
            var target = e.currentTarget;
            $.confirm('确定么?', function() {
                $.ajax({
                    url : "deleteProductCategory",
                    type : 'POST',
                    data : {
                        productCategoryId : target.dataset.id
                    },
                    dataType : 'json',
                    success : function(data) {
                        if (data.success) {
                            $.toast('删除成功！');
                            getlist();
                        } else {
                            $.toast('删除失败！');
                        }
                    }
                });
            });
        });
});

