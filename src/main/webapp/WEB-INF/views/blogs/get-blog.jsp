<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>

  <%@ include file="../main/i-head.jsp" %>
  <title>Post : Clean Blog - Start Bootstrap Theme</title>

</head>



<body>
	<% request.setCharacterEncoding("utf-8"); %>
  <!-- Navigation -->
  <%@ include file="../main/i-nav.jsp" %>
  
  <!-- Page Header -->
  <header class="masthead" style="background-image: url('/img/post-bg.jpg')">
    <div class="overlay"></div>
    <div class="container">
      <div class="row">
        <div class="col-lg-8 col-md-10 mx-auto">
          <div class="post-heading">
            <h2>Man must explore, and this is exploration at its greatest</h2>
            <h3 class="subheading"></h3>
            <span class="meta">Posted by
              <a href="#">Start Bootstrap</a>
              on August 24, 2019</span>
          </div>
        </div>
      </div>
    </div>
  </header>

  <!-- Post Content -->
  <article>
    <div class="container">
      <div class="row">
        <div class="col-lg-8 col-md-10 mx-auto">
          <h2 class="section-heading">${blog.title }</h2>       
		  <c:url value="/files/${blog.filepath}" var="url"/>
		  <p>${blog.content }</p>
		  <img class="img-fluid" id="image" src="" alt="">
		  <script>
			document.getElementById("image").src="/files/${blog.filepath }";
		  </script>
          <span class="caption text-muted">To go places and do things that have never been done</span>
          <p> Written ${blog.regDateTime }</p>
          <p>Photographs by <a href="#">${blog.blogger }</a>.</p>
          <div class="clearfix">
	         <a class="btn btn-primary float-right" href="edit/${blog.id }">수정 &rarr;</a>
	         
	         <a class="btn btn-primary float-right" href="delete/${blog.id }">삭제 &rarr;</a>
	       </div>
        </div>
      </div>
      
    </div>
  </article>
        
  <hr>

  <!-- Footer -->
  <%@ include file="../main/i-footer.jsp" %>
  
</body>

</html>
