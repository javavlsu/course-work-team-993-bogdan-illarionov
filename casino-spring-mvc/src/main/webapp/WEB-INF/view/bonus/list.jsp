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
      <br />
      <spring:url value="/bonus/create" var="createLink" />
      <div class="row">
        <div class="col-md-2">
          <h2>Add bonus:</h2>
        </div>
        <div class="col-md-4">
          <a class="btn btn-primary" href="${createLink}"><h4>New</h4></a>
        </div>
      </div>
      <br />
      <br />
      <div class="row form-group">
        <table class="table bg-light text-dark">
          <thead>
            <tr>
              <th>Id</th>
              <th>Name</th>
              <th>Trigger</th>
              <th>Expire</th>
              <th>Enabled</th>
              <th></th>
              <th></th>
            </tr>
          </thead>
          <tbody>
            <c:forEach items="${list}" var="bonus">
              <spring:url value="/bonus/edit/${bonus.getId()}" var="editLink" />
              <spring:url
                value="/bonus/edit/${bonus.getId()}"
                var="removeLink"
              />
              <tr>
                <td><c:out value="${bonus.getId()}" /></td>
                <td><c:out value="${bonus.getName()}" /></td>
                <td><c:out value="${bonus.getEnumTriggerAction().name()}" /></td>
                <td><c:out value="${bonus.getEnumExpireType().name()}" /></td>
                <td>
                  <c:if test="${bonus.isEnabled}">
                    <h4>&#9989;</h4>
                  </c:if>
                  <c:if test="${!bonus.isEnabled}">
                    <h3 class="text-dark">&#10060;</h3>
                  </c:if>
                </td>
                <td><a class="btn btn-dark" href="${editLink}">Edit</a></td>
                <td>
                  <a class="btn btn-danger" href="${removeLink}">Remove</a>
                </td>
              </tr>
            </c:forEach>
          </tbody>
        </table>
      </div>
    </main>
  </body>
</html>
