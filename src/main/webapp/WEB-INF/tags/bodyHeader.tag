<%@ tag pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="navbar navbar-inverse bg-primary">
  <a class="navbar-brand" href="/">Home</a>

  <form class="navbar-form navbar-right">
    <c:choose>
      <c:when test="${pageContext.request.userPrincipal.name != null}">

        Welcome : ${pageContext.request.userPrincipal.name}
        <button class="btn btn-sm align-middle">
          <a onclick="document.forms['logoutForm'].submit()">Logout</a>
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
