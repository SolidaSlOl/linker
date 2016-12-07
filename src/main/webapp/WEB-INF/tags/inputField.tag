<%@ tag pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ attribute name="name" required="true" rtexprvalue="true"
              description="Name of corresponding property in bean object" %>
<%@ attribute name="label" required="true" rtexprvalue="true"
              description="Label appears in red color if input is considered as invalid after submission" %>
<%@ attribute name="type" required="false" rtexprvalue="true"
              description="difines input type" %>
<%@ attribute name="input" required="true" rtexprvalue="true"
              description="difines defines field type" %>

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

<spring:bind path="${name}">
  <c:set var="cssGroup" value="form-group ${status.error ? 'has-error' : '' }"/>
  <c:set var="valid" value="${not status.error and not empty status.actualValue}"/>
  <div class="${cssGroup}">
    <label class="col-sm-2 control-label">${label}</label>

    <div class="col-sm-10">
      <c:choose>
        <c:when test="${input == 'form-group'}">
          <form:input class="form-control" path="${name}" type="text"/>
        </c:when>

        <c:when test="${input == 'textArea'}">
          <form:textarea path="${name}" cols="40" rows="5"/>
        </c:when>

        <c:otherwise>
          Mistake in input value
        </c:otherwise>
      </c:choose>

      <c:if test="${description != ''}">
        <small class="form-text text-muted">${description}</small>
      </c:if>

      <c:if test="${valid}">
        <span class="glyphicon glyphicon-ok form-control-feedback" aria-hidden="true"></span>
      </c:if>
      <c:if test="${status.error}">
        <span class="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true"></span>
        <span class="help-inline">${status.errorMessage}</span>
      </c:if>
    </div>
  </div>

</spring:bind>
