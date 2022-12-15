<%@ page import="com.company.controller.DatabaseController" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
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
            <h1>Register car into system</h1>
            <% if (request.getAttribute("errorAddCar")!=null){%>
            <%= request.getAttribute("errorAddCar")%>
            <br>
            <% }%>
            <form action="${pageContext.request.contextPath}/addCar" class="was-validated" method="post">
                <div class="mb-3 mt-3 text-start">
                    <label for="carModel" class="form-label ">Car model:</label>
                    <input type="text" class="form-control" id="carModel" placeholder="Enter car model :" name="carModel" required>
                    <div class="valid-feedback">Valid.</div>
                    <div class="invalid-feedback">Please fill out this field.</div>
                </div>
                <div class="mb-3 mt-3 text-start">
                    <label for="carNumber" class="form-label ">Car number:</label>
                    <input type="text" class="form-control" id="carNumber" placeholder="Enter car number Example (85A111AA):" name="carNumber" required>
                    <div class="valid-feedback">Valid.</div>
                    <div class="invalid-feedback">Please fill out this field.</div>
                </div>
                <div class="mb-3 mt-3 text-start">
                    <label for="countOfPlace" class="form-label ">Count of place:</label>
                    <input type="number" class="form-control" id="countOfPlace" placeholder="Place enter count of place in the car:" name="countOfPlace" required>
                    <div class="valid-feedback">Valid.</div>
                    <div class="invalid-feedback">Please fill out this field.</div>
                </div>
                <div class="mb-3 mt-3 text-start">
                    <label for="carColor" class="form-label">Color :</label>
                    <input type="text" class="form-control" id="carColor" placeholder="Enter car color:" name="carColor" required>
                    <div class="valid-feedback">Valid.</div>
                    <div class="invalid-feedback">Please fill out this field.</div>
                </div>
                <div class="form-floating">
                    <select class="form-select" id="carRegion" name="carRegion">
                        <%! List<String> regions=new ArrayList<>(); %>
                        <% regions= DatabaseController.getRegions();%>
                        <% for (String region : regions) { %>
                        <option><%=region%></option>
                        <% ;}%>
                    </select>
                    <label for="carRegion" class="form-label">Select car region (select one):</label>
                </div>
                <div class="mb-3 mt-3 text-start">
                    <label for="carYear" class="form-label">Year:</label>
                    <input type="number" class="form-control" id="carYear" placeholder="Enter car year:" name="carYear" required>
                    <div class="valid-feedback">Valid.</div>
                    <div class="invalid-feedback">Please fill out this field.</div>
                </div>
                <div class="mb-3 mt-3 text-start">
                    <label for="pricePerDay" class="form-label">Price per day :</label>
                    <input type="text" class="form-control" id="pricePerDay" placeholder="Enter price per day Example(35.43$):" name="pricePerDay" required>
                    <div class="valid-feedback">Valid.</div>
                    <div class="invalid-feedback">Please fill out this field.</div>
                </div>
                <div class="mb-3 mt-3 text-start">
                    <label for="additionInfo" class="form-label">Additional info :</label>
                    <textarea class="form-control" rows="3" id="additionInfo" placeholder="Enter addition info(optional):" name="additionInfo"></textarea>
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
