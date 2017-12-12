<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Duplicated Entry!</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <style>
        @media (min-width: 1200px) {
            .container {
                max-width: 970px;
            }
        }

        .dlk-radio input[type="radio"],
        .dlk-radio input[type="checkbox"] {
            margin-left: -99999px;
            display: none;
        }

        .dlk-radio input[type="radio"] + .fa,
        .dlk-radio input[type="checkbox"] + .fa {
            opacity: 0.15
        }

        .dlk-radio input[type="radio"]:checked + .fa,
        .dlk-radio input[type="checkbox"]:checked + .fa {
            opacity: 1
        }
    </style>
</head>
<body>
<div class="container">
    <div class=" row page-header">
        <div class="col-sm-4">
            <h1>Salad chef</h1>
        </div>
        <div class="col-sm-8">
            <ol class="breadcrumb" style="margin-top: 25px">
                <li><a href="/">Home</a></li>
                <li><a href="/salads">Order salad</a></li>
                <li><a href="/ingredients">Ingredients we use</a></li>
                <li><a href="/create/ingredient">Create ingredient</a></li>
                <li><a href="/create/salad">Create recipe</a></li>
            </ol>
        </div>
    </div>
    <h1>Duplicated entity</h1>
    <c:if test="${not empty requestScope.entity}">
        <p> Entity name: ${requestScope.entity.name}</p>
    </c:if>
    <c:if test="${not empty requestScope.message}">
        <p>${requestScope.message}</p>
    </c:if>
</div>
</body>
</html>