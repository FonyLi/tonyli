﻿var hrefstr=window.location.href;var now=new Date();$("body").append('<div style="z-index: 100; display: block; overflow: hidden; padding: 0px; position: fixed; margin-top: 225px; top: 50%; left: 10px;" ><a  href="http://qna0.eic.org.cn/tiny1_suitable_country" id="looyuLeftBtn" target="_blank"><img style="border:0" src="http://www.eic.org.cn/Images/study_place.png"></a></div>');Date.prototype.isIn=function(a,b){return this>a&&this<b};var _domain="eic.org.cn";$=jQuery;if(hrefstr.indexOf(_domain)>-1){document.domain=_domain}$(function(){var a=function(){$("body").css("background","#fff")};if($("body").css("background-image")==="none"&&$("body").css("background-color")==="transparent"){a();$(function(){a()})}});var enc=encodeURIComponent;String.prototype.containsAny=function(b){for(var a=0;a<b.length;a++){if(this.indexOf(b[a])>-1){return true}}return false};function isIe6(){return(/\bMSIE 6/.test(navigator.userAgent)&&!window.opera)}function sleep(a){var c=new Date();var b=c.getTime()+a;while(true){c=new Date();if(c.getTime()>b){return}}}function addEvent(c,b,a){if(c.addEventListener){c.addEventListener(b,a,false)}else{if(c.attachEvent){c.attachEvent("on"+b,function(){return a.call(c,window.event)})}}}(function(a){a.fn.fixedWhenIe6=function(c){this.css({position:"absolute"});var b=a(window);if(c.top){this.css({top:c.top+b.scrollTop()+"px"})}if(c.left){this.css({left:c.left+b.scrollLeft()+"px"})}if(c.right){this.css({right:c.right-b.scrollLeft()+"px"})}if(c.bottom){this.css({bottom:c.bottom-b.scrollTop()+"px"})}};a.fn.fixedWhenIe62=function(c){var d=c;var b=this;b.fixedWhenIe6(d);addEvent(window,"resize",function(){b.fixedWhenIe6(d)});addEvent(window,"scroll",function(){b.fixedWhenIe6(d)});return this};a.fn.appendAsInner=function(){this.css({position:"relative"});for(var b=0;b<arguments.length;b++){this.append(arguments[b].css({position:"absolute"}))}};a.fn.center=function(d){d=d?this.parent():window;var b="fixed";if(d!=window||isIe6()){b="absolute"}var c=((a(d).height()-this.outerHeight())/2);var e=((a(d).width()-this.outerWidth())/2);if(b==="absolute"){this.css({position:"absolute",top:c+a(d).scrollTop()+"px",left:e+a(d).scrollLeft()+"px"})}else{this.css({position:d!=window?"absolute":"fixed",top:c+"px",left:e+"px"})}this.data("centered",true);return this};a.fn.centerFixed=function(b){var d=b;var c=this;c.center(d);addEvent(window,"resize",function(){c.center(d)});if(isIe6()){addEvent(window,"scroll",function(){c.center(d)})}return this};a.fn.fixed=function(c){if(isIe6()){return this.fixedWhenIe62(c)}this.css({position:"fixed"});var b=a(parent);if(c.top){this.css({top:c.top+"px"})}if(c.left){this.css({left:c.left+"px"})}if(c.right){this.css({right:c.right+"px"})}if(c.bottom){this.css({bottom:c.bottom+"px"})}return this}})(jQuery);function showAd(a,c,b){if(a){$("body").prepend('<div id="eicTOPL" style="display:none">'+a+"</div>");$("#eicTOPL img").load(function(){$("#eicTOPL").slideDown(1000);setTimeout(function(){$("#eicTOPL").slideUp(1000);d()},8000)})}else{d()}function d(){setTimeout(function(){$("body").prepend('<div id="eicTOPS" style="width:1000px;margin:0 auto;display:none"><div id="eicTOPS2" style="visibility: visible; width: 1000px; height: 90px; overflow: hidden;">'+c+"</div></div>");$("#eicTOPS img:last-child").load(function(){$("#eicTOPS").slideDown(500);$("#eicTOPS2").KinSlideshow({moveStyle:"down",intervalTime:b||5,titleBar:{titleBar_height:24,titleBar_bgColor:"#08355c",titleBar_alpha:0.1},titleFont:{TitleFont_size:12,TitleFont_color:"#FFFFFF",TitleFont_weight:"normal"},btn:{btn_bgColor:"#FFFFFF",btn_bgHoverColor:"#1072aa",btn_fontColor:"#000000",btn_fontHoverColor:"#FFFFFF",btn_borderColor:"#cccccc",btn_borderHoverColor:"#1188c0",btn_borderWidth:1}})})},1500)}}(function(){if(typeof($.fn.live)==="undefined"){$.fn.live=function(a,b){$(document).on(a,this.selector,b)}}})("setLive");(function(){var n=function(q,s){var r=document.getElementsByTagName("script");for(var o=0,p=r.length;o<p;o++){if(r[o].src.indexOf(q)>-1){return r[o].getAttribute(s)}}};eic=this.eic||{};eic.c=eic.c||{};eic.f=eic.f||{};eic.inner=eic.inner||{};eic.c.config=$.parseJSON(n("public/js/analytics","config"))||{useSpLooyu:"false",disableLooyu:"false",disableBaidu:"false"};eic.c.config.useSpLooyu=eic.c.config.useSpLooyu==="true";eic.c.config.disableLooyu=eic.c.config.disableLooyu==="true";eic.c.config.disableBaidu=eic.c.config.disableBaidu==="true";eic.c.config.disableGaLooyu=false;eic.c.companyId=32383;eic.c.groupId=35315;eic.c.openLooyu="doyoo.util.openChat('g="+eic.c.groupId+"');doyoo.util.accept();return false;";eic.c.getDefaultProbeId=function(){if(hrefstr.indexOf("http://m.eic.org.cn")==0){return 112668}if(hrefstr.indexOf("http://expo.eic.org.cn")==0){return 112669}if(hrefstr.indexOf("http://passport.eic.org.cn")==0){return 112672}if(hrefstr.indexOf("http://college.eic.org.cn")==0){return 112671}return 108841};eic.c.qso=(function(q){if(q==""){return{}}var r={};for(var o=0;o<q.length;++o){var s=q[o].split("=");if(s.length!=2){continue}r[s[0]]=decodeURIComponent(s[1].replace(/\+/g," "))}return r})(window.location.search.substr(1).split("&"));$.fn.loadExpoLecture=function(o,p){p=p||"";if(hrefstr.indexOf("http://expo")>-1){this.load("/lecture/?num="+o+"&cityid="+p)}else{this.load("/f/edu_lecturer2.php?num="+o+"&cityid="+p)}};eic.f.alert=function(o){o=o.replace("<br />","\n").replace("<br/>","\n").replace("<br>","\n");window.alert(o);return};eic.f.setGa=function(o){if(!eic.c.config.disableGaLooyu){o.click(function(p){var q=$(p.target);if(!q.attr("onclick")){q=q.parent()}if(q.attr("onclick")&&q.attr("onclick").indexOf("doyoo")===0){_gaq.push(["_trackEvent","IM","looyu",hrefstr])}})}};eic.f.inFrame=function(){return window.self!==window.top};window.preventPopup=function(){if(eic.f.inFrame()){return true}var o=["/looyu/"];return hrefstr.containsAny(o)};eic.f.forbidLooyu=function(){return eic.f.inFrame()&&hrefstr.indexOf("/special/")==-1};eic.f.writeScript=function(o,p,q){if(!p){document.write('<script src= "'+o+'"></script>')}else{q=q||function(){};$.getScript(o,q)}};eic.f.writeOuterScript=function(o,p,q){if(eic.f.inFrame()){return}eic.f.writeScript(o,p,q)};eic.f.writeOuterScript("http://www.eic.org.cn/public/js/m.js");eic.f.nocopy=function(){document.body.oncopy=function(){alert("请使用收藏夹,或在线咨询启德教育集团.全国免费咨询电话:400-1010-123");return false}};eic.f.handleImage=function(o,q){var p=$("<img />").attr("src",o).css({display:"none"});$("body").append(p);p.load(function(){var r={width:p.width(),height:p.height()};p.remove();q(r)})};eic.inner.getSysLooyu=function(){if(now.isIn(new Date(2014,0,30,12),new Date(2014,1,3,6))){var o="http://www.eic.org.cn/special/eic_abroad_dream";eic.c.openLooyu="return false;";var q="http://www.eic.org.cn/images/2014/";return{l:{i:q+"l.jpg",href:o},m:{i:q+"m.jpg",href:o,a:{t:159,l:22,w:127,h:30},r:{t:10,l:417,w:20,h:20}},r:{i:q+"r.jpg",href:o}}}return false};eic.f.addLeft=function(p){if(p){e(p,"l");e(p,"t");e(p,"href");e(p,"onclick");p.i=p.i||"images/ly_l.jpg";var q=function(t){$("#looyuLeft").remove();var r=k(t.href);var s=$('			<div id="looyuLeft" class="looyuDiv _looyuTrigger" style="z-index:100;display:block;overflow:hidden;padding:0;position:fixed;">				<a id="looyuLeftBtn" href="'+t.href+'" onclick="'+t.onclick+'" target="'+r+'"><img src="'+t.i+'" style="border:0"/></a>			</div>').css({"margin-top":t.t+"px",top:"50%",left:t.s+"px"});if(t.w){b(s,t)}return s};var o=$("body");o.append(q(p))}};eic.f.addRight=function(p){if(p){e(p,"r");e(p,"t");e(p,"href");e(p,"onclick");p.i=p.i||"images/ly_r.jpg";var q=function(t){$("#looyuRight").remove();var r=k(t.href);var s=$('			<div id="looyuRight" class="looyuDiv _looyuTrigger" style="z-index:100;display:block;overflow:hidden;padding:0;position:fixed;">				<a id="looyuRightBtn" href="'+t.href+'" onclick="'+t.onclick+'" target="'+r+'"><img src="'+t.i+'" style="border:0"/></a>			</div>').css({"margin-top":t.t+"px",top:"50%",right:t.s+"px"});if(t.w){b(s,t)}return s};var o=$("body");o.append(q(p))}};function l(r,s,u,q,v,o,p){setTimeout(function(){eic.f.addMid({i:r,t:u,l:q,w:v,h:o,href:p})},parseInt(s)*1000)}var i=1;eic.f.addMid=function(o){if(o){o.i=o.i||"images/ly_m.jpg";e(o,"href");e(o,"onclick");var q=function(t){$("#looyuMid").remove();var r=k(t.href);var s=$('<div class="adBG" style="background:url('+t.i+')"><div style="width:100%;height:100%"><a class="_looyuTrigger2" onclick="'+t.onclick+'" style="width:100%;height:100%;display:block;"   href="'+t.href+'" target="_blank" ></a> </div><a href="javascript:void(0);"style="position:absolute;display:block;left:'+t.l+"px;top:"+t.t+"px;width:"+t.w+"px;height:"+t.h+'px;cursor:pointer;" class="yclose"></a><div>');return s};var p=$("body");p.append(q(o));$(".yclose").click(function(){$(".adBG").hide();if($("#hid"+i).length>0){l($("#hid"+i).attr("img"),$("#hid"+i).attr("time"),$("#hid"+i).attr("t"),$("#hid"+i).attr("l"),$("#hid"+i).attr("w"),$("#hid"+i).attr("h"),$("#hid"+i).attr("href"))}i++})}};function e(p,q,o){if(q==="onclick"){h(p);return}if(q==="href"){o="javascript:void(0)"}if(q==="t"){o=260}if(q==="r"||q==="l"||q==="s"){o=10}p[q]=(p[q]||o);p.s=p.r||p.l}var k=function(o){return(o.indexOf("void(0)")!==-1||o.indexOf("dooyu.")!==-1)?"":"_blank"};var b=function(o,p){o.css({height:p.h+"px",width:p.w+"px"})};var h=function(o){if(o.onclick){o.onclick=o.onclick+"return false;";return}if(o.href!=="javascript:void(0)"){o.onclick="";return}o.onclick=eic.c.openLooyu};eic.f.setSpecialLooyu=function(t,p,q,r){var u=eic.inner.getSysLooyu();if(u){t=u.l;p=u.m;q=u.r}if(!r&&(!eic.c.config.useSpLooyu||preventPopup())){return}eic.f.writeScript("http://gate.looyu.com/"+eic.c.companyId+"/"+eic.c.getDefaultProbeId()+".js");$("head").append('<link rel="stylesheet" type="text/css" href="http://www.eic.org.cn/public/style/v2/css/eic_index.css" />');eic.f.addLeft(t);eic.f.addRight(q);eic.f.addMid(p);var o=function(){$("#looyuMid").centerFixed().show()};$(function(){o();eic.f.setGa($("._looyuTrigger"))})};function f(o){eic.c.looyuJs='<script language="javascript" src= "http://gate.looyu.com/'+eic.c.companyId+"/"+o+'.js"></script>';eic.c.looyuLink="http://chat.looyu.com/chat/chat/p.do?c="+eic.c.companyId+"&f="+o+"&g="+eic.c.groupId}f(eic.c.getDefaultProbeId());eic.c.rightAdHtml='		<div id="leyuRight" style="top:128px;right:0px;display:block;height:388px;width:120px;overflow:hidden;padding:0;position:fixed;">			<a href="javascript:void(0)" onclick="'+eic.c.openLooyu+'" target="_blank" id="contactR">			<img id="leyuRightImg" src="http://img0.eic.org.cn/public/images/leyu/0125/r_online_3.png" style="border:0"/>			</a>		</div>';eic.c.use25thExpoLooyu=false;eic.c.promotionCode="";eic.c.defaultCityId=0;eic.c.defaultCityName="";eic.c.reserveIframe="";eic.c.enableRightBottom=true;var a="eic_28th_expo";var j="第28届启德国际教育展";var d="http://www.eic.org.cn/special/eic_28th_expo/";var c="http://www.eic.org.cn/f/reserve/expo/default/";var m="http://www.eic.org.cn/f/reserve/expo/bottom/";function g(o){return o+"?url="+d+"&eduname="+a+"&newname="+enc(j)+"&promotionCode="+eic.c.promotionCode+"&cityId="+eic.c.defaultCityId}eic.c.expoBottomReserveUrl=g(m);if(hrefstr.indexOf("shanghai/hujiang_25th_expo")>0){f(107527);eic.c.promotionCode="hujiang_25th_expo";eic.c.defaultCityId=9;eic.c.defaultCityName="上海";eic.c.reserveIframe=g(c);eic.c.rightAdHtml='		<div id="leyuRight" style="top:260px;right:0px;display:block;height:134px;width:113px;overflow:hidden;padding:0;position:fixed;">			<a href="'+eic.c.looyuLink+'" target="_blank" id="contactR">			<img id="leyuRightImg" src="http://www.eic.org.cn/public/images/leyu/25th-expo/r.jpg" style="border:0"/>			</a>		</div>'}else{if(hrefstr.indexOf("xian/renren_25th_expo")>0){eic.c.promotionCode="renren_25th_expo";eic.c.defaultCityId=16;eic.c.defaultCityName="西安";eic.c.reserveIframe=g(c)}else{if(hrefstr.indexOf("nanjing/renren_25th_expo")>0){eic.c.promotionCode="nanjing_renren_25th_expo";eic.c.defaultCityId=10;eic.c.defaultCityName="南京";eic.c.reserveIframe=g(c)}else{if(hrefstr.indexOf("special/au_university")>0){f(105417)}else{if(hrefstr.containsAny(["au_","/au/"])){f(102877)}else{if(hrefstr.indexOf("special/abroad_guide")>0){f(108605);eic.c.rightAdHtml='		<div id="leyuRight" style="top:260px;right:0px;display:block;height:150px;width:112px;overflow:hidden;padding:0;position:fixed;">			<a href="'+eic.c.looyuLink+'" target="_blank" id="contactR">			<img id="leyuRightImg" src="http://www.eic.org.cn/public/images/leyu/abroad-guide/r.jpg" style="border:0"/>			</a>		</div>'}}}}}}if(hrefstr.containsAny(["/eic_25th_expo","/renren_25th_expo","bj_news/20130520261304.html","sy_news/20130620263555.html","xian_news/20130618263429.html","interview_8550.html","cq_news/20130620263551.html","sz_study/20130608263146.html","special/gz_eic_25th_expo"])){eic.c.use25thExpoLooyu=true;f(108122);eic.c.rightAdHtml='		<div id="leyuRight" style="top:260px;right:0px;display:block;height:134px;width:113px;overflow:hidden;padding:0;position:fixed;">			<a href="'+eic.c.looyuLink+'" target="_blank" id="contactR">			<img id="leyuRightImg" src="http://www.eic.org.cn/public/images/leyu/25th-expo/r.jpg" style="border:0"/>			</a>		</div>'}})("setConst");(function(){$('a[href^="http://lg75.looyu.com"]').attr("href",eic.c.looyuLink)})("fixLooyuLink");(function(){var b=function(c,d){d=d||c;var f=$("#"+c);var e=eic.c[d];if(f.length&&e){f.val(e).attr("src",e)}};b("promotionCode");b("city","defaultCityId");b("reserveIframe");if($(".hd").length&&eic.c.defaultCityName){var a=$(".hd li").filter(function(){return $(this).text().indexOf(eic.c.defaultCityName)>-1})[0];$(a).trigger("mouseover")}if(!eic.f.inFrame()){if(now.isIn(new Date(2014,5,20,17,30),new Date(2014,6,7,0))){$(function(){var c='<div id="appendad" style="width: 100%;position: fixed;bottom:0;left:0;height:100px;z-index:9999;"><iframe width=100% height=100 border=0 scrolling="no" frameborder="0" border=0 src="'+eic.c.expoBottomReserveUrl+"\"></iframe><a style=\" width:17px; height:16px; display:block; background:url(http://www.eic.org.cn/f/reserve/expo/bottom/images/bg_yyclose.gif) no-repeat; position:absolute; top:0px; left:50%;margin-left:480px; text-indent:-9999px;\"  href=\"javascript:void(0)\" onclick=\"$('#appendad').hide();$('#timeMachineDiv').css('bottom','0');\">关闭</a></div>";$("body").append(c)})}}})("setExpo");function alt(){alert(123)}function isFunction(a){return !!a&&!a.nodeName&&a.constructor!=String&&a.constructor!=RegExp&&a.constructor!=Array&&/function/i.test(a+"")}if(isFunction("getCookie")&&getCookie("hasshow")==1){document.getElementById("close").innerHTML="<style> body{background:none; padding-top:0px;} </style>";document.getElementById("close").style.display="none"}function closenewyear(){document.getElementById("close").innerHTML="<style> body{background:none; padding-top:0px;} </style>";document.getElementById("close").style.display="none";setCookie("hasshow",1)}var _hmt=_hmt||[];_hmt.push(["_setAccount","3c24574cbeb3062e941a79f2feaac425"]);var kefulink="http://qtt.tq.cn/leavemsg.do?action=leavemsg&uin=9131125&ltype=0&src=bj4";var bjqqzx="http://b.qq.com/webc.htm?new=0&sid=800002346&eid=2188z8p8p8p8p8K8P8Q8x&o=www.eic.org.cn/beijing&q=7&ref=http://www/eic.org.cn/beijing";var rightAdLink="http://www.eic.org.cn/special/jp_kr_expo/";var rightAdImg="http://www.eic.org.cn/public/images/ja_ko.png";var rightAdLink1="http://www.eic.org.cn/special/nz_colleges_consult/";var rightAdLink2="http://www.eic.org.cn/special/eic_my_future/booking.html";var rightAdImg1="http://www.eic.org.cn/public/images/nz.jpg";$(".textcont .text").append("<p>想了解更多内容，请致电启德教育客服服务中心 400-1010-123 （8:00-24:00）</p>");var _bdhmProtocol=(("https:"==document.location.protocol)?"https://":"http://");if(hrefstr.indexOf("studytour.eic.org.cn")>0){if(!eic.c.config.disableBaidu){document.write(unescape("%3Cscript src='"+_bdhmProtocol+"hm.baidu.com/h.js%3Fd728374be359460e4a081a93ccdd70b7' type='text/javascript'%3E%3C/script%3E"))}}else{if(hrefstr.indexOf("college.eic.org.cn")>0){if(!eic.c.config.disableBaidu){document.write(unescape("%3Cscript src='"+_bdhmProtocol+"hm.baidu.com/h.js%3F54df9d27edebf6e564de65469b3bf156' type='text/javascript'%3E%3C/script%3E"))}}else{if(hrefstr.indexOf("special/eic_27th_expo/booking2014.html")>0){if(!eic.c.config.disableBaidu){document.write(unescape("%3Cscript src='"+_bdhmProtocol+"hm.baidu.com/h.js%3F3d2bfd7f60098397d5bd548489f43e8d' type='text/javascript'%3E%3C/script%3E"))}}else{if(!eic.c.config.disableBaidu){document.write(unescape("%3Cscript src='"+_bdhmProtocol+"hm.baidu.com/h.js%3F3c24574cbeb3062e941a79f2feaac425' type='text/javascript'%3E%3C/script%3E"));document.write(unescape("%3Cscript src='"+_bdhmProtocol+"hm.baidu.com/h.js%3Fab3291b2f80c4441d99082b45a387757' type='text/javascript'%3E%3C/script%3E"))}var bd_cpro_rtid="";bd_cpro_rtid="n1RzPs";if(hrefstr.indexOf("/beijing/")>0){bd_cpro_rtid="n1R1n6"}else{if(hrefstr.indexOf("/us/")>0){bd_cpro_rtid="n1Rzr0"}else{if(hrefstr.indexOf("/uk/")>0||hrefstr.indexOf("/ir/")>0){bd_cpro_rtid="n1Rzrf"}else{if(hrefstr.indexOf("/au/")>0||hrefstr.indexOf("/nz/")>0){bd_cpro_rtid="n1R1n0"}else{if(hrefstr.indexOf("/ca/")>0){bd_cpro_rtid="n1R1nf"}else{if(hrefstr.indexOf("/hk/")>0||hrefstr.indexOf("/si/")>0||hrefstr.indexOf("/ma/")>0||hrefstr.indexOf("/ja/")>0||hrefstr.indexOf("/ko/")>0){bd_cpro_rtid="n1R1ns"}else{if(hrefstr.indexOf("/ge/")>0||hrefstr.indexOf("/ne/")>0||hrefstr.indexOf("/fr/")>0||hrefstr.indexOf("/sp/")>0){bd_cpro_rtid="n1R1P0"}}}}}}}document.write('<script type="text/javascript" src="http://cpro.baidu.com/cpro/ui/rt.js"></script>');var google_conversion_label;var google_conversion_id=962482390;var google_conversion_language="en";var google_conversion_format="3";var google_conversion_color="ffffff";var google_conversion_value=0;google_conversion_label="G79dCMLYrgMQ1qH5ygM";if(hrefstr.indexOf("/ir/")>0){google_conversion_label="eVRFCLLLrgMQ1qH5ygM"}else{if(hrefstr.indexOf("/au/")>0){google_conversion_label="aCW8CKrMrgMQ1qH5ygM"}else{if(hrefstr.indexOf("/ge/")>0){google_conversion_label="nijACKLNrgMQ1qH5ygM"}else{if(hrefstr.indexOf("/fr/")>0){google_conversion_label="sNrcCJrOrgMQ1qH5ygM"}else{if(hrefstr.indexOf("/ko/")>0){google_conversion_label="XkiuCJLPrgMQ1qH5ygM"}else{if(hrefstr.indexOf("/ne/")>0){google_conversion_label="tBrSCLyi0AEQ7rKg8wM"}else{if(hrefstr.indexOf("/ca/")>0){google_conversion_label="uYVRCIrQrgMQ1qH5ygM"}else{if(hrefstr.indexOf("/ma/")>0){google_conversion_label="QbKXCILRrgMQ1qH5ygM"}else{if(hrefstr.indexOf("/us/")>0){google_conversion_label="l0MgCLrKrgMQ1qH5ygM"}else{if(hrefstr.indexOf("/ja/")>0){google_conversion_label="E3uNCPrRrgMQ1qH5ygM"}else{if(hrefstr.indexOf("/hk/")>0){google_conversion_label="AZgwCOrTrgMQ1qH5ygM"}else{if(hrefstr.indexOf("/si/")>0){google_conversion_label="hPRvCOLUrgMQ1qH5ygM"}else{if(hrefstr.indexOf("/nz/")>0){google_conversion_label="jjNsCNrVrgMQ1qH5ygM"}else{if(hrefstr.indexOf("/uk/")>0){google_conversion_label="3Vq2CNLWrgMQ1qH5ygM"}else{if(hrefstr.indexOf("/sp/")>0){google_conversion_label="b1bXCPLSrgMQ1qH5ygM"}else{if(hrefstr.indexOf("/NordicEurope/")>0){google_conversion_label="qLUTCMrXrgMQ1qH5ygM"}else{if(hrefstr.indexOf("/beijing/")>0){google_conversion_label="_yNBCLrZrgMQ1qH5ygM"}else{if(hrefstr.indexOf("/shanghai/")>0){google_conversion_label="uln4CKrbrgMQ1qH5ygM"}else{if(hrefstr.indexOf("/jinan/")>0){google_conversion_label="4iGuCJrdrgMQ1qH5ygM"}else{if(hrefstr.indexOf("/guangdong/")>0){google_conversion_label="WqVpCKLcrgMQ1qH5ygM"}else{if(hrefstr.indexOf("/wuhan/")>0){google_conversion_label="9vvSCJLergMQ1qH5ygM"}else{if(hrefstr.indexOf("/shenzhen/")>0){google_conversion_label="E6RGCIrfrgMQ1qH5ygM"}}}}}}}}}}}}}}}}}}}}}}}}}if(!_gaq){if(hrefstr.indexOf("/eic_three_privileges1/")>1||hrefstr.indexOf("/eic_three_privileges2/")>1){}else{var _gaq=_gaq||[];var pluginUrl="//www.google-analytics.com/plugins/ga/inpage_linkid.js";_gaq.push(["_require","inpage_linkid",pluginUrl]);_gaq.push(["_setAccount","UA-9380624-1"]);_gaq.push(["_setDomainName","eic.org.cn"]);_gaq.push(["_addOrganic","baidu","word"]);_gaq.push(["_addOrganic","sogou","query"]);_gaq.push(["_addOrganic","youdao","q"]);_gaq.push(["_addOrganic","soso","w"]);_gaq.push(["_addOrganic","sina","q"]);_gaq.push(["_addOrganic","vnet","kw"]);_gaq.push(["_trackPageview"])}}(function(){var d="/liuyuezhan/";var c=function(){return[document.getElementById("looyu_dom_1"),document.getElementById("doyoo_mon_accept"),document.getElementById("contactR"),document.getElementById("looyuLeftBtn"),document.getElementById("looyuMidBtn"),document.getElementById("looyuRightBtn")]};window._hmt=window._hmt||[];var b=function(g,f,e){if(g.addEventListener){g.addEventListener(f,e,false)}else{if(g.attachEvent){g.attachEvent("on"+f,e)}}};var a=function(e,f){b(e,"mouseup",function(){window._hmt.push(["_trackPageview",d+f])})};b(window,"load",function(){var f=c();for(var e=f.length-1;e>=0;e--){f[e]&&a(f[e],e+1)}})})();function judgeyouxiajiao(){return true}if(false){$(".looyuLink").each(function(b,a){$(a).attr("href",eic.c.looyuLink)});if(!eic.c.config.useSpLooyu&&!eic.f.forbidLooyu()&&!eic.c.config.disableLooyu&&hrefstr.indexOf("/looyu/")==-1){if(!preventPopup()){eic.f.setSpecialLooyu({i:"http://expo.eic.org.cn/images/looyu/looyu_right.png",t:-58},null,null,true)}if(!preventPopup()&&!eic.inner.getSysLooyu()){$("head").append('<link rel="stylesheet" type="text/css" href="http://www.eic.org.cn/public/style/v2/css/eic_index.css" />');$("body").append('<div class="adBG" style="z-index:999;display:none;"><div style="width:100%;height:100%"><a class="_looyuTrigger2" onclick="'+eic.c.openLooyu+'" style="width:100%;height:100%;display:block;"   href="javascript:void(0);" target="_blank" ></a> </div><a href="javascript:void(0);" class="yclose">关闭</a><div>');if(window.location.pathname.indexOf("/ca/")>=0||window.location.pathname.indexOf("/ca_")>=0){$(".adBG").attr("style","background:url('http://www.eic.org.cn/Images/ca_ask1.jpg')").show();$(".yclose").attr("style","right:395px;top:250px;width:100px;")}}$(function(){var a=0;if($.cookie(window.location.pathname)==null){$(".adBG").show()}else{if((new Date().getTime()-parseInt($.cookie(window.location.pathname)))>30000){$(".adBG").show();$.cookie(window.location.pathname,new Date().getTime())}else{setTimeout(function(){$(".adBG").show();$.cookie(window.location.pathname,new Date().getTime())},(new Date().getTime()-parseInt($.cookie(window.location.pathname))))}}$(".yclose").click(function(){$(".adBG").hide();setTimeout(function(){$.cookie(window.location.pathname,new Date().getTime());switch(a){case 0:if(window.location.pathname.indexOf("/ca")>=0){$(".adBG").attr("style","background:url('http://www.eic.org.cn/Images/ca_ask2.jpg')").show();$(".yclose").attr("style","right:395px;top:250px;width:100px;");a=2}else{if("http://www.eic.org.cn/http://www.eic.org.cn/beijing/http://www.eic.org.cn/guangzhou/http://www.eic.org.cn/xiamen/http://www.eic.org.cn/wuhan/".indexOf(window.location.href)>-1){$(".adBG").attr("style","background:url('http://www.eic.org.cn/Images/ca_ask1.jpg')").show();$(".yclose").attr("style","right:395px;top:250px;width:100px;");a=1}else{$(".adBG").attr("style","background:url('http://www.eic.org.cn/Images/bg_ask2.jpg')").show();$(".yclose").attr("style","right:395px;top:285px;width:100px;");a=2}}break;case 1:if(window.location.pathname.indexOf("/ca")>=0||window.location.pathname.indexOf("/ca_")>=0){$(".adBG").attr("style","background:url('http://www.eic.org.cn/Images/ca_ask2.jpg')").show();$(".yclose").attr("style","right:395px;top:250px;width:100px;")}else{$(".adBG").attr("style","background:url('http://www.eic.org.cn/Images/bg_ask2.jpg')").show();$(".yclose").attr("style","right:395px;top:285px;width:100px;")}a=2;break;case 2:if(window.location.pathname.indexOf("/ca")>=0||window.location.pathname.indexOf("/ca_")>=0){$(".adBG").attr("style","background:url('http://www.eic.org.cn/Images/ca_ask3.jpg')").show();$(".yclose").attr("style","right:395px;top:270px;width:100px;")}else{$(".adBG").attr("style","background:url('http://www.eic.org.cn/Images/bg_ask1.jpg')").show();$(".yclose").attr("style","right:395px;top:255px;width:100px;")}a=3;break;case 3:$(".adBG").html('<div style="width:100%;height:100%"><a class="_looyuTrigger2" style="width:100%;height:100%;display:block;"   href="http://qna.eic.org.cn/online_consultation/" target="_blank" ></a> </div><a href="javascript:void(0);" class="yclose">关闭</a>');$(".adBG").attr("style","background:url('http://www.eic.org.cn/Images/bg_ask3.jpg')").show();$(".yclose").attr("style","right:395px;top:255px;width:100px;");$(".yclose").click(function(){$(".adBG").hide()});a=4;break;default:break}},30000);return false});eic.f.setGa($("._looyuTrigger2"))})}};