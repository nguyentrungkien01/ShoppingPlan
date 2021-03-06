let gOrderIdUnit = null;

function getUnitType() {
    fetch('/ShoppingPlan/stall/stall-detail/api/unitType')
        .then(res => res.json()).then(datas => {
            setUnitTypePanel(datas)
        })
}

function getUnitData(unitTypeId) {
    fetch('/ShoppingPlan/stall/stall-detail/api/unitData', {
        method: 'post',
        body: JSON.stringify({
            "unitTypeId": unitTypeId.toString()
        }),
        headers: {
            'Content-Type': 'application/json'
        }
    }).then(res => res.json()).then(datas => {
        setActiveUnitType(unitTypeId)
        setUnitData(datas)
        setActiveUnitData()
    })
}

function setActiveUnitType(unitTypeId) {
    for (let i = 1; i <= $("#unitTypePanel").children().length; i++) {
        const button = $(`#unitTypePanel button:nth-child(${i})`)
        button.removeClass("active")
        if (button.attr("id").includes(unitTypeId))
            button.addClass("active")
    }

}

function setActiveUnitData() {
    for (let i = 1; i <= $("#unitData").children().length; i++) {
        const choice = $(`#unitData tr:nth-child(${i}) td:nth-child(3) button`);
        choice.removeClass('active')
        for (let j = 1; j <= $("#unitDataResult").children().length; j++) {
            const rowResult = $(`#unitDataResult tr:nth-child(${j})`)
            if (choice.attr('id').includes(rowResult.attr('id'))) {
                choice.addClass('active')
                break
            }
        }
    }
}


function setUnitTypePanel(datas) {
    let buttons = '';
    for (let i = 0; i < datas.length; i++)
        buttons += `
            <button type="button" class="btn btn-outline-success"  id ="${datas[i]['unitTypeId']}"
                onclick="getUnitData('${datas[i]['unitTypeId']}')">${datas[i]['unitTypeName']}</button>
       `
    $("#unitTypePanel").html(buttons)
    $("#unitTypePanel button:nth-child(1)").addClass('active')
    getUnitData(`${datas[0]['unitTypeId']}`)
}

function setUnitData(datas) {
    $("#title").text(datas['unitTypeName'])
    let unitData = '';
    const units = datas['units'];
    for (let i = 0; i < units.length; i++) {
        unitData += `
            <tr>
                <td>${i+1}</td>
                <td>${units[i]['unitName']}</td>
                <td>
                     <button type="button" class="btn btn-outline-success" id ="${units[i]['unitId']}"
                            onclick="addUnitDataResult('${units[i]['unitId']}',
                                                       '${units[i]['unitName']}')">Ch???n</button>
                </td>
            </tr>
        `
    }
    $('#unitData').html(unitData)
}

function addUnitDataResult(unitId, unitName) {
    if (gOrderIdUnit == null)
        gOrderIdUnit = 0

    for (let i = 1; i <= gOrderIdUnit; i++)
        if ($(`#unitDataResult tr:nth-child(${i})`).attr('id').includes(unitId)) {
            swal(
                'Ch???n th???t b???i',
                '????n v??? n??y ???? ???????c ch???n v??o danh s??ch!',
                'error'
            )
            return
        }

    gOrderIdUnit++
    var unitDataResult = $('#unitDataResult').html()
    if (unitDataResult == null)
        unitDataResult = ''
    unitDataResult += `
        <tr id = "${unitId}">
            <td>${gOrderIdUnit}</td>
            <td>${unitName}</td>
            <td>
                <div class="form-group">
                    <div class="row flex-nowrap align-items-center justify-content-center">
                        <div style="width:200px">
                            <input type="number" class="form-control" value="1000"
                                name="unitPrice${gOrderIdUnit}" required/>
                        </div>
                        <p style="width:30px; margin-bottom:unset;padding-left: 15px;">VN??</p>
                    </div>
                </div>
            </td>
            <td>
                <button type="button" class="btn btn-outline-danger"
                    onclick ="removeChoice('${unitId}')">H???y</button> 
            </td>
        </tr>
    `
    $('#unitDataResult').html(unitDataResult)
    setActiveUnitData()
}

function removeChoice(unitId) {
    for (let i = 1; i <= gOrderIdUnit; i++) {
        const row = $(`#unitDataResult tr:nth-child(${i})`)
        if (row.attr("id").includes(unitId)) {
            row.remove()
            break
        }
    }
    setActiveUnitData()
    gOrderIdUnit--
    for (let i = 1; i <= gOrderIdUnit; i++)
        $(`#unitDataResult tr:nth-child(${i}) td:nth-child(1)`)
        .text(i.toString())


}

function checkAddProductStallResult(stallId) {
    var param = new URLSearchParams(window.location.search).get('error')
    if (param != null) {
        param = window.atob(param)
        if (param === "successful")
            swal(
                'Th??m s???n ph???m v??o qu???y h??ng th??nh c??ng',
                'Ch??c m???ng b???n ???? th??m s???n ph???m v??o qu???y h??ng th??nh c??ng!',
                'success'
            ).then(() => window.location.href = `/ShoppingPlan/stall/detail/?stallId=${stallId}`)
        else if (param === "noUnit")
            swal(
                'Th??m s???n ph???m th???t b???i ',
                'Vui l??ng th??m ????n v??? v?? ????n gi?? cho s???n ph???m!',
                'error'
            )
        else
            swal(
                'Th??m s???n ph???m v??o qu???y h??ng th???t b???i',
                'Vui l??ng ki???m tra l???i th??ng tin!',
                'error'
            )

    }
}


$(document).ready(function () {
    $("#confirm").text('Th??m s???n ph???m')
    const stallId = new URLSearchParams(window.location.search).get("stallId");
    $("#stallDetailControl").attr("action", `/ShoppingPlan/stall/detail/add/?stallId=${stallId}`)
    checkAddProductStallResult(stallId)
    getUnitType()

    $("#stallDetailControl").submit(function () {
        let value = '';
        for (let i = 1; i <= gOrderIdUnit; i++) {
            var unitId = $(`#unitDataResult tr:nth-child(${i})`).attr('id')
            var unitPrice = $(`#unitDataResult tr:nth-child(${i}) td:nth-child(3) input`).val()
            value += `${unitId.trim()},${unitPrice.trim()};`
        }

        if (value.length > 0) {
            value = value.substring(0, value.length - 1)
            var input = $("<input />", {
                name: "units",
                value: value,
                type: "hidden"
            })
            $(this).append(input)
        }
    })
})