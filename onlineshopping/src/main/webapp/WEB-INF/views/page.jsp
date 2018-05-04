
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<spring:url var = "css" value="/resources/css"/>
<spring:url var = "js" value="/resources/js"/>
<spring:url var = "images" value="/resources/images"/>


<c:set var="req" value="${pageContext.request}" />
<c:set var="url">${req.requestURL}</c:set>
<c:set var="base" value="${fn:substring(url, 0, fn:length(url) - fn:length(req.requestURI))}${req.contextPath}" />


<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <meta name="_csrf" content="${_csrf.token}">
    <meta name="_csrf_header" content="${_csrf.headerName}">

    <title>Shop Homepage - ${title}</title>

    <script>
        window.menu = '${title}';
        window.contextRoot = '${base}';
    </script>

    <!-- Bootstrap core CSS -->
    <link href="${css}/bootstrap.min.css" rel="stylesheet">

    <!-- Bootstrap Readable theme CSS -->
    <link href="${css}/bootstrap-readable-theme.css" rel="stylesheet">

    <!-- DataTable Bootstrap CSS -->
    <link rel="stylesheet" type="text/css" href="${css}/dataTables.bootstrap4.css"/>

    <!-- Custom styles for this template -->
    <link href="${css}/myapp.css" rel="stylesheet">

</head>

<body>
     <div class="wrapper">

     <!-- Navigation -->
     <%@include file="shared/navbar.jsp"%>

    <div class="content">
        <!-- Page Content -->
        <c:if test="${userClickHome == true}">
            <%@include file="home.jsp"%>
        </c:if>

        <c:if test="${userClickAbout == true}">
            <%@include file="about.jsp"%>
        </c:if>

        <c:if test="${userClickAllProducts == true or userClickCategoryProducts == true}">
            <jsp:include page="listProducts.jsp"/>
        </c:if>

        <c:if test="${userClickShowProduct == true}">
            <jsp:include page="singleProduct.jsp"/>
        </c:if>

        <c:if test="${userClickContact == true}">
            <jsp:include page="contact.jsp"/>
        </c:if>

        <c:if test="${userClickManageProduct == true}">
            <jsp:include page="manageProducts.jsp"/>
        </c:if>

        <c:if test="${userClickShowCart == true}">
            <jsp:include page="cart.jsp"/>
        </c:if>

    </div>
    <!-- Footer -->
    <jsp:include page="shared/footer.jsp"/>

    <script src="//code.jquery.com/jquery-2.2.4.min.js"></script>

    <!-- Bootstrap core JavaScript -->
    <script src="${js}/bootstrap.min.js"></script>

    <!--DataTable plugin-->
    <script type="text/javascript" src="${js}/jquery.dataTables.js"></script>
    <!--Bootstrap DataTable plugin-->

    <script src="${js}/dataTables.bootstrap4.js"></script>

    <script src="${js}/bootbox.min.js"></script>

    <script src="${js}/jquery.validate.min.js"></script>

    <script src="${js}/myapp.js"></script>

     </div>

</body>

</html>
