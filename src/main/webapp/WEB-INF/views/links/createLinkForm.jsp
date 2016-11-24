<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="linker" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<linker:layout title="Create link">
  <h2>Creating link</h2>

  <form:form modelAttribute="link" class="form-horizontal">
    <div class="form-group row" class="has-feedback">
      <linker:inputField label="Actual link" name="original" type="text"
                            input="form-group"/>
      <linker:inputField label="Tags" name="tag" type="text"
                            input="form-group"/>
      <linker:inputField label="Description" name="description" type="text"
                            input="textArea"/>

    </div>
    <div class="form-group row">
      <div class="col-sm-offset-2 col-sm-8">
        <button class="btn btn-primary" type="submit">
          Add link
        </button>
      </div>
    </div>
  </form:form>
</linker:layout>
