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
		//수정창 보이기
				$("#updateclick").click(function() {
		          	alert(no);
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