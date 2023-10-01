<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
	<title>이미지 보여주기</title>
</head>
<body>
    <div>
        <h1>test 입니다.</h1>
    	<div contentEditable="true" id="editableDiv">
    	    텍스트를 수정할 수 있습니다.
    	</div>
        <button onclick="postFile()">전송</button>
        <input type="file" id="chooseFile" name="chooseFile" accept="image/*" onchange="loadFile(this)" />
    </div>
    <div id="image-show">
    </div>
    <script>
        let imgIdx = 0;
        let imgFiles = [];
        function loadFile(input) {
            var file = input.files[0];	//선택된 파일 가져오기
            imgFiles.push(file);

        	//미리 만들어 놓은 div에 text(파일 이름) 추가
        	var name = document.getElementById('chooseFile');
        	name.value = "";
        	name.click();

        	name.textContent = file.name;

        	//새로운 이미지 div 추가
        	var newImage = document.createElement("img");
        	newImage.setAttribute("class", "d-flex justify-content-center");
        	newImage.setAttribute("id", `myImage\${imgIdx++}`);

        	//이미지 source 가져오기
        	newImage.src = URL.createObjectURL(file);
        	newImage.style.width = "30%";
        	newImage.style.height = "30%";
        	newImage.style.objectFit = "contain";

        	//이미지를 image-show div에 추가
        	var container = document.getElementById('editableDiv');
        	var imgDiv = document.createElement("div");
        	imgDiv.appendChild(newImage);
            container.appendChild(imgDiv);
        };

        function postFile(){
            const url = '${root}/practice?action=save';
            let formData = new FormData();

            for(let i=0; i<imgIdx; ++i){
                let imgBlob = imgFiles[i];
                formData.append(`myImg\${i}`, new File([imgBlob], `myImg\${i}.jpg`));
                console.log(`myImg\${i}`);
            }
            formData.append("imgSize", imgIdx);

            let request = {
                method: "POST",
                body: formData
            };

            fetch(url, request)
                .then((response) => {
                    location.href = response.url;
                });
        };
    </script>
</body>
</html>