<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.figo.dtos.photos.PhotoDTO" %>
<%@ page import="com.figo.dtos.cars.CarDTO" %>
<%@ page import="com.figo.enums.CarStatus" %>
<%@ page import="java.util.Objects" %>
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
<h1 class="text-center">Cars</h1>
<br>

<%! List<PhotoDTO> images = new ArrayList<>();%>
<%! List<String> imagesUrl = new ArrayList<>();%>
<%! List<CarDTO> cars = new ArrayList<>(); %>
<%cars = (List<CarDTO>) request.getAttribute("cars");%>
<% images= (List<PhotoDTO>) request.getAttribute("photos");
 if (cars.size()>0){
%>

<form method="post" action="showAllCars">
    <div class="container-fluid">
        <div class="row">
            <% for (CarDTO car : cars) {
                if (Objects.nonNull(images)) {
                    imagesUrl.addAll(images.stream().filter(photoDTO -> photoDTO.getCarNumber().equals(car.getCarNumber())).findFirst().orElse(new PhotoDTO()).getUrls());
                } %>
            <div class="card col-3">
                <form method="post" action="showAllCars">
                    <div id="<%=car.getCarNumber()%>" class="carousel slide" data-bs-ride="carousel">
                        <%for (int i = 0; i < imagesUrl.size(); i++) { %>
                        <div class="carousel-indicators">
                            <button type="button"
                                    <%if (i == 0) {%>
                                    class="active"<%}%>
                                    data-bs-target=<%="#"+car.getCarNumber()%> data-bs-slide-to=<%=i%>  >
                            </button>
                        </div>
                        <div class="carousel-inner">
                            <div class="carousel-item <% if (i == 0) {%> active<%}%>">
                                <img src="<%="carPhotos"+imagesUrl.get(i).substring(imagesUrl.get(i).lastIndexOf("/"))%> "
                                     alt="<%=car.getCarModel()%>"
                                     class="d-block w-100" height="250">
                            </div>
                        </div>
                        <button class="carousel-control-prev" type="button"
                                data-bs-slide="prev" data-bs-target=<%="#" + car.getCarNumber()%>>
                            <span class="carousel-control-prev-icon"></span>
                        </button>
                        <button class="carousel-control-next" type="button"
                                data-bs-slide="next" data-bs-target=<%="#" + car.getCarNumber()%>>
                            <span class="carousel-control-next-icon"></span>
                        </button>
                        <%}%>
                    </div>
                    <div class="card-body">
                        <h4 class="card-title">Model: <%=car.getCarModel()%>
                        </h4>
                        <h5 class="card-text"><input type="hidden" name="deleteCarNumber"
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
                        <h5 class="card-text">Car status <%=car.getCarStatus()%></h5>
                        <%if (car.getAdditionInfo() != null) {%>
                        <h5 class="card-text">Info: <%=car.getAdditionInfo()%>
                        </h5>
                        <%}%>
                        <button class="  btn btn-danger  " name="command" value="delete" type="submit">Delete</button>
                        <%if (car.getCarStatus().equals(String.valueOf(CarStatus.BROKEN))) {%>
                        <button class="  btn btn-primary " name="command" value="repair" type="submit">Repaired</button>
                        <%}%>
                    </div>
                </form>
            </div>

            <%}%>

        </div>

    </div>
</form>
<%}%>
</body>
</html>
