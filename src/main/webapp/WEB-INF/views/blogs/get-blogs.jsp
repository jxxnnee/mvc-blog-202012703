<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>

<%@ include file="../main/i-head.jsp"%>
<title>Blog List : Clean Blog - Start Bootstrap Theme</title>

</head>

<body>

	<!-- Navigation -->
	<jsp:include page="../main/i-nav.jsp" />

	<!-- Page Header -->
	<header class="masthead"
		style="background-image: url('/img/home-bg.jpg')">
		<div class="overlay"></div>
		<div class="container">
			<div class="row">
				<div class="col-lg-8 col-md-10 mx-auto">
					<div class="site-heading">
						<h1>Blog List</h1>
						<span class="subheading">블로그 목록</span>
					</div>
				</div>
			</div>
		</div>
	</header>

	<!-- Main Content -->
	<div class="container">
		<div class="row">
		
			<div class="col-lg-8 col-md-10 mx-auto">
				<c:if test="${pagination.orderBy == 'DESC'}">
				<a class="btn btn-primary float-right" href="../blogs/all?curPage=1&orderBy=ASC">작성일 &uarr;</a>
				</c:if>
				<c:if test="${pagination.orderBy == 'ASC'}">
				<a class="btn btn-primary float-right" href="../blogs/all?curPage=1&orderBy=DESC">작성일순 &darr;</a>
				</c:if>
				<c:forEach items="${requestScope.blogList }" var="blog">
					<div class="post-preview">
						<a href="${blog.id }">
							<h2 class="post-title">${blog.title }</h2>
							<h3 class="post-subtitle">${blog.content }</h3>
						</a>
						<p class="post-meta">
							Posted by <a href="#">${blog.blogger }</a> on ${blog.regDateTime }
						</p>
					</div>
					<hr>
				</c:forEach>
				<!-- Pager -->
				<div class="clearfix">
						<div class="row">
						<div class="col-sm-12 col-md-4">Total : ${pagination.totalRowCount } blogs</div> 
						<div class="col-sm-12 col-md-6">
							<div class="dataTables_paginate paging_simple_numbers" id="dataTable_paginate"> 
								<ul class="pagination">
									<c:if test="${pagination.prev}">
									<li class="paginate_button page-item previous" id="dataTable_previous"> 
									<a href="../blogs/all?curPage=${pagination.startPage - 1}&orderBy=${pagination.orderBy }" aria-controls="dataTable" data-dt-idx="0" tabindex="0" class="page-link">Prev</a> 
									</li>
									</c:if>
									
									<c:forEach var="i" begin="${pagination.startPage }" end="${pagination.endPage }">
									<li class="paginate_button page-item
									<c:if test="${pagination.curPage == i }"> active </c:if> ">
									<a href="../blogs/all?curPage=${i}&orderBy=${pagination.orderBy }" aria-controls="dataTable" data-dt-idx="1" tabindex="0" class="page-link">${i} </a> 
									</li>
									</c:forEach>
									
									<c:if test="${pagination.next}">
									<li class="paginate_button page-item next" id="dataTable_next">
									<a href="../blogs/all?curPage=${pagination.endPage + 1}&orderBy=${pagination.orderBy }" aria-controls="dataTable" data-dt-idx="7" tabindex="0" class="page-link">Next</a> 
									</li>
									</c:if> 
								</ul>
							</div>
						</div>
						
						
						</div>
					</div>
				</div>
			</div>
		</div>
	<hr>

	<!-- Footer -->
	<%@ include file="../main/i-footer.jsp"%>

	<!-- Bootstrap core JavaScript -->
	<script src="/vendor/jquery/jquery.min.js"></script>
	<script src="/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Custom scripts for this template -->
	<script src="/js/clean-blog.min.js"></script>

</body>

</html>
