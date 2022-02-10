<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!-- Carousel -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/owl.carousel.min.js"></script>
<!-- Script Intro -->
<script>
    $(document).ready(function () {
        $('#owl-intro').owlCarousel({
            loop: true,
            nav: false,
            autoplay: true,
            autoplayTimeout: 4000,
            margin: 10,
            items: 1
        });
    })
</script>