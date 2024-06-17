$(document).ready(function() {
        function getMaNV(callback) {
            var currentURL = window.location.href;
            var url = new URL(currentURL);
            var manv = url.searchParams.get("maNV");
            callback(manv);
        }

        function getNVByMANV() {
            getMaNV(function(manv) {
                if (manv) {
                    $.ajax({
                        url: "/api/nhan-vien/" + manv,
                        type: "GET",
                        success: function(data) {
                            $('#MANV').val(data.maNV);
                            $('#FullName').val(data.tenNV);
                            var genderValue = data.gioiTinh ? "1" : "2"; // true là Nam, false là Nữ
                            $('#Gender').val(genderValue);
                            $('#Address').val(data.diaChi);
                            $('#Phone').val(data.dienThoai);
                            $('#Brithday').val(data.ngaySinh);

                            // Xử lý checkbox vai trò (Role)
                            if (data.vaiTro == false) {
                                $('#RoleNV').prop('checked', true);
                                $('#RoleQL').prop('checked', false);
                            } else {
                                $('#RoleNV').prop('checked', false);
                                $('#RoleQL').prop('checked', true);
                            }

                            // Kiểm tra trường hợp nhân viên không còn hoạt động (isActive = false)
                            if (data.isActive == false) {
                                alert("Nhân viên không còn hoạt động!");
                                window.location.href = "/admin/nhan-vien"; // Chuyển hướng về trang danh sách nhân viên
                            }
                        },
                        error: function(xhr, status, error) {
                            if (xhr.status == 404) {
                                alert("Không tìm thấy nhân viên có mã " + manv);
                                window.location.href = "/admin/nhan-vien";
                            }
                        }
                    });
                }
            });
        }

        getNVByMANV();


        function fetchEmployeeData() {
            $.ajax({
                url: "/api/nhan-vien/isActive",
                type: "GET",
                success: function (response) {
                    var tbody = $('#employeeTable tbody');
                    tbody.empty(); // Clear the table body before adding new rows
                    response.forEach(function (item) {
                        var row = $('<tr></tr>');
                        row.append('<td class="nvClick">' + item.maNV + '</td>');
                        row.append('<td>' + item.tenNV + '</td>');
                        row.append('<td>' + (item.gioiTinh ? 'Nam' : 'Nữ') + '</td>');
                        row.append('<td>' + item.diaChi + '</td>');
                        row.append('<td>' + item.dienThoai + '</td>');
                        row.append('<td>' + item.ngaySinh + '</td>');
                        row.append('<td>' + (item.vaiTro ? 'Admin' : 'Staff') + '</td>');
                        tbody.append(row);
                    });
                    $('.nvClick').on('click', function(e) {
                        var maNV = $(this).text();
                        var newUrl = new URL(window.location);
                        newUrl.searchParams.set('maNV', maNV);
                        window.history.pushState({}, '', newUrl);
                        $('#nhanVienAdmin').get(0).scrollIntoView({ behavior: 'smooth' });
                        getNVByMANV();
                    });
                },
                error: function (xhr, status, error) {
                    console.error("Error fetching employee data:", error);
                }
            });
        }

        fetchEmployeeData();
        function clearNhanVien() {
           $('#MANV').val("");
            $('#FullName').val("");
              $('#Gender').val() === "1";
            $('#Address').val("");
            $('#Phone').val("");
          $('#Brithday').val("");
          $('#Password').val("");
        }

    $('#btnCreate').click(function(e) {
        // Lấy thông tin từ form
        var tenNV = $('#FullName').val();
        var gioiTinh = $('#Gender').val() === "1";
        var diaChi = $('#Address').val();
        var dienThoai = $('#Phone').val();
        var ngaySinh = $('#Brithday').val();
        var matkhau = $('#Password').val();
        var vaiTro = $('#RoleQL').prop('checked'); // true nếu là quản lý

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
                $('#employeeTable tbody').empty();
                fetchEmployeeData();
                alert("Thêm mới thành viên thành công")
                // Reset form sau khi thêm
                $('#formUpdate')[0].reset();
            },
            error: function (xhr, status, error) {
                console.error("Error adding employee:", error);
            }
        });
    })

        $('#btnClear').click(function(e) {
            clearNhanVien()
            window.history.pushState({}, '', "/admin/nhan-vien");
            $('#formUpdate').get(0).scrollIntoView({ behavior: 'smooth' });
        })
        $('#btnUpdate').click(function(e) {
            // Lấy mã nhân viên từ form
            var maNV = $('#MANV').val();
            var tenNV = $('#FullName').val();
            var gioiTinh = $('#Gender').val() === "1"; // "1" là Nam, "2" là Nữ
            var diaChi = $('#Address').val();
            var dienThoai = $('#Phone').val();
            var ngaySinh = $('#Brithday').val();
            var matkhau = $('#Password').val();
            var vaiTro = $('#RoleQL').prop('checked'); // true nếu là quản lý

            if ( maNV =="" ||tenNV =="" || diaChi==""|| dienThoai==""|| ngaySinh=="") {
                alert("Vui lòng điền đầy đủ thông tin!");
                return;
            }

            // Tạo đối tượng nhân viên cần cập nhật
            var updatedNhanVien = {
                tenNV: tenNV,
                gioiTinh: gioiTinh,
                diaChi: diaChi,
                dienThoai: dienThoai,
                ngaySinh: ngaySinh,
                vaiTro: vaiTro
            };

            // Gửi yêu cầu PUT để cập nhật thông tin nhân viên
            $.ajax({
                url: "/api/nhan-vien/update/" + maNV,
                type: "PUT",
                contentType: "application/json",
                data: JSON.stringify(updatedNhanVien),
                success: function (data) {
                    fetchEmployeeData(); // Gọi lại hàm fetchEmployeeData() để lấy dữ liệu mới
                    alert("Thông tin nhân viên đã được cập nhật!");
                    clearNhanVien()
                    e.preventDefault()
                    window.location = "/admin/nhan-vien"
                },
                error: function (xhr, status, error) {
                    console.error("Error updating employee:", error);
                }
            });
        })
        $('#btnDelete').click(function(e) {
            e.preventDefault(); // Prevent the default action (e.g., form submission)

            // Lấy mã nhân viên từ form
            var maNV = $('#MANV').val();
            if (maNV === "") {
                alert("Vui lòng điền đầy đủ thông tin!");
                return;
            }

            // Swal.fire({
            //     title: "Bạn có chắc chắn muốn xóa nhân viên"+maNV,
            //     showDenyButton: true,
            //     showCancelButton: true,
            //     confirmButtonText: "Có",
            //     denyButtonText: `Không`
            // }).then((result) => {
            //     /* Read more about isConfirmed, isDenied below */
            //     if (result.isConfirmed) {
            //
            //     } else if (result.isDenied) {
            //         window.location.reload()
            //     }
            // });
            $.ajax({
                url: "/api/nhan-vien/delete/" + maNV,
                type: "PUT",
                success: function (response) {
                    alert(response);
                    fetchEmployeeData();
                    clearNhanVien()
                    e.preventDefault()
                    window.location = "/admin/nhan-vien"
                },
                error: function (xhr, status, error) {
                    alert(status + ": " + error);
                    console.error("Error deleting employee:", error);
                }
            });

        });
});



