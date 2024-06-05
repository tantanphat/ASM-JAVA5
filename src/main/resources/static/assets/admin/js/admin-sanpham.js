$(document).ready(function() {
    var currentURL = window.location.href;
    var segments = currentURL.split('/');
    var masp = segments[segments.length - 1];
    console.log("Current URL: " + masp);

    function hienThiListDanhMucSanPham(callback) {
        $.ajax({
            url: "/api/san-pham/danh-muc-sp",
            type: "GET",
            success: function (response) {
                var selectElement = document.getElementById('danhMucSanPham');
                response.forEach(function (item) {
                    var option = new Option(item.tenDanhMuc, item.maDM);
                    selectElement.add(option);
                });
                callback(); // Gọi hàm callback khi danh sách danh mục sản phẩm đã được tải
            },
            error: function (xhr, status, error) {
                // Xử lý lỗi
                console.error("Error fetching employee data:", error);
            }
        })
    }

// Gọi hàm hienThiListDanhMucSanPham và truyền hàm callback để lấy thông tin sản phẩm
    hienThiListDanhMucSanPham(function () {
        $.ajax({
            url: "/api/san-pham/masp/" + masp,
            type: "GET",
            success: function (data) {
                $('#maSanPham').val(data.maSP);
                $('#tenSanPham').val(data.tenSP);
                $('#Sl_SP').val(data.soLuong);
                $('#donGiaBan').val(data.giaBan);
                $('#size').val(data.size);
                $('#ghiChu').val(data.ghiChu);
                $('#anhSP').attr('src',"/assets/sanpham/"+data.anh);
                $('#danhMucSanPham').val(data.maDM);
                var formContainer = document.getElementById('formUpdate');
                formContainer.scrollIntoView({ behavior: 'smooth' });
            }
        });
    });

    function hienThiListSP() {
        $.ajax({
            url: "/api/san-pham",
            type: "GET",
            success: function (response) {
                var tbody = $('#SanPham_Table tbody');
                tbody.append();
                response.forEach(function (item) {
                    console.log("SP: "+item)
                    var row = $('<tr></tr>');
                    row.append('<td><a href="/admin/san-pham/masp/' + item.maSP + '">' + item.maSP + '</a></td>');
                    row.append('<td>' + item.tenSP + '</td>');
                    row.append('<td>' + item.soLuong + '</td>');
                    row.append('<td>' + item.giaBan + '</td>');
                    row.append('<td>' + item.ghiChu + '</td>');
                    row.append('<td>' + item.maDM + '</td>');
                    row.append('<td>' + item.size + '</td>');
                    tbody.append(row);
                });
            },
            error: function (xhr, status, error) {
                console.error("Error fetching product data:", error);
            }
        });
    }

    //Chọn ảnh
    $('#anhSP').click(function () {
        $('#fileInput').click();
    })
    // $("#anhSP").attr("src", "/assets/sanpham/noImage.png");
    $('#fileInput').on('change', function() {
        var file = this.files[0];
        let reader = new FileReader();
        reader.onload = function (event) {
            $("#anhSP")
                .attr("src", event.target.result);
        };
        reader.readAsDataURL(file);
    })

    $('#demo').click(function () {
        var data = new FormData();
        data.append('file', $('#fileInput')[0].files[0]);
        $.ajax({
            url: '/api/san-pham/upload-anh-sp',
            type: 'post',
            data: data,
            processData: false,
            contentType: false,
            // contentType: 'multipart/form-data',
            success: function(response) {
                alert("Thành công")
            },
            error: function(xhr, status, error) {
                alert("Error fetching product data:")
            }
        });

    })

    $('#updateSP').click(function () {
        var file = $('#fileInput')[0].files[0];
        if (file) {
            formData.append('file', file);
        }



        var masp = $('#maSanPham').val();
        var tenSP = $('#tenSanPham').val();
        var danhMucSanPham = $('#danhMucSanPham').val();
        var Sl_SP = $('#Sl_SP').val();
        var donGiaBan = $('#donGiaBan').val();
        var size = $('#size').val();
        var ghiChu = $('#ghiChu').val();
        var data = {
            maSP: masp,
            tenSP: tenSP,
            maDM: danhMucSanPham,
            soLuong: Sl_SP,
            giaBan: donGiaBan,
            size: size,
            ghiChu: ghiChu,
            anh: $('#anhSP').attr('src').split('/').pop()
        }
        var data = {
            maSP: masp,
            tenSP: tenSP,
            maDM: danhMucSanPham,
            soLuong: Sl_SP,
            giaBan: donGiaBan,
            size: size,
            ghiChu: ghiChu,
            anh: $('#anhSP').attr('src').split('/').pop()
        };

        formData.append('sp', new Blob([JSON.stringify(data)], { type: "application/json" }));
        $.ajax({
            url: "/api/san-pham/updateSP",
            type: "post",
            data: formData,
            dataType: "json",
            contentType: "application/json",
            success: function (response) {
                alert(response);
                hienThiListSP();
            },
            error: function (xhr, status, error) {
                console.error("Lỗi:", error);
                alert("Lỗi");
            }
        })
    })

    hienThiListSP();
});

