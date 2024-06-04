$(document).ready(function() {
    // Function to fetch data from the API and populate the table
    function TableSanPham(month) {
        $.ajax({
            url: '/api/thong-ke/san-pham-ban-duoc/' + month,
            type: 'GET',
            success: function (response) {
                var tbody = $('#SanPhamBanDuoc_Table tbody');
                tbody.empty(); // Clear the table body before adding new rows
                response.forEach(function (item) {
                    var row = $('<tr></tr>');
                    row.append('<td>' + item[0] + '</td>');
                    row.append('<td>' + item[1] + '</td>');
                    row.append('<td>' + item[2] + '</td>');
                    row.append('<td>' + item[3] + '</td>');
                    row.append('<td>' + item[4] + '</td>');
                    tbody.append(row);
                });
            },
            error: function (xhr, status, error) {
                console.error("Error fetching employee data:", error);
            }
        });

        $.ajax({
            url: '/api/thong-ke/san-pham-khong-ban/' + month,
            type: 'GET',
            success: function (response) {
                var tbody = $('#SanPhamKBan_Table tbody');
                tbody.empty(); // Clear the table body before adding new rows
                response.forEach(function (item) {
                    var row = $('<tr></tr>');
                    row.append('<td>' + item[0] + '</td>'); // Mã hóa đơn
                    row.append('<td>' + item[1] + '</td>'); // Số lượng SP
                    row.append('<td>' + item[2] + '</td>'); // Mã NV
                    row.append('<td>' + item[3] + '</td>'); // Tên NV
                    row.append('<td>' + item[4] + '</td>'); // Ngày bán
                    tbody.append(row);
                });
            },
            error: function (xhr, status, error) {
                console.error("Error fetching employee data:", error);
            }
        });
    }

    // Event listener for the search button click
    $('#search-button_SP').click(function() {
        // Get the month from the input field
        var month = $('#month-search_SP').val();
        TableSanPham(month);
    });
});