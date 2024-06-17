$(document).ready(function() {
    // // Lấy URL hiện tại
    // var currentURL = window.location.href;
    // // Tách URL để lấy mã khách hàng (makh)
    // var segments = currentURL.split('/');
    // var makh = segments[segments.length - 1];

    function getMAKH(callback) {
        var currentURL = window.location.href;
        var url = new URL(currentURL);
        var maKH = url.searchParams.get("maKH");
        callback(maKH); // Gọi callback và truyền giá trị manv vào
    }

    function getKHByMAKH() {
        getMAKH(function(maKH) {
            if (maKH) {
                $.ajax({
                    url: "/api/khach-hang/" + maKH,
                    type: "GET",
                    success: function(data) {
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

                    },
                    error: function(xhr, status, error) {
                        if (xhr.status == 404) {
                            alert("Không tìm thấy khách hàng có mã " + maKH);
                            window.location.href = "/admin/khach-hang";
                        }
                    }
                });
            }
        });
    }
    getKHByMAKH()


    function clear() {
        $('#MAKH').val("");
        $('#FullNameKH').val("");
        $('#AddressKH').val("");
        $('#PhoneKH').val("");
        $('#Email').val("");
    }

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
                row.append('<td class="khClick">' + item.maKH + '</td>'); // Mã khách hàng
                row.append('<td>' + item.tenKH + '</td>'); // Tên khách hàng
                row.append('<td>' + item.diaChi + '</td>'); // Địa chỉ
                row.append('<td>' + item.sdt + '</td>'); // Số điện thoại
                row.append('<td>' + item.email + '</td>'); // Email
                row.append('<td>' + item.thanhVien + '</td>'); // Thành viên
                tbody.append(row); // Thêm hàng vào bảng
            });
        }
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

                    // tbody.empty(); // Xóa nội dung cũ của bảng
                    // var row = $('<tr></tr>');
                    // row.append('<td><a href="/admin/khach-hang/' + data.maKH + '">' + data.maKH + '</td>'); // Mã khách hàng
                    // row.append('<td>' + data.tenKH + '</td>'); // Tên khách hàng
                    // row.append('<td>' + data.diaChi + '</td>'); // Địa chỉ
                    // row.append('<td>' + data.sdt + '</td>'); // Số điện thoại
                    // row.append('<td>' + data.email + '</td>'); // Email
                    // // row.append('<td>' + item.matKhau + '</td>'); // Mật khẩu
                    // row.append('<td>' + data.thanhVien + '</td>'); // Thành viên
                    // tbody.append(row); // Thêm hàng vào bảng

                    var formContainer = document.getElementById('htmlKH');
                    formContainer.scrollIntoView({behavior: 'smooth'});
                }
            });
        }
    });

        fetchEmployeeDataKH();


    $('#btnClearKH_click').click(function () {
        clear()

        window.history.pushState({}, '', "/admin/khach-hang");
        $('#formUpdate').get(0).scrollIntoView({ behavior: 'smooth' });
    })

    function fetchEmployeeDataKH() {
        $.ajax({
            url: "/api/khach-hang",
            type: "GET",
            success: function(response) {
                // Xử lý thành công, đổ dữ liệu vào bảng
                var tbody = $('#khach-hang-table tbody');
                tbody.empty(); // Xóa nội dung cũ của bảng
                response.forEach(function(item) {
                    var row = $('<tr></tr>');
                    row.append('<td class="khClick">' + item.maKH + '</td>'); // Mã khách hàng
                    row.append('<td>' + item.tenKH + '</td>'); // Tên khách hàng
                    row.append('<td>' + item.diaChi + '</td>'); // Địa chỉ
                    row.append('<td>' + item.sdt + '</td>'); // Số điện thoại
                    row.append('<td>' + item.email + '</td>'); // Email
                    row.append('<td>' + item.thanhVien + '</td>'); // Thành viên
                    tbody.append(row); // Thêm hàng vào bảng
                });

                // Gán sự kiện click cho các phần tử có class "khClick" trong tbody
                $('#khach-hang-table tbody').on('click', '.khClick', function(e) {
                    var maKH = $(this).text();
                    var newUrl = new URL(window.location);
                    newUrl.searchParams.set('maKH', maKH);
                    window.history.pushState({}, '', newUrl);
                    $('#formUpdate').get(0).scrollIntoView({ behavior: 'smooth' });
                    getKHByMAKH()
                });
            },
            error: function(xhr, status, error) {
                // Xử lý lỗi khi lấy dữ liệu
                console.error("Lỗi khi lấy dữ liệu khách hàng:", error);
            }
        });

    }

    function btnCreatKH_click() {
        // Lấy thông tin từ form
        var tenKH = $('#FullNameKH').val().trim();
        var diaChi = $('#AddressKH').val().trim();
        var sdt = $('#PhoneKH').val().trim();
        var email = $('#Email').val().trim();
        var thanhVien = $('input[name="role_radio"]:checked').val();// true nếu là thành viên

// Biến để theo dõi trạng thái hợp lệ của form
        var isValid = true;

        var namePattern = /^([a-vxyỳọáầảấờễàạằệếýộậốũứĩõúữịỗìềểẩớặòùồợãụủíỹắẫựỉỏừỷởóéửỵẳẹèẽổẵẻỡơôưăêâđA-VXYỲỌÁẦẢẤỜỄÀẠẰỆẾÝỘẬỐŨỨĨÕÚỮỊỖÌỀỂẨỚẶÒÙỒỢÃỤỦÍỸẮẪỰỈỎỪỶỞÓÉỬỴẲẸÈẼỔẴẺỠƠÔƯĂÊÂĐ]+)(\s{1}[a-vxyỳọáầảấờễàạằệếýộậốũứĩõúữịỗìềểẩớặòùồợãụủíỹắẫựỉỏừỷởóéửỵẳẹèẽổẵẻỡơôưăêâđA-VXYỲỌÁẦẢẤỜỄÀẠẰỆẾÝỘẬỐŨỨĨÕÚỮỊỖÌỀỂẨỚẶÒÙỒỢÃỤỦÍỸẮẪỰỈỎỪỶỞÓÉỬỴẲẸÈẼỔẴẺỠƠÔƯĂÊÂĐ]+)*$/;

// Reset lỗi
        $('.error').remove();

// Kiểm tra từng trường và thêm thông báo lỗi
        if (tenKH === "") {
            isValid = false;
            $('#FullNameKH').after('<span class="error">Họ tên không được để trống</span>');
        } else if (!namePattern.test(tenKH)) {
            isValid = false;
            $('#FullNameKH').after('<span class="error">Họ tên chỉ được chứa chữ cái và khoảng trắng</span>');
        }

        if (diaChi === "") {
            isValid = false;
            $('#AddressKH').after('<span class="error">Địa chỉ không được để trống</span>');
        }

        var phonePattern = /^[0-9]{10,11}$/;
        if (!phonePattern.test(sdt)) {
            isValid = false;
            $('#PhoneKH').after('<span class="error">Số điện thoại không hợp lệ</span>');
        }

        var emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/;
        if (email === "") {
            isValid = false;
            $('#Email').after('<span class="error">Email không được để trống</span>');
        } else if (!emailPattern.test(email)) {
            isValid = false;
            $('#Email').after('<span class="error">Email không hợp lệ</span>');
        }

        if (isValid) {
            // Tạo đối tượng khách hàng mới (không bao gồm maKH)
            var newKhachHang = {
                tenKH: tenKH,
                diaChi: diaChi,
                sdt: sdt,
                email: email,
                thanhVien: thanhVien
            };

            $.ajax({
                type: 'GET',
                url: '/api/khach-hang/check-khach-hang',
                data: { mail: email },
                contentType: 'application/x-www-form-urlencoded',
                success: function(response) {
                    if (response == true ) {
                        Swal.fire({
                            icon: "error",
                            title: "Error",
                            text: "Email đã tồn tại"
                        });
                    } else {

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
                },
                error: function(jqXHR, exception, exceptionText) {

                }
            });

        }
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
                $('#formUpdate').get(0).scrollIntoView({ behavior: 'smooth' });
            },
            error: function (xhr, status, error) {
                console.error("Error updating employee:", error);
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
                // $('#formUpdate')[0].reset();
                $('#formUpdate').get(0).scrollIntoView({ behavior: 'smooth' });
            },
            error: function (xhr, status, error) {
                console.error("Error deleting employee:", error);
            }
        });
    }

    $('#btnUpdateKH_click').click(function () {
        btnUpdateKH_click()
    })

    $('#btnDeleteKH_click').click(function () {
        btnDeleteKH_click()
    })

    $('#btnCreatKH_click').click(function () {
        btnCreatKH_click()
    })

    $('#XuatListKH').click(function(e) {
        e.preventDefault()
        $.ajax({
            url: '/api/khach-hang/xuat-ra-excel',
            type: 'GET',
            success: function(response) {
                console.log(response);
                alert('Xuất ra file thành công');
            }
        }).fail(function(xhr) {
            if (xhr.status === 404) {
                alert('Xuất ra file thất bại');
            } else {
                alert('Đã có một ngoại lệ xảy ra');
            }
        });

    })


});


