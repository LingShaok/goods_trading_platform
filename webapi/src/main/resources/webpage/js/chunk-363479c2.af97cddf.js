(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-363479c2"],{"44ee":function(e,t,o){"use strict";o("6eec")},"5eae":function(e,t,o){"use strict";o.r(t);var a=function(){var e=this,t=e.$createElement,o=e._self._c||t;return o("div",{staticClass:"leader-wrap"},[o("van-tabs",{model:{value:e.active,callback:function(t){e.active=t},expression:"active"}},[o("van-tab",{attrs:{title:"发布商品"}},[o("van-form",{on:{submit:e.onSubmit}},[o("van-field",{attrs:{name:"商品名称",label:"商品名称",placeholder:"商品名称",rules:[{required:!0,message:"请填写商品名称"}]},model:{value:e.goodName,callback:function(t){e.goodName=t},expression:"goodName"}}),o("van-field",{attrs:{name:"商品描述",label:"商品描述",placeholder:"商品描述",rules:[{required:!0,message:"请填写商品描述"}]},model:{value:e.goodMessage,callback:function(t){e.goodMessage=t},expression:"goodMessage"}}),o("van-field",{attrs:{name:"商品价格",label:"商品价格",placeholder:"商品价格(元)",rules:[{required:!0,message:"请填写商品价格"}]},model:{value:e.goodPrice,callback:function(t){e.goodPrice=t},expression:"goodPrice"}}),o("van-field",{attrs:{name:"商品数量",label:"商品数量",placeholder:"商品数量(个)",rules:[{required:!0,message:"请填写商品数量"}]},model:{value:e.number,callback:function(t){e.number=t},expression:"number"}}),o("van-field",{attrs:{readonly:"",clickable:"",name:"picker",value:e.value,label:"交易方式",placeholder:"点击选择交易方式"},on:{click:function(t){e.showPicker=!0}}}),o("van-popup",{attrs:{position:"bottom"},model:{value:e.showPicker,callback:function(t){e.showPicker=t},expression:"showPicker"}},[o("van-picker",{attrs:{"show-toolbar":"",columns:e.columns},on:{confirm:e.onConfirm,cancel:function(t){e.showPicker=!1}}})],1),o("van-field",{attrs:{name:"uploader",label:"商品图片上传"},scopedSlots:e._u([{key:"input",fn:function(){return[o("van-uploader",{attrs:{"after-read":e.afterRead},model:{value:e.uploader,callback:function(t){e.uploader=t},expression:"uploader"}})]},proxy:!0}])}),o("div",{staticStyle:{margin:"16px"}},[o("van-button",{attrs:{round:"",block:"",type:"info","native-type":"submit"},on:{click:e.release}},[e._v("发布")])],1)],1)],1),o("van-tab",{attrs:{title:"我的商品"}},e._l(e.goodList,(function(t,a){return o("van-swipe-cell",{key:a,scopedSlots:e._u([{key:"right",fn:function(){return[o("van-button",{staticClass:"delete-button",attrs:{square:"",text:"删除",type:"danger"},on:{click:function(o){return e.delItem(t.commodityId)}}})]},proxy:!0}],null,!0)},[o("van-card",{staticClass:"goods-card",attrs:{price:t.price,desc:t.describe,title:t.commodityName,thumb:e.$8181+t.commodityPic},on:{click:function(o){return e.modify(t.commodityId)}},scopedSlots:e._u([{key:"footer",fn:function(){return[o("van-button",{attrs:{size:"mini"}},[e._v("修改")])]},proxy:!0}],null,!0)})],1)})),1)],1)],1)},n=[],s=o("bc3a"),i=o.n(s),c={data:function(){return{goodName:"",goodMessage:"",goodPrice:"",value:"",columns:["自约地点","卖家送货","买家提货","买家决定"],showPicker:!1,uploader:[],goodList:[],active:0,avader:"",number:"",value1:""}},computed:{},watch:{},created:function(){this.getMyGoods()},methods:{onSubmit:function(){},onConfirm:function(e){this.value=e,"自约地点"==e?this.value1=0:"卖家送货"==e?this.value1=1:"买家提货"==e?this.value1=2:"买家决定"==e&&(this.value1=3),console.log(this.value1),this.showPicker=!1},getMyGoods:function(){var e=this;i.a.get(this.$8181+"/commodity/v1/getMyCommodity").then((function(t){200==t.status&&(console.log(t),e.goodList=t.data.result)})).catch((function(e){console.log(e)}))},delItem:function(e){var t=this;i.a.get(this.$8181+"/commodity/v1/deleteMyCommodity",{params:{commodityId:e}}).then((function(e){200==e.status&&(console.log(e),t.$toast.success("删除成功"),t.getMyGoods())})).catch((function(e){console.log(e),this.$toast.fail("删除失败")}))},modify:function(e){this.$router.push({path:"/modify",query:{commodityId:e}})},afterRead:function(e){var t=this;console.log(e);var o=new FormData;o.append("picture",e.file),i.a.post(this.$8181+"/userBaseInfo/v1/uploadPicture",o,{headers:{"Content-Type":"multipart/form-data"}}).then((function(e){200===e.status&&(console.log(e.data.result),t.avader=e.data.result)})).catch((function(e){console.log(e)}))},release:function(){var e=this,t={commodityName:this.goodName,commodityPic:this.avader,describe:this.goodMessage,number:this.number,price:this.goodPrice,transaction:this.value1};i.a.post(this.$8181+"/commodity/v1/addOrUpdateCommodity",t).then((function(t){200===t.status&&(console.log(t.data.result),e.$router.go(-1))})).catch((function(e){console.log(e)}))}}},l=c,r=(o("44ee"),o("2877")),u=Object(r["a"])(l,a,n,!1,null,"7bf0d442",null);t["default"]=u.exports},"6eec":function(e,t,o){}}]);