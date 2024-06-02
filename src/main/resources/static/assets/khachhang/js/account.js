$(document).ready(function () {
    $.ajax({
        url: "/api/user",
        type: "GET",
        beforeSend: function(xhr) {
            // Thêm thông tin xác thực vào header
            xhr.setRequestHeader("Authorization", "Basic " + btoa("username:password"));
        },
        success: function (data) {
            console.log(data);
            $('#tenKH').val(data.hoTen);
            $('#diaChi').val(data.diaChi);
            $('#email').val(data.email);
            $('#sdt').val(data.soDienThoai);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            if (jqXHR.status === 401) {
                console.log("Unauthorized");
            } else {
                console.log(jqXHR); // Log thông tin lỗi khác
            }
        }
    });

    $("#updateInfoKH").click(function () {

    });
});