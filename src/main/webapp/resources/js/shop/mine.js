// $(function() {
// //
// //     getlist();
// //     function getlist(e) {
// //         $.ajax({
// //             url : "/o2o/shopadmin/mineinfo",
// //             type : "GET",
// //             dataType : "json",
// //             success : function(data) {
// //                 setlist(data);
// //                 //alert(data.info['localAuthId']);
// //             }
// //         });
// //     }
// //     function setUser(data) {
// //         $('#username').text(data.username);
// //     }
// //     function setUserinfo(data) {
// //         $('#userinfo').text(data.userinfo);
// //     }
// //     /*    function setUserimg(data) {
// //         $('#jui-pus-user').text(data.name);
// //     }
// // */
// //     function setlist(data) {
// //         var html = '';
// //
// //             //html ='<div class="jui-pus-user"><img src="'+item['imageurl']+'" alt=""></div>';
// //             html ='<div class="jui-pus-user"><img src="'+data.info.personInfo['profileImg']+'" alt=""></div>';
// //             html +=' <div class="jui-flex-box"><h2 class="username">'
// //             +data.info.personInfo['name']+'</h2><p class="userinfo">'
// //             +data.info.personInfo['email']+'</p></div>';
// //             $('.jui-flex').html(html);
// //
// //
// //
// //     }
// //
// // });


$(function () {

    getlist();

    function getlist(e) {
        $.ajax({
            url: "/o2o/shopadmin/mineinfo",
            type: "GET",
            dataType: "json",
            success: function (data) {
                //alert(JSON.stringify(data));
                setlist(data);
                //alert(data.info['localAuthId']);
            }
        });
    }

    function setlist(data) {
        var html = '';
        var set = '';
        var sex = '女';
        if (data.info.personInfo['gender'] = 1) {
            sex = '男';
        }
        //html ='<div class="jui-pus-user"><img src="'+item['imageurl']+'" alt=""></div>';
        set = ' <li><span style="width: 100%;"><p></p>个人详细信息</span>';
        html = '<div class="jui-pus-user"><img src="' + data.info.personInfo['profileImg'] + '" alt=""></div>';
        set += '<ul><img class="ui-col ui-col-10 ll" src="../resources/img/sex.png" alt=""><li class="ui-row" style="width: 90%">性别：' + sex
            + '</li><img class="ui-col ui-col-10 ll" src="../resources/img/usertype.png" alt=""><li class="ui-row" style="width: 90%">用户状态:' + data.info.personInfo['enableStatus']
            + '</li><img class="ui-col ui-col-10 ll" src="../resources/img/user.png" alt=""><li class="ui-row" style="width: 90%">用户类型:' + data.info.personInfo['userType']
            + '</li><img class="ui-col ui-col-10 ll" src="../resources/img/clock.png" alt=""><li class="ui-row" style="width: 90%">创建时间:' + data.info.personInfo['createTime']
            + '</li></ul></li>';
        html += ' <div class="jui-flex-box"><h2 class="username">'
            + data.info.personInfo['name'] + '</h2><p class="userinfo">'
            + data.info.personInfo['email'] + '</p></div>';
        $('.jui-flex').html(html);
        $('#parent').html(set);
        var html = '';

        //  alert("success!!");

        //alert("set success!!");
    }

});

