<%@page contentType = "text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored = "false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 

<html>
  <head>
    <title>Users Bonuses</title>
    <jsp:include page="../shared/_head.jsp" />
  </head>
  <body>
    <jsp:include page="../shared/_nav.jsp" />
    <main class="content">
        <div class="row">
            <div class="col">
                <form:form method="POST" modelAttribute="viewModel">
                    <div class="row">
                        <div class="col-md-2 form-group row">
                            <div class="col-md-4">
                                <h3>Users:</h2>
                            </div>
                            <div class="col-md-2">
                                <form:select path="userId">
                                    <form:option value="0" label="Choose user"/>
                                    <form:options items="${usersMap}" />
                                </form:select>
                                <form:errors path="userId" cssClass="error"/>
                            </div>
                        </div>
                        <div class="col-md-2 form-group row">
                            <div class="col-md-5">
                                <h3>Bonues:</h2>
                            </div>
                            <div class="col-md-2">
                                <form:select path="bonusId">
                                    <form:option value="0" label="Choose bonus"/>
                                    <form:options items="${bonusMap}" />
                                </form:select>
                                <form:errors path="bonusId" cssClass="error"/>
                            </div>
                        </div>
                        <div class="col-md-1 form-group row">
                            <div class="col-md-2">
                                <button id="searchSubmit" type="submit" class="w-70 btn btn-primary">Add</button>
                            </div>
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
        <br/>
        <c:forEach items="${usersBonusMap.keySet()}" var="userLogin">
            
            <div class="row">
                <div class="col-md-2" style = "border-right: 1px solid black;">
                    <div class="row" >
                        <div class="col-md-1">
                            <h2><b>Login:</b></h2>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-1" >
                            <h2><c:out value="${userLogin}" /></h2>
                        </div>
                    </div>
                </div>

                <c:forEach items="${usersBonusMap.get(userLogin)}" var="usersBonus">
                    <spring:url value="/bonus/users/${usersBonus.userId}/enable/${usersBonus.bonusId}" var="enableLink" />
                    <spring:url value="/index" var="deleteLink" />
                    <div class="col-md-4">
                        <div class="row">
                            <h3 class = "col-md-4"><u><c:out value="${bonusMap.get(usersBonus.getBonusId())}" /></u></h3>
                            <div class="col-md-2">
                                <a href="${enableLink}" class="btn btn-dark">Switch</a>
                            </div>
                            <div class="col-md-2">
                                <a href="${deleteLink}" class="btn btn-danger">Remove</a>
                            </div>
                        </div>
                        
                        <c:forEach items="${usersBonus.config}" var="bonusParam">
                            <div class="row">
                                <div class="col-md-3">
                                    <h4><b><c:out value="${bonusParam.name}" /></b></h4>
                                </div>
                                <div class="col-md-3">
                                    <h4><c:out value="${bonusParam.value}" /></h4>
                                </div>
                            </div>
                        </c:forEach>
                        
                    </div>
                </c:forEach>
            </div>
            <br/>
            <br/>
            </c:forEach>

      </main>
  </body>
</html>
