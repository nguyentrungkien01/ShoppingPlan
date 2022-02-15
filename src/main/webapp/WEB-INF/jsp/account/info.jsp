<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="content">
    <div class="page-inner">
        <h4 class="page-title">Thông tin tài khoản</h4>
        <div class="row">
            <div class="col-md-8">
                <div class="card card-with-nav">
                    <div class="card-body">
                        <div class="row mt-3">
                            <div class="col-md-6">
                                <div class="form-group-default">
                                    <div class="pt-2 pb-2">
                                        <span class="text-success fw-bold">Họ và tên đệm:</span>
                                        <span>${info.get('lastName')}</span>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group-default">
                                    <div class="pt-2 pb-2">
                                        <span class="text-success fw-bold">Tên:</span>
                                        <span>${info.get('firstName')}</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group-default">
                                    <div class="pt-2 pb-2">
                                        <span class="text-success fw-bold">Ngày sinh:</span>
                                        <span>${info.get('dateOfBirth')}</span>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group-default">
                                    <div class="pt-2 pb-2">
                                        <span class="text-success fw-bold">Giới tính:</span>
                                        <span>${info.get('sex')}</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group-default">
                                    <div class="pt-2 pb-2">
                                        <span class="text-success fw-bold">Căn cước công dân:</span>
                                        <span>${info.get('idCard')}</span>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group-default">
                                    <div class="pt-2 pb-2">
                                        <span class="text-success fw-bold">Ngày tham gia:</span>
                                        <span>${info.get('dateJoined')}</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-group-default">
                                    <div class="pt-2 pb-2">
                                        <span class="text-success fw-bold">Địa chỉ facebook:</span>
                                        <c:choose>
                                            <c:when test="${info.get('facebookLink')==null}"><span class="not-fill">Chưa
                                                    cập nhật thông tin !</span></c:when>
                                            <c:otherwise> <span>${info.get('facebookLink')}</span></c:otherwise>
                                        </c:choose>

                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-group-default">
                                    <div class="pt-2 pb-2">
                                        <span class="text-success fw-bold">Số lượng quầy hàng cá nhân đang mở:</span>
                                        <span>${info.get('amountStall')}</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row mb-1">
                            <div class="col-md-12">
                                <div class="form-group-default">
                                    <div class="pt-2 pb-2">
                                        <span class="text-success fw-bold">Số lượng sản phẩm cá nhân đang bày
                                            bán:</span>
                                        <span>${info.get('amountProduct')}</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row mb-1">
                            <div class="col-md-12">
                                <div class="form-group-default">
                                    <div class="pt-2 pb-2">
                                        <span class="text-success fw-bold">Số lượng bị báo cáo:</span>
                                        <span>${info.get('amountReport')}</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="card card-profile">
                    <div class="card-header"
                        style="border-radius: 10px 10px 0 0;background-image: url('https://appsrv1-147a1.kxcdn.com/atlantis-pro/img/blogpost.jpg');">
                        <div class="profile-picture">
                            <div class="avatar avatar-xl">
                                <img src="https://res.cloudinary.com/nguyentrungkien/image/upload/v1644318450/default_avatar_mvuqgu_vigdu2_ashpil.png"
                                    alt="avatar" class="avatar-img rounded-circle">
                            </div>
                        </div>
                    </div>
                    <div class="card-body">
                        <div class="user-profile text-center">
                            <div class="name">
                                <spanc class="text-primary fw-bold">${pageContext.request.userPrincipal.name}</spanc>
                            </div>
                            <div class="job">
                                <span class="text-warning fw-bold">Vai trò:</span>
                                <span>khách hàng</span>
                            </div>
                            <div class="desc">
                                <span class="text-warning fw-bold">Số điện thoại: </span>
                                <div id="phoneNumberDatas" class="mt-2">
                                </div>
                                <a href="javascript:;" id="addPhoneNumber" class="btn btn-success mt-2 btn-sm"> <i
                                        class="fa fa-plus"></i> Thêm</a>
                                <form>
                                    <div class="form-group row">
                                        <input type="text" class="form-control col-9" id="inputPhoneNumber">
                                        <a href="javascript:;" id="confirm" class="ml-2 btn btn-success"><i
                                                class="fa fa-check"></i></a>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>