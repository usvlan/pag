var $docVontent = $('#docVontent');
var $name = $('#name').textbox();
var $parentName = $('#parentName').textbox();

var docId = ApiUtil.getParam('docId');
var projectId = ApiUtil.getParam('projectId');
var parentId = ApiUtil.getParam('parentId');
var opt = ApiUtil.getParam('opt');
var isNew = opt == 'new';
var editor;

var apiTpl;

var DocEditor = (function () {

    // 初始化
    function initNew() {
        initParentFolder(parentId);
        initMarkdown();
    }

    function initMarkdown() {
        editor = editormd("editormdView", {
            width   : "100%",
            height  : 640,
            syncScrolling : "single",
//	        autoHeight      : true,
            path    : "../editormd/lib/"
        });
    }

    // 请求资源
    function postRes(docId) {
        if(docId) {
            ApiUtil.post('doc.detail.get',{id:docId},function(resp){
                var docDetail = resp.data;

                $name.textbox('setValue', docDetail.name);
                $parentName.textbox('setValue', docDetail.parentName);
                $docVontent.html(docDetail.content);

                initMarkdown();
            });
        }
    }

    function initOperateBar(docId) {
        $('#btnSave').click(function () {
            DocEditor.saveDoc(docId);
        });
        $('#btnCancel').click(function () {
            DocEditor.cancelDoc(docId);
        });
    }

    // 插入模板按钮
    $('#btn-insertApiTpl').click(function(){
        if(!apiTpl) {
            loadTemplateContent("api.txt", function (result) {
                apiTpl = result;
                setMarkDown(apiTpl);
            });
        } else {
            setMarkDown(apiTpl);
        }
    });

    // 加载模板内容
    function loadTemplateContent(templateFileName, callback) {
        $.get("../page/template/" + templateFileName, "", callback);
    }

    // 设置内容
    function setMarkDown(content) {
        var oldContent = editor.getMarkdown();
        editor.setMarkdown(oldContent + content);
    }

    function goBack() {
        location.href = 'docManager.html?projectId=' + projectId;
    }

    function initParentFolder(parentId) {
        ApiUtil.post('doc.detail.get',{id:parentId},function(resp){
            var docDetail = resp.data;
            $parentName.textbox('setValue', docDetail.name);
        });
    }

    return {
        init: function () {
            if(!projectId) {
                alert('项目不存在');
                return;
            }
            if(opt == 'update') {
                postRes(docId);
            } else {
                initNew();
            }
            initOperateBar(docId);
        }
        ,saveDoc:function (docId) {
            var $form = $('#docFrm');
            var validateResult = $form.form('validate');
            if (validateResult) {
                var url = isNew ? 'doc.page.create' : 'doc.page.update';
                var param = $form.form('getData');
                var markdown = editor.getMarkdown();
                param.id = docId;
                param.content = ApiUtil.htmlEncode(markdown);
                param.parentId = parentId;
                param.projectId = projectId;
                ApiUtil.post(url, param, function(resp){
                    goBack();
                });
            }
        }
        ,cancelDoc:function (docId) {
            goBack();
        }
    };
})();