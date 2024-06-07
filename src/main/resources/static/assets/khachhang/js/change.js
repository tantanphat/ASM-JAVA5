$(document).ready(function () {
    $('#changePass').click(function () {
        var password = $('#Password').val();
        var conformPassword = $('#conformPassword').val();
        var newPassword = $('#newPassword').val();

        if (password == "" || conformPassword=="" || newPassword==""){
            alert("Vui lòng nhập đầy đủ thông tin");
            return false;
        }

        if (password !== conformPassword) {
            alert("Mật khẩu không trùng khớp");
            return false;
        }

        var requestData = {
            matKhauCu: password,
            matKhauMoi: newPassword
        };
        $.ajax({
            url: "/api/khach-hang/change-password",
            type: "PUT",
            data: JSON.stringify(requestData),
            contentType: "application/json",

            success: function (response) {
                alert(response);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                if (jqXHR.status === 401) {
                    console.log("Unauthorized");
                } else if (jqXHR.status === 400) {
                    alert(jqXHR.responseText); // Hiển thị lỗi từ server
                } else {
                    console.log("Lỗi");
                    console.log(jqXHR,textStatus,errorThrown); // Log thông tin lỗi khác
                }
            }
        });
    });
});
