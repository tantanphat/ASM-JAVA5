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

    $('#createDMSP').click(function(e) {
        var maDM = $('#maDanhMMuc').val();
        var tenDM = $('#tenDanhMuc').val();
        var data = {
            tenDanhMuc: tenDM
        }
        if (maDM == '') {
            $.ajax({
                url: "/api/danh-muc-san-pham/create",
                type: "POST",
                data: JSON.stringify(data),
                contentType: "application/json",
                success: function (response) {
                    alert(response)
                    hienThiListDanhMucSanPham()
                },
                error: function (xhr, status, error) {
                    console.error("Error fetching employee data:", error);
                }
            })
        } else {
            alert("Không thể thêm")

        }

    })
    $('#updateDMSP').click(function(e) {
        var maDM = $('#maDanhMMuc').val();
        var tenDM = $('#tenDanhMuc').val();
        var data = {
            maDM:maDM,
            tenDanhMuc: tenDM
        }
        if (maDM == '') {
            alert("Vui lòng đầy đủ thông tin")
        } else {

            $.ajax({
                url: "/api/danh-muc-san-pham/update",
                type: "PUT",
                data: JSON.stringify(data),
                contentType: "application/json",
                success: function (response) {
                    alert(response)
                    hienThiListDanhMucSanPham()
                },
                error: function (xhr, status, error) {
                    console.error("Error fetching employee data:", error);
                }
            })
        }

    })
    $('#clearDMSP').click(function(e) {
        $('#maDanhMMuc').val("");
        $('#tenDanhMuc').val("");
    })
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

