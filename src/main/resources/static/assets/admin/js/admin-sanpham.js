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
                var timestamp = new Date().getTime();
                var imgSrc = "https://res.cloudinary.com/dtnf47wll/image/upload/" + data.anh;
                $('#anhSP').attr('src', imgSrc);
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

    function hienThiListSP(pageNo, pageSize, sortBy, sortOrder) {
        // Fetch data from the server and update the UI
        $.ajax({
            url: `/api/san-pham?pageNo=${pageNo}&pageSize=${pageSize}&sortBy=${sortBy}&sortOrder=${sortOrder}`,
            type: "GET",
            success: function (data) {
                var tbody = $('#SanPham_Table tbody');
                tbody.empty(); // Clear existing data
                data.forEach(function (item) {

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
                // Attach click event to product ID for displaying details
                $('.ad_prodct').on('click', function(e){
                    var masp = $(this).text();
                    hienThiSPLenForm(masp);
                    $('#admin_product').get(0).scrollIntoView({ behavior: 'smooth' });
                });
            },
            error: function (xhr, status, error) {
                console.error("Error fetching product data:", error);
            }
        });

        // Update pagination
        $.ajax({
            url: "/api/san-pham/lengthSP",
            type: "GET",
            success: function (totalItems) {
                var pagi = $('#paginationSP');
                pagi.empty();
                var totalPages = Math.ceil(totalItems / pageSize);
                for (var i = 0; i < totalPages; i++) {
                    pagi.append('<span class="page">' + (i + 1) + '</span>');
                }
                $('.page').on('click', function (e) {
                    var newPageNo = $(this).text() - 1;
                    $(this).siblings().removeClass('active');
                    $(this).addClass('active');
                    hienThiListSP(newPageNo, pageSize, sortBy, sortOrder);
                });
            }
        });
    }

    var pageNo = 0;
    var pageSize = 5;
    var sortBy = "maSP";
    var sortOrder = ""; // Ascending or descending
    hienThiListDanhMucSanPham(function () {
        hienThiListSP(pageNo, pageSize, sortBy, sortOrder);
    });

    // Change event for page size
    $('#pageSize').change(function () {
        pageSize = $(this).val();
        hienThiListSP(pageNo, pageSize, sortBy, sortOrder);
    });

    // Click events for sorting
    $('.ad_masp').click(function () {
        sortBy = "maSP";
        hienThiListSP(pageNo, pageSize, sortBy, sortOrder);
    });
    $('.ad_tenSP').click(function () {
        sortBy = "tenSP";
        hienThiListSP(pageNo, pageSize, sortBy, sortOrder);
    });
    $('.ad_soLuong').click(function () {
        sortBy = "soLuong";
        hienThiListSP(pageNo, pageSize, sortBy, sortOrder);
    });
    $('.ad_giaBan').click(function () {
        sortBy = "giaBan";
        hienThiListSP(pageNo, pageSize, sortBy, sortOrder);
    });
    $('.ad_ghiChu').click(function () {
        sortBy = "ghiChu";
        hienThiListSP(pageNo, pageSize, sortBy, sortOrder);
    });
    $('.ad_maDM').click(function () {
        sortBy = "maDM";
        hienThiListSP(pageNo, pageSize, sortBy, sortOrder);
    });
    $('.ad_Size').click(function () {
        sortBy = "size";
        hienThiListSP(pageNo, pageSize, sortBy, sortOrder);
    });

    $('#order').click(function () {
        if (sortOrder === '') {
            $(this).html('<i class="fa-solid fa-arrow-up-a-z"></i>');
            sortOrder = 'asc';
        } else if (sortOrder === 'asc') {
            $(this).html('<i class="fa-solid fa-arrow-down-a-z"></i>');
            sortOrder = 'desc';
        } else {
            $(this).html('<i class="fa-solid fa-arrow-up-a-z"></i>');
            sortOrder = 'asc';
        }
        hienThiListSP(pageNo,pageSize,sortBy,sortOrder);
    });

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
    $('#updateSP').click(function (e) {

        var file = $('#fileInput')[0].files[0];
        var formData = new FormData();

        var masp = $('#maSanPham').val();
        var tenSP = $('#tenSanPham').val();
        var danhMucSanPham = $('#danhMucSanPham').val();
        var Sl_SP = $('#Sl_SP').val();
        var donGiaBan = $('#donGiaBan').val();
        var size = $('#size').val();
        var ghiChu = $('#ghiChu').val();
        var anh;

        if (!masp || !tenSP || !Sl_SP || !donGiaBan || !size || !ghiChu) {
            Swal.fire({
                icon: "error",
                title: "Oops...",
                text: "Vui lòng điền đầy đủ thông tin!",
            });
            return;
        }

        if (file) {
            anh = file.name;
        } else {
            anh = $('#anhSP').attr('src').split('/').pop();
        }

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
                hienThiListDanhMucSanPham(function (){

                });
                e.preventDefault();
            }
        });

        if (file) {
            formData.append('file', file);
            $.ajax({
                url: '/api/san-pham/upload-anh-sp',
                type: 'POST',
                data: formData,
                processData: false,
                contentType: false,
                success: function(response) {
                    alert("Tải ảnh lên thành công");
                    e.preventDefault();
                },
                error: function(xhr, status, error) {
                    alert("Lỗi khi tải lên file: " + error);
                }
            });

        }

        // clearSP();
        hienThiListDanhMucSanPham(function (){
            hienThiListSP(pageNo, pageSize, sortBy, sortOrder);
        });
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

    })
    $('#createSP').click(function () {
        // Gather input values
        var maSP = $('#maSanPham').val().trim();
        var tenSP = $('#tenSanPham').val().trim();
        var maDM = $('#danhMucSanPham').val().trim();
        var soLuong = $('#Sl_SP').val().trim();
        var giaBan = $('#donGiaBan').val().trim();
        var size = $('#size').val().trim();
        var ghiChu = $('#ghiChu').val().trim();
        var file = $('#fileInput')[0].files[0];
        var formData = new FormData();
        var anh;

        // Validation flag
        var isValid = true;

        // Reset previous error messages
        $('.error').remove();

        if (maSP.length > 0) {
            alert("Sản phẩm đã tồn tại")
            return;
        }

        // Validate each field and add error messages if necessary
        if (tenSP === "") {
            isValid = false;
            $('#tenSanPham').after('<span class="error">Tên sản phẩm không được để trống</span>');
        }

        if (maDM === "") {
            isValid = false;
            $('#danhMucSanPham').after('<span class="error">Danh mục sản phẩm không được để trống</span>');
        }

        var numberPattern = /^[0-9]+$/;
        if (!numberPattern.test(soLuong) || soLuong <= 0) {
            isValid = false;
            $('#Sl_SP').after('<span class="error">Số lượng phải là số dương</span>');
        }

        if (!numberPattern.test(giaBan) || giaBan <= 0) {
            isValid = false;
            $('#donGiaBan').after('<span class="error">Giá bán phải là số dương</span>');
        }

        if (size === "") {
            isValid = false;
            $('#size').after('<span class="error">Size không được để trống</span>');
        }

        if (file) {
            anh = file.name;
            formData.append('file', file)
        } else {
            anh = "noImage.png";
        }

        // If all inputs are valid, proceed with the AJAX requests
        if (isValid) {
            if (file) {
                $.ajax({
                    url: '/api/san-pham/upload-anh-sp',
                    type: 'POST',
                    data: formData,
                    processData: false,
                    contentType: false,
                    success: function (response) {
                        alert("Upload thành công");
                    },
                    error: function (xhr, status, error) {
                        alert("Error uploading file: " + error);
                    }
                });
            }

            var data = {
                tenSP: tenSP,
                maDM: maDM,
                soLuong: soLuong,
                giaBan: giaBan,
                size: size,
                ghiChu: ghiChu,
                anh: anh
            };

            $.ajax({
                url: "/api/san-pham/adSP",
                type: "POST",
                data: JSON.stringify(data),
                dataType: "json",
                contentType: "application/json",
                success: function (response) {
                    $('#admin_product').get(0).scrollIntoView({behavior: 'smooth'});
                    alert("Thêm thành công");
                    hienThiListDanhMucSanPham(function (){
                        hienThiListSP(pageNo, pageSize, sortBy, sortOrder);
                    });
                },
                error: function (xhr, status, error) {
                    console.error("Lỗi:", error);
                    console.error("status:", status);
                    console.error("xhr:", xhr);
                    alert("Lỗi");
                }
            });
        } else {
            alert("Vui lòng điền đầy đủ thông tin hợp lệ");
        }
    });

    $('img').on('error', function() {
        $(this).attr('src', '/assets/sanpham/noImage.png');
    });
});