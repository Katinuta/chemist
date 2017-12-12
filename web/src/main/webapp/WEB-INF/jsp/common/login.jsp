<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="css/normalize.css">
    <link href="https://fonts.googleapis.com/css?family=Quicksand" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Abril+Fatface" rel="stylesheet">
    <style>
        body {
            margin: 0;
            font-family: 'Quicksand', sans-serif;
            /*font-family: 'Abril Fatface', cursive;*/
            background-color: #00cc99;
            padding: 0% 25%;
            overflow: auto;
        }

        nav {
            list-style-type: none;
            margin: 0;
            padding: 0;
            overflow: hidden;

        }

        nav a {
            display: block;
            color: white;
            text-align: center;
            padding: 20px 15px;
            text-decoration: none;
            float: left;
            font-size: 24px;
        }

        a.right {
            float: right;

        }

        @media screen and (max-width: 600px) {
            nav a, a.right {
                float: none;
            }
        }

        header {

        }

        a.active {
            background-color: blue;
        }

        a:hover:not(.active) {
            background-color: #009973;
        }

        main {
            display: block;
            background-color: white;
            border-radius: 10px;
            height: 90vh;

            /*width: 50%;*/
            /*border: 2px solid #73AD21;*/
            /*position: static;*/

        }

        form::before {
            /*content: url(pills.jpg);*/

        }

        .form {
            position: relative;
            left: 20%;
            top: 10%;
            width: 60%;
            border-radius: 5px;
            background-color: #f2f2f2;
            padding: 20px;
            /*padding-right: 20px;*/

        }

        input[type=text], input[type=password], select {
            width: 100%;
            padding: 12px 20px;
            margin: 8px 0px;
            display: inline-block;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
            font-family: 'Quicksand', sans-serif;
        }

        input[type=submit] {
            width: 100%;
            background-color: #00cc99;
            color: white;
            padding: 14px 20px;
            margin: 8px 0;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        input[type=submit]:hover {
            background-color:  #009973;
        }

        select, option {
            font-family: 'Quicksand', sans-serif;
        }
    </style>
</head>
<body>
<header>
    <nav>
        <a href="" id="main">Chemist</a>
        <%--<a href="#home">Home</a>--%>
        <%--<a class="right" href="#sign_up">Sing up</a>--%>
        <%--<a class="right" href="#sign_in">Sign in</a>--%>

    </nav>
</header>
<main>
    <!-- <div class ="white"> -->
    <div class="form" >
        <form action="controller">
            <input name="command" type="hidden" name="login">
            <label for="login">Login</label>
            <input type="text" name="login" id="login" required placeholder="Your login...">
            <label for="password">Password</label>
            <input type="password" name="password" id="password" required placeholder="Your password...">

            <input type="submit" value="Submit">
        </form>
    </div>

</main>
<footer>

</footer>
</body>
</html>