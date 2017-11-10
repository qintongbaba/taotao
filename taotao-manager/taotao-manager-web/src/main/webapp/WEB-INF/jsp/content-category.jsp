<%--
  Created by IntelliJ IDEA.
  User: wuqinghua
  Date: 17/11/8
  Time: 下午10:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<div style="padding-top: 10px;padding-left: 10px;margin: 10px;">
    <ul id="categoryTree" class="easyui-tree"></ul>
</div>

<div id="mm" class="easyui-menu" style="width:120px;" data-options="onClick:menuHandler">
    <div data-options="iconCls:'icon-add',name:'add'">添加</div>
    <div data-options="iconCls:'icon-edit',name:'rename'">重命名</div>
    <div class="menu-sep"></div>
    <div data-options="iconCls:'icon-remove',name:'delete'">删除</div>
</div>


<script type="application/javascript">

    $(function () {
        $("#categoryTree").tree({
            url: "${pageContext.request.contextPath}/content/category/list",
            animate: true,
            method: 'GET',
//            添加右击事件
            onContextMenu: function (e, node) {
                e.preventDefault();
                // 查找节点
                $('#categoryTree').tree('select', node.target);
                // 显示快捷菜单
                $('#mm').menu('show', {
                    left: e.pageX,
                    top: e.pageY
                });
            },

//            编辑完成后调用
            onAfterEdit: function (node) {
                var _tree = $(this);
                if (node.id == 0) { //新增节点
                    console.log(node)
                    $.post("${pageContext.request.contextPath}/content/category/create",
                        {"parentId": node.parentId, "name": node.text},
                        function (data) {
                            if (data.status == 200) {
                                _tree.tree("update", {
                                    target: node.target,
                                    id: data.data.id
                                });
                            } else {
                                $.messager.alert("提示", "创建分类<" + node.text + ">失败!")
                            }
                        });
                } else { //重命名节点
                    $.post("${pageContext.request.contextPath}/content/category/rename",
                        {"id": node.id, "newName": node.text}, function (data) {
                            if (data.status == 200) {
                                $.messager.alert("提示", "重命名分类<" + node.text + ">成功!")
                            } else {
                                $.messager.alert("提示", "重命名分类<" + node.text + ">失败!")
                            }
                        })
                }
            }
        });
    });

    //    右键事件
    function menuHandler(item) {
        var tree = $("#categoryTree");
        var node = tree.tree("getSelected");
        if (item.name == 'add') {
            tree.tree('append', {
                parent: (node ? node.target : null),
                data: [{
                    "id": 0,
                    "text": "新建分类",
                    "state": "open",
                    parentId: node.id
                }]
            });

            var _node = tree.tree('find', 0);
            tree.tree("select", _node.target).tree("beginEdit", _node.target);
        } else if (item.name == 'rename') {
            tree.tree("beginEdit", node.target);
        } else {
            $.messager.confirm('确认', '确认删除名为<' + node.text + '>的分类么？', function (r) {
                if (r) {
                    $.post("${pageContext.request.contextPath}/content/category/delete",
                        {"id": node.id}, function (data) {
                            if (data.status == 200) {
                                tree.tree("remove", node.target);
                                $.messager.alert("提示", "删除分类<" + node.text + ">成功!")
                            } else {
                                $.messager.alert("提示", "删除分类<" + node.text + ">失败!" + data.msg)
                            }
                        });
                }
            })
        }
    }

</script>
