
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Sidebar -->
<div class="sidebar sidebar-style-2" data-background-color="dark2">
	<div class="sidebar-wrapper scrollbar scrollbar-inner">
		<div class="sidebar-content">
			<ul class="nav nav-primary">
                <li class="active nav-item">
                    <a href="javascript:;"><p>Trang chủ</p></a>
                </li>
                <li class="nav-item">
                    <a href="javascript:;"><p>Quầy hàng</p></a>
                </li>
                <li class="nav-item">
                    <a href="javascript:;"><p>Đường đi</p></a>
                </li>
				<li class="nav-item">
                    <a href="<c:url value='/sign-out'/>"><p>Đăng xuất</p></a>
                </li>
			</ul>
		</div>
	</div>
</div>
<!-- End Sidebar -->