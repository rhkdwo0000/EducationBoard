<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="http://code.jquery.com/jquery-1.10.2.js"></script>
<script type="text/javascript" src="/MavenSpringSetting/JS/function.js?ver=1.0"></script>
<title>Insert title here</title>
<script type="text/javascript">

/*페이지 로딩시 실행함수*/
$(function() {
	if ( "" != "${category}" &&  "" != "${searchText}") {
		$("#searchText").val("${searchText}") ;
		$("#searchCategory").val("${category}")
	}
});
</script>
</head>
<body>
	<div>
		<h1>자유게시판</h1>
	</div>
	<div style="width:650px;" align="right">
		<a href="./freeBoardInsert.ino">글쓰기</a>
	</div>
	<hr style="width: 600px">
	
	<c:set var="temp" value="임시" />
		<c:choose>
			 <c:when test="${null eq category}">
				<c:forEach items="${list }" var="dto">
					<div style="width: 50px; float: left;">${dto.origin_num }</div>	
					<div style="width: 300px; float: left;"><a href="./freeBoardDetail.ino?num=${dto.origin_num }">${dto.name }</a></div>
					<div style="width: 150px; float: left;">${dto.title }</div>
					<div style="width: 150px; float: left;">${dto.new_day }</div> 
					<hr style="width: 600px">
				</c:forEach>
			<c:if test="${1 < curRange}">
				<a href="./main.ino">처음</a>
			</c:if>
			<c:if test="${1 != curRange}">
				<a href="./main.ino?curPage=${startPage-1}">이전</a>
			</c:if>
	
			<c:forEach begin="${startPage}" end="${endPage}" var="index">
				<a href="./main.ino?curPage=${index}" style="margin-left: 10px;">${index}</a>
			</c:forEach>
			<c:if test="${curRange < rangeCnt}">
				<a href="./main.ino?curPage=${endPage+1}">다음</a>
			</c:if>
			<c:if test="${curRange != rangeCnt}">
				<a href="./main.ino?curPage=${pageCnt}">끝</a>
			</c:if>
			</c:when>
	
			<c:when test="${category ne null}">
				<c:forEach items="${list }" var="dto">
					<div style="width: 50px; float: left;">${dto.origin_num }</div>	
					<div style="width: 300px; float: left;"><a href="./freeBoardDetail.ino?num=${dto.origin_num }">${dto.name }</a></div>
					<div style="width: 150px; float: left;">${dto.title }</div>
					<div style="width: 150px; float: left;">${dto.new_day }</div> 
					<hr style="width: 600px">
				</c:forEach>
			<c:if test="${1 < curRange}">
				<a href="./main.ino?category=${category}&searchText=${searchText}">처음</a>
			</c:if>
			
			<c:if test="${1 != curRange}">
				<a href="./main.ino?curPage=${startPage-1}&category=${category}&searchText=${searchText}">이전</a>
			</c:if>
	
			<c:forEach begin="${startPage}" end="${endPage}" var="index">
				<a href="./main.ino?curPage=${index}&category=${category}&searchText=${searchText}" style="margin-left: 10px;">${index}</a>
			</c:forEach>
			<c:if test="${curRange < rangeCnt}">
				<a href="./main.ino?curPage=${endPage+1}&category=${category}&searchText=${searchText}">다음</a>
			</c:if>
			<c:if test="${curRange != rangeCnt}">
				<a href="./main.ino?curPage=${pageCnt}&category=${category}&searchText=${searchText}">끝</a>
			</c:if>
			</c:when>	
		</c:choose>
	
	
<%-- 		<c:forEach items="${list}" var="dto"> --%>
<%-- 			<div style="width: 50px; float: left;">${dto.origin_num }</div>	 --%>
<%-- 			<div style="width: 300px; float: left;"><a href="./freeBoardDetail.ino?num=${dto.origin_num }">${dto.name }</a></div> --%>
<%-- 			<div style="width: 150px; float: left;">${dto.title }</div> --%>
<%-- 			<div style="width: 150px; float: left;">${dto.new_day }</div>  --%>
<!-- 			<hr style="width: 600px"> -->
<%-- 		</c:forEach> --%>
		
		
	<div>
		<select id = "searchCategory" name="searchCategory" style="width: 70px; height: 25px; margin-left: 40px;">
   	 		<option value="TITLE">제목</option>
   			<option value="NAME">작성자</option>
		</select>
		<input type="text" name= "searchText" id = "searchText"size="20">
		<input type="button" name= "searchButton" value="검색" size="20" onclick="search()">
	</div>

</body>
</html>