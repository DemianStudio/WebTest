<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="pageTitle" value="댓글 수정"/>
<%@include file="../common/head.jspf" %>


<script>
	// 댓글작성 관련
	let ReplyModify__submitDone = false;
	function ReplyModify__submitForm(form) {
		if ( ReplyModify__submitFormDone ) {
			return;
		}    
		
		// 좌우공백 제거
		form.body.value = form.body.value.trim();
		
		if ( form.body.value.length == 0 ) {
			alert('내용을 입력해주세요.');
			form.body.focus();
			return;
		}
		
		if ( form.body.value.length < 2 ) {
			alert('댓글을 2자 이상 입력해주세요.');
			form.body.focus();
			return;
		}
		
		ReplyModify__submitDqone = true;
		form.submit();		
	}
</script>


<fmt:formatDate value="${reply.regDate }"	pattern="yyyy년 MM월 dd일 hh시" var="regDate" />
<fmt:formatDate value="${reply.updateDate }"	pattern="yyyy년 MM월 dd일 hh시" var="updateDate" />
<section class="mt-5">
  <div class="container mx-auto px-3">
	<form class="table-box-type-1" method="POST" action="../reply/doModify" onsubmit="ReplyModify__submit">
	  <input type="hidden" name="id" value="${reply.id}"/>
	  <input type="hidden" name="replaceUri" value="${param.replaceUri}"/>
	
      <table>
      <colgroup>
        <col width="200"/>
      </colgroup>
        <tbody>
          <tr>
            <th>게시물 번호</th>
            <td>${reply.relId}</td>
          </tr>
          <tr>
            <th>댓글 번호</th>
            <td>${reply.id}</td>
          </tr>
          <tr>
            <th>댓글 작성날짜</th>
            <td>${regDate}</td>
          </tr>
          <tr>
            <th>댓글 수정날짜</th>
            <td>${updateDate}</td>
          </tr>
          <tr>
            <th>댓글 작성자</th>
            <td>${reply.extra__writerName}</td>
          </tr>
          <tr>
            <th>댓글 추천</th>
            <td>
            	<span class="text-blue-700">${goodReactionPoint }</span>
            </td>
          </tr>
          <tr>
            <th>내용</th>
            <td>
              <textarea rows="5" class="w-full textarea textarea-error" name="body" placeholder="내용">${reply.body}</textarea>
            </td>
          </tr>
          <tr>
            <th>댓글 수정</th>
            <td>
              <input class="btn btn-active btn-accent" type="submit" value="댓글 수정"/>
              <a class="btn btn-outline btn-primary" href="${param.replaceUri}">뒤로가기</a>
            </td> 
          </tr>
        </tbody>
      </table>   
	
	  <div class="btns">
		<button class="mt-3 btn btn-outline" type="button" onclick="history.back();">뒤로가기</button>
		<a class="mt-3 btn btn-outline" href="../article/modify?id=${article.id}">게시물 수정</a>
		
		<c:if test="${article.extra__actorCanDelete}">
			<a class="mt-3 btn btn-outline" onclick="if( confirm('정말 삭제하시겠습니까?') == false )return false;" href="../article/doDelete?id=${article.id}">게시물 삭제</a>
		</c:if>	
	  </div>
	</form>
  </div>
</section>
<%@include file="../common/foot.jspf" %>