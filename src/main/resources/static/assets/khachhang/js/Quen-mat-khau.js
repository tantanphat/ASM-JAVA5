
$(document).ready(function(){
    // https://www.linkedin.com/in/atakangk/
//jQuery time
    var current_fs, next_fs, previous_fs; //fieldsets
    var left, opacity, scale; //fieldset properties which we will animate
    var animating; //flag to prevent quick multi-click glitches

function btnNext(btn) {

        if(animating) return false;
        animating = true;

        current_fs = $(btn).parent();
        next_fs = $(btn).parent().next();

        //activate next step on progressbar using the index of next_fs
        $("#progressbar li").eq($("fieldset").index(next_fs)).addClass("active");

        //show the next fieldset
        next_fs.show();
        //hide the current fieldset with style
        current_fs.animate({opacity: 0}, {
            step: function(now, mx) {
                //as the opacity of current_fs reduces to 0 - stored in "now"
                //1. scale current_fs down to 80%
                scale = 1 - (1 - now) * 0.2;
                //2. bring next_fs from the right(50%)
                left = (now * 50)+"%";
                //3. increase opacity of next_fs to 1 as it moves in
                opacity = 1 - now;
                current_fs.css({
                    'transform': 'scale('+scale+')',
                    'position': 'absolute'
                });
                next_fs.css({'left': left, 'opacity': opacity});
            },
            duration: 800,
            complete: function(){
                current_fs.hide();
                animating = false;
            },
            //this comes from the custom easing plugin
            easing: 'easeInOutBack'
        });

    }

    $(".previous").click(function(){
        if(animating) return false;
        animating = true;

        current_fs = $(this).parent();
        previous_fs = $(this).parent().prev();

        //de-activate current step on progressbar
        $("#progressbar li").eq($("fieldset").index(current_fs)).removeClass("active");

        //show the previous fieldset
        previous_fs.show();
        //hide the current fieldset with style
        current_fs.animate({opacity: 0}, {
            step: function(now, mx) {
                //as the opacity of current_fs reduces to 0 - stored in "now"
                //1. scale previous_fs from 80% to 100%
                scale = 0.8 + (1 - now) * 0.2;
                //2. take current_fs to the right(50%) - from 0%
                left = ((1-now) * 50)+"%";
                //3. increase opacity of previous_fs to 1 as it moves in
                opacity = 1 - now;
                current_fs.css({'left': left});
                previous_fs.css({'transform': 'scale('+scale+')', 'opacity': opacity});
            },
            duration: 800,
            complete: function(){
                current_fs.hide();
                animating = false;
            },
            //this comes from the custom easing plugin
            easing: 'easeInOutBack'
        });
    });
    function guiMailCode(email, callback) {
        var email = $('#email').val(); // Assuming email is defined somewhere
        $.ajax({
            url: "/api/khach-hang/mail-for-forgot-password",
            type: "GET",
            data: { email: email },
            success: function(response) {
                alert(response);
                $('#guiEmail').attr('disabled', true);
                if (typeof callback === 'function') {
                    callback();
                }
            },
            error: function(xhr, status, error) {
                if (xhr.status === 400) {
                    alert(xhr.responseText);
                    $('#email').val(null);
                } else {
                    alert("An unexpected error occurred. Please try again later.");
                }
            }
        });
    }

    function nextStep() {
        btnNext('#guiEmail');
        $('#guiEmail').removeAttr('disabled');
    }

    $('#guiEmail').click(function(){
        var email = $('#email').val();
        var emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (!emailRegex.test(email)) {
            alert("Email không hợp lệ");
            return;
        }
        $(this).attr('class','next');

        guiMailCode(email, nextStep);

    });

    $('#btnMAXN').click(function(){
        var MaXN = $('#MaXN').val();
        $.ajax({
            url: "/api/khach-hang/conform-code-password",
            type: "GET",
            success: function(response) {
                var code= response;
                if (MaXN==code) {
                    btnNext('#btnMAXN');
                } else {
                    alert("Mã xác nhận không đúng");
                }
            },
            error: function(error) {
                alert("Error: " + error)
            }
        });
    })

    $('#doiMK').click(function(){
        var email = $('#email').val();
        var newPass = $('#newPass').val();
        var confNewPass = $('#confNewPass').val();

        if (newPass != confNewPass) {
            alert("Mật khẩu không trùng khớp");
            return;
        }

        $.ajax({
            url: "/api/khach-hang/forgor-password-new-password",
            type: "PUT",
            data: {email:  email, pass: newPass},
            success: function(response) {
                alert(response);
                window.location.href = "http://localhost:8080/Dang-nhap";
            },
            error: function(xhr, status, error) {
                console.log(status)
                console.log(error)
                if (xhr.status === 400) {
                    alert(xhr.responseText);
                    $('#email').val(null);
                } else {
                    alert("An unexpected error occurred. Please try again later.");
                }
            }
        });
    })

});

