<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" value="비밀번호 확인"/>
<%@include file="../common/head.jspf" %>

<script>
	// 댓글작성 관련
	let MemberCheckPassword__submitDone = false;
	function MemberCheckPassword__submitForm(form) {
		if ( MemberCheckPassword__submitFormDone ) {
			return;
		}    

 		form.loginPwInput.value = form.loginPwInput.value.trim();
		
		if(form.loginPwInput.value.length == 0) {
			alert('비밀번호를 입력해세요.');
			form.loginPwInput.focus();
			return;
			
		} 
		
		form.loginPw.value = form.loginPwInput.value;
		form.loginPwInput.value = '';
		
		MemberCheckPassword__submitDqone = true;
		form.submit();
	}
</script>

<section class="mt-5">
  <div class="container mx-auto">
	  <form class="table-box-type-1" method="POST" action="../member/doCheckPassword" onsubmit="MemberCheckPassword__submit(this); reutn false;">
	  	<input type="hidden" name="replaceUri" value="${param.replaceUri }"/>
	  	<input type="hidden" name="loginPw"/>
		  <table>
			  <colgroup>
			  	<col width="200"/>
			  </colgroup>
			  <tbody>
			  	<tr>
			  		<th>로그인아이디</th>
			  		<td>
			  			${rq.loginedMember.loginId }
			  		</td>
			  	</tr>
			  	<tr>
			  		<th>비밀번호</th>
			  		<td>
			  			<input required="required" type="password" class="w-96" name="loginPw" placeholder="로그인 비밀번호"/>
			  		</td>
			  	</tr>

			  	<tr>
			  		<th>확인</th>
			  		<td>
			  			<input type="submit" value="확인"/>
			  			<button type="button" onclick="history.back();">뒤로가기</button>
			  		</td>
			  	</tr>
			  </tbody>
		  </table>
	  </form>
  </div>
</section>

<%@include file="../common/foot.jspf" %>