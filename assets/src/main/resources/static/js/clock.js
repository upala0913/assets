$(function () {
    setInterval(function () {
        var date = new Date();
        var shi = date.getHours();//获得当前时间的小时数//11
        var shi1 = parseInt(shi/10);
        var shi2 = shi%10;
        var fen = date.getMinutes();//获得当前时间分钟数//15
        var fen1 = parseInt(fen/10);
        var fen2 = fen%10;
        var miao = date.getSeconds();//获得当前时间秒数//49
        var m1 = parseInt(miao/10);
        var m2 = miao%10;
        $("#hour1").html("<img src='/static/image/clock/"+shi1+".gif'>");
        $("#hour2").html("<img src='/static/image/clock/"+shi2+".gif'>");
        $("#colon1").html("<img src='/static/image/clock/colon.gif'>");
        $("#minute1").html("<img src='/static/image/clock/"+fen1+".gif'>");
        $("#minute2").html("<img src='/static/image/clock/"+fen2+".gif'>");
        $("#colon2").html("<img src='/static/image/clock/colon.gif'>");
        $("#second1").html("<img src='/static/image/clock/"+m1+".gif'>");
        $("#second2").html("<img src='/static/image/clock/"+m2+".gif'>");
    }, 1000);

});