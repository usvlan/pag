var Main = (function () {

    function doSave() {
        var $form = $('#frm');
        var validateResult = $form.form('validate');
        if (validateResult) {
            var data = $form.form('getData');
            ApiUtil.post('project.create', data, function (resp) {
                location.href = 'main.html';
            });
        }
    }

    // public函数
    return {
        init:function () {
            $('#btnSave').click(function () {
                doSave();
            });
            $('#btnCancel').click(function () {
                history.go(-1);
            });
        }
        , createProject:function () {

        }
    }// end return;
})();