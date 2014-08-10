$.ajaxCore = function (url, successFun) {
    $.ajax({
        type: "post",
        url: url,
        dataType: "json",
        success: successFun,
        error: function (a) {
            alert(a.responseText);
        }
    });
};


var bdata;
$.provincialleague = function (url, selProvinceId, selCitysId) {
    var p = $("#" + selProvinceId), c = $("#" + selCitysId);
    $.ajaxCore(url, function (data) {
        p.prepend("<option value=\"\"> -- 请选择省份 -- </option>");
        bdata = data.b;
        $.each(data.a, function (i, item) {
            $("<option></option>")
                .val(item.Province_autoid)
                .text(item.Province_name)
                .appendTo(p);
        });

    });

    p.change(function () {
        c.empty();
        c.prepend("<option value=\"\"> -- 请选择城市 -- </option>");
        var id = $(this).val();
        $.each(bdata, function (i, item) {
            if (item.Province_autoid == id) {
                $("<option></option>")
                    .val(item.City_autoid)
                    .text(item.City_name)
                    .appendTo(c);
            }
        });
    });
}