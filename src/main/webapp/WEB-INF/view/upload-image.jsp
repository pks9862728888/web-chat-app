<!DOCTYPE html>
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

        <div class="upload-container">
            <form action="/process-uploaded-image" method="POST" enctype="multipart/form-data">
                <div class="form-group">
                    <label for="file">Upload image file:</label>
                    <input type="file" class="form-control" id="file" name="file">
                </div>
                <button type="submit" class="btn btn-primary">Submit</button>
            </form>
        </div>
    </div>

</body>

</html>