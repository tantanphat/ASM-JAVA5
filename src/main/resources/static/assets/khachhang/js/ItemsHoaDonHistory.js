$(document).ready(function() {
    function getMaNV(callback) {
        var currentURL = window.location.href;
        var url = new URL(currentURL);
        var MaDH = url.searchParams.get("MaDH");
        callback(MaDH); // Gọi callback và truyền giá trị manv vào
    }

    function getNVByMANV() {
        getMaNV(function(MaDH) {
            if (MaDH) {
                $.ajax({
                    url: "/api/khach-hang/San-pham-hoa-don?MaDH=" + MaDH,
                    type: "GET",
                    success: function(data) {
                        var tbody = $('#listSPItemsHD tbody');
                        tbody.empty(); // Xóa nội dung cũ của bảng
                        data.forEach(function(item) {
                            var row = $('<tr></tr>');
                            row.append('<td><img src="https://res.cloudinary.com/dtnf47wll/image/upload/' + item[0] + '" alt="Product Image" width="50"></td>');
                            row.append('<td>' + item[1] + '</td>');
                            row.append('<td>' + item[2] + '</td>');
                            row.append('<td>' + item[3] + '</td>');
                            row.append('<td>' + item[4] + '</td>');
                            row.append('<td>' + item[5] + '</td>');
                            tbody.append(row); // Thêm hàng vào bảng
                        })
                    },
                    error: function(xhr, status, error) {
                        if (xhr.status == 404) {
                            alert("Không tìm thấy nhân viên có mã " + manv);
                            window.location.href = "/Trang-chu";
                        }
                    }
                });
            }
        });
    }
    getNVByMANV();

});