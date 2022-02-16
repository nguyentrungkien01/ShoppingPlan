<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="content">
    <div class="panel-header bg-primary-gradient">
        <div class="page-inner py-5">
            <div>
                <h2 class="text-white pb-2 fw-bold">Quầy hàng</h2>
                <h5 class="text-white op-7 mb-2">Kế hoạch mua sắm</h5>
            </div>
        </div>
    </div>
    <div class="page-inner mt--1">
        <div class="row justify-content-center">
            <div class="col-12">
                <div class="card">
                    <div class="card-header">
                        <div class="card-head-row">
                            <div class="card-title">Các quầy hàng cá nhân (Số lượng: <span id="stallAmount"  class="text-success"></span>)
                            </div>
                            <div class="card-tools">
                                <a href="<c:url value = '/stall/add' />" class="btn btn-xs btn-secondary btn-sm"
                                    id="add">Thêm</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row" id="stallInfo"></div>
        <ul class="pagination" id="pagination"></ul>
    </div>
</div>