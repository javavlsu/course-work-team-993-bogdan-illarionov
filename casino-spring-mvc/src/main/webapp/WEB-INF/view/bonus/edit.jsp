<%@page contentType = "text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored = "false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
  <head>
    <title>Edit bonus</title>
    <jsp:include page="../shared/_head.jsp" />
    <spring:url value="/account/register" var="registerLink" />
  </head>
  <body>
    <jsp:include page="../shared/_nav.jsp" />
    <main class="content">
      <form:form method="POST" class="row" modelAttribute="viewModel">
        <div class="col-md-1"></div>
        <div class="col-md-5">
          <h1>Edit bonus:</h1>
          <form:hidden path = "id"/>
          <form:hidden path = "expireTypeId" />
          <form:hidden path = "triggerActionTypeId" />
          <br/>
          <div class="row">
            <div class="col-md-8">
              <div class="row">
                <div class="col-md-3">
                  <label for="nameString">Name:</label>
                </div>
                <div class="col-md-6">
                  <form:input
                    class="form-control"
                    type="text"
                    id="nameString"
                    path="name"
                  />
                </div>
              </div>
              <div class="row">
                <div class="col-md-3"></div>
                <div class="col-md-6">
                  <form:errors path="name" cssClass="error" />
                </div>
              </div>
            </div>
          </div>
          <br />
          <div class="row">
            <div class="col-md-8">
              <div class="row">
                <div class="col-md-3">
                  <label for="enableBool">Enabled: </label>
                </div>
                <div class="col-md-6">
                  <form:checkbox
                    path="isEnabled"
                    value=" Enabled"
                    id="enableBool"
                  />
                </div>
              </div>
            </div>
          </div>
          <br />
          <div class="row">
            <div class="col-md-8">
              <div class="row">
                <div class="col-md-3">
                  <label for="countInt">Count:</label>
                </div>
                <div class="col-md-6">
                  <form:input
                    class="form-control"
                    type="number"
                    id="countInt"
                    path="triggerCount"
                  />
                </div>
              </div>
              <div class="row">
                <div class="col-md-3"></div>
                <div class="col-md-6">
                  <form:errors path="triggerCount" cssClass="error" />
                </div>
              </div>
            </div>
          </div>
          <br />
          <div class="row">
            <div class="col-md-8">
              <div class="row">
                <div class="col-md-3">
                  <label for="termString">Term</label>
                </div>
                <div class="col-md-6">
                  <form:input
                    class="form-control"
                    type="text"
                    id="termString"
                    path="toTerm"
                  />
                </div>
              </div>
              <div class="row">
                <div class="col-md-3"></div>
                <div class="col-md-6">
                  <form:errors path="toTerm" cssClass="error" />
                </div>
              </div>
            </div>
          </div>
          <br />
          <div class="row">
            <div class="col-md-8">
              <div class="row">
                <div class="col-md-3">
                  <label for="lotsIdsString">Lots ids</label>
                </div>
                <div class="col-md-6">
                  <form:input
                    class="form-control"
                    type="text"
                    id="lotsIdsString"
                    path="lotsIds"
                  />
                </div>
              </div>
              <div class="row">
                <div class="col-md-3"></div>
                <div class="col-md-6">
                  <form:errors path="lotsIds" cssClass="error" />
                </div>
              </div>
            </div>
          </div>
          <br />
          <div class="row">
            <div class="col-md-8">
              <div class="row">
                <div class="col-md-3"><label for="numberKoef">Koef</label></div>
                <div class="col-md-6">
                  <form:input
                    class="form-control"
                    type="number"
                    step="0.01"
                    id="numberKoef"
                    path="bonusKoef"
                  />
                </div>
              </div>
              <div class="row">
                <div class="col-md-3"></div>
                <div class="col-md-6">
                  <form:errors path="bonusKoef" cssClass="error" />
                </div>
              </div>
            </div>
          </div>
          <br />
          <div class="row">
            <div class="col-md-8">
              <div class="row">
                <div class="col-md-3">
                  <label for="select1">Expire type:</label>
                </div>
                <div class="col-md-6">
                  <b><c:out value="${viewModel.getStringExpireType()}" /></b>
                </div>
              </div>
            </div>
          </div>
          <br />
          <div class="row">
            <div class="col-md-8">
              <div class="row">
                <div class="col-md-3">
                  <label for="select2">Trigger type:</label>
                </div>
                <div class="col-md-6">
                  <b><c:out value="${viewModel.getStringTriggerType()}" /></b>
                </div>
              </div>
            </div>
          </div>
          <br />
          <div class="row">
            <div class="col-md-8">
              <div class="row">
                <div class="col-md-1"></div>
                <div class="col-md-6">
                  <form:button type="submit" class="btn btn-primary"
                    >Edit</form:button
                  >
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="col-md-4"></div>
      </form:form>
    </main>
  </body>
</html>
