<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<spring:url value="/lots" var="lotsLink" />
<spring:url value="/index" var="homeLink" />
<spring:url value="/account/manage" var="manageUsersLink" />

<header>
  <nav
    class="navbar navbar-expand-sm navbar-toggleable-sm navbar-light bg-white border-bottom box-shadow mb-3"
  >
    <div class="container-fluid">
      <a
        class="navbar-brand"
        asp-area=""
        asp-controller="Home"
        asp-action="Index"
        >Casino</a
      >
      <button
        class="navbar-toggler"
        type="button"
        data-bs-toggle="collapse"
        data-bs-target=".navbar-collapse"
        aria-controls="navbarSupportedContent"
        aria-expanded="false"
        aria-label="Toggle navigation"
      >
        <span class="navbar-toggler-icon"></span>
      </button>
      <div
        class="navbar-collapse collapse d-sm-inline-flex justify-content-between"
      >
        <ul class="navbar-nav flex-grow-1">
          <li class="nav-item">
            <a class="nav-link text-dark" href="${homeLink}">Home</a>
          </li>
          <li class="nav-item">
            <a class="nav-link text-dark" href="${lotsLink}">Lots</a>
          </li>
          <sec:authorize access="hasAnyAuthority('Admin')">
            <li class="nav-item">
              <a class="nav-link text-dark" href="${manageUsersLink}">Manage Users</a>
            </li>
          </sec:authorize>
        </ul>
        <jsp:include page="_login.jsp" />
      </div>
    </div>
  </nav>
</header>
