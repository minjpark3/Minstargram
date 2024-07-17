<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>

<main class="uploadContainer">
    <section class="upload">
        <div class="upload-top">
            <a href="/" class="">
                <img src="/images/logo.jpg" alt="">
            </a>
            <p>사진 업로드</p>
        </div>

        <form class="upload-form" action="/image" method="post" enctype="multipart/form-data">
            <input type="file" name="file" onchange="imageChoose(this)" />
            <div class="upload-img">
                <img src="${imageUrl != null ? imageUrl : '/images/person.jpeg'}" alt="" id="imageUploadPreview" />
            </div>
            <div class="upload-form-detail">
                <input type="text" placeholder="사진 설명" name="caption" />
                <button class="cta blue">업로드</button>
            </div>
        </form>
    </section>
</main>
<script src="/js/upload.js"></script>
<%@ include file="../layout/footer.jsp" %>
<%--<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>--%>
<%--<%@ include file="../layout/header.jsp" %>--%>

<%--<!--사진 업로드페이지 중앙배치-->--%>
<%--<main class="uploadContainer">--%>
<%--    <!--사진업로드 박스-->--%>
<%--    <section class="upload">--%>
<%--        <!--사진업로드 로고-->--%>
<%--        <div class="upload-top">--%>
<%--            <a href="home.html" class="">--%>
<%--                <img src="/images/logo.jpg" alt="">--%>
<%--            </a>--%>
<%--            <p>사진 업로드</p>--%>
<%--        </div>--%>
<%--        <!--사진업로드 로고 end-->--%>

<%--        <!--사진업로드 Form-->--%>
<%--        <form class="upload-form" action="/image" method="post" enctype="multipart/form-data">--%>
<%--            <input type="file" name="file" onchange="imageChoose(this)" />--%>
<%--            <div class="upload-img">--%>
<%--                <img src="<c:out value='${imageUrl}' default='/images/person.jpeg'/>" alt="" id="imageUploadPreview" />--%>
<%--            </div>--%>

<%--            <!--사진설명 + 업로드버튼-->--%>
<%--            <div class="upload-form-detail">--%>
<%--                <input type="text" placeholder="사진설명" name="caption" />--%>
<%--                <button class="cta blue">업로드</button>--%>
<%--            </div>--%>
<%--            <!--사진설명end-->--%>
<%--        </form>--%>
<%--        <!--사진업로드 Form-->--%>
<%--    </section>--%>
<%--    <!--사진업로드 박스 end-->--%>
<%--</main>--%>
<%--<br/><br/>--%>

<%--<script src="/js/upload.js"></script>--%>
<%--<%@ include file="../layout/footer.jsp" %>--%>