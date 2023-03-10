
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="com.figo.dtos.paycards.PayCardDTO" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Add card</title>
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
<% if (request.getAttribute("cardStatus")!=null){%>
<h4 class="text-center"><%= request.getAttribute("cardStatus")%></h4>
<%}%>
<%! List<PayCardDTO> cards = new ArrayList<>();%>
<% if (request.getAttribute("cards")!=null){%>
     <%  cards= (List<PayCardDTO>) request.getAttribute("cards"); %>
<%}%>
<div class="container-fluid">
    <div class="row">
<% if (cards.size()>0){%>
        <table class="table table-dark table-striped">
            <thead>
            <tr>
                <th>Card Number</th>
                <th>Balance</th>
                <th>Delete</th>
            </tr>
            </thead>
            <% for (PayCardDTO card : cards) {%>
            <tbody>
            <form action="addCard" method="post">
                <tr>
                    <td > <input type="hidden" name="cardNumberForDelete" value=<%=card.getCardNumber()%>> <%=card.getCardNumber()%> </td>
                    <td><%=card.getBalance()%></td>
                    <td><button class=" btn btn-outline-danger " type="submit" >Delete</button></td>
                </tr>
            </form>
            </tbody>
            <%}%>
        </table>
        <%}%>
        <div class="col-6 offset-3 text-center bg-light">
            <form action="addCard" class="was-validated" method="post">
                <h1>Add card our system</h1>

                <br>
                <div class="mb-3 mt-3 text-start">
                    <label for="cardNumber" class="form-label">Card number:</label>
                    <input type="number" class="form-control" id="cardNumber" placeholder="Enter card number" name="cardNumber" required>
                    <div class="valid-feedback">Valid.</div>
                    <div class="invalid-feedback">Please fill out this field.</div>
                </div>
                <div class="mb-3 text-start">
                    <label for="cardBalance" class="form-label">Balance:</label>
                    <input type="text" class="form-control" id="cardBalance" placeholder="Enter card balance Example(35.43$)" name="cardBalance" required>
                    <div class="valid-feedback">Valid.</div>
                    <div class="invalid-feedback">Please fill out this field.</div>
                </div>
                <div class="form-check mb-3 text-start">
                    <input class="form-check-input" type="checkbox" id="myCheck" name="remember" required>
                    <label class="form-check-label" for="myCheck">I agree .</label>
                    <div class="valid-feedback">Valid.</div>
                    <div class="invalid-feedback">Check this checkbox to continue.</div>
                </div>
                <button type="submit" class="btn btn-primary">Add</button>
            </form>
            <br>
        </div>
    </div>
</div>
</body>
</html>
