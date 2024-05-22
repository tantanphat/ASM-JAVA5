// var loginBtn = document.getElementById("Log-in-btn");
// var signupBtn = document.getElementById("Sign-up-btn");
// var logoutBtn = document.getElementById("Log-out-btn");
// var userDropdown = document.getElementById("userAvatar");

// // Hiển thị form đăng nhập và đăng ký, ẩn form đăng xuất và dropdown ban đầu
// loginBtn.addEventListener("click", function () {
//   loginBtn.classList.add("d-none");
//   signupBtn.classList.add("d-none");
//   logoutBtn.classList.remove("d-none");
//   userDropdown.classList.remove("d-none");
// });

// // Sự kiện cho nút log out
// logoutBtn.addEventListener("click", function () {
//   loginBtn.classList.remove("d-none");
//   signupBtn.classList.remove("d-none");
//   //  logoutBtn.classList.add('d-none');
//   userDropdown.classList.add("d-none");
// });

// Đảm bảo bạn đã khai báo ứng dụng AngularJS trước đó
var app = angular.module("myapp", []);

app.controller("myProduct", function ($scope) {
  // Khai báo các biến
  $scope.loginBtn = true;
  $scope.signupBtn = true;
  $scope.logoutBtn = false;
  $scope.userDropdown = false;

  // Hiển thị form đăng nhập và đăng ký, ẩn form đăng xuất
  $scope.login = function () {
    $scope.loginBtn = false;
    $scope.signupBtn = false;
    $scope.logoutBtn = true;
    $scope.userDropdown = true;
  };

  // Sự kiện cho nút log out
  $scope.logout = function () {
    $scope.loginBtn = true;
    $scope.signupBtn = true;
    $scope.logoutBtn = false;
    $scope.userDropdown = false;
  };
});

//Tab cho form signin
var text_sign = document.getElementById("nav-signup-tab");
var text_log = document.getElementById("nav-login-tab");

text_sign.addEventListener("click", function () {
  text_sign.classList.add("shadow-lg", "fw-bolder", "fs-3");
  text_sign.classList.remove("opacity-50");
  text_log.classList.remove("shadow-lg", "fs-3");
  text_log.classList.add("opacity-50", "fs-3");
});

text_log.addEventListener("click", function () {
  text_log.classList.add("shadow-lg", "fw-bolder", "fs-3");
  text_log.classList.remove("opacity-50");
  text_sign.classList.remove("shadow-lg", "fs-3");
  text_sign.classList.add("opacity-50", "fs-3");
});
