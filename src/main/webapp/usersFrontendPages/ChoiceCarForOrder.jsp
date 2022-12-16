<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>

<%@ page import="com.figo.dtos.cars.CarDTO" %>
<%@ page import="com.figo.dtos.photos.PhotoDTO" %>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Cars</title>
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
<h1 class="text-center">Cars</h1>
<br>

<%! List<PhotoDTO> images = new ArrayList<>();%>
<%! List<String> imagesUrl = new ArrayList<>();%>
<%! List<CarDTO> cars = new ArrayList<>(); %>
<%cars = (List<CarDTO>) request.getAttribute("cars");%>
<% images= (List<PhotoDTO>) request.getAttribute("photos");

%>

    <div class="container-fluid">
        <div class="row">
            <% for (CarDTO car :  cars) {
                imagesUrl.addAll(images.stream().filter(photoDTO -> photoDTO.getCarNumber().equals(car.getCarNumber())).findFirst().orElse(new PhotoDTO()).getUrls());
            %>
            <div class="card col-3 m-2">
                <form method="post" action="carsShow">
                    <div id="#demo" class="carousel slide" data-bs-ride="carousel">
                        <%for (int i = 0; i < imagesUrl.size(); i++) { %>
                        <div class="carousel-indicators">
                            <button type="button"
                                    <%if (i == 0) {%>
                                    class="active"<%}%>
                                    data-bs-target="#demo" data-bs-slide-to=<%=i%>  >
                            </button>
                        </div>
                        <div class="carousel-inner">
                            <div class="carousel-item <% if (i == 0) {%> active<%}%>">
                                <img src="<%="carImages"+imagesUrl.get(i).substring(imagesUrl.get(i).lastIndexOf("/"))%> "
                                     alt="<%=car.getCarModel()%>"
                                     class="d-block w-100   " height="250">
                            </div>
                        </div>
                        <button class="carousel-control-prev" type="button"
                                 data-bs-target="#demo" data-bs-slide="prev">
                            <span class="carousel-control-prev-icon"></span>
                        </button>
                        <button class="carousel-control-next" type="button"
                                 data-bs-target="#demo" data-bs-slide="next">
                            <span class="carousel-control-next-icon"></span>
                        </button>
                        <%}%>
                    </div>
                    <div class="card-body">
                        <h4 class="card-title">Model: <%=car.getCarModel()%>
                        </h4>
                        <h5 class="card-text"><input type="hidden" name="choiceCarNumber"
                                                     value="<%=car.getCarNumber()%>">Number: <%=car.getCarNumber()%>
                        </h5>
                        <h5 class="card-text">Region: <%=car.getCarRegion()%>
                        </h5>
                        <h5 class="card-text">Color: <%=car.getCarColor()%>
                        </h5>
                        <h5 class="card-text">Year: <%=car.getCarYear()%>
                        </h5>
                        <h5 class="card-text">Count of place: <%=car.getCountOfPlace()%>
                        </h5>
                        <h5 class="card-text">Price per day: <%=car.getPricePerDay()%>
                        </h5>
                        <%if (car.getAdditionInfo() != null) {%>
                        <h5 class="card-text">Info: <%=car.getAdditionInfo()%></h5>
                        <%}%>
                        <button class="  btn btn-primary  " type="submit">Rent a car</button>
                    </div>
                </form>
            </div>

            <%}%>

        </div>

    </div>

</body>
</html>
