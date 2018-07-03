<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/6/25
  Time: 10:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>显示页面</title>
    <style>
        li{
            float: left;
            margin-right: 12px;
            list-style-type: none;
        }
    </style>


</head>
<body>
<table>

        <thead>
        <tr>
        <td>编号</td>
        <td>员工姓名</td>
        <td>员工地址</td>
        <td>员工电话</td>
        <td>部门名称</td>
        <td>操作</td>
        </tr>
        </thead>
        <tbody></tbody>
        <tfoot id="PageNum"></tfoot>

</table>
</body>
<script src="/js/jquery-1.8.3.js"></script>
<script type="text/javascript">
    function getdel(id){
        $.post( "/emoloyee/delInfo?id="+id,function (result) {
             alert(id);
            if (result=="ok"){
                alert("删除成功！")
                location.href= "/emoloyee/list/index";
            } else {
                alert("删除失败！");
                location.href= "/emoloyee/list/index";
            }
        })
    }
    $(function() {
        page(1);
    });
    function page(pageNo) {
        $.getJSON(
                "/emoloyee/list?index="
                + pageNo,
                function(result) {
                    var content = "";
                    var li = "<ul>";
                    var number = 0;
                    var count = 0; // 信息数量
                    if (result.pageNo > 1) {
                        li += "<li><a href='javascript:page(" + 1
                            + ")'>首页</a>" + "</li>"
                            + "<li><a href='javascript:page("
                            + (result.pageNo - 1) + ")'>上一页</a>"
                            + "</li>";
                    }
                    $
                        .each(
                            result.infolist,
                            function(i, e) {
                                count++; // 累加信息数量
                                content += "<tr>"
                                    + "<td>"
                                    + e.id
                                    + "</td>"
                                    + "<td>"
                                    + e.ename
                                    + "</td>"
                                    + "<td>"
                                    + e.eaddress
                                    + "</td>"
                                    + "<td>"
                                    + e.etelephone
                                    + "</td>"
                                    + "<td>"
                                    + e.dname
                                    + "</td>"
                                    + "<td>"
                                    + "<a href='javascript:void(0)' onclick='getdel("+e.id+")'>删除</a>"
                                    + "</td>"
                                    + "</tr>";
                            });
                    for (var i = 1; i <= result.pageCount; i++) {
                        li += "<li><a href='javascript:page(" + i
                            + ")'>" + i + "</a></li>";
                    }

                    if (result.pageNo < result.pageCount) {
                        li += "<li><a href='javascript:page("
                            + (result.pageNo + 1) + ")'>下一页</a>"
                            + "</li>"
                            + "<li><a href='javascript:page("
                            + result.pageCount + ")'>尾页</a>"
                            + "</li>";
                    }
                    li += "</ul>";
                    $("#PageNum").empty();
                    $("#PageNum").html(li);
                    $("tbody").empty();
                    $("tbody").html(content);
                });
    }
</script>
</html>
