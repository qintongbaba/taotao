<%--
  Created by IntelliJ IDEA.
  User: wuqinghua
  Date: 17/10/19
  Time: 下午5:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<table id="itemList" title="商品列表"></table>

<script type="text/javascript">

    <%--加载商品列表--%>
    $(function () {
        $("#itemList").datagrid({
            url: '${pageContext.request.contextPath}/item/list',
            method: 'get',
            columns: [[
                {field: 'ck', checkbox: true},
                {field: 'id', title: '商品ID', align: 'center', width: 60},
                {field: 'title', title: '商品标题', align: 'center', width: 200},
                {field: 'catName', title: '叶子类目', align: 'center', width: 100},
                {field: 'sellPoint', title: '卖点', align: 'center', width: 250},
                {
                    field: 'price',
                    title: '价格',
                    align: 'center',
                    width: 70,
                    formatter: TT.formatPrice
                },
                {field: 'num', title: '库存数量', align: 'center', width: 70},
                {field: 'barcode', title: '条形码', align: 'center', width: 100},
                {field: 'statusDesc', title: '状态', align: 'center', width: 60},
                {
                    field: 'created', title: '创建时间', align: 'center',
                    width: 130, formatter: TT.formatDateTime
                },
                {
                    field: 'updated',
                    title: '更新时间',
                    align: 'center',
                    width: 130,
                    formatter: TT.formatDateTime
                }
            ]],
            pagination: true,     //含有分页栏
            singleSelect: false,   //允许单行选中
            pageSize: 20,
            toolbar: [{           //工具栏
                text: '新增',
                iconCls: 'icon-add',
                handler: function () {
                    alert('还没有实现!');
                }
            }, '-', {
                text: '编辑',
                iconCls: 'icon-edit'
            }, {
                text: '删除',
                iconCls: 'icon-cancel'
            }, {
                text: '上架',
                iconCls: 'icon-remove'
            }, {
                text: '下架',
                iconCls: 'icon-remove'
            }]
        })
    })
</script>
