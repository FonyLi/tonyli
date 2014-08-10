function len(s)
{var l=0;var a=s.split("");for(var i=0;i<a.length;i++)
{if(a[i].charCodeAt(0)<299)
{l++}else
{l+=2}}return l}function checkcode()
{var is27expo=jQuery("#newname").val().indexOf("第27届启德国际教育展")>-1;if(jQuery("#name").val()==""&&is27expo)
{alert("启德留学申请为实名制，请准确填写您的姓名。");jQuery("#name").focus();return false}else
{if(jQuery("#name").val()=="")
{if(jQuery("#name").val().length>50)
{alert(prefix+"请正确填写您的姓名");jQuery("#name").focus();return false}}}if(jQuery("#telephone").val()=="")
{alert("方便我们与您确认申请信息，请准确填写您的手机号。");jQuery("#telephone").focus();return false}else
{if(jQuery("#telephone").val()!="")
{var reg=/^(0?1[3,5,8](\d)
{9}(-(\d)
{1,6})?|0(\d)
{3}-(\d)
{6,8}(-(\d)
{1,6})?|0(\d)
{2}-(\d)
{8}(-(\d)
{1,6})?|00(\d)
{11,20})$/;if(!reg.test(jQuery("#telephone").val()))
{alert("方便我们与您确认报名信息，请准确填写您的手机号。");jQuery("#telephone").focus();return false}else
{if(jQuery("#telephone").val().length>13)
{alert("方便我们与您确认报名信息，请准确填写您的手机号。");jQuery("#telephone").focus();return false}}}}if(jQuery("#state").val()=="0"&&is27expo)
{alert("为帮您制定合理的留学规划，请选择您的留学目的地。");jQuery("#state").focus();return false}else
{if(jQuery("#state").val()=="0")
{alert(prefix+"请填写您的意向留学国家");jQuery("#state").focus();return false}}var prefix="为确保成功预约，";if(jQuery("#city").val()=="0"&&is27expo)
{alert("为确保申请成功，请选择您所在的城市。");jQuery("#city").focus();return false}else
{if(jQuery("#city").val()=="0")
{alert(prefix+"请选择您想预约的活动城市");jQuery("#city").focus();return false}}if(jQuery("#website").val()=="0"&&jQuery("#newname").val()=="第27届启德国际教育展")
{alert("为确保报名成功，请选择获得本届教育展的网络信息来源。");jQuery("#website").focus();return false}if(jQuery("#engine").val()=="0"&&jQuery("#newspaper").val()=="0"&&jQuery("#website").val()=="0"&&jQuery("#schoolsPublicity").val()=="0"&&jQuery("#recommended").val()=="0")
{alert("请选择获得本次活动信息的途径！");jQuery("#engine").focus();return false}return true}function addUsersMess()
{function getvalue(selector,def)
{var jqobj=jQuery(selector);def=(def==null?"":def);var und=(jqobj==null||jqobj.length===0);return und?def:jqobj.val()}if(checkcode())
{var postParam="";var TopCountryid="";var SpecialBookCourse="";var SpecialBookEnglishSort="";var recommended1=0;var schoolsPublicity1=0;var fromurl="";var promotionCode="";SpecialBookEnglishSort+=getvalue("#newspaper");SpecialBookCourse+=getvalue("#engine");TopCountryid+=getvalue("#state")+",";recommended1=getvalue("#recommended",0);schoolsPublicity1=getvalue("#schoolsPublicity",0);fromurl=getvalue("#fromurl");promotionCode=getvalue("#promotionCode");var url="/f/consulting.php";jQuery.ajax(
{type:"post",url:url,data:
{type:"submit",name:jQuery("#name").val(),sex:"",eduname:jQuery("#eduname").val(),newname:jQuery("#newname").val(),whether:"",telephone:jQuery("#telephone").val(),email:"",cname:"",qq:"",cityid:jQuery("#city").val(),state:TopCountryid,website:getvalue("#website"),radio:"",schoolRecommendation:"",companyRecommended:"",schoolsPublicity:schoolsPublicity1,engine:SpecialBookCourse,ad:SpecialBookEnglishSort,recommended:recommended1,notice:"",activities:"",metroAd:"",outdoorAd:"",fromurl:fromurl,need_gift1:getvalue("#need_gift1"),promotionCode:promotionCode},dataType:"text",success:function(data)
{if(data)
{alert("恭喜您提交成功！ 我们将在3个工作日内与您电话确认申请信息，如有疑问，欢迎拨打全国咨询热线400—1010—123。");if(typeof(parent.expoReserveDone)==="function")
{parent.expoReserveDone()}}else
{alert("预约失败，请联系我们的客服。客服电话 400 1010 123")}}})}}function getId(name)
{return document.getElementById(name)}function getShow(type)
{if(type=="show")
{var msg="Waiting...";jQuery("#login_right").html(msg);jQuery.ajax(
{type:"POST",url:"/f/consulting.php?type=show&date=2012-05-14",cache:false,success:function(msg)
{jQuery("#apply_show").html(msg)}})}else
{var msg="Waiting...";var page=getId("pageNum").value;jQuery("#login_right").html(msg);jQuery.ajax(
{type:"POST",url:"/f/consulting.php?type=show&date=2012-05-14&page="+page,cache:false,success:function(msg)
{jQuery("#apply_show").html(msg)}})}}function callbackAdduserMess()
{if(addxmlHTTP.readyState==4)
{if(addxmlHTTP.status==200)
{jQuery.getScript("/special/edu_expo_2012/js/20120229jyz.js");alert("您已成功预约！您的预约号为"+addxmlHTTP.responseText);jQuery.getScript("/special/edu_expo_2012/js/20120229jyz.js")}else
{alert("提交错误，请重新填写提交")}}};