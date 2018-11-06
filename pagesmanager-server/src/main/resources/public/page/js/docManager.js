var DocManager = (function () {
    var $grid;
    var projectId = ApiUtil.getParam('projectId');
    
    function initDocGrid() {
        $grid = $('#grid').treegrid({
            loader: function (param, success, error) {
                if (projectId) {
                    param.projectId = projectId;
                    ApiUtil.post('doc.treegrid.page', param, function (resp) {
                        var respData = resp.data;
                        success(respData);
                    });
                }
            }
            ,rownumbers: true
            ,animate: true
            ,collapsible: true
            ,pagination :true
            ,idField:'id'
            ,treeField:'name'
            ,toolbar: '#tb'
            ,columns : [[
                {field: 'name', title: '文档名称', width: 200},
                {field: 'cont', title: '内容', width: 60,formatter:function(val,obj,index){
                    return '<a href="#" onclick="DocManager.viewContent(' + index + ')">查看</a>';
                }},
                {field: 'gmtCreate', title: '创建时间', width: 150},
                {field: 'gmtUpdate', title: '修改时间', width: 150},
                {field : '_opt',title : '操作', width: 100,formatter:function(val,obj,index){
                    console.log(obj)
                    var id = obj.id;
                    var btns = [
                        '<a href="#" onclick="DocManager.update(' + id + ')">修改</a>'
                    ];
                    if (!obj._parentId) {
                        btns.push('<a href="#" onclick="DocManager.addDoc(' + id + ')">添加页面</a>')
                    }
                    return btns.join('&nbsp;&nbsp;');
                }},
            ]]
        });
    }
    
    function initProjectGrid() {
        $('#projectGrid').propertygrid({
            loader: function (param, success, error) {
                if (projectId) {
                    param.id = projectId;
                    ApiUtil.post('project.propertygrid.detail', param, function (resp) {
                        var respData = resp.data;
                        success(respData);
                    });
                }
            },
            showGroup: true,
            fit: true,
            border: false,
            scrollbarSize: 0
        });
    }

    function newDoc(parentId) {

    }
    
    $('#btnAddFolder').click(function () {
        $('#addFolderFrm').form('reset');
        $('#addFolderDlg').dialog('open');
    });

    $('#btnSaveFolder').click(function () {
        var $form = $('#addFolderFrm');
        var validateResult = $form.form('validate');
        if (validateResult) {
            var param = $form.form('getData');
            param.projectId = projectId;
            ApiUtil.post('doc.folder.create', param, function (resp) {
                $grid.treegrid('reload');
                $('#addFolderDlg').dialog('close');
            });
        }
    });
    
    // public函数
    return {
        init:function () {
            initDocGrid();
            initProjectGrid();
        }
        , createProject:function () {

        }
        , viewContent: function (index) {
            
        }
        , update: function (index) {

        }
        , addDoc: function (id) {
            var row = $grid.treegrid('find', id);
            console.log(row)
            // newDoc(row.parentId)
        }
    }// end return;
})();