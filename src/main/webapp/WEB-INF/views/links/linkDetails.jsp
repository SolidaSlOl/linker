<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="linker" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<linker:layout title="Link details">

  <spring:url value="${link.original}" var="original">
    <spring:param name="original" value="${link.original}"/>
  </spring:url>

  <p><b>Original link: </b> <a href="${fn:escapeXml(original)} ">
    <c:out value="${link.original}"/>
  </a></p>

  <spring:url value="http://localhost:8080/{shorten}" var="shorten">
    <spring:param name="shorten" value="${link.shorten}"/>
  </spring:url>

  <p><b>Shorten link:</b> <a href="${fn:escapeXml(shorten)} ">
    <c:out value="localhost:8080/${link.shorten}"/>
  </a></p>

  <p><b>Description: </b> ${link.description}</p>

  <p><b>Tags: </b> ${link.tags}</p>

  <c:if test="${pageContext.request.userPrincipal.name != null}">
    <p><b>Clicks: </b> ${link.clicks}</p>
  </c:if>
  
</linker:layout>

