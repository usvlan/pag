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
            ApiUtil.post('template.system.get',{id:1},function(resp){
                var template = resp.data;
                apiTpl = template.content;
                setMarkDown(apiTpl);
            });
        } else {
            setMarkDown(apiTpl);
        }
    });

    // 设置内容
    function setMarkDown(content) {
        var oldContent = editor.getMarkdown();
        editor.setMarkdown(oldContent + content);
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
            var url = isNew ? 'doc.page.create' : 'doc.page.update'
            var markdown = editor.getMarkdown();
            var data = {
                id:docId
                ,name:$.trim($name.textbox('getValue'))
                ,content:ApiUtil.htmlEncode(markdown)
                ,parentId:parentId
            };
            if(isNew) {
                data.projectId = projectId;
            }
            ApiUtil.post(url,data,function(resp){
                ApiUtil.goMain();
            });
        }
        ,cancelDoc:function (docId) {
            ApiUtil.goMain();
        }
    };
})();