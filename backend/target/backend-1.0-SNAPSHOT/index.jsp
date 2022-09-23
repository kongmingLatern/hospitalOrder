<%--
  Created by IntelliJ IDEA.
  User: kele
  Date: 2022/9/22
  Time: 23:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form >
    <input type="text" name="doctorName" id ="doctorName" />
    <input type="text" name="age" id="age"/>
    <input type="text" name="position" id="position"/>
    <input type="text" name="department" id=department"/>
    <input type="submit" id="submit" />
</form>
<script src="js/axios-0.18.0.js"></script>
<script>
    var formdata={
        doctorName:"",
        age:"",
        position:"",
        department:"",
    }
    document.getElementById("submit").onclick= function (){
        formdata.doctorName = document.getElementById("doctorName").value;
        formdata.age = document.getElementById("age").value;
        formdata.position = document.getElementById("position").value;
        formdata.department = document.getElementById("department").value;
        axios({
            method:"post",
            url:"http://localhost:8080/doctor/add",
            data:{formdata}
        })
    }
</script>
</body>
</html>
