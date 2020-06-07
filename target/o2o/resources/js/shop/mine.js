$(function() {

    getlist();
    function getlist(e) {
        $.ajax({
            url : "/o2o/shopadmin/mineinfo",
            type : "GET",
            dataType : "json",
            success : function(data) {
                setlist(data);
                //alert(data.info['localAuthId']);
            }
        });
    }
    function setUser(data) {
        $('#username').text(data.username);
    }
    function setUserinfo(data) {
        $('#userinfo').text(data.userinfo);
    }
    /*    function setUserimg(data) {
        $('#jui-pus-user').text(data.name);
    }
*/
    function setlist(data) {
        var html = '';

            //html ='<div class="jui-pus-user"><img src="'+item['imageurl']+'" alt=""></div>';
            html ='<div class="jui-pus-user"><img src="'+data.info.personInfo['profileImg']+'" alt=""></div>';
            html +=' <div class="jui-flex-box"><h2 class="username">'
            +data.info.personInfo['name']+'</h2><p class="userinfo">'
            +data.info.personInfo['email']+'</p></div>';
            $('.jui-flex').html(html);



    }

});
