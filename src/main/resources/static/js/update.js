// (1) 회원정보 수정
function update(userId, event) {
    event.preventDefault(); // 폼 태그 액션을 막기!!
    let data = $("#profileUpdate").serialize();
    console.log(data);

    $.ajax({
        type: "put",
        url: `/api/user/${userId}`,
        data: data,
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        dataType: "json"
    }).done(res => {
        console.log("update 성공", res);
        location.href = `/user/${userId}`;
    }).fail(error => {
        if (error.responseJSON.data == null) {
            alert(error.responseJSON.message);
        } else {
            alert(JSON.stringify(error.responseJSON.data));
        }
    });
}

function updatePassword(userId, event) {
    event.preventDefault(); // 폼 태그 액션을 막기!!
    let data = $("#passwordUpdate").serialize();
    console.log(data);

    $.ajax({
        type: "put",
        url: `/api/user/${userId}/password`,
        data: data,
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        dataType: "json"
    }).done(res => {
        console.log("password update 성공", res);
        alert("비밀번호가 성공적으로 변경되었습니다.");
    }).fail(error => {
        if (error.responseJSON.data == null) {
            alert(error.responseJSON.message);
        } else {
            alert(JSON.stringify(error.responseJSON.data));
        }
    });
}
