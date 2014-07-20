Ext.setup({
    tabletStartupScreen: 'tablet_startup.png',
    phoneStartupScreen: 'phone_startup.png',
    icon: 'icon.png',
    glossOnIcon: false,
    onReady : function() {
        Ext.regModel('Persons', {
            fields: ['user_id','user_name','tel']
        });
   
        var PersonStore = new Ext.data.Store({
            model :'Persons',               
            data:[
                    // 공백
            ],
        });   
        
		var PersonList = new Ext.List({
			fullscreen:true,
			id:'PersonList',
	        store:PersonStore,                      
	        onItemDisclosure: {
	            handler: function(record, btn, index) {  
	                main.MainPanel.layout.setActiveItem(detail.panel_detail); 
	                detail.panel_detail.setUserId(record.get('user_id'));
	                detail.panel_detail.getUserInfo();
	            }
	        },
	        itemTpl:'<div><strong>{user_id}</strong> {user_name}  tel:{tel} </div>',
	    });    

		function setPersonsList(Jv_data) {
	        PersonStore = new Ext.data.Store({
	            model :'Persons',
	            data:Jv_data,
	        });
	        Ext.getCmp('PersonList').bindStore(PersonStore);    
	    };
	    
	    new Ext.Panel({
	         fullscreen: true,               
	        scroll:'vertical',
	        cardSwitchAnimation:"cube",
	        scroll: 'vertical',
	        items:
	        [{
	            xtype: 'fieldset',
	            title: '회원 검색',
	            defaults: {
	                required: true,
	                labelAlign: 'left' }
	            ,
	            items:[{
	                layout: {
	                    type: 'hbox',
	                    pack: 'center',
	                    
	                },  
	                items:[{
	                    xtype:'textfield',
	                    id:'user_name',
	                    width:'50%',
	                    placeHolder:'회원 이름 입력',
	                    autoCapitalisze:true,
	                    useClearIcon:false
	                                
	                },{
	                    xtype:'spacer',
	                    width:'10%',
	                },{
	                    xtype:'button',
	                    ui: 'decline-round',
	                    name:'button_search',
	                    width:'30%',
	                    handler:function(){                 
	                    Ext.Ajax.request({
	                        url: common_url  + '/jsp/psn_list.jsp?user_name=' + Ext.getCmp("user_name").getValue(),
	                        success: function(response, opts) {
	                            console.log(response.responseText);
	                            var JsonData = JSON.parse(response.responseText);
	                            console.log(JsonData);
	                            if(JsonData.data.err == "")
	                            {
	                                setPersonsList(JsonData.data.psn_list);
	                            }
	                            else
	                            {
	                                alert(JsonData.data.err);
	                            }
	                        }
	                    });                     
	                    },                      
	                    text:'조회'
	                }]                                      
	            }]
	        },
	        {
	            xtype: 'fieldset',
	            instructions: '상세정보를 보고 싶으시면 리스트를 클릭하십시요',
	            defaults: {
	                
	                labelAlign: 'left' }
	            ,
	            items:PersonList
	        }]
        });       
	}
});
