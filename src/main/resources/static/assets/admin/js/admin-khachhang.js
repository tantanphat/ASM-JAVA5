$(document).ready(function() {
    var currentURL = window.location.href;
    var segments = currentURL.split('/');
    var makh = segments[segments.length - 1];
    console.log(makh); // Logs "NV001"
    $.ajax({
        url: "/api/khach-hang/"+makh,
        type: "GET",
        success: function (data) {
            console.log('Hello', data);
            $('#MANV').val(data.maKH);
            $('#FullName').val(data.tenKH);
            $('#Address').val(data.diaChi);
            $('#Phone').val(data.sdt);
            $('#Brithday').val(data.email);
            $('#Password').val(data.matKhau);
            var roleValue = data.thanhVien;
            if (roleValue == true) {
                $('#Roletrue').prop('checked', true);
                $('#Rolefasle').prop('checked', false);
            } else {
                $('#Roletrue').prop('checked', false);
                $('#Rolefasle').prop('checked', true);
            }

            var formContainer = document.getElementById('formUpdate');
            formContainer.scrollIntoView({ behavior: 'smooth' });
        }
    });

    $.ajax({
        url: "/api/list-khach-hang",
        type: "GET",
        headers: {
            'Authorization': 'Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhcHBWZXIiOiIwLjAuMCIsImV4cCI6NDcyNjM4OTEyMiwibG9jYWxlIjoiIiwibWFzdGVyVmVyIjoiIiwicGxhdGZvcm0iOiIiLCJwbGF0Zm9ybVZlciI6IiIsInVzZXJJZCI6IiJ9.QIZbmB5_9Xlap_gDhjETfMI6EAmR15yBtIQkWFWJkrg',
        },
        success: function(response) {
            // Xử lý thành công, đổ dữ liệu vào bảng
            var tbody = $('#khach-hang-table tbody');
            tbody.empty(); // Xóa nội dung cũ của bảng
            response.forEach(function(item) {
                var row = $('<tr></tr>');
                row.append('<td><a href="/admin/khach-hang/' + item.maKH + '">' + item.maKH+ '</td>'); // Mã khách hàng
                row.append('<td>' + item.tenKH + '</td>'); // Tên khách hàng
                row.append('<td>' + item.diaChi + '</td>'); // Địa chỉ
                row.append('<td>' + item.sdt + '</td>'); // Số điện thoại
                row.append('<td>' + item.email + '</td>'); // Email
                row.append('<td>' + item.matKhau + '</td>'); // Mật khẩu
                row.append('<td>' + item.thanhVien + '</td>'); // Thành viên
                tbody.append(row); // Thêm hàng vào bảng
            });
        },
        error: function(xhr, status, error) {
            // Xử lý lỗi khi lấy dữ liệu
            console.error("Lỗi khi lấy dữ liệu khách hàng:", error);
        }
    });
});