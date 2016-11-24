<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="linker" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<linker:layout title="Home" >

	<c:if test="${pageContext.request.userPrincipal.name != null}">
    <a href="/links/create" class="btn btn-primary" role="button">
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
          <spring:url value="http://localhost:8080/{shorten}" var="shorten">
            <spring:param name="shorten" value="${link.shorten}"/>
          </spring:url>

          <a href="${fn:escapeXml(shorten)} ">
            <c:out value="localhost:8080/${link.shorten}"/>
          </a>
        </td>
        <td>
          <spring:url value="http://localhost:8080/links/{linkId}" var="linkId">
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

