<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>이미지 첨부</title> 
<script src="../../js/popup.js" type="text/javascript" charset="utf-8"></script>
<link rel="stylesheet" href="../../css/popup.css" type="text/css"  charset="utf-8"/>
<?php
include '../../../../dbconn.php';
$upfile = $_FILES['upload_file']['tmp_name'];
$upfile_name = $_FILES['upload_file']['name'];
$upfile_type = $_FILES['upload_file']['type'];
$upfile_size= $_FILES['upload_file']['size'];
$maxsize=3000*1024;
$destination = "../../../../upload/" . $filename;
//move_uploaded_file ( $tmpfilename, $destination );
$sql5 = "select * from asan1 order by no desc";
$result5 = mysql_query($sql5, $connect);
$row5 = mysql_fetch_array($result5);
$noo = $row5[no]+1;
$n123=substr($upfile_name,-4,4);
$filename = sprintf("%s",$upfile_name);
$filename = iconv("UTF-8","EUC-KR",$filename);
$filename=$noo.'_'.rand(0, 99999).'_'.date('ymd').'_'.$n123;
$fileurl = "/upload/" . $filename;

if($upfile){
   		if ($upfile_type!='image/pjpeg' and $upfile_type!='image/jpeg' and $upfile_type!='image/png' and $upfile_type!='image/x-png' and $upfile_type!='image/gif' and $upfile_type!='image/tiff' and $upfile_type!='image/jpg' and $upfile_type!='image/jpeg' and $upfile_type!='image/png' ) { 
			echo"<script>window.alert('그림파일만 업로드 가능 합니다.$upfile_type');history.go(-1)</script>";
   			exit;
 			}
      	if($upfile_size >$maxsize) {
         echo"<script>window.alert('업로드 파일 사이즈가 지정된 용량(2M)을 초과합니다.');history.go(-1)</script>";
         exit;
        }
      	if(strlen($upfile_size) < 7) {
         $filesize = sprintf("%0.2f KB", $upfile_size/1000);
        }
      	else
        {
         $filesize = sprintf("%0.2f MB", $upfile_size/1000000);
        }
        $size=getimagesize($upfile);
        if($size[0]>750){
        	echo"<script>window.alert('사진의 가로크기가 700을 초과합니다.');history.go(-1)</script>";
        	exit;
        }
      	if(!copy($upfile, "../../../../upload/$filename") )
        {
         echo "<script>window.alert('파일을 지정한 디렉토리에 복사하는데 실패했습니다.');history.go(-1)</script>";
         exit;
        }         
        if (!unlink($upfile) ) {
         echo "<script> window.alert('임시파일을 삭제하는데 실패했습니다.');history.go(-1)</script>";
         exit;
        }
        $sql = "insert into asan1image(no,filename,desination,filesize,filetype,fileurl) ";
        $sql .= "values($noo,'$filename', '$desination', '$filesize', '$upfile_type', '$fileurl')";
        mysql_query($sql, $connect);
        mysql_close();
   		}
?>
<script type="text/javascript">
	function done() {
		if (typeof(execAttach) == 'undefined') { //Virtual Function
	        return;
	    }
		
		var _mockdata = {
				'imageurl': '<?php echo $fileurl?>',
				'filename': '<?php echo $filename?>',
				'filesize': '<?php echo $filesize?>',
				'imagealign': 'C',
				'originalurl': '<?php echo $fileurl?>',
				'thumburl': '<?php echo $fileurl?>'
		};
		execAttach(_mockdata);
		closeWindow();
	}

	function initUploader(){
	    var _opener = PopupUtil.getOpener();
	    if (!_opener) {
	        alert('잘못된 경로로 접근하셨습니다.');
	        return;
	    }
	    
	    var _attacher = getAttacher('image', _opener);
	    registerAction(_attacher);
	}
</script>
</head>
<body onload="initUploader();">
<div class="wrapper">
	<div class="header">
		<h1>사진 첨부</h1>
	</div>	
	<div class="body">
		<dl class="alert">
		    <dt>사진 첨부 확인</dt>
		    <dd>
		    	확인을누루시면  사진첨부가 됩니다.<br /> 
			</dd>
		</dl>
	</div>
	<div class="footer">
		<p><a href="#" onclick="closeWindow();" title="닫기" class="close">닫기</a></p>
		<ul>
			<li class="submit"><a href="#" onclick="done();" title="등록" class="btnlink">등록</a> </li>
			<li class="cancel"><a href="#" onclick="closeWindow();" title="취소" class="btnlink">취소</a></li>
		</ul>
	</div>
</div>
</body>
</html>
