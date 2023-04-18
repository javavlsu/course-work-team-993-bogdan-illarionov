<%@page contentType = "text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored = "false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
  <head>
    <title>GAME</title>
    <jsp:include page="../shared/_head.jsp" />
  </head>
  <body>
    <jsp:include page="../shared/_nav.jsp" />
    <main class="content">
      <h1><c:out value="Welcome to ${lot.getName()}!" /></h1>
      <div class="row">
        <img
          class="small-title-image"
          src="https://www.scoopearth.com/wp-content/uploads/2022/04/photo-1592398191627-25b41eeaa398.jpg"
          alt="casino image"
        />
        <div class="col-md-4">
            <c:if test="${gameResult != null}">
                <c:if test="${gameWin == true}">
                    <h3 align="center"><c:out value="Congratulations!" /></h3>
                </c:if>
                <c:if test="${gameWin == false}">
                    <h3 align="center"><c:out value="Very sorry. Next time you will definitely be lucky!" /></h3>
                </c:if>
                <h3 align="center"><c:out value="Game result is: ${gameResult}!" /></h3>
            </c:if>

        </div>
      </div>

      <div class="row form-group">
        <form:form method="POST" class="row" modelAttribute="viewModel">
        <div class="col-md-4">
          <form:input class="form-control" type="number" step="0.1" id="sizeOfBet" path = "betSize"/>
        </div>
        <div class="col-md-4">
          <form:select path="outcomeId">
              <form:option value="-" label="Выберите исход"/>
              <form:options items="${outcomesMap}" />
          </form:select>
        </div>
        <div class="col-md-4">
          <sec:authorize access="hasAnyAuthority('Player')">
            <div class="row">
              <form:button type="submit" class="btn btn-primary">Bet</form:button>
            </div>
          </sec:authorize>
        </div>
        </form:form>
      </div>
      <div class="row h-25">
        <div class="col bg-info"><c:out value="${lot.getDescription()}" /></div>
      </div>
    </main>
  </body>
</html>
