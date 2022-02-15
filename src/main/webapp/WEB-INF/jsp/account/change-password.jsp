<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="auth-wrapper change-password footer-bt0">
    <div class="auth-content">
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
                    <h3 class="font-weight-bold">Đổi mật khẩu</h3>
                    <h4 id='username'>Người dùng: <span>${pageContext.request.userPrincipal.name}</span></h4>
                </div>
                <form action = "<c:url value='/change-password' />" method="post">
                    <div class="form-group">
                        <span class="password-icon psw">
                            <i class="fas fa-key"></i>
                        </span>
                        <input type="password" class="form-control" name="oldPassword"
                            required pattern="[A-Za-z0-9]{6,8}" title="Chiều dài 6-8 ký tự (a-z, A-Z, 0-9)"
                            placeholder="Nhập mật khẩu cũ" required>
                    </div>
                    <div class="form-group">
                        <span class="password-icon psw">
                            <i class="fas fa-key"></i>
                        </span>
                        <input type="password" class="form-control" name ="newPassword"
                              required pattern="[A-Za-z0-9]{6,8}" title="Chiều dài 6-8 ký tự (a-z, A-Z, 0-9)"
                            placeholder="Nhập mật khẩu mới" required>
                    </div>
                    <div class="form-group">
                        <span class="password-icon psw">
                            <i class="fas fa-key"></i>
                        </span>
                        <input type="password" class="form-control" name="confirmPassword"
                            required pattern="[A-Za-z0-9]{6,8}" title="Chiều dài 6-8 ký tự (a-z, A-Z, 0-9)"
                            placeholder="Nhập lại mật khẩu mới" required>
                    </div>
                    <button type="submit" class="btn_1 full_width text-center">Đổi mật khẩu</button>
                </form>
            </div>
        </div>
    </div>
</div>