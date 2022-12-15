
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>User page</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<nav class="navbar navbar-expand-sm bg-dark  justify-content-center text-white navbar-dark">
    <div class="container-fluid">
        <ul class="navbar-nav ">
            <li class="nav-item "><a href="${pageContext.request.contextPath}/userCabinet" class="nav-link" >Home</a></li>
            <li class="nav-item "><a href="${pageContext.request.contextPath}/carsShow" class=" nav-link" >Show Car</a></li>
            <li class="nav-item "> <a href="${pageContext.request.contextPath}/MyOrders" class=" nav-link" >My orders</a></li>
            <li class="nav-item"><a href="${pageContext.request.contextPath}/addCard" class=" nav-link" >Add card</a></li>
            <li class="nav-item "><a href="${pageContext.request.contextPath}/Penalties" class="nav-link">Penalties</a></li>
            <li class="nav-item  "><a href="${pageContext.request.contextPath}/logout" class="nav-link " >Logout</a></li>
        </ul>
    </div>
</nav>
<div class="container-fluid">
    <div class="row">
        <div class="col-6 offset-3 text-center">
            <br>
            <h1 class="text-center">Welcome User</h1>
            <br>
            <% if (request.getAttribute("carRequest") != null) {%>
            <h4 class="text-center"><%= request.getAttribute("carRequest")%>
            </h4>
            <br>
            <% }%>

        </div>
    </div>
</div>
</body>
</html>
