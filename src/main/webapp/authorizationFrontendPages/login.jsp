<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>

<div class="container-fluid">
    <div class="row">
        <div class="col-6 offset-3 text-center bg-light">
            <form action="login" class="was-validated" method="post">
                <h1>Login our system</h1>
                <% if (request.getAttribute("error")!=null){%>
                <%= request.getAttribute("error")%>
                <br>
                <% request.setAttribute("error",null);}%>
                <div class="mb-3 mt-3 text-start">
                    <label for="username" class="form-label">Email:</label>
                    <input type="text" class="form-control" id="username" placeholder="Enter username" name="username" required>
                    <div class="valid-feedback">Valid.</div>
                    <div class="invalid-feedback">Please fill out this field.</div>
                </div>
                <div class="mb-3 text-start">
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
                <button type="submit" class="btn btn-primary">Login</button>
            </form>
            <br>
        </div>
    </div>
</div>
</body>
</html>