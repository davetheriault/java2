
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="title" scope="request" value="Home | filmventory"/>
<%@include file="includes/fvheader.jsp" %>
<%@include file="includes/fbsdk.html" %>


<main class="w3-main w3-roboto" >

    <div class="w3-card w3-margin">
        <h3 class="w3-red w3-padding-left w3-margin-0 w3-roboto">
            Add Movies</h3>
        <div class="w3-container">
            <form class="w3-form" id="findmov" action="FindMovie" method="get">
                <input class="w3-input" type="text" name="search" placeholder="Search Movies by Title..." required />
                <input type="submit" value="Search" form="findmov" name="movSubmit" />
            </form>
            
        </div>
        
    </div>
    <br>

</main>
</body>
</html>
