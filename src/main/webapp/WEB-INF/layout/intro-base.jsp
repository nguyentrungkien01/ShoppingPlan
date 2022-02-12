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
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

    <!-- Popper JS -->
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>

    <!-- Latest compiled JavaScript -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>

    <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"
        integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous" />

    <!-- Fullpage -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/fullPage.js/2.9.7/jquery.fullpage.min.css" />
    <!-- Main css -->
    <link href="<c:url value = '/css/intro.css' />" rel="stylesheet" />
    <!-- Cursor -->
    <link rel="stylesheet" href="<c:url value = '/plugin/cursor/cursor.css' />" />
    <!-- sweet alert -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.2/sweetalert.min.js"
        integrity="sha512-AA1Bzp5Q0K1KanKKmvN/4d3IRKVlv9PYgwFPvm32nPO6QS8yH1HO7LbgB1pgiOxPtfeg5zEn2ba64MUcqJx6CA==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <tiles:insertAttribute name="ref" />
</head>

<body data-background-color="dark">
    <div class="wrapper">
        <tiles:insertAttribute name="header" />
        <tiles:insertAttribute name="content" />
    </div>

    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <!-- jQuery UI -->
    <script src="<c:url value ='/plugin/jquery-ui-1.12.1.custom/jquery-ui.min.js'/>"></script>
    <script src="<c:url value ='/plugin/jquery-ui-touch-punch/jquery.ui.touch-punch.min.js'/>"></script>
    <!-- jQuery Scrollbar -->
    <script src="<c:url value ='/plugin/jquery-scrollbar/jquery.scrollbar.min.js'/>"></script>

    <!-- Fullpage -->
    <!-- This following line is optional. Only necessary if you use the option css3:false and you want to use other easing effects rather than "easeInOutCubic". -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/fullPage.js/2.9.7/vendors/jquery.easings.min.js"></script>
    <!-- This following line is only necessary in the case of using the option `scrollOverflow:true` -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/fullPage.js/2.9.7/vendors/scrolloverflow.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/fullPage.js/2.9.7/jquery.fullpage.min.js"></script>

    <!-- Main js -->
    <script src="<c:url value = '/js/intro.js' />"></script>
    <script src="<c:url value = '/plugin/cursor/cursor.js' />"></script>

    <script>
        $(document).ready(function () {
            $('#fullpage').fullpage({
                //options here
                autoScrolling: true,
                scrollHorizontally: true,
                anchors: ['tongquan', 'muctieu'],
                navigation: true,
                navigationPosition: 'right',
                showActiveTooltip: true,
                slidesNavigation: true,
                slidesNavPosition: 'bottom',

            });

            //methods
            $.fn.fullpage.setAllowScrolling(true);

        });
    </script>
    <script>
        // Section Menu Active
        var scrollLink = $('.page-scroll');
        // Active link switching
        $(window).scroll(function () {
            var scrollbarLocation = $(this).scrollTop();

            scrollLink.each(function () {

                var sectionOffset = $(this.hash).offset().top - 73;

                if (sectionOffset <= scrollbarLocation) {
                    $(this).parent().addClass('active');
                    $(this).parent().siblings().removeClass('active');
                }
            });
        });
    </script>
</body>

</html>