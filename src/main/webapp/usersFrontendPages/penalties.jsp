<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.figo.domain.Penalty" %>
<%@ page import="com.figo.dtos.paycards.PayCardDTO" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Penalties</title>
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
<div class="container-fluid ">
    <div class="row">
        <h1 class="text-center">Your Penalties</h1>
        <% if (request.getAttribute("userPenaltiesError") != null) {%>
        <h5 class="text-center text-success"><%=request.getAttribute("userPenaltiesError")%>
        </h5>
        <br>
        <% }%>
        <%! List<PayCardDTO> cards=new ArrayList<>(); %>
        <% if (request.getAttribute("userPenalties") != null) {%>
        <%! List<Penalty> userPenalties = new ArrayList<>();%>
        <%userPenalties = (List<Penalty>) request.getAttribute("userPenalties");%>
        <%  cards= (List<PayCardDTO>) request.getAttribute("cards"); %>
        <br>
        <% }%>
        <% for (Penalty userPenalty : userPenalties) {%>

        <div class="card col-3 m-2">
            <form method="post" action="Penalties">
                <div class="card-body">
                    <h4 class="card-title">Model: <%=userPenalty.getCarModel()%></h4>
                    <h5 class="card-text">Number: <%=userPenalty.getCarNumber()%>
                    </h5>
                    <h5 class="card-text">Info: <p><%=userPenalty.getInfo()%></p>
                    </h5>
                    <h5 class="card-text">Total price: <%=userPenalty.getAmount()%>
                    </h5>

                    <button class="  btn btn-primary  "  onclick="document.getElementById('id01').style.display='block'" type="button">Pay</button>

                </div>
            </form>
        </div>
        <div id="id01" class="modal ">
            <div class="modal-dialog">
                <div class="modal-content bg-light">
                    <form method="post" action="Penalties" class="was-validated">
                        <br>
                        <div class="form-floating offset-1 col-10">
                            <input type="hidden" name="orderId" value="<%=userPenalty.getOrderId()%>">
                            <input type="hidden" name="penaltyAmount" value="<%=userPenalty.getAmount()%>">
                            <select class="form-select" id="card" name="card">

                                <% for (PayCardDTO card : cards) {%>
                                <option><%=card.getCardNumber()%></option>
                                <td > <input type="hidden" name="cardIdForPay" value=<%=card.getCardNumber()%>> <%=card.getBalance()%> </td>
                                <% ;}%>
                            </select>
                            <label for="card" class="form-label">Select payment card (select one):</label>
                        </div>
                        <div class="form-check mb-3 text-start offset-1">
                            <input class="form-check-input" type="checkbox" id="myCheck" name="remember"
                                   required>
                            <label class="form-check-label" for="myCheck">I confirm .</label>
                            <div class="valid-feedback">Valid.</div>
                            <div class="invalid-feedback">Check this checkbox to continue.</div>
                        </div>
                        <button type="submit" class="btn btn-danger col-3 offset-1 "  name="command" value="payed">Submit</button>
                        <button type="submit" class="btn btn-warning col-3  "  name="command" value="addCard">Add Card</button>
                        <button type="button" class=" btn btn-primary col-3  close" title="Close Modal"
                                onclick="document.getElementById('id01').style.display='none'">Cancel
                        </button>
                    </form>
                </div>
            </div>
        </div>
        <%}%>


    </div>
</div>
<script>
    // Get the modal
    const modal = document.getElementById('id01');

    // When the user clicks anywhere outside of the modal, close it
    window.onclick = function (event) {
        if (event.target === modal) {
            modal.style.display = "none";
        }
    }
</script>
</body>
</html>
