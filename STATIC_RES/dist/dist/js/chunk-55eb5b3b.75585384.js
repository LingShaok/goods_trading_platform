(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-55eb5b3b"],{"3d3c":function(t,e,i){t.exports=i.p+"img/vip.e25d97e6.png"},"468c":function(t,e,i){"use strict";i.r(e);var s=function(){var t=this,e=t.$createElement,s=t._self._c||e;return s("div",{staticClass:"leader"},[s("van-nav-bar",{attrs:{title:t.cateName}}),s("img",{staticClass:"img",attrs:{src:i("3d3c"),alt:"",srcset:""}})],1)},a=[],n=(i("b0c0"),i("e7e5"),i("d399")),o=i("2b0e");o["a"].use(n["a"]);var c={data:function(){return{tabData:[],id:""}},computed:{},watch:{},created:function(){this.id=this.$route.query.id,this.cateName=this.$route.query.name,console.log(this.id),this.getInfo(),this.getList()},methods:{getInfo:function(){var t=this;this.$postBody(this.$8181+"/video/showVideosPageByCategory",{id:this.id}).then((function(e){200!=e.code&&0!=e.code||(null!=t.res.data?(console.log(t.res.data),t.tabData=e.data.list):n["a"].fail("该分类下还没有视频哦~"))}))},subscribe:function(){},getList:function(){},goDetail:function(t){sessionStorage.setItem("id",t),this.$router.push({path:"/musicDetail"})}}},d=c,r=(i("5dd0"),i("2877")),u=Object(r["a"])(d,s,a,!1,null,"3152727c",null);e["default"]=u.exports},"5dd0":function(t,e,i){"use strict";i("9b05")},"9b05":function(t,e,i){}}]);