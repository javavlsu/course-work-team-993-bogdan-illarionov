<%@page contentType = "text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored = "false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 

<html>
  <head>
    <title>GAME</title>
    <jsp:include page="../shared/_head.jsp" />
  </head>
  <body>
    <jsp:include page="../shared/_nav.jsp" />
    <main class="content">
        <h1 align="center">Choose any lot now!</h1>

        <form:form method="POST" class="col">
              <div class="row">
                <div class="col-md-5 form-group">
                  <label for="searchString">Search</label>
                  <input class="form-control" type="text" id="searchString"/>
                </div>
                <div class="col-md-3">
                  <button id="searchSubmit" type="submit" class="w-70 btn btn-dark">Поиск</button>
                </div>
              </div>
        </form:form>
        <br/>
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

      </main>
  </body>
</html>
