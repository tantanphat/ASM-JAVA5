
$.ajax({
    url: "/list-hoadonchitiet",
    type: "GET",
    headers: {
        'Authorization': 'Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhcHBWZXIiOiIwLjAuMCIsImV4cCI6NDcyNjM4OTEyMiwibG9jYWxlIjoiIiwibWFzdGVyVmVyIjoiIiwicGxhdGZvcm0iOiIiLCJwbGF0Zm9ybVZlciI6IiIsInVzZXJJZCI6IiJ9.QIZbmB5_9Xlap_gDhjETfMI6EAmR15yBtIQkWFWJkrg',
    },
    success: function(response) {
        var tbody = $('#hoadonchitiet-Table tbody');
        tbody.empty();
        response.forEach(function(item) {
            var row = $('<tr></tr>');
            row.append('<td>' + item.hdct_maHDCT + '</a></td>');
            row.append('<td>' + item.hdct_maHDBan + '</td>');
            row.append('<td>' + item.hdct_maSP + '</td>');
            row.append('<td>' + item.hdct_soLuong + '</td>');
            row.append('<td>' + item.hdct_giamGia + '</td>');
            tbody.append(row);
        })
    },
    error: function(xhr, status, error) {
        // Xử lý lỗi
        console.error("Error fetching employee data:", error);
    }
});
