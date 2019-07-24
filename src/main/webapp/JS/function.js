/**
 * 함수모음
 * 
 */

/* 게시글 등록함수 */
function insertButton() {
	
	var formdate = $("#insert").serialize();

	if ("" == $("#name").val() || "" == $("#title").val() || "" == $("#content").val()) {
		alert("이름,제목,내용모두 공백이없어야합니다")
	} else {
		var message = confirm("등록하시겠습니까")
		if (true == message) {
			$.ajax({
				url : "./Insert.ino",
				Type : "POST",
				data : formdate,
				success : function(result) {
					location.href = "./main.ino"
				}
			});
		} else {
			return false;
		}
	}
}

/* 게시글 수정함수 */
function modify() {
	var message = confirm("수정하시겠습니까")
	var formdate = $("#detailForm").serialize();
	if (true == message) {
		$.ajax({
			url : "./update.ino",
			Type : "POST",
			data : formdate,
			success : function(result) {
				location.href = "./main.ino"
			}
		});
	} else {
		return false;
	}
}

/* 게시글 삭제함수 */
function deleteCheck(num) {
	var message = confirm("삭제하시겠습니까")
	if (true == message) {
		location.href = './Delete.ino?num=' + num;
	} else {
		return false;
	}
}

/* 검색시 유효성검사 함수 */
function search() {
	if ("" ==  $("#searchText").val() ) {
		alert("검색어를 입력해주세요")
	} else {
		location.href = "./main.ino?searchText=" + $("#searchText").val()
				+ "&category=" + $("#searchCategory option:selected").val();
	}
}
