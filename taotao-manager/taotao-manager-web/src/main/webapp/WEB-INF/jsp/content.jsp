<%--
  Created by IntelliJ IDEA.
  User: wuqinghua
  Date: 17/11/9
  Time: 上午8:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="easyui-panel" title="Nested Panel"
     data-options="width:'100%',minHeight:500,noheader:true,border:false" style="padding:10px;">
    <div id="content" class="easyui-layout" data-options="fit:true">
        <div data-options="region:'west',split:false" style="width: 250px;padding:
        5px">
            <ul id="category_tree" class="easyui-tree"></ul>
        </div>
        <div data-options="region:'center'" style="padding: 5px">
            <table id="contentTable"></table>
        </div>
    </div>
</div>

<script type="application/javascript">

    $(function () {

        var tree = $("#category_tree");
        var dataGrid = $("#contentTable");
        tree.tree({
            url: "${pageContext.request.contextPath}/content/category/list",
            animate: true,
            method: 'GET',
            onClick: function (node) {
                if (tree.tree('isLeaf', node.target)) {
                    dataGrid.datagrid('reload', {
                        categroyId: node.id
                    })
                }
            }
        });

        dataGrid.datagrid({
            url: '${pageContent.request.contextPath}/content/query/list',
            queryParams: {categroyId: 0},
            method: 'GET',
            columns: [[
                {field: 'ck', checkbox: true},
                {field: 'id', title: 'ID', width: 50},
                {field: 'title', title: '内容标题', width: 120},
                {field: 'subTitle', title: '内容子标题', width: 100},
                {field: 'titleDesc', title: '内容描述', width: 120},
                {field: 'url', title: '内容连接', width: 60},
                {field: 'pic', title: '图片', width: 50},
                {field: 'pic2', title: '图片2', width: 50},
                {field: 'created', title: '创建日期', width: 130, formatter: TT.formatDateTime},
                {field: 'updated', title: '更新时间', width: 130, formatter: TT.formatDateTime}
            ]],
            pagination: true, singleSelect: false, pageSize: 20,
            toolbar: [{
                iconCls: 'icon-add',
                text: '新增',
                handler: function () {

                    TT.createWindow({
                        title: '内容新增',
                        height: '90%',
                        width: '70%',
                        url: '${pageContext.request.contextPath}/show/content-add'
                    })
                }
            }, {
                iconCls: 'icon-edit',
                text: '编辑',
                handler: function () {
                }
            }, "-", {
                iconCls: 'icon-remove',
                text: '删除',
                handler: function () {
                }
            }]
        });
    });
</script>