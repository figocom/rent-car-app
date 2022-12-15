<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.figo.daos.RegionDAO" %>




<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title >Login</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <div class="col-6 offset-3 text-center bg-light">
            <h1>Register our system</h1>
            <% if (request.getAttribute("errorRegister")!=null){%>
            <%= request.getAttribute("errorRegister")%>
            <br>
            <% }%>
            <form action="register" class="was-validated" method="post">
                <div class="mb-3 mt-3 text-start">
                    <label for="firstName" class="form-label ">First name:</label>
                    <input type="text" class="form-control" id="firstName" placeholder="Enter first name" name="firstName" required>
                    <div class="valid-feedback">Valid.</div>
                    <div class="invalid-feedback">Please fill out this field.</div>
                </div>
                <div class="mb-3 mt-3 text-start">
                    <label for="lastName" class="form-label ">Last name:</label>
                    <input type="text" class="form-control" id="lastName" placeholder="Enter last name" name="lastName" required>
                    <div class="valid-feedback">Valid.</div>
                    <div class="invalid-feedback">Please fill out this field.</div>
                </div>
                <div class="mb-3 mt-3 text-start">
                    <label for="phoneNumber" class="form-label ">Phone number (should contain 9 numbers):</label>
                    <input type="text" class="form-control" id="phoneNumber" placeholder="901234567" name="phoneNumber" required>
                    <div class="valid-feedback">Valid.</div>
                    <div class="invalid-feedback">Please fill out this field.</div>
                </div>
                <div class="mb-3 mt-3 text-start">
                    <label for="username" class="form-label">Email or username :</label>
                    <input type="text" class="form-control" id="username" placeholder="Enter username or username" name="username" required>
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
                <div class="mb-3 mt-3 text-start">
                    <label for="address" class="form-label">Address:</label>
                    <input type="text" class="form-control" id="address" placeholder="Enter your address" name="address" required>
                    <div class="valid-feedback">Valid.</div>
                    <div class="invalid-feedback">Please fill out this field.</div>
                </div>
                <div class="mb-3 mt-3 text-start">
                    <label for="password" class="form-label">Password:</label>
                    <input type="password" class="form-control" id="password" placeholder="Enter password" name="password" required>
                    <div class="valid-feedback">Valid.</div>
                    <div class="invalid-feedback">Please fill out this field.</div>

                </div>
                <div class="form-check mb-3 text-start">
                    <input class="form-check-input" type="checkbox" id="myCheck" name="remember" required>
                    <label class="form-check-label" for="myCheck">I agree .</label>
                    <div class="valid-feedback">Valid.</div>
                    <div class="invalid-feedback">Check this checkbox to continue.</div>
                </div>
                <button type="submit" class="btn btn-primary">Register</button>
            </form>
            <br>
        </div>
    </div>
</div>


</body>
</html>