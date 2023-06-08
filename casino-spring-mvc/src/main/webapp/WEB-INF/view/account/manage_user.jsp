<%@page contentType = "text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored = "false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
  <head>
    <title>Manage user</title>
    <jsp:include page="../shared/_head.jsp" />
  </head>
  <body>
    <jsp:include page="../shared/_nav.jsp" />
    
    <main class="content">
        <h1>Manage ${login}!</h1>
        
        <br/>
        <br/>
        <h2>Roles</h2>
        <div class="row">
            
            <c:forEach items="${users_roles}" var="role">
                <spring:url value="/account/manage/user/remove?name=${login}&role=${role.getId()}" var="addLink" />
                <div class="col-md-1">
                    <a class="text-dark" href="${addLink}">
                        <h3>
                            <b>${role.getName()}</b>
                        </h3>    
                    </a>    
                </div>
        </c:forEach>
        </div>
        <br/>
        <h2>Available roles:</h2>
        <div class="row">
            
            <c:forEach items="${roles}" var="role">
                <spring:url value="/account/manage/user/add?name=${login}&role=${role.getId()}" var="removeLink" />
                <div class="col-md-1">
                    <a class="text-dark" href="${removeLink}">
                        <h3>
                            <b>${role.getName()}</b>
                        </h3>    
                    </a>    
                </div>
        </c:forEach>
        </div>

      </main>

  </body>
</html>
