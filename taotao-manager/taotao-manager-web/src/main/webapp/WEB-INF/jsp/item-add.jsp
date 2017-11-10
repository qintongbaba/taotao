<%--
  Created by IntelliJ IDEA.
  User: wuqinghua
  Date: 17/10/23
  Time: 下午2:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="${pageContext.request.contextPath}/kindeditor/themes/default/default.css"
      type="text/css" rel="stylesheet">
<style>
    #addItemDiv form > div {
        padding-top: 10px;
        padding-left: 10px;
    }
</style>
<div id="addItemDiv" style="padding: 10px;">
    <form id="addItemForm" action="" method="post" class="itemForm">
        <div>
            <label for="itemCat">商品类目:</label>
            <a id="itemCat" href="javascript:void(0)"
               class="easyui-linkbutton selectItemCat">选择类目</a>
            <input type="hidden" name="cid" style="width:280px;"/>
        </div>
        <div>
            <label for="title">商品标题:</label>
            <input id="title" type="text" class="easyui-textbox" name="title"
                   data-options="required:true"
                   style="width: 280px;">
        </div>
        <div>
            <label for="sellPoint">商品卖点:</label>
            <input id="sellPoint" type="text" class="easyui-textbox" name="sellPoint"
                   data-options="multiline:true,validType:'length[0,150]'"
                   style="width: 280px;height: 60px;">
        </div>
        <div>
            <label for="priceView">商品价格:</label>
            <input id="priceView" type="text" class="easyui-numberbox" name="priceView"
                   data-options="min:1,max:99999999,precision:2,required:true" style="width:
                   280px;"/>
            <input type="hidden" name="price"/>
        </div>

        <div>
            <label for="num">库存数量:</label>
            <input id="num" class="easyui-numberbox" type="text" name="num"
                   data-options="min:1,max:99999999,precision:0,required:true" style="width:
                   280px;"/>
        </div>
        <div>
            <label for="barcode">条形码:&nbsp;&nbsp;&nbsp;</label>
            <input id="barcode" class="easyui-textbox" type="text" name="barcode"
                   data-options="validType:'length[1,30]'" style="width: 280px;"/>
        </div>
        <div>
            <label for="itemImage">商品图片:</label>
            <a id="itemImage" href="javascript:void(0)" class="easyui-linkbutton picUpload">上传图片
            </a>
            <input type="hidden" name="image">
        </div>
        <div style="clear: both;">
            <label>商品描述:</label>
            <textarea style="width: 800px;height: 300px;visibility: hidden;"
                      name="desc"></textarea>
        </div>
        <div>
            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">提交</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">重置</a>
        </div>
    </form>
</div>


<script type="text/javascript"
        src="${pageContext.request.contextPath}/kindeditor/kindeditor-all-min.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/kindeditor/lang/zh_CN.js"></script>
<script type="text/javascript">
    var itemDescEditor;
    $(function () {
        //初始化商品类目
        TT.initItemCatTree();
        //初始化图片上传按钮
        TT.initPicUpload();
        //创建富文本
        itemDescEditor = TT.createEditor('#addItemForm [name=desc]');
    })


    //提交商品信息
    function submitForm() {
        //表单有效性验证
        var _form = $("#addItemForm");
        if (!_form.form("validate")) {
            $.messager.alert("提示", '表单还没有填写完成!');
            return;
        }

        //商品的价格将其的单位变为分
        $("#addItemForm [name=price]").val(eval($('#addItemForm [name=priceView]').val()) * 100);

        //同步商品描述
        itemDescEditor.sync();

        //进行提交
        var formData = $("#addItemForm").serializeJson();
        $.ajax({
            url: "${pageContext.request.contextPath}/item/save",
            type: 'post',
            dataType: 'json',
            data: JSON.stringify(formData),
            contentType: 'application/json;charset=utf-8',
            success: function (data) {
                if (data.status == 200) {
                    $.messager.alert('提示', '保存成功');
                }
            }
        })

    }


    //重置表单
    function clearForm() {
        var _form = $('#addItemForm');
        _form.form('reset');
        itemDescEditor.html('');
    }


</script>

