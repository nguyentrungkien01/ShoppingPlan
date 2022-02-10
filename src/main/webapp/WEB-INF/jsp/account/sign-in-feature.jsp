<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:if test="${param.error!=null}">
<div class="modal fade" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle"
    aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="swal2-popup" style="display: grid;">
            <div class="swal2-icon swal2-error swal2-icon-show" style="display: flex;"><span class="swal2-x-mark">
                    <span class="swal2-x-mark-line-left"></span>
                    <span class="swal2-x-mark-line-right"></span>
                </span>
            </div>
            <h2 class="swal2-title" id="swal2-title" style="display: block;">Đăng nhập thất bại</h2>
            <div class="swal2-html-container" id="swal2-html-container" style="display: block;">Sai tên đăng nhập hoặc mật khẩu!</div>
            <div class="swal2-actions" style="display: flex;">
                <div class="swal2-loader"></div>
                <button type="button" class="swal2-confirm swal2-styled" aria-label="" style="display: inline-block;"
                    data-dismiss="modal">OK</button>
            </div>
        </div>
    </div>
</div>
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
                            minlength="8" maxlength="20" value="username1" />
                    </div>
                    <div class="position-relative form-group">
                        <span class="password-icon"> <i class="fas fa-key"></i></span>
                        <label>Mật khẩu</label>
                        <input class="form-control" placeholder="Password" type="password" name="password"
                            minlength="6" maxlength="8" value="12345678">
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