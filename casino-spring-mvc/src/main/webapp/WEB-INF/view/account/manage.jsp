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
      <spring:url value="/bonus/users" var="bonusesLink" />
      <div class="row" style="border-bottom: 2px solid black">
        <div class="col-md-2">
          <h1>Manage users:</h1>
        </div>
        <div class="col-md-2">
          <a href="${bonusesLink}" class="link text-info"
            ><h2>Users bonuses</h2></a
          >
        </div>
      </div>
      <br />
      <c:forEach items="${viewModel.usersRoles}" var="user">
        <div class="row">
          <div class="col-md-1">
            <a class="text-dark" href="${userLink}">
              <h3>
                <b>${user.getLogin()}</b>
              </h3>
            </a>
          </div>
          <div class="col-md-2 row">
            <c:forEach items="${viewModel.roles}" var="role">
              <div class="col-md-4">
                <c:choose>
                  <c:when test="${user.checkRole(role.name)}">
                    <spring:url
                      value="/account/manage/remove?name=${user.login}&role=${role.getId()}"
                      var="link"
                    />
                    <a href="${link}" class="text-primary"
                      ><h3>${role.getName()}</h3></a
                    >
                  </c:when>
                  <c:otherwise>
                    <spring:url
                      value="/account/manage/add?name=${user.login}&role=${role.getId()}"
                      var="link"
                    />
                    <a href="${link}" class="text-secondary"
                      ><h3>${role.getName()}</h3></a
                    >
                  </c:otherwise>
                </c:choose>
              </div>
            </c:forEach>
          </div>
        </div>
      </c:forEach>
    </main>
  </body>
</html>
