$(document).ready(function(){
   if ( window.location.pathname === "/admin/report/nhan-vien") {
       $("#linkNV").addClass("active");
   } else {
       $("#linkNV").removeClass("active");
   }

    if ( window.location.pathname === "/admin/report/doanh-thu") {
        $("#linkDT").addClass("active");
    } else {
        $("#linkDT").removeClass("active");
    }

    if ( window.location.pathname === "/admin/report/san-pham") {
        $("#linkSP").addClass("active");
    } else {
        $("#linkSP").removeClass("active");
    }

    $('.roleAdminNot').click(function () {
        alert("Chỉ có admin mới được quyền vào mục này")
    })

    $('#btnExportMain').click(function () {
        var year = $("#dashBoardDTByNam").val();
        $.ajax({
            url: '/api/thong-ke/excel-thong-ke?year=' + year,
            type: 'GET',
            success: function(response) {
                alert("Xuất ra thành công");
            },
            error: function(xhr, status, error) {
                alert("Failed to export doanh thu data: " + error);
            }
        });
    });


});



