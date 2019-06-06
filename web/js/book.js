var Cookie={get:function(n){var dc="; "+ document.cookie+"; ";var coo=dc.indexOf("; "+ n+"=");if(coo!=-1){var s=dc.substring(coo+ n.length+ 3,dc.length);return unescape(s.substring(0,s.indexOf("; ")));}else{return null;}},set:function(name,value,expires,path,domain,secure){var expDays=expires*24*60*60*3;var expDate=new Date();expDate.setTime(expDate.getTime()+ expDays);var expString=expires?"; expires="+ expDate.toGMTString():"";var pathString="; path="+(path||"/");var domain=domain?"; domain="+ domain:"";document.cookie=name+"="+ escape(value)+ expString+ domain+ pathString+(secure?"; secure":"");},del:function(n){var exp=new Date();exp.setTime(exp.getTime()- 1);var cval=this.get(n);if(cval!=null)document.cookie=n+"="+ cval+";expires="+ exp.toGMTString();}}
function readbook(bookid){$.get("/modules/article/articlevisit.php?id="+ bookid);}
function vote_nomsg(aid){$.get('/modules/article/uservote.php?id='+ aid+'&ajax_request=1');}
function addBookmark(title,url){if(!title){title=document.title}
    if(!url){url=window.location.href}
    if(window.sidebar){window.sidebar.addPanel(title,url,"");}else if(document.all){window.external.AddFavorite(url,title);}else if(window.opera||window.print){alert('ä¯ÀÀÆ÷²»Ö§³Ö£¬ÇëÊ¹ÓÃCtrl + D ÊÕ²Ø£¡');return true;}}
function killErrors(){return true;}
window.onerror=killErrors;var jieqiUserInfo=new Array();jieqiUserInfo['jieqiUserId']=0;jieqiUserInfo['jieqiUserUname']='';jieqiUserInfo['jieqiUserName']='';jieqiUserInfo['jieqiUserGroup']=0;if(document.cookie.indexOf('jieqiUserInfo')>=0){var cookieInfo=get_cookie_value('jieqiUserInfo');start=0;offset=cookieInfo.indexOf(',',start);while(offset>0){tmpval=cookieInfo.substring(start,offset);tmpidx=tmpval.indexOf('=');if(tmpidx>0){tmpname=tmpval.substring(0,tmpidx);tmpval=tmpval.substring(tmpidx+ 1,tmpval.length);jieqiUserInfo[tmpname]=tmpval}
    start=offset+ 1;if(offset<cookieInfo.length){offset=cookieInfo.indexOf(',',start);if(offset==-1)offset=cookieInfo.length}else{offset=-1}}}
function get_cookie_value(Name){var search=Name+"=";var returnvalue="";if(document.cookie.length>0){offset=document.cookie.indexOf(search);if(offset!=-1){offset+=search.length;end=document.cookie.indexOf(";",offset);if(end==-1)end=document.cookie.length;returnvalue=unescape(document.cookie.substring(offset,end))}}
    return returnvalue}
var isLogin=jieqiUserInfo['jieqiUserId']>0;
function login(){
    if(isLogin){
    document.writeln("<li><a href=\"\/modules\/article\/bookcase.php\" title='登录'><i class='fa fa-book fa-fw'></i>登录<\/a></li>")
    document.writeln('<li class="dropdown"> <a class="dropdown-toggle" href="javascript:;" data-toggle="dropdown"><i class="fa fa-user fa-fw"></i>'+ unescape(jieqiUserInfo['jieqiUserName_un'])+'<span class="caret"></span></a> <ul class="dropdown-menu" role="menu">');
    document.writeln("<li><a href=\"\/userdetail.php\" title='登录'><i class='fa fa-info fa-fw'></i>登录<\/a></li>")
    document.writeln("<li><a href=\"\/message.php\" title='信息'><i class='fa fa-envelope fa-fw'></i>信息<\/a></li>")
    document.writeln("<li><a href=\"\/logout.php\" title='注销'><i class='fa fa-power-off fa-fw'></i>注销<\/a></li>")
    document.writeln('</ul></li>');
    }
    else{
        document.writeln("<li><a href=\"login.php\">登录</a></li><li><a href=\"register.php\">注册</a></li>")
    }
}
var isIE=!!window.ActiveXObject;var isIE6=isIE&&!window.XMLHttpRequest;var isIE8=isIE&&!!document.documentMode;var isIE7=isIE&&!isIE6&&!isIE8;function tip_ie7(){if(isIE&&(isIE6||isIE7||isIE8)){document.writeln("<div class=\"tip-browser-upgrade\">");document.writeln("    ÄãÕýÔÚÊ¹ÓÃIEµÍ¼¶ä¯ÀÀÆ÷£¬Èç¹ûÄãÏëÓÐ¸üºÃµÄÔÄ¶ÁÌåÑé£¬<br />Ç¿ÁÒ½¨ÒéÄúÁ¢¼´ <a class=\"blue\" href=\"http://windows.microsoft.com/zh-cn/internet-explorer/download-ie\" target=\"_blank\" rel=\"nofollow\">Éý¼¶IEä¯ÀÀÆ÷</a> »òÕßÓÃ¸ü¿ì¸ü°²È«µÄ <a class=\"blue\" href=\"https://www.google.com/intl/zh-CN/chrome/browser/?hl=zh-CN\" target=\"_blank\" rel=\"nofollow\">¹È¸èä¯ÀÀÆ÷Chrome</a> ¡£");document.writeln("</div>");}}
function is_mobile(){var regex_match=/(nokia|iphone|android|motorola|^mot-|softbank|foma|docomo|kddi|up.browser|up.link|htc|dopod|blazer|netfront|helio|hosin|huawei|novarra|CoolPad|webos|techfaith|palmsource|blackberry|alcatel|amoi|ktouch|nexian|samsung|^sam-|s[cg]h|^lge|ericsson|philips|sagem|wellcom|bunjalloo|maui|symbian|smartphone|midp|wap|phone|windows ce|iemobile|^spice|^bird|^zte-|longcos|pantech|gionee|^sie-|portalmmm|jigs browser|hiptop|^benq|haier|^lct|operas*mobi|opera*mini|320x320|240x320|176x220)/i;var u=navigator.userAgent;if(null==u){return true;}
    var result=regex_match.exec(u);if(null==result){return false}else{return true}}
function foot(){var date=new Date();var year=date.getFullYear();document.writeln("<footer>");if(!is_mobile()){document.writeln("<p>±¾Õ¾Ð¡ËµÎª×ªÔØ×÷Æ·£¬ËùÓÐÕÂ½Ú¾ùÓÉÍøÓÑÉÏ´«£¬×ªÔØÖÁ±¾Õ¾Ö»ÊÇÎªÁËÐû´«±¾ÊéÈÃ¸ü¶à¶ÁÕßÐÀÉÍ¡£</p>");}
    document.writeln("<p>Copyright &#169; "+year+" <a href=\"http://www.yisvip.com/\">svip</a>("+location.host+") All Rights Reserved. »¦ICP±¸19819998ºÅ</p>");document.writeln("</footer>");$("[data-toggle=tooltip]").tooltip();$("[data-toggle=popover]").popover();$("#bookIntroMore").off("click").on("click",function(){var that=$(this);var isExpand=that.data("isExpand");var expandHtml='Õ¹¿ª<i class="fa fa-angle-double-down fa-fw"></i>';var shrinkHtml='ÊÕÆð<i class="fa fa-angle-double-up fa-fw"></i>';var normalHeight=200;function setNormal(){that.html(expandHtml);$("#bookIntro").height(normalHeight);that.data("isExpand","no");}
        function setExpand(){that.html(shrinkHtml);$("#bookIntro").height("auto");that.data("isExpand","yes");}
        isExpand=="yes"?setNormal():setExpand();});backToTop();tj();if(is_mobile()){_BottomAll();}}
function ErrorLink(articlename){var error_url='/newmessage.php?tosys=1&title=¡¶'+ articlename+'¡·´ß¸ü±¨´í&content='+ location.href;$("#errorlink,.errorlink").attr('href',error_url);}
function ReadKeyEvent(){var index_page=$("#linkIndex").attr("href");var prev_page=$("#linkPrev").attr("href");var next_page=$("#linkNext").attr("href");function jumpPage(){var event=document.all?window.event:arguments[0];if(event.keyCode==37)document.location=prev_page;if(event.keyCode==39)document.location=next_page;if(event.keyCode==13)document.location=index_page;}
    document.onkeydown=jumpPage;}
function showMsg(msg){isLogin=isLogin&&msg.indexOf("ÄúÐèÒªµÇÂ¼")<=0;if(!isLogin){if(is_mobile()){if(confirm("¶Ô²»Æð£¬ÄúÐèÒªµÇÂ¼²ÅÄÜÊ¹ÓÃ±¾¹¦ÄÜ£¡µã»÷È·¶¨Á¢¼´µÇÂ¼¡£")){window.location.href="/login.php?jumpurl="+location.href;}}else{var loginConfirmBox=dialog.normal({id:"-dialog-confirm",title:"<i class='fa fa-exclamation-triangle fa-fw text-warning'></i>Î´µÇÂ¼ÌáÊ¾",html:"¶Ô²»Æð£¬ÄúÐèÒªµÇÂ¼²ÅÄÜÊ¹ÓÃ±¾¹¦ÄÜ£¡µã»÷È·¶¨Á¢¼´µÇÂ¼¡£",button:2,callback:function(){window.location.href="/login.php?jumpurl="+location.href;}});loginConfirmBox.show();}
    return false;}
    if(is_mobile()){alert(msg.replace(/<br[^<>]*>/g,'\n'));}else{dialog.alert(msg);}}
function BookVote(article_id){$.get('/modules/article/uservote.php?id='+ article_id+'&ajax_request=1',function(data){showMsg(data);});}
function BookCaseAdd(article_id){var url='/modules/article/addbookcase.php?bid='+ article_id+'&ajax_request=1';$.get(url,function(res){showMsg(res);});}
function BookCaseMark(article_id,chapter_id){var url='/modules/article/addbookcase.php?bid='+ article_id+'&cid='+ chapter_id+'&ajax_request=1';$.get(url,function(res){showMsg(res);});}
function backToTop(){document.writeln("<div class=\"back-to-top\" id=\"back-to-top\" title='·µ»Ø¶¥²¿'><i class='fa fa-chevron-up'></i></div>");var left=($(document).width()- $(".body-content").width())/ 2 + $(".body-content").width() + 10;
    if(is_mobile()){$("#back-to-top").css({right:10,bottom:"30%"});}else{$("#back-to-top").css({left:left});}
    $(window).resize(function(){if($(document).width()- $(".body-content").width()<100){$("#back-to-top").hide();}else{$("#back-to-top").show();var left=($(document).width()- $(".body-content").width())/ 2 + $(".body-content").width() + 10;
        if(is_mobile()){$("#back-to-top").css({right:10,bottom:"30%"});}else{$("#back-to-top").css({left:left});}}});var isie6=window.XMLHttpRequest?false:true;function newtoponload(){var c=$("#back-to-top");function b(){var a=document.documentElement.scrollTop||window.pageYOffset||document.body.scrollTop;if(a>100){if(isie6){c.hide();clearTimeout(window.show);window.show=setTimeout(function(){var d=document.documentElement.scrollTop||window.pageYOffset||document.body.scrollTop;if(d>0){c.fadeIn(100);}},300)}else{c.fadeIn(100);}}else{c.fadeOut(100);}}
        if(isie6){c.style.position="absolute"}
        window.onscroll=b;b()}
    if(window.attachEvent){window.attachEvent("onload",newtoponload)}else{window.addEventListener("load",newtoponload,false)}
    document.getElementById("back-to-top").onclick=function(){window.scrollTo(0,0)};}

function bd_push(){var bp=document.createElement('script');bp.src='//push.zhanzhang.baidu.com/push.js';var s=document.getElementsByTagName("script")[0];s.parentNode.insertBefore(bp,s);}
function zn(){}
function tj(){}