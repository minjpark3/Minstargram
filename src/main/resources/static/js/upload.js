// // // (1) 스토리 이미지 업로드를 위한 사진 선택 로직
function imageChoose(input) {
	if (input.files && input.files[0]) {
		let file = input.files[0];

		if (!file.type.match("image.*")) {
			alert("이미지를 등록해야 합니다.");
			return;
		}

		let reader = new FileReader();
		reader.onload = function (e) {
			document.getElementById('imageUploadPreview').src = e.target.result;
		}
		reader.readAsDataURL(file);
	}
}


// // function imageChoose(obj) {
// // 	let f = obj.files[0];
// //
// // 	if (!f.type.match("image.*")) {
// // 		alert("이미지를 등록해야 합니다.");
// // 		return;
// // 	}
// //
// // 	let reader = new FileReader();
// // 	reader.onload = (e) => {
// // 		$("#imageUploadPreview").attr("src", e.target.result);
// // 	}
// // 	reader.readAsDataURL(f); // 이 코드 실행시 reader.onload 실행됨.
// // }
// //
// // function imageChoose(input) {
// // 	if (input.files && input.files[0]) {
// // 		var reader = new FileReader();
// // 		reader.onload = function (e) {
// // 			document.getElementById('imageUploadPreview').src = e.target.result;
// // 		}
// // 		reader.readAsDataURL(input.files[0]);
// // 	}
// // }
// function imageChoose(input) {
// 	if (input.files && input.files[0]) {
// 		var reader = new FileReader();
// 		reader.onload = function (e) {
// 			document.getElementById('imageUploadPreview').src = e.target.result;
// 		}
// 		reader.readAsDataURL(input.files[0]);
// 	}
// }