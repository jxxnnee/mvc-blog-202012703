<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <% 
    	Object name = session.getAttribute("name");
    	Object id = session.getAttribute("id");
    %>
  <nav class="navbar navbar-expand-lg navbar-light fixed-top" id="mainNav">
    <div class="container">
    	<% if(name != null) {%>
      		<a class="navbar-brand" href="/bloggers/<%=id %>"><%=name %>'s Blogger</a>
      	<% } else { %>
      		<a class="navbar-brand" href="/bloggers/login">Please Login</a>
      	<% } %>
      <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
        Menu
        <i class="fas fa-bars"></i>
      </button>
      <div class="collapse navbar-collapse" id="navbarResponsive">
        <ul class="navbar-nav ml-auto">
          <% if(name != null) { %>
          <li class="nav-item">
            <a class="nav-link" href="/blogs/new">write blog</a>
          </li>
           <% } %>
          <li class="nav-item">
            <a class="nav-link" href="/blogs/all">show blogs</a>
          </li>
          <% if(name == null) { %>
          <li class="nav-item">
            <a class="nav-link" href="/bloggers/new">Sing In</a>
          </li>
          <% } %>
          <li class="nav-item">
          	<% if(name == null) { %>
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
