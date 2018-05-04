<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="container">

    <div class="row">

        <div class="col-md-3">
            <%@include file="shared/sidebar.jsp"%>
        </div>

        <div class="col-md-9">
            <!--Added breadcrumb component -->
            <div class="row">

                <div class="col-md-12">
                    <c:set var="req" value="${pageContext.request}" />
                    <c:set var="url">${req.requestURL}</c:set>
                    <c:set var="base" value="${fn:substring(url, 0, fn:length(url) - fn:length(req.requestURI))}${req.contextPath}" />

                    <c:if test="${userClickAllProducts == true}">
                        <script>
                            window.categoryId = '';
                        </script>
                        <ol class="breadcrumb">
                            <li class="breadcrumb-item"><a href="${base}/home">Home</a> </li>
                            <li class="breadcrumb-item active">All Products</li>
                        </ol>
                    </c:if>

                    <c:if test="${userClickCategoryProducts == true}">
                        <script>
                            window.categoryId = '${category.id}';
                        </script>
                        <ol class="breadcrumb">
                            <li class="breadcrumb-item"><a href="${base}/home">Home</a> </li>
                            <li class=" breadcrumb-item active">Category</li>
                            <li class="breadcrumb-item active">${category.name}</li>
                        </ol>
                    </c:if>
                </div>
            </div>
            <div class="row">

                <div class="col-md-12">

                    <div class="container-fluid">

                        <div class="table-responsive">

                            <table id="productListTable" class="table table-striped table-hover">

                                <thead>

                                <tr>
                                    <th></th>
                                    <th>Name</th>
                                    <th>Brand</th>
                                    <th>Unit Price</th>
                                    <th>Quantity</th>
                                    <th></th>
                                </tr>

                                </thead>

                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>