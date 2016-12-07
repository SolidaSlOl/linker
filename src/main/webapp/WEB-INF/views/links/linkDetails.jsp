<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="linker" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


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

<linker:layout title="Link details">

  <spring:url value="${link.original}" var="original">
    <spring:param name="original" value="${link.original}"/>
  </spring:url>

  <p><b>Original link: </b> <a href="${fn:escapeXml(original)} ">
    <c:out value="${link.original}"/>
  </a></p>

  <spring:url value="/{shorten}" var="shorten">
    <spring:param name="shorten" value="${link.shorten}"/>
  </spring:url>

  <p><b>Shorten link:</b> <a href="${fn:escapeXml(shorten)} ">
    <c:out value="/${link.shorten}"/>
  </a></p>

  <p><b>Description: </b> ${link.description}</p>

  <p><b>Tags: </b>
    <c:forEach items="${link.tags}" var="tag">

        <spring:url value="/tags/{tagName}" var="tagName">
          <spring:param name="tagName" value="${tag.name}"/>
        </spring:url>

        <a href="${fn:escapeXml(tagName)} ">
          <c:out value="${tag.name} "/>
        </a>
    </c:forEach>

  <c:if test="${pageContext.request.userPrincipal.name == link.user.username}">
    <p><b>Clicks: </b> ${link.clicks}</p>
  </c:if>

</linker:layout>

