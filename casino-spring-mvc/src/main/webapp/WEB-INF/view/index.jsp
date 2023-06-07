<%@page contentType = "text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored = "false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
  <head>
    <title>Home</title>
    <jsp:include page="shared/_head.jsp" />
  </head>
  <body>
    <jsp:include page="shared/_nav.jsp" />

    <div class="content">
      <h1>Welcome</h1>
      <p>${pageContext.request.contextPath}</p>
      <div class="row">
        <div class="col-md-3">
          <h1><u>Main lots</u></h1>
        </div>
      </div>
      <div class="row">
        <div class="col-md-3">
          <div class="row">
            <img class="small-title-image" src="https://www.scoopearth.com/wp-content/uploads/2022/04/photo-1592398191627-25b41eeaa398.jpg" alt="">
          </div>
          <div class="row">
            <h2>Title</h2>
          </div>
        </div>
        <div class="col-md-1"></div>
        <div class="col-md-3">
          <div class="row">
            <img class="small-title-image" src="https://www.scoopearth.com/wp-content/uploads/2022/04/photo-1592398191627-25b41eeaa398.jpg" alt="">
          </div>
          <div class="row">
            <h2>Title</h2>
          </div>
        </div>
        <div class="col-md-1"></div>
        <div class="col-md-3">
          <div class="row">
            <img class="small-title-image" src="https://www.scoopearth.com/wp-content/uploads/2022/04/photo-1592398191627-25b41eeaa398.jpg" alt="">
          </div>
          <div class="row">
            <h2>Title</h2>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col-md-3">
          <h1><u>Current history</u></h1>
        </div>
      </div>
      <div class="row">
        <table class="table bg-light text-dark">
          <thead>
              <tr>
                  <th>#</th>
                  <th>login</th>
                  <th>date</th>
                  <th>status</th>
                  <th>delta</th>
              </tr>
          </thead>
          <tbody>
              <tr>
                <td>1</td>
                <td>Login1</td>
                <td>01-02-2022 08:20:33</td>
                <td>Won</td>
                <td>+ 100</td>
              </tr>
              <tr>
                <td>1</td>
                <td>Login1</td>
                <td>01-02-2022 08:20:33</td>
                <td>Loss</td>
                <td>- 100 000</td>
              </tr>
              <tr>
                <td>3</td>
                <td>Login3</td>
                <td>01-02-2022 08:20:33</td>
                <td>Won</td>
                <td>+ 100</td>
              </tr>
              <!-- <c:forEach items="${list}" var="bonus">
                  <spring:url value="/bonus/edit/${bonus.getId()}" var="editLink" />
                  
              </c:forEach> -->
          </tbody>
         <table>
      </div>
    </div>

  </body>
</html>
