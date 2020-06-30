<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <% 
    	Object blogger = session.getAttribute("blogger");
    %>
  <nav class="navbar navbar-expand-lg navbar-light fixed-top" id="mainNav">
    <div class="container">
    	<% if(blogger != null) {%>
      		<a class="navbar-brand" href="/bloggers/${sessionScope.blogger.id}">${sessionScope.blogger.uName}'s Blogger</a>
      	<% } else { %>
      		<a class="navbar-brand" href="/bloggers/login">Please Login</a>
      	<% } %>
      <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
        Menu
        <i class="fas fa-bars"></i>
      </button>
      <div class="collapse navbar-collapse" id="navbarResponsive">
        <ul class="navbar-nav ml-auto">
          <% if(blogger != null) { %>
          <li class="nav-item">
            <a class="nav-link" href="/blogs/new">write blog</a>
          </li>
           <% } %>
          <li class="nav-item">
            <a class="nav-link" href="/blogs/all?curPage=1">show blogs</a>
          </li>
          <form class="form-inline ml-3" action="/blogs/all" method="get"> 
          <div class="input-group input-group-sm">
			<input id="curPage" type="hidden" name="curPage" value=1>
			<input class="form-control form-control-navbar" type="search" name="keyword" placeholder="Search" aria-label="Search">
			<div class="input-group-append">
			<button class="btn btn-navbar" type="submit">
				<i class="fas fa-search"></i> 
			</button>
			</div>
		  </div>
		  </form>
          <% if(blogger == null) { %>
          <li class="nav-item">
            <a class="nav-link" href="/bloggers/new">Sing In</a>
          </li>
          <% } %>
          <li class="nav-item">
          	<% if(blogger == null) { %>
            <a class="nav-link" href="/bloggers/login">Login</a>
            <% } else { %>
            <a class="nav-link" href="/bloggers/logout">Logout</a>
            <% } %>
          </li>
          
          <li class="nav-item dropdown">
	        <a class="nav-link dropdown-toggle" href="#" id="dropdown04" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">Blog</a>
	        <div class="dropdown-menu" aria-labelledby="dropdown04">
	          <a class="dropdown-item" href="blog-create.jsp">Create Blog</a>
	          <a class="dropdown-item" href="blog-read-list.do">Read Blog List</a>
	          <a class="dropdown-item" href="#">Something else here</a>
	        </div>
	      </li>
          
        </ul>
      </div>
    </div>
  </nav>
