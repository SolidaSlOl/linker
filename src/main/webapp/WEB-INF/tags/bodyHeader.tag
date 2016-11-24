<%@ tag pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<div class="navbar navbar-inverse bg-primary">
  <a class="navbar-brand" href="/">Home</a>
  <ul class="nav navbar-nav">
    <c:if test="${pageContext.request.userPrincipal.name != null}">
      <li>
        <a class="nav-link" href="/users/my_links">My links</a>
      </li>
    </c:if>
  </ul>

  <form class="navbar-form navbar-right">
    <c:choose>
      <c:when test="${pageContext.request.userPrincipal.name != null}">
        <form class="navbar-form navbar-right" method="POST" action="${contextPath}/logout">
          <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
        <button class="btn btn-sm align-middle">
         <a onclick="document.forms['logoutForm'].submit()">Logout</a></h2>
        </button>
      </c:when>

      <c:otherwise>
        <button class="btn btn-sm align-middle">
          <a href="<c:url value="/login"/>">login</a>
        </button>
        <button class="btn btn-sm align-middle">
          <a href="<c:url value="/registration"/>">register</a>
        </button>
      </c:otherwise>
    </c:choose>
  </form>
</div>

<c:if test="${pageContext.request.userPrincipal.name != null}">
  <form id="logoutForm" method="POST" action="${contextPath}/logout">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
  </form>
</c:if>
