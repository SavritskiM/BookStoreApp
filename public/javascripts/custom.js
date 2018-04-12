$(function() {
    $("button#delete-book").click(function () {
        var url = $(this).data("url");
        var rUrl = $(this).data("r-url");
        sendDeleteRequest(url, rUrl);
    });
});

function sendDeleteRequest(url, rUrl) {
    $.ajax({
        url: url,
        method: "DELETE",
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        success: function () {
            window.location = rUrl;
        },
        error: function () {
            window.location.reload();
        }
    });
}
