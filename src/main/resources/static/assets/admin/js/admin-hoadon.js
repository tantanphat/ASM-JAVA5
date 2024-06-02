$(document).ready(function() {
    var currentURL = window.location.href;
    var segments = currentURL.split('/');
    var hd_MaHDBan = segments[segments.length - 1];
    console.log(hd_MaHDBan);

    $.ajax({
        url: "/api/hoa-don/" + hd_MaHDBan,
        type: "GET",
        success: function(data) {
            console.log('HoaDon', data);
            $('#MaHD').val(data.hd_MaHDBan);
            $('#TenKH_HD').val(data.khachHang.tenKH);
            $('#Date').val(data.hd_NgayBan);
            $('#MaKH_HD').val(data.hd_MaKH);
            $('#MaNV_HD').val(data.hd_MaNV);
            $('#Address_HD').val(data.khachHang.diaChi);
            $('#TenNV_HD').val(data.nhanVien.tenNV);
            $('#Phone_HD').val(data.khachHang.sdt);
            var formContainer = document.getElementById('formUpdate');
            formContainer.scrollIntoView({ behavior: 'smooth' });

            // Load HoaDonChiTiet
            loadHoaDonChiTiet(hd_MaHDBan);

            $('#hoa-don-chi-tiet-tab').tab('show');
        }
    });

    // function loadHoaDonChiTiet(maHDBan) {
    //     $.ajax({
    //         url: "/api/hoa-don-chi-tiet/" + maHDBan,
    //         type: "GET",
    //         success: function(response) {
    //             var tbody = $('#hoadonchitiet-Table tbody');
    //             tbody.empty();
    //             response.forEach(function(item) {
    //                 var row = $('<tr></tr>');
    //                 row.append('<td><a href="/admin/hoa-don-chi-tiet/' + item.hdct_maHDCT + '">' + item.hdct_maHDCT + '</a></td>');
    //                 row.append('<td>' + item.hdct_maHDBan + '</td>');
    //                 row.append('<td>' + item.hdct_maSP + '</td>');
    //                 row.append('<td>' + item.sanPham.giaBan*100 + 'đ' + '</td>');
    //                 row.append('<td>' + item.hdct_soLuong + '</td>');
    //                 row.append('<td>' + item.hdct_giamGia + '</td>');
    //                 row.append('<td>' + (item.sanPham.soLuong*item.sanPham.giaBan)*(100-(item.hdct_giamGia)) + 'đ' + '</td>');
    //                 tbody.append(row);
    //             })
    //         },
    //         error: function(xhr, status, error) {
    //             // Xử lý lỗi
    //             console.error("Error fetching employee data:", error);
    //         }
    //     });
    // }
});


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
        console.log("Objects: "+item)
    var row = $('<tr></tr>');
    row.append('<td><a href="/admin/hoa-don/' + item.hd_MaHDBan + '">' + item.hd_MaHDBan + '</a></td>');
    row.append('<td>' + item.hd_MaNV + '</td>');
    row.append('<td>' + item.hd_NgayBan + '</td>');
    row.append('<td>' + item.hd_MaKH + '</td>');
    tbody.append(row);
})
},
    error: function(xhr, status, error) {
    // Xử lý lỗi
    console.error("Error fetching employee data:", error);
}
});
function btnCreatHD_click() {
    var hoaDonData = {
        hd_MaHDBan: $('#MaHD').val(),
        hd_MaNV: $('#MaNV_HD').val(),
        hd_NgayBan: $('#Date').val(),
        hd_MaKH: $('#MaKH_HD').val(),
        // data.khachHang.tenKH: $('#TenKH_HD').val(),
        // data.khachHang.diaChi: $('#Address_HD').val(),
        // data.nhanVien.tenNV: $('#TenNV_HD').val(),
        // data.khachHang.sdt:  $('#Phone_HD').val()
    };

    $.ajax({
        url: "/api/hoa-don/add",
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify(hoaDonData),
        success: function (data) {
            // Thêm hàng mới vào bảng
            var tbody = $('#HoaDon-Table tbody');
            var row = $('<tr></tr>');
            row.append('<td><a href="/admin/hoa-don/' + data.hd_MaHDBan + '">' + data.hd_MaHDBan + '</a></td>');
            row.append('<td>' + data.hd_MaNV + '</td>');
            row.append('<td>' + data.hd_NgayBan + '</td>');
            row.append('<td>' + data.hd_MaKH + '</td>');
            tbody.append(row);
            alert("Hóa đơn đã được tạo thành công!");
        },
        error: function (xhr, status, error) {
            console.error("Lỗi khi tạo Hóa đơn:", error);
            alert("Lỗi khi tạo Hóa đơn.");
        }
    });
}
