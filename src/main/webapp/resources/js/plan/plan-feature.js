let gIndexHintProductName = null
let gOrderChoice = 0
let gOffSet = null;
let gLimit = null;
let gCurrentPage = null;

function getProductNameHint(data) {
    if (data.length > 0)
        fetch('/ShoppingPlan/plan/api/productNameHint', {
            method: 'post',
            body: JSON.stringify({
                'keyword': data.toString().trim()
            }),
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(res => res.json()).then(datas => setHintData(datas))

}

function getSearchResult() {
    fetch('/ShoppingPlan/plan/api/searchResult', {
        method: 'post',
        body: JSON.stringify({
            'keyword': $('#name').val().trim(),
            'limit': gLimit.toString(),
            'offSet': gOffSet.toString()
        }),
        headers: {
            'Content-Type': 'application/json'
        }
    }).then(res => res.json()).then(datas => {
        if (datas.length === 0) {
            swal(
                'Tìm không thành công',
                'Không tìm thấy sản phẩm phù hợp với yêu cầu!',
                'error'
            )
            return
        }
        setSearchResult(datas)
        setActiveDataResult()

    })
}

function getProductAmount() {
    fetch('/ShoppingPlan/plan/api/amountSearchResult', {
        method: 'post',
        body: JSON.stringify({
            'keyword': $('#name').val().trim()
        }),
        headers: {
            'Content-Type': 'application/json'
        }
    }).then(res => res.json()).then(datas => {
        setPagination(datas['amount'])
    })
}

function getReport(formId) {
    fetch('/ShoppingPlan/plan/api/reportInfo').then(res => res.json()).then(datas => {
        var formData = ''
        for (let i = 0; i < datas.length; i++)
            formData += `
            <div class="row align-items-center ml-3 mb-1">
            <label class="colorinput">
                <input name="color" type="checkbox" id="${datas[i]['id']}" value="${datas[i]['id']}"
                    class="colorinput-input">
                <span class="colorinput-color bg-danger"></span>
            </label>
            <label for="${datas[i]['id']}" class="ml-2">${datas[i]['name']}</label>
        </div>
            `
        formData += " <input class='btn btn-warning' type='submit' value='Xác nhận'>"
        $(`#${formId}`).html(formData)

    })
}

function addReport(reportIds) {
    fetch('/ShoppingPlan/plan/api/addReport', {
        method: 'post',
        body: JSON.stringify({
            'reportIds': reportIds
        }),
        headers: {
            'Content-Type': 'application/json'
        }
    }).then(res => res.json()).then(datas => {
        if (datas)
            swal(
                'Báo cáo thành công',
                'Chúc mừng bạn gửi báo cáo thành công!',
                'success'
            )
        else
            swal(
                'Báo cáo thất bại',
                'Đã xảy ra sự cố trong quá trình gửi báo cáo! Vui lòng thử lại sau',
                'error'
            )
    })
}

function setActiveDataResult() {
    for (let i = 3; i <= $("#dataResult").children().length; i += 2) {
        const choice = $(`#dataResult > div:nth-child(${i}) > div:nth-child(2) > button:nth-child(2)`)
        choice.removeClass('active')
        for (let j = 1; j <= $("#dataChoice").children().length; j++) {
            const rowResult = $(`#dataChoice > div:nth-child(${j})`)
            if ($(`#dataResult > div:nth-child(${i}`).attr('id').includes(rowResult.attr('id'))) {
                choice.addClass('active')
                break
            }
        }

    }
}

//hint
function setHintData(datas) {
    if (datas.length > 0)
        gIndexHintProductName = 1
    let row = '';
    for (let i = 0; i < datas.length; i++)
        row += `<p onclick = "onClickHint('${datas[i]['productName']}')"
            onmouseover="onMouseOverHint(${i + 1})">${datas[i]['productName']}</p>`
    $('#hint').html(row)
}

function onClickHint(hint) {
    $('#name').val(hint.trim())
    $('#hint').html('')
}

function onMouseOverHint(position) {
    gIndexHintProductName = position
    for (let i = 1; i <= $('#hint').children().length; i++)
        $(`.show-hint p:nth-child(${i})`).css("background-color", "white");
    $(`.show-hint p:nth-child(${position})`).css("background-color", "#04a9f5");
}

// search result
function setSearchResult(datas) {
    var searchResult = `
    <div class="card-title">Kết quả tìm kiếm</div>
        <div class="separator-solid"></div>`
    for (let i = 0; i < datas.length; i++) {
        var product = datas[i]['product']
        var category = datas[i]['category']
        var stall = datas[i]['stall']
        var location = datas[i]['location']
        var user = datas[i]['user']
        var units = datas[i]['units']
        var productImage = product['productImage']
        if (productImage == null || productImage.length <= 0)
            productImage = "https://res.cloudinary.com/nguyentrungkien/image/upload/v1643466396/product/default_ss6fj9.jpg"

        searchResult += `
            <div class="row align-items-center" id="${product['productId']}">
                <div class="col-12 col-md-8">
                    <div class="row align-items-center">
                        <div class="col">
                         <div class="card-title"> <h1>${product['productName']}</h1></div>
                          <p class="mb-1">Loại hàng: ${category['categoryName']}</p>
                          <p class="mb-1">Địa chỉ: ${location['locationName']}</p>
                        </div>
                        <div class="col">
                            <img src="${productImage}" style="width: 100px; height: 100px; border-radius: 10px;">
                        </div>
                    </div>
                  <div id="product_${product['productId']}" class="product-detail"></div>
                </div>
                <div class="col-12 col-md-4">  
                  <button type="button" class="btn btn-outline-success m-2"
                            onclick="showDetail('product_${product['productId']}', '${encodeURIComponent(JSON.stringify(datas[i]))}')" id="showDetail">
                            Xem chi tiết</button>
                  <button type="button" class="btn btn-outline-success m-2" 
                            onclick="addChoice(
                                '${encodeURIComponent(JSON.stringify(product))}', 
                                '${encodeURIComponent(JSON.stringify(units))}',
                                '${location['locationName']}',
                                '${user['userLastName']} ${user['userFirstName']}')">Chọn</button>
                </div>
            </div>
            <div class="separator-solid"></div>
        `
    }
    $('#dataResult').html(searchResult)
    setActiveDataResult()
}

function addChoice(product, units, locationName, owner) {
    product = JSON.parse(decodeURIComponent(product))
    units = JSON.parse(decodeURIComponent(units))

    for (let i = 1; i <= gOrderChoice; i++) {
        if ($(`#dataChoice > div:nth-child(${i})`).attr('id').includes(product['productId'])) {
            swal(
                'Chọn thất bại',
                'Sản phẩm này đã được chọn!',
                'error'
            )
            return
        }
    }
    gOrderChoice++
    let dataChoice = $('#dataChoice').html();

    if (dataChoice == null)
        dataChoice = ''
    let productImage = product['productImage'];
    if (productImage == null || productImage.length <= 0)
        productImage = "https://res.cloudinary.com/nguyentrungkien/image/upload/v1643466396/product/default_ss6fj9.jpg"

    //product units html
    let productUnits = '<ul class="list-group">';
    for (let i = 0; i < units.length; i++)
        productUnits += `
             <li class="list-group-item d-flex justify-content-between align-items-center">
                ${units[i]['unitPrice']} VNĐ
                <span class="badge bg-primary rounded-pill">${units[i]['unitName']}</span>
              </li>
        `
    productUnits += '</ul>'

    //data choice
    dataChoice += `
        <div class="card mb-3" id="${product['productId']}">
              <div class="row align-items-center">
                    <div class="col-md-4 text-center">
                        <img src="${productImage}" class="img-fluid rounded-start" style="border-radius:10px;">
                    </div>
                    <div class="col-md-8">
                          <div class="card-body">
                                <div class="d-flex">
                                    <h5 class="card-title"><span class="badge rounded-pill bg-info text-dark" >${gOrderChoice}</span> <span>${product['productName']}</span></h5>
                                    <button type="button" class="btn btn-outline-danger btn-sm ml-3" 
                                            onclick="removeChoice('${product['productId']}')">Hủy</button>
                                </div>
                                <p class="card-text">Địa chỉ: ${locationName}</p>
                                <div class="card-text">
                                    ${productUnits}
                                </div>
                                <p class="card-text text-right"><small class="text-muted">Chủ sở hữu: <span>${owner}</span></small></p>
                          </div>
                    </div>
              </div>
        </div>
    `
    $('#dataChoice').html(dataChoice)
    setActiveDataResult()
    $('#amountChoice').text(gOrderChoice)
    toggleSearchRouteBtn()
}

function showDetail(detailId, productDetail) {
    var text = document.getElementById('showDetail');
    if (text.innerText.toLowerCase() === 'xem chi tiết')
        text.innerText = 'Đóng xem chi tiết';
    else
        text.innerText = 'Xem chi tiết';
    productDetail = JSON.parse(decodeURIComponent(productDetail))
    var stall = productDetail['stall']
    var location = productDetail['location']
    var user = productDetail['user']
    var units = productDetail['units']
    let stallImage = stall['stallImage'];
    if (stallImage == null || stallImage.length <= 0)
        stallImage = "https://res.cloudinary.com/nguyentrungkien/image/upload/v1643466243/stall/default_upj3uc.jpg"
    var stallDescription = stall['stallDescription']
    if (stallDescription == null)
        stallDescription = "Chưa cập nhật"
    var userFacebookLink = user['userFacebookLink']
    if (userFacebookLink == null)
        userFacebookLink = "javascript:;"

    var phoneNumberList = user['phoneNumbers']
    let phoneNumbers = '<span class="font-weight-normal">';
    for (let i = 0; i < phoneNumberList.length; i++)
        phoneNumbers += ` ${phoneNumberList[i]['name']};`
    phoneNumbers += '</span>'

    let productUnits = '<ul class="list-group">';
    for (let i = 0; i < units.length; i++)
        productUnits += `
             <li class="list-group-item d-flex justify-content-between align-items-center">
                ${units[i]['unitPrice']} VNĐ
                <span class="badge bg-success rounded-pill">${units[i]['unitName']}</span>
              </li>
        `
    productUnits += '</ul>'

    var detail = document.getElementById(detailId)
    var button = null
    for (let i = 3; i <= $('#dataResult').children().length; i += 2)
        if ($(`#dataResult > div:nth-child(${i})`).attr('id').includes(detailId.substring("product_".length))) {
            button = $(`#dataResult > div:nth-child(${i}) > div:nth-child(2) > button:nth-child(1)`)
            break
        }

    var newDetail = ''
    if (detail.innerHTML == null || detail.innerHTML.length <= 0) {
        button.addClass('active')
        newDetail = `
                <h1 class="card-title text-center m-4 pt-2">Chi tiết sản phẩm</h1>
                <h1 class="card-title font-weight-bold">Chủ quầy hàng</h1>
                <p><span class="font-weight-bold">Tên:</span> ${user['userLastName']} ${user['userFirstName']} 
                    <button type="button" class="btn btn-outline-danger ml-2 btn-sm" onclick="showForm('form_${detailId.substring(0, detailId.length-2)}')">Báo cáo</button> 
                    <form id="form_${detailId.substring(0, detailId.length-2)}" hidden="true">
                    </form>
                </p>
            
                <a href="${userFacebookLink}">Địa chỉ facebook</a>
                <div class="separator-solid"></div>
                <p class="mt-3"><span class="card-title font-weight-bold">Số điện thoại:</span> ${phoneNumbers}</p>
                <div class="separator-solid"></div>
                <h1 class="card-title mt-3 font-weight-bold">Quầy hàng: ${stall['stallName']}</h1>
                <div class="row align-items-center">
                    <div class="col">
                        <p><span class="font-weight-bold">Mô tả:</span> ${stallDescription}</p>
                    </div>
                    <div class="col">
                        <img src="${stallImage}" style="width: 100px; height: 100px"/>
                    </div>
                </div>
                <div class="separator-solid"></div>
                <h1 class="card-title mt-4 font-weight-bold">Vị trí: ${location['locationName']}</h1>
                <div class="row">
                    <div class="col">
                        <p><span class="font-weight-bold">Kinh độ:</span> ${location['locationLongitude']}</p>
                    </div>
                    <div class="col">
                        <p><span class="font-weight-bold">Vĩ độ:</span> ${location['locationLatitude']}</p>
                    </div>
                </div>
                <div class="separator-solid"></div>
                <h3 class="card-title mt-4 mb-3 font-weight-bold">Đơn giá:</h3>
                ${productUnits}
        `
    } else {
        button.removeClass('active')
    }
    detail.innerHTML = newDetail
    getReport(`form_${detailId.substring(0, detailId.length-2)}`)
}

function removeChoice(productId) {

    for (let i = 1; i <= gOrderChoice; i++) {
        const row = $(`#dataChoice > div:nth-child(${i})`)
        if (row.attr("id").includes(productId)) {
            row.remove()
            break
        }
    }
    setActiveDataResult()
    gOrderChoice--
    for (let i = 1; i <= gOrderChoice; i++)
        $(`#dataChoice > div:nth-child(${i}) h5 span:nth-child(1)`)
        .text(i.toString())
    $('#amountChoice').text(gOrderChoice)
    toggleSearchRouteBtn()
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
        getSearchResult()
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
        getSearchResult()
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
    getSearchResult()
    $('#pagination').children().removeClass('active')
    $(`#pagination li:nth-child(${gCurrentPage + 1})`).addClass('active')
}

function initData() {
    gLimit = 4
    gOffSet = 0
    getProductAmount()
    getSearchResult()
}

function toggleSearchRouteBtn() {
    if (gOrderChoice <= 0)
        $('#searchRoute').hide()
    else
        $('#searchRoute').show()
}

function showForm(id) {
    swal({
        title: "Xác nhận báo cáo",
        text: "Bạn có chắc chắn muốn báo cáo chủ cửa hàng này không?!",
        icon: "warning",
        dangerMode: true,
    }).then(confirmDelete => {
        if (confirmDelete) {
            for (let i = 0; i < $(`#${id} input`).length - 1; i++)
                $(`#${id} input`)[i].checked = false

            const formReport = document.getElementById(id);
            if (formReport.hidden) {
                formReport.hidden = false
                $(document).on('submit', `#${id}`, function (event) {
                    var reportResult = ''
                    for (let i = 0; i < $(`#${id} input`).length - 1; i++) {
                        let inputObj = $(`#${id} input`)[i];
                        if (inputObj.checked)
                            reportResult += `${inputObj.value},`
                    }
                    if (reportResult.length > 0) {
                        reportResult = reportResult.substring(0, reportResult.length - 1)
                        addReport(reportResult)
                    }
                    formReport.hidden = true
                    event.preventDefault()
                })
            } else
                formReport.hidden = true
        }
    })
}

$(document).ready(function () {

    toggleSearchRouteBtn()

    $('#amountChoice').text(gOrderChoice)
    // hint
    $('#name').change(function () {
        getProductNameHint($(this).val())
    })

    $('#hint').hide()

    $('#name').on('input', function () {
        $('#hint').show()
        getProductNameHint($(this).val())
    })
    $('#name').focus(function () {
        $('#hint').show()
        if (gIndexHintProductName != null)
            $(`.show-hint p:nth-child(1)`).css("background-color", "#04a9f5");

    })
    $('#name').keydown(function (event) {
        if (gIndexHintProductName == null)
            return;
        if (event.keyCode === 13) {
            event.preventDefault()
            $('#name').val($(`.show-hint p:nth-child(${gIndexHintProductName})`).text().trim())
            $('#hint').html('')
        }

        if (event.keyCode === 40) {
            for (let i = 1; i <= $('#hint').children().length; i++)
                $(`.show-hint p:nth-child(${i})`).css("background-color", "white");
            if (++gIndexHintProductName > $('#hint').children().length)
                gIndexHintProductName = 1
            $(`.show-hint p:nth-child(${gIndexHintProductName})`).css("background-color", "#04a9f5");
        }
        if (event.keyCode === 38) {
            for (let i = 1; i <= $('#hint').children().length; i++)
                $(`.show-hint p:nth-child(${i})`).css("background-color", "white");
            if (--gIndexHintProductName <= 0)
                gIndexHintProductName = $('#hint').children().length
            $(`.show-hint p:nth-child(${gIndexHintProductName})`).css("background-color", "#04a9f5");
        }
    })

    //search
    $('#search').click(function () {
        $('#hint').hide()
        if ($('#name').val().length > 0)
            initData()
    })

    //reset
    $('#reset').click(function () {
        location.reload();
    })

    //find direction
    $("#planControl").submit(function () {
        let value = '';
        for (let i = 1; i <= gOrderChoice; i++) {
            var productId = $(`#dataChoice > div:nth-child(${i})`).attr('id')
            value += `${productId.trim()},`
        }
        if (value.length > 0) {
            value = value.substring(0, value.length - 1)
            var input = $("<input />", {
                name: "products",
                value: value,
                type: "hidden"
            })
            $(this).append(input)
        }
    })
})