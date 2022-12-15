<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add car</title>
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
        <div class="col-6 offset-3 text-center bg-light">
            <h1>Add car photo system</h1>
            <form method="post" action="FileUploadServlet" enctype="multipart/form-data"  class="was-validated">
                <% if (request.getAttribute("errorAddCar")!=null){%>
                <%= request.getAttribute("errorAddCar")%>
                <br>
                <% }%>
                <div class="mb-3 mt-3 text-start">
                    <label for="carNumber" class="form-label ">Car number:</label>
                    <input type="text" class="form-control" id="carNumber" placeholder="Enter car number Example (85A111AA):" name="carNumber" required>
                    <div class="valid-feedback">Valid.</div>
                    <div class="invalid-feedback">Please fill out this field.</div>
                </div>
                <div class="mb-3 text-start">
                    <label for="carPhoto1" class="form-label">Add 1 photo:</label>
                    <input type="file" name="photo1" id="carPhoto1" multiple accept="image/*,image/jpeg"
                           class="form-control" required>
                    <div class="valid-feedback">Valid.</div>
                    <div class="invalid-feedback">Please fill out this field.</div>
                </div>
                <div class="mb-3 text-start">
                    <label for="carPhoto2" class="form-label">Add 2 photo(optional):</label>
                    <input type="file" name="photo2" id="carPhoto2" multiple accept="image/*,image/jpeg"
                           class="form-control">

                </div>
                <div class="mb-3 text-start">
                    <label for="carPhoto3" class="form-label">Add 3 photo(optional):</label>

                    <input type="file" name="photo3" id="carPhoto3" multiple accept="image/*,image/jpeg"
                           class="form-control">
                </div>
                <div class="mb-3 text-start">
                    <label for="carPhoto4" class="form-label">Add 4 photo(optional):</label>

                    <input type="file" name="photo4" id="carPhoto4" multiple accept="image/*,image/jpeg"
                           class="form-control">
                </div>
                <div class="form-check mb-3 text-start">
                    <input class="form-check-input" type="checkbox" id="myCheck" name="remember" required>
                    <label class="form-check-label" for="myCheck">I confirm .</label>
                    <div class="valid-feedback">Valid.</div>
                    <div class="invalid-feedback">Check this checkbox to continue.</div>
                </div>
                <button type="submit" class="btn btn-primary ">Submit</button>
                <br>
            </form>
            <br>
        </div>
    </div>
</div>
</body>
</html>
