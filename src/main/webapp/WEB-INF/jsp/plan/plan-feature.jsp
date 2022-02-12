<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="page-inner">
    <h4 class="page-title">Kế hoạch</h4>
    <div class="row">
        <div class="col-12">
            <div class="card">
                <div class="card-header">
                    <div class="card-title">Sản phẩm</div>
                </div>
                <div class="card-body">
                    <div class="row">
                        <div class="col-12">
                            <div class="form-group form-show-validation row">
                                <label for="name" class="col-lg-3 col-md-3 col-sm-4 mt-sm-3 text-right">Tên sản phẩm
                                    cần mua
                                    <span class="required-label">*</span></label>
                                <div class="col-lg-5 col-md-9 col-sm-8 mt-2">
                                    <input type="text" class="form-control" id="name"
                                        placeholder="Nhập tên sản phẩm...">
                                    <div class="show-hint show-hint-product" id="hint"></div>
                                </div>

                                <div class="col-lg-4 col-md-12 col-sm-12 row mt-2">
                                    <button type="button" class="btn btn-primary  ml-3 mb-2" id="search">Tìm
                                        kiêm</button>
                                    <button type="button" class="btn btn-danger ml-3 mb-2" id="reset">Làm mới</button>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="col-12">

                        <div id="dataResult"></div>
                        <ul class="pagination" id="pagination"></ul>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-12">
            <div class="card">
                <div class="card-header">
                    <div class="card-title">Danh sách sản phẩm đã chọn <span class="badge bg-danger text-dark"
                            id="amountChoice"> </span></div>
                </div>
                <div class="card-body">
                    <div class="row">
                        <div class="col-12">
                            <div id="dataChoice"></div>
                            <form method="post" action="<c:url value='/plan/route' />" id="planControl">
                                <div class="form-group btn-center">
                                    <button type="submit" class="btn btn-primary " id="searchRoute">Tìm đường</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>