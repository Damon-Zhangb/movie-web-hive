$(function () {
    $.ajax({
        url: "/movies",
        type: "GET",
        success: function (result) {
            //1.解析并显示电影数据
            get_movie(result);
        }
    })
})

function saveToHbase(name) {
    $.ajax({
        url: "/save",
        type: "POST",
        data: "name=" + name,
        success: function (result) {
        }
    })
}


$("#url1").click(function () {
    var name = $("#name1").text();
    saveToHbase(name)
})
$("#url2").click(function () {
    var name = $("#name2").text();
    saveToHbase(name)
})
$("#url3").click(function () {
    var name = $("#name3").text();
    saveToHbase(name)
})
$("#url4").click(function () {
    var name = $("#name4").text();
    saveToHbase(name)
})
$("#url5").click(function () {
    var name = $("#name5").text();
    saveToHbase(name)
})
$("#url6").click(function () {
    var name = $("#name6").text();
    saveToHbase(name)
})
$("#url7").click(function () {
    var name = $("#name7").text();
    saveToHbase(name)
})
$("#url8").click(function () {
    var name = $("#name8").text();
    saveToHbase(name)
})

function get_movie(result) {
    movies = result.extend.movie;
    $("#url1").attr("href", movies[0].url)
    $("#url2").attr("href", movies[1].url)
    $("#url3").attr("href", movies[2].url)
    $("#url4").attr("href", movies[3].url)
    $("#url5").attr("href", movies[4].url)
    $("#url6").attr("href", movies[5].url)
    $("#url7").attr("href", movies[6].url)
    $("#url8").attr("href", movies[7].url)


    $("#name1").append(movies[0].name)
    $("#name2").append(movies[1].name)
    $("#name3").append(movies[2].name)
    $("#name4").append(movies[3].name)
    $("#name5").append(movies[4].name)
    $("#name6").append(movies[5].name)
    $("#name7").append(movies[6].name)
    $("#name8").append(movies[7].name)

    $("#author1").append(movies[0].author)
    $("#author2").append(movies[1].author)
    $("#author3").append(movies[2].author)
    $("#author4").append(movies[3].author)
    $("#author5").append(movies[4].author)
    $("#author6").append(movies[5].author)
    $("#author7").append(movies[6].author)
    $("#author8").append(movies[7].author)

}


