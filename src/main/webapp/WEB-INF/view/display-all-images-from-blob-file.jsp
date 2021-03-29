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
                        <a class="nav-link" href="/upload-image-to-shared-location">Upload Image to Shared Location</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/view-all-images-from-shared-location">View Images from Shared Location</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/upload-image-to-database">Upload Image to Database</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/view-all-images-from-database">View Images from Database</a>
                    </li>
                </ul>
            </nav>

            <div class="container-fluid background-light-blue">
                <c:choose>
                    <c:when test="${images.size() == 0}">
                        <h4 class="p-1rem">No images found.</h4>
                    </c:when>
                    <c:otherwise>
                        <div class="row p-tb-1rem">
                            <c:forEach var="imageObj" items="${images}">
                                <div class="col-4 p-1rem">
                                    <img src="data:image/jpeg;base64,${imageObj.getBase64EncodedFile()}" class="rounded img-thumbnail" />
                                </div>
                            </c:forEach>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </body>
</html>