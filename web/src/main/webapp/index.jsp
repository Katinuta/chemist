
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Hello, world!</title>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous"> -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>
<body>
<header></header>
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">WebSiteName</a>
        </div>
        <ul class="nav navbar-nav">
            <li class="active"><a href="#">Home</a></li>
            <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Page 1 <span
                    class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li><a href="#">Page 1-1</a></li>
                    <li><a href="#">Page 1-2</a></li>
                    <li><a href="#">Page 1-3</a></li>
                </ul>
            </li>
            <li><a href="#">Page 2</a></li>
        </ul>


    </div>
</nav>
<main>
    <div class="container">
        <h2>Vertical (basic) form</h2>
        <c:out value=""></c:out>
        <form action="chemist">
            <div class="form-group">
                <label for="name">Name:</label>
                <input class="form-control" id="name" placeholder="Enter name" name="name" required>
            </div>
            <div class="form-group">
                <label for="surname">Фамилия:</label>
                <input class="form-control" id="surname" placeholder="Enter surname" name="surname">
            </div>
            <div class="form-group">
                <label for="login">Login:</label>
                <input class="form-control" id="login" placeholder="Enter login" name="login">
            </div>
            <div class="form-group">
                <label for="pwd">Password:</label>
                <input type="password" class="form-control" id="pwd" placeholder="Enter password" name="pwd">
            </div>
            <div class="form-group">
                <label for="phone">Phone:</label>
                <input class="form-control" id="phone" placeholder="Enter phone" name="phone">
            </div>
            <div class="form-group">
                <label for="account">Account:</label>
                <input class="form-control" id="account" placeholder="Enter adress" name="account">
            </div>


            <div class="checkbox">
                <label><input type="checkbox" name="remember"> Remember me</label>
            </div>
            <button type="submit" class="btn btn-default">Submit</button>
        </form>
    </div>

</main>
<footer class="container-fluid text-center">Footer</footer>


</body>
</html>