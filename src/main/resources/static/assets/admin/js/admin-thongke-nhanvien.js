$(document).ready(function() {
    // Function to fetch data from the API and populate the table
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

    // Event listener for the search button click
    $('#search-button').click(function() {
        // Get the month from the input field
        var month = $('#month-search').val();
        TableNhanVien(month);
    });
});