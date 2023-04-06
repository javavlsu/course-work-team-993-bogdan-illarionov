<%@page contentType = "text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored = "false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
  <head>
    <title>Manage users</title>
    <jsp:include page="../shared/_head.jsp" />
  </head>
  <body>
    <jsp:include page="../shared/_nav.jsp" />
    
    <main class="content">
        <h1>Manage users!</h1>
        <c:forEach items="${users}" var="user">
            <spring:url value="/account/manage/user?name=${user.getLogin()}" var="userLink" />
            <div class="row">
                <div class="col-md-1">
                    <a class="text-dark" href="${userLink}">
                        <h3>
                            <b>${user.getLogin()}</b>
                        </h3>    
                    </a>
                </div>

                <div class="col-md-2 row">
                    <c:forEach items="${user.getRoles()}" var="role">
                        <div class="col-md-4">
                            <h3>${role.getName()}</h3>
                        </div>
                    </c:forEach>
                
                </div>
            </div>
        </c:forEach>
      </main>

  </body>
</html>
