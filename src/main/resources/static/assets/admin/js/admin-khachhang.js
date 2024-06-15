$(document).ready(function() {
    // Lấy URL hiện tại
    var currentURL = window.location.href;
    // Tách URL để lấy mã khách hàng (makh)
    var segments = currentURL.split('/');
    var makh = segments[segments.length - 1];
    function clear() {
        $('#MAKH').val("");
        $('#FullNameKH').val("");
        $('#AddressKH').val("");
        $('#PhoneKH').val("");
        $('#Email').val("");
    }

    // Gửi yêu cầu GET để lấy thông tin khách hàng
    $.ajax({
        url: "/api/khach-hang/" + makh,
        type: "GET",
        success: function (data) {
            // Đổ dữ liệu lên form
            $('#MAKH').val(data.maKH);
            $('#FullNameKH').val(data.tenKH);
            $('#AddressKH').val(data.diaChi);
            $('#PhoneKH').val(data.sdt);
            $('#Email').val(data.email);
            var roleValue = data.thanhVien;
            if (roleValue == false) {
                $('#Rolefalse').prop('checked', true);
                $('#Roletrue').prop('checked', false);
            } else {
                $('#Rolefalse').prop('checked', false);
                $('#Roletrue').prop('checked', true);
            }

            // Cuộn trang đến form
            var formContainer = document.getElementById('formUpdate');
            formContainer.scrollIntoView({ behavior: 'smooth' });
        }
    });

    $('#pagination-container-kh ').pagination({
        dataSource: function(done){
            $.ajax({
                url: "/api/khach-hang",
                type: "GET",
                success: function(response) {
                    done(response)
                }
            });
        },
        pageSize: 5,
        autoHidePrevious: true,
        autoHideNext: true,
        prevText: '&laquo; Trước',
        nextText: 'Sau &raquo;',
        ellipseText: '...',
        className: 'paginationjs-theme-blue paginationjs-small',
        callback: function(data, pagination) {

            var tbody = $('#khach-hang-table tbody');
            tbody.empty(); // Xóa nội dung cũ của bảng
            data.forEach(function(item) {
                var row = $('<tr></tr>');
                row.append('<td><a href="/admin/khach-hang/' + item.maKH + '">' + item.maKH+ '</td>'); // Mã khách hàng
                row.append('<td>' + item.tenKH + '</td>'); // Tên khách hàng
                row.append('<td>' + item.diaChi + '</td>'); // Địa chỉ
                row.append('<td>' + item.sdt + '</td>'); // Số điện thoại
                row.append('<td>' + item.email + '</td>'); // Email
                // row.append('<td>' + item.matKhau + '</td>'); // Mật khẩu
                row.append('<td>' + item.thanhVien + '</td>'); // Thành viên
                tbody.append(row); // Thêm hàng vào bảng
            });
        }
    })

    $('#XuatListKH').click(function(e) {
        $.ajax({
            url:'/api/khach-hang/xuat-ra-excel',
            type: 'GET',
        }).done(function() {
            alert('Xuất ra file thành công')
        })
    })

    $("#searchKH").on('input', function(e) {
        var key = $(this).val();
        var tbody = $('#khach-hang-table tbody');

        if (key === '') {
            // Clear the table and fetch the initial data
            tbody.empty();
            fetchEmployeeDataKH();
        } else {
            $.ajax({
                url: "/api/khach-hang/tim-kiem",
                type: "GET",
                data: {key: key},
                success: function (data) {
                    console.log(data.tenKH)
                    $('#MAKH').val(data.maKH);
                    $('#FullNameKH').val(data.tenKH);
                    $('#AddressKH').val(data.diaChi);
                    $('#PhoneKH').val(data.sdt);
                    $('#Email').val(data.email);

                    tbody.empty(); // Xóa nội dung cũ của bảng
                    var row = $('<tr></tr>');
                    row.append('<td><a href="/admin/khach-hang/' + data.maKH + '">' + data.maKH + '</td>'); // Mã khách hàng
                    row.append('<td>' + data.tenKH + '</td>'); // Tên khách hàng
                    row.append('<td>' + data.diaChi + '</td>'); // Địa chỉ
                    row.append('<td>' + data.sdt + '</td>'); // Số điện thoại
                    row.append('<td>' + data.email + '</td>'); // Email
                    // row.append('<td>' + item.matKhau + '</td>'); // Mật khẩu
                    row.append('<td>' + data.thanhVien + '</td>'); // Thành viên
                    tbody.append(row); // Thêm hàng vào bảng

                    var formContainer = document.getElementById('htmlKH');
                    formContainer.scrollIntoView({behavior: 'smooth'});
                }
            });
        }
    });
    $(document).ready(function() {
        fetchEmployeeDataKH();
    });

    $('#btnClearKH_click').click(function () {
        clear()
        window.location.href = "http://localhost:8080/admin/khach-hang"
    })
});

function btnCreatKH_click() {
    if (!validateInput()) {
        return;
    }
    // Lấy thông tin từ form
    var tenKH = $('#FullNameKH').val();
    var diaChi = $('#AddressKH').val();
    var sdt = $('#PhoneKH').val();
    var email = $('#Email').val();
    var thanhVien = $('#Roletrue').prop('checked'); // true nếu là thành viên

    // Tạo đối tượng khách hàng mới (không bao gồm maKH)
    var newKhachHang = {
        tenKH: tenKH,
        diaChi: diaChi,
        sdt: sdt,
        email: email,
        thanhVien: thanhVien
    };

    // Gửi yêu cầu POST để thêm khách hàng mới
    $.ajax({
        url: "/api/khach-hang/add",
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify(newKhachHang),
        success: function (data) {
            // Cập nhật bảng hiển thị khách hàng
            var tbody = $('#khach-hang-table tbody');
            var row = $('<tr></tr>');
            row.append('<td><a href="/admin/khach-hang/' + data.maKH + '">' + data.maKH + '</td>'); // Mã khách hàng
            row.append('<td>' + data.tenKH + '</td>'); // Tên khách hàng
            row.append('<td>' + data.diaChi + '</td>'); // Địa chỉ
            row.append('<td>' + data.sdt + '</td>'); // Số điện thoại
            row.append('<td>' + data.email + '</td>'); // Email
            row.append('<td>' + (data.thanhVien ? 'true' : 'false') + '</td>'); // Thành viên
            tbody.append(row);
            // Reset form sau khi thêm
            $('#formUpdate')[0].reset();
        },
        error: function (xhr, status, error) {
            console.error("Error adding customer:", error);
        }
    });
}



function btnUpdateKH_click() {
    // Lấy mã khách hàng từ form
    var maKH = $('#MAKH').val();
    // Lấy thông tin mới từ form
    var tenKH = $('#FullNameKH').val();
    var diaChi = $('#AddressKH').val();
    var sdt = $('#PhoneKH').val();
    var email = $('#Email').val();
    var thanhVien = $('#Roletrue').prop('checked'); // true nếu là thành viên

    // Tạo đối tượng khách hàng cần cập nhật
    var updatedKhachHang = {
        tenKH: tenKH,
        diaChi: diaChi,
        sdt: sdt,
        email: email,
        thanhVien: thanhVien
    };

    // Gửi yêu cầu PUT để cập nhật thông tin khách hàng
    $.ajax({
        url: "/api/khach-hang/" + maKH,
        type: "PUT",
        contentType: "application/json",
        data: JSON.stringify(updatedKhachHang),
        success: function (data) {
            // Cập nhật thông tin khách hàng trong bảng hiển thị
            $('#khach-hang-table tbody').empty(); // Xóa hết dữ liệu cũ trong bảng
            fetchEmployeeDataKH(); // Gọi lại hàm fetchEmployeeData() để lấy dữ liệu mới
            alert("Thông tin nhân viên đã được cập nhật!");
        },
        error: function (xhr, status, error) {
            console.error("Error updating employee:", error);
        }
    });
}

function fetchEmployeeDataKH() {
    $.ajax({
        url: "/api/khach-hang",
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
                // row.append('<td>' + item.matKhau + '</td>'); // Mật khẩu
                row.append('<td>' + item.thanhVien + '</td>'); // Thành viên
                tbody.append(row); // Thêm hàng vào bảng
            });
        },
        error: function(xhr, status, error) {
            // Xử lý lỗi khi lấy dữ liệu
            console.error("Lỗi khi lấy dữ liệu khách hàng:", error);
        }
    });
}

function btnDeleteKH_click() {
    // Lấy mã nhân viên từ form
    var maKH = $('#MAKH').val();

    // Gửi yêu cầu DELETE để xóa nhân viên
    $.ajax({
        url: "/api/khach-hang/" + maKH,
        type: "DELETE",
        success: function () {
            $('#employeeTable tbody').empty(); // Xóa hết dữ liệu cũ trong bảng
            fetchEmployeeDataKH(); // Gọi lại hàm fetchEmployeeData() để lấy dữ liệu mới
            alert("Khách hàng đã được xóa thành công!");
            $('#formUpdate')[0].reset();
        },
        error: function (xhr, status, error) {
            console.error("Error deleting employee:", error);
        }
    });
}

function validateInput() {
    var isValid = true;
    var emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    var phonePattern = /^[0-9]{10,11}$/;
    var namePattern = /^[a-zA-Z\s]+$/;

    // Check required fields
    if ($('#FullNameKH').val().trim() === '') {
        isValid = false;
        alert("Tên khách hàng không được để trống");
    } else if (!namePattern.test($('#FullNameKH').val().trim())) {
        isValid = false;
        alert("Tên khách hàng chỉ được chứa chữ cái và khoảng trắng");
    }

    if ($('#AddressKH').val().trim() === '') {
        isValid = false;
        alert("Địa chỉ không được để trống");
    }

    if ($('#PhoneKH').val().trim() === '') {
        isValid = false;
        alert("Số điện thoại không được để trống");
    } else if (!phonePattern.test($('#PhoneKH').val())) {
        isValid = false;
        alert("Số điện thoại không hợp lệ");
    }

    if ($('#Email').val().trim() === '') {
        isValid = false;
        alert("Email không được để trống");
    } else if (!emailPattern.test($('#Email').val())) {
        isValid = false;
        alert("Email không hợp lệ");
    }

    return isValid;
}
