
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Home</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>

<nav class="navbar navbar-expand-sm bg-dark  justify-content-center text-white navbar-dark">
    <div class="container-fluid">
        <ul class="navbar-nav ">
            <li class="nav-item ">
                <a href="${pageContext.request.contextPath}/adminCabinet" class="nav-link">Home</a>
            </li>
            <li class="nav-item ">
                <a href="${pageContext.request.contextPath}/addCar" class="nav-link" >Add Car</a>
            </li>
            <li class="nav-item ">
                <a href="${pageContext.request.contextPath}/showOrders" class="nav-link" >Show orders</a>
            </li>
            <li class="nav-item">
                <a href="${pageContext.request.contextPath}/showAllCars" class="nav-link">Show all cars</a>
            </li>
            <li class="nav-item ">
                <a href="${pageContext.request.contextPath}/WWADMIN" class="nav-link" >Work with admin</a>
            </li>
            <li class="nav-item ">
                <a href="${pageContext.request.contextPath}/FileUploadServlet" class="nav-link" >Add Photo to car</a>
            </li>
            <li class="nav-item  ">
                <a href="${pageContext.request.contextPath}/logout" class="nav-link ">Logout</a></li>
        </ul>
    </div>
</nav>
<div class="container-fluid">
    <div class="row">
        <div class="col-6 offset-3">
            <br>
            <h1 class="text-center">Welcome Admin</h1>
            <br>
            <% if (request.getAttribute("successAddCarPhoto") != null) {%>
            <h4 class="text-center"><%= request.getAttribute("successAddCarPhoto")%></h4>
            <br>
            <% }%>

        </div>
    </div>

</div>
</body>
</html>
