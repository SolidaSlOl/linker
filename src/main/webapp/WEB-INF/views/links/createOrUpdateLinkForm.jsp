<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="linker" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<linker:layout title="Adding link">
  <h2>
    <c:choose>
      <c:when test="${link['new']}">
        Adding link
      </c:when>
      <c:otherwise>
        Updating link
      </c:otherwise>
    </c:choose>
  </h2>

  <form:form modelAttribute="link" class="form-horizontal">
    <div class="form-group row" class="has-feedback">
      <linker:inputField label="Actual link" name="original" type="text"
                            input="form-group"/>
      <linker:inputField label="Tags" name="tagsInString" type="text"
                            input="form-group"/>
      <linker:inputField label="Description" name="description" type="text"
                            input="textArea"/>

    </div>
    <div class="form-group row">
      <div class="col-sm-offset-2 col-sm-8">
        <button class="btn btn-primary" type="submit">
          <c:choose>
            <c:when test="${link['new']}">
              Add link
            </c:when>
            <c:otherwise>
              Update link
            </c:otherwise>
          </c:choose>
        </button>
      </div>
    </div>
  </form:form>
</linker:layout>
