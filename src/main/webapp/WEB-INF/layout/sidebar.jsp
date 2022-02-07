
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<nav id="sidebar">
	<%-- <a href="#" class="img logo rounded-circle mb-5" style="background-image: url(https://res.cloudinary.com/nguyentrungkien/image/upload/v1639903260/i0ivuvbzf3szqis83ezf.jpg);"></a> --%>
	<div class="custom-menu">
		<button type="button" id="sidebarCollapse" class="btn btn-primary">
			<i class="fa fa-bars"></i>
			<span class="sr-only">Toggle Menu</span>
		</button>
	</div>
	<div class="p-4">
		<%-- <h1>Ten khach hang</h1> --%>
		<ul class="list-unstyled components mb-5">
			<li class="active">
				<a href="#"><span class="fa fa-home mr-3"></span> Trang chủ</a>
			</li>
			<li>
				<a href="#"><span class="fa fa-user mr-3"></span> Quầy hàng</a>
			</li>
				<li>
				<a href="#"><span class="fa fa-user mr-3"></span> Đường đi</a>
			</li>
		<li>
				<a href="<c:url value='/sign-out'/>"><span class="fa fa-user mr-3"></span> Đăng xuất</a>
			</li>
		</ul>

	</div>
</nav>