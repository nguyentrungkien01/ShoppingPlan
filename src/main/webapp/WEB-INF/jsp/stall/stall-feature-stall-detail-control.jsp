<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="page-inner">
    <h4 class="page-title">Thông tin sản phẩm</h4>
    <div class="row">
        <div class="col-12">
            <form action="" method="post" enctype="multipart/form-data" id="stallDetailControl">
                <div class="card">
                    <div class="card-header">
                        <div class="card-title">Sản phẩm</div>
                    </div>
                    <div class="card-body">
                        <div class="row">
                            <div class="col-12">
                                <div class="form-group form-show-validation row">
                                    <label for="name" class="col-lg-3 col-md-3 col-sm-4 mt-sm-2 text-right">Tên sản phẩm
                                        <span class="required-label">*</span></label>
                                    <div class="col-lg-8 col-md-9 col-sm-8">
                                        <input type="text" class="form-control" id="name" name="name"
                                            placeholder="Nhập tên sản phẩm..." required pattern=".{1,30}"
                                            oninvalid="this.setCustomValidity('Chiều dài từ 1-30 ký tự')"
                                            oninput="this.setCustomValidity('')">
                                    </div>
                                </div>

                                <div class="form-group form-show-validation row">
                                    <label for="category" class="col-lg-3 col-md-3 col-sm-4 mt-sm-2 text-right">Loại sản
                                        phẩm
                                        <span class="required-label">*</span></label>
                                    <div class="col-lg-6 col-md-6 col-sm-8">
                                        <select class="form-control" id="category" name="category" required>
                                            <c:forEach var="cat" items="${categories}">
                                                <option value="${cat.get('categoryId')}">${cat.get('categoryName')}
                                                </option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>

                                <div class="form-group form-show-validation row">
                                    <label for="image" class="col-lg-3 col-md-3 col-sm-4 mt-sm-2 text-right">Ảnh đại
                                        diện</label>
                                    <div class="col-lg-8 col-md-9 col-sm-8">
                                        <input type="file" class="form-control" id="image" name="image">
                                    </div>
                                </div>
                            </div>

                            <div class="col-12 col-lg-6">
                                <div class="card-title">Bảng đơn vị</div>
                                <div class="separator-solid"></div>
                                <div class="table-responsive">
                                    <div id="unitTypePanel" class="card-category"></div>
                                    <div class="card-category">
                                        <p id="title"></p>
                                    </div>
                                    <table class="table table-striped table-bordered table-hover model-list">
                                        <thead class="bg-thead">
                                            <tr>
                                                <th scope="">STT</th>
                                                <th scope="col">Tên đơn vị</th>
                                                <th scope="col">Thao tác</th>
                                            </tr>
                                        </thead>
                                        <tbody id="unitData">
                                        </tbody>
                                    </table>
                                </div>
                            </div>

                            <div class="col-12 col-lg-6">
                                <div class="card-title">Danh sách đơn vị đã chọn</div>
                                <div class="separator-solid"></div>
                                <div class="table-responsive">
                                    <table class="table table-striped table-bordered table-hover model-list">
                                        <thead class="bg-thead">
                                            <tr>
                                                <th scope="">STT</th>
                                                <th scope="col">Tên đơn vị</th>
                                                <th scope="col">Đơn giá</th>
                                                <th scope="col">Thao tác</th>
                                            </tr>
                                        </thead>
                                        <tbody id="unitDataResult">
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="card-footer btn-center">
                        <button type="submit" class="btn btn-primary" id="confirm"></button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>