(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-5b0c67a8"],{"159b":function(e,t,r){var a=r("da84"),s=r("fdbc"),i=r("17c2"),o=r("9112");for(var c in s){var n=a[c],d=n&&n.prototype;if(d&&d.forEach!==i)try{o(d,"forEach",i)}catch(l){d.forEach=i}}},"17c2":function(e,t,r){"use strict";var a=r("b727").forEach,s=r("a640"),i=r("ae40"),o=s("forEach"),c=i("forEach");e.exports=o&&c?[].forEach:function(e){return a(this,e,arguments.length>1?arguments[1]:void 0)}},4160:function(e,t,r){"use strict";var a=r("23e7"),s=r("17c2");a({target:"Array",proto:!0,forced:[].forEach!=s},{forEach:s})},"7fbc":function(e,t,r){},"917c":function(e,t,r){"use strict";r("7fbc")},a99d:function(e,t,r){"use strict";r.r(t);var a=function(){var e=this,t=e.$createElement,r=e._self._c||t;return r("div",{staticClass:"leader-wrap"},[r("van-nav-bar",{attrs:{title:"我的订单"}}),r("div",{staticClass:"order-list"},e._l(e.order_detail,(function(t,a){return r("div",{key:a,staticClass:"order",style:"background:"+(0==e.order_detail[a].isMyOrder?"#FFFAF0":"\t#F0FFFF")+";background-image: url("+e.$8181+(0==t.isFinish?"/static/Finish.png":"/static/noFinish.png")+");background-repeat: no-repeat; background-position: 110% 20%; background-size: 50% 110%;  ",on:{click:function(r){return e.checkOrder(t)}}},[r("div",{staticClass:"orderName"},[e._v(" 订单属性："+e._s(1==t.isMyOrder?"卖":"买")+" ")]),r("div",{staticClass:"orderName"},[e._v("商品名称："+e._s(t.goodsName))]),r("div",{staticClass:"orderName"},[e._v("订单编号："+e._s(t.orderNumber))]),r("div",{staticClass:"orderName"},[e._v("购买数量："+e._s(t.number))]),r("div",{staticClass:"orderName"},[e._v("买家ID："+e._s(t.sellerId))]),r("div",{staticClass:"orderName"},[e._v("创建时间："+e._s(t.createTime))]),r("div",{staticClass:"orderName"},[e._v(" 买家确认："+e._s(1==t.isPayBuyer?"已确认":"未确认")+" ")]),r("div",{staticClass:"orderName"},[e._v(" 卖家确认："+e._s(1==t.isPaySeller?"已确认":"未确认")+" ")]),r("div",{staticClass:"orderName"},[e._v(" 订单状态："+e._s(0==t.isFinish?"已完成":"未完成")+" ")])])})),0),r("van-dialog",{attrs:{title:"评价","show-cancel-button":""},on:{confirm:e.confirmRate},model:{value:e.show,callback:function(t){e.show=t},expression:"show"}},[r("div",{staticClass:"rateDiv"},[e._v("商家态度："),r("van-rate",{model:{value:e.attitude,callback:function(t){e.attitude=t},expression:"attitude"}})],1),r("div",{staticClass:"rateDiv"},[e._v("交易速度："),r("van-rate",{model:{value:e.speed,callback:function(t){e.speed=t},expression:"speed"}})],1),r("div",{staticClass:"rateDiv"},[e._v(" 符合程度："),r("van-rate",{model:{value:e.accordance,callback:function(t){e.accordance=t},expression:"accordance"}})],1),r("div",{staticClass:"rateDiv1"},[r("van-field",{attrs:{rows:"2",autosize:"",label:"描述：",type:"textarea",maxlength:"50",placeholder:"请输入评价","show-word-limit":""},model:{value:e.discribe,callback:function(t){e.discribe=t},expression:"discribe"}})],1)])],1)},s=[],i=(r("4160"),r("159b"),r("e17f"),r("2241")),o=r("bc3a"),c=r.n(o),n={data:function(){return{active:0,order_detail:[],userNumber:"",show:!1,discribe:"",attitude:0,speed:0,accordance:0,orderNumber:""}},computed:{},watch:{},created:function(){this.userNumber=sessionStorage.getItem("userNumber"),this.getMyOrder()},methods:{onConfirm:function(){},getMyOrder:function(){var e=this;c.a.get(this.$8181+"/order/v1/getMyOrder").then((function(t){200==t.status&&(e.order_detail=t.data.result,e.order_detail.forEach((function(t){t.sellerId==e.userNumber?t.isMyOrder=1:t.isMyOrder=0,1==t.isPayBuyer&&1==t.isPaySeller?t.isFinish=0:t.isFinish=1})),console.log(e.order_detail))})).catch((function(e){console.log(e)}))},checkOrder:function(e){var t=this;if(this.orderNumber=e.orderNumber,0==e.isFinish){var r=0;return c.a.get(this.$8181+"/evaluate/v1/getIsEvaluate",{params:{orderNumber:e.orderNumber}}).then((function(e){if(200==e.status){if(1==e.data.result)return r=1,void t.$toast.fail("该订单已评论！");i["a"].confirm({title:"评价",message:"该订单已完成，是否进行评价？"}).then((function(){t.show=!0})).catch((function(){}))}})).catch((function(e){console.log(e)})),void console.log(r+"------------")}console.log(e),i["a"].confirm({title:"确认订单",message:"是否确认订单？"}).then((function(){c.a.get(t.$8181+"/order/v1/sureOrder",{params:{orderNumber:e.orderNumber}}).then((function(e){200==e.status&&(t.$toast.success("确认成功"),t.getMyOrder())})).catch((function(e){console.log(e)}))})).catch((function(){}))},confirmRate:function(){var e=this;c.a.post(this.$8181+"/evaluate/v1/addEvaluate",{accordance:this.accordance,attitude:this.attitude,describe:this.discribe,speed:this.speed,orderNumber:this.orderNumber}).then((function(t){console.log(t),200==t.data.status?(e.$toast.success("评价成功"),e.getMyOrder()):e.$toast.fail(t.data.message)})).catch((function(e){console.log(e)}))}}},d=n,l=(r("917c"),r("2877")),u=Object(l["a"])(d,a,s,!1,null,"3471b500",null);t["default"]=u.exports},fdbc:function(e,t){e.exports={CSSRuleList:0,CSSStyleDeclaration:0,CSSValueList:0,ClientRectList:0,DOMRectList:0,DOMStringList:0,DOMTokenList:1,DataTransferItemList:0,FileList:0,HTMLAllCollection:0,HTMLCollection:0,HTMLFormElement:0,HTMLSelectElement:0,MediaList:0,MimeTypeArray:0,NamedNodeMap:0,NodeList:1,PaintRequestList:0,Plugin:0,PluginArray:0,SVGLengthList:0,SVGNumberList:0,SVGPathSegList:0,SVGPointList:0,SVGStringList:0,SVGTransformList:0,SourceBufferList:0,StyleSheetList:0,TextTrackCueList:0,TextTrackList:0,TouchList:0}}}]);