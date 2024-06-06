$(document).ready(function() {
    // var currentURL = window.location.href;
    // var segments = currentURL.split('/');
    // var masp = segments[segments.length - 1];
    // console.log("Current URL: " + masp);

    function clearSP() {
        $('#maSanPham').val(null);
        $('#tenSanPham').val(null);
        $('#danhMucSanPham').val(null);
        $('#Sl_SP').val(null);
        $('#donGiaBan').val(null);
        $('#size').val(null);
        $('#ghiChu').val(null);
        $('#anhSP').attr('src','/assets/sanpham/noImage.png');
    }

    function hienThiSPLenForm(masp) {
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
            }
        });
    }

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
                console.error("Error fetching employee data:", error);
            }
        })
    }
    hienThiListDanhMucSanPham();

    function hienThiListSP(pageNo,pageSize,sortBy,sortOrder) {

        $('#pageSize').change(function () {
            var pageSize = $(this).val();
            hienThiListSP(pageNo,pageSize,sortBy,sortOrder);
        })
        $('.ad_masp').click(function () {
            hienThiListSP(pageNo,pageSize,"maSP",sortOrder);
        })
        $('.ad_tenSP').click(function () {
            hienThiListSP(pageNo,pageSize,"tenSP",sortOrder);
        })
        $('.ad_soLuong').click(function () {
            hienThiListSP(pageNo,pageSize,"soLuong",sortOrder);
        })
        $('.ad_giaBan').click(function () {
            hienThiListSP(pageNo,pageSize,"giaBan",sortOrder);
        })
        $('.ad_ghiChu').click(function () {
            hienThiListSP(pageNo,pageSize,"ghiChu",sortOrder);
        })
        $('.ad_maDM').click(function () {
            hienThiListSP(pageNo,pageSize,"maDM",sortOrder);
        })
        $('.ad_Size').click(function () {
            hienThiListSP(pageNo,pageSize,"size",sortOrder);
        })


        //Lấy tống số lượng sp rồi tạo pagi
        $.ajax({
            url: "/api/san-pham/lengthSP",
            type: "GET",
            success: function (totalItems) {
                var pagi = $('#paginationSP');
                pagi.empty()
                var itemsPage  = 5;//Số lượng item trong 1 trang
                var totalPages = Math.ceil(totalItems/itemsPage);//Tống số trang
                for (var i = 1; i <= totalPages-1; i++) {
                    pagi.append('<span class="page">' + i + '</span>');
                }
                $('.page').on('click', function(e) {
                    var pageNo = $(this).text();
                    $(this).attr('class', 'active')
                    hienThiListSP(pageNo,itemsPage,sortBy,sortOrder);
                })
            }
        })
        $.ajax({
            url: "/api/san-pham",
            type: "GET",
            data:{pageNo:pageNo,pageSize:pageSize,sortBy:sortBy,sortOrder:sortOrder},
            success: function (response) {
                var tbody = $('#SanPham_Table tbody');
                tbody.empty()
                response.forEach(function (item) {
                    var row = $('<tr></tr>');
                    row.append('<td class="ad_prodct">' + item.maSP + '</td>');
                    row.append('<td>' + item.tenSP + '</td>');
                    row.append('<td>' + item.soLuong + '</td>');
                    row.append('<td>' + item.giaBan + '</td>');
                    row.append('<td>' + item.ghiChu + '</td>');
                    row.append('<td>' + item.maDM + '</td>');
                    row.append('<td>' + item.size + '</td>');
                    tbody.append(row);
                });
                $('.ad_prodct').on('click', function(e){
                    var masp = $(this).text();
                    hienThiSPLenForm(masp)
                    $('#admin_product').get(0).scrollIntoView({ behavior: 'smooth' });
                })
            },
            error: function (xhr, status, error) {
                console.error("Error fetching product data:", error);
            }
        });
    }
    $('#order').click(function () {
        var order = $(this).html();
        if (order.indexOf('<i class="fa-solid fa-arrow-down-a-z"></i>') !== -1) {
            $(this).html('<i class="fa-solid fa-arrow-up-a-z"></i>');
            sortOrder = 'asc';
        } else if (order.indexOf('<i class="fa-solid fa-arrow-up-a-z"></i>') !== -1) {
            $(this).html('<i class="fa-solid fa-arrow-down-a-z"></i>');
            sortOrder = 'desc';
        }
        hienThiListSP(pageNo,pageSize,sortBy,sortOrder)
    })
    hienThiListSP();
    $('#anhSP').click(function () {
        $('#fileInput').click();
    })
    $('#fileInput').on('change', function() {
        var file = this.files[0];
        if (file) {
            var reader = new FileReader();
            reader.onload = function(e) {
                $('#anhSP').attr('src', e.target.result);
            }
            reader.readAsDataURL(file);
        }
    });
    $('#updateSP').click(function () {

        var file = $('#fileInput')[0].files[0];
        var formData = new FormData();

        var masp = $('#maSanPham').val();
        var tenSP = $('#tenSanPham').val();
        var danhMucSanPham = $('#danhMucSanPham').val();
        var Sl_SP = $('#Sl_SP').val();
        var donGiaBan = $('#donGiaBan').val();
        var size = $('#size').val();
        var ghiChu = $('#ghiChu').val();
        var anh ;

        if (!masp || !tenSP || !Sl_SP || !donGiaBan || !size || !ghiChu) {
            Swal.fire({
                icon: "error",
                title: "Oops...",
                text: "Something went wrong!",
            });
            return;
        }

        if (file) {
            anh = file.name;
        } else {
            anh = $('#anhSP').attr('src').split('/').pop();
        }
        alert(anh)
        var data = {
            maSP: masp,
            tenSP: tenSP,
            maDM: danhMucSanPham,
            soLuong: Sl_SP,
            giaBan: donGiaBan,
            size: size,
            ghiChu: ghiChu,
            anh: anh
        };

        $.ajax({
            url: "/api/san-pham/updateSP",
            type: "PUT",
            data: JSON.stringify(data),
            dataType: "json",
            contentType: "application/json",
            success: function (response) {
                alert(response);
                hienThiListSP();
            }
        })

        if (file) {
            formData.append('file', file)
            $.ajax({
                url: '/api/san-pham/upload-anh-sp',
                type: 'POST',
                data: formData,
                processData: false,
                contentType: false,
                success: function(response) {
                    alert("Thành công");
                },
                error: function(xhr, status, error) {
                    alert("Error uploading file: " + error);
                }
            });
        }
        e.preventDefault();
        window.location.reload();
        clearSP()
        hienThiListSP();
        $('#admin_product').get(0).scrollIntoView({ behavior: 'smooth' });
    });
    $('#clearSP').click(function () {
        clearSP()
        $('#admin_product').get(0).scrollIntoView({ behavior: 'smooth' });
    })
    $('#demo').click(function () {
        var fileInput = $('#fileInput')[0];
        var files = fileInput.files;

        if (files.length > 0) {
            var nameFile = files[0].name;
            alert(nameFile);
        } else {
            // Người dùng chưa chọn file nào
            alert("File chưa chọn: "+$('#anhSP').attr('src').split('/').pop());
        }
        // $.ajax({
        //     url: '/api/san-pham/upload-anh-sp',
        //     type: 'post',
        //     data: data,
        //     processData: false,
        //     contentType: false,
        //     // contentType: 'multipart/form-data',
        //     success: function(response) {
        //         alert("Thành công")
        //     },
        //     error: function(xhr, status, error) {
        //         alert("Error fetching product data:")
        //     }
        // });

    })
    $('#createSP').click(function () {
        var file = $('#fileInput')[0].files[0];
        var formData = new FormData();
        var anh ;
        if (file) {
            anh = file.name;
            formData.append('file', file)
            $.ajax({
                url: '/api/san-pham/upload-anh-sp',
                type: 'POST',
                data: formData,
                processData: false,
                contentType: false,
                success: function(response) {
                    alert("Thành công");
                },
                error: function(xhr, status, error) {
                    alert("Error uploading file: " + error);
                }
            });
        } else {
            anh = "noImage.png";
        }
        var data = {
            tenSP: $('#tenSanPham').val(),
            maDM:  $('#danhMucSanPham').val(),
            soLuong: $('#Sl_SP').val(),
            giaBan: $('#donGiaBan').val(),
            size: $('#size').val(),
            ghiChu: $('#ghiChu').val(),
            anh: anh
        };

        $.ajax({
            url: "/api/san-pham/adSP",
            type: "POST",
            data: JSON.stringify(data),
            dataType: "json",
            contentType: "application/json",
            success: function (response) {
                $('#admin_product').get(0).scrollIntoView({ behavior: 'smooth' });
                alert("Thêm thành công");
                hienThiListSP();
            },
            error: function (xhr, status, error) {
                console.error("Lỗi:", error);
                console.error("status:", status);
                console.error("xhr:", xhr);
                alert("Lỗi");
            }
        })

    })


});

