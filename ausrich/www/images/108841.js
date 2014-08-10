
















if(typeof doyoo=='undefined' || !doyoo){
var d_genId=function(){
    var id ='',ids='0123456789abcdef';
    for(var i=0;i<34;i++){ id+=ids.charAt(Math.floor(Math.random()*16));  }  return id;
};
var doyoo={
env:{
secure:false,
mon:'http://m154.looyu.com/monitor',
chat:'http://ali064.looyu.com/chat',
file:'http://static.doyoo.net/110402',
compId:32383,
confId:108841,
vId:d_genId(),
lang:'',
fixFlash:1,
subComp:0
}

, monParam:{
index:-1,

style:{mbg:'http://cdn.eic.org.cn/image/looyu/empty.png',mh:0,mw:0,
elepos:'0 0 0 0 0 0 0 0 244 55 163 55 595 320 31 30',
mbabg:'',
mbdbg:''},

title:'\u5728\u7ebf\u5ba2\u670d',
text:'<p>&nbsp;</p>',
auto:30,
group:'35315',
start:'00:00',
end:'24:00',
mask:false,
status:true,
fx:0,
mini:1,
pos:0,
offShow:1,
loop:300,
autoHide:0,
hidePanel:0,
miniStyle:1,
monHideStatus:[0,0,0],
monShowOnly:''
}



,sniffer:{
ids:'\u5728\u7ebf\u54a8\u8be2,\u5728\u7ebf\u7b54\u7591,\u7f51\u4e0a\u54a8\u8be2,\u7f51\u4e0a\u7b54\u7591,\u4e13\u5bb6\u54a8\u8be2,\u4e13\u5bb6\u7b54\u7591',
gids:'35315,35315,35315,35315,35315,35315'
}

};



document.write('<div id="doyoo_monitor"></div>');

document.write('<div id="doyoo_share" style="display:none;"></div>');
document.write('<lin'+'k rel="stylesheet" type="text/css" href="http://static.doyoo.net/110402/looyu.css?140702"></li'+'nk>');
document.write('<scr'+'ipt type="text/javascript" src="http://static.doyoo.net/110402/looyu.js?140702"></scr'+'ipt>');

}

