var apibaseurl = "http://common.api.eic.org.cn/";

var thisobj;
$.fn.extend({
    timemachineBind: function (sitekey) {
        thisobj = $(this);
        var citys = {
            "index.html": "","": "index.html", "index.html": "home/index", "us/": "country/us", "ca/": "country/ca", "au/": "country/au",
            "nz/": "country/nz", "uk/": "country/uk", "ir/": "country/ir", "fr/": "country/fr", "ne/": "country/ne", "it/": "country/it",
            "ge/": "country/ge", "NorthernEurope/": "country/nordiceurope", "sp/": "country/es", "ko/": "country/kr", "ja/": "country/jp",
            "ma/": "country/my", "hk/": "country/hk", "sg/": "country/sg", "si/": "country/sg",
            "beijing/": "city/beijing", "jinan": "city/jinan", "qingdao": "city/qingdao", "guangzhou": "city/guangzhou",
            "shenzhen/": "city/shenzhen", "dongguan/": "city/dongguan", "shantou/": "city/shantou",
            "zhongshan/": "city/zhongshan", "xiamen/": "city/xiamen", "zhuhai/": "city/zhuhai",
            "shanghai/": "city/shanghai", "hangzhou/": "city/hangzhou", "ningbo/": "city/ningbo",
            "nanjing/": "city/nanjing", "zhengzhou/": "city/zhengzhou", "wuhan/": "city/wuhan",
            "changsha/": "city/changsha", "dalian/": "city/dalian", "shenyang/": "city/shenyang",
            "xian/": "city/xian", "chongqing/": "city/chongqing", "chengdu/": "city/chengdu",
            "tianjin/": "city/tianjin", "fuzhou/": "city/fuzhou", "sydney/": "city/sydney"
        };

        var localhref = window.location.pathname;
        if (localhref != "" && localhref != undefined)
            localhref = localhref.substring(1);

        var url = "";
        var istrue = false;
        for (var i in citys) {
            if (localhref == i) {
                url = citys[i];
                istrue = true;
            }

            else if (localhref == citys[i]) {
                istrue = true;
                url = i;
            }
        }
        if (istrue) {
            var imgurl = "images/timemachine_new.png";
            if (sitekey == 2)
                imgurl = "images/timemachine.png";

            thisobj.prepend(
                "<a href='" + "http://www.eic.org.cn/" + url + "'>" +
                "<img src='" + apibaseurl + imgurl + "'/></a>");
            thisobj.fadeIn();

        } else {
            thisobj.hide();
        }
    }
});










