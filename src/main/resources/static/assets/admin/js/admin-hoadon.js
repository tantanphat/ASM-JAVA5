
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
    row.append('<td>' + item.hd_MaHDBan + '</a></td>');
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
