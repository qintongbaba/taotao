<%--
  Created by IntelliJ IDEA.
  User: wuqinghua
  Date: 17/11/4
  Time: 下午8:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
    #paramTempAddDiv form > div {
        padding-top: 20px;
        padding-left: 20px;
    }

    .hide {
        display: none;
    }
</style>
<div id="paramTempAddDiv">
    <form id="paramTempForm">
        <div>
            <label for="itemCat">商品类目:</label>
            <a id="itemCat" href="javascript:void(0)"
               class="easyui-linkbutton selectItemCat">选择类目</a>
            <input type="hidden" name="cid" style="width:280px;"/>
        </div>

        <div class="hide addGroupTr">
            <label for="addGroup">规格参数:</label>
            <a id="addGroup" href="javascript:void(0)"
               class="easyui-linkbutton">添加分组</a>
        </div>
        <div>
            <a href="javascript:void(0)" class="easyui-linkbutton submit">提交</a>
            <a href="javascript:void(0)" class="easyui-linkbutton close">关闭</a>
        </div>
    </form>
</div>
<div class="itemParamAddTemplate" style="display: none;">
    <li class="param">
        <ul>
            <li>
                <input class="easyui-textbox" style="width: 150px;" name="group"/>&nbsp;<a
                    href="javascript:void(0)" class="easyui-linkbutton addParam" title="添加参数"
                    data-options="plain:true,iconCls:'icon-add'"></a>
            </li>
            <li>
                <span>|-------</span><input style="width: 150px;" class="easyui-textbox"
                                            name="param"/>&nbsp;<a href="javascript:void(0)"
                                                                   class="easyui-linkbutton delParam"
                                                                   title="删除"
                                                                   data-options="plain:true,iconCls:'icon-cancel'"></a>
            </li>
        </ul>
    </li>
</div>

<script type="text/javascript">
    $(function () {
        //初始化商品类目
        TT.initItemCatTree({
            fun: function (node) {
                $(".addGroupTr").hide().find(".param").remove();

                //判断该类目是否添加过规格参数
                $.post('${pageContext.request.contextPath}/param/temp/getByCatId/' + node.id,
                    function (data) {
                        if (data.status == 200 && data.data) {
                            $.messager.alert('提示', '该类目已存在规格参数，请重新选择', undefined, function () {
                                $("#paramTempForm #itemCat").click();
                            });
                        }
                        $(".addGroupTr").show();
                        $("#addGroup").after($("<ul></ul>"));
                    })
            }
        });

        //添加商品类目规格参数分组
        $("#addGroup").click(function () {
            var template = $(".itemParamAddTemplate li").eq(0).clone();
            $("#addGroup+ul").append(template);

            template.find(".addParam").click(function () {
                var li = $(".itemParamAddTemplate li").eq(2).clone();
                li.find('.delParam').click(function () {
                    $(this).parent().remove();
                });
                li.appendTo($(this).parentsUntil("ul").parent());
            });
            template.find(".delParam").click(function () {
                $(this).parent().remove();
            });
        });

        //关闭
        $(".close").click(function () {
            $('.panel-tool-close').click();
        })

        //提交
        $(".submit").click(function () {

            //将参数变为json格式字符串
            var params = [];
            var groups = $(".addGroupTr [name=group]");
            groups.each(function (i, e) {
                var p = $(e).parentsUntil("ul").parent().find("[name=param]");
                var _ps = [];
                p.each(function (_i, _e) {
                    var _val = $(_e).siblings("input").val();
                    if ($.trim(_val).length > 0) {
                        _ps.push(_val);
                    }
                });
                var _val = $(e).siblings("input").val();
                if ($.trim(_val).length > 0 && _ps.length > 0) {
                    params.push({
                        "group": _val,
                        "params": _ps
                    });
                }
            });

            var cid = $('#paramTempForm [name=cid]').val();

            $.post('${pageContext.request.contextPath}/param/temp/save/' + cid,
                {"paramData": JSON.stringify(params)}, function (data) {
                    if (data.status == 200) {
                        $.messager.alert('提示', '保存规格参数成功!', undefined, function () {
                            $(".panel-tool-close").click();
                            $("#paramTempList").datagrid("reload");
                        });
                    }
                });
        })
    })
</script>