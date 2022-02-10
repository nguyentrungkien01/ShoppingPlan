
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Sidebar -->
<div class="sidebar sidebar-style-2" data-background-color="dark2">
	<div class="sidebar-wrapper scrollbar scrollbar-inner">
		<div class="sidebar-content">
			<ul class="nav nav-primary" id ="sidebarContent">
                <li class="nav-item" id="1">
                    <a href="<c:url value ='/homepage'/>"><p>Trang chủ</p></a>
                </li>
                <li class="nav-item" id="2">
                    <a href="<c:url value ='/stall'/>"><p>Quầy hàng</p></a>
                </li>
                <li class="nav-item" id="3">
                    <a href="<c:url value ='/plan'/>"><p>Kế hoạch</p></a>
                </li>
			</ul>
		</div>
	</div>
</div>
<!-- End Sidebar -->