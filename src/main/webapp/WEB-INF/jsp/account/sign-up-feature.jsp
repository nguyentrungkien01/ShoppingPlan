<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<form  method = "post" action="<c:url value = '/sign-up' />" >
    <h1>Thông tin cá nhân</h1>
    <div class="form-group">
         <label for="lastName">Họ và tên đệm <span>(*)<span> </label>
        <input type="text" class="form-control" placeholder="Nguyễn Văn"
            id="lastName" name = "lastName" required
            pattern=".{1,20}" title="Chiều dài từ 1-20 ký tự">
    </div>

      <div class="form-group">
        <label for="firstName">Tên <span>(*)<span> </label>
      <input type="text" class="form-control" placeholder="An"
          id="firstName" name = "firstName" required
          pattern=".{1,50}" title="Chiều dài từ 1-50 ký tự">
       </div>

        <div class="form-check form-check-inline">
          <label class="form-check-label" for="male">Nam </label>
          <input class="form-check-input" type="radio" name="sex" id="male" checked>
        </div>

        <div class="form-check form-check-inline">
          <label class="form-check-label" for="female">Nữ </label>
          <input class="form-check-input" type="radio" name="sex" id="female" >

        </div>

         <div class="form-group">
           <label for="idCard">Căn cước công dân <span>(*)<span> </label>
           <input type="text" class="form-control" placeholder="0123456789"
               id="idCard" name = "idCard" required
               pattern="[0-9]{12}" title="Chiều dài 12 ký tự (0-9)">
        </div>

         <div class="form-group">
           <label for="facebookLink">Địa chỉ facebook cá nhân: </label>
           <input type="url" class="form-control" placeholder="https://"
               id="facebookLink" name = "facebookLink">
        </div>

    <h1>Thông tin tài khoản</h1>
     <div class="form-group">
        <label for="username">Tên đăng nhập <span>(*)<span> </label>
        <input type="text" class="form-control" placeholder="tendangnhap"
            id="username" name = "username" required
              pattern="[A-Za-z0-9]{5,10}" title="Chiều dài 5-10 ký tự (a-z, A-Z, 0-9)">
        <div class="invalid-feedback">
              Tên đăng nhập không được để trống
          </div>
      </div>

      <div class="form-group">
          <label for="password">Mật khẩu <span>(*)<span> </label>
          <input type="password" class="form-control" placeholder="Nhập mật khẩu"
            id="password" name = "password" required
              pattern="[A-Za-z0-9]{6,8}" title="Chiều dài 6-8 ký tự (a-z, A-Z, 0-9)">
         <div class="invalid-feedback">
              Mật khẩu không được để trống
         </div>
       </div>

        <div class="form-group">
             <label for="password">Nhập lại mật khẩu <span>(*)<span> </label>
             <input type="password" class="form-control" placeholder="Nhập lại mật khẩu"
               id="confirmPassword" name = "confirmPassword" required
                 pattern="[A-Za-z0-9]{6,8}" title="Chiều dài 6-8 ký tự (a-z, A-Z, 0-9)">
            <div class="invalid-feedback" >
               Mật khẩu xác nhận không được để trống
            </div>
        </div>
      <button type="submit" class="btn btn-primary">Đăng ký</button>
</form>
