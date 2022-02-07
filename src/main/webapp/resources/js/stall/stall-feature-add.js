
function getLngLat (locationId){
    fetch('/ShoppingPlan/stall/api/lngLatData', {
        method: "post",
        body:JSON.stringify({
            "locationId":locationId.toString(),
        }),
        headers: {
            'Content-Type': 'application/json'
        }
    })
    .then(res=>res.json()).then(datas=>{
        $("#longitude").val(datas["longitude"])
        $("#latitude").val(datas["latitude"])
    })
}

function checkAddStallResult(){
    var param = new URLSearchParams(window.location.search).get('error')
    if (param!=null) {
        param=window.atob(param)
        if (param === "successful")
            swal(
                'Thêm quầy hàng thành công',
                'Chúc mừng bạn đã thêm quầy hàng thành công!',
                'success'
            ).then(() => window.location.href = "/ShoppingPlan/stall")
        else
            swal(
                'Thêm quầy hàng thất bại',
                'Vui lòng kiểm tra lại thông tin!',
                'error'
            )

    }
}

function setCookie(cname, cvalue, exdays) {
    const d = new Date();
    d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
    let expires = "expires="+d.toUTCString();
    document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
}

$(document).ready(function(){
    checkAddStallResult()
    $("#confirm").text('Thêm quầy hàng')
    $("#stallControl").attr("action", "/ShoppingPlan/stall/add")
    setCookie("prePageAddLocation", window.location.href, 365);
    if($("#location").val()!=null)
        getLngLat(window.atob($("#location").val()))
    $("#location").change(function (){
        getLngLat(window.atob($(this).val()))
    })

})