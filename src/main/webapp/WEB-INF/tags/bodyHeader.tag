<%@ tag pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%--~
  ~ The MIT License (MIT)
  ~
  ~ Copyright (c) 2016-2016 Mikita Herasiutsin
  ~
  ~ Permission is hereby granted, free of charge, to any person obtaining a copy
  ~ of this software and associated documentation files (the "Software"), to deal
  ~ in the Software without restriction, including without limitation the rights
  ~ to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
  ~ copies of the Software, and to permit persons to whom the Software is
  ~ furnished to do so, subject to the following conditions:
  ~
  ~ The above copyright notice and this permission notice shall be included
  ~ in all copies or substantial portions of the Software.
  ~
  ~ THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
  ~ IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
  ~ FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
  ~ AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
  ~ LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
  ~ OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
  ~ SOFTWARE.
  --%>

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
