<%@ tag pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="simpleLink" tagdir="/WEB-INF/tags" %>

<%@ attribute name="title" required="true" %>
<%@ attribute name="css" required="false" %>

<!DOCTYPE html>
<html>
<simpleLink:htmlHeader css="${css}"/>

<body>

<div class="container">

  <jsp:doBody/>

</div>
<simpleLink:footer />

</body>

</html>