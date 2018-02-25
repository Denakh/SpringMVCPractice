<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>SpringMVCPractice</title>
</head>
<body>
<div align="center">
    <form action="/view" method="POST">
        View photos:
        <input type="submit"/>
    </form>

    <form action="/add_photo" enctype="multipart/form-data" method="POST">
        Add photo: <input type="file" name="photo">
        <input type="submit"/>
    </form>

    <form action="/filezip" enctype="multipart/form-data" method="POST">
        File for zip archive: <input type="file" name="file">
        <input type="submit" value="get zip archive"/>
    </form>
</div>
</body>
</html>
