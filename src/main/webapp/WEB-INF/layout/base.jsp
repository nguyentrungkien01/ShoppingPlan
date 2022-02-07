<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<!DOCTYPE html>
   <html>
       <head>
           <meta charset="utf-8">
           <meta name="viewport" content="width=device-width, initial-scale=1">
             <!-- Latest compiled and minified CSS -->
             <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">

             <!-- jQuery library -->
             <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"></script>

             <!-- Popper JS -->
             <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>

             <!-- Latest compiled JavaScript -->
             <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>

            <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>

          <!-- sweet alert -->
          <script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.2/sweetalert.min.js" integrity="sha512-AA1Bzp5Q0K1KanKKmvN/4d3IRKVlv9PYgwFPvm32nPO6QS8yH1HO7LbgB1pgiOxPtfeg5zEn2ba64MUcqJx6CA==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>

            <link href="<c:url value = '/css/sidebar.css' />"  rel= "stylesheet"/>
            <script src="<c:url value = '/js/sidebar.js' />"></script>
            <tiles:insertAttribute  name= "ref" />

       </head>
        <body>
        	<div class="wrapper d-flex align-items-stretch">
                <tiles:insertAttribute name = "sidebar"/>

                     <!-- Page Content  -->
                <div id="content" class="p-4 p-md-5 pt-5">
                    <tiles:insertAttribute name="header" />
                    <tiles:insertAttribute name="content" />
                    <tiles:insertAttribute name="footer"/>
                </div>
            </div>
           
        </body>
</html>