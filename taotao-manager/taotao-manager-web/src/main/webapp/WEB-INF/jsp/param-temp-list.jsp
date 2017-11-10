<%--
  Created by IntelliJ IDEA.
  User: wuqinghua
  Date: 17/11/4
  Time: 下午8:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<table id="paramTempList" title="规格参数模版"></table>

<script type="text/javascript">
    $(function () {
        $("#paramTempList").datagrid({
            url: '${pageContext.request.contextPath}/param/temp/list',
            method: 'get',
            columns: [[
                {field: 'ck', checkbox: true},
                {field: 'id', title: 'ID', width: 60},
                {field: 'itemCatId', title: '商品类目ID', width: 80},
                {field: 'itemCatName', title: '商品类目名称', width: 80},
                {field: 'paramData', title: '规格(只显示分组名称)', width: 300},
                {field: 'created', title: '创建时间', formatter: TT.formatDateTime},
                {field: 'updated', title: '更新时间', formatter: TT.formatDateTime}
            ]],
            pagination: true,     //含有分页栏
            singleSelect: true,   //允许单行选中
            pageSize: 20,
            toolbar: [{
                text: '新增',
                iconCls: 'icon-add',
                handler: function () {
                    TT.createWindow({
                        url:'${pageContext.request.contextPath}/show/param-temp-add'
                    })
                }
            }, '-', {
                text: '编辑',
                iconCls: 'icon-edit'
            }, {
                text: '删除',
                iconCls: 'icon-cancel'
            }]
        })
    })
</script>