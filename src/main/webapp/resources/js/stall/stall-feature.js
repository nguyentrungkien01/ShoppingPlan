var gOffSet = null
var gLimit = null
var gAmount = null
var gCurrentPage=null
function getStallInfo(){
    fetch("/ShoppingPlan/stall/api/stallInfo", {
        method: "post",
        body:JSON.stringify({
            "offSet":gOffSet.toString(),
            "limit":gLimit.toString(),
        }),
        headers: {
            'Content-Type': 'application/json'
         }
    }).then(res=>res.json()).then(datas=>{
        setStallInfo(datas)
    })
}

// stall amount
function getStallAmount(){
     fetch("/ShoppingPlan/stall/api/stallAmount")
     .then(res=>res.json()).then(datas => {
        $("#stallAmount").text(datas["stallAmount"])
        gAmount= parseInt(datas["stallAmount"])
        setPagination(gAmount)
     })
}

function deleteStall(stallId) {
    swal({
        title: "Xác nhận xóa",
        text: "Bạn có chắc chắn muốn xóa quầy hàng này? \n Mọi thông tin liên quan quầy hàng này sẽ bị xóa theo!",
        icon: "warning",
        dangerMode: true,
    }).then(confirmDelete => {
        if (confirmDelete) {
            fetch("/ShoppingPlan/stall/api/deleteStall", {
                method: "post",
                body: JSON.stringify({
                    "stallId": stallId.toString(),
                }),
                headers: {
                    'Content-Type': 'application/json'
                }
            }).then(res => res.json()).then(datas => {
                if (datas["result"]) {
                    swal(
                        'Xóa quầy hàng thành công',
                        'Chúc mừng bạn đã xóa quầy hàng thành công!',
                        'success'
                    ).then(() => {
                        initData()
                    })

                } else {
                    swal(
                        'Xóa quầy hàng thất bại',
                        'Đã có lỗi xảy ra trong quá trình xóa, vui lòng thử lại sau!',
                        'fail'
                    )
                }

            })
        }
    });

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
            page += `<li class="page-item ${i === 1 ? 'active' : ''}">
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

        if (gCurrentPage === 1 && gCurrentPage != amountPage)
            $('#next_item').show()

        gOffSet -= gLimit
        getStallInfo()
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
        if (gCurrentPage == amountPage)
            $('#next_item').hide()
        if (gCurrentPage == amountPage && gCurrentPage != 1)
            $('#previous_item').show()

        gOffSet += gLimit
        getStallInfo()
    } else {
        $('#next_item').hide()
    }
    $('#pagination').children().removeClass('active')
    $(`#pagination li:nth-child(${gCurrentPage + 1})`).addClass('active')
}

function setPage(itemIdx, amountPage) {
    gCurrentPage = itemIdx
    if (itemIdx == amountPage) {
        $('#next_item').hide()
        if (itemIdx != 1)
            $('#previous_item').show()
    } else
    if (itemIdx == 1) {
        $('#previous_item').hide()
        if (itemIdx != amountPage)
            $('#next_item').show()
    } else {
        $('#previous_item').show()
        $('#next_item').show()
    }
    gOffSet = (itemIdx - 1) * gLimit
    getStallInfo()
    $('#pagination').children().removeClass('active')
    $(`#pagination li:nth-child(${gCurrentPage + 1})`).addClass('active')
}

function setIndexPagination() {
    gOffSet = 0
}

// stall infomation
function setStallInfo(datas){
     var row = ""
     for (let i =0; i<datas.length; i++){
        var description = datas[i]["stallDescription"]
        var image = datas[i]["stallImage"]
        if(description==null)
            description="Chưa cập nhật thông tin"
        if(image==null)
            image ="https://res.cloudinary.com/nguyentrungkien/image/upload/v1644934569/stall/default_arwh0s.jpg"
        var stallId = window.btoa(datas[i]['stallId'].toString())
         row +=`
                <div class="col-lg-4 col-md-6 col-sm-12">
                <div class="card">
                    <div class="card-header">
                        <img class="card-img card-img-bdras" src=${image} alt="Card image">
                        <div class="card-title">
                            <h4 class="mt-3 text-success">${datas[i]["stallName"]} <span
                                    class="badge badge-danger">${datas[i]["stallProductListAmount"]}</span></h4>
                        </div>
                    </div>
                    <div class="card-body">${description}</div>
                    <div class="card-footer">
                        <a href="/ShoppingPlan/stall/detail/?stallId=${stallId}" class="btn btn-primary mb-2">Xem chi tiết</a>
                        <a href="/ShoppingPlan/stall/edit/?stallId=${stallId}" class="btn btn-warning mb-2">Sửa</a>
                        <a href="javascript:;" class="btn btn-danger mb-2" onclick="deleteStall('${stallId}')">Xóa</a>
                    </div>
                </div>
            </div>
        `
        }
     $("#stallInfo").html(row)

}

function initData() {
    gLimit = 9
    setIndexPagination()
    getStallAmount()
    getStallInfo()
}

$(document).ready(function(){
    initData()
})