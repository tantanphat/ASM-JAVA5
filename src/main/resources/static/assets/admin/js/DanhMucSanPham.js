$(document).ready(function() {

    function hienDanhMucLenForm(madm) {
        $.ajax({
            url: "/api/danh-muc-san-pham/madm",
            type: "GET",
            data: {madm:madm},
            success: function (response) {
                $('#maDanhMMuc').val(response.maDM);
                $('#tenDanhMuc').val(response.tenDanhMuc);
            },
            error: function (xhr, status, error) {
                console.error("Error fetching employee data:", error);
            }
        })
    }

    function hienThiListDanhMucSanPham(callback) {
        $.ajax({
            url: "/api/danh-muc-san-pham",
            type: "GET",
            success: function (response) {
                var tbody = $('#DanhMucSPTable tbody');
                tbody.empty(); // Clear the table body before adding new rows
                response.forEach(function (item) {
                    var row = $('<tr></tr>');
                    row.append('<td class="maDM">' + item.maDM + '</td>');
                    row.append('<td>' + item.tenDanhMuc + '</td>');
                    tbody.append(row);
                })

                $('.maDM').on('click', function(e) {
                    var maDM = $(this).text();
                    hienDanhMucLenForm(maDM);
                })
            },
            error: function (xhr, status, error) {
                console.error("Error fetching employee data:", error);
            }
        })
    }
    hienThiListDanhMucSanPham()
});

