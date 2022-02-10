
$(document).ready(function() {
    if (localStorage.getItem("sideBarActive") === null)
        localStorage.setItem("sideBarActive", "1")

    $('#sidebarContent li').click(function () {
        $('#sidebarContent').find('.active').removeClass('active')
        localStorage.setItem("sideBarActive", $(this).attr("id"))
    })

    $(`#sidebarContent li:nth-child(${localStorage.getItem("sideBarActive")})`).addClass('active')

})