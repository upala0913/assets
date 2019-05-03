$(function () {

    var url = "http://api.jisuapi.com/area/";
    var key = "137b2595309f2dc3";

    var init = function () {
        getData (null, "province");
        initEvent ();
    };

    // 省级
    var getData = function (index, area)
    {
        var tempUrl = url + area + "?callback=?";
        var tempKey = {
            appkey:key,
            parentid:index
        };
        $.get(tempUrl, tempKey, function (response) {
            if (response.msg === "ok")
            {
                var data = response.result;
                render(area, data);
            } else
            {
                if (response.status == 203)
                {
                    alert ("已经查到底了！");
                } else
                {
                    alert ("系统繁忙，请重试！");
                }
            }
        }, "json");
    };

    // 市级区级
    var initEvent = function ()
    {
        $(".province").on("change", onProvinceChange);
        $(".city").on("change", onCityChange);
    };
    var onProvinceChange = function ()
    {
        var $this = $(this),
            val = $this.val();
        $(".city, .town").html("<option value='0' >请选择</option>");
        if (val == 0)
        {
            return ;
        }
        getData(val, "city");
    };
    var onCityChange = function ()
    {
        var $this = $(this),
            val = $this.val();
        if (val == 0)
        {
            $(".town").html("<option value='0' >请选择</option>");
            return ;
        }
        getData(val, "town");
    };

    var render = function (area, data)
    {
        var arr = ["<option value='0' >请选择</option>"];
        $.each(data, function (index, obj) {
            arr.push("<option value="+ obj.id +" >"+ obj.name +"</option>");
        });
        $("." + area).html(arr.join(""));
    };

    // 初始化
    init ();
});