function doLike(p_id, u_id) {
    console.log(p_id + " , " + u_id);
    
    const d={
        p_id : p_id,
        u_id : u_id,
        operation : 'like'
    }
    
    $.ajax({
        url:"LikeServlet",
        data:d,
        success: function (data, textStatus, jqXHR) {
            if(data.trim() == 'true'){
                let c=$('.like-counter').html();
                c++;
                $('.like-counter').html(c);
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log(data)
        }
    })
}
