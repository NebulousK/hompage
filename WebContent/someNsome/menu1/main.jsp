<%@page import="homepage.someDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width">
<title>Responsive Aside website template</title>

<link type="text/css" rel="stylesheet" href="/homepage/css/components.css">
<link type="text/css" rel="stylesheet" href="/homepage/css/responsee.css">
<link type="text/css" rel="stylesheet" href="/homepage/css/template-style.css">

<link type="text/css" rel="stylesheet" href="/homepage/css/luYzpvna9qk.css" />
<link type="text/css" rel="stylesheet" href="/homepage/css/oBwXKrMsLPd.css" />
<link type="text/css" rel="stylesheet" href="/homepage/css/k4p1J0mZLFW.css" />
<link type="text/css" rel="stylesheet" href="/homepage/css/u4n0TOW16uV.css" />
<link type="text/css" rel="stylesheet" href="/homepage/css/aO5MXY7tAfJ.css" />
<script type="text/javascript" src="/homepage/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="/homepage/js/jquery-ui.min.js"></script>
<script type="text/javascript" src="/homepage/js/modernizr.js"></script>
<script type="text/javascript" src="/homepage/js/responsee.js"></script>
<link rel="stylesheet" href="/homepage/css/editor.css" type="text/css" charset="utf-8" />
<script src="/homepage/js/editor_loader.js" type="text/javascript" charset="utf-8"></script>
<jsp:useBean id="dao" class="homepage.someDao"/>
<jsp:useBean id="dto" class="homepage.someDto"/>
</head>
<body class="size-1140">
	<!-- TOP NAV WITH LOGO -->
	<header>
		<nav>
			<div class="line">
				<div class="s-12 l-2">
					<img class="s-5 l-12 center" src="/homepage/images/logo.png">
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
					<jsp:include page="/menu.jsp" />
				</aside>
				<!-- CONTENT -->
				<section class="s-12 l-7">
					<!--============================================================================= 글쓰기 시작 -->
					<div class="_4-u2 mbm">
						<div id="main_border_down" style="width: 580px;">
							<form name="tx_editor_form" id="tx_editor_form" action="proc.jsp" method="post" accept-charset="utf-8">
								<div id="board_title">
								<input type="hidden" name="action" id="action" value="insert">
								<input type="hidden" name="id" id="id" value="${sessionScope.id}">
								<input type="hidden" name="no" id="no" value="${sessionScope.no}">
									<!-- 타이틀 -->
								</div>
								<div id='blnk2' style="height: 0px;">&nbsp;&nbsp;&nbsp;</div>
								<!-- 에디터 컨테이너 시작 -->
								<div id="tx_trex_container" class="tx-editor-container">
									<!-- 사이드바 -->
									<div id="tx_sidebar" class="tx-sidebar">
										<div class="tx-sidebar-boundary">
											<!-- 사이드바 / 첨부 -->
											<ul class="tx-bar tx-bar-left tx-nav-attach">
												<!-- 이미지 첨부 버튼 시작 -->
												<!-- @decsription <li></li> 단위로 위치를 이동할 수 있다.-->
												<li class="tx-list">
													<div unselectable="on" id="tx_image" class="tx-image tx-btn-trans">
														<a href="javascript:;" title="사진" class="tx-text">사진</a>
													</div>
												</li>
												<!-- 이미지 첨부 버튼 끝 -->
												<li class="tx-list">
													<div unselectable="on" id="tx_file" class="tx-file tx-btn-trans">
														<a href="javascript:;" title="파일" class="tx-text">파일</a>
													</div>
												</li>
												<li class="tx-list">
													<div unselectable="on" id="tx_media" class="tx-media tx-btn-trans">
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
													<div unselectable="on" class="tx-btn-lrbg tx-fullscreen" id="tx_fullscreen">
														<a href="javascript:;" class="tx-icon"
															title="넓게쓰기 (Ctrl+M)">넓게쓰기</a>
													</div>
												</li>
											</ul>
											<ul class="tx-bar tx-bar-right tx-nav-opt">
												<li class="tx-list">
													<div unselectable="on" class="tx-switchtoggle" id="tx_switchertoggle">
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
									<div id="tx_toolbar_basic" class="tx-toolbar tx-toolbar-basic">
										<div class="tx-toolbar-boundary">
											<ul class="tx-bar tx-bar-left">
												<li class="tx-list">
													<div id="tx_fontfamily" unselectable="on" class="tx-slt-70bg tx-fontfamily">
														<a href="javascript:;" title="글꼴">굴림</a>
													</div>
													<div id="tx_fontfamily_menu" class="tx-fontfamily-menu tx-menu" unselectable="on"></div>
												</li>
											</ul>
											<ul class="tx-bar tx-bar-left">
												<li class="tx-list">
													<div unselectable="on" class="tx-slt-42bg tx-fontsize" id="tx_fontsize">
														<a href="javascript:;" title="글자크기">9pt</a>
													</div>
													<div id="tx_fontsize_menu" class="tx-fontsize-menu tx-menu" unselectable="on"></div>
												</li>
											</ul>
											<ul class="tx-bar tx-bar-left tx-group-font">

												<li class="tx-list">
													<div unselectable="on" class="tx-btn-lbg tx-bold" id="tx_bold">
														<a href="javascript:;" class="tx-icon" title="굵게 (Ctrl+B)">굵게</a>
													</div>
												</li>
												<li class="tx-list">
													<div unselectable="on" class="tx-btn-bg tx-underline" id="tx_underline">
														<a href="javascript:;" class="tx-icon" title="밑줄 (Ctrl+U)">밑줄</a>
													</div>
												</li>
												<li class="tx-list">
													<div unselectable="on" class="tx-btn-bg tx-italic" id="tx_italic">
														<a href="javascript:;" class="tx-icon"
															title="기울임 (Ctrl+I)">기울임</a>
													</div>
												</li>
												<li class="tx-list">
													<div unselectable="on" class="tx-btn-bg tx-strike" id="tx_strike">
														<a href="javascript:;" class="tx-icon"
															title="취소선 (Ctrl+D)">취소선</a>
													</div>
												</li>
												<li class="tx-list">
													<div unselectable="on" class="tx-slt-tbg tx-forecolor" id="tx_forecolor">
														<a href="javascript:;" class="tx-icon" title="글자색">글자색</a>
														<a href="javascript:;" class="tx-arrow" title="글자색 선택">글자색
															선택</a>
													</div>
													<div id="tx_forecolor_menu"
														class="tx-menu tx-forecolor-menu tx-colorpallete" unselectable="on"></div>
												</li>
												<li class="tx-list">
													<div unselectable="on" class="tx-slt-brbg tx-backcolor" id="tx_backcolor">
														<a href="javascript:;" class="tx-icon" title="글자 배경색">글자
															배경색</a> <a href="javascript:;" class="tx-arrow"
															title="글자 배경색 선택">글자 배경색 선택</a>
													</div>
													<div id="tx_backcolor_menu"
														class="tx-menu tx-backcolor-menu tx-colorpallete" unselectable="on"></div>
												</li>
											</ul>
											<ul class="tx-bar tx-bar-left tx-group-align">
												<li class="tx-list">
													<div unselectable="on" class="tx-btn-lbg tx-alignleft" id="tx_alignleft">
														<a href="javascript:;" class="tx-icon"
															title="왼쪽정렬 (Ctrl+,)">왼쪽정렬</a>
													</div>
												</li>
												<li class="tx-list">
													<div unselectable="on" class="tx-btn-bg tx-aligncenter" id="tx_aligncenter">
														<a href="javascript:;" class="tx-icon"
															title="가운데정렬 (Ctrl+.)">가운데정렬</a>
													</div>
												</li>
												<li class="tx-list">
													<div unselectable="on" class="tx-btn-bg tx-alignright" id="tx_alignright">
														<a href="javascript:;" class="tx-icon"
															title="오른쪽정렬 (Ctrl+/)">오른쪽정렬</a>
													</div>
												</li>
												<li class="tx-list">
													<div unselectable="on" class="tx-btn-rbg tx-alignfull" id="tx_alignfull">
														<a href="javascript:;" class="tx-icon" title="양쪽정렬">양쪽정렬</a>
													</div>
												</li>
											</ul>
											<ul class="tx-bar tx-bar-left tx-group-tab">
												<li class="tx-list">
													<div unselectable="on" class="		 tx-btn-lbg 	tx-indent"
														id="tx_indent">
														<a href="javascript:;" title="들여쓰기 (Tab)" class="tx-icon">들여쓰기</a>
													</div>
												</li>
												<li class="tx-list">
													<div unselectable="on" class="		 tx-btn-rbg 	tx-outdent"
														id="tx_outdent">
														<a href="javascript:;" title="내어쓰기 (Shift+Tab)"
															class="tx-icon">내어쓰기</a>
													</div>
												</li>
											</ul>
											<ul class="tx-bar tx-bar-left tx-group-list">
												<li class="tx-list">
													<div unselectable="on" class="tx-slt-31lbg tx-lineheight"
														id="tx_lineheight">
														<a href="javascript:;" class="tx-icon" title="줄간격">줄간격</a>
														<a href="javascript:;" class="tx-arrow" title="줄간격">줄간격
															선택</a>
													</div>
													<div id="tx_lineheight_menu"
														class="tx-lineheight-menu tx-menu" unselectable="on"></div>
												</li>
												<li class="tx-list">
													<div unselectable="on" class="tx-slt-31rbg tx-styledlist"
														id="tx_styledlist">
														<a href="javascript:;" class="tx-icon" title="리스트">리스트</a>
														<a href="javascript:;" class="tx-arrow" title="리스트">리스트
															선택</a>
													</div>
													<div id="tx_styledlist_menu"
														class="tx-styledlist-menu tx-menu" unselectable="on"></div>
												</li>
											</ul>
											<ul class="tx-bar tx-bar-left tx-group-etc">
												<li class="tx-list">
													<div unselectable="on" class="		 tx-btn-lbg 	tx-emoticon"
														id="tx_emoticon">
														<a href="javascript:;" class="tx-icon" title="이모티콘">이모티콘</a>
													</div>
													<div id="tx_emoticon_menu" class="tx-emoticon-menu tx-menu"
														unselectable="on"></div>
												</li>
												<li class="tx-list">
													<div unselectable="on" class="		 tx-btn-bg 	tx-link"
														id="tx_link">
														<a href="javascript:;" class="tx-icon" title="링크 (Ctrl+K)">링크</a>
													</div>
													<div id="tx_link_menu" class="tx-link-menu tx-menu"></div>
												</li>
												<li class="tx-list">
													<div unselectable="on" class="		 tx-btn-bg 	tx-specialchar"
														id="tx_specialchar">
														<a href="javascript:;" class="tx-icon" title="특수문자">특수문자</a>
													</div>
													<div id="tx_specialchar_menu"
														class="tx-specialchar-menu tx-menu"></div>
												</li>
												<li class="tx-list">
													<div unselectable="on" class="		 tx-btn-bg 	tx-table"
														id="tx_table">
														<a href="javascript:;" class="tx-icon" title="표만들기">표만들기</a>
													</div>
													<div id="tx_table_menu" class="tx-table-menu tx-menu"
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
														id="tx_horizontalrule">
														<a href="javascript:;" class="tx-icon" title="구분선">구분선</a>
													</div>
													<div id="tx_horizontalrule_menu"
														class="tx-horizontalrule-menu tx-menu" unselectable="on"></div>
												</li>
											</ul>
											<ul class="tx-bar tx-bar-left">
												<li class="tx-list">
													<div unselectable="on"
														class="		 tx-btn-lbg 	tx-richtextbox" id="tx_richtextbox">
														<a href="javascript:;" class="tx-icon" title="글상자">글상자</a>
													</div>
													<div id="tx_richtextbox_menu"
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
														id="tx_quote">
														<a href="javascript:;" class="tx-icon"
															title="인용구 (Ctrl+Q)">인용구</a>
													</div>
													<div id="tx_quote_menu" class="tx-quote-menu tx-menu"
														unselectable="on"></div>
												</li>
												<li class="tx-list">
													<div unselectable="on" class="		 tx-btn-bg 	tx-background"
														id="tx_background">
														<a href="javascript:;" class="tx-icon" title="배경색">배경색</a>
													</div>
													<div id="tx_background_menu"
														class="tx-menu tx-background-menu tx-colorpallete"
														unselectable="on"></div>
												</li>
												<li class="tx-list">
													<div unselectable="on" class="		 tx-btn-rbg 	tx-dictionary"
														id="tx_dictionary">
														<a href="javascript:;" class="tx-icon" title="사전">사전</a>
													</div>
												</li>
											</ul>
											<ul class="tx-bar tx-bar-left tx-group-undo">
												<li class="tx-list">
													<div unselectable="on" class="		 tx-btn-lbg 	tx-undo"
														id="tx_undo">
														<a href="javascript:;" class="tx-icon"
															title="실행취소 (Ctrl+Z)">실행취소</a>
													</div>
												</li>
												<li class="tx-list">
													<div unselectable="on" class="		 tx-btn-rbg 	tx-redo"
														id="tx_redo">
														<a href="javascript:;" class="tx-icon"
															title="다시실행 (Ctrl+Y)">다시실행</a>
													</div>
												</li>
											</ul>
											<ul class="tx-bar tx-bar-right">
												<li class="tx-list">
													<div unselectable="on" class="tx-btn-nlrbg tx-advanced"
														id="tx_advanced">
														<a href="javascript:;" class="tx-icon" title="툴바 더보기">툴바
															더보기</a>
													</div>
												</li>
											</ul>
										</div>
									</div>
									<!-- 툴바 - 기본 끝 -->
									<!-- 툴바 - 더보기 시작 -->
									<div id="tx_toolbar_advanced"
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
														id="tx_mergecells">
														<a href="javascript:;" class="tx-icon2" title="병합">병합</a>
													</div>
													<div id="tx_mergecells_menu"
														class="tx-mergecells-menu tx-menu" unselectable="on"></div>
												</li>
												<li class="tx-list">
													<div unselectable="on" class="tx-btn-bg tx-insertcells"
														id="tx_insertcells">
														<a href="javascript:;" class="tx-icon2" title="삽입">삽입</a>
													</div>
													<div id="tx_insertcells_menu"
														class="tx-insertcells-menu tx-menu" unselectable="on"></div>
												</li>
												<li class="tx-list">
													<div unselectable="on" class="tx-btn-rbg tx-deletecells"
														id="tx_deletecells">
														<a href="javascript:;" class="tx-icon2" title="삭제">삭제</a>
													</div>
													<div id="tx_deletecells_menu"
														class="tx-deletecells-menu tx-menu" unselectable="on"></div>
												</li>
											</ul>

											<ul class="tx-bar tx-bar-left tx-group-align">
												<li class="tx-list">
													<div id="tx_cellslinepreview" unselectable="on"
														class="tx-slt-70lbg tx-cellslinepreview">
														<a href="javascript:;" title="선 미리보기"></a>
													</div>
													<div id="tx_cellslinepreview_menu"
														class="tx-cellslinepreview-menu tx-menu" unselectable="on"></div>
												</li>
												<li class="tx-list">
													<div id="tx_cellslinecolor" unselectable="on"
														class="tx-slt-tbg tx-cellslinecolor">
														<a href="javascript:;" class="tx-icon2" title="선색">선색</a>

														<div class="tx-colorpallete" unselectable="on"></div>
													</div>
													<div id="tx_cellslinecolor_menu"
														class="tx-cellslinecolor-menu tx-menu tx-colorpallete"
														unselectable="on"></div>
												</li>
												<li class="tx-list">
													<div id="tx_cellslineheight" unselectable="on"
														class="tx-btn-bg tx-cellslineheight">
														<a href="javascript:;" class="tx-icon2" title="두께">두께</a>

													</div>
													<div id="tx_cellslineheight_menu"
														class="tx-cellslineheight-menu tx-menu" unselectable="on"></div>
												</li>
												<li class="tx-list">
													<div id="tx_cellslinestyle" unselectable="on"
														class="tx-btn-bg tx-cellslinestyle">
														<a href="javascript:;" class="tx-icon2" title="스타일">스타일</a>
													</div>
													<div id="tx_cellslinestyle_menu"
														class="tx-cellslinestyle-menu tx-menu" unselectable="on"></div>
												</li>
												<li class="tx-list">
													<div id="tx_cellsoutline" unselectable="on"
														class="tx-btn-rbg tx-cellsoutline">
														<a href="javascript:;" class="tx-icon2" title="테두리">테두리</a>

													</div>
													<div id="tx_cellsoutline_menu"
														class="tx-cellsoutline-menu tx-menu" unselectable="on"></div>
												</li>
											</ul>
											<ul class="tx-bar tx-bar-left">
												<li class="tx-list">
													<div id="tx_tablebackcolor" unselectable="on"
														class="tx-btn-lrbg tx-tablebackcolor"
														style="background-color: #9aa5ea;">
														<a href="javascript:;" class="tx-icon2" title="테이블 배경색">테이블
															배경색</a>
													</div>
													<div id="tx_tablebackcolor_menu"
														class="tx-tablebackcolor-menu tx-menu tx-colorpallete"
														unselectable="on"></div>
												</li>
											</ul>
											<ul class="tx-bar tx-bar-left">
												<li class="tx-list">
													<div id="tx_tabletemplate" unselectable="on"
														class="tx-btn-lrbg tx-tabletemplate">
														<a href="javascript:;" class="tx-icon2" title="테이블 서식">테이블
															서식</a>
													</div>
													<div id="tx_tabletemplate_menu"
														class="tx-tabletemplate-menu tx-menu tx-colorpallete"
														unselectable="on"></div>
												</li>
											</ul>

										</div>
									</div>
									<!-- 툴바 - 더보기 끝 -->
									<!-- 편집영역 시작 -->
									<!-- 에디터 Start -->
									<div id="tx_canvas" class="tx-canvas">
										<div id="tx_loading" class="tx-loading">
											<div>
												<img src="/homepage/images/icon/editor/loading2.png"
													width="113" height="21" align="absmiddle" />
											</div>
										</div>
										<div id="tx_canvas_wysiwyg_holder" class="tx-holder"
											style="display: block;">
											<iframe id="tx_canvas_wysiwyg" name="tx_canvas_wysiwyg"
												allowtransparency="true" frameborder="0"></iframe>
										</div>
										<div class="tx-source-deco">
											<div id="tx_canvas_source_holder" class="tx-holder">
												<textarea id="tx_canvas_source" rows="30" cols="30"></textarea>
											</div>
										</div>
										<div id="tx_canvas_text_holder" class="tx-holder">
											<textarea id="tx_canvas_text" rows="30" cols="30"></textarea>
										</div>
									</div>
									<!-- 높이조절 Start -->
									<div id="tx_resizer" class="tx-resize-bar">
										<div class="tx-resize-bar-bg"></div>
										<img id="tx_resize_holder" src="/homepage/images/icon/editor/skin/01/btn_drag01.gif" width="58" height="12" unselectable="on" alt="" />
									</div>
									
									<!-- 편집영역 끝 -->
									<!-- 첨부박스 시작 -->
									<!-- 파일첨부박스 Start -->
									<div id="tx_attach_div" class="tx-attach-div">
										<div id="tx_attach_txt" class="tx-attach-txt">파일 첨부</div>
										<div id="tx_attach_box" class="tx-attach-box">
											<div class="tx-attach-box-inner">
												<div id="tx_attach_preview" class="tx-attach-preview">
													<p></p>
													<img src="/homepage/images/icon/editor/pn_preview.gif"
														width="147" height="108" unselectable="on" />
												</div>
												<div class="tx-attach-main">
													<div id="tx_upload_progress" class="tx-upload-progress">
														<div>0%</div>
														<p>파일을 업로드하는 중입니다.</p>
													</div>
													<ul class="tx-attach-top">
														<li id="tx_attach_delete" class="tx-attach-delete"><a>전체삭제</a></li>
														<li id="tx_attach_size" class="tx-attach-size">파일: <span
															id="tx_attach_up_size" class="tx-attach-size-up"></span>/<span
															id="tx_attach_max_size"></span>
														</li>
														<li id="tx_attach_tools" class="tx-attach-tools"></li>
													</ul>
													<ul id="tx_attach_list" class="tx-attach-list"></ul>
												</div>
											</div>
										</div>
									</div>
									<!-- 첨부박스 끝 -->
								</div>
								<!-- 에디터 컨테이너 끝 -->
							</form>
							<div id="board_right" style="height: 30px">
							<!-- <button onclick='saveContent()' align=right><img src="/board/freeboard/image/write.gif" align=center border=0 ></button> -->
							<!-- <div style="text-align: right;height: 15px"> -->
							<a href="#" onclick='saveContent()'> <img src="/homepage/images/write.gif" align=right border=0></a>
							<!-- </div> -->
							<!-- </div> -->
						</div>
						<script type="text/javascript">
							var config = {
								txHost : '', /* 런타임 시 리소스들을 로딩할 때 필요한 부분으로, 경로가 변경되면 이 부분 수정이 필요. ex) http://xxx.xxx.com */
								txPath : '', /* 런타임 시 리소스들을 로딩할 때 필요한 부분으로, 경로가 변경되면 이 부분 수정이 필요. ex) /xxx/xxx/ */
								txService : 'sample', /* 수정필요없음. */
								txProject : 'sample', /* 수정필요없음. 프로젝트가 여러개일 경우만 수정한다. */
								initializedId : "", /* 대부분의 경우에 빈문자열 */
								wrapper : "tx_trex_container", /* 에디터를 둘러싸고 있는 레이어 이름(에디터 컨테이너) */
								form : 'tx_editor_form' + "", /* 등록하기 위한 Form 이름 */
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
								var editor = new Editor(config);
							});	
							
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
						</script>

						<!-- Sample: Saving Contents -->
						<script type="text/javascript">
							/* 예제용 함수 */
							function saveContent() {
								Editor.save(); // 이 함수를 호출하여 글을 등록하면 된다.		
							}

							/**
							 * Editor.save()를 호출한 경우 데이터가 유효한지 검사하기 위해 부르는 콜백함수로
							 * 상황에 맞게 수정하여 사용한다.
							 * 모든 데이터가 유효할 경우에 true를 리턴한다.
							 * @function
							 * @param {Object} editor - 에디터에서 넘겨주는 editor 객체
							 * @returns {Boolean} 모든 데이터가 유효할 경우에 true
							 */
							function validForm(editor) {
								// Place your validation logic here

								// sample : validate that content exists
								var validator = new Trex.Validator();
								var content = editor.getContent();
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
							function setForm(editor) {
								var formGenerator = editor.getForm();
								var content = editor.getContent();
								formGenerator.createField(tx.textarea({
									'name' : "content", // 본문 내용을 필드를 생성하여 값을 할당하는 부분
									'style' : {
										'display' : "none"
									}
								}, content));
								/* 아래의 코드는 첨부된 데이터를 필드를 생성하여 값을 할당하는 부분으로 상황에 맞게 수정하여 사용한다.
								 첨부된 데이터 중에 주어진 종류(image,file..)에 해당하는 것만 배열로 넘겨준다. */
								var images = editor.getAttachments('image');
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
								var files = editor.getAttachments('file');
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
					</div>
					<!-- ------------------------------------------------------------------글 쓰기 끝------------------------------------------------------------------------- -->
					<!-- ==================================================================내용 시작=========================================================================== -->
					<%
						int totalRecord = 0; //총
						int numPerPage = 10;  //한페이지
						int pagePerBlock = 3; //한 블럭당 페이지수
						int totalPage = 0;  //전체 페이지수
						int totalBlock = 0; //전체 블락수
						int nowPage = 0;  // 현제 페이지 번호
						int nowBlock = 0; // 현제 블럭 번호
						int beginPerPage = 0; //페이지당 시작 번호
						
						ArrayList<someDto> g = dao.some_board_list(Integer.parseInt((String) session.getAttribute("no")));
						
						totalRecord = g.size();
			        	totalPage = (int) Math.ceil(((double)totalRecord/numPerPage));
			        	beginPerPage = nowPage * numPerPage;
			            totalBlock = (int) Math.ceil(((double)totalPage/pagePerBlock));
						if(g.size() < 0){
					%>
						작성된 글이 없습니다.
					<%
						}else{
							for(int i=beginPerPage; i<beginPerPage + numPerPage; i++){
								if(i == totalRecord){
									break;
								}
							    dto = (someDto) g.get(i);
					%>
					<div>
						<div class="_4-u2 mbm _5jmm _5pat _5v3q _5sq8 _5x16" id="u_ps_0_0_m">
							<div class="userContentWrapper _5pcr _3ccb">
								<div class="_4r_y">
									<div class="_6a uiPopover _5pbi _5puc _5v56" id="u_ps_0_0_n">
										<a class="_5pbj _p" href="#" id="u_ps_0_0_w"></a>
									</div>
								</div>
								<div class="clearfix _5x46">
									<a class="_5pb8 _5v9u _29h _303" href="https://www.facebook.com/jooj.kim?fref=nf"> 
									<img class="_s0 _5xib _5sq7 _rw img" src="/homepage/profile/${sessionScope.photo}" alt="" /></a>
									<div class="_3dp _29k">
										<div>
											<div class="_6a">
												<div class="_6a _6b" style="height: 40px"></div>
												<div class="_6a _6b">
													<h5 class="_5pbw">
														<div class="fwn fcg">
															<span class="fwb fcg"> 
															<a href="https://www.facebook.com/jooj.kim?fref=nf"><%=dto.getName() %></a></span>
														</div>
													</h5>
													<div class="_5pcp">
														<span><span class="fsm fwn fcg"> 
														<a class="_5pcq" href="#" rel="theater"> 
														<abbr title="2014&#xb144; 5&#xc6d4; 19&#xc77c; &#xc6d4;&#xc694;&#xc77c; &#xc624;&#xc804; 4:21"
															  data-utime="1400440877" data-shorten="1"
															  class="_5ptz timestamp livetimestamp"><%=dto.getDate() %></abbr></a></span></span> · <a
															class="uiStreamPrivacy inlineBlock fbStreamPrivacy fbPrivacyAudienceIndicator _5pcq"
															href="#" role="button" id="u_ps_0_0_1o"> <i
															class="lock img sp_tl_T4GR6Alw sx_151e86"></i></a>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="_5pbx userContent">
									<p><%=dto.getContent() %></p>
								</div>
								<form
									class="live_759045664127399_316526391751760 commentable_item autoexpand_mode"
									method="post" action="#" id="u_ps_0_0_15">
									<div class="clearfix">
										<div class="_5pcp _5vsi lfloat _ohe">
											<button title="&#xc88b;&#xc544;&#xc694;" type="submit"
												name="like" onclick="fc_click(this, false); return true;"
												class="like_link stat_elem as_link">
												<span class="default_message">좋아요</span><span
													class="saving_message">좋아요 취소</span>
											</button>
											· <label class="uiLinkButton comment_link"
												title="&#xb313;&#xae00; &#xb0a8;&#xae30;&#xae30;"> <input
												class="uiLinkButtonInput" type="button"
												value="&#xb313;&#xae00; &#xb2ec;&#xae30;"
												onclick="return fc_click(this);" /></label> · <a
												class="share_action_link" href="#">공유하기</a> · <a
												class="uiBlingBox feedbackBling"> <i
												class="img sp_p5WkkL41GeK sx_408c76"></i><span class="text"><%=dto.getLike() %></span>
												<i class="mls img sp_p5WkkL41GeK sx_b783d0"></i><span
												class="text"><%=dto.getHit() %></span> <i
												class="mls img sp_p5WkkL41GeK sx_e57ebd"></i><span
												class="text">658</span></a>
										</div>
									</div>
									<div class="uiUfi UFIContainer _5pc9 _5vsj _5v9k"
										id="u_ps_0_0_1b"></div>
								</form>
							</div>
						</div>
					</div>
					<%		}
						}
					%>
					<!-- -----------------------------=========================================================내용끝 ============================================== -->
					<!-- ==================================================================내용 시작=========================================================================== -->
				</section>
				<!-- ASIDE NAV 2 -->
				<aside class="s-12 l-five">
					<jsp:include page="/Friend.jsp" />
				</aside>
			</div>
		</div>
	</div>
	<!-- FOOTER -->
	<footer class="box">
		<jsp:include page="/footer.html" />
	</footer>

</body>
</html>