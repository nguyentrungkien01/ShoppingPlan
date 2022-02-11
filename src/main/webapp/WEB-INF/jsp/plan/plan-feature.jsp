<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="row">
    <div class= "col">
        <div class="row">
            <div class="col">
                <form>
                   <div class="form-group">
                       <label for="name">Tên sản phẩm cần mua <span>(*)</span></label>
                       <input type="text" class="form-control" id="name" placeholder="Tên sản phẩm">
                       <div class="show-hint" id="hint"></div>
                   </div>
                </form>
            </div>
            <div class="col">
              <div class="form-group">
                   <button type="button" class="btn btn-primary" id = "search">Tìm kiêm</button>
                   <button type="button" class="btn btn-danger" id="reset">Làm mới</button>
              </div>
            </div>
        </div>

        <div id="dataResult"></div>
        <ul class="pagination" id="pagination"></ul>
    </div>

    <div class="col">
        <h1>Danh sách sản phẩm đã chọn <span  class="badge bg-danger text-dark" id="amountChoice" > </span></h1>
       <div id="dataChoice"></div>
       <form method="post" action="<c:url value='/plan/route' />" id="planControl">
             <div class="form-group">
                   <button type="submit" class="btn btn-primary" id = "searchRoute">Tìm đường</button>
              </div>
       </form>
    </div>
</div>