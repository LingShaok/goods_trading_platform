(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-2026a72a"],{"057a":function(e,a,o){"use strict";o.r(a);var t=function(){var e=this,a=e.$createElement,o=e._self._c||a;return o("div",{staticClass:"leader-wrap"},[o("van-form",{on:{submit:e.onSubmit}},[o("van-field",{attrs:{name:"商品名称",label:"商品名称",placeholder:"商品名称",rules:[{required:!0,message:"请填写商品名称"}]},model:{value:e.goodName,callback:function(a){e.goodName=a},expression:"goodName"}}),o("van-field",{attrs:{name:"商品描述",label:"商品描述",placeholder:"商品描述",rules:[{required:!0,message:"请填写商品描述"}]},model:{value:e.goodMessage,callback:function(a){e.goodMessage=a},expression:"goodMessage"}}),o("van-field",{attrs:{name:"商品价格",label:"商品价格",placeholder:"商品价格(元)",rules:[{required:!0,message:"请填写商品价格"}]},model:{value:e.price,callback:function(a){e.price=a},expression:"price"}}),o("van-field",{attrs:{name:"商品数量",label:"商品数量",placeholder:"商品数量(个)",rules:[{required:!0,message:"请填写商品数量"}]},model:{value:e.number,callback:function(a){e.number=a},expression:"number"}}),o("van-field",{attrs:{readonly:"",clickable:"",name:"picker",value:e.value,label:"交易方式",placeholder:"点击选择交易方式"},on:{click:function(a){e.showPicker=!0}}}),o("van-popup",{attrs:{position:"bottom"},model:{value:e.showPicker,callback:function(a){e.showPicker=a},expression:"showPicker"}},[o("van-picker",{attrs:{"show-toolbar":"",columns:e.columns},on:{confirm:e.onConfirm,cancel:function(a){e.showPicker=!1}}})],1),o("van-field",{attrs:{name:"uploader",label:"商品图片上传"},scopedSlots:e._u([{key:"input",fn:function(){return[o("van-uploader",{attrs:{"after-read":e.afterRead},model:{value:e.uploader,callback:function(a){e.uploader=a},expression:"uploader"}})]},proxy:!0}])}),o("div",{staticStyle:{margin:"16px"}},[o("van-button",{attrs:{round:"",block:"",type:"info","native-type":"submit"},on:{click:e.release}},[e._v("修改")])],1)],1)],1)},s=[],i=o("bc3a"),l=o.n(i),n={data:function(){return{id:"",goods_detail:[],goodName:"",goodMessage:"",price:"",value:"",columns:["自约地点","卖家送货","买家提货","买家决定"],showPicker:!1,uploader:[],goodList:[],active:0,value1:"",number:"",avader:"",commodityPic:""}},computed:{},watch:{},created:function(){this.id=this.$route.query.commodityId,this.getMyGoods()},methods:{onSubmit:function(){},onConfirm:function(e){this.value=e,"自约地点"==e?this.value1=0:"卖家送货"==e?this.value1=1:"买家提货"==e?this.value1=2:"买家决定"==e&&(this.value1=3),console.log(this.value1),this.showPicker=!1},getMyGoods:function(){var e=this;l.a.get(this.$8181+"/commodity/v1/getOneCommodity",{params:{commodityId:this.id}}).then((function(a){200==a.status&&(e.goods_detail=a.data.result,e.goodName=a.data.result.commodityName,e.goodMessage=a.data.result.describe,e.price=a.data.result.price,e.value=a.data.result.transaction,e.commodityPic=a.data.result.commodityPic,"自约地点"==e.value?e.value1=0:"卖家送货"==e.value?e.value1=1:"买家提货"==e.value?e.value1=2:"买家决定"==e.value&&(e.value1=3),e.number=a.data.result.number,e.uploader.push({url:e.$8181+a.data.result.commodityPic}))})).catch((function(e){console.log(e)}))},afterRead:function(e){var a=this;this.uploader=[],console.log(e);var o=new FormData;o.append("picture",e.file),l.a.post(this.$8181+"/userBaseInfo/v1/uploadPicture",o,{headers:{"Content-Type":"multipart/form-data"}}).then((function(e){200===e.status&&(console.log(e.data.result),a.avader=e.data.result,a.uploader.push({url:a.$8181+e.data.result}))})).catch((function(e){console.log(e)}))},release:function(){var e=this;if(console.log(this.uploader),0!=this.uploader.length){var a={commodityId:this.id,commodityName:this.goodName,commodityPic:this.commodityPic,describe:this.goodMessage,number:this.number,price:this.price,transaction:this.value1};l.a.post(this.$8181+"/commodity/v1/addOrUpdateCommodity",a).then((function(a){200===a.status&&(console.log(a.data.result),e.$router.push({path:"/home"}))})).catch((function(e){console.log(e)}))}else this.$toast.fail("商品图片为空！")}}},r=n,c=(o("ce94"),o("2877")),u=Object(c["a"])(r,t,s,!1,null,"d00a01e4",null);a["default"]=u.exports},ce94:function(e,a,o){"use strict";o("e322")},e322:function(e,a,o){}}]);