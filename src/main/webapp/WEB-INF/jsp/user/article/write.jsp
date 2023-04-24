<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" value="게시물 작성"/>
<%@include file="../common/head.jspf" %>

<section class="mt-5">
  <div class="container mx-auto px-3">
	<form class="table-box-type-1" method="POST" action="../article/doWrite">
	  <input type="hidden" name="id" value="${article.id}"/>
	
      <table>
      <colgroup>
        <col width="200"/>
      </colgroup>
        <tbody>
          <!-- radio 버튼형 선택 <tr>
            <th>게시판</th>
	            <td>
		            <label>
		            	<input type="radio" name="boardId" value="1"/>
		            </label>	
		            <label>	
		            	<input type="radio" name="boardId" value="1"/>
	            	</label>	
            	</td>
          </tr> -->
          <tr>
            <th>게시판</th>
            <td>
	          <select name="boardId">
	          	<option selected disabled>게시판을 선택해주세요.</option>
	          	<option value="1">공지사항</option>
	          	<option value="2">자유게시판</option>
	          </select>
          	</td>
          </tr>
          <tr>
            <th>작성자</th>
            <td>${rq.loginedMember.nickname}</td>
          </tr>
          <tr>
            <th>제목</th>
            <td>
              <input required="required" type="text" class="input input-bordered input-primary w-full max-w-xs" name="title" placeholder="제목" value="${article.title}"/>
            </td>
          </tr>
          <tr>
            <th>내용</th>
            <td>
              <textarea required="required" type="text" class="w-full textarea textarea-error" name="body" placeholder="내용">${article.body}</textarea>
            </td>
          </tr>
          <tr>
            <th>작성</th>
            <td>
              <input class="btn btn-active btn-accent" type="submit" value="작성"/>
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