$(document).ready(function() {
    // Function to fetch data from the API and populate the table
    function TableDoanhThu(month_DT) {
        $.ajax({
            url: '/api/thong-ke/doanh-thu/' + month_DT,
            type: 'GET',
            success: function (response) {
                var tbody = $('#DoanhThu_Table tbody');
                tbody.empty(); // Clear the table body before adding new rows
                response.forEach(function (item) {
                    var row = $('<tr></tr>');
                    row.append('<td>' + item[0] + '</td>'); // Số HD đã bán
                    row.append('<td>' + item[1] + '</td>'); // Số sản phẩm
                    row.append('<td>' + item[2] + '</td>'); // Doanh thu
                    row.append('<td>' + item[3] + '</td>'); // HD thấp nhất
                    row.append('<td>' + item[4] + '</td>'); // HD cao nhất
                    row.append('<td>' + item[5] + '</td>'); // Trung bình
                    tbody.append(row); // Append the row to the correct tbody
                });
            },
            error: function (xhr, status, error) {
                console.error("Error fetching doanh thu data:", error);
            }
        });

        $.ajax({
            url: '/api/thong-ke/san-pham-ban/' + month_DT,
            type: 'GET',
            success: function (response) {
                var tbody = $('#SanPhamBan_Table tbody');
                tbody.empty(); // Clear the table body before adding new rows
                response.forEach(function (item) {
                    var row = $('<tr></tr>');
                    row.append('<td>' + item[0] + '</td>'); // Mã hóa đơn
                    row.append('<td>' + item[1] + '</td>'); // Số lượng SP
                    row.append('<td>' + item[2] + '</td>'); // Mã NV
                    row.append('<td>' + item[3] + '</td>'); // Tên NV
                    row.append('<td>' + item[4] + '</td>'); // Ngày bán
                    row.append('<td>' + item[5] + '000đ' + '</td>'); // Tổng tiền
                    tbody.append(row); // Append the row to the correct tbody
                });
            },
            error: function (xhr, status, error) {
                console.error("Error fetching sản phẩm bán data:", error);
            }
        });
    }
    function TableNhanVien(month) {
        $.ajax({
            url: '/api/thong-ke/nhan-vien-co-don/' + month,
            type: 'GET',
            success: function (response) {
                var tbody = $('#NhanVienThang_Table tbody');
                tbody.empty(); // Clear the table body before adding new rows
                response.forEach(function (item) {
                    var row = $('<tr></tr>');
                    row.append('<td>' + item[0] + '</td>'); // Mã nhân viên
                    row.append('<td>' + item[1] + '</td>'); // Tên nhân viên
                    row.append('<td>' + item[2] + '</td>'); // Số hóa đơn bán được
                    row.append('<td>' + item[3] + '</td>'); // Số sản phẩm bán được
                    row.append('<td>' + item[4] + '000đ' + '</td>'); // Tổng tiền bán được
                    tbody.append(row);
                });
            },
            error: function (xhr, status, error) {
                console.error("Error fetching employee data:", error);
            }
        });

        $.ajax({
            url: '/api/thong-ke/nhan-vien-chi-tiet/' + month,
            type: 'GET',
            success: function (response) {
                var tbody = $('#NhanVienChiTiet_Table tbody');
                tbody.empty(); // Clear the table body before adding new rows
                response.forEach(function (item) {
                    var row = $('<tr></tr>');
                    row.append('<td>' + item[0] + '</td>'); // Mã hóa đơn
                    row.append('<td>' + item[1] + '</td>'); // Số lượng SP
                    row.append('<td>' + item[2] + '</td>'); // Mã NV
                    row.append('<td>' + item[3] + '</td>'); // Tên NV
                    row.append('<td>' + item[4] + '</td>'); // Ngày bán
                    row.append('<td>' + item[5] + '000đ' + '</td>'); // Tổng tiền
                    tbody.append(row);
                });
            },
            error: function (xhr, status, error) {
                console.error("Error fetching employee data:", error);
            }
        });
    }
    function TableSanPham(month) {
        $('#pagination-container-tk-sp-dc').pagination({
            dataSource: function(done){
                $.ajax({
                    url: '/api/thong-ke/san-pham-ban-duoc/' + month,
                    type: "GET",
                    success: function(response) {
                        done(response)
                    }
                });
            },
            pageSize: 3,
            autoHidePrevious: true,
            autoHideNext: true,
            prevText: '&laquo; Trước',
            nextText: 'Sau &raquo;',
            callback: function(data, pagination) {
                var tbody = $('#SanPhamBanDuoc_Table tbody');
                tbody.empty(); // Clear the table body before adding new rows
                data.forEach(function (item) {
                    var row = $('<tr></tr>');
                    row.append('<td>' + item[0] + '</td>'); // Mã hóa đơn
                    row.append('<td>' + item[1] + '</td>'); // Số lượng SP
                    row.append('<td>' + item[2] + '</td>'); // Mã NV
                    row.append('<td>' + item[3] + '</td>'); // Tên NV
                    row.append('<td>' + item[4] + '</td>'); // Ngày bán
                    tbody.append(row);
                });
            }
        })

        $('#pagination-container-tk-sp').pagination({
            dataSource: function(done){
                $.ajax({
                    url: '/api/thong-ke/san-pham-khong-ban/' + month,
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
            callback: function(data, pagination) {
                var tbody = $('#SanPhamKBan_Table tbody');
                tbody.empty(); // Clear the table body before adding new rows
                data.forEach(function (item) {
                    var row = $('<tr></tr>');
                    row.append('<td>' + item[0] + '</td>'); // Mã hóa đơn
                    row.append('<td>' + item[1] + '</td>'); // Số lượng SP
                    row.append('<td>' + item[2] + '</td>'); // Mã NV
                    row.append('<td>' + item[3] + '</td>'); // Tên NV
                    row.append('<td>' + item[4] + '</td>'); // Ngày bán
                    tbody.append(row);
                });
            }
        })
    }

    $('#month-search').on('input', function(e) {
        var month_report = $('#month-search').val();
        TableDoanhThu(month_report);
        TableNhanVien(month_report);
        TableSanPham(month_report);
    })

    $('#btnSPExcel').click(function () {
        var thang = $('#month-search').val()
        $.ajax({
            url: '/api/thong-ke/excel-thong-ke-san-pham?month='+thang,
            type: 'GET',
            success: function(response) {
                alert("Xuất ra thành công");
            },
            error: function(xhr, status, error) {
                alert("Failed to export doanh thu data: " + error);
            }
        });
    });
});
