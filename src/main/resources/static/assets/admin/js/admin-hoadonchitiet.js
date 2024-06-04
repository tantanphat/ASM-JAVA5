$(document).ready(function() {
    var currentURL = window.location.href;
    var segments = currentURL.split('/');
    var hdct_maHDCT = segments[segments.length - 1];
    console.log(hdct_maHDCT);

    $.ajax({
        url: "/api/hoa-don-chi-tiet/" + hdct_maHDCT,
        type: "GET",
        success: function (data) {
            console.log('Hello', data);
            $('#MHDCT').val(data.hdct_maHDCT);
            $('#DonGia').val(data.sanPham.giaBan*100 + 'đ');
            $('#TenSP').val(data.sanPham.tenSP);
            $('#Quantity').val(data.hdct_soLuong);
            $('#MaSP_CT').val(data.hdct.maSP);
            $('#Sale').val(data.hdct_giamGia);
            $('#ThanhTien').val((data.hdct.soLuong*data.sanPham.giaBan)*(100-(data.hdct_giamGia)) + 'đ');
            var formContainer = document.getElementById('formUpdate');
            formContainer.scrollIntoView({ behavior: 'smooth' });
        }
    });
    var maHDBan = "HD00001";
    $.ajax({
        url: "/api/hoa-don-chi-tiet/mahd/" + maHDBan,
        type: "GET",
        success: function(response) {
            var tbody = $('#hoadonchitiet-Table tbody');
            tbody.empty();
            response.forEach(function(item) {
                var row = $('<tr></tr>');
                row.append('<td><a href="/admin/hoa-don-chi-tiet/' + item.hdct_maHDCT + '">' + item.hdct_maHDCT + '</a></td>');
                row.append('<td>' + item.hdct_maHDBan + '</td>');
                row.append('<td>' + item.sanPham.maSP + '</td>');
                row.append('<td>' + item.sanPham.giaBan*100 + 'đ' + '</td>');
                row.append('<td>' + item.hdct_soLuong + '</td>');
                row.append('<td>' + item.hdct_giamGia + '</td>');
                row.append('<td>' + (item.sanPham.soLuong*item.sanPham.giaBan)*(100-(item.hdct_giamGia)) + 'đ' + '</td>');
                tbody.append(row);
            })
        },
        error: function(xhr, status, error) {
            // Xử lý lỗi
            console.error("Error fetching employee data:", error);
        }
    });
});
