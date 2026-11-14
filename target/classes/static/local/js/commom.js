$(function () {
    let url = window.location.href;
    $("#btnFresh").click(function () {
        window.location.href = url;
    })

    $("#btngoBack").click(function () {
        window.location.href ="http://localhost:8090/login/flowers/flowersList";
    })

    $("tr").hover(function () {
            $(this).addClass("trbg")
        }
        ,
        function () {
            $(this).removeClass("trbg")
        })
})