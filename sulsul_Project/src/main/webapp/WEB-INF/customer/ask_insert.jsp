<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="untree_co-section rows"> 
  <main>
  <div class="container">
   <div class="row">
     <div style="height: 20px"></div>
     <div class="col-lg-3"></div>
     <div class="col-lg-6">
        <div class="text-center">
          <b style="font-size: 30px; ">1:1 문의하기</b>
         </div>
         <div style="height: 20px"></div>
	     <table class="table">
	      <tr>
	        <th width="20%">제목</th>
	        <td width="80%"><input type="text" size="45" class="input-sm" v-model="subject"></td>
	      </tr>
	      <tr>
	        <th width="20%">내용</th>
	        <td width="80%"><textarea rows="10" cols="45" v-model="content"></textarea></td>
	      </tr>
	      <tr>
	        <th width="20%">비밀번호</th>
	        <td width="80%"><input type="password" size="10" class="input-sm" v-model="pwd"></td>
	      </tr>
	      <tr>
	        <td colspan="2" class="text-center">
	          <input type="button" value="글쓰기" class="btn btn-sm" style="background-color:#D0F1FB" v-on:click="write()">
	          <input type="button" value="취소" class="btn btn-sm" style="background-color:white"onclick="javascript:history.back()">
	        </td>
	      </tr>
	    </table>
    </div> 
    <div class="col-lg-3"></div>
   </div>
  </div>
  </main>
</div>
<script>
	new Vue({
		el:'.rows',
		data:{
			 id:'',
			 subject:'',
		     content:'',
		     pwd:''
		},
		methods:{
			write:function(){
				let _this=this;
				axios.get('http://localhost/web/customer/ask_insert_vue.do',{
					 params:{
		                 subject:this.subject,
		                 content:this.content,
		                 pwd:this.pwd
		              }
				}).then(function(response){
		              location.href="../customer/ask.do"
		        })
			}
		}
	})
</script>
</body>
</html>