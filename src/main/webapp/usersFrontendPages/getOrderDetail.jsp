
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.figo.daos.RegionDAO" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Order detail</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<nav class="navbar navbar-expand-sm bg-dark  justify-content-center text-white navbar-dark">
    <div class="container-fluid">
        <ul class="navbar-nav ">
            <li class="nav-item "><a href="carsShow" class=" nav-link" >Show Car</a></li>
            <li class="nav-item "> <a href="MyOrders" class=" nav-link" >My orders</a></li>
            <li class="nav-item"><a href="addCard" class=" nav-link" >Add card</a></li>
            <li class="nav-item "><a href="Penalties" class="nav-link">Penalties</a></li>
            <li class="nav-item  "><a href="logout" class="nav-link " >Logout</a></li>
        </ul>
    </div>
</nav>
<h1 class="text-center">Order detail</h1>
<br>
<% if (request.getAttribute("detailError")!=null){%>
<p class="text-center"><%= request.getAttribute("detailError")%></p>
<% }%>
<form action="order_detail" class="was-validated" method="post">
<div class="container-fluid">
    <div class="row">
        <div class="col-6 offset-3 text-center bg-light">
            <div class="mb-3 mt-3 text-start">
                    <label for="passport" class="form-label">Passport or id card :</label>
                    <input type="text" class="form-control" id="passport"
                           placeholder="Enter passport or idCard number Example(AA1234567)" name="passport" required>
                    <div class="valid-feedback">Valid.</div>
                    <div class="invalid-feedback">Please fill out this field.</div>
                </div>
                <div class="mb-3 mt-3 text-start">
                    <label for="driverLicense" class="form-label ">Driver license:</label>
                    <input type="text" class="form-control" id="driverLicense" placeholder="Enter driver license Example(AA1234567)"
                           name="driverLicense" required>
                    <div class="valid-feedback">Valid.</div>
                    <div class="invalid-feedback">Please fill out this field.</div>
                </div>


                <div class="mb-3 mt-3 text-start">
                    <label for="startDate" class="form-label ">Enter start date:</label>
                    <input type="text" class="form-control" id="startDate"
                           placeholder="Enter start date Example(yyyy-MM-dd )" name="startDate" required>
                    <div class="valid-feedback">Valid.</div>
                    <div class="invalid-feedback">Please fill out this field.</div>
                </div>
                <div class="mb-3 mt-3 text-start">
                    <label for="endDate" class="form-label ">Enter end date and time:</label>
                    <input type="text" class="form-control" id="endDate"
                           placeholder="Enter start date Example(yyyy-MM-dd)" name="endDate" required>
                    <div class="valid-feedback">Valid.</div>
                    <div class="invalid-feedback">Please fill out this field.</div>
                </div>
            <div class="form-floating">
                <select class="form-select" id="region" name="region">
                    <%! List<String> regions=new ArrayList<>(); %>
                    <% regions= RegionDAO.getRegions();%>
                    <% for (String region : regions) { %>
                    <option><%=region%></option>
                    <% ;}%>
                </select>
                <label for="region" class="form-label">Select region (select one):</label>
            </div>

                <div class="form-check mb-3 text-start">
                    <input class="form-check-input" type="checkbox" id="myCheck" name="remember" required>
                    <label class="form-check-label" for="myCheck">I agree .</label>
                    <div class="valid-feedback">Valid.</div>
                    <div class="invalid-feedback">Check this checkbox to continue.</div>
                </div>
                <button type="submit" class="btn btn-primary">Request for rent</button>
                <% if (request.getAttribute("choiceCar")!=null){%>
                  <input type="hidden" name="car_number" value="<%= request.getAttribute("choiceCar")%>">
                <br>
                <% }%></div>
    </div>
</div>
</form>
</body>
</html>
