$(document).ready(function() {
    var currentURL = window.location.href;
    var segments = currentURL.split('/');
    var masp = segments[segments.length - 1];
    console.log(masp);

    var categoryMapping = {
        1: 'Áo',
        2: 'Quần',
        3: 'Mũ-Nón',
        4: 'Giày-Dép',
        5: 'Phụ kiện',
        6: 'Khác'
    };

    var danhMucSanPhamSelect = $('#danhMucSanPham');
    $.each(categoryMapping, function(key, value) {
        var option = $('<option></option>')
            .attr('value', key)
            .text(value);
        danhMucSanPhamSelect.append(option);
    });

    $.ajax({
        url: "/api/san-pham/masp/" + masp,
        type: "GET",
        success: function (data) {
            console.log('Hello', data);
            $('#maSanPham').val(data.maSP);
            $('#tenSanPham').val(data.tenSP);
            $('#Sl_SP').val(data.soLuong);
            $('#donGiaBan').val(data.giaBan);
            $('#size').val(data.size);
            $('#ghiChu').val(data.ghiChu);
            $('#danhMucSanPham').val(data.maDM);  // Update this line to set the category
            var formContainer = document.getElementById('formUpdate');
            formContainer.scrollIntoView({ behavior: 'smooth' });
        }
    });

    $.ajax({
        url: "/api/san-pham",
        type: "GET",
        success: function (response) {
            var tbody = $('#SanPham_Table tbody');
            response.forEach(function (item) {
                var row = $('<tr></tr>');
                row.append('<td><a href="/admin/san-pham/masp/' + item.maSP + '">' + item.maSP + '</a></td>');
                row.append('<td>' + item.tenSP + '</td>');
                row.append('<td>' + item.soLuong + '</td>');
                row.append('<td>' + item.giaBan + '</td>');
                row.append('<td>' + item.ghiChu + '</td>');
                row.append('<td>' + categoryMapping[item.maDM] + '</td>');
                row.append('<td>' + item.size + '</td>');
                tbody.append(row);
            });
        },
        error: function (xhr, status, error) {
            console.error("Error fetching product data:", error);
        }
    });
});

