<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<div class="container">

    <c:set var="req" value="${pageContext.request}" />
    <c:set var="url">${req.requestURL}</c:set>
    <c:set var="base" value="${fn:substring(url, 0, fn:length(url) - fn:length(req.requestURI))}${req.contextPath}" />


    <!-- Breadcrumb -->
    <div class="row">

        <div class="col-xs-12">


            <ol class="breadcrumb">

                <li class="breadcrumb-item"><a href="${base}/home">Home</a></li>
                <li class="breadcrumb-item"><a href="${base}/show/all/products">Products</a></li>
                <li class="breadcrumb-item active">${product.name}</li>

            </ol>


        </div>


    </div>


    <div class="row">

        <!-- Display the product image -->
        <div class="col-xs-12 col-sm-4">

            <div class="thumbnail">

                <img src="${base}/resources/images/${product.code}.jpg" class="img img-fluid"/>

            </div>

        </div>


        <!-- Display the product description -->
        <div class="col-xs-12 col-sm-8">

            <h3>${product.name}</h3>
            <hr/>

            <p>${product.description}</p>
            <hr/>

            <h4>Price: <strong> &#8377; ${product.unitPrice} /-</strong></h4>
            <hr/>

            <c:choose>
                <c:when test="${product.quantity < 1}">

                    <h6>Qty. Available: <span style="color: red">Out of Stock</span> </h6>

                </c:when>

                <c:otherwise>
                    <h6>Qty. Available: ${product.quantity}</h6>
                </c:otherwise>
            </c:choose>

            <security:authorize access="hasAuthority('USER')">
            <c:choose>
                <c:when test="${product.quantity < 1}">

                    <a href="javascript:void(0)" class="btn btn-success disabled"><strike>
                        <span class="glyphicon glyphicon-shopping-cart"></span> Add to Cart</strike></a>

                </c:when>

                <c:otherwise>

                    <a href="${base}/cart/add/${product.id}/product" class="btn btn-success">
                        <span class="glyphicon glyphicon-shopping-cart"></span> Add to Cart</a>

                </c:otherwise>

            </c:choose>
            </security:authorize>
            <security:authorize access="hasAuthority('ADMIN')">

                <a href="${base}/manage/${product.id}/product" class="btn btn-warning">
                    <span class="glyphicon glyphicon-shopping-cart"></span>Edit</a>

            </security:authorize>

                <a href="${base}/show/all/products" class="btn btn-success">
                    <span class="glyphicon glyphicon-pencil"></span> Back</a>
        </div>


    </div>

</div>