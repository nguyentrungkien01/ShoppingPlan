<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<c:if test="${param.error!=null}">
    <script>
         swal(
            'Đăng nhập thất bại',
            'Thông tin bị sai hoặc tài khoản của bạn đã bị khóa do vi phạm nội quy chung. Vui lòng kiểm tra lại!',
            'error'
            )
    </script>
</c:if >

<div class="auth-wrapper">
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
                    <h3 class="font-weight-bold">Đăng nhập</h3>
                </div>
                <form method="post" action="<c:url value = '/sign-in' />">
                    <div class="form-group">
                        <span class="user-icon"><i class="fas fa-user-nurse"></i></span>
                        <label>Tên đăng nhập</label>
                        <input type="text" name="username" class="form-control" placeholder="Nhập tài khoản"
                            minlength="8" maxlength="20"  />
                    </div>
                    <div class="position-relative form-group">
                        <span class="password-icon"> <i class="fas fa-key"></i></span>
                        <label>Mật khẩu</label>
                        <input class="form-control" placeholder="Password" type="password" name="password"
                            minlength="6" maxlength="8">
                        <div class="show-password">
                            <i class="far fa-eye"></i>
                        </div>
                    </div>
                    <div class="form-group btn-center">
                        <input type="submit" value="Đăng nhập" class="btn_1 full_width text-center" />
                        <a href="<c:url value = '/sign-up' />" class="btn_1 full_width text-center">Đăng ký</a>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>