<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<div class="auth-wrapper">
  <div class="col-12 col-lg-7 auth-content">
    <div class="auth-bg">
      <span class="r"></span>
      <span class="r s"></span>
      <span class="r s"></span>
      <span class="r"></span>
    </div>
    <div class="card card-shadow">
      <div class="card-body">
        <div class="mb-4 text-center">
          <i class="fas fa-unlock-alt auth-icon mb-4"></i>
          <h3 class="font-weight-bold">Đăng ký</h3>
        </div>
        <form method="post" action="<c:url value = '/sign-up' />">
          <div class="card-body">
            <div class="separator-solid"></div>

            <div class="card-category">Nhập thông tin cá nhân</div>
            <div class="form-group form-show-validation row">
              <label for="last_name" class="col-lg-3 col-md-3 col-sm-4 mt-sm-2 text-right">Họ và tên đệm <span
                  class="required-label">*</span></label>
              <div class="col-lg-8 col-md-9 col-sm-8">
                <input type="text" class="form-control" id="last_name" name="lastName" maxlength="20"
                  placeholder="Nhập họ và tên đệm..." required pattern=".{1,20}"
                  oninvalid="this.setCustomValidity('Chiều dài từ 1-20 ký tự')" oninput="this.setCustomValidity('')">
              </div>
            </div>

            <div class="form-group form-show-validation row">
              <label for="first_name" class="col-lg-3 col-md-3 col-sm-4 mt-sm-2 text-right">Tên <span
                  class="required-label">*</span></label>
              <div class="col-lg-8 col-md-9 col-sm-8">
                <input type="text" class="form-control" id="first_name" name="firstName" maxlength="50"
                  placeholder="Nhập tên..." required pattern=".{1,50}" title="Chiều dài từ 1-50 ký tự"
                  oninvalid="this.setCustomValidity('Chiều dài từ 1-50 ký tự')" oninput="this.setCustomValidity('')">
              </div>
            </div>

            <div class="form-group form-show-validation row">
              <label for="idCard" class="col-lg-3 col-md-3 col-sm-4 mt-sm-2 text-right">CCDD<span
                  class="required-label">*</span></label>
              <div class="col-lg-8 col-md-9 col-sm-8">
                <input type="text" class="form-control" id="idCard" name="idCard"
                  placeholder="Nhập căn cước công dân..." required>
              </div>
            </div>
            <div class="form-group form-show-validation row">
              <label for="idCard" class="col-lg-3 col-md-3 col-sm-4 mt-sm-2 text-right">Số điện thoại<span
                  class="required-label">*</span></label>
              <div class="col-lg-8 col-md-9 col-sm-8">
                <input type="text" class="form-control" id="phoneNumber" name="phoneNumber"
                  placeholder="Nhập số điện thoại..." required>
              </div>
            </div>

            <div class="separator-solid"></div>

            <div class="form-group form-show-validation row">
              <label class="col-lg-3 col-md-3 col-sm-4 mt-sm-2 text-right">Giới tính <span
                  class="required-label">*</span></label>
              <div class="col-lg-8 col-md-9 col-sm-8 d-flex align-items-center">
                <div class="custom-control custom-radio">
                  <input type="radio" id="male" name="sex" class="custom-control-input" value="male" checked>
                  <label class="custom-control-label" for="male">Nam</label>
                </div>
                <div class="custom-control custom-radio">
                  <input type="radio" id="female" name="sex" class="custom-control-input" value="female">
                  <label class="custom-control-label" for="female">Nữ</label>
                </div>
              </div>
            </div>
            <div class="form-group form-show-validation row">
              <label for="idCard" class="col-lg-3 col-md-3 col-sm-4 mt-sm-2 text-right">Ngày sinh<span
                  class="required-label">*</span></label>
              <div class="col-lg-8 col-md-9 col-sm-8">
                <input type="date" class="form-control" id="dateOfBirth" name="dateOfBirth" required>
              </div>
            </div>
            <div class="form-group form-show-validation row">
              <label for="facebookLink" class="col-lg-3 col-md-3 col-sm-4 mt-sm-2 text-right">Facebook cá
                nhân</label>
              <div class="col-lg-8 col-md-9 col-sm-8">
                <input type="url" class="form-control" id="facebookLink" name="facebookLink" placeholder="https://">
              </div>
            </div>
            <div class="separator-solid"></div>

            <div class="card-category">Nhập thông tin tài khoản</div>
            <div class="form-group form-show-validation row">
              <label for="username" class="col-lg-3 col-md-3 col-sm-4 mt-sm-2 text-right">Tên đăng nhập <span
                  class="required-label">*</span></label>
              <div class="col-lg-8 col-md-9 col-sm-8">
                <input type="text" class="form-control" id="username" name="username"
                  placeholder="Nhập tên tài khoản..." required pattern="[A-Za-z0-9]{5,10}"
                  oninvalid="this.setCustomValidity('Chiều dài 5-10 ký tự (a-z, A-Z, 0-9)')"
                  oninput="this.setCustomValidity('')">
              </div>
            </div>

            <div class="form-group form-show-validation row">
              <label for="password" class="col-lg-3 col-md-3 col-sm-4 mt-sm-2 text-right">Mật khẩu
                <span class="required-label">*</span></label>
              <div class="col-lg-8 col-md-9 col-sm-8">
                <input type="password" class="form-control" id="password" name="password" placeholder="Nhập mật khẩu..."
                  required pattern="[A-Za-z0-9]{6,8}"
                  oninvalid="this.setCustomValidity('Chiều dài 6-8 ký tự (a-z, A-Z, 0-9)')"
                  oninput="this.setCustomValidity('')">
              </div>
              <div class="invalid-feedback">
                Mật khẩu không được để trống
              </div>
            </div>

            <div class="form-group form-show-validation row">
              <label for="password" class="col-lg-3 col-md-3 col-sm-4 mt-sm-2 text-right">Nhập lại mật khẩu
                <span class="required-label">*</span></label>
              <div class="col-lg-8 col-md-9 col-sm-8">
                <input type="password" class="form-control" id="confirmPassword" name="confirmPassword"
                  placeholder="Nhập lại mật khẩu..." required pattern="[A-Za-z0-9]{6,8}"
                  title="Chiều dài 6-8 ký tự (a-z, A-Z, 0-9)"
                  oninvalid="this.setCustomValidity('Chiều dài 6-8 ký tự (a-z, A-Z, 0-9)')"
                  oninput="this.setCustomValidity('')">
              </div>
              <div class="invalid-feedback">
                Mật khẩu không được để trống
              </div>
            </div>

            <div class="separator-solid"></div>

            <div class="form-group btn-center">
              <input type="submit" class="btn btn-success" value="Đăng ký">
              <a href="<c:url value = '/sign-in' />" class="btn_1 full_width text-center btn-danger">Quay lại đăng nhập</a>
            </div>
          </div>
        </form>
      </div>
    </div>
  </div>
</div>