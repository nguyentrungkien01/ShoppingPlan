<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- main header start -->
<div class="hc-main-header">
  <div class="container">
    <div class="row">
      <div class="col-md-3 col-sm-6">
        <div class="hc-logo">
          <a href="index.html">
            <img src="<c:url value = '/css/logo.svg' />" alt="logo" style="width: 80px; height: auto" />
          </a>
        </div>
      </div>

      <div class="col-md-9 col-sm-6">
        <div class="hc-main-head-flex">
          <div class="hc-main-menu">
            <div class="hc-navbar">
              <ul>
                <li class="active">
                  <a href="#tongquan" class="page-scroll">Tổng quan</a>
                </li>
                <li>
                  <a href="#muctieu" class="page-scroll">Mục tiêu</a>
                </li>
              </ul>
            </div>

            <!-- Toogle menu bars start -->
            <div class="hc-menu-toggle">
              <span></span>
              <span></span>
              <span></span>
            </div>
            <!-- Toogle menu bars end -->
          </div>

          <!-- Appointment start -->
          <div class="hc-head-btn">
            <a href="<c:url value = '/sign-in' />" class="hc-btn">
              Đăng nhập
            </a>
          </div>
          <!-- Appointment end -->
        </div>
      </div>
    </div>
  </div>
</div>
<!-- main header end -->