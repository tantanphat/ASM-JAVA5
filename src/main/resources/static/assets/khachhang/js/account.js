$(document).ready(function () {
    $.ajax({
        url: "/api/khach-hang/user",
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
        event.preventDefault(); // Không cho reload trang
        var tenKH = $('#tenKH').val();
        var diaChi = $('#diaChi').val();
        var email = $('#email').val();
        var sdt = $('#sdt').val();

        var data = {
            email: email,
            hoTen: tenKH,
            diaChi: diaChi,
            soDienThoai: sdt
        };
        $.ajax({
            url: '/api/khach-hang/updateKhachHang',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function(response) {
                console.log('Thành công');
                Swal.fire({
                    icon: "success",
                    title: "Cập nhật thành công",
                    showConfirmButton: false,
                    timer: 1500
                });

                // Load lại trang sau một khoảng thời gian
                setTimeout(function() {
                    location.reload();
                }, 2000); // 2000 milliseconds = 2 seconds
            },

            error: function(jqXHR, textStatus, errorThrown) {
                console.log('Thất bại');
                Swal.fire({
                    icon: "error",
                    title: "Lỗi",
                    text: "Không thể cập nhật thông tin. Vui lòng thử lại."
                });
            }
        });
    });
});