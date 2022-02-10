let gIndexHintProductName = null;

function getProductNameHint(data) {
    if (data.length > 0)
        fetch('/ShoppingPlan/plan/api/productNameHint', {
            method: 'post',
            body: JSON.stringify({
                'keyword': data.toString()
            }),
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(res => res.json()).then(datas => setHintData(datas))

}

function getSearchResult(data,  offset, limit) {
    fetch('/ShoppingPlan/plan/api/searchResult', {
        method: 'post',
        body: JSON.stringify({
            'keyword': data.toString(),
            'limit': limit.toString(),
            'offSet': offset.toString()
        }),
        headers: {
            'Content-Type': 'application/json'
        }
    }).then(res => res.json()).then(datas => {
        console.info(datas)
        setSearchResult(datas)
    })
}

//hint
function setHintData(datas){
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
function setSearchResult(datas){
    var searchResult= ''
    for(let i=0; i<datas.length; i++){
        var product = datas[i]['product']
        var category= datas[i]['category']
        var stall = datas[i]['stall']
        var location = datas[i]['location']
        var user = datas[i]['user']
        var units = datas[i]['units']
        var productImage = product['productImage']
        if(productImage==null || productImage.length<=0)
            productImage= "https://res.cloudinary.com/nguyentrungkien/image/upload/v1643466396/product/default_ss6fj9.jpg"

        searchResult+=`
            <div class="row">
                <div class="col">
                    <div class="row">
                        <div class="col">
                          <h1>${product['productName']}</h1>
                          <p>Loại hàng: ${category['categoryName']}</p>
                          <p>Địa chỉ: ${location['locationName']}</p>
                        </div>
                        <div class="col">
                            <img src="${productImage}" style="width: 100px; height: 100px">
                        </div>
                    </div>
                  <div id="product_${product['productId']}"></div>
                </div>
                <div class="col">
                    <button type="button" class="btn btn-outline-success"
                            onclick="showDetail('product_${product['productId']}', ${datas[i]})">
                            Xem chi tiết</button>
                    <button type="button" class="btn btn-outline-success" 
                            onclick="addChoice(${product})">Chọn</button>

                </div>
            </div>
        `
    }
    $('#dataResult').html(searchResult)
}
function addChoice(product){
    alert("choice")
}

function showDetail(detailId, product){
    alert("detail")
}

$(document).ready(function() {
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
            getSearchResult($('#name').val(), 0, 10)
    })

    //reset
    $('#reset').click(function () {
        location.reload();
    })
})