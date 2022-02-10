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
function setHintData(datas){
    if (datas.length > 0)
        gIndexHintProductName = 1
    let row = '';
    for (let i = 0; i < datas.length; i++)
        row += `<p id = "${datas[i]['productId']}" 
            onclick = "onClickHint('${datas[i]['productName']}')"
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

$(document).ready(function(){
    $('#name').change(function(){
       getProductNameHint($(this).val())
    })

    // id card input
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
})