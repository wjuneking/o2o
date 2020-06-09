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
        var usertype ='用户';
        var enablestatus='可用';
        if (data.info.personInfo['gender'] = 1) {
            sex = '男';
        }
        if (data.info.personInfo['userType']=1){
            usertype ='用户';
        }else if (data.info.personInfo['userType']=2){
            usertype ='商家';
        } else{
            usertype ='超级管理员';
        }
        if (data.info.personInfo['enableStatus']=0){
            enablestatus ='不可用';
        }

        //html ='<div class="jui-pus-user"><img src="'+item['imageurl']+'" alt=""></div>';
        set = ' <li><span style="width: 100%;"><p></p>个人详细信息</span>';
        html = '<div class="jui-pus-user"><img src="' + data.info.personInfo['profileImg'] + '" alt=""></div>';
        set += '<ul><img class="ui-col ui-col-10 ll" src="../resources/img/sex.png" alt=""><li class="ui-row" style="width: 90%">性别：' + sex
            + '</li><img class="ui-col ui-col-10 ll" src="../resources/img/usertype.png" alt=""><li class="ui-row" style="width: 90%">用户状态:' + enablestatus
            + '</li><img class="ui-col ui-col-10 ll" src="../resources/img/user.png" alt=""><li class="ui-row" style="width: 90%">用户类型:' + usertype
            + '</li><img class="ui-col ui-col-10 ll" src="../resources/img/clock.png" alt=""><li class="ui-row" style="width: 90%">创建时间:' + transferTime(data.info.personInfo['createTime'])
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
    function transferTime(cTime) {
        //将json串的一串数字进行解析
        var jsonDate = new Date(parseInt(cTime));

        //为Date对象添加一个新属性，主要是将解析到的时间数据转换为我们熟悉的“yyyy-MM-dd hh:mm:ss”样式
        Date.prototype.format = function(format) {
            var o = {
                //获得解析出来数据的相应信息，可参考js官方文档里面Date对象所具备的方法
                "y+" : this.getFullYear(),//得到对应的年信息
                "M+" : this.getMonth() + 1, //得到对应的月信息，得到的数字范围是0~11，所以要加一
                "d+" : this.getDate(), //得到对应的日信息
                "h+" : this.getHours(), //得到对应的小时信息 
                "m+" : this.getMinutes(), //得到对应的分钟信息
                "s+" : this.getSeconds(), //得到对应的秒信息
            }

            //将年转换为完整的年形式
            if (/(y+)/.test(format)) {
                format = format.replace(RegExp.$1,
                    (this.getFullYear() + "")
                        .substr(4 - RegExp.$1.length));
            }

            //连接得到的年月日 时分秒信息
            for ( var k in o) {
                if (new RegExp("(" + k + ")").test(format)) {
                    format = format.replace(RegExp.$1,
                        RegExp.$1.length == 1 ? o[k] : ("00" + o[k])
                            .substr(("" + o[k]).length));
                }
            }
            return format;
        }
        var newDate = jsonDate.format("yyyy-MM-dd hh:mm:ss");
        return newDate;
    }
});

