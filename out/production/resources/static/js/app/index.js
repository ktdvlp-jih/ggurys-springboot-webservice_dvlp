/*
* function을 따로가 아닌 같이 작성한 이유는 혹시나 다른 중복된 함수가 있으니 이러한 문제를 피하기 위해 객체 안에 function을 선언함
* 이런 식의 프론트엔드의 의존선 광리,스코프 관리등의 문제들로 자바스크립트 개발환경이 바뀌고 있습니다.
* #btn-save: 등록 버튼 클릭시 제목,작성자,재용의 값들이 데이터베이스에 저장되는 방식입니다.
* */

var main = {
    init: function () {
        var _this = this;
        // 글 등록 (저장)
        $('#btn-save').on('click', function () {
            _this.save();
        })
        // 글 수정
        $('#btn-update').on('click', function () {
            _this.update();
        })
        // 글 삭제
        $('#btn-delete').on('click', function () {
            _this.delete();
        })
    },
    save: function () {
        var data = {
            title: $('#title').val(),
            author: $('#author').val(),
            content: $('#content').val()
        };

        $.ajax({
            type: 'POST', // POST 등록
            url: '/api/v1/posts',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert("글이 등록되었습니다."); //성공 메세지 출력
            window.location.href = '/'; // 글 등록이 성공하면 메인페이지('/')로 이동
        }).fail(function (error) {
            alert(JSON.stringify(error)); //실패 메세지 출력
        })
    },
    update: function () {
        var data = {
            title: $('#title').val(),
            content: $('#content').val()
        };

        var id = $('#id').val(); //ID 값

        $.ajax({
            type: 'PUT', // PUT 수정
            url: '/api/v1/posts/'+id, // 어떤 게시글을 수정할지 URL Path로 구분하기위해 id추가
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert("글이 수정되었습니다."); //성공 메세지 출력
            window.location.href = '/'; // 글 등록이 성공하면 메인페이지('/')로 이동
        }).fail(function (error) {
            alert(JSON.stringify(error)); //실패 메세지 출력
        })
    },
    delete: function () {

        var id = $('#id').val(); //ID 값

        $.ajax({
            type: 'DELETE', // PUT 수정
            url: '/api/v1/posts/'+id, // 어떤 게시글을 수정할지 URL Path로 구분하기위해 id추가
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
        }).done(function () {
            alert("글이 삭제되었습니다."); //성공 메세지 출력
            window.location.href = '/'; // 글 등록이 성공하면 메인페이지('/')로 이동
        }).fail(function (error) {
            alert(JSON.stringify(error)); //실패 메세지 출력
        })
    }
}

main.init()