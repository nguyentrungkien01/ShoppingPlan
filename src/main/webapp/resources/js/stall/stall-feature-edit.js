function setStallData(stallId){
    fetch('/ShoppingPlan/stall/edit/api/data', {
        method: "post",
        body:JSON.stringify({
            "stallId":stallId.toString()
        }),
        headers: {
            'Content-Type': 'application/json'
        }
    }).then(res=>res.json()).then(datas=>{
        $("#name").val(datas["stallName"])
        $("#longitude").val(datas["longitude"])
        $("#latitude").val(datas["latitude"])
        if(datas["stallDescription"]!=null)
            $("#description").val(datas["stallDescription"])
        $(`#location option[value='${datas["locationId"]}']`).attr('selected','selected');

    })
}

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
function checkEditStallResult(){
    var param = new URLSearchParams(window.location.search).get('error')
    if (param!=null) {
        param=window.atob(param)
        if (param === "successful")
            swal(
                'Sửa thông tin quầy hàng thành công',
                'Chúc mừng bạn đã sửa thông quầy hàng thành công!',
                'success'
            ).then(() => window.location.href = "/ShoppingPlan/stall")
        else
            swal(
                'Sửa thông tin quầy hàng thất bại',
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
    var stallId=new URLSearchParams(window.location.search).get("stallId")
    $("#confirm").text('Sửa quầy hàng')
    $("#stallControl").attr("action", `/ShoppingPlan/stall/edit/?stallId=${stallId}`)
    setStallData(window.atob(stallId))
    setCookie("prePageAddLocation", window.location.href, 365);

    $("#location").change(function (){
        getLngLat(window.atob($(this).val()))
    })
    checkEditStallResult()
})