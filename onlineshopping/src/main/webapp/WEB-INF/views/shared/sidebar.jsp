<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h1 class="my-4">Shop Name</h1>
<div class="list-group">

    <c:set var="req" value="${pageContext.request}" />
    <c:set var="url">${req.requestURL}</c:set>
    <c:set var="base" value="${fn:substring(url, 0, fn:length(url) - fn:length(req.requestURI))}${req.contextPath}" />
    <c:forEach items="${categories}" var="category">
    <a href="${base}/show/category/${category.id}/products" class="list-group-item" id="a_${category.name}">${category.name}</a>
    </c:forEach>

</div>