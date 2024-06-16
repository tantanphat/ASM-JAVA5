$(document).ready(function() {
    function updateCountCart() {
        $.ajax({
            type: "GET",
            url: "/api/countListCart",
            success: function(response) {
                var totalCartItems = response; // Số lượng trong cart
                // Hiển thị trong scss .shoppingcart phần content
                $('#totalCartItems').text("Số lượng sản phẩm: "+totalCartItems)
                console.log("totalCart: " + totalCartItems);
                $(".header-bottom .header-right .shopping-card").attr("data-total", totalCartItems);
            },
            error: function(xhr, status, error) {
                console.log(error);
            }
        });
    }
    //Thêm sản phẩm vào giỏ hàng
    function updateCartTable() {
        $.ajax({
            type: "GET",
            url: "/api/getListCart",
            contentType: "application/json",
            success: function(response) {

                var tbody = $('#cartTable tbody');
                tbody.empty();
                var totalSum = 0;
                response.forEach(function(item) {
                    var row = $('<tr></tr>');
                    row.append('<td><img src="' + item.anh + '" alt="Product Image" width="50"></td>');
                    row.append('<td>' + item.tenSP + '</td>');
                    row.append('<td>' + item.size + '</td>');
                    row.append('<td ><div class="number-control">' +
                        '<div class="minusCart number-left" href="#" data-masp="' + item.maSP + '" data-size="' + item.size + '"></div>' +
                        '<div class="number-quantity">' + item.quantity + '</div>' +
                        '<div class=" addCart number-right"  data-masp="' + item.maSP + '" data-size="' + item.size + '"></div>' +
                        '</div></td>');
                    row.append('<td>' + item.giaBan + '</td>');
                    row.append('<td>' + (item.giaBan * item.quantity) + '</td>');
                    row.append('<td><button  class="btn border deleteCart" data-masp="' + item.maSP + '" data-size="' + item.size + '" >Remove</button></td>');
                    tbody.append(row);
                    totalSum += item.giaBan * item.quantity;
                    var maSP = item.maSP;
                    var size = item.size;
                    if ( item.quantity == 0){
                        $.ajax({
                            type: "GET",
                            url: "/cart/remove",
                            data: { maSP: maSP, size: size },
                            success: function(response) {
                                updateCartTable();
                            },
                            error: function(xhr, status, error) {
                                console.log(error);
                            }
                        });
                    }

                });
                $('#totalSum').text(totalSum);
            },
            error: function(xhr, status, error) {
                console.log(error); // Log the error for debugging
            }
        });
    }

    //Thêm sp vào cart
    $("#addToCart").click(function(event) {
        event.preventDefault(); // Ngăn chặn việc gửi form theo cách thông thường
        // Lấy giá trị từ các phần tử trong form
        var maSP = $("#maSP").val();
        var tenSP = $("#tenSP").text();
        var size = $("#sizeSelect").val();
        var soLuong = $("#quantityInput").val();
        var anh = $("#anh").attr("src");
        var giaBan = $("#giaBan").text();

        var formData = {
            maSP: maSP,
            tenSP: tenSP,
            giaBan: giaBan,
            quantity: soLuong,
            anh: anh,
            size: size,
        };
        console.log(formData);
        $.ajax({
            type: "POST",
            url: "/api/cart/add",
            data: JSON.stringify(formData), // Convert formData to JSON string
            contentType: "application/json", // Set the content type to JSON
            success: function(response) {
                // Xử lý phản hồi từ server
                updateCountCart();
                updateCartTable()
                alert("Sản phẩm đã được thêm vào giỏ hàng!");
            },
            error: function(xhr, status, error) {
                // Xử lý lỗi
                alert("Đã xảy ra lỗi khi thêm sản phẩm vào giỏ hàng: " + error);
            }
        });
    });

    //Đổ thông tin sản phẩm
    var url = new URL(window.location.href);
    var maSP = url.searchParams.get("maSP");
    $.ajax({
        type: "GET",
        url: "/api/san-pham/c",
        data: { maSP: maSP }, 
        contentType: "application/json",
        success: function(response) {
            console.log(response);
            var sanPham = response;

            $("#maSP").val(sanPham.maSP);
            $("#tenSP").text(sanPham.tenSP);
            $("#giaBan").text(sanPham.giaBan);
            // $("#anh").attr("src", "https://res.cloudinary.com/dtnf47wll/image/upload/" + sanPham.anh);
            $("#anh").attr("src", "/assets/sanpham/" + sanPham.anh);
            $('#ghiChu').text(sanPham.ghiChu);
        },
        error: function(xhr, status, error) {
            console.log(error);
        }
    });

    // Xóa sản phẩm khỏi giỏ hàng
    $('#cartTable').on('click', 'button.deleteCart', function(event) {
        event.preventDefault();
        // var maSP = $(this).attr('href').split('maSP=')[1].split('&')[0];
        // var size = $(this).attr('href').split('size=')[1];
        var maSP = $(this).data('masp');
        var size = $(this).data('size');
        $.ajax({
            type: "GET",
            url: "/cart/remove",
            data: { maSP: maSP, size: size },
            success: function(response) {
                updateCountCart();
                updateCartTable();
                
            },
            error: function(xhr, status, error) {
                console.log(error);
            }
        });
    });

    // Trừ số lượng sản phẩm trong cart - 1
    $('#cartTable').on('click', 'div.minusCart', function(event) {
        event.preventDefault();

        var maSP = $(this).data('masp');
        var size = $(this).data('size');

        $.ajax({
            type: "GET",
            url: "/cart/minus",
            data: { maSP: maSP, size: size },
            success: function(response) {
                updateCountCart();
                
                updateCartTable();
            },
            error: function(xhr, status, error) {
                console.log(error);
            }
        });
    });

    //Số lượng sp + 1
    $('#cartTable').on('click', 'div.addCart', function(event) {
        event.preventDefault();
        var maSP = $(this).data('masp');
        var size = $(this).data('size');
        $.ajax({
            type: "GET",
            url: "/cart/plus",
            data: { maSP: maSP, size: size},
            success: function(response) {
                updateCountCart();
                
                updateCartTable();
            },
            error: function(xhr, status, error) {
                console.log(error);
            }
        });
    });


    updateCountCart();
    
    updateCartTable();

});

