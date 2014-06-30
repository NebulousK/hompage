<a href='javascript:unlike("+dto.getNo())+", \'"+ id +"\', ${status.index})\' class='default_message' name='unlike' id='unlike${status.index}'>좋아요 취소</a>

<c:if test='${0 == flag}'>
<a href='javascript:like(${list.no}, \'${sessionScope.id}\', ${status.index})' class='default_message' name='like' id='like${status.index}'>좋아요</a>
</c:if>
</span>
</span>
· <mcjsjs><span class='default_message' name='reply' id='replyshow${status.index}' style='cursor:pointer;'
onclick='getreplylist(${list.no}, \'${sessionScope.id}\', ${status.index})'>
댓글</span></mcjsjs>
<mcjsjs><input type='hidden' id='divNoCheck${status.index}' value='${status.index}'/></mcjsjs>
· <a class='uiBlingBox feedbackBling'>
<i class='img sp_p5WkkL41GeK sx_408c76'></i><span class='text' id='likecnt${status.index}'>${list.like}</span>
</a>
</div>
</div>
</div>
<div id='replydiv${status.index}' style='background-color: #E6FFFF;'>
</div>
</div>
</div>
</div>