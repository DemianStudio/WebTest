<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="pageTitle" value="게시물 수정"/>
<%@include file="../common/head.jspf" %>


<script>
	// 댓글작성 관련
	let ArticleModify__submitDone = false;
	function ArticleModify__submitForm(form) {
		if ( ArticleModify__submitFormDone ) {
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
		
		ArticleModify__submitDqone = true;
		form.submit();		
	}
</script>

<fmt:formatDate value="${article.regDate }"	pattern="yyyy년 MM월 dd일 hh시" var="regDate" />
<fmt:formatDate value="${article.updateDate }"	pattern="yyyy년 MM월 dd일 hh시" var="updateDate" />
<section class="mt-5">
  <div class="container mx-auto px-3">
	<form class="table-box-type-1" method="POST" action="../article/doModify" onsubmit="ArticleModify__submit">
	  <input type="hidden" name="id" value="${article.id}"/>
	
      <table>
      <colgroup>
        <col width="200"/>
      </colgroup>
        <tbody>
          <tr>
            <th>번호</th>
            <td>${article.id}</td>
          </tr>
          <tr>
            <th>작성날짜</th>
            <td>${article.getRegDateForPrint()}</td>
          </tr>
          <tr>
            <th>수정날짜</th>
            <td>${article.getUpdateDateForPrint()}</td>
          </tr>
          <tr>
            <th>작성자</th>
            <td>${article.extra__writerName}</td>
          </tr>
          <tr>
            <th>조회수</th>
            <td>
            	<span class="text-blue-700">${article.hitCount }</span>
            </td>
          </tr>
          <tr>
          <tr>
            <th>추천</th>
            <td>
            	<span class="text-blue-700">${goodReactionPoint }</span>
            </td>
          </tr>
          <tr>
            <th>제목</th>
            <td>
              <input type="text" class="input input-bordered input-primary w-full max-w-xs" name="title" placeholder="제목" value="${article.title}"/>
            </td>
          </tr>
          <tr>
            <th>내용</th>
            <td>
              <textarea class="w-full textarea textarea-error" name="body" placeholder="내용">${article.body}</textarea>
            </td>
          </tr>
          <tr>
            <th>수정</th>
            <td>
              <input class="btn btn-active btn-accent" type="submit" value="수정"/>
              <button class="btn btn-active btn-ghost" type="button" onclick="history.back();">뒤로가기</button>
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