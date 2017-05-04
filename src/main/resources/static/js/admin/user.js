/*******用户相关********/

function sexFormatter(value, row, index) {
    if (value == 1)
        return "男";
    else if (value == 2)
        return "女";
    else
        return "未知";
}

function actionFormatter(value, row, index) {
    var state = row.state;
    var id = row.id;
    var res = '<button data-toggle="modal" class="btn btn-primary" href="list.html#modal-form" data-row='+id+'>修改</button>';
    if (state == 1)
        res += ' <button type="button" class="btn btn-default" onclick="changeSate(\'' + id + '\',0)" >禁用</button>';
    else
        res += ' <button typ-e="button" class="btn btn-info" onclick="changeSate(\'' + id + '\',1)" >启用</button>';
    res += ' <button typ-e="button" class="btn btn-danger" onclick="deleteOne(\'' + id + '\')">删除</button>';
    return res;
}

function changeSate(id, state) {
    $.ajax({
        type: 'POST',
        url: "changeState",
        data: {
            id: id,
            state: state
        },
        success: function () {
            $('#userTable').bootstrapTable('refresh', {url: '/admin/user/list'});
        },
        dataType: "json"
    });
}

function closeModal() {
    $('#modal-form').modal('hide');
}

function deleteOne(id) {
    layer.confirm('您确定要删除此行数据？', {
        btn: ['确定','取消'] //按钮
    }, function(){
        $.ajax({
            type: 'POST',
            url: "deleteOne",
            data: {
                id: id,
            },
            success: function (status) {
                if (status == 1) {
                    layer.msg('删除成功', {icon: 1});
                    $('#userTable').bootstrapTable('refresh', {url: '/admin/user/list'});
                }else {
                    layer.msg('删除失败', {icon: 1});
                }
            },
            dataType: "json"
        });

    }, function(){
        layer.msg('您已取消操作', {
            time: 1000, //20s后自动关闭
            btn: ['明白了', '知道了']
        });
    });
}

/**
 * 为模态框填充数据
 */
$('#modal-form').on('show.bs.modal', function (event) {
    var button = $(event.relatedTarget); // 触发事件的按钮
    var id = button.data('row');
    var modal = $(this);
    $.ajax({
        type: 'POST',
        url: "getUserById",
        data: {
            id: id
        },
        success: function (data) {
            modal.find('#id').val(data.id)
            modal.find('#name').val(data.username)
            modal.find('#phone').val(data.phone )
            modal.find('#remark').val(data.remarks)
        },
        dataType: "json"
    });
})


/**
 * 列表单行点击事件
 */
// $('#userTable').on('click-row.bs.table', function (row, $element) {
//     alert(row);
// })
