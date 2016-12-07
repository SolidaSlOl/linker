<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="linker" tagdir="/WEB-INF/tags" %>

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

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<linker:layout-login title="Home" css="/resources/css/common.css">
  <form:form method="POST" modelAttribute="userForm" class="form-signin">
    <h2 class="form-signin-heading">Create your account</h2>
    <spring:bind path="username">
      <div class="form-group ${status.error ? 'has-error' : ''}">
        <form:input type="text" path="username" class="form-control" placeholder="Username"
                    autofocus="true"></form:input>
        <form:errors path="username"></form:errors>
      </div>
    </spring:bind>

    <spring:bind path="password">
      <div class="form-group ${status.error ? 'has-error' : ''}">
        <form:input type="password" path="password" class="form-control" placeholder="Password"></form:input>
        <form:errors path="password"></form:errors>
      </div>
    </spring:bind>

    <spring:bind path="passwordConfirm">
      <div class="form-group ${status.error ? 'has-error' : ''}">
        <form:input type="password" path="passwordConfirm" class="form-control"
                    placeholder="Confirm your password"></form:input>
        <form:errors path="passwordConfirm"></form:errors>
      </div>
    </spring:bind>

    <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
  </form:form>
</linker:layout-login>
