<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.figo.domain.OrderCarPhoto" %>
<%@ page import="com.figo.enums.OrderStatus" %>
<%@ page import="com.figo.daos.OrderCarPhotoDAO" %>

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
            <li class="nav-item ">
                <a href="adminCabinet" class="nav-link">Home</a>
            </li>
            <li class="nav-item ">
                <a href="addCar" class="nav-link">Add Car</a>
            </li>
            <li class="nav-item ">
                <a href="showOrders" class="nav-link">Show orders</a>
            </li>
            <li class="nav-item">
                <a href="showAllCars" class="nav-link">Show all cars</a>
            </li>
            <li class="nav-item ">
                <a href="WWADMIN" class="nav-link">Work with admin</a>
            </li>
            <li class="nav-item ">
                <a href="FileUploadServlet" class="nav-link">Add Photo to car</a>
            </li>
            <li class="nav-item  ">
                <a href="logout" class="nav-link ">Logout</a></li>
        </ul>
    </div>
</nav>
<% List<String> imagesUrl;%>
<div class="container-fluid ">
    <div class="row">
        <h1 class="text-center">All Orders</h1>
        <% if (request.getAttribute("editOrderStatus") != null) {%>
        <h5 class="text-center text-success"><%=request.getAttribute("editOrderStatus")%>
        </h5>
        <br>
        <% }%>
        <% if (request.getAttribute("allOrders") != null) {%>
        <%! List<OrderCarPhoto> carOrders = new ArrayList<>(); %>
        <%carOrders = (List<OrderCarPhoto>) request.getAttribute("allOrders");;%>
        <br>
        <% }%>


        <% for (OrderCarPhoto carOrder : carOrders) {
            imagesUrl = carOrder.getPhotos().getUrls();%>
        <div class="card col-3 m-2">
            <form method="post" action="showOrders" class="was-validated">

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
                                 class="  d-block w-100 " height="250">
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
                    <input type="hidden" name="orderId" value="<%=carOrder.getOrderId()%>">
                    <h4 class="card-title">Model: <%=carOrder.getCar().getCarModel()%>
                    </h4>

                    <input type="hidden" name="orderId" value="<%=carOrder.getOrderId()%>">
                    <h5 class="card-text">Order id: <%=carOrder.getOrderId()%>
                    </h5>
                    <h5 class="card-text">Car number: <%=carOrder.getCar().getCarNumber()%>
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
                        <%=tso[0] + " " + tso[1]%>
                    </h5>
                    <h5 class="card-text">Driver License: <%=carOrder.getOrder().getDriverLicense()%>
                    </h5>
                    <h5 class="card-text">Passport: <%=carOrder.getOrder().getPassport()%>
                    </h5>
                    <h5 class="card-text">Price per day: <%=carOrder.getCar().getPricePerDay()%>
                    </h5>
                    <h5 class="card-text">Total price: <%=carOrder.getOrder().getTotalPrice()%>
                    </h5>
                    <h5 class="card-text">User phone number: <%=carOrder.getUserPhoneNumber()%>
                    </h5>
                    <h5 class="card-text text-info">Status: <%=carOrder.getOrder().getOrderStatus()%>
                    </h5>
                    <% if (carOrder.getOrder().getOrderStatus().equals(OrderStatus.REJECTED)) {%>
                    <% String rejectCause = OrderCarPhotoDAO.getRejectCause(carOrder.getOrderId());%>
                    <h5 class="card-text text-info"><%=rejectCause%>
                    </h5><%}%>
                    <% if (carOrder.getOrder().getOrderStatus().equals(OrderStatus.REQUESTED)) {%>
                    <button class="  btn btn-primary " type="submit" name="command" value="accept">Accept</button>
                    <button type="button" onclick="document.getElementById('id02').style.display='block'"
                            class="btn btn-primary">Reject
                    </button>
                    <%}%>
                    <% if (carOrder.getOrder().getOrderStatus().equals(OrderStatus.COMPLETED)) {%>
                    <button type="button" onclick="document.getElementById('id01').style.display='block'"
                            class="btn btn-primary">Give fine
                    </button>
                    <button type="submit" class="btn btn-primary" name="command" value="finishOrder">Finish Order
                    </button>
                    <%}%>

                </div>
            </form>
        </div>

        <div id="id01" class="modal ">
            <div class="modal-dialog">
                <div class="modal-content bg-light">
                    <form method="post" action="showOrders" class="was-validated">
                        <div class="mb-3 mt-3 text-start">
                            <label for="orderId" class="form-label ">Enter order id</label>
                            <input type="text" class="form-control" id="orderId" placeholder="Enter order id" name="orderId" required>
                            <div class="valid-feedback">Valid.</div>
                            <div class="invalid-feedback">Please fill out this field.</div>
                        </div>
                        <div class="mb-3 mt-3 text-start offset-1 col-10 ">
                            <label for="infoPenalty " class="form-label">Enter some cause for penalty
                                :</label>
                            <textarea class="form-control" rows="3" id="infoPenalty "
                                      placeholder="Enter some cause for penalty :"
                                      name="infoPenalty" required></textarea>
                            <div class="invalid-feedback">Please fill out this field.</div>

                        </div>
                        <div class="mb-3 mt-3 text-start offset-1 col-10">
                            <label for="pricePenalty" class="form-label">Enter penalty price :</label>
                            <input type="text" class="form-control" id="pricePenalty"
                                   placeholder="Enter penalty price Example(35.43$):" name="pricePenalty" required>
                            <div class="valid-feedback">Valid.</div>
                            <div class="invalid-feedback">Please fill out this field.</div>
                        </div>
                        <div class="form-check mb-3 text-start offset-1">
                            <input class="form-check-input" type="checkbox" id="myCheck" name="remember"
                                   required>
                            <label class="form-check-label" for="myCheck">I confirm .</label>
                            <div class="valid-feedback">Valid.</div>
                            <div class="invalid-feedback">Check this checkbox to continue.</div>
                        </div>
                        <button type="submit" class="btn btn-danger col-3 offset-3 " name="command" value="giveFine">
                            Submit
                        </button>
                        <button type="button" class=" btn btn-primary col-3  close" title="Close Modal"
                                onclick="document.getElementById('id01').style.display='none' ">Cancel
                        </button>
                    </form>
                </div>
            </div>
        </div>
        <div id="id02" class="modal ">
            <div class="modal-dialog">
                <div class="modal-content bg-light">
                    <form method="post" action="showOrders" class="was-validated">
                        <div class="mb-3 mt-3 text-start">
                            <label for="orderId1" class="form-label ">Enter order id</label>
                            <input type="text" class="form-control" id="orderId1" placeholder="Enter order id" name="orderId" required>
                            <div class="valid-feedback">Valid.</div>
                            <div class="invalid-feedback">Please fill out this field.</div>
                        </div>
                        <div class="mb-3 mt-3 text-start  offset-1 col-10">
                            <label for="rejectCause " class="form-label">Enter some cause for reject :</label>
                            <textarea class="form-control" rows="3" id="rejectCause "
                                      placeholder="Enter some cause for reject :"
                                      name="rejectCause" required></textarea>
                            <div class="valid-feedback">Valid.</div>
                            <div class="invalid-feedback">Please fill out this field.</div>

                        </div>
                        <div class="form-check mb-3 text-start offset-1">
                            <input class="form-check-input" type="checkbox" id="myCheck2" name="remember" required>
                            <label class="form-check-label" for="myCheck2">I confirm .</label>
                            <div class="valid-feedback">Valid.</div>
                            <div class="invalid-feedback">Check this checkbox to continue.</div>
                        </div>
                        <button type="submit" class="btn btn-danger col-3 offset-3 " name="command" value="reject">
                            Submit
                        </button>
                        <button type="button" class=" btn btn-primary col-3  close" title="Close Modal"
                                onclick="document.getElementById('id02').style.display='none'">Cancel
                        </button>
                    </form>
                </div>
            </div>
        </div>

        <%}%>

    </div>
</div>

</body>
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
</html>
