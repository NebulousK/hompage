								
										<a class='_5pb8 _5v9u _29h _303' href='/homepage/itsme/main2.jsp?id=" +dto.getId()+ "'> <img
											class='_s0 _5xib _5sq7 _rw img'
											src='/homepage/profile/" +dto.getPhoto()+ "' alt=''
											align='middle' /></a>
									</td>
									<td style='border: 0px; text-align: left;'><span
										class='fwb fcg'> <a
											href='/homepage/itsme/main2.jsp?id=" +dto.getId()+ "'
											style='font-size: 14px;'>" + dto.getId() + "</a></span><br />
										<div class='_5pcp' style='margin-top: 5px'>
											<span>" + dto.getDay()+ "</span>
										</div></td>
									<td style='border: 0px; text-align: right;'>
										if(dto.getId().equals(session.getAttribute("id"))){ + "
										<div class='dropdown'>
											<a class='dropdown-toggle' id='dLabel' role='button'
												data-toggle='dropdown' data-target='#' href='/page.html'>
												<b class='caret'></b>
											</a>
											<ul class='dropdown-menu' role='menu'
												aria-labelledby='dLabel'>
												<li
													style='text-align: center; font-size: 12px; line-height: 25px;'>
													<span id='updateclick" +i+ "'>수&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;정</span>
												</li>
												<mcjsjs>
												<input type='hidden' id='divNoCheck" +i + "' value='" +i+ "' /></mcjsjs>
												<input type='hidden' id='boardId' value='" +dto.getId() + "' />
												<input type='hidden' id='session.getAttribute("id")' value='" +session.getAttribute("id") + "' />
												<li
													style='text-align: center; font-size: 12px; line-height: 25px'>
													<span onclick='Bdelete(" +dto.getNo()+ ")'>삭&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;제</span>
												</li>
											</ul>
										</div> 
										
										if 끝
									</td>
								</tr>
							</table>
						</div>
					</h5>
					<div class='_5pcp'>
						<span>" + dto.getDay()+ "</span>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<div class='_55d0' id='testdiv" +i+ "'>
	<form name='tx_editor_form" +i + "' id='tx_editor_form" +i + "'
		action='/homepage/luvstoryupdate.some' method='post'
		accept-charset='utf-8'>
		<div id='board_title'>
			<input type='hidden' name='id' id='id' value='session.getAttribute("no")'>
			<input type='hidden' name='no' value='" +dto.getNo()+ "' />
		</div>
		<div id='blnk2' style='height: 0px;'>&nbsp;&nbsp;&nbsp;</div>
		<div id='tx_trex_container" +i + "' class='tx-editor-container'>
			<div id='tx_sidebar" +i + "' class='tx-sidebar'>
				<div class='tx-sidebar-boundary'>
					<ul class='tx-bar tx-bar-left tx-nav-attach'>
						<li class='tx-list'>
							<div unselectable='on' id='tx_image" +i + "'
								class='tx-image tx-btn-trans'>
								<a href='javascript:;' title='사진' class='tx-text'>사진</a>
							</div>
						</li>
						<li class='tx-list'>
							<div unselectable='on' id='tx_file" +i + "'
								class='tx-file tx-btn-trans'>
								<a href='javascript:;' title='파일' class='tx-text'>파일</a>
							</div>
						</li>
						<li class='tx-list'>
							<div unselectable='on' id='tx_media" +i + "'
								class='tx-media tx-btn-trans'>
								<a href='javascript:;' title='외부컨텐츠' class='tx-text'>외부컨텐츠</a>
							</div>
						</li>
						<li class='tx-list tx-list-extra'>
							<div unselectable='on' class='tx-btn-nlrbg tx-extra'>
								<a href='javascript:;' class='tx-icon' title='버튼 더보기'>버튼 더보기</a>
							</div>
							<ul class='tx-extra-menu tx-menu' style='left: -48px;'
								unselectable='on'>
							</ul>
						</li>
					</ul>
					<ul class='tx-bar tx-bar-right'>
						<li class='tx-list'>
							<div unselectable='on' class='tx-btn-lrbg tx-fullscreen'
								id='tx_fullscreen" +i + "'>
								<a href='javascript:;' class='tx-icon' title='넓게쓰기 (Ctrl+M)'>넓게쓰기</a>
							</div>
						</li>
					</ul>
					<ul class='tx-bar tx-bar-right tx-nav-opt'>
						<li class='tx-list'>
							<div unselectable='on' class='tx-switchtoggle'
								id='tx_switchertoggle" +i + "'>
								<a href='javascript:;' title='에디터 타입'>에디터</a>
							</div>
						</li>
					</ul>
				</div>
			</div>
			<div id='tx_toolbar_basic" +i + "' class='tx-toolbar tx-toolbar-basic'>
				<div class='tx-toolbar-boundary'>
					<ul class='tx-bar tx-bar-left'>
						<li class='tx-list'>
							<div id='tx_fontfamily" +i + "' unselectable='on'
								class='tx-slt-70bg tx-fontfamily'>
								<a href='javascript:;' title='글꼴'>굴림</a>
							</div>
							<div id='tx_fontfamily_menu" +i + "'
								class='tx-fontfamily-menu tx-menu' unselectable='on'></div>
						</li>
					</ul>
					<ul class='tx-bar tx-bar-left'>
						<li class='tx-list'>
							<div unselectable='on' class='tx-slt-42bg tx-fontsize'
								id='tx_fontsize" +i + "'>
								<a href='javascript:;' title='글자크기'>9pt</a>
							</div>
							<div id='tx_fontsize_menu" +i + "'
								class='tx-fontsize-menu tx-menu' unselectable='on'></div>
						</li>
					</ul>
					<ul class='tx-bar tx-bar-left tx-group-font'>

						<li class='tx-list'>
							<div unselectable='on' class='tx-btn-lbg tx-bold'
								id='tx_bold" +i + "'>
								<a href='javascript:;' class='tx-icon' title='굵게 (Ctrl+B)'>굵게</a>
							</div>
						</li>
						<li class='tx-list'>
							<div unselectable='on' class='tx-btn-bg tx-underline'
								id='tx_underline" +i + "'>
								<a href='javascript:;' class='tx-icon' title='밑줄 (Ctrl+U)'>밑줄</a>
							</div>
						</li>
						<li class='tx-list'>
							<div unselectable='on' class='tx-btn-bg tx-italic'
								id='tx_italic" +i + "'>
								<a href='javascript:;' class='tx-icon' title='기울임 (Ctrl+I)'>기울임</a>
							</div>
						</li>
						<li class='tx-list'>
							<div unselectable='on' class='tx-btn-bg tx-strike'
								id='tx_strike" +i + "'>
								<a href='javascript:;' class='tx-icon' title='취소선 (Ctrl+D)'>취소선</a>
							</div>
						</li>
						<li class='tx-list'>
							<div unselectable='on' class='tx-slt-tbg tx-forecolor'
								id='tx_forecolor" +i + "'>
								<a href='javascript:;' class='tx-icon' title='글자색'>글자색</a> <a
									href='javascript:;' class='tx-arrow' title='글자색 선택'>글자색 선택</a>
							</div>
							<div id='tx_forecolor_menu" +i + "'
								class='tx-menu tx-forecolor-menu tx-colorpallete'
								unselectable='on'></div>
						</li>
						<li class='tx-list'>
							<div unselectable='on' class='tx-slt-brbg tx-backcolor'
								id='tx_backcolor" +i + "'>
								<a href='javascript:;' class='tx-icon' title='글자 배경색'>글자 배경색</a>
								<a href='javascript:;' class='tx-arrow' title='글자 배경색 선택'>글자
									배경색 선택</a>
							</div>
							<div id='tx_backcolor_menu" +i + "'
								class='tx-menu tx-backcolor-menu tx-colorpallete'
								unselectable='on'></div>
						</li>
					</ul>
					<ul class='tx-bar tx-bar-left tx-group-align'>
						<li class='tx-list'>
							<div unselectable='on' class='tx-btn-lbg tx-alignleft'
								id='tx_alignleft" +i + "'>
								<a href='javascript:;' class='tx-icon' title='왼쪽정렬 (Ctrl+,)'>왼쪽정렬</a>
							</div>
						</li>
						<li class='tx-list'>
							<div unselectable='on' class='tx-btn-bg tx-aligncenter'
								id='tx_aligncenter" +i + "'>
								<a href='javascript:;' class='tx-icon' title='가운데정렬 (Ctrl+.)'>가운데정렬</a>
							</div>
						</li>
						<li class='tx-list'>
							<div unselectable='on' class='tx-btn-bg tx-alignright'
								id='tx_alignright" +i + "'>
								<a href='javascript:;' class='tx-icon' title='오른쪽정렬 (Ctrl+/)'>오른쪽정렬</a>
							</div>
						</li>
						<li class='tx-list'>
							<div unselectable='on' class='tx-btn-rbg tx-alignfull'
								id='tx_alignfull" +i + "'>
								<a href='javascript:;' class='tx-icon' title='양쪽정렬'>양쪽정렬</a>
							</div>
						</li>
					</ul>
					<ul class='tx-bar tx-bar-left tx-group-tab'>
						<li class='tx-list'>
							<div unselectable='on' class='		 tx-btn-lbg 	tx-indent'
								id='tx_indent" +i + "'>
								<a href='javascript:;' title='들여쓰기 (Tab)' class='tx-icon'>들여쓰기</a>
							</div>
						</li>
						<li class='tx-list'>
							<div unselectable='on' class='		 tx-btn-rbg 	tx-outdent'
								id='tx_outdent" +i + "'>
								<a href='javascript:;' title='내어쓰기 (Shift+Tab)' class='tx-icon'>내어쓰기</a>
							</div>
						</li>
					</ul>
					<ul class='tx-bar tx-bar-left tx-group-list'>
						<li class='tx-list'>
							<div unselectable='on' class='tx-slt-31lbg tx-lineheight'
								id='tx_lineheight" +i + "'>
								<a href='javascript:;' class='tx-icon' title='줄간격'>줄간격</a> <a
									href='javascript:;' class='tx-arrow' title='줄간격'>줄간격 선택</a>
							</div>
							<div id='tx_lineheight_menu" +i + "'
								class='tx-lineheight-menu tx-menu' unselectable='on'></div>
						</li>
						<li class='tx-list'>
							<div unselectable='on' class='tx-slt-31rbg tx-styledlist'
								id='tx_styledlist" +i + "'>
								<a href='javascript:;' class='tx-icon' title='리스트'>리스트</a> <a
									href='javascript:;' class='tx-arrow' title='리스트'>리스트 선택</a>
							</div>
							<div id='tx_styledlist_menu" +i + "'
								class='tx-styledlist-menu tx-menu' unselectable='on'></div>
						</li>
					</ul>
					<ul class='tx-bar tx-bar-left tx-group-etc'>
						<li class='tx-list'>
							<div unselectable='on' class='		 tx-btn-lbg 	tx-emoticon'
								id='tx_emoticon" +i + "'>
								<a href='javascript:;' class='tx-icon' title='이모티콘'>이모티콘</a>
							</div>
							<div id='tx_emoticon_menu" +i + "'
								class='tx-emoticon-menu tx-menu' unselectable='on'></div>
						</li>
						<li class='tx-list'>
							<div unselectable='on' class='		 tx-btn-bg 	tx-link'
								id='tx_link" +i + "'>
								<a href='javascript:;' class='tx-icon' title='링크 (Ctrl+K)'>링크</a>
							</div>
							<div id='tx_link_menu" +i + "' class='tx-link-menu tx-menu'></div>
						</li>
						<li class='tx-list'>
							<div unselectable='on' class='		 tx-btn-bg 	tx-specialchar'
								id='tx_specialchar" +i + "'>
								<a href='javascript:;' class='tx-icon' title='특수문자'>특수문자</a>
							</div>
							<div id='tx_specialchar_menu" +i + "'
								class='tx-specialchar-menu tx-menu'></div>
						</li>
						<li class='tx-list'>
							<div unselectable='on' class='		 tx-btn-bg 	tx-table'
								id='tx_table" +i + "'>
								<a href='javascript:;' class='tx-icon' title='표만들기'>표만들기</a>
							</div>
							<div id='tx_table_menu" +i + "' class='tx-table-menu tx-menu'
								unselectable='on'>
								<div class='tx-menu-inner'>
									<div class='tx-menu-preview'></div>
									<div class='tx-menu-rowcol'></div>
									<div class='tx-menu-deco'></div>
									<div class='tx-menu-enter'></div>
								</div>
							</div>
						</li>
						<li class='tx-list'>
							<div unselectable='on' class='tx-btn-rbg tx-horizontalrule'
								id='tx_horizontalrule" +i + "'>
								<a href='javascript:;' class='tx-icon' title='구분선'>구분선</a>
							</div>
							<div id='tx_horizontalrule_menu" +i + "'
								class='tx-horizontalrule-menu tx-menu' unselectable='on'></div>
						</li>
					</ul>
					<ul class='tx-bar tx-bar-left'>
						<li class='tx-list'>
							<div unselectable='on' class='		 tx-btn-lbg 	tx-richtextbox'
								id='tx_richtextbox" +i + "'>
								<a href='javascript:;' class='tx-icon' title='글상자'>글상자</a>
							</div>
							<div id='tx_richtextbox_menu" +i + "'
								class='tx-richtextbox-menu tx-menu'>
								<div class='tx-menu-header'>
									<div class='tx-menu-preview-area'>
										<div class='tx-menu-preview'></div>
									</div>
									<div class='tx-menu-switch'>
										<div class='tx-menu-simple tx-selected'>
											<a><span>간단 선택</span></a>
										</div>
										<div class='tx-menu-advanced'>
											<a><span>직접 선택</span></a>
										</div>
									</div>
								</div>
								<div class='tx-menu-inner'></div>
								<div class='tx-menu-footer'>
									<img class='tx-menu-confirm'
										src='/homepage/images/icon/editor/btn_confirm.gif?rv=1.0.1'
										alt='' /> <img class='tx-menu-cancel' hspace='3'
										src='/homepage/images/icon/editor/btn_cancel.gif?rv=1.0.1'
										alt='' />
								</div>
							</div>
						</li>
						<li class='tx-list'>
							<div unselectable='on' class='		 tx-btn-bg 	tx-quote'
								id='tx_quote" +i + "'>
								<a href='javascript:;' class='tx-icon' title='인용구 (Ctrl+Q)'>인용구</a>
							</div>
							<div id='tx_quote_menu" +i + "' class='tx-quote-menu tx-menu'
								unselectable='on'></div>
						</li>
						<li class='tx-list'>
							<div unselectable='on' class='		 tx-btn-bg 	tx-background'
								id='tx_background" +i + "'>
								<a href='javascript:;' class='tx-icon' title='배경색'>배경색</a>
							</div>
							<div id='tx_background_menu" +i + "'
								class='tx-menu tx-background-menu tx-colorpallete'
								unselectable='on'></div>
						</li>
						<li class='tx-list'>
							<div unselectable='on' class='		 tx-btn-rbg 	tx-dictionary'
								id='tx_dictionary" +i + "'>
								<a href='javascript:;' class='tx-icon' title='사전'>사전</a>
							</div>
						</li>
					</ul>
					<ul class='tx-bar tx-bar-left tx-group-undo'>
						<li class='tx-list'>
							<div unselectable='on' class='		 tx-btn-lbg 	tx-undo'
								id='tx_undo" +i + "'>
								<a href='javascript:;' class='tx-icon' title='실행취소 (Ctrl+Z)'>실행취소</a>
							</div>
						</li>
						<li class='tx-list'>
							<div unselectable='on' class='		 tx-btn-rbg 	tx-redo'
								id='tx_redo" +i + "'>
								<a href='javascript:;' class='tx-icon' title='다시실행 (Ctrl+Y)'>다시실행</a>
							</div>
						</li>
					</ul>
					<ul class='tx-bar tx-bar-right'>
						<li class='tx-list'>
							<div unselectable='on' class='tx-btn-nlrbg tx-advanced'
								id='tx_advanced" +i + "'>
								<a href='javascript:;' class='tx-icon' title='툴바 더보기'>툴바 더보기</a>
							</div>
						</li>
					</ul>
				</div>
			</div>
			<div id='tx_toolbar_advanced" +i + "'
				class='tx-toolbar tx-toolbar-advanced'>
				<div class='tx-toolbar-boundary'>
					<ul class='tx-bar tx-bar-left'>
						<li class='tx-list'>
							<div class='tx-tableedit-title'></div>
						</li>
					</ul>

					<ul class='tx-bar tx-bar-left tx-group-align'>
						<li class='tx-list'>
							<div unselectable='on' class='tx-btn-lbg tx-mergecells'
								id='tx_mergecells" +i + "'>
								<a href='javascript:;' class='tx-icon2' title='병합'>병합</a>
							</div>
							<div id='tx_mergecells_menu" +i + "'
								class='tx-mergecells-menu tx-menu' unselectable='on'></div>
						</li>
						<li class='tx-list'>
							<div unselectable='on' class='tx-btn-bg tx-insertcells'
								id='tx_insertcells" +i + "'>
								<a href='javascript:;' class='tx-icon2' title='삽입'>삽입</a>
							</div>
							<div id='tx_insertcells_menu" +i + "'
								class='tx-insertcells-menu tx-menu' unselectable='on'></div>
						</li>
						<li class='tx-list'>
							<div unselectable='on' class='tx-btn-rbg tx-deletecells'
								id='tx_deletecells" +i + "'>
								<a href='javascript:;' class='tx-icon2' title='삭제'>삭제</a>
							</div>
							<div id='tx_deletecells_menu" +i + "'
								class='tx-deletecells-menu tx-menu' unselectable='on'></div>
						</li>
					</ul>

					<ul class='tx-bar tx-bar-left tx-group-align'>
						<li class='tx-list'>
							<div id='tx_cellslinepreview" +i + "' unselectable='on'
								class='tx-slt-70lbg tx-cellslinepreview'>
								<a href='javascript:;' title='선 미리보기'></a>
							</div>
							<div id='tx_cellslinepreview_menu" +i + "'
								class='tx-cellslinepreview-menu tx-menu' unselectable='on'></div>
						</li>
						<li class='tx-list'>
							<div id='tx_cellslinecolor" +i + "' unselectable='on'
								class='tx-slt-tbg tx-cellslinecolor'>
								<a href='javascript:;' class='tx-icon2' title='선색'>선색</a>

								<div class='tx-colorpallete' unselectable='on'></div>
							</div>
							<div id='tx_cellslinecolor_menu" +i + "'
								class='tx-cellslinecolor-menu tx-menu tx-colorpallete'
								unselectable='on'></div>
						</li>
						<li class='tx-list'>
							<div id='tx_cellslineheight" +i + "' unselectable='on'
								class='tx-btn-bg tx-cellslineheight'>
								<a href='javascript:;' class='tx-icon2' title='두께'>두께</a>

							</div>
							<div id='tx_cellslineheight_menu" +i + "'
								class='tx-cellslineheight-menu tx-menu' unselectable='on'></div>
						</li>
						<li class='tx-list'>
							<div id='tx_cellslinestyle" +i + "' unselectable='on'
								class='tx-btn-bg tx-cellslinestyle'>
								<a href='javascript:;' class='tx-icon2' title='스타일'>스타일</a>
							</div>
							<div id='tx_cellslinestyle_menu" +i + "'
								class='tx-cellslinestyle-menu tx-menu' unselectable='on'></div>
						</li>
						<li class='tx-list'>
							<div id='tx_cellsoutline" +i + "' unselectable='on'
								class='tx-btn-rbg tx-cellsoutline'>
								<a href='javascript:;' class='tx-icon2' title='테두리'>테두리</a>

							</div>
							<div id='tx_cellsoutline_menu" +i + "'
								class='tx-cellsoutline-menu tx-menu' unselectable='on'></div>
						</li>
					</ul>
					<ul class='tx-bar tx-bar-left'>
						<li class='tx-list'>
							<div id='tx_tablebackcolor" +i + "' unselectable='on'
								class='tx-btn-lrbg tx-tablebackcolor'
								style='background-color: #9aa5ea;'>
								<a href='javascript:;' class='tx-icon2' title='테이블 배경색'>테이블
									배경색</a>
							</div>
							<div id='tx_tablebackcolor_menu" +i + "'
								class='tx-tablebackcolor-menu tx-menu tx-colorpallete'
								unselectable='on'></div>
						</li>
					</ul>
					<ul class='tx-bar tx-bar-left'>
						<li class='tx-list'>
							<div id='tx_tabletemplate" +i + "' unselectable='on'
								class='tx-btn-lrbg tx-tabletemplate'>
								<a href='javascript:;' class='tx-icon2' title='테이블 서식'>테이블
									서식</a>
							</div>
							<div id='tx_tabletemplate_menu" +i + "'
								class='tx-tabletemplate-menu tx-menu tx-colorpallete'
								unselectable='on'></div>
						</li>
					</ul>

				</div>
			</div>
			<div id='tx_canvas" +i + "' class='tx-canvas'>
				<div id='tx_loading" +i + "' class='tx-loading'>
					<div>
						<img src='/homepage/images/icon/editor/loading2.png' width='113'
							height='21' align='absmiddle' />
					</div>
				</div>
				<div id='tx_canvas_wysiwyg_holder" +i + "' class='tx-holder'
					style='display: block;'>
					<iframe id='tx_canvas_wysiwyg" +i + "' name='tx_canvas_wysiwyg'
						allowtransparency='true' frameborder='0'></iframe>
				</div>
				<div class='tx-source-deco'>
					<div id='tx_canvas_source_holder" +i + "' class='tx-holder'>
						<textarea id='tx_canvas_source" +i + "' rows='30' cols='30'></textarea>
					</div>
				</div>
				<div id='tx_canvas_text_holder" +i + "' class='tx-holder'>
					<textarea id='tx_canvas_text" +i + "' rows='30' cols='30'></textarea>
				</div>
			</div>
			<div id='tx_resizer" +i + "' class='tx-resize-bar'>
				<div class='tx-resize-bar-bg'></div>
				<img id='tx_resize_holder" +i + "'
					src='/homepage/images/icon/editor/skin/01/btn_drag01.gif'
					width='58' height='12' unselectable='on' alt='' />
			</div>
			<div id='tx_attach_div" +i + "' class='tx-attach-div'>
				<div id='tx_attach_txt" +i + "' class='tx-attach-txt'>파일 첨부</div>
				<div id='tx_attach_box" +i + "' class='tx-attach-box'>
					<div class='tx-attach-box-inner'>
						<div id='tx_attach_preview" +i + "' class='tx-attach-preview'>
							<p></p>
							<img src='/homepage/images/icon/editor/pn_preview.gif'
								width='147' height='108' unselectable='on' />
						</div>
						<div class='tx-attach-main'>
							<div id='tx_upload_progress" +i + "' class='tx-upload-progress'>
								<div>0%</div>
								<p>파일을 업로드하는 중입니다.</p>
							</div>
							<ul class='tx-attach-top'>
								<li id='tx_attach_delete" +i + "' class='tx-attach-delete'><a>전체삭제</a></li>
								<li id='tx_attach_size" +i + "' class='tx-attach-size'>파일: <span
									id='tx_attach_up_size" +i + "' class='tx-attach-size-up'></span>/<span
									id='tx_attach_max_size" +i + "'></span>
								</li>
								<li id='tx_attach_tools" +i + "' class='tx-attach-tools'></li>
							</ul>
							<ul id='tx_attach_list" +i + "' class='tx-attach-list'></ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
	<div id='board_right" +i + "' style='height: 30px'>
		<div style='text-align: right; height: 15px'>
			<input type='button' value='수정'
				class='_42ft _4jy0 _11b _4jy3 _4jy1 selected'
				onclick='saveContent(" +i+ ")' />
			<mcjsjs>
			<input type='button' value='취소'
				class='_42ft _4jy0 _11b _4jy3 _4jy1 selected'
				id='updateCancel" +i+ "' /></mcjsjs>
		</div>
	</div>

	<textarea id='load_contents_source" +i + "' style='display: none;'>" +dto.getContent() + "</textarea>

	<script type='text/javascript'>
							var config" +i + " = {
								txHost : '',
								txPath : '',
								txService : 'sample', 
								txProject : 'sample',
								initializedId : '" +i + "', /* 대부분의 경우에 빈문자열 */
								wrapper : 'tx_trex_container" +i + "', /* 에디터를 둘러싸고 있는 레이어 이름(에디터 컨테이너) */
								form : 'tx_editor_form" +i + "' + '', /* 등록하기 위한 Form 이름 */				
								txIconPath : '/homepage/images/icon/editor/', 
								txDecoPath : '/homepage/images/deco/contents/', 
								canvas : {
									initHeight : 150, // 높이
									styles : {
										color : '#123456', 
										fontFamily : '굴림',
										fontSize : '10pt', 
										backgroundColor : '#fff', 
										lineHeight : '1.5', 
										padding : '8px' 
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
									contentWidth : 580 
								}
							};



							EditorJSLoader.ready(function(Editor) {
								 new Editor(config" +i + ");
								Editor.getCanvas().observeJob(Trex.Ev.__CANVAS_PANEL_DELETE_SOMETHING, function (ev) {
								    var attachBox = Editor.getAttachBox();
								    attachBox.datalist.each(function (entry) {
								        if (entry.type === 'image'  && entry.existStage === false ) {
								            entry.execRemove();
								            enrty = null;
								        }
								    });
								    attachBox.refreshPreview();
								});   
								Editor.getToolbar().observeJob(Trex.Ev.__TOOL_CLICK, function (type) {
								    Editor.switchEditor('" +i+ "');
								});
							});	
						</script>
</div>
<div class='_5pbx userContent'>
	<p>" + dto.getContent() + "</p>
</div>
<div>
	<div></div>
</div>
<form action='proc.jsp' method='post' name='boardaction'>
	<div class='clearfix'>
		<div class='_5pcp _5vsi lfloat _ohe'>
			<a href='javascript:like(" +dto.getNo()+ ", '
				" +session.getAttribute("id") + "')' class='default_message' name='like'>좋아요</a> ·
			<mcjsjs>
			<span class='default_message' name='reply' id='replyshow" +i+ "'
				style='cursor: pointer;'>댓글</span></mcjsjs>
			<mcjsjs>
			<input type='hidden' id='divNoCheck" +i + "' value='" +i+ "' /></mcjsjs>
			· <a class='uiBlingBox feedbackBling'> <i
				class='img sp_p5WkkL41GeK sx_408c76'></i><span class='text'>" +dto.getLike() + "</span>
			</a>
		</div>
	</div>
</form>
</div>
<form method='post' action='like_proc.jsp'>
	<div id='replydiv" +i+ "' style='background-color: #E6FFFF;'>
		<input type='hidden' name='command' id='command' value='REPLYPOST'>
		<input type='hidden' name='no' id='no' value='" +dto.getNo()+ "' /> <input
			type='hidden' name='id' value='" +session.getAttribute("id")+ "' />
		<hr />
		<div class='_3dp _29k''>
			<div>
				<div class='_6a'>
					<div class='_6a _6b' style='margin-left: 7px'>
						<h5 class='_5pbw'>
							<div class='fwn fcg'>
								<span class='fwb fcg'> <a href='#'
									style='font-size: 14px;'>" +session.getAttribute("id")+ "</a>&nbsp;&nbsp; <textarea
										name='content' cols='' rows='1'
										class='DOMControl_placeholder uiTextareaAutogrow input autofocus mentionsTextarea textInput'
										onKeyDown='setLine(this)' style='width: 393px; resize: none;'></textarea>&nbsp;&nbsp;
									<button value='1' class='_42ft _4jy0 _11b _4jy3 _4jy1 selected'
										type='submit'>댓글</button>
								</span> <br />
								<br />
							</div>
						</h5>
					</div>
</form>
</div>
</div>
</div>

</div>
</div>
</div>