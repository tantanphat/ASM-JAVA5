function btnCreat_click() {
    verificationForm();
}

function validateName(FullName) {
    let regex = /^[a-zA-Z\s]+$/;
    return regex.test(FullName);
}

function validateBirthday(Birthday) {
    let regex = /^(0[1-9]|1[0-2])\/(0[1-9]|1\d|2\d|3[01])\/(19|20)\d{2}$/;
    if (!regex.test(Birthday)) {
        return false;
    }

    let sinh = new Date(Birthday);
    let tai = new Date();

    if (sinh > tai) {
        return false;
    }
    return true;
}

function validatePhone(Phone) {
    let regex = /^0[0-9]{9,10}$/;
    return regex.test(Phone);
}

function verificationForm() {
    if ($('#Gender').val() == '...') {
        Swal.fire({
            icon: "error",
            title: "Oops...",
            text: "Vui lòng chọn giới tính !",
        });
        $('#Gender').focus();
        return;
    }

    if ($('#FullName').val() == '' || !validateName($('#FullName').val())) {
        Swal.fire({
            icon: "error",
            title: "Oops...",
            text: "Vui lòng nhập họ tên!",
        });
        $('#FullName').focus();
        return;
    }

    if ($('#Brithday').val() == '' || !validateBirthday($('#Brithday').val())) {
        Swal.fire({
            icon: "error",
            title: "Oops...",
            text: "Ngày sinh sai định dạng",
        });
        return;
    }

    if ($('#Phone').val() == '' || !validatePhone($('#Phone').val())) {
        Swal.fire({
            icon: "error",
            title: "Oops...",
            text: "Vui lòng nhập Sdt!",
        });
        $('#Phone').focus();
        return;
    }

    if ($('#Password').val() == '') {
        Swal.fire({
            icon: "error",
            title: "Oops...",
            text: "Vui lòng nhập mật khẩu!",
        });
        $('#Password').focus();
        return;
    }

    if ($('#Password1').val() !== $('#Password').val()) {
        Swal.fire({
            icon: "error",
            title: "Oops...",
            text: "Vui lòng nhập lại!",
        });
        $('#Password1').focus();
        return;
    }

    if ($('#Address').val() == '') {
        Swal.fire({
            icon: "error",
            title: "Oops...",
            text: "Vui lòng nhập địa chỉ!",
        });
        $('#Address').focus();
        return;
    }


}


function btnClear_click() {
    var data = {
        Gender: '...',
        FullName: '',
        Brithday: '',
        Phone: '',
        Password: '',
        Password1: '',
        Address: ''
    };
    btnFillForm(data);
}

$(document).ready(function() {
    var currentURL = window.location.href;
    var segments = currentURL.split('/');
    var manv = segments[segments.length - 1];
    console.log(manv); // Logs "NV001"
    $.ajax({
        url: "/api/nhan-vien/"+manv,
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
                row.append('<td>' + item.phone + '</td>');
                row.append('<td>' + item.ngaySinh + '</td>');
                row.append('<td>' + item.matkhau + '</td>');
                row.append('<td>' + (item.vaiTro ? 'Admin' : 'Staff') + '</td>');
                tbody.append(row);
            })
        },
        error: function(xhr, status, error) {
            // Xử lý lỗi
            console.error("Error fetching employee data:", error);
        }
    });
});