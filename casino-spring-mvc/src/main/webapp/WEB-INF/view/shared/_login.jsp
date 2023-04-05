<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<spring:url value="/account/login" var="loginLink" />
<spring:url value="/account/register" var="registerLink" />
<spring:url value="/account/profile" var="profileLink" />
<spring:url value="/account/balance" var="balanceLink" />
<spring:url value="/account/logout" var="logoutLink" />

<ul class="navbar-nav">
  <sec:authorize access="!isAuthenticated()">
    <li class="nav-item">
      <a class="nav-link text-dark" href="${registerLink}">Register</a>
    </li>
    <li class="nav-item">
      <a class="nav-link text-dark" href="${loginLink}">LogIn</a>
    </li>
  </sec:authorize>
  <sec:authorize access="isAuthenticated()">
    <li class="nav-item">
      <a id="manage" class="nav-link text-dark" href="${profileLink}"
        >Hello, world!
      </a>
    </li>
    <li class="nav-item">
      <a class="nav-link text-dark" href="${balanceLink}">Balance</a>
    </li>
    <li class="nav-item">
      <a class="nav-link text-dark" href="${logoutLink}">LogOut</a>
    </li>
  </sec:authorize>
</ul>
