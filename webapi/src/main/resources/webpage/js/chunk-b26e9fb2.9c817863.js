(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-b26e9fb2"],{"09f3":function(t,i,a){},"5e4c":function(t,i,a){"use strict";a("09f3")},6043:function(t,i,a){"use strict";a.r(i);var s=function(){var t=this,i=t.$createElement,a=t._self._c||i;return a("div",{staticClass:"leader"},[a("van-nav-bar",{attrs:{title:"精品课程"}}),a("div",{staticClass:"card-box"},t._l(t.tabData,(function(i,s){return a("div",{key:s,staticClass:"card",on:{click:function(a){return t.goDetail(i.id,i.icon,i.courseName)}}},[a("img",{staticClass:"card-img",attrs:{src:i.icon,alt:""}}),a("div",{staticClass:"card-right"},[a("div",[t._v(t._s(i.courseName))]),a("div",{staticClass:"nums"},[t._v("价格："+t._s(i.price))]),a("div",{staticClass:"shopCard"},[a("van-goods-action-button",{attrs:{type:"danger",text:"立即购买"},on:{click:t.onClickButton}})],1)])])})),0)],1)},e=[],n=(a("e7e5"),a("d399")),c=a("2b0e");c["a"].use(n["a"]);var o={data:function(){return{tabData:[],id:"",userData:[]}},computed:{},watch:{},created:function(){this.id=this.$route.query.id,console.log(this.id),this.getInfo(),this.getList()},methods:{getInfo:function(){var t=this;this.$get(this.$8181+"/video/showVipCourse",{}).then((function(i){200!=i.code&&0!=i.code||(console.log(i.data),t.tabData=i.data)}))},subscribe:function(){},getList:function(){},goDetail:function(t,i,a){this.$router.push({path:"/music-course",query:{id:t,icon:i,name:a}})}}},r=o,u=(a("5e4c"),a("2877")),d=Object(u["a"])(r,s,e,!1,null,"476077fe",null);i["default"]=d.exports}}]);