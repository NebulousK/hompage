<%@page import="homepage.someDto"%>
<%@page import="homepage.board.ReplyDto"%>
<%@page import="homepage.board.BoardDto"%>
<%@page import="homepage.board.BoardDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width">
<title>Responsive Aside website template</title>

<link type="text/css" rel="stylesheet" href="/homepage/css/bootstrap.css">
<link type="text/css" rel="stylesheet" href="/homepage/css/components.css">
<link type="text/css" rel="stylesheet" href="/homepage/css/responsee.css"> 
<link type="text/css" rel="stylesheet" href="/homepage/css/template-style.css">  

 <link type="text/css" rel="stylesheet" href="/homepage/css/luYzpvna9qk.css" />  
<link type="text/css" rel="stylesheet" href="/homepage/css/oBwXKrMsLPd.css" />  
<link type="text/css" rel="stylesheet" href="/homepage/css/k4p1J0mZLFW.css" />
 <link type="text/css" rel="stylesheet" href="/homepage/css/u4n0TOW16uV.css" />  
 <link type="text/css" rel="stylesheet" href="/homepage/css/aO5MXY7tAfJ.css" /> 
  
<script type="text/javascript" src="/homepage/js/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="/homepage/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="/homepage/js/jquery-ui.min.js"></script>
<script type="text/javascript" src="/homepage/js/modernizr.js"></script>
<script type="text/javascript" src="/homepage/js/responsee.js"></script>
<script type="text/javascript" src="/homepage/js/bootstrap.js"></script>
<link rel="stylesheet" href="/homepage/css/editor.css" type="text/css" charset="utf-8" />
<script src="/homepage/js/editor_loader2.js" type="text/javascript" charset="utf-8"></script>

<script>

	
	//좋아요 자바스크립트
	function like(no, id){
		document.Baction.no.value = no;
		document.Baction.id.value = id;
		document.Baction.submit();
	}
	//좋아요 취소 자바스크립트
	function unlike(no, id){
		document.Baction1.no.value = no;
		document.Baction1.id.value = id;
		document.Baction1.submit();
	}
	//글삭제 자바 스크립트
	function Bdelete(no){
		if (confirm("정말 삭제하시겠습니까??") == true){    //확인
			document.Baction2.no.value = no;
			document.Baction2.submit();
		}else{   //취소
		    return;
		}
	}
	//글 수정 자바스크립트
	function Bupdate(form, id, no){
		document.updatewindow.postbox1.value = form.postbox1.value;
		document.updatewindow.no.value = no;
		document.updatewindow.id.value = id;
		document.updatewindow.submit();
	}
	
	//수정창 및 댓글
	$(document).ready(	
		
		function(){
			
		//댓글창 감추기	
			$("div").each(function(i){
					$("div#replydiv"+i).hide();	
				}
			);
		
		//수정창 감추기	
			$("div").each(function(i){
					$("div#testdiv"+i).hide();	
				}
			);
		//수정창 보이기
			$("mcjsjs").each(function(i) {
				position = $(window).scrollTop();
				var no = $("#divNoCheck" + i).val();
					//alert(no);
				$("#updateclick" + no).click(function() {
					$("div#testdiv" + no).show();
				});
			});
		//취소시 수정창 감추기
			$("mcjsjs").each(function(i) {
				position = $(this).scrollTop();
				var no = $("#divNoCheck" + i).val();
				$("#updateCancel" + no).click(function() {
				$("div#testdiv" + no).hide();
			});
		});
		//댓글창 보이기 숨기기
		$("mcjsjs").each(function(i) {
			var no = $("#divNoCheck" + i).val();
			$("#replyshow" + no).click(function() {
				if($("#replydiv" + no).css("display") == "none"){
				    $("#replydiv" + no).show();
				} else {
				    $("#replydiv" + no).hide();
				}
			});
		});
		
	});
	
	//댓글창 엔터시 row늘리기
    function setLine( txa ){
           var line = 0;
   
           var new_line = txa.value.split( "\n" ).length + 1 -1;
           if( new_line < line ) new_line = line;

           txa.rows = new_line;
    }
	
	//댓글지우기
	function replyDelete(no){
		if (confirm("정말 삭제하시겠습니까??") == true){    //확인
			document.Raction.no.value = no;
			document.Raction.submit();
		}
		else{
			return;
		}
	}
	
	//더보기 클릭시
	function moreboard(numper){
	 	document.more.numperpage.value = numper;
		document.more.submit(); 
	}
</script>
<!-- Sample: Saving Contents -->
						<script type="text/javascript">
							/* 예제용 함수 */
							function saveContent(i) {
								Editor.switchEditor(i);
								alert(Editor.getInitializedId());
								Editor.save(); // 이 함수를 호출하여 글을 등록하면 된다.		
							}

							/**                       
							 * Editor.save()를 호출한 경우 데이터가 유효한지 검사하기 위해 부르는 콜백함`로
							 * 상황에 맞게 수정하여 사용한다.
							 * 모든 데이터가 유효할 경우에 true를 리턴한다.
							 * @function
							 * @param {Object} editor - 에디터에서 넘겨주는 editor 객체
							 * @returns {Boolean} 모든 데이터가 유효할 경우에 true
							 */
							function validForm(Editor) {
								// Place your validation logic here

								// sample : validate that content exists
								var validator = new Trex.Validator();
								var content = Editor.getContent();
								if (!validator.exists(content)) {
									alert('내용을 입력하세요');
									return false;
								}

								return true;
							}

							/**
							 * Editor.save()를 호출한 경우 validForm callback 이 수행된 이후
							 * 실제 form submit을 위해 form 필드를 생성, 변경하기 위해 부르는 콜백함수로
							 * 각자 상황에 맞게 적절히 응용하여 사용한다.
							 * @function
							 * @param {Object} editor - 에디터에서 넘겨주는 editor 객체
							 * @returns {Boolean} 정상적인 경우에 true
							 */
							function setForm(Editor) {
								var formGenerator = Editor.getForm();
								var content = Editor.getContent();
								formGenerator.createField(tx.textarea({
									'name' : "content", // 본문 내용을 필드를 생성하여 값을 할당하는 부분
									'style' : {
										'display' : "none"
									}
								}, content));
								/* 아래의 코드는 첨부된 데이터를 필드를 생성하여 값을 할당하는 부분으로 상황에 맞게 수정하여 사용한다.
								 첨부된 데이터 중에 주어진 종류(image,file..)에 해당하는 것만 배열로 넘겨준다. */
								var images = Editor.getAttachments('image');
								for (var i = 0, len = images.length; i < len; i++) {
									// existStage는 현재 본문에 존재하는지 여부
									if (images[i].existStage) {
										// data는 팝업에서 execAttach 등을 통해 넘긴 데이터
										//alert('attachment information - image[' + i + '] \r\n' + JSON.stringify(images[i].data));
										formGenerator.createField(tx.input({
											'type' : "hidden",
											'name' : 'tx_attach_image',
											'value' : images[i].data.imageurl
										// 예에서는 이미지경로만 받아서 사용
										}));
									}
								}
								var files = Editor.getAttachments('file');
								for (var i = 0, len = files.length; i < len; i++) {
									//alert('attachment information - file[' + i + '] \r\n' + JSON.stringify(files[i].data));
									formGenerator.createField(tx.input({
										'type' : "hidden",
										'name' : 'tx_attach_file',
										'value' : files[i].data.attachurl
									}));
								}
								return true;
							}
</script>
<style>
.dropdown-menu {
  position: absolute;
  top: 100%;
  right: 0;
  z-index: 1000;
  display: none;
  float: left;
  min-width: 50px;
  padding: 5px 0;
  margin: 2px 0 0;
  list-style: none;
  background-color: #ffffff;
  border: 1px solid #ccc;
  border: 1px solid rgba(0, 0, 0, 0.2);
  *border-right-width: 2px;
  *border-bottom-width: 2px;
  -webkit-border-radius: 6px;
     -moz-border-radius: 6px;
          border-radius: 6px;
  -webkit-box-shadow: 0 5px 10px rgba(0, 0, 0, 0.2);
     -moz-box-shadow: 0 5px 10px rgba(0, 0, 0, 0.2);
          box-shadow: 0 5px 10px rgba(0, 0, 0, 0.2);
  -webkit-background-clip: padding-box;
     -moz-background-clip: padding;
          background-clip: padding-box;
}
</style>
</head>
<%
	//접속중인 아이디
	String connectid = (String)session.getAttribute("id");
%>
<%-- <body class="size-1140" onload="onload(<%=position%>)"> --%>
<body class="size-1140" onload="document.body.scrollTop=document.cookie" onunload="document.cookie=document.body.scrollTop">
	<!-- TOP NAV WITH LOGO -->
	<header>
		<nav>
			<div class="line">
				<div class="s-12 l-2">
					<img class="s-5 l-12 center"
						src="/homepage/images/logo.png">
				</div>
				
			</div>
		</nav>
	</header>
	<!-- ASIDE NAV AND CONTENT -->
	<div class="line">
		<div class="box  margin-bottom">
			<div class="margin">
				<!-- ASIDE NAV 2 -->
				<aside class="s-12 l-five">
					<jsp:include page="/menu.jsp"/>
				</aside>
				<!-- CONTENT -->
				<section class="s-12 l-7">
		<!--============================================================================= 글쓰기 시작 -->
	<div class="_4-u2 mbm" style="border: 0px">
						<div id="main_border_down" style="width: 580px;">
							<form name="tx_editor_formA" id="tx_editor_formA" action="Post_proc.jsp" method="post" accept-charset="utf-8">
								<div id="board_title">
								<input type="hidden" name="action" id="action" value="insert">
								<input type="hidden" name="id" id="id" value="${sessionScope.id}">
								<input type="hidden" name="no" id="no" value="${sessionScope.no}">
									<!-- 타이틀 -->
								</div>
								<div id='blnk2' style="height: 0px;">&nbsp;&nbsp;&nbsp;</div>
								<!-- 에디터 컨테이너 시작 -->
								<div id="tx_trex_containerA" class="tx-editor-container">
									<!-- 사이드바 -->
									<div id="tx_sidebarA" class="tx-sidebar">
										<div class="tx-sidebar-boundary">
											<!-- 사이드바 / 첨부 -->
											<ul class="tx-bar tx-bar-left tx-nav-attach">
												<!-- 이미지 첨부 버튼 시작 -->
												<!-- @decsription <li></li> 단위로 위치를 이동할 수 있다.-->
												<li class="tx-list">
													<div unselectable="on" id="tx_imageA" class="tx-image tx-btn-trans">
														<a href="javascript:;" title="사진" class="tx-text">사진</a>
													</div>
												</li>
												<!-- 이미지 첨부 버튼 끝 -->
												<li class="tx-list">
													<div unselectable="on" id="tx_fileA" class="tx-file tx-btn-trans">
														<a href="javascript:;" title="파일" class="tx-text">파일</a>
													</div>
												</li>
												<li class="tx-list">
													<div unselectable="on" id="tx_mediaA" class="tx-media tx-btn-trans">
														<a href="javascript:;" title="외부컨텐츠" class="tx-text">외부컨텐츠</a>
													</div>
												</li>
												<li class="tx-list tx-list-extra">
													<div unselectable="on" class="tx-btn-nlrbg tx-extra">
														<a href="javascript:;" class="tx-icon" title="버튼 더보기">버튼
															더보기</a>
													</div>
													<ul class="tx-extra-menu tx-menu" style="left: -48px;" unselectable="on">
														<!-- @decsription 일부 버튼들을 빼서 레이어로 숨기는 기능을 원할 경우 이 곳으로 이동시킬 수 있다.-->
													</ul>
												</li>
											</ul>
											<!-- 사이드바 / 우측영역 -->
											<ul class="tx-bar tx-bar-right">
												<li class="tx-list">
													<div unselectable="on" class="tx-btn-lrbg tx-fullscreen" id="tx_fullscreenA">
														<a href="javascript:;" class="tx-icon"
															title="넓게쓰기 (Ctrl+M)">넓게쓰기</a>
													</div>
												</li>
											</ul>
											<ul class="tx-bar tx-bar-right tx-nav-opt">
												<li class="tx-list">
													<div unselectable="on" class="tx-switchtoggle" id="tx_switchertoggleA">
														<a href="javascript:;" title="에디터 타입">에디터</a>
													</div>
												</li>
											</ul>
										</div>
									</div>

									<!-- 툴바 - 기본 시작 -->
									<!--
										@decsription
										툴바 버튼의 그룹핑의 변경이 필요할 때는 위치(왼쪽, 가운데, 오른쪽) 에 따라 <li> 아래의 <div>의 클래스명을 변경하면 된다.
										tx-btn-lbg: 왼쪽, tx-btn-bg: 가운데, tx-btn-rbg: 오른쪽, tx-btn-lrbg: 독립적인 그룹

										드롭다운 버튼의 크기를 변경하고자 할 경우에는 넓이에 따라 <li> 아래의 <div>의 클래스명을 변경하면 된다.
										tx-slt-70bg, tx-slt-59bg, tx-slt-42bg, tx-btn-43lrbg, tx-btn-52lrbg, tx-btn-57lrbg, tx-btn-71lrbg
										tx-btn-48lbg, tx-btn-48rbg, tx-btn-30lrbg, tx-btn-46lrbg, tx-btn-67lrbg, tx-btn-49lbg, tx-btn-58bg, tx-btn-46bg, tx-btn-49rbg
									-->
									<div id="tx_toolbar_basicA" class="tx-toolbar tx-toolbar-basic">
										<div class="tx-toolbar-boundary">
											<ul class="tx-bar tx-bar-left">
												<li class="tx-list">
													<div id="tx_fontfamilyA" unselectable="on" class="tx-slt-70bg tx-fontfamily">
														<a href="javascript:;" title="글꼴">굴림</a>
													</div>
													<div id="tx_fontfamily_menuA" class="tx-fontfamily-menu tx-menu" unselectable="on"></div>
												</li>
											</ul>
											<ul class="tx-bar tx-bar-left">
												<li class="tx-list">
													<div unselectable="on" class="tx-slt-42bg tx-fontsize" id="tx_fontsizeA">
														<a href="javascript:;" title="글자크기">9pt</a>
													</div>
													<div id="tx_fontsize_menuA" class="tx-fontsize-menu tx-menu" unselectable="on"></div>
												</li>
											</ul>
											<ul class="tx-bar tx-bar-left tx-group-font">

												<li class="tx-list">
													<div unselectable="on" class="tx-btn-lbg tx-bold" id="tx_boldA">
														<a href="javascript:;" class="tx-icon" title="굵게 (Ctrl+B)">굵게</a>
													</div>
												</li>
												<li class="tx-list">
													<div unselectable="on" class="tx-btn-bg tx-underline" id="tx_underlineA">
														<a href="javascript:;" class="tx-icon" title="밑줄 (Ctrl+U)">밑줄</a>
													</div>
												</li>
												<li class="tx-list">
													<div unselectable="on" class="tx-btn-bg tx-italic" id="tx_italicA">
														<a href="javascript:;" class="tx-icon"
															title="기울임 (Ctrl+I)">기울임</a>
													</div>
												</li>
												<li class="tx-list">
													<div unselectable="on" class="tx-btn-bg tx-strike" id="tx_strikeA">
														<a href="javascript:;" class="tx-icon"
															title="취소선 (Ctrl+D)">취소선</a>
													</div>
												</li>
												<li class="tx-list">
													<div unselectable="on" class="tx-slt-tbg tx-forecolor" id="tx_forecolorA">
														<a href="javascript:;" class="tx-icon" title="글자색">글자색</a>
														<a href="javascript:;" class="tx-arrow" title="글자색 선택">글자색
															선택</a>
													</div>
													<div id="tx_forecolor_menuA"
														class="tx-menu tx-forecolor-menu tx-colorpallete" unselectable="on"></div>
												</li>
												<li class="tx-list">
													<div unselectable="on" class="tx-slt-brbg tx-backcolor" id="tx_backcolorA">
														<a href="javascript:;" class="tx-icon" title="글자 배경색">글자
															배경색</a> <a href="javascript:;" class="tx-arrow"
															title="글자 배경색 선택">글자 배경색 선택</a>
													</div>
													<div id="tx_backcolor_menuA"
														class="tx-menu tx-backcolor-menu tx-colorpallete" unselectable="on"></div>
												</li>
											</ul>
											<ul class="tx-bar tx-bar-left tx-group-align">
												<li class="tx-list">
													<div unselectable="on" class="tx-btn-lbg tx-alignleft" id="tx_alignleftA">
														<a href="javascript:;" class="tx-icon"
															title="왼쪽정렬 (Ctrl+,)">왼쪽정렬</a>
													</div>
												</li>
												<li class="tx-list">
													<div unselectable="on" class="tx-btn-bg tx-aligncenter" id="tx_aligncenterA">
														<a href="javascript:;" class="tx-icon"
															title="가운데정렬 (Ctrl+.)">가운데정렬</a>
													</div>
												</li>
												<li class="tx-list">
													<div unselectable="on" class="tx-btn-bg tx-alignright" id="tx_alignrightA">
														<a href="javascript:;" class="tx-icon"
															title="오른쪽정렬 (Ctrl+/)">오른쪽정렬</a>
													</div>
												</li>
												<li class="tx-list">
													<div unselectable="on" class="tx-btn-rbg tx-alignfull" id="tx_alignfullA">
														<a href="javascript:;" class="tx-icon" title="양쪽정렬">양쪽정렬</a>
													</div>
												</li>
											</ul>
											<ul class="tx-bar tx-bar-left tx-group-tab">
												<li class="tx-list">
													<div unselectable="on" class="		 tx-btn-lbg 	tx-indent"
														id="tx_indentA">
														<a href="javascript:;" title="들여쓰기 (Tab)" class="tx-icon">들여쓰기</a>
													</div>
												</li>
												<li class="tx-list">
													<div unselectable="on" class="		 tx-btn-rbg 	tx-outdent"
														id="tx_outdentA">
														<a href="javascript:;" title="내어쓰기 (Shift+Tab)"
															class="tx-icon">내어쓰기</a>
													</div>
												</li>
											</ul>
											<ul class="tx-bar tx-bar-left tx-group-list">
												<li class="tx-list">
													<div unselectable="on" class="tx-slt-31lbg tx-lineheight"
														id="tx_lineheightA">
														<a href="javascript:;" class="tx-icon" title="줄간격">줄간격</a>
														<a href="javascript:;" class="tx-arrow" title="줄간격">줄간격
															선택</a>
													</div>
													<div id="tx_lineheight_menuA"
														class="tx-lineheight-menu tx-menu" unselectable="on"></div>
												</li>
												<li class="tx-list">
													<div unselectable="on" class="tx-slt-31rbg tx-styledlist"
														id="tx_styledlistA">
														<a href="javascript:;" class="tx-icon" title="리스트">리스트</a>
														<a href="javascript:;" class="tx-arrow" title="리스트">리스트
															선택</a>
													</div>
													<div id="tx_styledlist_menuA"
														class="tx-styledlist-menu tx-menu" unselectable="on"></div>
												</li>
											</ul>
											<ul class="tx-bar tx-bar-left tx-group-etc">
												<li class="tx-list">
													<div unselectable="on" class="		 tx-btn-lbg 	tx-emoticon"
														id="tx_emoticonA">
														<a href="javascript:;" class="tx-icon" title="이모티콘">이모티콘</a>
													</div>
													<div id="tx_emoticon_menuA" class="tx-emoticon-menu tx-menu"
														unselectable="on"></div>
												</li>
												<li class="tx-list">
													<div unselectable="on" class="		 tx-btn-bg 	tx-link"
														id="tx_linkA">
														<a href="javascript:;" class="tx-icon" title="링크 (Ctrl+K)">링크</a>
													</div>
													<div id="tx_link_menuA" class="tx-link-menu tx-menu"></div>
												</li>
												<li class="tx-list">
													<div unselectable="on" class="		 tx-btn-bg 	tx-specialchar"
														id="tx_specialcharA">
														<a href="javascript:;" class="tx-icon" title="특수문자">특수문자</a>
													</div>
													<div id="tx_specialchar_menuA"
														class="tx-specialchar-menu tx-menu"></div>
												</li>
												<li class="tx-list">
													<div unselectable="on" class="		 tx-btn-bg 	tx-table"
														id="tx_tableA">
														<a href="javascript:;" class="tx-icon" title="표만들기">표만들기</a>
													</div>
													<div id="tx_table_menuA" class="tx-table-menu tx-menu"
														unselectable="on">
														<div class="tx-menu-inner">
															<div class="tx-menu-preview"></div>
															<div class="tx-menu-rowcol"></div>
															<div class="tx-menu-deco"></div>
															<div class="tx-menu-enter"></div>
														</div>
													</div>
												</li>
												<li class="tx-list">
													<div unselectable="on" class="tx-btn-rbg tx-horizontalrule"
														id="tx_horizontalruleA">
														<a href="javascript:;" class="tx-icon" title="구분선">구분선</a>
													</div>
													<div id="tx_horizontalrule_menuA"
														class="tx-horizontalrule-menu tx-menu" unselectable="on"></div>
												</li>
											</ul>
											<ul class="tx-bar tx-bar-left">
												<li class="tx-list">
													<div unselectable="on"
														class="		 tx-btn-lbg 	tx-richtextbox" id="tx_richtextboxA">
														<a href="javascript:;" class="tx-icon" title="글상자">글상자</a>
													</div>
													<div id="tx_richtextbox_menuA"
														class="tx-richtextbox-menu tx-menu">
														<div class="tx-menu-header">
															<div class="tx-menu-preview-area">
																<div class="tx-menu-preview"></div>
															</div>
															<div class="tx-menu-switch">
																<div class="tx-menu-simple tx-selected">
																	<a><span>간단 선택</span></a>
																</div>
																<div class="tx-menu-advanced">
																	<a><span>직접 선택</span></a>
																</div>
															</div>
														</div>
														<div class="tx-menu-inner"></div>
														<div class="tx-menu-footer">
															<img class="tx-menu-confirm"
																src="/homepage/images/icon/editor/btn_confirm.gif?rv=1.0.1"
																alt="" /> <img class="tx-menu-cancel" hspace="3"
																src="/homepage/images/icon/editor/btn_cancel.gif?rv=1.0.1"
																alt="" />
														</div>
													</div>
												</li>
												<li class="tx-list">
													<div unselectable="on" class="		 tx-btn-bg 	tx-quote"
														id="tx_quoteA">
														<a href="javascript:;" class="tx-icon"
															title="인용구 (Ctrl+Q)">인용구</a>
													</div>
													<div id="tx_quote_menuA" class="tx-quote-menu tx-menu"
														unselectable="on"></div>
												</li>
												<li class="tx-list">
													<div unselectable="on" class="		 tx-btn-bg 	tx-background"
														id="tx_backgroundA">
														<a href="javascript:;" class="tx-icon" title="배경색">배경색</a>
													</div>
													<div id="tx_background_menuA"
														class="tx-menu tx-background-menu tx-colorpallete"
														unselectable="on"></div>
												</li>
												<li class="tx-list">
													<div unselectable="on" class="		 tx-btn-rbg 	tx-dictionary"
														id="tx_dictionaryA">
														<a href="javascript:;" class="tx-icon" title="사전">사전</a>
													</div>
												</li>
											</ul>
											<ul class="tx-bar tx-bar-left tx-group-undo">
												<li class="tx-list">
													<div unselectable="on" class="		 tx-btn-lbg 	tx-undo"
														id="tx_undoA">
														<a href="javascript:;" class="tx-icon"
															title="실행취소 (Ctrl+Z)">실행취소</a>
													</div>
												</li>
												<li class="tx-list">
													<div unselectable="on" class="		 tx-btn-rbg 	tx-redo"
														id="tx_redoA">
														<a href="javascript:;" class="tx-icon"
															title="다시실행 (Ctrl+Y)">다시실행</a>
													</div>
												</li>
											</ul>
											<ul class="tx-bar tx-bar-right">
												<li class="tx-list">
													<div unselectable="on" class="tx-btn-nlrbg tx-advanced"
														id="tx_advancedA">
														<a href="javascript:;" class="tx-icon" title="툴바 더보기">툴바
															더보기</a>
													</div>
												</li>
											</ul>
										</div>
									</div>
									<!-- 툴바 - 기본 끝 -->
									<!-- 툴바 - 더보기 시작 -->
									<div id="tx_toolbar_advancedA"
										class="tx-toolbar tx-toolbar-advanced">
										<div class="tx-toolbar-boundary">
											<ul class="tx-bar tx-bar-left">
												<li class="tx-list">
													<div class="tx-tableedit-title"></div>
												</li>
											</ul>

											<ul class="tx-bar tx-bar-left tx-group-align">
												<li class="tx-list">
													<div unselectable="on" class="tx-btn-lbg tx-mergecells"
														id="tx_mergecellsA">
														<a href="javascript:;" class="tx-icon2" title="병합">병합</a>
													</div>
													<div id="tx_mergecells_menuA"
														class="tx-mergecells-menu tx-menu" unselectable="on"></div>
												</li>
												<li class="tx-list">
													<div unselectable="on" class="tx-btn-bg tx-insertcells"
														id="tx_insertcellsA">
														<a href="javascript:;" class="tx-icon2" title="삽입">삽입</a>
													</div>
													<div id="tx_insertcells_menuA"
														class="tx-insertcells-menu tx-menu" unselectable="on"></div>
												</li>
												<li class="tx-list">
													<div unselectable="on" class="tx-btn-rbg tx-deletecells"
														id="tx_deletecellsA">
														<a href="javascript:;" class="tx-icon2" title="삭제">삭제</a>
													</div>
													<div id="tx_deletecells_menuA"
														class="tx-deletecells-menu tx-menu" unselectable="on"></div>
												</li>
											</ul>

											<ul class="tx-bar tx-bar-left tx-group-align">
												<li class="tx-list">
													<div id="tx_cellslinepreviewA" unselectable="on"
														class="tx-slt-70lbg tx-cellslinepreview">
														<a href="javascript:;" title="선 미리보기"></a>
													</div>
													<div id="tx_cellslinepreview_menuA"
														class="tx-cellslinepreview-menu tx-menu" unselectable="on"></div>
												</li>
												<li class="tx-list">
													<div id="tx_cellslinecolorA" unselectable="on"
														class="tx-slt-tbg tx-cellslinecolor">
														<a href="javascript:;" class="tx-icon2" title="선색">선색</a>

														<div class="tx-colorpallete" unselectable="on"></div>
													</div>
													<div id="tx_cellslinecolor_menuA"
														class="tx-cellslinecolor-menu tx-menu tx-colorpallete"
														unselectable="on"></div>
												</li>
												<li class="tx-list">
													<div id="tx_cellslineheightA" unselectable="on"
														class="tx-btn-bg tx-cellslineheight">
														<a href="javascript:;" class="tx-icon2" title="두께">두께</a>

													</div>
													<div id="tx_cellslineheight_menuA"
														class="tx-cellslineheight-menu tx-menu" unselectable="on"></div>
												</li>
												<li class="tx-list">
													<div id="tx_cellslinestyleA" unselectable="on"
														class="tx-btn-bg tx-cellslinestyle">
														<a href="javascript:;" class="tx-icon2" title="스타일">스타일</a>
													</div>
													<div id="tx_cellslinestyle_menuA"
														class="tx-cellslinestyle-menu tx-menu" unselectable="on"></div>
												</li>
												<li class="tx-list">
													<div id="tx_cellsoutlineA" unselectable="on"
														class="tx-btn-rbg tx-cellsoutline">
														<a href="javascript:;" class="tx-icon2" title="테두리">테두리</a>

													</div>
													<div id="tx_cellsoutline_menuA"
														class="tx-cellsoutline-menu tx-menu" unselectable="on"></div>
												</li>
											</ul>
											<ul class="tx-bar tx-bar-left">
												<li class="tx-list">
													<div id="tx_tablebackcolorA" unselectable="on"
														class="tx-btn-lrbg tx-tablebackcolor"
														style="background-color: #9aa5ea;">
														<a href="javascript:;" class="tx-icon2" title="테이블 배경색">테이블
															배경색</a>
													</div>
													<div id="tx_tablebackcolor_menuA"
														class="tx-tablebackcolor-menu tx-menu tx-colorpallete"
														unselectable="on"></div>
												</li>
											</ul>
											<ul class="tx-bar tx-bar-left">
												<li class="tx-list">
													<div id="tx_tabletemplateA" unselectable="on"
														class="tx-btn-lrbg tx-tabletemplate">
														<a href="javascript:;" class="tx-icon2" title="테이블 서식">테이블
															서식</a>
													</div>
													<div id="tx_tabletemplate_menuA"
														class="tx-tabletemplate-menu tx-menu tx-colorpallete"
														unselectable="on"></div>
												</li>
											</ul>

										</div>
									</div>
									<!-- 툴바 - 더보기 끝 -->
									<!-- 편집영역 시작 -->
									<!-- 에디터 Start -->
									<div id="tx_canvasA" class="tx-canvas">
										<div id="tx_loadingA" class="tx-loading">
											<div>
												<img src="/homepage/images/icon/editor/loading2.png"
													width="113" height="21" align="absmiddle" />
											</div>
										</div>
										<div id="tx_canvas_wysiwyg_holderA" class="tx-holder"
											style="display: block;">
											<iframe id="tx_canvas_wysiwygA" name="tx_canvas_wysiwyg"
												allowtransparency="true" frameborder="0"></iframe>
										</div>
										<div class="tx-source-deco">
											<div id="tx_canvas_source_holderA" class="tx-holder">
												<textarea id="tx_canvas_sourceA" rows="30" cols="30"></textarea>
											</div>
										</div>
										<div id="tx_canvas_text_holderA" class="tx-holder">
											<textarea id="tx_canvas_textA" rows="30" cols="30"></textarea>
										</div>
									</div>
									<!-- 높이조절 Start -->
									<div id="tx_resizerA" class="tx-resize-bar">
										<div class="tx-resize-bar-bg"></div>
										<img id="tx_resize_holderA" src="/homepage/images/icon/editor/skin/01/btn_drag01.gif" width="58" height="12" unselectable="on" alt="" />
									</div>
									
									<!-- 편집영역 끝 -->
									<!-- 첨부박스 시작 -->
									<!-- 파일첨부박스 Start -->
									<div id="tx_attach_divA" class="tx-attach-div">
										<div id="tx_attach_txtA" class="tx-attach-txt">파일 첨부</div>
										<div id="tx_attach_boxA" class="tx-attach-box">
											<div class="tx-attach-box-inner">
												<div id="tx_attach_previewA" class="tx-attach-preview">
													<p></p>
													<img src="/homepage/images/icon/editor/pn_preview.gif"
														width="147" height="108" unselectable="on" />
												</div>
												<div class="tx-attach-main">
													<div id="tx_upload_progressA" class="tx-upload-progress">
														<div>0%</div>
														<p>파일을 업로드하는 중입니다.</p>
													</div>
													<ul class="tx-attach-top">
														<li id="tx_attach_deleteA" class="tx-attach-delete"><a>전체삭제</a></li>
														<li id="tx_attach_sizeA" class="tx-attach-size">파일: <span
															id="tx_attach_up_sizeA" class="tx-attach-size-up"></span>/<span
															id="tx_attach_max_sizeA"></span>
														</li>
														<li id="tx_attach_toolsA" class="tx-attach-tools"></li>
													</ul>
													<ul id="tx_attach_listA" class="tx-attach-list"></ul>
												</div>
											</div>
										</div>
									</div>
									<!-- 첨부박스 끝 -->
								</div>
								<!-- 에디터 컨테이너 끝 -->
							</form>
							<div id="board_rightA" style="height: 30px">
							<!-- <button onclick='saveContent()' align=right><img src="/board/freeboard/image/write.gif" align=center border=0 ></button> -->
							<!-- <div style="text-align: right;height: 15px"> -->
							<a href="#" onclick='saveContent("A")'> <img src="/homepage/images/write.gif" align=right border=0></a>
							<!-- </div> -->
							<!-- </div> -->
						</div>
							<script type="text/javascript">
							var config = {
								txHost : '', /* 런타임 시 리소스들을 로딩할 때 필요한 부분으로, 경로가 변경되면 이 부분 수정이 필요. ex) http://xxx.xxx.com */
								txPath : '', /* 런타임 시 리소스들을 로딩할 때 필요한 부분으로, 경로가 변경되면 이 부분 수정이 필요. ex) /xxx/xxx/ */
								txService : 'sample', /* 수정필요없음. */
								txProject : 'sample', /* 수정필요없음. 프로젝트가 여러개일 경우만 수정한다. */
								initializedId : "A", /* 대부분의 경우에 빈문자열 */
								wrapper : "tx_trex_containerA", /* 에디터를 둘러싸고 있는 레이어 이름(에디터 컨테이너) */
								form : 'tx_editor_formA' + "", /* 등록하기 위한 Form 이름 */
								txIconPath : "/homepage/images/icon/editor/", /*에디터에 사용되는 이미지 디렉터리, 필요에 따라 수정한다. */
								txDecoPath : "/homepage/images/deco/contents/", /*본문에 사용되는 이미지 디렉터리, 서비스에서 사용할 때는 완성된 컨텐츠로 배포되기 위해 절대경로로 수정한다. */
								canvas : {
									initHeight : 150, // 높이
									styles : {
										color : "#123456", /* 기본 글자색 */
										fontFamily : "굴림", /* 기본 글자체 */
										fontSize : "10pt", /* 기본 글자크기 */
										backgroundColor : "#fff", /*기본 배경색 */
										lineHeight : "1.5", /*기본 줄간격 */
										padding : "8px" /* 위지윅 영역의 여백 */
									},
									showGuideArea : false
								},
								
								events : {
									preventUnload : false
								},
								sidebar : {
									attacher: {
									    image: {
									           objattr: {
									        	   width: 450,
									        	   height : 450
									        	   }
									           }
									    },
									attachbox : {
										show : true
									}
								},
								size : {
									contentWidth : 580 /* 지정된 본문영역의 넓이가 있을 경우에 설정 */
								}
							};
						
							EditorJSLoader.ready(function(Editor) {
								new Editor(config);
								Editor.getCanvas().observeJob(Trex.Ev.__CANVAS_PANEL_DELETE_SOMETHING, function (ev) {
								    // 데이터중에 존재하지 stage에 존재하지 않는 entry는 박스에서 바로 제거
								    var attachBox = Editor.getAttachBox();
								    attachBox.datalist.each(function (entry) {
								        if (entry.type === "image"  && entry.existStage === false ) {
								            entry.execRemove();
								            enrty = null;
								        }
								    });
								    attachBox.refreshPreview();
								});   
								Editor.getToolbar().observeJob(Trex.Ev.__TOOL_CLICK, function (type) {
								    Editor.switchEditor("A");
								});
							});	
						</script>
					</div>
<!-- ------------------------------------------------------------------글 쓰기 끝------------------------------------------------------------------------- -->
<!-- ==================================================================내용 시작=========================================================================== -->
<%
	BoardDao Bdao = new BoardDao();
	
	//글목록 벡터
	Vector list= new Vector();
	//글목록
	
	list = Bdao.getBoardList(connectid);
	//like 한 아이디 벡터
	Vector vlike = new Vector();
	//like 여부 확인 변수
	int flag = 0;
	
	
	int totalrecord = list.size();
	int numperpage = 0;
	if(request.getParameter("numperpage") == null || request.getParameter("numperpage").isEmpty() || request.getParameter("numperpage").equals("null")){
		numperpage = 5;
	}
	else{
		numperpage = Integer.parseInt(request.getParameter("numperpage"));
	}
	
	//리스트 뿌려지는 부분
	for(int i=0; i<numperpage;i++){
		if(totalrecord == i)
			break;
		someDto Bdto = (someDto) list.get(i);
%>
	<div>
		<div class="_4-u2 mbm _5jmm _5pat _5v3q _5sq8 _5x16" id="u_ps_0_0_m">
		
			<div class="userContentWrapper _5pcr _3ccb">
				<div class="_4r_y">
					<div class="_6a uiPopover _5pbi _5puc _5v56" id="u_ps_0_0_n">
						
					</div>
				</div>
				<div class="clearfix _5x46">
					<div class="_3dp _29k">
						<div>
							<div class="_6a">
								<div class="_6a _6b">
									<h5 class="_5pbw">
										<div class="fwn fcg">
							<table style="border: 0px;width: 554px">
								<tr>
									<td style="border: 0px;width: 55px; padding: 0px;margin: 0px;">
								<a class="_5pb8 _5v9u _29h _303" href="/homepage/itsme/main2.jsp?id=<%=Bdto.getId()%>">
									<!-- 프로필 이미지  -->
								<img class="_s0 _5xib _5sq7 _rw img" src="/homepage/profile/<%=Bdto.getPhoto()%>" alt="" align="middle" /></a>
									</td>
									<td style="border: 0px;text-align: left;">
										<span class="fwb fcg">
										<a href="/homepage/itsme/main2.jsp?id=<%=Bdto.getId()%>" style="font-size: 14px;"><%= Bdto.getId() %></a></span><br/>
										<div class="_5pcp" style="margin-top: 5px">
										<span><%= Bdto.getDay()%></span>
										</div>
									</td>
							<!-- 수정 드롭다운 메뉴 (자기글이 아니면 권한X) 접속한 아이디와 글쓴아이디 비교  -->
									<td style="border: 0px;text-align: right;">
<%
	//글쓴 아이디와 접속한 아이디 비교
	if(Bdto.getId().equals(connectid)){
%>
										<div class="dropdown">
  											<a class="dropdown-toggle" id="dLabel" role="button" data-toggle="dropdown" data-target="#" href="/page.html">
    										<b class="caret"></b>
  											</a>
 											<ul class="dropdown-menu" role="menu" aria-labelledby="dLabel">
												<li style="text-align: center;font-size: 12px;line-height: 25px;">
												<span id="updateclick<%=i%>">수&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;정</span></li>
												<mcjsjs><input type="hidden" id="divNoCheck<%=i %>" value="<%=i%>"/></mcjsjs>
												<input type="hidden" id="boardId" value="<%=Bdto.getId() %>" />
												<input type="hidden" id="connectId" value="<%=connectid %>" />
												<li style="text-align: center;font-size: 12px;line-height: 25px">
												<span onclick="Bdelete(<%=Bdto.getNo()%>)">삭&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;제</span></li>
											</ul>
										</div>
<%
	}
%>									</td>
											</tr>
							</table>
										</div>
									</h5>
									<div class="_5pcp">
										<span><%= Bdto.getDay()%></span>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
<!-- 수정창 생성  -->		
<div class="_55d0" id="testdiv<%=i%>">
<form name="tx_editor_form<%=i %>" id="tx_editor_form<%=i %>" action="like_proc.jsp" method="post" accept-charset="utf-8">
						<div id="board_title">
								<input type="hidden" name="command" value="UPDATE"/>			
								<input type="hidden" name="id" id="id" value="${sessionScope.id}">
								<input type="hidden" name="no" value="<%=Bdto.getNo()%>"/>
									<!-- 타이틀 -->
								</div>
								<div id='blnk2' style="height: 0px;">&nbsp;&nbsp;&nbsp;</div>
								<!-- 에디터 컨테이너 시작 -->
								<div id="tx_trex_container<%=i %>" class="tx-editor-container">
									<!-- 사이드바 -->
									<div id="tx_sidebar<%=i %>" class="tx-sidebar">
										<div class="tx-sidebar-boundary">
											<!-- 사이드바 / 첨부 -->
											<ul class="tx-bar tx-bar-left tx-nav-attach">
												<!-- 이미지 첨부 버튼 시작 -->
												<!-- @decsription <li></li> 단위로 위치를 이동할 수 있다.-->
												<li class="tx-list">
													<div unselectable="on" id="tx_image<%=i %>" class="tx-image tx-btn-trans">
														<a href="javascript:;" title="사진" class="tx-text">사진</a>
													</div>
												</li>
												<!-- 이미지 첨부 버튼 끝 -->
												<li class="tx-list">
													<div unselectable="on" id="tx_file<%=i %>" class="tx-file tx-btn-trans">
														<a href="javascript:;" title="파일" class="tx-text">파일</a>
													</div>
												</li>
												<li class="tx-list">
													<div unselectable="on" id="tx_media<%=i %>" class="tx-media tx-btn-trans">
														<a href="javascript:;" title="외부컨텐츠" class="tx-text">외부컨텐츠</a>
													</div>
												</li>
												<li class="tx-list tx-list-extra">
													<div unselectable="on" class="tx-btn-nlrbg tx-extra">
														<a href="javascript:;" class="tx-icon" title="버튼 더보기">버튼
															더보기</a>
													</div>
													<ul class="tx-extra-menu tx-menu" style="left: -48px;" unselectable="on">
														<!-- @decsription 일부 버튼들을 빼서 레이어로 숨기는 기능을 원할 경우 이 곳으로 이동시킬 수 있다.-->
													</ul>
												</li>
											</ul>
											<!-- 사이드바 / 우측영역 -->
											<ul class="tx-bar tx-bar-right">
												<li class="tx-list">
													<div unselectable="on" class="tx-btn-lrbg tx-fullscreen" id="tx_fullscreen<%=i %>">
														<a href="javascript:;" class="tx-icon"
															title="넓게쓰기 (Ctrl+M)">넓게쓰기</a>
													</div>
												</li>
											</ul>
											<ul class="tx-bar tx-bar-right tx-nav-opt">
												<li class="tx-list">
													<div unselectable="on" class="tx-switchtoggle" id="tx_switchertoggle<%=i %>">
														<a href="javascript:;" title="에디터 타입">에디터</a>
													</div>
												</li>
											</ul>
										</div>
									</div>

									<!-- 툴바 - 기본 시작 -->
									<!--
										@decsription
										툴바 버튼의 그룹핑의 변경이 필요할 때는 위치(왼쪽, 가운데, 오른쪽) 에 따라 <li> 아래의 <div>의 클래스명을 변경하면 된다.
										tx-btn-lbg: 왼쪽, tx-btn-bg: 가운데, tx-btn-rbg: 오른쪽, tx-btn-lrbg: 독립적인 그룹

										드롭다운 버튼의 크기를 변경하고자 할 경우에는 넓이에 따라 <li> 아래의 <div>의 클래스명을 변경하면 된다.
										tx-slt-70bg, tx-slt-59bg, tx-slt-42bg, tx-btn-43lrbg, tx-btn-52lrbg, tx-btn-57lrbg, tx-btn-71lrbg
										tx-btn-48lbg, tx-btn-48rbg, tx-btn-30lrbg, tx-btn-46lrbg, tx-btn-67lrbg, tx-btn-49lbg, tx-btn-58bg, tx-btn-46bg, tx-btn-49rbg
									-->
									<div id="tx_toolbar_basic<%=i %>" class="tx-toolbar tx-toolbar-basic">
										<div class="tx-toolbar-boundary">
											<ul class="tx-bar tx-bar-left">
												<li class="tx-list">
													<div id="tx_fontfamily<%=i %>" unselectable="on" class="tx-slt-70bg tx-fontfamily">
														<a href="javascript:;" title="글꼴">굴림</a>
													</div>
													<div id="tx_fontfamily_menu<%=i %>" class="tx-fontfamily-menu tx-menu" unselectable="on"></div>
												</li>
											</ul>
											<ul class="tx-bar tx-bar-left">
												<li class="tx-list">
													<div unselectable="on" class="tx-slt-42bg tx-fontsize" id="tx_fontsize<%=i %>">
														<a href="javascript:;" title="글자크기">9pt</a>
													</div>
													<div id="tx_fontsize_menu<%=i %>" class="tx-fontsize-menu tx-menu" unselectable="on"></div>
												</li>
											</ul>
											<ul class="tx-bar tx-bar-left tx-group-font">

												<li class="tx-list">
													<div unselectable="on" class="tx-btn-lbg tx-bold" id="tx_bold<%=i %>">
														<a href="javascript:;" class="tx-icon" title="굵게 (Ctrl+B)">굵게</a>
													</div>
												</li>
												<li class="tx-list">
													<div unselectable="on" class="tx-btn-bg tx-underline" id="tx_underline<%=i %>">
														<a href="javascript:;" class="tx-icon" title="밑줄 (Ctrl+U)">밑줄</a>
													</div>
												</li>
												<li class="tx-list">
													<div unselectable="on" class="tx-btn-bg tx-italic" id="tx_italic<%=i %>">
														<a href="javascript:;" class="tx-icon"
															title="기울임 (Ctrl+I)">기울임</a>
													</div>
												</li>
												<li class="tx-list">
													<div unselectable="on" class="tx-btn-bg tx-strike" id="tx_strike<%=i %>">
														<a href="javascript:;" class="tx-icon"
															title="취소선 (Ctrl+D)">취소선</a>
													</div>
												</li>
												<li class="tx-list">
													<div unselectable="on" class="tx-slt-tbg tx-forecolor" id="tx_forecolor<%=i %>">
														<a href="javascript:;" class="tx-icon" title="글자색">글자색</a>
														<a href="javascript:;" class="tx-arrow" title="글자색 선택">글자색
															선택</a>
													</div>
													<div id="tx_forecolor_menu<%=i %>"
														class="tx-menu tx-forecolor-menu tx-colorpallete" unselectable="on"></div>
												</li>
												<li class="tx-list">
													<div unselectable="on" class="tx-slt-brbg tx-backcolor" id="tx_backcolor<%=i %>">
														<a href="javascript:;" class="tx-icon" title="글자 배경색">글자
															배경색</a> <a href="javascript:;" class="tx-arrow"
															title="글자 배경색 선택">글자 배경색 선택</a>
													</div>
													<div id="tx_backcolor_menu<%=i %>"
														class="tx-menu tx-backcolor-menu tx-colorpallete" unselectable="on"></div>
												</li>
											</ul>
											<ul class="tx-bar tx-bar-left tx-group-align">
												<li class="tx-list">
													<div unselectable="on" class="tx-btn-lbg tx-alignleft" id="tx_alignleft<%=i %>">
														<a href="javascript:;" class="tx-icon"
															title="왼쪽정렬 (Ctrl+,)">왼쪽정렬</a>
													</div>
												</li>
												<li class="tx-list">
													<div unselectable="on" class="tx-btn-bg tx-aligncenter" id="tx_aligncenter<%=i %>">
														<a href="javascript:;" class="tx-icon"
															title="가운데정렬 (Ctrl+.)">가운데정렬</a>
													</div>
												</li>
												<li class="tx-list">
													<div unselectable="on" class="tx-btn-bg tx-alignright" id="tx_alignright<%=i %>">
														<a href="javascript:;" class="tx-icon"
															title="오른쪽정렬 (Ctrl+/)">오른쪽정렬</a>
													</div>
												</li>
												<li class="tx-list">
													<div unselectable="on" class="tx-btn-rbg tx-alignfull" id="tx_alignfull<%=i %>">
														<a href="javascript:;" class="tx-icon" title="양쪽정렬">양쪽정렬</a>
													</div>
												</li>
											</ul>
											<ul class="tx-bar tx-bar-left tx-group-tab">
												<li class="tx-list">
													<div unselectable="on" class="		 tx-btn-lbg 	tx-indent"
														id="tx_indent<%=i %>">
														<a href="javascript:;" title="들여쓰기 (Tab)" class="tx-icon">들여쓰기</a>
													</div>
												</li>
												<li class="tx-list">
													<div unselectable="on" class="		 tx-btn-rbg 	tx-outdent"
														id="tx_outdent<%=i %>">
														<a href="javascript:;" title="내어쓰기 (Shift+Tab)"
															class="tx-icon">내어쓰기</a>
													</div>
												</li>
											</ul>
											<ul class="tx-bar tx-bar-left tx-group-list">
												<li class="tx-list">
													<div unselectable="on" class="tx-slt-31lbg tx-lineheight"
														id="tx_lineheight<%=i %>">
														<a href="javascript:;" class="tx-icon" title="줄간격">줄간격</a>
														<a href="javascript:;" class="tx-arrow" title="줄간격">줄간격
															선택</a>
													</div>
													<div id="tx_lineheight_menu<%=i %>"
														class="tx-lineheight-menu tx-menu" unselectable="on"></div>
												</li>
												<li class="tx-list">
													<div unselectable="on" class="tx-slt-31rbg tx-styledlist"
														id="tx_styledlist<%=i %>">
														<a href="javascript:;" class="tx-icon" title="리스트">리스트</a>
														<a href="javascript:;" class="tx-arrow" title="리스트">리스트
															선택</a>
													</div>
													<div id="tx_styledlist_menu<%=i %>"
														class="tx-styledlist-menu tx-menu" unselectable="on"></div>
												</li>
											</ul>
											<ul class="tx-bar tx-bar-left tx-group-etc">
												<li class="tx-list">
													<div unselectable="on" class="		 tx-btn-lbg 	tx-emoticon"
														id="tx_emoticon<%=i %>">
														<a href="javascript:;" class="tx-icon" title="이모티콘">이모티콘</a>
													</div>
													<div id="tx_emoticon_menu<%=i %>" class="tx-emoticon-menu tx-menu"
														unselectable="on"></div>
												</li>
												<li class="tx-list">
													<div unselectable="on" class="		 tx-btn-bg 	tx-link"
														id="tx_link<%=i %>">
														<a href="javascript:;" class="tx-icon" title="링크 (Ctrl+K)">링크</a>
													</div>
													<div id="tx_link_menu<%=i %>" class="tx-link-menu tx-menu"></div>
												</li>
												<li class="tx-list">
													<div unselectable="on" class="		 tx-btn-bg 	tx-specialchar"
														id="tx_specialchar<%=i %>">
														<a href="javascript:;" class="tx-icon" title="특수문자">특수문자</a>
													</div>
													<div id="tx_specialchar_menu<%=i %>"
														class="tx-specialchar-menu tx-menu"></div>
												</li>
												<li class="tx-list">
													<div unselectable="on" class="		 tx-btn-bg 	tx-table"
														id="tx_table<%=i %>">
														<a href="javascript:;" class="tx-icon" title="표만들기">표만들기</a>
													</div>
													<div id="tx_table_menu<%=i %>" class="tx-table-menu tx-menu"
														unselectable="on">
														<div class="tx-menu-inner">
															<div class="tx-menu-preview"></div>
															<div class="tx-menu-rowcol"></div>
															<div class="tx-menu-deco"></div>
															<div class="tx-menu-enter"></div>
														</div>
													</div>
												</li>
												<li class="tx-list">
													<div unselectable="on" class="tx-btn-rbg tx-horizontalrule"
														id="tx_horizontalrule<%=i %>">
														<a href="javascript:;" class="tx-icon" title="구분선">구분선</a>
													</div>
													<div id="tx_horizontalrule_menu<%=i %>"
														class="tx-horizontalrule-menu tx-menu" unselectable="on"></div>
												</li>
											</ul>
											<ul class="tx-bar tx-bar-left">
												<li class="tx-list">
													<div unselectable="on"
														class="		 tx-btn-lbg 	tx-richtextbox" id="tx_richtextbox<%=i %>">
														<a href="javascript:;" class="tx-icon" title="글상자">글상자</a>
													</div>
													<div id="tx_richtextbox_menu<%=i %>"
														class="tx-richtextbox-menu tx-menu">
														<div class="tx-menu-header">
															<div class="tx-menu-preview-area">
																<div class="tx-menu-preview"></div>
															</div>
															<div class="tx-menu-switch">
																<div class="tx-menu-simple tx-selected">
																	<a><span>간단 선택</span></a>
																</div>
																<div class="tx-menu-advanced">
																	<a><span>직접 선택</span></a>
																</div>
															</div>
														</div>
														<div class="tx-menu-inner"></div>
														<div class="tx-menu-footer">
															<img class="tx-menu-confirm"
																src="/homepage/images/icon/editor/btn_confirm.gif?rv=1.0.1"
																alt="" /> <img class="tx-menu-cancel" hspace="3"
																src="/homepage/images/icon/editor/btn_cancel.gif?rv=1.0.1"
																alt="" />
														</div>
													</div>
												</li>
												<li class="tx-list">
													<div unselectable="on" class="		 tx-btn-bg 	tx-quote"
														id="tx_quote<%=i %>">
														<a href="javascript:;" class="tx-icon"
															title="인용구 (Ctrl+Q)">인용구</a>
													</div>
													<div id="tx_quote_menu<%=i %>" class="tx-quote-menu tx-menu"
														unselectable="on"></div>
												</li>
												<li class="tx-list">
													<div unselectable="on" class="		 tx-btn-bg 	tx-background"
														id="tx_background<%=i %>">
														<a href="javascript:;" class="tx-icon" title="배경색">배경색</a>
													</div>
													<div id="tx_background_menu<%=i %>"
														class="tx-menu tx-background-menu tx-colorpallete"
														unselectable="on"></div>
												</li>
												<li class="tx-list">
													<div unselectable="on" class="		 tx-btn-rbg 	tx-dictionary"
														id="tx_dictionary<%=i %>">
														<a href="javascript:;" class="tx-icon" title="사전">사전</a>
													</div>
												</li>
											</ul>
											<ul class="tx-bar tx-bar-left tx-group-undo">
												<li class="tx-list">
													<div unselectable="on" class="		 tx-btn-lbg 	tx-undo"
														id="tx_undo<%=i %>">
														<a href="javascript:;" class="tx-icon"
															title="실행취소 (Ctrl+Z)">실행취소</a>
													</div>
												</li>
												<li class="tx-list">
													<div unselectable="on" class="		 tx-btn-rbg 	tx-redo"
														id="tx_redo<%=i %>">
														<a href="javascript:;" class="tx-icon"
															title="다시실행 (Ctrl+Y)">다시실행</a>
													</div>
												</li>
											</ul>
											<ul class="tx-bar tx-bar-right">
												<li class="tx-list">
													<div unselectable="on" class="tx-btn-nlrbg tx-advanced"
														id="tx_advanced<%=i %>">
														<a href="javascript:;" class="tx-icon" title="툴바 더보기">툴바
															더보기</a>
													</div>
												</li>
											</ul>
										</div>
									</div>
									<!-- 툴바 - 기본 끝 -->
									<!-- 툴바 - 더보기 시작 -->
									<div id="tx_toolbar_advanced<%=i %>"
										class="tx-toolbar tx-toolbar-advanced">
										<div class="tx-toolbar-boundary">
											<ul class="tx-bar tx-bar-left">
												<li class="tx-list">
													<div class="tx-tableedit-title"></div>
												</li>
											</ul>

											<ul class="tx-bar tx-bar-left tx-group-align">
												<li class="tx-list">
													<div unselectable="on" class="tx-btn-lbg tx-mergecells"
														id="tx_mergecells<%=i %>">
														<a href="javascript:;" class="tx-icon2" title="병합">병합</a>
													</div>
													<div id="tx_mergecells_menu<%=i %>"
														class="tx-mergecells-menu tx-menu" unselectable="on"></div>
												</li>
												<li class="tx-list">
													<div unselectable="on" class="tx-btn-bg tx-insertcells"
														id="tx_insertcells<%=i %>">
														<a href="javascript:;" class="tx-icon2" title="삽입">삽입</a>
													</div>
													<div id="tx_insertcells_menu<%=i %>"
														class="tx-insertcells-menu tx-menu" unselectable="on"></div>
												</li>
												<li class="tx-list">
													<div unselectable="on" class="tx-btn-rbg tx-deletecells"
														id="tx_deletecells<%=i %>">
														<a href="javascript:;" class="tx-icon2" title="삭제">삭제</a>
													</div>
													<div id="tx_deletecells_menu<%=i %>"
														class="tx-deletecells-menu tx-menu" unselectable="on"></div>
												</li>
											</ul>

											<ul class="tx-bar tx-bar-left tx-group-align">
												<li class="tx-list">
													<div id="tx_cellslinepreview<%=i %>" unselectable="on"
														class="tx-slt-70lbg tx-cellslinepreview">
														<a href="javascript:;" title="선 미리보기"></a>
													</div>
													<div id="tx_cellslinepreview_menu<%=i %>"
														class="tx-cellslinepreview-menu tx-menu" unselectable="on"></div>
												</li>
												<li class="tx-list">
													<div id="tx_cellslinecolor<%=i %>" unselectable="on"
														class="tx-slt-tbg tx-cellslinecolor">
														<a href="javascript:;" class="tx-icon2" title="선색">선색</a>

														<div class="tx-colorpallete" unselectable="on"></div>
													</div>
													<div id="tx_cellslinecolor_menu<%=i %>"
														class="tx-cellslinecolor-menu tx-menu tx-colorpallete"
														unselectable="on"></div>
												</li>
												<li class="tx-list">
													<div id="tx_cellslineheight<%=i %>" unselectable="on"
														class="tx-btn-bg tx-cellslineheight">
														<a href="javascript:;" class="tx-icon2" title="두께">두께</a>

													</div>
													<div id="tx_cellslineheight_menu<%=i %>"
														class="tx-cellslineheight-menu tx-menu" unselectable="on"></div>
												</li>
												<li class="tx-list">
													<div id="tx_cellslinestyle<%=i %>" unselectable="on"
														class="tx-btn-bg tx-cellslinestyle">
														<a href="javascript:;" class="tx-icon2" title="스타일">스타일</a>
													</div>
													<div id="tx_cellslinestyle_menu<%=i %>"
														class="tx-cellslinestyle-menu tx-menu" unselectable="on"></div>
												</li>
												<li class="tx-list">
													<div id="tx_cellsoutline<%=i %>" unselectable="on"
														class="tx-btn-rbg tx-cellsoutline">
														<a href="javascript:;" class="tx-icon2" title="테두리">테두리</a>

													</div>
													<div id="tx_cellsoutline_menu<%=i %>"
														class="tx-cellsoutline-menu tx-menu" unselectable="on"></div>
												</li>
											</ul>
											<ul class="tx-bar tx-bar-left">
												<li class="tx-list">
													<div id="tx_tablebackcolor<%=i %>" unselectable="on"
														class="tx-btn-lrbg tx-tablebackcolor"
														style="background-color: #9aa5ea;">
														<a href="javascript:;" class="tx-icon2" title="테이블 배경색">테이블
															배경색</a>
													</div>
													<div id="tx_tablebackcolor_menu<%=i %>"
														class="tx-tablebackcolor-menu tx-menu tx-colorpallete"
														unselectable="on"></div>
												</li>
											</ul>
											<ul class="tx-bar tx-bar-left">
												<li class="tx-list">
													<div id="tx_tabletemplate<%=i %>" unselectable="on"
														class="tx-btn-lrbg tx-tabletemplate">
														<a href="javascript:;" class="tx-icon2" title="테이블 서식">테이블
															서식</a>
													</div>
													<div id="tx_tabletemplate_menu<%=i %>"
														class="tx-tabletemplate-menu tx-menu tx-colorpallete"
														unselectable="on"></div>
												</li>
											</ul>

										</div>
									</div>
									<!-- 툴바 - 더보기 끝 -->
									<!-- 편집영역 시작 -->
									<!-- 에디터 Start -->
									<div id="tx_canvas<%=i %>" class="tx-canvas">
										<div id="tx_loading<%=i %>" class="tx-loading">
											<div>
												<img src="/homepage/images/icon/editor/loading2.png" width="113" height="21" align="absmiddle" />
											</div>
										</div>
										<div id="tx_canvas_wysiwyg_holder<%=i %>" class="tx-holder"
											style="display: block;">
											<iframe id="tx_canvas_wysiwyg<%=i %>" name="tx_canvas_wysiwyg" allowtransparency="true" frameborder="0"></iframe>
										</div>
										<div class="tx-source-deco">
											<div id="tx_canvas_source_holder<%=i %>" class="tx-holder">
												<textarea id="tx_canvas_source<%=i %>" rows="30" cols="30"></textarea>
											</div>
										</div>
										<div id="tx_canvas_text_holder<%=i %>" class="tx-holder">
											<textarea id="tx_canvas_text<%=i %>" rows="30" cols="30"></textarea>
										</div>
									</div>
									<!-- 높이조절 Start -->
									<div id="tx_resizer<%=i %>" class="tx-resize-bar">
										<div class="tx-resize-bar-bg"></div>
										<img id="tx_resize_holder<%=i %>" src="/homepage/images/icon/editor/skin/01/btn_drag01.gif" width="58" height="12" unselectable="on" alt="" />
									</div>
									
									<!-- 편집영역 끝 -->
									<!-- 첨부박스 시작 -->
									<!-- 파일첨부박스 Start -->
									<div id="tx_attach_div<%=i %>" class="tx-attach-div">
										<div id="tx_attach_txt<%=i %>" class="tx-attach-txt">파일 첨부</div>
										<div id="tx_attach_box<%=i %>" class="tx-attach-box">
											<div class="tx-attach-box-inner">
												<div id="tx_attach_preview<%=i %>" class="tx-attach-preview">
													<p></p>
													<img src="/homepage/images/icon/editor/pn_preview.gif"
														width="147" height="108" unselectable="on" />
												</div>
												<div class="tx-attach-main">
													<div id="tx_upload_progress<%=i %>" class="tx-upload-progress">
														<div>0%</div>
														<p>파일을 업로드하는 중입니다.</p>
													</div>
													<ul class="tx-attach-top">
														<li id="tx_attach_delete<%=i %>" class="tx-attach-delete"><a>전체삭제</a></li>
														<li id="tx_attach_size<%=i %>" class="tx-attach-size">파일: <span
															id="tx_attach_up_size<%=i %>" class="tx-attach-size-up"></span>/<span
															id="tx_attach_max_size<%=i %>"></span>
														</li>
														<li id="tx_attach_tools<%=i %>" class="tx-attach-tools"></li>
													</ul>
													<ul id="tx_attach_list<%=i %>" class="tx-attach-list"></ul>
												</div>
											</div>
										</div>
									</div>
									<!-- 첨부박스 끝 -->
								</div>
								<!-- 에디터 컨테이너 끝 -->
							</form>
							<div id="board_right<%=i %>" style="height: 30px">
							<%-- <a href="#" onclick='saveContent(<%=i%>)'> <img src="/homepage/images/write.gif" align=right border=0></a> --%>
							 <div style="text-align: right;height: 15px">
							<input type="button" value="수정" class="_42ft _4jy0 _11b _4jy3 _4jy1 selected" onclick='saveContent(<%=i%>)'/>
							<mcjsjs><input type="button" value="취소" class="_42ft _4jy0 _11b _4jy3 _4jy1 selected" id="updateCancel<%=i%>"/></mcjsjs>
							</div>
							<!-- </div> -->
							<!-- </div> -->
						</div>
						
						<textarea id="load_contents_source<%=i %>" style="display:none;"><%=Bdto.getContent() %></textarea>
						
							<script type="text/javascript">
							var config<%=i %> = {
								txHost : '', /* 런타임 시 리소스들을 로딩할 때 필요한 부분으로, 경로가 변경되면 이 부분 수정이 필요. ex) http://xxx.xxx.com */
								txPath : '', /* 런타임 시 리소스들을 로딩할 때 필요한 부분으로, 경로가 변경되면 이 부분 수정이 필요. ex) /xxx/xxx/ */
								txService : 'sample', /* 수정필요없음. */
								txProject : 'sample', /* 수정필요없음. 프로젝트가 여러개일 경우만 수정한다. */
								initializedId : "<%=i %>", /* 대부분의 경우에 빈문자열 */
								wrapper : "tx_trex_container<%=i %>", /* 에디터를 둘러싸고 있는 레이어 이름(에디터 컨테이너) */
								form : 'tx_editor_form<%=i %>' + "", /* 등록하기 위한 Form 이름 */				
								txIconPath : "/homepage/images/icon/editor/", /*에디터에 사용되는 이미지 디렉터리, 필요에 따라 수정한다. */
								txDecoPath : "/homepage/images/deco/contents/", /*본문에 사용되는 이미지 디렉터리, 서비스에서 사용할 때는 완성된 컨텐츠로 배포되기 위해 절대경로로 수정한다. */
								canvas : {
									initHeight : 150, // 높이
									styles : {
										color : "#123456", /* 기본 글자색 */
										fontFamily : "굴림", /* 기본 글자체 */
										fontSize : "10pt", /* 기본 글자크기 */
										backgroundColor : "#fff", /*기본 배경색 */
										lineHeight : "1.5", /*기본 줄간격 */
										padding : "8px" /* 위지윅 영역의 여백 */
									},
									showGuideArea : false
								},
								events : {
									preventUnload : false
								},
								sidebar : {
									attacher: {
									    image: {
									           objattr: {
									        	   width: 450,
									        	   height : 450
									        	   }
									           }
									    },
									attachbox : {
										show : true
									}
								},
								size : {
									contentWidth : 580 /* 지정된 본문영역의 넓이가 있을 경우에 설정 */
								}
							};
							
	
	
							EditorJSLoader.ready(function(Editor) {
								 new Editor(config<%=i %>);
								Editor.getCanvas().observeJob(Trex.Ev.__CANVAS_PANEL_DELETE_SOMETHING, function (ev) {
								    // 데이터중에 존재하지 stage에 존재하지 않는 entry는 박스에서 바로 제거
								    var attachBox = Editor.getAttachBox();
								    attachBox.datalist.each(function (entry) {
								        if (entry.type === "image"  && entry.existStage === false ) {
								            entry.execRemove();
								            enrty = null;
								        }
								    });
								    attachBox.refreshPreview();
								});   
								Editor.getToolbar().observeJob(Trex.Ev.__TOOL_CLICK, function (type) {
								    Editor.switchEditor("<%=i%>");
								});
							});	
						</script>
</div>
<!-- </form> --> <!-- =========================================================================끝 --> 
				
				
				<div class="_5pbx userContent">
					<p><%= Bdto.getContent() %></p>
				</div>
				<div>
					<div>
						<!-- 글에 포함된 사진  -->
						<!-- <div class="mtm">
							<div class="_5cq3" id="u_ps_0_0_o">
								<a class="_4-eo" href="#" rel="theater" style="width: 470px;">
								<div class="_46-h _4-ep" style="width: 470px; height: 352px;" id="u_ps_0_0_p">
										<img class="_46-i img" src="https://fbcdn-sphotos-h-a.akamaihd.net/hphotos-ak-frc3/t1.0-9/q71/s480x480/10275928_759045664127399_7768173795641478267_n.jpg"
											style="left: -5px; top: 0px;" alt="&#xbd84;&#xc2dd;."
											width="480" height="360" />
									</div></a>
							</div>
						</div> -->
					</div>
				</div>
				<!-- <form  method="post" action="like_proc.jsp"  name="boardaction"> -->
			<form action="like_proc.jsp" method="post" name="boardaction">
					
					<div class="clearfix">
						<div class="_5pcp _5vsi lfloat _ohe">
<%
	//like를 한 아이디인지 여부
	vlike = Bdao.board_likeSelect(Bdto.getNo());

	for(int j=0; j<vlike.size();j++){
		String likeid =(String) vlike.get(j);
		/* System.out.println(vlike.size());
		System.out.println(likeid);
		System.out.println(id);
		System.out.println(id.equals(likeid)); */
		if(connectid.equals(likeid)){
			flag = 1;
			break;
		}
		else{
			flag = 0;
		}
		//System.out.println(flag);
	}
%>

<%
	if(flag == 0){
%>							
					 			<a href="javascript:like(<%=Bdto.getNo()%>, '<%=connectid %>')" class="default_message" name="like">좋아요</a>
<%
	}
	else{
%>
								<a href="javascript:unlike(<%=Bdto.getNo()%>, '<%=connectid %>')" class="default_message" name="unlike">좋아요 취소</a>
<%
		flag = 0;
	}
%>						
<!-- 					 	 	<button title="&#xc88b;&#xc544;&#xc694;" type="submit"
								name="like" onclick="fc_click(this, false); return true;"
								class="like_link stat_elem as_link">
								<span class="default_message">좋아요</span>
								<span class="saving_message">좋아요 취소</span>
								
								<button title="&#xc88b;&#xc544;&#xc694;" type="submit"
								name="like" onclick="likeCheck()"
								class="like_link stat_elem as_link">
								<span class="default_message">좋아요</span>
								<span class="saving_message">좋아요 취소</span>
						 	
							</button> -->	
							
							· <mcjsjs><span class="default_message" name="reply" id="replyshow<%=i%>" style="cursor:pointer;">댓글</span></mcjsjs>
							<mcjsjs><input type="hidden" id="divNoCheck<%=i %>" value="<%=i%>"/></mcjsjs>
							 · <a class="uiBlingBox feedbackBling">
							 <i class="img sp_p5WkkL41GeK sx_408c76"></i><span class="text"><%=Bdto.getLike() %></span>
							</a>
						</div>
					</div>
				<!-- 	<div class="uiUfi UFIContainer _5pc9 _5vsj _5v9k" id="u_ps_0_0_1b"></div> -->
				</form>
			</div>
<!-- 댓글 디자인  -->
			<form method="post" action="like_proc.jsp" >
			<div id="replydiv<%=i%>" style="background-color: #E6FFFF;">
			<input type="hidden" name="command" id="command" value="REPLYPOST"> 
			<input type="hidden" name="no" id="no" value="<%=Bdto.getNo()%>"/>
			<input type="hidden" name="id" value="<%=connectid%>"/>
				<hr/>
					<div class="_3dp _29k" ">
						<div>
							<div class="_6a">
								<div class="_6a _6b" style="margin-left: 7px" >
									<h5 class="_5pbw">
										<div class="fwn fcg">
										<span class="fwb fcg">
										<a href="#" style="font-size: 14px;"><%=connectid%></a>&nbsp;&nbsp;
										<textarea name="content" cols="" rows="1" class="DOMControl_placeholder uiTextareaAutogrow input autofocus mentionsTextarea textInput" onKeyDown="setLine(this)" style="width: 393px; resize:none;"></textarea>&nbsp;&nbsp;
										<button value="1" class="_42ft _4jy0 _11b _4jy3 _4jy1 selected" type="submit">댓글</button>
										</span>
										<br/><br/>
										</div></h5>
										</div>
			</form>	
<%
	//댓글 반복문
	Vector Rlist = Bdao.getReplyList(Bdto.getNo());

	for(int r = 0; r<Rlist.size();r++){
		ReplyDto Rdto = new ReplyDto();
		Rdto = (ReplyDto) Rlist.get(r);
%>
					<div style="height: 50px;margin-left: 7px" >
								<a class="_5pb8 _5v9u _29h _303" href="https://www.facebook.com/jooj.kim?fref=nf">
					<!-- 프로필 이미지  -->
					<img class="_s0 _5xib _5sq7 _rw img" src="/homepage/profile/<%=Rdto.getPhoto() %>" alt="" align="middle" style="width: 40px;height: 40px" /></a>
										<span class="fwb fcg">
										<a href="#" style="font-size: 14px;"><%= Rdto.getId() %></a>&nbsp;&nbsp;</span><span><%=Rdto.getContent() %></span>&nbsp;&nbsp;&nbsp;&nbsp;
<%
	//댓글 작성자 아이디 접속 아이디와 비교
	if(Rdto.getId().equals(connectid)){
%>
										<a href="#" onclick="replyDelete(<%=Rdto.getNo()%>)">...삭제</a>
<%
	}
%>							
										<br/>
										<div class="_5pcp" style="margin-top: 5px">
										<span><%=Rdto.getRegist_day() %></span>
										</div>
									</h5>
									</div>
					
<%
	}
	//댓글 반복문 끝
%>
						</div>
					<!-- 리스트div  -->			
					</div>
				</div>

			</div>
		</div>
	</div>
<%
	}
	//리스트 for문 끝
%>

<div align="center"><span style="cursor: pointer;" onclick="moreboard(<%=numperpage+5%>)">..더보기</span></div>

<!-- like 스크립트를 위한 폼 시작  -->
<form action="like_proc.jsp" method="post" name="Baction">
	<input type="hidden" name="command" value="LIKE" />
	
	<input type="hidden" name="no" />
	<input type="hidden" name="id" />
</form>
<form action="like_proc.jsp" method="post" name="Baction1">
	<input type="hidden" name="command" value="UNLIKE" />
	
	<input type="hidden" name="no" />
	<input type="hidden" name="id" />
</form>
<form action="like_proc.jsp" method="post" name="Baction2">
	<input type="hidden" name="command" value="DELETE" />
	
	<input type="hidden" name="no" />
</form>
<form action="like_proc.jsp" method="post" name="Raction">
	<input type="hidden" name="command" value="REPLYDELETE" />

	<input type="hidden" name="no" />
</form>
<form action="main.jsp" method="post" name="more">
	<input type="hidden" name="numperpage"/>
</form>
<!-- like 스크립트를 위한 끝  -->

				</section>
<!-- -----------------------------=========================================================내용끝 ============================================== -->
				<!-- ASIDE NAV 2 -->
				<aside class="s-12 l-five">
					<jsp:include page="/Friend.jsp"/>
				</aside>
			</div>
		</div>
	</div>
	<!-- FOOTER -->
	<footer class="box">
		<jsp:include page="/footer.html"/>
	</footer>

</body>
</html>