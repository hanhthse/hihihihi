<%-- 
    Document   : navigationBar
    Created on : Jan 19, 2024, 7:38:08 PM
    Author     : Acer
--%>
<%@page import="com.apethotel.entity.Users"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.bookstore.Constant.Constant"%>
<style>
    .dropdown {
        position: relative;
        display: inline-block;
    }

    .dropdown-content {
        display: none;
        position: absolute;
        background-color: #f9f9f9;
        min-width: 160px;
        margin-left: 10px;
        box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
        z-index: 1;
    }

    .dropdown-content a {
        color: black;
        padding: 12px 16px;
        text-decoration: none;
        display: block;
    }

    .dropdown-content a:hover {background-color: #f1f1f1}

    .dropbtn {
        background-color: white;
        color: black;
        padding:5px;
        font-size: 18px;
        border: 1;
        cursor: pointer;
    }

    .dropbtn:hover, .dropbtn:focus {
        background-color: #3e8e41;
    }

    .dropdown:hover .dropdown-content {
        display: block;
    }

    .dropdown:hover .dropbtn {
        background-color: #007bff;
    }

</style>

<section id="navigation-bar">
    <div class="container-fluid">
        <nav class="navbar navbar-expand-lg navbar-light ">

            <a href="home" class="navbar-brand logo"><img src="https://i.ibb.co/xJncp9Z/Screenshot-2024-01-26-234033.png" alt="logo" border="0" style="height: 100px"></a>

            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo01"
                    aria-controls="navbarTogglerDemo01" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarTogglerDemo01">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item"> <a href="home" class="nav-link">Home</a> </li>
                    <li class="nav-item"><a href="#footer" class="nav-link">About</a></li>
                    <li class="nav-item"><a href="#cages" class="nav-link">All product</a></li>


                </ul>

                <nav class="navbar navbar-light pl-sm-0">
                    <form class="form-inline" action="home" method="get">
                        <input type="hidden" name="action" value="search"/>
                        <input class="form-control mr-sm-2" 
                               type="search"
                               placeholder="Search"
                               aria-label="Search"
                               name="keyword"
                               value="<%=request.getAttribute("findKeyWord") != null
                                       ? request.getAttribute("findKeyWord") : ""%>">
                        <button class="btn btn-outline-success my-2 my-sm-0 ml-sm-0" type="submit">Search</button>
                    </form>
                </nav>      
                <%
                    Users acc = (Users) session.getAttribute(Constant.SESSION_ACCOUNT);
                    if (acc == null) { %>
                <a href="${pageContext.request.contextPath}/authen?action=login">
                    <button class="btn btn-outline-primary ml-2">Login</button>
                </a>

                <%} else {%>

                <div class="dropdown user-info">
                    <button class="btn btn-outline-primary ml-2 dropbtn"><%= acc.getName()%></button>
                    <div class="dropdown-content">
                        <a href="#editprofile">Edit Profile</a>
                        <a href="${pageContext.request.contextPath}/authen?action=logout">Sign out</a>
                    </div>
                </div>
                <% if (acc.getRoleId() == Constant.ROLE_ADMIN) { %>
                <a href="admin/dashboard">
                    <button class="btn btn-outline-secondary ml-2">Manages</button>
                </a>
                <%}%>
                <%}%>





            </div>

        </nav>
    </div>
</section>
