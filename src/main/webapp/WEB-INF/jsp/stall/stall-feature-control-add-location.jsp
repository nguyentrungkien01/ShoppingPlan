<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="page-inner">
    <h4 class="page-title">Thêm toạ độ</h4>
    <div class="row">
        <div class="col-12">
            <div class="card">
                <div class="card-header">
                    <div class="card-title">Thông tin toạ độ</div>
                </div>
                <div class="card-body">
                    <form action="<c:url value='/stall/add/location'/>" method="post">
                        <div class="row">
                            <div class="col-12">
                                <div class="form-group form-show-validation row">
                                    <label for="locationName" class="col-lg-3 col-md-3 col-sm-4 mt-sm-2 text-right">Tên
                                        địa chỉ<span class="required-label">*</span></label>
                                    <div class="col-lg-8 col-md-9 col-sm-8">
                                        <input type="text" class="form-control" id="locationName" name="locationName"
                                            placeholder="Nhập địa chỉ..." required pattern=".{1,100}"
                                            oninvalid="this.setCustomValidity('Chiều dài từ 1-100 ký tự')"
                                            oninput="this.setCustomValidity('')">
                                    </div>
                                </div>
                            </div>

                            <div class="col-6">
                                <div class="form-group form-show-validation row">
                                    <label for="longitude" class="col-lg-3 col-md-3 col-sm-4 mt-sm-2 text-right">Kinh
                                        độ<span class="required-label">*</span></label>
                                    <div class="col-lg-8 col-md-9 col-sm-8">
                                        <input type="text" class="form-control" id="longitude" name="longitude" readonly
                                            required>
                                    </div>
                                </div>
                            </div>

                            <div class="col-6">
                                <div class="form-group form-show-validation row">
                                    <label for="latitude" class="col-lg-3 col-md-3 col-sm-4 mt-sm-2 text-right">Vĩ
                                        độ<span class="required-label">*</span></label>
                                    <div class="col-lg-8 col-md-9 col-sm-8">
                                        <input type="text" class="form-control" id="latitude" name="latitude" readonly
                                            required>
                                    </div>
                                </div>
                            </div>
                        </div>
                </div>
                <div class="card-footer btn-center">
                    <button type="submit" class="btn btn-primary">Thêm</button>
                </div>
                </form>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-12">
            <div class="card">
                <div class="card-header">
                    <div class="card-title">Bản đồ</div>
                </div>
                <div class="card-body">
                    <div id="mapStyle" class="text-center mb-3">
                        <button id="streets-v11" type="button" class="btn btn-outline-success m-2 active">Đường
                            phố</button>
                        <button id="satellite-streets-v11" type="button" class="btn btn-outline-success m-2">Vệ
                            tinh</button>
                        <button id="outdoors-v11" type="button" class="btn btn-outline-success m-2">Ngoài trời</button>
                        <button id="light-v10" type="button" class="btn btn-outline-success m-2">Sáng</button>
                        <button id="dark-v10" type="button" class="btn btn-outline-success m-2">Tối</button>
                    </div>
                    <div id="map"></div>
                </div>
            </div>
        </div>
    </div>
</div>