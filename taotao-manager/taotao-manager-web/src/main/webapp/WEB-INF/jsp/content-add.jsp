<%--
  Created by IntelliJ IDEA.
  User: wuqinghua
  Date: 17/11/9
  Time: 下午1:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="${pageContext.request.contextPath}/kindeditor/themes/default/default.css"
      type="text/css" rel="stylesheet">
<style>
    #contentAddForm > div {
        padding-top: 10px;
    }
</style>
<div style="padding: 10px;">
    <form id="contentAddForm">
        <input type="hidden" name="categoryId"/>
        <div>
            <label for="title">内容标题&nbsp;&nbsp;&nbsp;:</label>
            <input id="title" type="text" class="easyui-textbox" name="title" style="width: 280px;"
                   data-options="required:true">
        </div>

        <div>
            <label for="subTitle">内容子标题:</label>
            <input id="subTitle" type="text" class="easyui-textbox" name="subTitle"
                   style="width: 280px;">
        </div>

        <div>
            <label for="titleDesc">内容描述&nbsp;&nbsp;&nbsp;:</label>
            <input id="titleDesc" type="text" class="easyui-textbox" name="titleDesc"
                   style="width: 280px;height:60px;"
                   data-options="multiline:true,validType:'length[0,150]'">
        </div>

        <div>
            <label for="url">URL&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:</label>
            <input id="url" type="text" class="easyui-textbox" name="url" style="width: 280px;">
        </div>

        <div>
            <label for="pic">图片&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:</label>
            <a id="pic" href="javascript:void(0);" class="easyui-linkbutton onePicUpload">图片上传</a>
            <input type="hidden" name="pic"/>
        </div>

        <div>
            <label for="pic">图片2&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:</label>
            <a id="pic2" href="javascript:void(0);"
               class="easyui-linkbutton onePicUpload">图片上传</a><input type="hidden" name="pic2"/>
        </div>

        <div>
            <label>内容&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:</label>
            <textarea style="width:800px;height:300px;visibility:hidden;" name="content"/>
        </div>

    </form>

    <div style="padding:5px">
        <a href="javascript:void(0)" class="easyui-linkbutton"
           onclick="contentAddPage.submitForm()">提交</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="contentAddPage.clearForm()">重置</a>
    </div>

</div>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/kindeditor/kindeditor-all-min.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/kindeditor/lang/zh_CN.js"></script>
<script type="application/javascript">

    var contentEditor;
    $(function () {
        //初始化内容描述福客户端
        contentEditor = TT.createEditor("#contentAddForm [name=content]");
        //初始化图片上传
        TT.initOnePicUpload();
    });


    var contentAddPage = {
        submitForm: function () {
            if (!$('#contentAddForm').form('validate')) {
                $.messager.alert('提示', '表单还未填写完成!');
                return;
            }
            contentEditor.sync();

            var tree = $("#category_tree");
            var node = tree.tree("getSelected");
            $("#contentAddForm [name=categoryId]").val(node ? node.id : null);

            var formData = $("#contentAddForm").serializeJson();
            $.ajax({
                url: "${pageContext.request.contextPath}/content/save",
                type: 'post',
                dataType: 'json',
                data: formData,
                success: function (data) {
                    if (data.status == 200) {
                        $.messager.alert('提示', '保存成功');
                        $("#contentTable").datagrid("reload");
                        TT.closeCurrentWindow();
                    }
                }
            })

        },
        clearForm: function () {
            $('#contentAddForm').form('reset');
            contentEditor.html('');
        }
    }

</script>
