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
        <div class="col-md-5">
          <div class="row">
            <img
              class="small-title-image"
              src="${lot.link}"
              alt="casino image"
            />
          </div>
          <div class="row" style = "border-top: 1px solid black; margin-top: 10px;">
            <br/>
          </div>
          <div class="row form-group">
            <div class="col-md-1"></div>
            <form:form method="POST" class="row" modelAttribute="viewModel">

              <div class="col-md-3">
                <form:input class="form-control" type="number" step="0.01" id="sizeOfBet" path = "betSize"/>
                <form:errors path="betSize" cssClass="error"/>
              </div>
              <div class="col-md-8">
                <form:select path="outcomeId">
                    <form:option value="0" label="Выберите исход"/>
                    <form:options items="${outcomesMap}" />
                </form:select>
              </div>
              <div class="col-md-1">
                <sec:authorize access="hasAnyAuthority('Player')">
                  <div class="row">
                    <form:button type="submit" class="btn btn-primary">Bet</form:button>
                  </div>
                </sec:authorize>
              </div>
            </form:form>
          </div>
        </div>
        <div class="col-md-1"></div>
        <div class="col-md-5">
          <div class="row h-25">
            <div class="col text-info"><h2><c:out value="${lot.getDescription()}" /></h2></div>
          </div>
          <div class="row" style = "border-top: 1px solid black;">
            <div class="col-md-8">
              <c:if test="${gameResult != null}">
                <div class="row">
                  <div class="col-md-8">
                    <c:if test="${gameWin == true}">
                      <h2>You <b class = "text-danger">WIN</b>!</h2>
                    </c:if>
                    <c:if test="${gameWin == false}">
                      <h2 class = "text-dark"><c:out value="Better luck next time!"/></h2>
                    </c:if>  
                  </div>
                </div>
                <div class="row">
                  <div class="col-md-6">
                    <h3>
                      <u>Game result:</u>
                    </h3>
                  </div>
                  <div class="col-md-6">
                    <h2><c:out value="${gameResult}" /></h2>
                  </div>
                </div>
              </c:if>
            </div>  
          </div>
        </div>
      </div>

     
      
    </main>
  </body>
</html>
