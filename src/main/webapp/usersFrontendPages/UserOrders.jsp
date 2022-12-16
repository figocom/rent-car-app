<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.figo.domain.OrderCarPhoto" %>
<%@ page import="com.figo.enums.OrderStatus" %>
<%@ page import="com.figo.domain.PayCard" %>
<%@ page import="com.figo.dtos.paycards.PayCardDTO" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>

    <head>
        <title>Orders</title>
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
<% List<String> imagesUrl;%>
<div class="container-fluid ">
    <div class="row">
        <h1 class="text-center">Your Orders</h1>
        <% if (request.getAttribute("editOrderStatus") != null) {%>
        <h5 class="text-center text-success"><%=request.getAttribute("editOrderStatus")%>
        </h5>
        <br>
        <% }%>

        <%! List<PayCardDTO> cards=new ArrayList<>(); %>
        <% if (request.getAttribute("myOrders") != null) {%>
        <%! List<OrderCarPhoto> carOrder = new ArrayList<>();%>
        <%carOrder = (List<OrderCarPhoto>) request.getAttribute("myOrders");
           cards= (List<PayCardDTO>) request.getAttribute("cards");
        %>
        <br>
        <% }%>
        <% for (OrderCarPhoto carOrder : carOrder) {
            imagesUrl = carOrder.getPhotos().getUrls();%>
        <div class="card col-3 m-2">
            <form method="post" action="MyOrders">
                <div id="<%=carOrder.getOrderId()%>" class="carousel slide" data-bs-ride="carousel">
                    <%for (int i = 0; i < imagesUrl.size(); i++) { %>
                    <div class="carousel-indicators">
                        <button type="button"
                                <%if (i == 0) {%>
                                class="active"<%}%>
                                data-bs-target=<%="#"+carOrder.getOrderId()%> data-bs-slide-to=<%=i%>  >
                        </button>
                    </div>
                    <div class="carousel-inner">
                        <div class="carousel-item <% if (i == 0) {%> active<%}%>">
                            <img src="<%="carPhotos"+imagesUrl.get(i).substring(imagesUrl.get(i).lastIndexOf("/"))%> "
                                 alt="<%=carOrder.getCar().getCarModel()%>"
                                 class="d-block w-100" height="250">
                        </div>
                    </div>
                    <button class="carousel-control-prev" type="button"
                            data-bs-slide="prev" data-bs-target=<%="#" + carOrder.getOrderId() %>>
                        <span class="carousel-control-prev-icon"></span>
                    </button>
                    <button class="carousel-control-next" type="button"
                            data-bs-slide="next" data-bs-target=<%="#" + carOrder.getOrderId()%>>
                        <span class="carousel-control-next-icon"></span>
                    </button>
                    <%}%>
                </div>
                <div class="card-body">
                    <h4 class="card-title">Model: <%=carOrder.getCar().getCarModel()%>
                    </h4>
                    <input type="hidden" name="orderId" value="<%=carOrder.getOrderId()%>">
                    <h5 class="card-text">Number: <%=carOrder.getCar().getCarNumber()%>
                    </h5>
                    <h5 class="card-text">Region: <%=carOrder.getOrder().getRegion()%>
                    </h5>
                    <h5 class="card-text">Start time: <%
                        String[] ts = carOrder.getOrder().getStartTime().toString().split("T");%>
                        <%=ts[0]%>
                    </h5>
                    <h5 class="card-text">End time: <%
                        String[] tse = carOrder.getOrder().getEndTime().toString().split("T");%>
                        <%=tse[0]%>
                    </h5>
                    <h5 class="card-text">Ordered time: <%
                        String[] tso = carOrder.getCreatedTime().toString().split("T");%>
                        <%=tso[0]%>
                    </h5>
                    <h5 class="card-text">Driver License: <%=carOrder.getOrder().getDriverLicense()%>
                    </h5>
                    <h5 class="card-text">Price per day: <%=carOrder.getCar().getPricePerDay()%>
                    </h5>
                    <h5 class="card-text">Total price: <%=carOrder.getOrder().getTotalPrice()%>$
                    </h5>
                    <% if (carOrder.getOrder().getOrderStatus().equals(OrderStatus.REQUESTED)) {%>
                    <h5 class="card-text text-primary">Status: <%=carOrder.getOrder().getOrderStatus()%>
                    </h5><%}%>
                    <% if (carOrder.getOrder().getOrderStatus().equals(OrderStatus.ACCEPTED)) {%>
                    <h5 class="card-text text-warning">Status: <%=carOrder.getOrder().getOrderStatus()%>
                    </h5>
                    <input type="hidden" name="totalPrice" value="<%=carOrder.getOrder().getTotalPrice()%>">
                    <button class="  btn btn-primary  "  onclick="document.getElementById('id01').style.display='block'" type="button">Pay</button>

                    <%}%>
                    <% if (carOrder.getOrder().getOrderStatus().equals(OrderStatus.REJECTED)) {%>
                    <h5 class="card-text text-danger">Status: <%=carOrder.getOrder().getOrderStatus()%>

                    </h5><%}%>
                    <% if (carOrder.getOrder().getOrderStatus().equals(OrderStatus.Payed)) {%>
                    <h5 class="card-text text-info">Status: <%=carOrder.getOrder().getOrderStatus()%>
                        <input type="hidden" name="orderId" value="<%=carOrder.getOrderId()%>">
                        <input type="hidden" name="totalPrice" value="<%=carOrder.getOrder().getTotalPrice()%>">
                        <button class="  btn btn-primary  "  name="command" value="completed" type="submit" >Completed</button>
                    </h5><%}%>
                    <% if (carOrder.getOrder().getOrderStatus().equals(OrderStatus.COMPLETED)) {%>
                    <h5 class="card-text text-success"> Status: <%=carOrder.getOrder().getOrderStatus()%>
                    </h5><%}%>

                </div>
            </form>
        </div>
        <div id="id01" class="modal ">
            <div class="modal-dialog">
                <div class="modal-content bg-light">
                    <form method="post" action="MyOrders" class="was-validated">
                        <br>
                        <div class="form-floating offset-1 col-10">
                            <input type="hidden" name="orderId" value="<%=carOrder.getOrderId()%>">
                            <input type="hidden" name="totalPrice" value="<%=carOrder.getOrder().getTotalPrice()%>">
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
