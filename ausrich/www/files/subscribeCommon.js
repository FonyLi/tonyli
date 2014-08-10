//需要引入colorbox.js
var _baseurl = "";
var _selfurl = "";
$.fn.extend({
    subscribeBind: function (senderurl, returnurl) {
        _baseurl = senderurl;
        _selfurl = returnurl;
        $(this).attr("href", _baseurl + "?returnurl=" + _selfurl + "&reserveType=1&targetId=" + $(this).attr("mid"));
        $(this).colorbox({
            iframe: true,
            width: "789px",
            height: "539px",
            fastIframe: true
        });
    }
});

function showreg(mobile, code) {
    $.colorbox({ open: true, href: _baseurl + "successReg/?returnurl=" + _selfurl + "&mobile=" + mobile + "&code=" + code, iframe: true, width: "683px", height: "555px" });
}

function showsuccess() {
    $.colorbox({ open: true, href: _baseurl + "success/", iframe: true, width: "423px", height: "205px" });
}

function showregsuccess() {
    $.colorbox({ open: true, href: _baseurl + "regsuccess/", iframe: true, width: "423px", height: "205px" });
}