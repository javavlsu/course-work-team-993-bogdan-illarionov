<%@page contentType = "text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored = "false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
  <head>
    <title>Mange lot outcomes</title>
    <jsp:include page="../shared/_head.jsp" />
  </head>
  <body>
    <jsp:include page="../shared/_nav.jsp" />
    <main class="content">

        <br/>
        <div class="row form-group">
            <sec:authorize access="hasAnyAuthority('Admin')">
                   <table class="table bg-light text-dark">
                    <thead>
                        <tr>
                            <th>id</th>
                            <th>value</th>
                            <th>koef</th>
                            <th>edit</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${outcomes}" var="outcome">
                            <spring:url value="edit/${outcome.getId()}" var="outcomeLink" />
                            <tr>
                               <td><c:out value="${outcome.getId()}" /></td>
                               <td><c:out value="${outcome.getValue()}" /></td>
                               <td><c:out value="${outcome.getKoef()}" /></td>
                               <td>
                                    <a
                                     href="${outcomeLink}"
                                     class="nav-link text-dark"
                                    >
                                    Edit
                                    </a>
                               </td>
                            </r>
                        </c:forEach>
                    </tbody>
                   <table>
           </sec:authorize>
        </div>
      </main>
  </body>
</html>
