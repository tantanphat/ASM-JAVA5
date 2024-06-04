$(document).ready(function() {
    $('#notAuth').click(function(e) {
        e.preventDefault();
        Swal.fire({
            title: "Bạn chưa đăng nhập",
            text: "Vui lòng đăng nhập để mua sắm",
            icon: "question"
        });
        window.setTimeout(function() {
            window.location.href = "/Dang-nhap";
        }, 1000);
    });
});