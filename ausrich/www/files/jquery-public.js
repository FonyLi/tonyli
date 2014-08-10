;(function($){
	$.fn.extend({
		//多行无缝上下滚动 
		"rollingUpDownTxt":function(){
				var wrap=$(this);
				var interval=2000;
				var moving;
				wrap.hover(function(){ 
					clearInterval(moving);
					},function() {
					    moving = setInterval(function() {
					        var field = wrap.find('li:first');
					        var h = field.height() + parseInt(field.css("padding-top")) + parseInt(field.css("padding-bottom"));
					        field.animate({ marginTop: -h + 'px' }, 600, function() {
					            field.css('marginTop', 0).appendTo(wrap);
					        });
					    }, interval);
					}).trigger('mouseleave');
			} 		   
		});	  
})(jQuery);

jQuery(function ($) {
    /*延迟加载 参数threshold: 200为距离200米加载*/
    if (typeof($.fn.lazyload) == 'function') {
        $("img.lazy").lazyload({ effect: "fadeIn", skip_invisible: false });
    }
    //头部 主导航下拉效果
	$(".headernav-ul li").hover(function(){
		$(".pulldownBox").hide();
		$(".headernav-ul li a").removeClass("headernav-selected");
		var $showmenu = $($(this).attr("name"));		
		$showmenu.show();
		$("a", this).addClass("headernav-selected");
	},function(){
		var $showmenu = $($(this).attr("name"));
		$("#header-nav").mouseleave(function() {
		  $showmenu.hide();
		  $(".headernav-ul li a").removeClass("headernav-selected");
        });		
	});
	//头部 搜索框的下拉效果
	$(".search_news").hover(function(){$(".search_menu").show();},function(){$(".search_menu").hide();});
	$(".search_menu li").hover(function(){$(this).addClass("selected");},function(){$(this).removeClass("selected");});
	$(".search_menu li").click(function(){
	   	$(".search_news .search_type").html("").html($(this).html());
	   	$("#searchtypeid").attr("value", $(this).html());
	   	$(".search_menu").hide();
	});
    //头部顶部分公司的下拉效果
	var $topfen = $(".menu-hd");
	$topfen.hover(function(){
		  $("#fenlist").show();
		  $topfen.addClass("fen_menu_hover");
	  },function(){
		  $(".menu").mouseleave(function(){
			  $("#fenlist").hide();
			  $topfen.removeClass("fen_menu_hover");
		  });	
	  });

	//尾部收缩效果
	var footerIsopen = true;
    $("#footer_show a").click(function(){
	   	if(footerIsopen){
		  //关闭
		  $("#footer_list").hide();
		  $("#footer_show a b").removeClass("showall");
		  $("#footer_show a b").addClass("showclose");
		  footerIsopen = false;
		}else{
		  //打开
		  $("#footer_list").show();
		  $("#footer_show a b").removeClass("showclose");
		  $("#footer_show a b").addClass("showall");		  
		  footerIsopen = true;		  
		}
	});
	//尾部友情连接收缩效果
    $("#footer_list a.btn_up").click(function(){
		  $("#footer_list .flink_info").show();
		  $("#footer_list .up").hide();
		  $("#footer_list .down").show();
	});
    $("#footer_list a.btn_down").click(function(){
		  $("#footer_list .flink_info").hide();
		  $("#footer_list .up").show();
		  $("#footer_list .down").hide();		  
	});
    gototop("#totop");
});

//返回顶部函数 totopdiv:为返回顶部的div
function gototop(totopdiv) {
    $("li", $(totopdiv)).hover(function () {
        $(".totop_litxt").hide();
        $(".totop_litxt",this).show();
        $(this).addClass("totopli_selected");
        if ($(this).attr("id") == "sewm_li") { $(".gotophidden").hide(); $("#twowm").show(); }
        if ($(this).attr("id") == "yycs_li") { $(".gotophidden").hide(); $("#gotoptel").show(); }
        if ($(this).attr("id") == "vjlq_li") { $(".gotophidden").hide(); $("#gotopfenxiang").show(); }
    }, function () {
        if ($(this).attr("id") == "sewm_li") {
            $(totopdiv).mouseleave(function () { $(".gotophidden").hide();$(".totop_litxt").hide(); $("li", $(totopdiv)).removeClass("totopli_selected"); });
        } else {
            $(this).removeClass("totopli_selected");
            $(".totop_litxt").hide();
        }
    });
    $(window).scroll(function () {
        var scrolltop = $(window).scrollTop();        
        if (scrolltop >= 150) { $("#fhdb_li").css("visibility", "visible"); } else { $("#fhdb_li").css("visibility", "hidden"); }
    });
    $("#fhdb_li").click(function () {
        $("html,body").animate({ scrollTop: 0 }, 1000);
    });
}

//用于标签切换 需引入jtabber.js
function jtabshow(params) {
    $.jtabber(params);
}

//用于焦点图的左右轮换 需引入jcarousellite.js
function jTurnlr(params) {
    $(params.mainDiv).jCarouselLite(params);
}

//用于弹出框 需引入jquery.colorbox.js
function jBoxPopUp(params) {
    $(params.mainDiv).colorbox(params);
    $(".popbox .popbox_close").click(function () { jQuery().colorbox.close(); });
}

/*搜索模块，点击显示二层菜单,项目菜单点击效果
示例:应用于活动搜索页面
showDownMenu(true, "#hd_menuall", ".hdlist_menu");
showDownMenu(false, "#zhuanjia_a", ".hdlist_menu");
showDownMenu(false, "#mingshi_a", ".hdlist_menu");
*/
function showDownMenu(isall,clicksrcdiv,menulistdiv) {
    $(clicksrcdiv).click(function () {
        $(menulistdiv).hide();
        if(!isall) {$($(this).attr("name")).show(); }
        $(this).parent().children().removeClass("on");
        $(this).addClass("on");
    });
}