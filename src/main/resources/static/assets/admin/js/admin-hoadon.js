$(document).ready(function() {
    let check = false;

    function doThonTinHoaDonLenForm(data) {
        $.ajax({
            url: "/api/hoa-don/" + data,
            type: "GET",
            success: function(data) {
                $('#MaHD').val(data.hd_MaHDBan);
                $('#TenKH_HD').val(data.khachHang.tenKH);
                $('#Date').val(data.hd_NgayBan);
                $('#MaKH_HD').val(data.hd_MaKH);
                var selectElement = document.getElementById('MaNV_HD');
                selectElement.value = data.hd_MaNV;
                $('#Address_HD').val(data.khachHang.diaChi);
                $('#TenNV_HD').val(data.nhanVien.tenNV);
                $('#Phone_HD').val(data.khachHang.sdt);
                $('#Mail_KH').val(data.khachHang.email)
                var formContainer = document.getElementById('hoaDonForm');
                formContainer.scrollIntoView({ behavior: 'smooth' });
            }
        });
    }
    function doHDCTLenTableTheoMaHDBan(data) {
        $.ajax({
            url: "/api/hoa-don-chi-tiet/mahd/"+data,
            type: "GET",
            success: function(response) {
                var tbody = $('#hoadonchitiet-Table tbody');
                tbody.empty();
                response.forEach(function(item) {
                    var row = $('<tr></tr>');
                    row.append('<td class="clHDCT">' + item.hdct_maHDCT + '</td>');
                    row.append('<td>' + item.hdct_maHDBan + '</td>');
                    row.append('<td>' + item.sanPham.maSP + '</td>');
                    row.append('<td>' + item.sanPham.giaBan*100 + 'đ' + '</td>');
                    row.append('<td>' + item.hdct_soLuong + '</td>');
                    row.append('<td>' + item.hdct_giamGia + '</td>');
                    row.append('<td>' + (item.hdct_soLuong*item.sanPham.giaBan)*(100-(item.hdct_giamGia)) + 'đ' + '</td>');
                    tbody.append(row);
                    $('.clHDCT').click(function(e) {
                        var hdct_maHDCT = $(this).text();
                        $.ajax({
                            url: "/api/hoa-don-chi-tiet/" + hdct_maHDCT,
                            type: "GET",
                            success: function (data) {
                                $('#MHDCT').val(data.hdct_maHDCT);
                                $('#DonGia').val(data.sanPham.giaBan*100 + 'đ');
                                $('#TenSP').val(data.sanPham.tenSP);
                                $('#Quantity').val(data.hdct_soLuong);
                                $('#MaSP_CT').val(data.sanPham.maSP);
                                $('#Sale').val(data.hdct_giamGia);
                                $('#ThanhTien').val((data.hdct_soLuong * data.sanPham.giaBan) * (100-(data.hdct_giamGia)) + 'đ');
                                var formContainer = document.getElementById('formUpdate');
                                formContainer.scrollIntoView({ behavior: 'smooth' });
                            }
                        });
                    });
                })
            },
            error: function(xhr, status, error) {
                // Xử lý lỗi
                console.error("Error fetching employee data:", error);
            }
        });
    }
    function doHoaDonLenTable() {
        $.ajax({
            url: "/api/hoa-don",
            type: "GET",
            headers: {
                'Authorization': 'Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhcHBWZXIiOiIwLjAuMCIsImV4cCI6NDcyNjM4OTEyMiwibG9jYWxlIjoiIiwibWFzdGVyVmVyIjoiIiwicGxhdGZvcm0iOiIiLCJwbGF0Zm9ybVZlciI6IiIsInVzZXJJZCI6IiJ9.QIZbmB5_9Xlap_gDhjETfMI6EAmR15yBtIQkWFWJkrg',
            },
            success: function(response) {
                var tbody = $('#HoaDon-Table tbody');
                tbody.empty();
                response.forEach(function(item) {
                    var row = $('<tr></tr>');
                    row.append('<td class="hdMaHD">' + item.hd_MaHDBan + '</td>');
                    row.append('<td>' + item.hd_MaNV + '</td>');
                    row.append('<td>' + item.hd_NgayBan + '</td>');
                    row.append('<td>' + item.hd_MaKH + '</td>');
                    tbody.append(row);
                    $('.hdMaHD').click(function(e) {
                        var hd_MaHDBan = $(this).text();
                        doThonTinHoaDonLenForm(hd_MaHDBan)
                        doHDCTLenTableTheoMaHDBan(hd_MaHDBan)
                    });
                })
            },
            error: function(xhr, status, error) {
                // Xử lý lỗi
                console.error("Error fetching employee data:", error);
            }
        });
    }
    function listMaNV() {
        $.ajax({
            url: "/api/nhan-vien/listMaNV",
            type: "GET",
            success: function(response) {
                var selectElement = document.getElementById('MaNV_HD');
                response.forEach(function(maNV) {
                    var option = new Option(maNV, maNV);
                    selectElement.add(option);
                });
            },
            error: function(xhr, status, error) {
                // Xử lý lỗi
                console.error("Error fetching employee data:", error);
            }
        });
    }
    function doLaiHDCTTable(mahd){
        $.ajax({
            url: "/api/hoa-don-chi-tiet/mahd/"+mahd,
            type: "GET",
            success: function(response) {
                var tbody = $('#hoadonchitiet-Table tbody');
                tbody.empty();
                response.forEach(function(item) {
                    var row = $('<tr></tr>');
                    row.append('<td class="clHDCT">' + item.hdct_maHDCT + '</td>');
                    row.append('<td>' + item.hdct_maHDBan + '</td>');
                    row.append('<td>' + item.sanPham.maSP + '</td>');
                    row.append('<td>' + item.sanPham.giaBan*100 + 'đ' + '</td>');
                    row.append('<td>' + item.hdct_soLuong + '</td>');
                    row.append('<td>' + item.hdct_giamGia + '</td>');
                    row.append('<td>' + (item.hdct_soLuong*item.sanPham.giaBan)*(100-(item.hdct_giamGia)) + 'đ' + '</td>');
                    tbody.append(row);
                })
            },
            error: function(xhr, status, error) {
                // Xử lý lỗi
                console.error("Error fetching employee data:", error);
            }
        });
    }
    function clearHDCT(){
        $('#MHDCT').val("")
        $('#DonGia').val("")
        $('#TenSP').val("")
        $("#Quantity").val("")
        $("#MaSP_CT").val("")
        $("#Sale").val("")
        $("#ThanhTien").val("")
    }

    function doTenSPKhiNhapMaSP() {
        $('#MaSP_CT').on('input', function() {
            var timSP = $(this).val();
            if (timSP.trim() !== "") {
                $.ajax({
                    url: "/api/san-pham/timkiems",
                    type: "GET",
                    data: { maSP: timSP },
                    success: function(response) {
                        $('#TenSP').val(response.tenSP);
                        $('#DonGia').val(response.donGia*100+"đ");
                    }
                });
            } else {
                $('#TenSP').val('');
            }
        });
    }

    $('#hdct_add').click(function(e) {
        var maHDBan = $("#MaHD").val()
        var maSP = $('#MaSP_CT').val()
        var soLuong= $('#Quantity').val()
        var giamGia= $('#Sale').val()

        if ( maSP == "" || soLuong == ""  || giamGia == "" || maHDBan == "" )   {
            Swal.fire({
                icon: "error",
                title: "Oops...",
                text: "Something went wrong!",
            });
            return;
        } else {
            let data = {
                maHDBan: maHDBan,
                maSP: maSP,
                soLuong: soLuong,
                giamGia: giamGia,
            };
            $.ajax({
                url: "/api/hoa-don-chi-tiet/add-hdct",
                type: "post",
                data: JSON.stringify(data),
                contentType: "application/json",
                dataType: "json",
            })
            doLaiHDCTTable(maHDBan);
        }

    })

    $('#updateHDCT').click(function(e) {MHDCT
        var MHDCT = $("#MHDCT").val()
        var maHDBan = $("#MaHD").val()
        var maSP = $('#MaSP_CT').val()
        var soLuong= $('#Quantity').val()
        var giamGia= $('#Sale').val()

        if (MHDCT == "" || maHDBan == "" || soLuong == "" || maSP == "" || giamGia == "" )   {
            Swal.fire({
                icon: "error",
                title: "Oops...",
                text: "Something went wrong!",
            });
            return;
        } else {
            var data = {
                hdct_maHDCT: MHDCT,
                hdct_maHDBan: maHDBan,
                hdct_maSP: maSP,
                hdct_soLuong: soLuong,
                hdct_giamGia: giamGia,
            };
            $.ajax({
                url: "/api/hoa-don-chi-tiet/update-hdct",
                type: "post",
                data: JSON.stringify(data),
                contentType: "application/json",
                dataType: "json",
                success: function (response) {
                    doLaiHDCTTable(maHDBan)
                    alert("Updated")

                },error: function (jqXHR, textStatus, errorThrown){
                    console.log(jqXHR);
                    console.log(textStatus);
                    console.log(errorThrown);
                }
            })
            clearHDCT()
            doLaiHDCTTable(maHDBan);
        }


    })

    $('#deleteHDCT').click(function(e) {
        var MHDCT = $("#MHDCT").val()
        var maHDBan = $("#MaHD").val()
        if (MHDCT == "" || maHDBan == ""  )   {
            Swal.fire({
                icon: "error",
                title: "Oops...",
                text: "Something went wrong!",
            });
            return;
        } else {
            $.ajax({
                url: "/api/hoa-don-chi-tiet/delete-hdct",
                type: "delete",
                data: {mahdct:MHDCT},
                success: function (response) {
                    clearHDCT()
                    doLaiHDCTTable(maHDBan);

                },error: function (jqXHR, textStatus, errorThrown){
                    console.log(jqXHR);
                    console.log(textStatus);
                    console.log(errorThrown);
                }
            })

        }

    })

    $("#deleteHD").click(function () {
        var mahd = $('#MaHD').val();
        $.ajax({
            url: "/api/hoa-don/delete",
            type: "delete",
            data: { mahd: mahd },
            success: function (response) {
                alert(response);
                $('#MaHD').val(null);
                $('#TenKH_HD').val(null);
                $('#Date').val(null);
                $('#MaKH_HD').val(null);
                $('#Address_HD').val(null);
                $('#TenNV_HD').val(null);
                $('#Phone_HD').val(null);
                doHoaDonLenTable();
            },
            error: function (xhr, status, error) {
                console.error("Lỗi:", error);
                alert("Lỗi");
            }
        });
    });
    $("#createHD").click(function () {
        var data = {
            maNV: $('#MaNV_HD').val(),
            maKH: $('#MaKH_HD').val(),
            tenKH: $('#TenKH_HD').val(),
            diaChi: $('#Address_HD').val(),
            email: $('#Mail_KH').val(),
            sdt: $('#Phone_HD').val(),

        };
        $.ajax({
            url: "/api/hoa-don/createHD",
            type: "post",
            data: JSON.stringify(data),
            dataType: "json",
            contentType: "application/json",
            success: function (response) {
                alert(response);
                doHoaDonLenTable();
            },
            error: function (xhr, status, error) {
                console.error("Lỗi:", error);
                alert("Lỗi");
            }
        });
    });
    $("#clearHD").click(function () {

        $('#MaHD').val(null);
        $('#TenKH_HD').val(null);
        $('#Date').val(null);
        $('#MaKH_HD').val(null);
        $('#Address_HD').val(null);
        $('#TenNV_HD').val(null);
        $('#Phone_HD').val(null);
        $('#Mail_KH').val(null);
    });
    $("#editHD").click(function () {
        check = !check;

        if (check) {
            // Enable input fields when check is true
            $('#TenKH_HD').removeAttr('disabled');
            $('#MaKH_HD').removeAttr('disabled');
            $('#Address_HD').removeAttr('disabled');
            $('#Phone_HD').removeAttr('disabled');
            $('#Mail_KH').removeAttr('disabled');
        } else {
            // Disable input fields when check is false
            $('#TenKH_HD').attr('disabled', 'disabled');
            $('#MaKH_HD').attr('disabled', 'disabled');
            $('#Address_HD').attr('disabled', 'disabled');
            $('#Phone_HD').attr('disabled', 'disabled');
            $('#Mail_KH').attr('disabled', 'disabled');
        }
    });

    doThonTinHoaDonLenForm();
    doHoaDonLenTable();
    doHDCTLenTableTheoMaHDBan();
    doTenSPKhiNhapMaSP();
    listMaNV();



});
