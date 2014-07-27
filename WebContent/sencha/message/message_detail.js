Ext.ns("message");
Ext.ns("message.panel_message");

message.init = function(){
    var messageList;
    var messageStore;
    var setUserId;
    
    Ext.regModel('message', {
        fields: ['photo','id','no']
    });     

    messageStore = new Ext.data.Store({
        model :'message',               
        data:[
                // 공백
        ],
    });     
    
    setUserId = function(user_id)
    {
        this.input_user_id = user_id;
    },
    
    messageList = new Ext.XTemplate(
    	    '<tpl if="id"=="'+ common_id +'">',
    	    '<div id="memo_area" class="me">',
    		'<div class="nick_area">',
    			'<table border="0" cellpadding="5" cellspacing="0">',
    				'<tr>',
    					'<td align="right"><img class="hu_icon" src="/homepage/profile/<%=dto.getPhoto() %>" width="25" height="25" style="width:25px;height:25px;" /></td>',
    					'<td width="5"></td>',
    					'<td align="left" valign="center">',
    											'{id}',
    											'<br>',
    						'<span class="date">',
    							'{day}',
    						'</span>',
    					'</td>',
    				'</tr>',
    			'</table>',
    		'</div>',
    					'<div class="tri"> </div>',
    				'<span class="memotext">',
    							'{content}',
    					'</span>',
    					'</div>', 	
    	    '</tpl>',
    	    '<tpl else>',
    	    
    	    '</tpl>'
    				
    	);
    	
    	/*new Ext.List({
        id:'messageList',
        store:messageStore,
        height:'100%',
        blockRefresh:true,
        onItemDisclosure: {
            handler: function(record, btn, index) {
            	main.MainPanel.layout.setActiveItem(mcall.panel_mcall);
            	mcall.panel_mcall.setUserId(record.get('name'));
            	mcall.panel_mcall.getUserInfo();
            }
        },
        itemTpl:'<div style="float: left"><input type="hidden" name="no" value="{no}"/><img src="'+ common_url +'/profile/{photo}" width="50px" height="50px" /></div><div style="float: left; height:50px;line-height:50px;">&nbsp;&nbsp;&nbsp;<strong>이름 : {id}</strong></div>',
    });        
*/
    function setmessageList(Jv_data) {
        messageStore = new Ext.data.Store({
            model :'message',
            data:Jv_data,
        });
        Ext.getCmp('messageList').bindStore(messageStore);    
    };
               
    message.panel_message = new Ext.Panel({
        useCurrentLocation: true,
        fullscreen: true,
        cardSwitchAnimation:"cube",
        getmessageList:function()
        {
            Ext.Ajax.request({
                url: common_url + '/mList.message?id='+ common_id,
                success: function(response, opts) {
                    console.log(response.responseText);
                    var JsonData = JSON.parse(response.responseText);
                    console.log(JsonData);
                    if(JsonData.data.err == "")
                    {
                    	setmessageList(JsonData.data.message_list);
                    }
                    else
                    {
                        alert(JsonData.data.err);
                    }                
                }
            });         
        },
        items: messageList
    });
};   