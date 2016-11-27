<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="linker" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<linker:layout title="Link list">
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
      <th>Clicks</th>
      <th></th>
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
        <th>
          <c:out value="${link.clicks}"/>
        </th>
        <td>
          <spring:url value="/links/{linkId}" var="linkId">
            <spring:param name="linkId" value="${link.id}"/>
          </spring:url>

          <a href="${fn:escapeXml(linkId)} ">
            <c:out value="More information"/>
          </a>
        </td>
        <td>
          <c:if test="${pageContext.request.userPrincipal.name != null}">
            <a href="/links/${link.id}/edit" class="btn btn-primary" role="button">
              edit
            </a>
          </c:if>
        </td>
      </tbody>
    </c:forEach>
  </table>
</linker:layout>
