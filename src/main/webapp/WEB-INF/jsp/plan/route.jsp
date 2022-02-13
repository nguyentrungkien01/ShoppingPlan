<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="page-inner">
    <h4 class="page-title">Tìm đường</h4>
    <div class="row">
        <div class="col-12 col-md-6">
            <div class="card">
                <div class="card-header">
                    <div class="card-title">Kiểu bản đồ</div>
                </div>
                <div class="card-body">
                    <div class="row">
                        <div class="col-12" id="mapStyle">
                            <button id="streets-v11" type="button" class="btn btn-outline-success m-2 active">Đường
                                phố</button>
                            <button id="satellite-streets-v11" type="button" class="btn btn-outline-success m-2">Vệ
                                tinh</button>
                            <button id="outdoors-v11" type="button" class="btn btn-outline-success m-2">Ngoài
                                trời</button>
                            <button id="light-v10" type="button" class="btn btn-outline-success m-2">Sáng</button>
                            <button id="dark-v10" type="button" class="btn btn-outline-success m-2">Tối</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-12 col-md-6">
            <div class="card">
                <div class="card-header">
                    <div class="card-title">Kiểu phương tiện</div>
                </div>
                <div class="card-body">
                    <div class="row">
                        <div class="col-12" id="controls">
                            <button id="driving-traffic" type="button" class="btn btn-outline-success m-2 active">Cơ
                                giới</button>
                            <button id="driving" type="button" class="btn btn-outline-success m-2">Ô tô</button>
                            <button id="walking" type="button" class="btn btn-outline-success m-2">Xe đạp</button>
                            <button id="cycling" type="button" class="btn btn-outline-success m-2">Đi bộ</button>
                        </div>
                    </div>
                </div>
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
                    <div id="map"></div>
                    <div id="instructions"></div>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-12">
            <div class="card">
                <div class="card-header">
                    <div class="card-title">Danh sách các sản phẩm cần mua</div>
                </div>
                <div class="card-body">
                    <div class="table-responsive">
                        <table class="table table-striped table-bordered table-hover model-list">
                            <thead class="bg-thead">
                                <tr>
                                    <th scope="">STT</th>
                                    <th scope="col">Mã sản phẩm</th>
                                    <th scope="col">Tên sản phẩm</th>
                                    <th scope="col">Loại sản phẩm</th>
                                    <th scope="col">Tên quầy hàng</th>
                                    <th scope="col">Vị trí</th>
                                </tr>
                            </thead>

                            <tbody>
                                <c:forEach var="product" items="${products}">
                                    <tr>
                                        <td>${product.get('orderId')}</td>
                                        <td>${product.get('productId')}</td>
                                        <td>${product.get('productName')}</td>
                                        <td>${product.get('categoryName')}</td>
                                        <td>${product.get('stallName')}</td>
                                        <td>${product.get('locationName')}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>