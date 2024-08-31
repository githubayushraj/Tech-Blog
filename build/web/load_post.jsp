<%@page import="com.tech.entite.Users"%>
<%@page import="com.tech.dao.LikeDao"%>
<%@page import="java.util.List"%>
<%@page import="com.tech.entite.Post"%>
<%@page import="com.tech.helper.Connector"%>
<%@page import="com.tech.dao.PostDao"%>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">
<script src="js/myJs.js" type="text/javascript"></script>
<div class="row">
    <%
        PostDao dao = new PostDao(Connector.getConnection());
        int cid = Integer.parseInt(request.getParameter("cid"));
        List<Post> posts = (cid == 0) ? dao.getAllPost() : dao.getAllPostByCatId(cid);
        
        if (posts == null || posts.isEmpty()) {
            out.println("<h3 class='display-3 text-center'>No Post In This Category...</h3>");
        } else {
            for (Post p : posts) {
    %>
    <div class="col-md-4">
        <div class="card">
            <img src="blog_pics/<%= p.getPic() %>" class="card-img-top" alt="Post Image">
            <div class="card-body">
                <b><%= p.getTitle() %></b>
                <p><%= p.getContent() != null ? p.getContent() : "No content available" %></p>
            </div>
            <div class="footer primary-background text-center mb-2">
                <%
                    LikeDao ld=new LikeDao(Connector.getConnection());
                    Users user=(Users)session.getAttribute("currentuser");
                %>
  
                <a href="#" onclick="doLike(<%=p.getPid()%>,<%=user.getId()%>)" class="btn btn-outline-primary"><i class="fas fa-thumbs-up"></i> <span class="like-counter"><%=ld.countLike(p.getPid())%></span></a>
                <a href="Read_more.jsp?post_id=<%= p.getPid() %>" class="btn btn-outline-primary">Read more</a>
                <a href="#" class="btn btn-outline-primary"><i class="far fa-comment"></i> <span>0</span></a>
            </div>
        </div>
    </div>
    <% 
            } 
        } 
    %>
</div>
