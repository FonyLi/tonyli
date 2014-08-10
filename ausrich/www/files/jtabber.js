(function ($) {
    $.jtabber = function (params) {
        var navDiv = params.mainLinkTag;
        var selectedClass = params.activeLinkClass;//当前选中类
        var hiddenContentDiv = params.hiddenContentClass;//切换的内容类
        var showDefaultTab = isNaN(params.showDefaultTab) ? 1 : params.showDefaultTab;//首次显示第几元素
        var effect = params.effect;//值为slide、fade
        var effectSpeed = params.effectSpeed;//effect的速度
        var eventval = (params.eventval == null) ? "mouseenter" : params.eventval;//值为　mouseenter(hover)　或　click

        if (!isNaN(showDefaultTab)) {
            showDefaultTab--;
            $("." + hiddenContentDiv + ":eq(" + showDefaultTab + ")").css('display', 'block');
            $(navDiv + ":eq(" + showDefaultTab + ")").addClass(selectedClass);
        }

        $(navDiv).bind(eventval.toString(), function () {
            $(navDiv).each(function() { $(this).removeClass(selectedClass); });
            $("." + hiddenContentDiv).css('display', 'none');
            $(this).addClass(selectedClass);
            //$(navDiv).index(this)为当前li的序号
            var contentDivId = $("." + hiddenContentDiv).eq($(navDiv).index(this));
            if (effect != null) {
                switch (effect) {
                    case 'slide': contentDivId.slideDown(effectSpeed); break;
                    case 'fade': contentDivId.fadeIn(effectSpeed); break;
                }
            } else {
                contentDivId.css('display', 'block');
            }
            return false;
        });
    }
})(jQuery);