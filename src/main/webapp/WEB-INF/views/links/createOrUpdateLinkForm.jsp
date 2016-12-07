<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="linker" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

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
