(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-6764f83e"],{4767:function(t,e,i){},"5e05":function(t,e,i){"use strict";i.r(e);var s=function(){var t=this,e=t.$createElement,i=t._self._c||e;return i("div",{staticClass:"leader-wrap"},[i("van-nav-bar",{attrs:{title:"视频详情","left-text":"返回","left-arrow":""},on:{"click-left":t.onClickLeft}}),i("div",{staticClass:"img-box"},[i("img",{staticClass:"good-img",attrs:{src:t.goodsList.cover,alt:""}})]),i("van-tabs",{model:{value:t.active,callback:function(e){t.active=e},expression:"active"}},[i("van-tab",{attrs:{title:"课程详情"}},[i("div",{staticStyle:{display:"flex","justify-content":"center"}},[i("div",{staticClass:"good-detail"},[i("div",{staticStyle:{display:"flex","align-items":"center","margin-left":"14px","margin-top":"6px"},on:{click:t.activity}},[i("van-image",{attrs:{round:"",width:"3rem",height:"3rem",src:t.userDetail.headImg}}),i("div",{staticStyle:{"font-size":"15px"}},[t._v(t._s(t.userDetail.username)+" "),i("div",{staticStyle:{"font-size":"9px"}},[t._v(" "+t._s(t.userDetail.sign)+" ")])])],1),i("div",{staticClass:"detail"},[t._v(t._s(t.goodsList.title))]),i("div",{staticClass:"detail-other"},[t._v(" 播放次数："+t._s(t.goodsList.plays)+"次 收藏次数："+t._s(t.goodsList.collectCount)+"次 点赞次数："+t._s(t.goodsList.likeCount)+"次 ")]),i("div",{staticClass:"detail",staticStyle:{"font-size":"14px",color:"#999"}},[t._v(" 介绍："+t._s(t.goodsList.brief)+" ")]),i("div",{staticClass:"detail",staticStyle:{"font-size":"14px",color:"#999"}},[t._v(" 用户评分： "),i("van-rate",{attrs:{"allow-half":"","void-icon":"star","void-color":"#eee",readonly:"",size:"16px"},model:{value:t.value,callback:function(e){t.value=e},expression:"value"}}),i("div",{staticStyle:{"margin-left":"20px"}},[t._v(" 分值："+t._s(t.value)+" ")])],1)])]),i("div",{staticStyle:{display:"flex","justify-content":"center"}},[i("div",{staticClass:"good-detail"},[i("div",{staticClass:"detail"},[t._v(" 你的评分： "),i("van-rate",{attrs:{"allow-half":"","void-icon":"star","void-color":"#eee",color:"#ffd21e"},model:{value:t.value1,callback:function(e){t.value1=e},expression:"value1"}}),i("div",{staticStyle:{"margin-left":"20px","font-size":"14px"}},[t._v(" 分值："+t._s(t.value1)+" ")])],1)])])]),i("van-tab",{attrs:{title:"评论"}},[i("div",{staticClass:"logs"},t._l(t.commentsList,(function(e,s){return i("div",{key:s,staticStyle:{display:"flex","justify-content":"center"}},[i("div",{staticClass:"good-detail"},[i("div",{staticStyle:{display:"flex","align-items":"center"}},[i("van-tag",{staticStyle:{"margin-left":"10px"},attrs:{color:"#ffe1e1","text-color":"#ad0000",type:"warning"}},[t._v(t._s(s+1)+"楼")]),i("van-image",{attrs:{round:"",width:"3rem",height:"3rem",src:e.fromAvatar}}),i("div",{staticClass:"detail"},[t._v(t._s(e.fromName))])],1),i("div",{staticClass:"detail-other"},[t._v(" 时间："+t._s(e.updateTime)+" ")]),i("div",{staticClass:"detail",staticStyle:{"font-size":"15px"}},[t._v(" "+t._s(e.content)+" ")])])])})),0),i("div",{staticClass:"add",on:{click:function(e){t.show=!0}}},[i("van-icon",{staticClass:"addIcon",attrs:{name:"plus"}})],1)])],1),i("van-popup",{staticClass:"pop",model:{value:t.show,callback:function(e){t.show=e},expression:"show"}},[i("van-field",{attrs:{rows:"4",autosize:"",label:"留言",type:"textarea",maxlength:"50",placeholder:"请输入留言","show-word-limit":""},model:{value:t.message,callback:function(e){t.message=e},expression:"message"}}),i("div",{staticStyle:{margin:"16px"}},[i("van-button",{attrs:{round:"",block:"",type:"info"},on:{click:t.submit}},[t._v("提交")])],1)],1),i("van-goods-action",[i("van-goods-action-icon",{attrs:{icon:"chat-o",text:"客服"},on:{click:t.onClickIcon}}),i("van-goods-action-icon",{attrs:{icon:"star-o",text:"收藏"},on:{click:t.onClickIcon}}),i("van-goods-action-button",{attrs:{type:"danger",text:"立即观看"},on:{click:t.onClickButton}})],1)],1)},a=[],o={data:function(){return{value:3.5,value1:1,active:0,goodsList:[],commentsList:[],userDetail:[],showPopup:!1,show:!1,message:""}},computed:{},watch:{},created:function(){this.id=sessionStorage.getItem("id"),this.getGoods(),this.getComments()},methods:{onClickLeft:function(){this.$router.go(-1)},onClickIcon:function(){var t=this;this.$get(this.$8181+"/shopCart/addCard",{courseId:this.id}).then((function(e){200==e.code&&t.$toast.success("添加到购物车成功")}))},onClickButton:function(){var t=this;this.$get(this.$8181+"/video/videoPlaysAdd",{videoId:this.id}).then((function(){window.location.href=t.goodsList.path}))},getGoods:function(){var t=this;this.$get(this.$8181+"/video/getVideoDetails",{videoId:this.id}).then((function(e){200!=e.code&&0!=e.code||(t.goodsList=e.data,t.getUserDetail(e.data.userId))}))},getUserDetail:function(t){var e=this;this.$get(this.$8181+"/user/getUserDetailById",{id:t}).then((function(t){200!=t.code&&0!=t.code||(e.userDetail=t.data)}))},getComments:function(){var t=this;this.$get(this.$8181+"/getCommentsByOwnerId",{ownerId:this.id}).then((function(e){200!=e.code&&0!=e.code||(t.commentsList=e.data,console.log(e.data))}))},submit:function(){var t=this;this.$postBody(this.$8181+"/save",{ownerId:this.id,content:this.message}).then((function(e){200!=e.code&&0!=e.code||(t.$toast.success("评价成功！"),t.getComments(),t.show=!1)}))},activity:function(){this.$router.push({path:"/music-activity",query:{id:this.goodsList.userId}})}}},n=o,c=(i("9e3a"),i("2877")),l=Object(c["a"])(n,s,a,!1,null,"69eea312",null);e["default"]=l.exports},"9e3a":function(t,e,i){"use strict";i("4767")}}]);