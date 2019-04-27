/**
 * 注册的js
 */
$(function () {

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
     * 判断数据是否为空
     *          如果数据为空，则返回true
     *          如果不为空，则返回false
     * @param data
     * @returns {boolean}
     */
    function isDataEmpty (data)
    {
        return data == "";
    }

    // 检测账号
    function checkUserCard ()
    {
        var data = $("#userCard").val();
        if (isDataEmpty (data))
        {
            $("#userCard-sign").html("<img class='sign' src='/static/image/sign/error.png' alt='图标' />");
            showMessage ("账号不能为空！");
            return false;
        } else
        {
            $.ajax({
                url:"/user/userCard",
                type:"post",
                data:{"userCard":data},
                dataType:"text",
                success:function (data)
                {
                    if (data == "ok")
                    {
                        $("#userCard-sign").html("<img class='sign' src='/static/image/sign/error.png' alt='图标' />");
                        showMessage ("账号存在，不可以注册，请重新填写账号！");
                        return false;
                    } else if (data == "null")
                    {
                        $("#userCard-sign").html("<img class='sign' src='/static/image/sign/error.png' alt='图标' />");
                        showMessage ("账号为空，请确认是否填写账号");
                        return false;
                    } else if (data == "no")
                    {
                        $("#userCard-sign").html("<img class='sign' src='/static/image/sign/right.png' alt='图标' />");
                        return true;
                    } else
                    {
                        $("#userCard-sign").html("<img class='sign' src='/static/image/sign/error.png' alt='图标' />");
                        return false;
                    }
                }
            });
        }
    }

    // 检测姓名
    function checkRealName ()
    {
        var data = $("#realName").val();
        if (isDataEmpty (data))
        {
            showMessage ("姓名不能为空！");
            $("#realName-sign").html("<img class='sign' src='/static/image/sign/error.png' alt='图标' />");
            return false;
        } else
        {
            $("#realName-sign").html("<img class='sign' src='/static/image/sign/right.png' alt='图标' />");
            return true;
        }
    }

    // 检测密码
    function checkPassword ()
    {
        var data = $("#password").val();
        if (isDataEmpty (data))
        {
            $("#password-sign").html("<img class='sign' src='/static/image/sign/error.png' alt='图标' />");
            showMessage ("密码不能为空！");
            return false;
        } else
        {
            var text = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,20}$";
            if (data.match(text))
            {
                $("#password-sign").html("<img class='sign' src='/static/image/sign/right.png' alt='图标' />");
                return true;
            } else
            {
                $("#password-sign").html("<img class='sign' src='/static/image/sign/error.png' alt='图标' />");
                showMessage("密码格式必须是6-20位之间的数字与字母的结合！");
                return false;
            }
        }
    }

    // 检测密码是否相等
    function checkPasswordEquals ()
    {
        var password = $("#password").val();
        var rePassword = $("#repassword").val();
        if (password != rePassword)
        {
            $("#repassword-sign").html("<img class='sign' src='/static/image/sign/error.png' alt='图标' />");
            showMessage ("两次密码不一致！");
            return false;
        } else
        {
            $("#repassword-sign").html("<img class='sign' src='/static/image/sign/right.png' alt='图标' />");
            return true;
        }
    }

    // 检测确认密码
    function checkRePassword ()
    {
        var data = $("#repassword").val();
        if (isDataEmpty (data))
        {
            $("#repassword-sign").html("<img class='sign' src='/static/image/sign/error.png' alt='图标' />");
            showMessage ("确认密码不能为空！");
            return false;
        } else
        {
            return checkPasswordEquals ();
        }
    }

    // 检测邮件
    function checkEmail ()
    {
        var data = $("#email").val();
        if (isDataEmpty (data))
        {
            $("#email-sign").html("<img class='sign' src='/static/image/sign/error.png' alt='图标' />");
            showMessage ("邮件不能为空！");
            return false;
        } else
        {
            var regex = "^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*\\.[a-zA-Z0-9]{2,6}$";
            if (data.match(regex))
            {
                $("#email-sign").html("<img class='sign' src='/static/image/sign/right.png' alt='图标' />");
                return true;
            } else
            {
                $("#email-sign").html("<img class='sign' src='/static/image/sign/error.png' alt='图标' />");
                showMessage("邮箱格式不正确！");
                return false;
            }
        }
    }

    // 检测验证码
    function checkCard ()
    {
        var data = $("#card").val();
        if (isDataEmpty (data))
        {
            $("#card-sign").html("<img class='sign' src='/static/image/sign/error.png' alt='图标' />");
            showMessage ("验证码不能为空！");
            return false;
        } else
        {
            $.ajax({
                url:"/user/checkCard",
                type:"post",
                data:{"card":data},
                dataType:"text",
                success:function (data)
                {
                    if (data == "ok")
                    {
                        $("#card-sign").html("<img class='sign' src='/static/image/sign/right.png' alt='图标' />");
                        return true;
                    } else if (data == "null")
                    {
                        $("#card-sign").html("<img class='sign' src='/static/image/sign/error.png' alt='图标' />");
                        showMessage ("验证码为空！");
                        return false;
                    } else
                    {
                        $("#card-sign").html("<img class='sign' src='/static/image/sign/error.png' alt='图标' />");
                        showMessage ("验证码不对！");
                        return false;
                    }
                }
            });
        }
    }

    /**
     * 账号失去焦点
     */
    $("#userCard").blur(function () {
        checkUserCard ();
    });

    /**
     * 真实姓名失去焦点
     */
    $("#realName").blur(function () {
        checkRealName ();
    });

    /**
     * 密码失去焦点
     */
    $("#password").blur(function () {
        checkPassword ();
    });

    /**
     * 确认密码失去焦点
     */
    $("#repassword").blur(function () {
        checkRePassword ();
    });

    /**
     * 邮件失去焦点
     */
    $("#email").blur(function () {
        checkEmail ();
    });

    /**
     * 验证码失去焦点
     */
    $("#card").blur(function () {
        checkCard ();
    });

    // 检测数据是否完整
    function checkData ()
    {
        return checkUserCard() && checkRealName() && checkPassword() && checkRePassword() && checkEmail() && checkCard();
    }

    // 发送验证码
    $(".send").click(function () {
        var email = $("#email").val();
        if (checkEmail ())
        {
            $.ajax({
                url:"/user/sendEmail",
                type:"post",
                data:{"email":email},
                dataType:"text",
                success:function (data)
                {
                    if (data == "ok")
                    {
                        showMessage ("验证码已发送至"+email+"邮箱，请注意查收！");
                    } else if (data == "null")
                    {
                        showMessage ("邮箱为空！");
                    } else
                    {
                        showMessage ("验证码发送出错，请重新点击发送验证码！");
                    }
                }
            });
        }
    });

    // 获取用户数据
    function getData ()
    {
        var userCard = $("#userCard").val();
        var realName = $("#realName").val();
        var password = $("#password").val();
        var email = $("#email").val();
        var user = {"username":userCard,"realName":realName,"password":password,"email":email};
        return user;
    }

    // 检测提交数据是否完整
    $(".submit").click(function () {
        var user = getData ();
        if (checkData ())
        {
            $.ajax({
                url:"/user/registry",
                type:"post",
                data:user,
                dataType:"text",
                success:function (data)
                {
                    if (data == "ok")
                    {
                        showMessage("注册成功！")
                    }
                }
            });
        }
    });

});