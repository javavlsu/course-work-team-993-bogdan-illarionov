<%@page contentType = "text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored = "false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
  <head>
    <title>Bonuses</title>
    <jsp:include page="../shared/_head.jsp" />
  </head>
  <body>
    <jsp:include page="../shared/_nav.jsp" />
    <main class="content">
        <br/>
        <spring:url value="/bonus/create" var="createLink" />
        <h2>Add new bonus</h2>
        <a class="btn btn-primary" href="${createLink}"><h3>Create</h3></a>
        <br/>
        <br/>
        <div class="row form-group">
                   <table class="table bg-light text-dark">
                    <thead>
                        <tr>
                            <th>id</th>
                            <th>name</th>
                            <th>trigger</th>
                            <th>expire</th>
                            <th>enabled</th>
                            <th>edit</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${list}" var="bonus">
                            <spring:url value="/bonus/edit/${bonus.getId()}" var="editLink" />
                            <tr>
                               <td><c:out value="${bonus.getId()}" /></td>
                               <td><c:out value="${bonus.getName()}" /></td>
                               <td><c:out value="${bonus.getStringTriggerType()}" /></td>
                               <td><c:out value="${bonus.getStringExpireType()}" /></td>
                               <td><c:out value="${bonus.getIsEnabled()}" /></td>
                               <td><a class="btn btn-info" href="${editLink}">Edit</a></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                   <table>
        </div>
      </main>
  </body>
</html>
