function post() {
    var questionId = $("#question_id").val();
    var comment_context = $("#comment_context").val();
    $.ajax({
        type: "POST",
        url: "/comment",
        contentType: "application/json",
        data: JSON.stringify({
            "parentId": questionId,
            "content": comment_context,
            "type": 1
        }),
        success: function (response) {
            if (response.code == 200) {
                $("#comment_section").hide();
            } else {
                if (response.code = 2003) {
                    var isAccepted = confirm(response.message);
                    if(isAccepted==true){
                        window.open("https://github.com/login/oauth/authorize?client_id=3a24a3d41e782fc5cf88&redirect_url=http://localhost:8887/callback&scope=user&state=1")
                        window.localStorage.setItem("closable",true)
                    }
                } else {
                    alert(response.message);

                }
            }
            /* console.log(response)*/
        },
        dataType: "json"
    });
}