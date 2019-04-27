
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
     * 上传图片
     */
    $(".submit").click(function () {
        var formData = new FormData();
        var params = $(".file")[0].files[0];
        formData.append("myFile", params);
        if (formData != null)
        {
            $.ajax({
                url:"/userInfo/uploadFile",
                data:formData,
                type:"post",
                cache : false,
                contentType : false,
                processData : false,
                dataType:"text",
                success:function (data)
                {
                    if (data == "null")
                    {
                        showMessage ("上传文件为空！");
                        return false;
                    } else if (data == "no")
                    {
                        showMessage ("上传文件格式不对！");
                        return false;
                    } else
                    {
                        $(".user_header").attr("src", "/img/"+data);
                        $(".fileName").val(data);
                        return true;
                    }
                }
            });
        }
    });

});