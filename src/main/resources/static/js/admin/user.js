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
    var res = '<button data-toggle="modal" class="btn btn-primary" href="list.html#modal-form" data-row='+row+'>修改</button>';
    if (state == 1)
        res += ' <button type="button" class="btn btn-default" onclick="changeSate(\'' + id + '\',0)" >禁用</button>';
    else
        res += ' <button typ-e="button" class="btn btn-info" onclick="changeSate(\'' + id + '\',1)" >启用</button>';
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
    $(this).modal('hide');
}

/**
 * 为模态框填充数据
 */
$('#modal-form').on('show.bs.modal', function (event) {
    var button = $(event.relatedTarget); // 触发事件的按钮
    var recipient = button.data('row');
    var modal = $(this);
    modal.find('#name').val('name ')
    modal.find('#phone').val('phone ' )
    modal.find('#remark').val('remark ')
    // modal.find('.modal-body input').val(recipient)
})