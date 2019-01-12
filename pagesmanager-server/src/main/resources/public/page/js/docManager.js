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
            ,fitColumns: true
            ,rownumbers: true
            ,animate: true
            ,collapsible: true
            ,pagination :true
            ,idField:'id'
            ,treeField:'name'
            ,toolbar: '#tb'
            ,pageSize:50
            ,columns : [[
                {field: 'name', title: '文档名称'},
                {field: 'cont', title: '内容',formatter:function(val,obj,index){
                    return '<a href="#" onclick="DocManager.viewContent(' + obj.id + ')">查看</a>';
                }},
                {field: 'gmtCreate', title: '创建时间'},
                {field: 'isShow', title: '是否显示', formatter:function(val,obj,index){
                    return val ? '<span class="green">是</span>' : '<span class="red">否</span>';
                }},
                {field: 'gmtUpdate', title: '修改时间'},
                {field : '_opt',title : '操作', formatter:function(val,obj,index){
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

    $('#btnRelease').click(function () {
        var param = {projectId:projectId};
        ApiUtil.post('project.release', param, function (resp) {
            MsgUtil.topMsg("后台发布中...");
        });
    });

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

    $('#btnAddDoc').click(function () {
        newDoc(0, projectId);
    });

    function newDoc(parentId, projectId) {
        location.href = 'docEditor.html?parentId=' + (parentId || 0) + '&projectId=' + projectId + '&opt=new';
    }

    function updateDoc(docId, parentId, projectId) {
        location.href = 'docEditor.html?parentId=' + (parentId || 0) +
            '&docId=' + docId +
            '&projectId=' + projectId +
            '&opt=update';
    }

    // public函数
    return {
        init:function () {
            initDocGrid();
            initProjectGrid();
        }
        , viewContent: function (id) {
            var row = $grid.treegrid('find', id);
            var docId = row.id;
            window.open('docView.html?docId=' + docId);
        }
        , update: function (id) {
            var row = $grid.treegrid('find', id);
            var parentId = row.parentId;
            var docId = row.id;
            updateDoc(docId, parentId, projectId);
        }
        , addDoc: function (id) {
            var docId = id
            newDoc(docId, projectId);
        }
    }// end return;
})();