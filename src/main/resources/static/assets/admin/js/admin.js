$(document).ready(function(){
   if ( window.location.pathname === "/admin/report/nhan-vien") {
       $("#linkNV").addClass("active");
   } else {
       $("#linkNV").removeClass("active");
   }

    if ( window.location.pathname === "/admin/report/doanh-thu") {
        $("#linkDT").addClass("active");
    } else {
        $("#linkDT").removeClass("active");
    }

    if ( window.location.pathname === "/admin/report/san-pham") {
        $("#linkSP").addClass("active");
    } else {
        $("#linkSP").removeClass("active");
    }
});



