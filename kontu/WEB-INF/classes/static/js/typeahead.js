/*!
 * jQuery Typeahead
 * Copyright (C) 2016 RunningCoder.org
 * Licensed under the MIT license
 *
 * @author Tom Bertrand
 * @version 2.7.1 (2016-9-30)
 * @link http://www.runningcoder.org/jquerytypeahead/
 */
!function(t){"function"==typeof define&&define.amd?define("jquery-typeahead",["jquery"],function(e){return t(e)}):"object"==typeof module&&module.exports?module.exports=function(e,i){return void 0===e&&(e="undefined"!=typeof window?require("jquery"):require("jquery")(i)),t(e)}:t(jQuery)}(function(t){"use strict";window.Typeahead={version:"2.7.1"};var e={input:null,minLength:2,maxItem:8,dynamic:!1,delay:300,order:null,offset:!1,hint:!1,accent:!1,highlight:!0,group:!1,groupOrder:null,maxItemPerGroup:null,dropdownFilter:!1,dynamicFilter:null,backdrop:!1,backdropOnFocus:!1,cache:!1,ttl:36e5,compression:!1,suggestion:!1,searchOnFocus:!1,resultContainer:null,generateOnLoad:null,mustSelectItem:!1,href:null,display:["display"],template:null,groupTemplate:null,correlativeTemplate:!1,emptyTemplate:!1,cancelButton:!0,loadingAnimation:!0,filter:!0,matcher:null,source:null,callback:{onInit:null,onReady:null,onShowLayout:null,onHideLayout:null,onSearch:null,onResult:null,onLayoutBuiltBefore:null,onLayoutBuiltAfter:null,onNavigateBefore:null,onNavigateAfter:null,onMouseEnter:null,onMouseLeave:null,onClickBefore:null,onClickAfter:null,onSendRequest:null,onReceiveRequest:null,onPopulateSource:null,onCacheSave:null,onSubmit:null,onCancel:null},selector:{container:"typeahead__container",result:"typeahead__result",list:"typeahead__list",group:"typeahead__group",item:"typeahead__item",empty:"typeahead__empty",display:"typeahead__display",query:"typeahead__query",filter:"typeahead__filter",filterButton:"typeahead__filter-button",dropdown:"typeahead__dropdown",dropdownItem:"typeahead__dropdown-item",button:"typeahead__button",backdrop:"typeahead__backdrop",hint:"typeahead__hint",cancelButton:"typeahead__cancel-button"},debug:!1},i=".typeahead",o={from:"ãàáäâẽèéëêìíïîõòóöôùúüûñç",to:"aaaaaeeeeeiiiiooooouuuunc"},n=~window.navigator.appVersion.indexOf("MSIE 9."),s=~window.navigator.appVersion.indexOf("MSIE 10"),r=~window.navigator.userAgent.indexOf("Trident")&&~window.navigator.userAgent.indexOf("rv:11"),a=function(t,e){this.rawQuery=t.val()||"",this.query=t.val()||"",this.namespace="."+i,this.tmpSource={},this.source={},this.isGenerated=null,this.generatedGroupCount=0,this.groupCount=0,this.groupBy="group",this.groups=[],this.result={},this.groupTemplate="",this.resultHtml=null,this.resultCount=0,this.resultCountPerGroup={},this.options=e,this.node=t,this.namespace="."+this.helper.slugify.call(this,t.selector)+i,this.container=null,this.resultContainer=null,this.item=null,this.xhr={},this.hintIndex=null,this.filters={dropdown:{},dynamic:{}},this.dropdownFilter={"static":[],dynamic:[]},this.dropdownFilterAll=null,this.requests={},this.backdrop={},this.hint={},this.hasDragged=!1,this.focusOnly=!1,this.__construct()};a.prototype={extendOptions:function(){this.options.dynamic&&(this.options.cache=!1,this.options.compression=!1);if(this.options.cache&&(this.options.cache=function(t){var e,i=["localStorage","sessionStorage"];if(t===!0)t="localStorage";else if("string"==typeof t&&!~i.indexOf(t))return!1;e="undefined"!=typeof window[t];try{window[t].setItem("typeahead","typeahead"),window[t].removeItem("typeahead")}catch(o){e=!1}return e&&t||!1}.call(this,this.options.cache)),this.options.compression&&("object"==typeof LZString&&this.options.cache||(this.options.compression=!1)),"undefined"==typeof this.options.maxItem||/^\d+$/.test(this.options.maxItem)&&0!==this.options.maxItem||(this.options.maxItem=1/0),this.options.maxItemPerGroup&&!/^\d+$/.test(this.options.maxItemPerGroup)&&(this.options.maxItemPerGroup=null),this.options.display&&!Array.isArray(this.options.display)&&(this.options.display=[this.options.display]),this.options.group&&(Array.isArray(this.options.group)||("string"==typeof this.options.group?this.options.group={key:this.options.group}:"boolean"==typeof this.options.group&&(this.options.group={key:"group"}),this.options.group.key=this.options.group.key||"group")),this.options.highlight&&!~["any",!0].indexOf(this.options.highlight)&&(this.options.highlight=!1),this.options.dropdownFilter&&this.options.dropdownFilter instanceof Object){Array.isArray(this.options.dropdownFilter)||(this.options.dropdownFilter=[this.options.dropdownFilter]);for(var i=0,n=this.options.dropdownFilter.length;n>i;++i)this.dropdownFilter[this.options.dropdownFilter[i].value?"static":"dynamic"].push(this.options.dropdownFilter[i])}this.options.dynamicFilter&&!Array.isArray(this.options.dynamicFilter)&&(this.options.dynamicFilter=[this.options.dynamicFilter]),this.options.accent&&("object"==typeof this.options.accent?this.options.accent.from&&this.options.accent.to&&this.options.accent.from.length===this.options.accent.to.length:this.options.accent=o),this.options.groupTemplate&&(this.groupTemplate=this.options.groupTemplate),this.options.resultContainer&&("string"==typeof this.options.resultContainer&&(this.options.resultContainer=t(this.options.resultContainer)),this.options.resultContainer instanceof t&&this.options.resultContainer[0]&&(this.resultContainer=this.options.resultContainer)),this.options.maxItemPerGroup&&this.options.group&&this.options.group.key&&(this.groupBy=this.options.group.key),this.options.callback&&this.options.callback.onClick&&(this.options.callback.onClickBefore=this.options.callback.onClick,delete this.options.callback.onClick),this.options.callback&&this.options.callback.onNavigate&&(this.options.callback.onNavigateBefore=this.options.callback.onNavigate,delete this.options.callback.onNavigate),this.options=t.extend(!0,{},e,this.options)},unifySourceFormat:function(){if(this.groupCount=0,Array.isArray(this.options.source))return this.options.source={group:{data:this.options.source}},this.groupCount=1,!0;"string"==typeof this.options.source&&(this.options.source={group:{ajax:{url:this.options.source}}}),this.options.source.ajax&&(this.options.source={group:{ajax:this.options.source.ajax}}),(this.options.source.url||this.options.source.data)&&(this.options.source={group:this.options.source});var t,e,i;for(t in this.options.source)if(this.options.source.hasOwnProperty(t)){if(e=this.options.source[t],"string"==typeof e&&(e={ajax:{url:e}}),i=e.url||e.ajax,Array.isArray(i)?(e.ajax="string"==typeof i[0]?{url:i[0]}:i[0],e.ajax.path=e.ajax.path||i[1]||null,delete e.url):("object"==typeof e.url?e.ajax=e.url:"string"==typeof e.url&&(e.ajax={url:e.url}),delete e.url),!e.data&&!e.ajax)return!1;e.display&&!Array.isArray(e.display)&&(e.display=[e.display]),this.options.source[t]=e,this.groupCount++}return!0},init:function(){this.helper.executeCallback.call(this,this.options.callback.onInit,[this.node]),this.container=this.node.closest("."+this.options.selector.container)},delegateEvents:function(){var e=this,i=["focus"+this.namespace,"input"+this.namespace,"propertychange"+this.namespace,"keydown"+this.namespace,"keyup"+this.namespace,"dynamic"+this.namespace,"generate"+this.namespace];t("html").on("touchmove",function(){e.hasDragged=!0}).on("touchstart",function(){e.hasDragged=!1}),this.node.closest("form").on("submit",function(t){return e.options.mustSelectItem&&e.helper.isEmpty(e.item)?void t.preventDefault():(e.options.backdropOnFocus||e.hideLayout(),e.options.callback.onSubmit?e.helper.executeCallback.call(e,e.options.callback.onSubmit,[e.node,this,e.item,t]):void 0)}).on("reset",function(){setTimeout(function(){e.node.trigger("input"+e.namespace)})});var o=!1;if(this.node.attr("placeholder")&&(s||r)){var a=!0;this.node.on("focusin focusout",function(){a=!(this.value||!this.placeholder)}),this.node.on("input",function(t){a&&(t.stopImmediatePropagation(),a=!1)})}this.node.off(this.namespace).on(i.join(" "),function(t,i){switch(t.type){case"generate":e.isGenerated=null,e.generateSource();break;case"focus":if(e.focusOnly){e.focusOnly=!1;break}e.options.backdropOnFocus&&(e.buildBackdropLayout(),e.showLayout()),e.options.searchOnFocus&&e.query.length>=e.options.minLength&&(e.isGenerated?e.showLayout():null===e.isGenerated&&e.generateSource());break;case"keydown":t.keyCode&&~[9,13,27,38,39,40].indexOf(t.keyCode)&&(o=!0,e.navigate(t));break;case"keyup":null!==e.isGenerated||e.options.dynamic||e.generateSource(),n&&e.node[0].value.replace(/^\s+/,"").toString().length<e.query.length&&e.node.trigger("input"+e.namespace);break;case"propertychange":if(o){o=!1;break}case"input":if(e.rawQuery=e.node[0].value.toString(),""===e.rawQuery&&""!==e.query&&(t.originalEvent=i||{},e.helper.executeCallback.call(e,e.options.callback.onCancel,[e.node,t])),e.query=e.rawQuery.replace(/^\s+/,""),e.options.cancelButton&&e.toggleCancelButton(),e.options.hint&&e.hint.container&&""!==e.hint.container.val()&&0!==e.hint.container.val().indexOf(e.rawQuery)&&e.hint.container.val(""),e.options.dynamic)return e.isGenerated=null,void e.helper.typeWatch(function(){e.query.length>=e.options.minLength?e.generateSource():e.hideLayout()},e.options.delay);case"dynamic":if(!e.isGenerated)break;e.searchResult(),e.buildLayout(),(e.result.length>0||e.options.emptyTemplate&&""!==e.query)&&e.query.length>=e.options.minLength?e.showLayout():e.hideLayout()}}),this.options.generateOnLoad&&this.node.trigger("generate"+this.namespace)},generateSource:function(){if(!this.isGenerated||this.options.dynamic){if(this.generatedGroupCount=0,this.isGenerated=!1,this.options.loadingAnimation&&this.container.addClass("loading"),!this.helper.isEmpty(this.xhr)){for(var e in this.xhr)this.xhr.hasOwnProperty(e)&&this.xhr[e].abort();this.xhr={}}var i,o,n,s,r,a=this;for(i in this.options.source)if(this.options.source.hasOwnProperty(i)){if(n=this.options.source[i],this.options.cache&&(s=window[this.options.cache].getItem("TYPEAHEAD_"+this.node.selector+":"+i))){this.options.compression&&(s=LZString.decompressFromUTF16(s)),r=!1;try{s=JSON.parse(s+""),s.data&&s.ttl>(new Date).getTime()?(this.populateSource(s.data,i),r=!0):window[this.options.cache].removeItem("TYPEAHEAD_"+this.node.selector+":"+i)}catch(l){}if(r)continue}!n.data||n.ajax?n.ajax&&(this.requests[i]||(this.requests[i]=this.generateRequestObject(i))):"function"==typeof n.data?(o=n.data.call(this),Array.isArray(o)?a.populateSource(o,i):"function"==typeof o.promise&&!function(e){t.when(o).then(function(t){t&&Array.isArray(t)&&a.populateSource(t,e)})}(i)):this.populateSource(t.extend(!0,[],n.data),i)}this.handleRequests()}},generateRequestObject:function(t){var e=this,i=this.options.source[t],o={request:{url:i.ajax.url||null,dataType:"json",beforeSend:function(o,n){e.xhr[t]=o;var s=e.requests[t].callback.beforeSend||i.ajax.beforeSend;"function"==typeof s&&s.apply(null,arguments)}},callback:{beforeSend:null,done:null,fail:null,then:null,always:null},extra:{path:i.ajax.path||null,group:t},validForGroup:[t]};if("function"!=typeof i.ajax&&(i.ajax instanceof Object&&(o=this.extendXhrObject(o,i.ajax)),Object.keys(this.options.source).length>1))for(var n in this.requests)this.requests.hasOwnProperty(n)&&(this.requests[n].isDuplicated||o.request.url&&o.request.url===this.requests[n].request.url&&(this.requests[n].validForGroup.push(t),o.isDuplicated=!0,delete o.validForGroup));return o},extendXhrObject:function(e,i){return"object"==typeof i.callback&&(e.callback=i.callback,delete i.callback),"function"==typeof i.beforeSend&&(e.callback.beforeSend=i.beforeSend,delete i.beforeSend),e.request=t.extend(!0,e.request,i),"jsonp"!==e.request.dataType.toLowerCase()||e.request.jsonpCallback||(e.request.jsonpCallback="callback_"+e.extra.group),e},handleRequests:function(){var e=this,i=Object.keys(this.requests).length;if(this.helper.executeCallback.call(this,this.options.callback.onSendRequest,[this.node,this.query])===!1)return void(this.isGenerated=null);for(var o in this.requests)this.requests.hasOwnProperty(o)&&(this.requests[o].isDuplicated||!function(o,n){if("function"==typeof e.options.source[o].ajax){var s=e.options.source[o].ajax.call(e,e.query);if(n=e.extendXhrObject(n,s),"object"!=typeof n.request||!n.request.url)return}var r,a=!1;if(~n.request.url.indexOf("{{query}}")&&(a||(n=t.extend(!0,{},n),a=!0),n.request.url=n.request.url.replace("{{query}}",encodeURIComponent(e.query))),n.request.data)for(var l in n.request.data)if(n.request.data.hasOwnProperty(l)&&~String(n.request.data[l]).indexOf("{{query}}")){a||(n=t.extend(!0,{},n),a=!0),n.request.data[l]=n.request.data[l].replace("{{query}}",e.query);break}t.ajax(n.request).done(function(t,i,o){for(var s,a=0,l=n.validForGroup.length;l>a;a++)r=e.requests[n.validForGroup[a]],r.callback.done instanceof Function&&(s=r.callback.done(t,i,o),t=Array.isArray(s)&&s||t)}).fail(function(t,i,o){for(var s=0,a=n.validForGroup.length;a>s;s++)r=e.requests[n.validForGroup[s]],r.callback.fail instanceof Function&&r.callback.fail(t,i,o)}).always(function(t,o,s){for(var a=0,l=n.validForGroup.length;l>a;a++)r=e.requests[n.validForGroup[a]],r.callback.always instanceof Function&&r.callback.always(t,o,s),e.populateSource("function"==typeof t.promise&&[]||t,r.extra.group,r.extra.path||r.request.path),i-=1,0===i&&e.helper.executeCallback.call(e,e.options.callback.onReceiveRequest,[e.node,e.query])}).then(function(t,i){for(var o=0,s=n.validForGroup.length;s>o;o++)r=e.requests[n.validForGroup[o]],r.callback.then instanceof Function&&r.callback.then(t,i)})}(o,this.requests[o]))},populateSource:function(t,e,i){var o=this,n=this.options.source[e],s=n.ajax&&n.data;t="string"==typeof i?this.helper.namespace(i,t):t,Array.isArray(t)||(t=[]),s&&("function"==typeof s&&(s=s()),Array.isArray(s)&&(t=t.concat(s)));for(var r,a=n.display?"compiled"===n.display[0]?n.display[1]:n.display[0]:"compiled"===this.options.display[0]?this.options.display[1]:this.options.display[0],l=0,h=t.length;h>l;l++)null!==t[l]&&"boolean"!=typeof t[l]?("string"==typeof t[l]&&(r={},r[a]=t[l],t[l]=r),t[l].group=e):(_debug.log({node:this.node.selector,"function":"populateSource()",message:"WARNING - NULL/BOOLEAN value inside "+e+"! The data was skipped."}),_debug.print());if(!this.options.dynamic&&this.dropdownFilter.dynamic.length)for(var c,p,u={},l=0,h=t.length;h>l;l++)for(var d=0,f=this.dropdownFilter.dynamic.length;f>d;d++)c=this.dropdownFilter.dynamic[d].key,p=t[l][c],p&&(this.dropdownFilter.dynamic[d].value||(this.dropdownFilter.dynamic[d].value=[]),u[c]||(u[c]=[]),~u[c].indexOf(p.toLowerCase())||(u[c].push(p.toLowerCase()),this.dropdownFilter.dynamic[d].value.push(p)));if(this.options.correlativeTemplate){var y=n.template||this.options.template,g="";if("function"==typeof y&&(y=y()),y){if(Array.isArray(this.options.correlativeTemplate))for(var l=0,h=this.options.correlativeTemplate.length;h>l;l++)g+="{{"+this.options.correlativeTemplate[l]+"}} ";else g=y.replace(/<.+?>/g,"");for(var l=0,h=t.length;h>l;l++)t[l].compiled=g.replace(/\{\{([\w\-\.]+)(?:\|(\w+))?}}/g,function(e,i){return o.helper.namespace(i,t[l],"get","")}).trim();n.display?~n.display.indexOf("compiled")||n.display.unshift("compiled"):~this.options.display.indexOf("compiled")||this.options.display.unshift("compiled")}else;}if(this.options.callback.onPopulateSource&&(t=this.helper.executeCallback.call(this,this.options.callback.onPopulateSource,[this.node,t,e,i])),this.tmpSource[e]=t,this.options.cache&&!window[this.options.cache].getItem("TYPEAHEAD_"+this.node.selector+":"+e)){this.options.callback.onCacheSave&&(t=this.helper.executeCallback.call(this,this.options.callback.onCacheSave,[this.node,t,e,i]));var m=JSON.stringify({data:t,ttl:(new Date).getTime()+this.options.ttl});this.options.compression&&(m=LZString.compressToUTF16(m)),window[this.options.cache].setItem("TYPEAHEAD_"+this.node.selector+":"+e,m)}this.incrementGeneratedGroup()},incrementGeneratedGroup:function(){if(this.generatedGroupCount++,this.groupCount===this.generatedGroupCount){this.isGenerated=!0,this.xhr={};for(var t=Object.keys(this.options.source),e=0,i=t.length;i>e;e++)this.source[t[e]]=this.tmpSource[t[e]];this.tmpSource={},this.options.dynamic||this.buildDropdownItemLayout("dynamic"),this.options.loadingAnimation&&this.container.removeClass("loading"),this.node.trigger("dynamic"+this.namespace)}},navigate:function(t){if(this.helper.executeCallback.call(this,this.options.callback.onNavigateBefore,[this.node,this.query,t]),27===t.keyCode)return t.preventDefault(),void(this.query.length?(this.node.val(""),this.node.trigger("input"+this.namespace,[t])):(this.node.blur(),this.hideLayout()));if(this.isGenerated&&this.result.length){var e=this.resultContainer.find("."+this.options.selector.item),i=e.filter(".active"),o=i[0]&&e.index(i)||null,n=null;if(13===t.keyCode)return void(i.length>0&&(t.preventDefault(),i.find("a:first").trigger("click",t)));if(39===t.keyCode)return void(o?e.eq(o).find("a:first")[0].click():this.options.hint&&""!==this.hint.container.val()&&this.helper.getCaret(this.node[0])>=this.query.length&&e.find('a[data-index="'+this.hintIndex+'"]')[0].click());e.length>0&&i.removeClass("active"),38===t.keyCode?(t.preventDefault(),i.length>0?o-1>=0&&(n=o-1,e.eq(n).addClass("active")):(n=e.length-1,e.last().addClass("active"))):40===t.keyCode&&(t.preventDefault(),i.length>0?o+1<e.length&&(n=o+1,e.eq(n).addClass("active")):(n=0,e.first().addClass("active"))),t.preventInputChange&&~[38,40].indexOf(t.keyCode)&&this.buildHintLayout(null!==n&&n<this.result.length?[this.result[n]]:null),this.options.hint&&this.hint.container&&this.hint.container.css("color",t.preventInputChange?this.hint.css.color:null===n&&this.hint.css.color||this.hint.container.css("background-color")||"fff"),this.node.val(null===n||t.preventInputChange?this.rawQuery:this.result[n][this.result[n].matchedKey]),this.helper.executeCallback.call(this,this.options.callback.onNavigateAfter,[this.node,e,null!==n&&e.eq(n).find("a:first")||void 0,null!==n&&this.result[n]||void 0,this.query,t])}},searchResult:function(t){t||(this.item={}),this.resetLayout(),this.helper.executeCallback.call(this,this.options.callback.onSearch,[this.node,this.query])!==!1&&(this.query.length>=this.options.minLength&&this.searchResultData(),this.helper.executeCallback.call(this,this.options.callback.onResult,[this.node,this.query,this.result,this.resultCount,this.resultCountPerGroup]))},searchResultData:function(){var t,e,i,o,n,s,r,a,l,h,c,p,u,d=this,f=this.groupBy,y=null,g=this.query.toLowerCase(),m=this.options.maxItemPerGroup,v=this.filters.dynamic&&!this.helper.isEmpty(this.filters.dynamic),b="function"==typeof this.options.matcher&&this.options.matcher;this.options.accent&&(g=this.helper.removeAccent.call(this,g));for(t in this.source)if(this.source.hasOwnProperty(t)&&(!this.filters.dropdown||"group"!==this.filters.dropdown.key||this.filters.dropdown.value===t)){r="undefined"!=typeof this.options.source[t].filter?this.options.source[t].filter:this.options.filter,l="function"==typeof this.options.source[t].matcher&&this.options.source[t].matcher||b;for(var k=0,w=this.source[t].length;w>k&&(!(this.result.length>=this.options.maxItem)||this.options.callback.onResult);k++)if((!v||this.dynamicFilter.validate.apply(this,[this.source[t][k]]))&&(e=this.source[t][k],null!==e&&"boolean"!=typeof e&&(!this.filters.dropdown||(e[this.filters.dropdown.key]||"").toLowerCase()===(this.filters.dropdown.value||"").toLowerCase()))){if(y="group"===f?t:e[f],y&&!this.result[y]&&(this.result[y]=[],this.resultCountPerGroup[y]=0),m&&"group"===f&&this.result[y].length>=m&&!this.options.callback.onResult)break;n=this.options.source[t].display||this.options.display;for(var x=0,C=n.length;C>x;x++)if(s=/\./.test(n[x])?this.helper.namespace(n[x],e):e[n[x]],"undefined"!=typeof s&&""!==s){if(s=this.helper.cleanStringFromScript(s),"function"==typeof r){if(a=r.call(this,e,s),void 0===a)break;if(!a)continue;"object"==typeof a&&(e=a)}if(~[void 0,!0].indexOf(r)){if(o=s,o=o.toString().toLowerCase(),this.options.accent&&(o=this.helper.removeAccent.call(this,o)),i=o.indexOf(g),this.options.correlativeTemplate&&"compiled"===n[x]&&0>i&&/\s/.test(g)){c=!0,p=g.split(" "),u=o;for(var q=0,O=p.length;O>q;q++)if(""!==p[q]){if(!~u.indexOf(p[q])){c=!1;break}u=u.replace(p[q],"")}}if(0>i&&!c)continue;if(this.options.offset&&0!==i)continue;if(l){if(h=l.call(this,e,s),void 0===h)break;if(!h)continue;"object"==typeof h&&(e=h)}}if(this.resultCount++,this.resultCountPerGroup[y]++,this.resultItemCount<this.options.maxItem){if(m&&this.result[y].length>=m)break;e.matchedKey=n[x],this.result[y].push(e),this.resultItemCount++}break}if(!this.options.callback.onResult){if(this.resultItemCount>=this.options.maxItem)break;if(m&&this.result[y].length>=m&&"group"===f)break}}}if(this.options.order){var S,n=[];for(var t in this.result)if(this.result.hasOwnProperty(t)){for(var x=0,C=this.result[t].length;C>x;x++)S=this.options.source[this.result[t][x].group].display||this.options.display,~n.indexOf(S[0])||n.push(S[0]);this.result[t].sort(d.helper.sort(n,"asc"===d.options.order,function(t){return t.toString().toUpperCase()}))}}var A,F=[];A="function"==typeof this.options.groupOrder?this.options.groupOrder.apply(this,[this.node,this.query,this.result,this.resultCount,this.resultCountPerGroup]):Array.isArray(this.options.groupOrder)?this.options.groupOrder:"string"==typeof this.options.groupOrder&&~["asc","desc"].indexOf(this.options.groupOrder)?Object.keys(this.result).sort(d.helper.sort([],"asc"===d.options.groupOrder,function(t){return t.toString().toUpperCase()})):Object.keys(this.result),this.groups=A;for(var x=0,C=A.length;C>x;x++)F=F.concat(this.result[A[x]]||[]);this.result=F},buildLayout:function(){if(this.buildHtmlLayout(),this.buildBackdropLayout(),this.buildHintLayout(),this.options.callback.onLayoutBuiltBefore){var e=this.helper.executeCallback.call(this,this.options.callback.onLayoutBuiltBefore,[this.node,this.query,this.result,this.resultHtml]);e instanceof t&&(this.resultHtml=e)}this.resultHtml&&this.resultContainer.html(this.resultHtml),this.options.callback.onLayoutBuiltAfter&&this.helper.executeCallback.call(this,this.options.callback.onLayoutBuiltAfter,[this.node,this.query,this.result])},buildHtmlLayout:function(){if(this.options.resultContainer!==!1){this.resultContainer||(this.resultContainer=t("<div/>",{"class":this.options.selector.result}),this.container.append(this.resultContainer));var e;if(!this.result.length){if(!this.options.emptyTemplate||""===this.query)return;e="function"==typeof this.options.emptyTemplate?this.options.emptyTemplate.call(this,this.query):this.options.emptyTemplate.replace(/\{\{query}}/gi,this.helper.cleanStringFromScript(this.query))}var i=this.query.toLowerCase();this.options.accent&&(i=this.helper.removeAccent.call(this,i));var o=this,n=this.groupTemplate||"<ul></ul>",s=!1;this.groupTemplate?n=t(n.replace(/<([^>]+)>\{\{(.+?)}}<\/[^>]+>/g,function(t,i,n,r,a){var l="",h="group"===n?o.groups:[n];if(!o.result.length)return s===!0?"":(s=!0,"<"+i+' class="'+o.options.selector.empty+'"><a href="javascript:;">'+e+"</a></"+i+">");for(var c=0,p=h.length;p>c;++c)l+="<"+i+' data-group-template="'+h[c]+'"><ul></ul></'+i+">";return l})):(n=t(n),this.result.length||n.append(e instanceof t?e:'<li class="'+o.options.selector.empty+'"><a href="javascript:;">'+e+"</a></li>")),n.addClass(this.options.selector.list+(this.helper.isEmpty(this.result)?" empty":""));for(var r,a,l,h,c,p,u,d,f,y,g,m=this.groupTemplate&&this.result.length&&o.groups||[],v=0,b=this.result.length;b>v;++v)l=this.result[v],r=l.group,h=this.options.source[l.group].href||this.options.href,d=[],f=this.options.source[l.group].display||this.options.display,this.options.group&&(r=l[this.options.group.key],this.options.group.template&&("function"==typeof this.options.group.template?a=this.options.group.template(l):"string"==typeof this.options.template&&(a=this.options.group.template.replace(/\{\{([\w\-\.]+)}}/gi,function(t,e){return o.helper.namespace(e,l,"get","")}))),n.find('[data-search-group="'+r+'"]')[0]||(this.groupTemplate?n.find('[data-group-template="'+r+'"] ul'):n).append(t("<li/>",{"class":o.options.selector.group,html:t("<a/>",{href:"javascript:;",html:a||r,tabindex:-1}),"data-search-group":r}))),this.groupTemplate&&m.length&&(g=m.indexOf(r||l.group),~g&&m.splice(g,1)),c=t("<li/>",{"class":o.options.selector.item+" "+o.options.selector.group+"-"+this.helper.slugify.call(this,r),html:t("<a/>",{href:function(){return h&&("string"==typeof h?h=h.replace(/\{\{([^\|}]+)(?:\|([^}]+))*}}/gi,function(t,e,i){var n=o.helper.namespace(e,l,"get","");return i=i&&i.split("|")||[],~i.indexOf("slugify")&&(n=o.helper.slugify.call(o,n)),n}):"function"==typeof h&&(h=h(l)),l.href=h),h||"javascript:;"}(),"data-group":r,"data-index":v,html:function(){if(p=l.group&&o.options.source[l.group].template||o.options.template)"function"==typeof p&&(p=p.call(o,o.query,l)),u=p.replace(/\{\{([^\|}]+)(?:\|([^}]+))*}}/gi,function(t,e,n){var s=o.helper.cleanStringFromScript(String(o.helper.namespace(e,l,"get","")));return n=n&&n.split("|")||[],~n.indexOf("slugify")&&(s=o.helper.slugify.call(o,s)),~n.indexOf("raw")||o.options.highlight===!0&&i&&~f.indexOf(e)&&(s=o.helper.highlight.call(o,s,i.split(" "),o.options.accent)),s});else{for(var e=0,n=f.length;n>e;e++)y=/\./.test(f[e])?o.helper.namespace(f[e],l):l[f[e]],"undefined"!=typeof y&&""!==y&&d.push(y);u='<span class="'+o.options.selector.display+'">'+o.helper.cleanStringFromScript(String(d.join(" ")))+"</span>"}(o.options.highlight===!0&&i&&!p||"any"===o.options.highlight)&&(u=o.helper.highlight.call(o,u,i.split(" "),o.options.accent)),t(this).append(u)}})}),function(e,i,n){n.on("click",function(e,n){return n&&"object"==typeof n&&(e.originalEvent=n),o.options.mustSelectItem&&o.helper.isEmpty(i)?void e.preventDefault():(o.item=i,void(o.helper.executeCallback.call(o,o.options.callback.onClickBefore,[o.node,t(this),i,e])!==!1&&(e.originalEvent&&e.originalEvent.defaultPrevented||e.isDefaultPrevented()||(o.query=o.rawQuery=i[i.matchedKey].toString(),o.focusOnly=!0,o.node.val(o.query).focus(),o.searchResult(!0),o.buildLayout(),o.hideLayout(),o.helper.executeCallback.call(o,o.options.callback.onClickAfter,[o.node,t(this),i,e])))))}),n.on("mouseenter",function(e){o.helper.executeCallback.call(o,o.options.callback.onMouseEnter,[o.node,t(this),i,e])}),n.on("mouseleave",function(e){o.helper.executeCallback.call(o,o.options.callback.onMouseLeave,[o.node,t(this),i,e])})}(v,l,c),(this.groupTemplate?n.find('[data-group-template="'+r+'"] ul'):n).append(c);if(this.result.length&&m.length)for(var v=0,b=m.length;b>v;++v)n.find('[data-group-template="'+m[v]+'"]').remove();this.resultHtml=n}},buildBackdropLayout:function(){this.options.backdrop&&(this.backdrop.container||(this.backdrop.css=t.extend({opacity:.6,filter:"alpha(opacity=60)",position:"fixed",top:0,right:0,bottom:0,left:0,"z-index":1040,"background-color":"#000"},this.options.backdrop),this.backdrop.container=t("<div/>",{"class":this.options.selector.backdrop,css:this.backdrop.css}).insertAfter(this.container)),this.container.addClass("backdrop").css({"z-index":this.backdrop.css["z-index"]+1,position:"relative"}))},buildHintLayout:function(e){if(this.options.hint){if(this.node[0].scrollWidth>Math.ceil(this.node.innerWidth()))return void(this.hint.container&&this.hint.container.val(""));var i=this,o="",e=e||this.result,n=this.query.toLowerCase();if(this.options.accent&&(n=this.helper.removeAccent.call(this,n)),this.hintIndex=null,this.query.length>=this.options.minLength){if(this.hint.container||(this.hint.css=t.extend({"border-color":"transparent",position:"absolute",top:0,display:"inline","z-index":-1,"float":"none",color:"silver","box-shadow":"none",cursor:"default","-webkit-user-select":"none","-moz-user-select":"none","-ms-user-select":"none","user-select":"none"},this.options.hint),this.hint.container=t("<input/>",{type:this.node.attr("type"),"class":this.node.attr("class"),readonly:!0,unselectable:"on","aria-hidden":"true",tabindex:-1,click:function(){i.node.focus()}}).addClass(this.options.selector.hint).css(this.hint.css).insertAfter(this.node),this.node.parent().css({position:"relative"})),this.hint.container.css("color",this.hint.css.color),n)for(var s,r,a,l=0,h=e.length;h>l;l++){r=e[l].group,s=this.options.source[r].display||this.options.display;for(var c=0,p=s.length;p>c;c++)if(a=String(e[l][s[c]]).toLowerCase(),this.options.accent&&(a=this.helper.removeAccent.call(this,a)),0===a.indexOf(n)){o=String(e[l][s[c]]),this.hintIndex=l;break}if(null!==this.hintIndex)break}this.hint.container.val(o.length>0&&this.rawQuery+o.substring(this.query.length)||"")}}},buildDropdownLayout:function(){if(this.options.dropdownFilter){var e=this;t("<span/>",{"class":this.options.selector.filter,html:function(){t(this).append(t("<button/>",{type:"button","class":e.options.selector.filterButton,style:"display: none;",click:function(i){i.stopPropagation(),e.container.toggleClass("filter");var o=e.namespace+"-dropdown-filter";t("html").off(o),e.container.hasClass("filter")&&t("html").on("click"+o+" touchend"+o,function(i){t(i.target).closest("."+e.options.selector.filter)[0]||e.hasDragged||e.container.removeClass("filter")})}})),t(this).append(t("<ul/>",{"class":e.options.selector.dropdown}))}}).insertAfter(e.container.find("."+e.options.selector.query))}},buildDropdownItemLayout:function(e){function i(t){"*"===t.value?delete this.filters.dropdown:this.filters.dropdown=t,this.container.removeClass("filter").find("."+this.options.selector.filterButton).html(t.template),this.node.trigger("dynamic"+this.namespace),this.node.focus()}var o,n,s=this,r="string"==typeof this.options.dropdownFilter&&this.options.dropdownFilter||"All",a=this.container.find("."+this.options.selector.dropdown);("static"===e&&this.options.dropdownFilter===!0||"string"==typeof this.options.dropdownFilter)&&this.dropdownFilter["static"].push({key:"group",template:"{{group}}",all:r,value:Object.keys(this.options.source)});for(var l=0,h=this.dropdownFilter[e].length;h>l;l++){n=this.dropdownFilter[e][l],Array.isArray(n.value)||(n.value=[n.value]),n.all&&(this.dropdownFilterAll=n.all);for(var c=0,p=n.value.length;p>=c;c++)(c!==p||l===h-1)&&(c===p&&l===h-1&&"static"===e&&this.dropdownFilter.dynamic.length||(o=this.dropdownFilterAll||r,n.value[c]?o=n.template?n.template.replace(new RegExp("{{"+n.key+"}}","gi"),n.value[c]):n.value[c]:this.container.find("."+s.options.selector.filterButton).html(o),function(e,o,n){a.append(t("<li/>",{"class":s.options.selector.dropdownItem+" "+s.helper.slugify.call(s,o.key+"-"+(o.value[e]||r)),html:t("<a/>",{href:"javascript:;",html:n,click:function(t){t.preventDefault(),i.call(s,{key:o.key,value:o.value[e]||"*",template:n})}})}))}(c,n,o)))}this.dropdownFilter[e].length&&this.container.find("."+s.options.selector.filterButton).removeAttr("style")},dynamicFilter:{isEnabled:!1,init:function(){this.options.dynamicFilter&&(this.dynamicFilter.bind.call(this),this.dynamicFilter.isEnabled=!0)},validate:function(t){var e,i,o=null,n=null;for(var s in this.filters.dynamic)if(this.filters.dynamic.hasOwnProperty(s)&&(i=~s.indexOf(".")?this.helper.namespace(s,t,"get"):t[s],"|"!==this.filters.dynamic[s].modifier||o||(o=i==this.filters.dynamic[s].value||!1),"&"===this.filters.dynamic[s].modifier)){if(i!=this.filters.dynamic[s].value){n=!1;break}n=!0}return e=o,null!==n&&(e=n,n===!0&&null!==o&&(e=o)),!!e},set:function(t,e){var i=t.match(/^([|&])?(.+)/);e?this.filters.dynamic[i[2]]={modifier:i[1]||"|",value:e}:delete this.filters.dynamic[i[2]],this.dynamicFilter.isEnabled&&(this.searchResult(),this.buildLayout())},bind:function(){for(var e,i=this,o=0,n=this.options.dynamicFilter.length;n>o;o++)e=this.options.dynamicFilter[o],"string"==typeof e.selector&&(e.selector=t(e.selector)),e.selector instanceof t&&e.selector[0]&&e.key&&!function(t){t.selector.off(i.namespace).on("change"+i.namespace,function(){i.dynamicFilter.set.apply(i,[t.key,i.dynamicFilter.getValue(this)])}).trigger("change"+i.namespace)}(e)},getValue:function(t){var e;return"SELECT"===t.tagName?e=t.value:"INPUT"===t.tagName&&("checkbox"===t.type?e=t.checked&&t.getAttribute("value")||t.checked||null:"radio"===t.type&&t.checked&&(e=t.value)),e}},showLayout:function(){if(!this.container.hasClass("result")&&(this.result.length||this.options.emptyTemplate||this.options.backdropOnFocus)){
var e=this;t("html").off(this.namespace).on("click"+this.namespace+" touchend"+this.namespace,function(i){t(i.target).closest(e.container)[0]||e.hasDragged||e.hideLayout()}),this.container.addClass([this.result.length||this.options.emptyTemplate&&this.query.length>=this.options.minLength?"result ":"",this.options.hint&&this.query.length>=this.options.minLength?"hint":"",this.options.backdrop||this.options.backdropOnFocus?"backdrop":""].join(" ")),this.helper.executeCallback.call(this,this.options.callback.onShowLayout,[this.node,this.query])}},hideLayout:function(){(this.container.hasClass("result")||this.container.hasClass("backdrop"))&&(this.container.removeClass("result hint filter"+(this.options.backdropOnFocus&&t(this.node).is(":focus")?"":" backdrop")),this.options.backdropOnFocus&&this.container.hasClass("backdrop")||(t("html").off(this.namespace),this.helper.executeCallback.call(this,this.options.callback.onHideLayout,[this.node,this.query])))},resetLayout:function(){this.result={},this.resultCount=0,this.resultCountPerGroup={},this.resultItemCount=0,this.resultHtml=null,this.options.hint&&this.hint.container&&this.hint.container.val("")},buildCancelButtonLayout:function(){if(this.options.cancelButton){var e=this;t("<span/>",{"class":this.options.selector.cancelButton,mousedown:function(t){t.stopImmediatePropagation(),t.preventDefault(),e.node.val(""),e.node.trigger("input"+e.namespace,[t])}}).insertBefore(this.node)}},toggleCancelButton:function(){this.container.toggleClass("cancel",!!this.query.length)},__construct:function(){this.extendOptions(),this.unifySourceFormat()&&(this.dynamicFilter.init.apply(this),this.init(),this.delegateEvents(),this.buildCancelButtonLayout(),this.buildDropdownLayout(),this.buildDropdownItemLayout("static"),this.helper.executeCallback.call(this,this.options.callback.onReady,[this.node]))},helper:{isEmpty:function(t){for(var e in t)if(t.hasOwnProperty(e))return!1;return!0},removeAccent:function(t){if("string"==typeof t){var e=o;return"object"==typeof this.options.accent&&(e=this.options.accent),t=t.toLowerCase().replace(new RegExp("["+e.from+"]","g"),function(t){return e.to[e.from.indexOf(t)]})}},slugify:function(t){return t=String(t),""!==t&&(t=this.helper.removeAccent.call(this,t),t=t.replace(/[^-a-z0-9]+/g,"-").replace(/-+/g,"-").replace(/^-|-$/g,"")),t},sort:function(t,e,i){var o=function(e){for(var o=0,n=t.length;n>o;o++)if("undefined"!=typeof e[t[o]])return i(e[t[o]]);return e};return e=[-1,1][+!!e],function(t,i){return t=o(t),i=o(i),e*((t>i)-(i>t))}},replaceAt:function(t,e,i,o){return t.substring(0,e)+o+t.substring(e+i)},highlight:function(t,e,i){t=String(t);var o=i&&this.helper.removeAccent.call(this,t)||t,n=[];Array.isArray(e)||(e=[e]),e.sort(function(t,e){return e.length-t.length});for(var s=e.length-1;s>=0;s--)""!==e[s].trim()?e[s]=e[s].replace(/[-[\]{}()*+?.,\\^$|#\s]/g,"\\$&"):e.splice(s,1);o.replace(new RegExp("(?:"+e.join("|")+")(?!([^<]+)?>)","gi"),function(t,e,i){n.push({offset:i,length:t.length})});for(var s=n.length-1;s>=0;s--)t=this.helper.replaceAt(t,n[s].offset,n[s].length,"<strong>"+t.substr(n[s].offset,n[s].length)+"</strong>");return t},getCaret:function(t){if(t.selectionStart)return t.selectionStart;if(document.selection){t.focus();var e=document.selection.createRange();if(null===e)return 0;var i=t.createTextRange(),o=i.duplicate();return i.moveToBookmark(e.getBookmark()),o.setEndPoint("EndToStart",i),o.text.length}return 0},cleanStringFromScript:function(t){return"string"==typeof t&&t.replace(/<\/?(?:script|iframe)\b[^>]*>/gm,"")||t},executeCallback:function(t,e){if(t){var i;if("function"==typeof t)i=t;else if(("string"==typeof t||Array.isArray(t))&&("string"==typeof t&&(t=[t,[]]),i=this.helper.namespace(t[0],window),"function"!=typeof i))return;return i.apply(this,(t[1]||[]).concat(e?e:[]))}},namespace:function(t,e,i,o){if("string"!=typeof t||""===t)return!1;for(var n=t.split("."),s=e||window,i=i||"get",r=o||{},a="",l=0,h=n.length;h>l;l++){if(a=n[l],"undefined"==typeof s[a]){if(~["get","delete"].indexOf(i))return"undefined"!=typeof o?o:void 0;s[a]={}}if(~["set","create","delete"].indexOf(i)&&l===h-1){if("set"!==i&&"create"!==i)return delete s[a],!0;s[a]=r}s=s[a]}return s},typeWatch:function(){var t=0;return function(e,i){clearTimeout(t),t=setTimeout(e,i)}}()}},t.fn.typeahead=t.typeahead=function(t){return l.typeahead(this,t)};var l={typeahead:function(e,i){if(i&&i.source&&"object"==typeof i.source){if("function"==typeof e){if(!i.input)return;e=t(i.input)}if(e.length&&"INPUT"===e[0].nodeName)return i.input&&!e.selector&&(e.selector=i.input),window.Typeahead[i.input||e.selector]=new a(e,i)}}};return window.console=window.console||{log:function(){}},Array.isArray||(Array.isArray=function(t){return"[object Array]"===Object.prototype.toString.call(t)}),"trim"in String.prototype||(String.prototype.trim=function(){return this.replace(/^\s+/,"").replace(/\s+$/,"")}),"indexOf"in Array.prototype||(Array.prototype.indexOf=function(t,e){void 0===e&&(e=0),0>e&&(e+=this.length),0>e&&(e=0);for(var i=this.length;i>e;e++)if(e in this&&this[e]===t)return e;return-1}),Object.keys||(Object.keys=function(t){var e,i=[];for(e in t)Object.prototype.hasOwnProperty.call(t,e)&&i.push(e);return i}),a});