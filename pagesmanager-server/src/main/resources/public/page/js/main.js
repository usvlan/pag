var Main = (function () {

    var $tabs = $("#mainTab");
    var $tabsMenu = $("#tabsMenu");
    // key:text
    var btnMap = {};

    //几个关闭事件的实现
    function closeTab(menu, type) {
        var curTabTitle = $(menu).data("tabTitle");
        if (type === "close") {
            doCloseTab(curTabTitle);
        } else if (type === "Refresh") {
            var $curTab = $tabs.tabs('getSelected'); // 获得当前tab
            var url = $($curTab.panel('options').content).attr('src');
            if (url) {
                $tabs.tabs('update', {
                    tab: $curTab,
                    options: {
                        content: '<iframe src="'+url+'?app=' + curTabTitle + '&q='+new Date().getTime()+'" scrolling="yes" frameborder="0" style="width:100%;height:99%;"></iframe>'
                    }
                });
            }
        } else {
            var allTabs = $tabs.tabs("tabs");
            var closeTabsTitle = [];

            $.each(allTabs, function () {
                var opt = $(this).panel("options");
                if (opt.closable && opt.title != curTabTitle && type === "Other") {
                    closeTabsTitle.push(opt.title);
                } else if (opt.closable && type === "All") {
                    closeTabsTitle.push(opt.title);
                }
            });

            for (var i = 0; i < closeTabsTitle.length; i++) {
                $tabs.tabs("close", closeTabsTitle[i]);
            }
        }
    }

    function doCloseTab(title) {
        $tabs.tabs("close", title);
    }

    //在右边center区域打开菜单，新增tab
    function openNewTab(text, $btn) {
        var text = $btn.find('.l-btn-text').eq(0).html();
        var projectId = $btn.attr('projectid');

        acvtiveBtn(text);

        if ($tabs.tabs('exists', text)) {
            $tabs.tabs('select', text);
        } else {
            $tabs.tabs('add', {
                title: text,
                closable: true,
                content: '<iframe src="docManager.html?projectId='+projectId+'&q='+new Date().getTime()+'" scrolling="yes" frameborder="0" style="width:100%;height:99%;"></iframe>'
            });
        }
    }

    function storeBtn() {
        $('.west-area').find('.easyui-linkbutton').each(function () {
            var $btn = $(this);
            var text = $btn.find('.l-btn-text').eq(0).html();
            btnMap[text] = $btn;
        });
    }

    function acvtiveBtn(text) {
        var className = 'btn-actived';
        for(var key in btnMap){
            var $btn = btnMap[key];
            if (text == key) {
                if ($btn && !$btn.hasClass(className)) {
                    $btn.addClass(className);
                }
            } else {
                $btn.removeClass(className);
            }
        }
    }

    // public函数
    return {
        init:function () {
            //绑定tabs的右键菜单
            $tabs.tabs({
                onContextMenu: function (e, title) {
                    e.preventDefault();
                    $('#tabsMenu').menu('show', {
                        left: e.pageX,
                        top: e.pageY
                    }).data("tabTitle", title);
                }
                ,onSelect:function (title,index) {
                    acvtiveBtn(title);
                }
            });

            //实例化menu的onClick事件
            $tabsMenu.menu({
                onClick: function (item) {
                    closeTab(this, item.name);
                }
            });

            var $projectDiv = $('#projectList');

            ApiUtil.post('project.listall', {}, function (resp) {
                var projectArr = resp.data;
                var html = [];
                for (var i = 0; i < projectArr.length; i++) {
                    var project = projectArr[i];
                    var projectName = project.name;
                    var tag = '<a href="javascript:void(0)" id="applist_'+i+'" projectid="'+project.id+'" projectname="'+projectName+'" class="easyui-linkbutton l-btn l-btn-large" data-options="iconCls:\'icon-large-smartart\',size:\'large\',iconAlign:\'top\'">' +
                        '<span class="l-btn-left l-btn-icon-top">' +
                        '<span class="l-btn-text">'+projectName+'</span>' +
                        '<span class="l-btn-icon icon-large-smartart">&nbsp;</span>' +
                        '</span>' +
                        '</a>'
                    html.push(tag);
                }
                $projectDiv.html(html.join(''));

                $projectDiv.find('.easyui-linkbutton').click(function () {
                    var $btn = $(this);
                    var text = $btn.attr('projectname');
                    acvtiveBtn(text);
                    openNewTab(text, $btn);
                });

                storeBtn();
            });

        }
    }// end return;
})();