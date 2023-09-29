<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
	<title>이미지 보여주기</title>
</head>
<body>
    <h1>test 입니다.</h1>
    <div>
    	<div>사진을 올려주세요</div>
    	<form id="imgForm">
    		<input type="file" id="chooseFile" name="chooseFile" accept="image/*" onchange="loadFile(this)" />
            <button onclick="postFile()">전송</button>
        </form>

    </div>
    <div id="image-show">
    </div>

    <script>
        function loadFile(input) {
            var file = input.files[0];	//선택된 파일 가져오기

        	//미리 만들어 놓은 div에 text(파일 이름) 추가
        	var name = document.getElementById('chooseFile');
        	name.textContent = file.name;

        	//새로운 이미지 div 추가
        	var newImage = document.createElement("img");
        	newImage.setAttribute("class", 'img');
        	newImage.setAttribute("id", "myImage");

        	//이미지 source 가져오기
        	newImage.src = URL.createObjectURL(file);
        	newImage.style.width = "70%";
        	newImage.style.height = "70%";
        	//newImage.style.visibility = "hidden";   //버튼을 누르기 전까지는 이미지를 숨긴다
        	newImage.style.objectFit = "contain";

        	//이미지를 image-show div에 추가
        	var container = document.getElementById('image-show');
        	container.appendChild(newImage);
        };

        function postFile(){
            let imgBlob = document.getElementById("chooseFile").files[0];
            const url = '${root}/practice?action=show';

            let formData = new FormData();
            formData.append("myImg", imgBlob);

            let request = {
                method: "POST",
                body: formData
            };

            fetch(url, request)
                .then((response) => {
                    location.href = "./img/show.jsp"
                });
        };
    </script>
</body>
</html>