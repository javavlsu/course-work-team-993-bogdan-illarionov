<%@page contentType = "text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored = "false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
  <head>
    <title>Lots</title>
    <jsp:include page="shared/_head.jsp" />
  </head>
  <body>
    <jsp:include page="shared/_nav.jsp" />
    <main class="content">
      <form:form method="POST" class="col">
      <div class="row">
        <div class="col-md-3"></div>
        <div class="col-md-5 form-group">
          <label for="searchString">Search</label>
          <input class="form-control" type="text" id="searchString"/>
        </div>
        <div class="col-md-3">
          <button id="searchSubmit" type="submit" class="w-70 btn btn-dark">Поиск</button>
        </div>
      </div>
      <div class="row">
        <div class="col-md-8">
          <div class="row">
            <c:forEach items="${lots}" var="lot">
              <spring:url value="/lots/${lot.getId()}" var="lotLink" />
              <div class="col-md-3">
                <a
                  href="${lotLink}"
                  class="nav-link text-dark"
                >
                  <img
                    class="small-title-image"
                    src="https://www.scoopearth.com/wp-content/uploads/2022/04/photo-1592398191627-25b41eeaa398.jpg"
                    alt="casino image"
                  />
                  <h2><c:out value="${lot.getName()}" /></h2>
                </a>
              </div>
            </c:forEach>
          </div>
        </div>
        <div class="col-md-4 text-light bg-info">
          <p>Some filter</p>
        </div>
      </div>
    </form:form>
    </main>
  </body>
</html>
