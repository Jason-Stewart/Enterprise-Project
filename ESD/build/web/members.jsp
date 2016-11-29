<%-- 
    Document   : members
    Created on : 29-Nov-2016, 12:51:29
    Author     : Owen Harvey
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<style>
body {
    font-family: "Lato", sans-serif;
    transition: background-color .5s;
}

.sidenav {
    height: 100%;
    width: 0;
    position: fixed;
    z-index: 1;
    top: 0;
    left: 0;
    background-color: #111;
    overflow-x: hidden;
    transition: 0.5s;
    padding-top: 60px;
}

.sidenav a {
    padding: 8px 8px 8px 32px;
    text-decoration: none;
    font-size: 25px;
    color: #818181;
    display: block;
    transition: 0.3s
}

.sidenav a:hover, .offcanvas a:focus{
    color: #f1f1f1;
}

.sidenav .closebtn {
    position: absolute;
    top: 0;
    right: 25px;
    font-size: 36px;
    margin-left: 50px;
}

#main {
    transition: margin-left .5s;
    padding: 45px;
}

@media screen and (max-height: 450px) {
  .sidenav {padding-top: 15px;}
  .sidenav a {font-size: 18px;}
}
</style>
<body>

<div id="mySidenav" class="sidenav">
  <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
  <a href="adminhome.jsp">Home</a>
  <a href="members">Members</a>
  <a href="claims">Claims</a>
  <a href="finance">Finance</a>
  

</div>

<div id="Members">

  <h2>View-Members</h2>
  <span style="font-size:30px;cursor:pointer" onclick="openNav()">&#9776; open</span>
  <p>View/Edit Membership Status</p>
</div>
    
    <body>
        <form method="POST" action="members">
            Search: <input type="text" name="value"><br>
            <select name="criteria">
            <option value="">Select Field</option>
            <option value="id">id</option>
            <option value="name">name</option>
            <option value="address">address</option>
            <option value="dob">dob</option>
            <option value="dor">dor</option>
            <option value="status">status</option>
            <option value="balance">balance</option>
            </select>            
            <input type="submit" value="Submit"><br>
        </form>
        
    </body>
    
<% 
   String table = (String)request.getAttribute("memberTable");
   out.println(table);
%>

<body>
    <form action="members">
        ID: <input type="text" name="id" ><br>
        <select name="status">
         <option value="APPROVED">approve</option>
         <option value="SUSPENDED">suspend</option>
        </select>
     <input type="submit" value="Submit">
    </form>
</body>

<script>
function openNav() {
    document.getElementById("mySidenav").style.width = "250px";
    document.getElementById("main").style.marginLeft = "250px";
    document.body.style.backgroundColor = "rgba(0,0,0,0.4)";
}

function closeNav() {
    document.getElementById("mySidenav").style.width = "0";
    document.getElementById("main").style.marginLeft= "0";
    document.body.style.backgroundColor = "white";
}
</script>
     
</body>
</html> 

