(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-762324be"],{4931:function(e,t,s){"use strict";s.r(t);var n=function(){var e=this,t=e.$createElement,s=e._self._c||t;return s("div",{staticClass:"leader-wrap"},[s("van-nav-bar",{attrs:{title:"消息"}}),e._l(e.messageList,(function(t,n){return s("van-cell",{key:n,attrs:{center:"",title:t.listenerId==e.userNumber?t.speakerName:t.listenerName,value:t.lastTime,label:t.lastMassage},on:{click:function(s){return e.goMessageInfo(t)}}})}))],2)},r=[],a=s("bc3a"),i=s.n(a),u={data:function(){return{messageList:[],userNumber:""}},computed:{},watch:{},created:function(){this.getMyOrder()},methods:{goMessageInfo:function(e){this.$router.push({path:"/messageInfo",query:{listenerId:e.listenerId==this.userNumber?e.speakerId:e.listenerId,listenerName:e.listenerId==this.userNumber?e.speakerName:e.listenerName}})},getMyOrder:function(){var e=this;i.a.post(this.$8181+"/chat/v1/getMessageList").then((function(t){200==t.status&&(e.messageList=t.data.result,e.userNumber=sessionStorage.getItem("userNumber"))})).catch((function(e){console.log(e)}))}}},c=u,o=(s("e30f"),s("2877")),l=Object(o["a"])(c,n,r,!1,null,"10333299",null);t["default"]=l.exports},"4e8c":function(e,t,s){},e30f:function(e,t,s){"use strict";s("4e8c")}}]);