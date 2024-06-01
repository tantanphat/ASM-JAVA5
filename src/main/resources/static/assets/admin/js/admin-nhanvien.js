    $(document).ready(function() {
        var currentURL = window.location.href;
        var segments = currentURL.split('/');
        var manv = segments[segments.length - 1];
        console.log(manv);

        $.ajax({
            url: "/api/nhan-vien/" + manv,
            type: "GET",
            success: function (data) {
                console.log('Hello', data);
                $('#MANV').val(data.maNV);
                $('#FullName').val(data.tenNV);
                var genderValue = data.gioiTinh ? "1" : "2"; // true là Nam, false là Nữ
                $('#Gender').val(genderValue);
                $('#Address').val(data.diaChi);
                $('#Phone').val(data.dienThoai);
                $('#Brithday').val(data.ngaySinh);
                $('#Password').val(data.matkhau);
                var roleValue = data.vaiTro;
                if (roleValue == false) {
                    $('#RoleNV').prop('checked', true);
                    $('#RoleQL').prop('checked', false);
                } else {
                    $('#RoleNV').prop('checked', false);
                    $('#RoleQL').prop('checked', true);
                }

                var formContainer = document.getElementById('formUpdate');
                formContainer.scrollIntoView({ behavior: 'smooth' });
            }
        });

        $.ajax({
            url: "/api/nhan-vien",
            type: "GET",
            headers: {
                'Authorization': 'Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhcHBWZXIiOiIwLjAuMCIsImV4cCI6NDcyNjM4OTEyMiwibG9jYWxlIjoiIiwibWFzdGVyVmVyIjoiIiwicGxhdGZvcm0iOiIiLCJwbGF0Zm9ybVZlciI6IiIsInVzZXJJZCI6IiJ9.QIZbmB5_9Xlap_gDhjETfMI6EAmR15yBtIQkWFWJkrg',
            },
            success: function(response) {
                var tbody = $('#employeeTable tbody');
                tbody.empty();
                response.forEach(function(item) {
                    var row = $('<tr></tr>');
                    row.append('<td><a href="/admin/nhan-vien/' + item.maNV + '">' + item.maNV + '</a></td>');
                    row.append('<td>' + item.tenNV + '</td>');
                    row.append('<td>' + (item.gioiTinh ? 'Nam':'Nữ') + '</td>');
                    row.append('<td>' + item.diaChi + '</td>');
                    row.append('<td>' + item.dienThoai + '</td>');
                    row.append('<td>' + item.ngaySinh + '</td>');
                    // row.append('<td>' + item.matkhau + '</td>');
                    row.append('<td>' + (item.vaiTro ? 'Admin' : 'Staff') + '</td>');
                    tbody.append(row);
                })
            },
            error: function(xhr, status, error) {
                console.error("Error fetching employee data:", error);
            }
        });
});

    function btnCreat_click() {
        // Lấy thông tin từ form
        var tenNV = $('#FullName').val();
        var gioiTinh = $('#Gender').val() === "1";
        var diaChi = $('#Address').val();
        var dienThoai = $('#Phone').val();
        var ngaySinh = $('#Brithday').val();
        var matkhau = $('#Password').val();
        var vaiTro = $('#RoleNV').prop('checked'); // true nếu là quản lý

        // Tạo đối tượng nhân viên mới (không bao gồm maNV)
        var newNhanVien = {
            tenNV: tenNV,
            gioiTinh: gioiTinh,
            diaChi: diaChi,
            dienThoai: dienThoai,
            ngaySinh: ngaySinh,
            matkhau: matkhau,
            vaiTro: vaiTro
        };

        // Gửi yêu cầu POST để thêm nhân viên mới
        $.ajax({
            url: "/api/nhan-vien/add",
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify(newNhanVien),
            success: function (data) {
                // Cập nhật bảng hiển thị nhân viên
                var tbody = $('#employeeTable tbody');
                var row = $('<tr></tr>');
                row.append('<td><a href="/admin/nhan-vien/' + data.maNV + '">' + data.maNV + '</a></td>');
                row.append('<td>' + data.tenNV + '</td>');
                row.append('<td>' + (data.gioiTinh ? 'Nam' : 'Nữ') + '</td>');
                row.append('<td>' + data.diaChi + '</td>');
                row.append('<td>' + data.dienThoai + '</td>');
                row.append('<td>' + data.ngaySinh + '</td>');
                row.append('<td>' + (data.vaiTro ? 'Admin' : 'Staff') + '</td>');
                tbody.append(row);
                // Reset form sau khi thêm
                $('#formUpdate')[0].reset();
            },
            error: function (xhr, status, error) {
                console.error("Error adding employee:", error);
            }
        });
    }
function btnClear_click() {
    window.location.href = "http://localhost:8080/admin/nhan-vien?action=Clear";
}

    function btnUpdate_click() {
        // Lấy mã nhân viên từ form
        var maNV = $('#MANV').val();
        // Lấy thông tin mới từ form
        var tenNV = $('#FullName').val();
        var gioiTinh = $('#Gender').val() === "1"; // "1" là Nam, "2" là Nữ
        var diaChi = $('#Address').val();
        var dienThoai = $('#Phone').val();
        var ngaySinh = $('#Brithday').val();
        var matkhau = $('#Password').val();
        var vaiTro = $('#RoleNV').prop('checked'); // true nếu là quản lý

        // Tạo đối tượng nhân viên cần cập nhật
        var updatedNhanVien = {
            tenNV: tenNV,
            gioiTinh: gioiTinh,
            diaChi: diaChi,
            dienThoai: dienThoai,
            ngaySinh: ngaySinh,
            matkhau: matkhau,
            vaiTro: vaiTro
        };

        // Gửi yêu cầu PUT để cập nhật thông tin nhân viên
        $.ajax({
            url: "/api/nhan-vien/" + maNV,
            type: "PUT",
            contentType: "application/json",
            data: JSON.stringify(updatedNhanVien),
            success: function (data) {
                // Cập nhật thông tin nhân viên trong bảng hiển thị
                $('#employeeTable tbody').empty(); // Xóa hết dữ liệu cũ trong bảng
                fetchEmployeeData(); // Gọi lại hàm fetchEmployeeData() để lấy dữ liệu mới
                alert("Thông tin nhân viên đã được cập nhật!");
            },
            error: function (xhr, status, error) {
                console.error("Error updating employee:", error);
            }
        });
    }

    function fetchEmployeeData() {
        $.ajax({
            url: "/api/nhan-vien",
            type: "GET",
            success: function (response) {
                var tbody = $('#employeeTable tbody');
                response.forEach(function (item) {
                    var row = $('<tr></tr>');
                    row.append('<td><a href="/admin/nhan-vien/' + item.maNV + '">' + item.maNV + '</a></td>');
                    row.append('<td>' + item.tenNV + '</td>');
                    row.append('<td>' + (item.gioiTinh ? 'Nam' : 'Nữ') + '</td>');
                    row.append('<td>' + item.diaChi + '</td>');
                    row.append('<td>' + item.dienThoai + '</td>');
                    row.append('<td>' + item.ngaySinh + '</td>');
                    row.append('<td>' + (item.vaiTro ? 'Admin' : 'Staff') + '</td>');
                    tbody.append(row);
                });
            },
            error: function (xhr, status, error) {
                console.error("Error fetching employee data:", error);
            }
        });
    }

    function btnDelete_click() {
        // Lấy mã nhân viên từ form
        var maNV = $('#MANV').val();

        // Gửi yêu cầu DELETE để xóa nhân viên
        $.ajax({
            url: "/api/nhan-vien/" + maNV,
            type: "DELETE",
            success: function () {
                $('#employeeTable tbody').empty(); // Xóa hết dữ liệu cũ trong bảng
                fetchEmployeeData(); // Gọi lại hàm fetchEmployeeData() để lấy dữ liệu mới
                alert("Nhân viên đã được xóa thành công!");
                $('#formUpdate')[0].reset();
            },
            error: function (xhr, status, error) {
                console.error("Error deleting employee:", error);
            }
        });
    }

// function validateName(FullName) {
//     let regex = /^[a-zA-Z\s]+$/;
//     return regex.test(FullName);
// }
//
// function validateBirthday(Birthday) {
//     let regex = /^(0[1-9]|1[0-2])\/(0[1-9]|1\d|2\d|3[01])\/(19|20)\d{2}$/;
//     if (!regex.test(Birthday)) {
//         return false;
//     }
//
//     let sinh = new Date(Birthday);
//     let tai = new Date();
//
//     if (sinh > tai) {
//         return false;
//     }
//     return true;
// }
//
// function validatePhone(Phone) {
//     let regex = /^0[0-9]{9,10}$/;
//     return regex.test(Phone);
// }
//
// function verificationForm() {
//     if ($('#Gender').val() == '...') {
//         Swal.fire({
//             icon: "error",
//             title: "Oops...",
//             text: "Vui lòng chọn giới tính !",
//         });
//         $('#Gender').focus();
//         return;
//     }
//
//     if ($('#FullName').val() == '' || !validateName($('#FullName').val())) {
//         Swal.fire({
//             icon: "error",
//             title: "Oops...",
//             text: "Vui lòng nhập họ tên!",
//         });
//         $('#FullName').focus();
//         return;
//     }
//
//     if ($('#Brithday').val() == '' || !validateBirthday($('#Brithday').val())) {
//         Swal.fire({
//             icon: "error",
//             title: "Oops...",
//             text: "Ngày sinh sai định dạng",
//         });
//         return;
//     }
//
//     if ($('#Phone').val() == '' || !validatePhone($('#Phone').val())) {
//         Swal.fire({
//             icon: "error",
//             title: "Oops...",
//             text: "Vui lòng nhập Sdt!",
//         });
//         $('#Phone').focus();
//         return;
//     }
//
//     if ($('#Password').val() == '') {
//         Swal.fire({
//             icon: "error",
//             title: "Oops...",
//             text: "Vui lòng nhập mật khẩu!",
//         });
//         $('#Password').focus();
//         return;
//     }
//
//     if ($('#Password1').val() !== $('#Password').val()) {
//         Swal.fire({
//             icon: "error",
//             title: "Oops...",
//             text: "Vui lòng nhập lại!",
//         });
//         $('#Password1').focus();
//         return;
//     }
//
//     if ($('#Address').val() == '') {
//         Swal.fire({
//             icon: "error",
//             title: "Oops...",
//             text: "Vui lòng nhập địa chỉ!",
//         });
//         $('#Address').focus();
//         return;
//     }
//
//
// }