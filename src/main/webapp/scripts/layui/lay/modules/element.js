/** layui-v1.0.9_rls MIT License By http://www.layui.com */
 ;layui.define("jquery",function(i){"use strict";var t=layui.jquery,a=(layui.hint(),layui.device()),e="layui-this",l="layui-show",n=function(){this.config={}};n.prototype.set=function(i){var a=this;return t.extend(!0,a.config,i),a},n.prototype.on=function(i,t){return layui.onevent("element",i,t)},n.prototype.tabAdd=function(i,a){var e=t(".layui-tab[lay-filter="+i+"]"),l=e.children(".layui-tab-title"),n=e.children(".layui-tab-content"),s=!1===a.close?" no-close":"";return l.append('<li lay-id="'+(a.id||"")+'"'+s+">"+(a.title||"unnaming")+"</li>"),n.append('<div class="layui-tab-item">'+(a.content||"")+"</div>"),u.hideTabMore(!0),u.tabAuto(),this},n.prototype.tabDelete=function(i,a){var e=t(".layui-tab[lay-filter="+i+"]"),l=e.children(".layui-tab-title"),n=l.find('>li[lay-id="'+a+'"]');return u.tabDelete(null,n),this},n.prototype.tabChange=function(i,a){var e=t(".layui-tab[lay-filter="+i+"]"),l=e.children(".layui-tab-title"),n=l.find('>li[lay-id="'+a+'"]');return u.tabClick(null,null,n),this},n.prototype.progress=function(i,a){var e="layui-progress",l=t("."+e+"[lay-filter="+i+"]"),n=l.find("."+e+"-bar"),s=n.find("."+e+"-text");return n.css("width",a),s.text(a),this};var s="layui-nav-tree",o="layui-nav-child",c="layui-nav-more",r="layui-anim layui-anim-upbit",u={tabClick:function(i,a,n){var s=n||t(this),a=a||s.parent().children("li").index(s),o=s.parents(".layui-tab").eq(0),c=o.children(".layui-tab-content").children(".layui-tab-item"),r=o.attr("lay-filter");s.addClass(e).siblings().removeClass(e),c.eq(a).addClass(l).siblings().removeClass(l),layui.event.call(this,"element","tab("+r+")",{elem:o,index:a})},tabDelete:function(i,a){var l=a||t(this).parent(),n=l.index(),s=l.parents(".layui-tab").eq(0),o=s.children(".layui-tab-content").children(".layui-tab-item");l.hasClass(e)&&(l.next()[0]?u.tabClick.call(l.next()[0],null,n+1):l.prev()[0]&&u.tabClick.call(l.prev()[0],null,n-1)),l.remove(),o.eq(n).remove(),setTimeout(function(){u.tabAuto()},50)},tabAuto:function(){var i="layui-tab-bar",e=this;t(".layui-tab").each(function(){var l=t(this),n=l.attr("lay-filter"),s=l.children(".layui-tab-title"),o=(l.children(".layui-tab-content").children(".layui-tab-item"),'lay-stope="tabmore"'),c=t('<span class="layui-unselect layui-tab-bar" '+o+"><i "+o+' class="layui-icon">&#xe61a;</i></span>');if(e===window&&8!=a.ie&&u.hideTabMore(!0),l.attr("lay-allowClose")&&s.find("li").each(function(i){var a=t(this);if(!a.find(".layui-tab-close")[0]&&void 0===a.attr("no-close")){var e=t('<i class="layui-icon layui-unselect layui-tab-close">&#x1006;</i>');e.on("click",function(t){var e=layui.getEvent("element","closetab("+n+")");(null==e||e({elem:a,id:a.attr("lay-id"),content:a.parent().next().find(">div:eq("+i+")")}))&&u.tabDelete(t,a)}),a.append(e)}}),s.prop("scrollWidth")>s.outerWidth()+1){if(s.find("."+i)[0])return;s.append(c),l.attr("overflow",""),c.on("click",function(i){s[this.title?"removeClass":"addClass"]("layui-tab-more"),this.title=this.title?"":"收缩"})}else s.find("."+i).remove(),l.removeAttr("overflow")})},hideTabMore:function(i){var a=t(".layui-tab-title");!0!==i&&"tabmore"===t(i.target).attr("lay-stope")||(a.removeClass("layui-tab-more"),a.find(".layui-tab-bar").attr("title",""))},rightMenu:function(i){},clickThis:function(){var i=t(this),a=i.parents(".layui-nav"),l=a.attr("lay-filter");i.find("."+o)[0]||(a.find("."+e).removeClass(e),i.addClass(e),layui.event.call(this,"element","nav("+l+")",i))},clickChild:function(){var i=t(this),a=i.parents(".layui-nav"),l=a.attr("lay-filter");a.find("."+e).removeClass(e),i.addClass(e),layui.event.call(this,"element","nav("+l+")",i)},showChild:function(){var i=t(this),a=i.parents(".layui-nav"),e=i.parent(),l=i.siblings("."+o);a.hasClass(s)&&(l.removeClass(r),e["none"===l.css("display")?"addClass":"removeClass"]("layui-nav-itemed"))},collapse:function(){var i=t(this),a=i.find(".layui-colla-icon"),e=i.siblings(".layui-colla-content"),n=i.parents(".layui-collapse").eq(0),s=n.attr("lay-filter"),o="none"===e.css("display");if("string"==typeof n.attr("lay-accordion")){var c=n.children(".layui-colla-item").children("."+l);c.siblings(".layui-colla-title").children(".layui-colla-icon").html("&#xe602;"),c.removeClass(l)}e[o?"addClass":"removeClass"](l),a.html(o?"&#xe61a;":"&#xe602;"),layui.event.call(this,"element","collapse("+s+")",{title:i,content:e,show:o})}};n.prototype.init=function(i){var e={tab:function(){u.tabAuto.call({})},nav:function(){var i,e,n,d=function(u,d){var y=t(this),h=y.find("."+o);d.hasClass(s)?u.css({top:y.position().top,height:y.children("a").height(),opacity:1}):(h.addClass(r),u.css({left:y.position().left+parseFloat(y.css("marginLeft")),top:y.position().top+y.height()-5}),i=setTimeout(function(){u.css({width:y.width(),opacity:1})},a.ie&&a.ie<10?0:200),clearTimeout(n),"block"===h.css("display")&&clearTimeout(e),e=setTimeout(function(){h.addClass(l),y.find("."+c).addClass(c+"d")},300))};t(".layui-nav").each(function(){var a=t(this),r=t('<span class="layui-nav-bar"></span>'),y=a.find(".layui-nav-item");a.find(".layui-nav-bar")[0]||(a.append(r),y.on("mouseenter",function(){d.call(this,r,a)}).on("mouseleave",function(){a.hasClass(s)||(clearTimeout(e),e=setTimeout(function(){a.find("."+o).removeClass(l),a.find("."+c).removeClass(c+"d")},300))}),a.on("mouseleave",function(){clearTimeout(i),n=setTimeout(function(){a.hasClass(s)?r.css({height:0,top:r.position().top+r.height()/2,opacity:0}):r.css({width:0,left:r.position().left+r.width()/2,opacity:0})},200)})),y.each(function(){var i=t(this),a=i.find("."+o);if(a[0]&&!i.find("."+c)[0]){i.children("a").append('<span class="'+c+'"></span>')}i.off("click",u.clickThis).on("click",u.clickThis),i.children("a").off("click",u.showChild).on("click",u.showChild),a.children("dd").off("click",u.clickChild).on("click",u.clickChild)})})},breadcrumb:function(){t(".layui-breadcrumb").each(function(){var i=t(this),a=i.attr("lay-separator")||">",e=i.find("a");e.find(".layui-box")[0]||(e.each(function(i){i!==e.length-1&&t(this).append('<span class="layui-box">'+a+"</span>")}),i.css("visibility","visible"))})},progress:function(){var i="layui-progress";t("."+i).each(function(){var a=t(this),e=a.find(".layui-progress-bar"),l=e.attr("lay-percent");e.css("width",l),a.attr("lay-showPercent")&&setTimeout(function(){var t=Math.round(e.width()/a.width()*100);t>100&&(t=100),e.html('<span class="'+i+'-text">'+t+"%</span>")},350)})},collapse:function(){t(".layui-collapse").each(function(){t(this).find(".layui-colla-item").each(function(){var i=t(this),a=i.find(".layui-colla-title"),e=i.find(".layui-colla-content"),l="none"===e.css("display");a.find(".layui-colla-icon").remove(),a.append('<i class="layui-icon layui-colla-icon">'+(l?"&#xe602;":"&#xe61a;")+"</i>"),a.off("click",u.collapse).on("click",u.collapse)})})}};return layui.each(e,function(i,t){t()})};var d=new n,y=t(document);d.init();y.on("click",".layui-tab-title li",u.tabClick),y.on("click",u.hideTabMore),t(window).on("resize",u.tabAuto),i("element",function(i){return d.set(i)})});