(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-9b6ed098"],{2934:function(e,t,a){"use strict";a.r(t);var o=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"leader-wrap"},[a("van-nav-bar",{attrs:{title:"购买"}}),a("van-field",{attrs:{name:"商品名字",label:"商品名字",disabled:""},model:{value:e.goods_detail.commodityName,callback:function(t){e.$set(e.goods_detail,"commodityName",t)},expression:"goods_detail.commodityName"}}),a("van-field",{attrs:{name:"商品描述",label:"商品描述",disabled:""},model:{value:e.goods_detail.describe,callback:function(t){e.$set(e.goods_detail,"describe",t)},expression:"goods_detail.describe"}}),a("van-field",{attrs:{name:"stepper",label:"商品数量"},scopedSlots:e._u([{key:"input",fn:function(){return[a("van-stepper",{model:{value:e.value,callback:function(t){e.value=t},expression:"value"}})]},proxy:!0}])}),a("van-field",{attrs:{name:"希望交易方式",label:"希望交易方式",placeholder:"请输入希望交易方式",rules:[{required:!0,message:"请填写希望交易方式"}]},model:{value:e.jiaoyifangshi,callback:function(t){e.jiaoyifangshi=t},expression:"jiaoyifangshi"}}),a("van-submit-bar",{attrs:{price:3050,"button-text":"提交订单"},on:{submit:e.onSubmit}})],1)},i=[],s=(a("e7e5"),a("d399")),n=a("bc3a"),l=a.n(n),d={data:function(){return{oldPwd:"",newPwd:"",active:0,value:1,id:"",columns:["自约地点","卖家送货","买家提货"],showPicker:!1,jiaoyifangshi:"",goods_detail:[],value1:""}},computed:{},watch:{},created:function(){this.id=this.$route.query.commodityId,this.getGoodsDetail()},methods:{onSubmit:function(){var e=this;"自约地点"==this.goods_detail.transaction?this.value1=0:"卖家送货"==this.goods_detail.transaction?this.value1=1:"买家提货"==this.goods_detail.transaction?this.value1=2:"买家决定"==this.goods_detail.transaction&&(this.value1=3),l.a.get(this.$8181+"/order/v1/createOrder",{params:{sellerId:this.goods_detail.sellerInfo.sellerId,transaction:this.value1,goodsId:this.id,number:this.value}}).then((function(t){200==t.status&&(console.log(t),s["a"].fail("订单提交成功"),e.$router.push({path:"/home"}))})).catch((function(e){s["a"].fail("不能购买自己的东西"),console.log(e)}))},onConfirm:function(){},getGoodsDetail:function(){var e=this;l.a.get(this.$8181+"/commodity/v1/getOneCommodity",{params:{commodityId:this.id}}).then((function(t){200==t.status&&(e.goods_detail=t.data.result)})).catch((function(e){console.log(e)}))}}},c=d,r=(a("54a3"),a("2877")),u=Object(r["a"])(c,o,i,!1,null,"d8f2dbe0",null);t["default"]=u.exports},"54a3":function(e,t,a){"use strict";a("a73a")},a73a:function(e,t,a){}}]);