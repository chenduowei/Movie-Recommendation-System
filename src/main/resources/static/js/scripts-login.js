$(document).ready(function () {
    $("#loginForm").on("submit", function (event) {
        if (!this.checkValidity()) {
            event.preventDefault();
            event.stopPropagation();
        }
        this.classList.add("was-validated");
    });
});
