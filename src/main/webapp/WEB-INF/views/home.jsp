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

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<linker:layout title="Home" >

	<c:if test="${pageContext.request.userPrincipal.name != null}">
        <a href="/links/new" class="btn btn-primary" role="button">
          Add link
        </a>
	</c:if>
  <table class="table">
    <thead class="thead-inverse">
    <tr>
      <th>Original URL</th>
      <th>Short URL</th>
      <th></th>
    </tr>
    </thead>
    <c:forEach items="${links}" var="link">
      <tbody>
        <td>
          <spring:url value="${link.original}" var="original">
            <spring:param name="original" value="${link.original}"/>
          </spring:url>

          <a href="${fn:escapeXml(original)} ">
            <c:out value="${link.original}"/>
          </a>
        </td>
        <td>
          <spring:url value="/{shorten}" var="shorten">
            <spring:param name="shorten" value="${link.shorten}"/>
          </spring:url>

          <a href="${fn:escapeXml(shorten)} ">
            <c:out value="/${link.shorten}"/>
          </a>
        </td>
        <td>
          <spring:url value="/links/{linkId}" var="linkId">
            <spring:param name="linkId" value="${link.id}"/>
          </spring:url>

          <a href="${fn:escapeXml(linkId)} ">
            <c:out value="More information"/>
          </a>
        </td>
      </tbody>
    </c:forEach>
  </table>
</linker:layout>

