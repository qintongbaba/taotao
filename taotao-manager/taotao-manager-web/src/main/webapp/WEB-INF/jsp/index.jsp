<%--
  Created by IntelliJ IDEA.
  User: wuqinghua
  Date: 17/10/19
  Time: 下午1:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>淘淘后台管理系统</title>
    <%--添加css--%>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/easyui/themes/icon.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/css/style.css">
</head>


<body class="easyui-layout">
<%--左面菜单栏--%>
<div data-options="region:'west',title:'菜单',split:true" style="width: 220px;">
    <ul id="menu" class="easyui-tree" style="margin-top: 10px;margin-left: 10px;">
    </ul>
</div>

<%--中间内容--%>
<div data-options="region:'center',title:''">
    <div id="tabs" class="easyui-tabs">
    </div>
</div>


<%--添加js--%>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/easyui/jquery.min.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
<script type="text/javascript">
    var treeData = [{
        "id": 1,
        "text": "商品管理",
        "children": [
            {
                "id": 2,
                "text": "新增商品",
                "attributes": {
                    "url": "${pageContext.request.contextPath}/show/item-add"
                }
            }, {
                "id": 3,
                "text": "查询商品",
                "attributes": {
                    "url": "${pageContext.request.contextPath}/show/item-list"
                }
            }, {
                "id": 4,
                "text": "规格参数模版",
                "attributes": {
                    "url": "${pageContext.request.contextPath}/show/param-temp-list"
                }
            }
        ]
    }, {
        "id": 5,
        "text": "内容管理",
        "children": [
            {
                "id": 6,
                "text": "内容分类管理",
                "attributes": {
                    "url": "${pageContext.request.contextPath}/show/content-category"
                }
            }, {
                "id": 7,
                "text": "内容管理",
                "attributes": {
                    "url": "${pageContext.request.contextPath}/show/content"
                }
            }
        ]
    }];
    $(function () {
        //加载菜单栏
        $("#menu").tree({
            data: treeData,
            onClick: function (node) {
                //如果是叶子节点
                if ($("#menu").tree("isLeaf", node.target)) {
                    var tabs = $("#tabs");
                    var tab = tabs.tabs("getTab", node.text);
                    if (tab) {  //如果改tab已存在选中
                        tabs.tabs("select", node.text);
                    } else {
                        //新建一个tab
                        tabs.tabs("add", {
                            title: node.text,
                            closable: true,
                            href: node.attributes.url
                        });
                    }
                }
            }
        })
    })
</script>
</body>
</html>
