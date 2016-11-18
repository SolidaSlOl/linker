<%@ tag pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="simpleLink" tagdir="/WEB-INF/tags" %>

<%@ attribute name="pageName" required="true" %>
<%@ attribute name="title" required="true" %>

<!DOCTYPE html>
<html>
<simpleLink:htmlHeader/>

<body>

<div class="container">
  <simpleLink:bodyHeader/>

  <jsp:doBody/>

</div>
<simpleLink:footer/>

</body>

</html>