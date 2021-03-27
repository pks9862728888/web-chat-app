<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <html lang="en">

    <head>
        <title>Webchat | Upload Image</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <link rel="stylesheet" href="/css/index.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </head>

    <body>

        <div class="home-body">
            <nav class="navbar navbar-expand-sm bg-dark">
                <a class="navbar-brand" href="/">Webchat</a>
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link" href="/upload-image">Upload Image</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/view-all-images">View Images</a>
                    </li>
                </ul>
            </nav>

            <div class="container-fluid background-light-blue">
                <c:forEach var="imageObj" items="${images}">
                    <p>Id: ${imageObj.getId()} Name: ${imageObj.getName()}</p>
                </c:forEach>
            </div>
        </div>

    </body>

    </html>