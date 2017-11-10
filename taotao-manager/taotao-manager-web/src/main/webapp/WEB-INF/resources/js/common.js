//将form表单转换为 json
(function ($) {
    $.fn.serializeJson = function () {
        var serializeObj = {};
        var array = this.serializeArray();
        var str = this.serialize();
        $(array).each(function () {
            if (serializeObj[this.name]) {
                if ($.isArray(serializeObj[this.name])) {
                    serializeObj[this.name].push(this.value);
                } else {
                    serializeObj[this.name] = [serializeObj[this.name], this.value];
                }
            } else {
                serializeObj[this.name] = this.value;
            }
        });
        return serializeObj;
    };
})(jQuery);

Date.prototype.format = function (format) {
    var o = {
        "M+": this.getMonth() + 1, //month
        "d+": this.getDate(), //day
        "h+": this.getHours(), //hour
        "m+": this.getMinutes(), //minute
        "s+": this.getSeconds(), //second
        "q+": Math.floor((this.getMonth() + 3) / 3), //quarter
        "S": this.getMilliseconds() //millisecond
    };
    if (/(y+)/.test(format)) {
        format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    }
    for (var k in o) {
        if (new RegExp("(" + k + ")").test(format)) {
            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
        }
    }
    return format;
}


var TT = TAOTAO = {
    //界面格式化时间方法
    formatDateTime: function (val, row) {
        var date = new Date(val);
        return date.format("yyyy-MM-dd hh:mm:ss");
    },

    //界面格式化价格
    formatPrice: function (val, row) {
        return (val / 1000).toFixed(2);
    },


    //初始化商品类型选择树
    initItemCatTree: function (param) {
        $('.selectItemCat').each(function (i, e) {
            var _ele = $(e);
            _ele.after("<span style='margin-left:10px;'></span>");
            _ele.unbind('click').click(function () {
                $("<div>").css({padding: "5px"}).html("<ul>").window({
                    title: "商品类目",
                    width: "500",
                    height: "450",
                    onOpen: function () {
                        var _win = this;
                        $("ul", _win).tree({
                            url: '/item/cat/list',
                            animate: true,
                            onClick: function (node) {
                                if ($(this).tree("isLeaf", node.target)) {
                                    _ele.parent().find("[name=cid]").val(node.id);
                                    _ele.next().text(node.text).attr("cid", node.id);
                                    $(_win).window('close');

                                    //回调函数
                                    if (param && param.fun) {
                                        param.fun.call(this, node);
                                    }
                                }
                            }
                        })
                    }
                })
            })
        })
    },

    //kindEditor
    kindEditorParam: {
        uploadJson: '/pic/upload',  //指定上传文件的请求url
        filePostName: 'uploadFile'   //指定上传文件form名称
    },

    //初始化文件上传按钮
    initPicUpload: function (data) {
        $('.picUpload').each(function (i, e) {
            var _ele = $(e);

            //获取form表单
            var form = _ele.parentsUntil('form');

            //添加click事件
            _ele.click(function () {
                KindEditor.editor(TT.kindEditorParam).loadPlugin('multiimage', function () {
                    var editor = this;
                    editor.plugin.multiImageDialog({
                        clickFn: function (urlList) {
                            //回显
                            //在按钮的后面添加div  先删除
                            _ele.siblings('div.pics').remove();
                            _ele.after('<div class="pics"><ul></ul></div>');
                            var imageArr = [];
                            $.each(urlList, function (i, data) {
                                imageArr.push(data.url);
                                _ele.siblings('div.pics').find('ul').append('<li><img src="' + data.url + '" width="80px" height="50px"/></li>');
                            });

                            //填充图片路径
                            form.find('[name=image]').val(imageArr.join(","));
                            editor.hideDialog();
                        }
                    })
                });
            })
        })
    },

    //初始化单个文件上传按钮
    initOnePicUpload: function (data) {
        $(".onePicUpload").each(function (i, e) {
            var _ele = $(e);
            var form = _ele.parentsUntil("form");
            _ele.click(function () {
                KindEditor.editor(TT.kindEditorParam).loadPlugin('image', function () {
                    var editor = this;
                    editor.plugin.imageDialog({
                        showRemote: false,
                        clickFn: function (url, title, width, height, border, align) {
                            var input = _ele.siblings("input");
                            input.parent().find("img").remove();
                            input.val(url);
                            input.after("<a href='" + url + "' target='_blank'><img src='" + url + "' width='80' height='50'/></a>");
                            this.hideDialog();
                        }
                    })
                })
            })
        });
    },


    //创建一个富文本框
    createEditor: function (select) {
        return KindEditor.create(select, TT.kindEditorParam);
    },

    //创建一个easyui的window
    createWindow: function (params) {
        $("<div>").css({padding: "5px"}).window({
            width: params.width ? params.width : "80%",
            height: params.height ? params.height : "80%",
            modal: true,
            title: params.title ? params.title : " ",
            href: params.url,
            onClose: function () {
                $(this).window("destroy");
            },
            onLoad: function () {
                if (params.onLoad) {
                    params.onLoad.call(this);
                }
            }
        }).window("open");
    },
    closeCurrentWindow: function () {
        $(".panel-tool-close").click();
    }
}