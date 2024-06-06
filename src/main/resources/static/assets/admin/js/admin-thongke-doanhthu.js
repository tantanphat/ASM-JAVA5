$(document).ready(function() {
    // Function to fetch data from the API and populate the table
    function TableDoanhThu(month) {
        $.ajax({
            url: '/api/thong-ke/doanh-thu/' + month,
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
            url: '/api/thong-ke/san-pham-ban/' + month,
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

    $('#month-search_DT').on('input', function(e) {
        var month = $('#month-search_DT').val();
        TableDoanhThu(month);
    })

});$(document).ready(function() {
    // Function to fetch data from the API and populate the table
    function TableDoanhThu(month) {
        $.ajax({
            url: '/api/thong-ke/doanh-thu/' + month,
            type: 'GET',
            success: function (response) {
                var tbody = $('#DoanhThu_Table tbody');
                tbody.empty(); // Clear the table body before adding new rows
                response.forEach(function (item) {
                    var row = $('<tr></tr>');
                    row.append('<td>' + item[0] + '</td>'); // Số HD đã bán
                    row.append('<td>' + item[1] + '</td>'); // Số sản phẩm
                    row.append('<td>' + (item[2]*100) + 'đ' + '</td>'); // Doanh thu
                    row.append('<td>' + (item[3]*100) + 'đ' + '</td>'); // HD thấp nhất
                    row.append('<td>' + (item[4]*100) + 'đ' + '</td>'); // HD cao nhất
                    row.append('<td>' + (item[5]*100) + 'đ' + '</td>'); // Trung bình
                    tbody.append(row); // Append the row to the correct tbody
                });
            },
            error: function (xhr, status, error) {
                console.error("Error fetching doanh thu data:", error);
            }
        });

        $.ajax({
            url: '/api/thong-ke/san-pham-ban/' + month,
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
                    row.append('<td>' + (item[5]*100) + 'đ' + '</td>'); // Tổng tiền
                    tbody.append(row); // Append the row to the correct tbody
                });
            },
            error: function (xhr, status, error) {
                console.error("Error fetching sản phẩm bán data:", error);
            }
        });
    }

    // Event listener for the search button click
    $('#search-button_DT').click(function() {
        // Get the month from the input field
        var month = $('#month-search_DT').val();
        TableDoanhThu(month);
    });
});