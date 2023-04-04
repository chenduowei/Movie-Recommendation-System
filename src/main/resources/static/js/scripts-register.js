$(document).ready(function () {
    $("form").on("submit", function (event) {
        let isValid = true;
        const username = $("#username");
        const password = $("#password");

        // 验证用户名
        if (username.val().trim().length < 3) {
            username.addClass("is-invalid");
            isValid = false;
        } else {
            username.removeClass("is-invalid");
        }

        // 验证密码
        if (password.val().trim().length < 6) {
            password.addClass("is-invalid");
            isValid = false;
        } else {
            password.removeClass("is-invalid");
        }
        if (!isValid) {
            event.preventDefault();
        }
    });
});
