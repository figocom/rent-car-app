<%@ page import="java.util.ArrayList" %>
<%@ page import="com.figo.domain.User" %>
<%@ page import="java.util.List" %>
<%@ page import="com.figo.dtos.users.UserDTO" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admins</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<nav class="navbar navbar-expand-sm bg-dark  justify-content-center text-white navbar-dark">
    <div class="container-fluid">
        <ul class="navbar-nav ">
            <li class="nav-item ">
                <a href="adminCabinet" class="nav-link">Home</a>
            </li>
            <li class="nav-item ">
                <a href="addCar" class="nav-link" >Add Car</a>
            </li>
            <li class="nav-item ">
                <a href="showOrders" class="nav-link" >Show orders</a>
            </li>
            <li class="nav-item">
                <a href="showAllCars" class="nav-link">Show all cars</a>
            </li>
            <li class="nav-item ">
                <a href="WWADMIN" class="nav-link" >Work with admin</a>
            </li>
            <li class="nav-item ">
                <a href="FileUploadServlet" class="nav-link" >Add Photo to car</a>
            </li>
            <li class="nav-item  ">
                <a href="logout" class="nav-link ">Logout</a></li>
        </ul>
    </div>
</nav>
<body>
<h1 class="text-center">Admins</h1>
<br>

<ul class="list-group col-10 offset-1">
    <% if (request.getAttribute("isDeleted")!=null){%>
    <h4 class="text-center"><%= request.getAttribute("isDeleted")%></h4>
    <br>
    <% }%>
    <%! List<UserDTO> admins = new ArrayList<>();%>
    <% admins= (List<UserDTO>) request.getAttribute("admins");
        System.out.println(admins);%>
    <table class="table table-dark table-striped">
        <thead>
        <tr>
            <th>Firstname</th>
            <th>Lastname</th>
            <th>Phone number</th>
            <th>Delete</th>
        </tr>
        </thead>
        <% for (UserDTO user : admins) {%>
        <tbody>
        <form action="WWADMIN" method="post">
        <tr>
            <td > <input type="hidden" name="phoneNumber" value=<%=user.getPhoneNumber()%>> <%=user.getFirstName()%> </td>
            <td><%=user.getLastName()%></td>
            <td><%=user.getPhoneNumber()%></td>
            <td><button class=" btn btn-outline-danger " type="submit" >Delete</button></td>
        </tr>
        </form>
        </tbody>
        <%}%>
    </table>
    <br>
    <form action="WWADMIN" method="post" class="was-validated">
        <div class="mb-3 text-start ">
            <label for="phoneNumberForAdd" class="form-label">Enter phone number:</label>
            <input type="number" class="form-control" id="phoneNumberForAdd" placeholder="Enter Phone number" name="phoneNumberForAdd" required>
            <div class="valid-feedback">Valid.</div>
            <div class="invalid-feedback">Please fill out this field.</div>
            <button type="submit" class="btn btn-primary offset-5">Add to admins list</button>
        </div>
    </form>
</ul>


</body>
</html>
