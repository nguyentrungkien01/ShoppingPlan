var gOffSet = null
var gLimit = null
var gAmount = null
var gCurrentPage=null
var gStallId=null;
function getProductListInfo(){
    fetch("/ShoppingPlan/stall/stall-detail/api/productListInfo", {
        method: "post",
        body:JSON.stringify({
            "stallId": gStallId.toString(),
            "offSet":gOffSet.toString(),
            "limit":gLimit.toString(),
        }),
        headers: {
            'Content-Type': 'application/json'
         }
    }).then(res=>res.json()).then(datas=>{
      setProductListInfo(datas)
    })
}

// stall amount
function getProductListAmount() {
    fetch("/ShoppingPlan/stall/stall-detail/api/productListAmount", {
        method: "post",
        body: JSON.stringify({
            "stallId": gStallId.toString()
        }),
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(res => res.json()).then(datas => {
        gAmount = parseInt(datas["productListAmount"])
        setPagination(gAmount)
    })
}

//delete product
function deleteProduct(productId) {
    swal({
        title: "Xác nhận xóa",
        text: "Bạn có chắc chắn muốn xóa sản phẩm này? \n Mọi thông tin liên quan đến sản phẩm này sẽ bị xóa theo!",
        icon: "warning",
        dangerMode: true,
    }).then(confirmDelete => {
        if (confirmDelete) {
            fetch("/ShoppingPlan/stall/stall-detail/api/deleteProduct", {
                method: "post",
                body: JSON.stringify({
                    "productId": productId.toString()
                }),
                headers: {
                    'Content-Type': 'application/json'
                }
            }).then(res => res.json()).then(datas => {
                if(datas["result"]){
                    swal(
                        'Xóa sản phẩm thành công',
                        'Chúc mừng bạn đã xóa sản phẩm thành công!',
                        'success'
                    ).then(() => initData())

                }else{
                    swal(
                        'Xóa sản phẩm thất bại',
                        'Đã có lỗi xảy ra trong quá trình xóa, vui lòng thử lại sau!',
                        'fail'
                    )
                }
            })
        }
    })
}

//pagination
function setPagination(amount) {
    var page = ''
    var amountPage = Math.ceil(amount / gLimit)
    if (amount <= gLimit) {
        $('#pagination').html('')
        return
    }
    if (amountPage !== 1) {
        gCurrentPage = 1
        $('#pagination').html('')
        page += `<li class="page-item" id ='previous_item'>
                <button class="page-link" onclick='setPrevious(${amountPage} )'><</button>
            </li>`
        for (let i = 1; i <= amountPage; i++)
            page += `<li class="page-item ${i == 1 ? 'active' : ''}">
                    <button class="page-link" onclick='setPage(${i},${amountPage})'>${i}</button>
                </li>`

        page += `<li class="page-item" id = 'next_item'>
                <button class="page-link" onclick='setNext(${amountPage}, ${amount})'>></button>
            </li>`
        $('#pagination').html(page)
    }
}

function setPrevious(amountPage) {
    if (gOffSet - gLimit >= 0) {
        $('#previous_item').show()
        gCurrentPage--;
        if (gCurrentPage === 1)
            $('#previous_item').hide()

        if (gCurrentPage == 1 && gCurrentPage != amountPage)
            $('#next_item').show()

        gOffSet -= gLimit
        getProductListInfo()
    } else {
        $('#previous_item').hide()
    }
    $('#pagination').children().removeClass('active')
    $(`#pagination li:nth-child(${gCurrentPage + 1})`).addClass('active')
}

function setNext(amountPage, amountData) {
    if (gOffSet + gLimit <= amountData) {
        $('#next_item').show()
        gCurrentPage++;
        if (gCurrentPage === amountPage)
            $('#next_item').hide()
        if (gCurrentPage === amountPage && gCurrentPage != 1)
            $('#previous_item').show()
        gOffSet += gLimit
        getProductListInfo()
    } else {
        $('#next_item').hide()
    }
    $('#pagination').children().removeClass('active')
    $(`#pagination li:nth-child(${gCurrentPage + 1})`).addClass('active')
}

function setPage(itemIdx, amountPage) {
    gCurrentPage = itemIdx
    if (itemIdx === amountPage) {
        $('#next_item').hide()
        if (itemIdx !== 1)
            $('#previous_item').show()
    } else
    if (itemIdx === 1) {
        $('#previous_item').hide()
        if (itemIdx !== amountPage)
            $('#next_item').show()
    } else {
        $('#previous_item').show()
        $('#next_item').show()
    }
    gOffSet = (itemIdx - 1) * gLimit
    getProductListInfo()
    $('#pagination').children().removeClass('active')
    $(`#pagination li:nth-child(${gCurrentPage + 1})`).addClass('active')
}

function setIndexPagination() {
    gOffSet = 0
}

// stall infomation
function setProductListInfo(datas){
     var row = ""
     for (let i =0; i<datas["productDetailList"].length; i++){
        var productImage = datas["productDetailList"][i]['productImage']
        if(productImage == null)
            productImage = "https://res.cloudinary.com/nguyentrungkien/image/upload/v1643466396/product/default_ss6fj9.jpg"
        var productInfos = datas["productDetailList"][i]
         var stallId = window.btoa(gStallId)
        row +=`
            <tr>
                <td>
                    <a href= "/ShoppingPlan/stall/detail/edit/?stallId=${stallId}&productId=${productInfos["productId"]}"><span class="fa fa-pencil"></span></a>
                    <a href= "javascript:;" onclick="deleteProduct('${productInfos["productId"]}')"><span class="fa fa-trash"></span></a>
                </td>
                <td scope="row">${productInfos["productId"]}</th>
                <td>${productInfos["productName"]}</td>
                <td>`
        for(let j=0; j<productInfos["productUnits"].length; j++){
                var productUnits = productInfos["productUnits"][j]
                row+=`
                    <p>${productUnits["unitPrice"]} VNĐ / ${productUnits["unitName"]}  </p>
                `
        }
        row+=`</td>
                <td>${datas["productDetailList"][i]["productCategory"]}</td>
                <td>
                   <img src="${productImage}"/>
                </td>
            </tr>
        `
        }
     $("#productListData").html(row)

}

function initData() {
    gStallId= parseInt(window.atob(new URLSearchParams(window.location.search).get("stallId")))
    gLimit = 1
    setIndexPagination()
    getProductListAmount()
    getProductListInfo()
}

$(document).ready(function(){
    initData()
    const stallId = window.btoa(gStallId);
    $("#add").prop("href", `/ShoppingPlan/stall/detail/add/?stallId=${stallId}`);
})