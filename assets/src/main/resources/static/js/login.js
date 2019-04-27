$(function ()
{
    /**
     * 弹出消息框
     * @param msg
     */
    function showMessage (msg)
    {
        $("#myModalLabel").text("消息");
        $('#myModal').modal();
        $("#message").text(msg);
    }

    /**
     * 获取数据
     * @returns {{username: (*|jQuery), password: (*|jQuery)}}
     */
    function getData ()
    {
        var username = $("#username").val();
        var password = $("#password").val();

        var data = {"username":username, "password":password};
        return data;
    }

    /**
     * 获取用户编号
     */
    function getUserNo ()
    {
        var userNo = "";

    }

    /**
     * 检测账号是否存在
     * @returns {boolean}
     */
    function checkUser ()
    {
        var username = $("#username").val();
        if (username == "" || username.length == 0)
        {
            $("#user-sign").html("<img class='sign' src='/static/image/sign/error.png' alt='图标' />");
            showMessage ("账号不能为空！");
            return false;
        } else
        {
            $.ajax({
                url:"/user/userCard",
                type:"post",
                data:{"userCard": username},
                dataType:"text",
                success:function (data)
                {
                    if (data == null)
                    {
                        $("#user-sign").html("<img class='sign' src='/static/image/sign/error.png' alt='图标' />");
                        showMessage ("账号参数不合格！");
                        return false;
                    } else if (data == "no")
                    {
                        $("#user-sign").html("<img class='sign' src='/static/image/sign/error.png' alt='图标' />");
                        showMessage ("账号不存在，请先注册，在登录！");
                        return false;
                    } else {
                        $("#user-sign").html("<img class='sign' src='/static/image/sign/right.png' alt='图标' />");
                        return true;
                    }
                }
            });
        }
    }

    /**
     * 检测密码是否合格
     * @returns {boolean}
     */
    function checkPass ()
    {
        var pass = $("#password").val();
        if (pass == "" || pass.length == 0)
        {
            $("#pass-sign").html("<img class='sign' src='/static/image/sign/error.png' alt='图标' />");
            showMessage ("密码不能为空！");
            return false;
        } else
        {
            var regex = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,20}$";
            if (pass.match(regex))
            {
                $("#pass-sign").html("<img class='sign' src='/static/image/sign/right.png' alt='图标' />");
                return true;
            } else
            {
                $("#pass-sign").html("<img class='sign' src='/static/image/sign/error.png' alt='图标' />");
                showMessage("密码格式必须是6-20位之间的数字与字母的结合！");
                return false;
            }
        }
    }

    // 账号失去焦点
    $("#username").blur(function () {
        checkUser ();
    });

    // 密码失去焦点
    $("#password").blur(function () {
        checkPass ();
    });

    // 清空输入框
    $("#reset").click(function () {

    });

    // 激活
    $(".active").click(function () {
        var data = getData();
        if ((data.username == "" || data.username.length == 0) && (data.password == "" || data.password.length == 0))
        {
            showMessage ("请先填写账号和密码！");
            return false;
        } else
        {
            $.ajax({
                url:"/user/queryUserNo",
                type:"post",
                data:data,
                dataType:"text",
                success:function (data) {
                    if (data == "null")
                    {
                        showMessage ("请请确认账号和密码填写完整！");
                        return false;
                    } else if (data == "no")
                    {
                        showMessage ("账号或密码错误，请确认账号密码！");
                        return false;
                    } else
                    {
                        var userNo = data;
                        $.ajax({
                            url:"/user/checkUserState/"+userNo,
                            type:"post",
                            dataType:"text",
                            success:function (data)
                            {
                                if (data == "ok")
                                {
                                    showMessage ("你已激活！");
                                    return false;
                                } else
                                {
                                    $.ajax({
                                        url:"/user/updateUserState/"+1+"/"+userNo,
                                        type:"post",
                                        dataType:"text",
                                        success:function (data)
                                        {
                                            data == "ok"?showMessage ("激活成功！"):showMessage ("激活失败！");
                                        }
                                    });
                                }
                            }
                        });
                    }
                }
            });
        }
    });

    // 提交数据
    $("#submit").click(function () {
        var data = getData();
        // 登录之前检测该用户是否激活状态
        if ((data.username == "" || data.username.length == 0) && (data.password == "" || data.password.length == 0))
        {
            showMessage ("请先填写账号和密码！");
            return false;
        } else
        {
            $.ajax({
                url:"/user/queryUserNo",
                type:"post",
                data:data,
                dataType:"text",
                success:function (data)
                {
                    if (data == "null")
                    {
                        showMessage ("请请确认账号和密码填写完整！");
                        return false;
                    } else if (data == "no")
                    {
                        showMessage ("账号或密码错误，请确认账号密码！");
                        return false;
                    } else
                    {
                        var userNo = data;
                        $.ajax({
                            url:"/user/checkUserState/"+userNo,
                            type:"post",
                            dataType:"text",
                            success:function (data)
                            {
                                if (data == "null")
                                {
                                    // 数据为空
                                    showMessage ("账号或密码为空，请检测账号或密码填写是否正确！");
                                } else if (data == "-1")
                                {
                                    // 账号或密码错误
                                    showMessage ("账号或密码错误！");
                                } else if (data == "ok") // 意识激活状态
                                {
                                    // 可以登录
                                    var userData = getData();
                                    $.ajax({
                                        url:"/user/loginUser",
                                        type:"post",
                                        async:false,
                                        data:userData,
                                        dataType:"text",
                                        success:function (data)
                                        {
                                            if (data == "ok")
                                            {
                                                window.location.href="/assets/";
                                            } else
                                            {
                                                window.location.href="/login/userLogin/";
                                            }
                                        }
                                    });
                                } else {
                                    // 不是激活状态，可先激活
                                    showMessage ("你好，你目前不是激活状态，请先激活，在登录，谢谢合作！");
                                }
                            }
                        });
                    }
                }
            });
        }
    });

});