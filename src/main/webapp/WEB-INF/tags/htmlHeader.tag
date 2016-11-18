<%@ tag pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>${title}</title>
  <spring:url value="/resources/core/css/bootstrap.min.css" var="bootstrapCss"/>
  <spring:url value="/resources/core/css/belarusgo.css" var="belarusgoCss"/>
  <link href="${bootstrapCss}" rel="stylesheet"/>
  <link href="${belarusgoCss}" rel="stylesheet"/>
</head>