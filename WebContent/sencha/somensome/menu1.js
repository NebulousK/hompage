Ext.ns("detail");
Ext.ns("detail.panel_detail");

detail.panel_detail = new Ext.form.FormPanel({
	fullscreen: true,
    useCurrentLocation: true,               
    scroll:'vertical',
    cardSwitchAnimation:"cube",
    width: '100%',
    getUserInfo:function()
    {
        Ext.Ajax.request({
            url: common_url + '/mdash.some?no=532&sex=man',
            success: function(response, opts) {
                console.log(response.responseText);
                var JsonData = JSON.parse(response.responseText);
                console.log(JsonData);
                if(JsonData.data.err == "")
                {
                    detail.panel_detail.setPersonFields(JsonData.data.psn_detail);
                }
                else
                {
                    alert(JsonData.data.err);
                }                
            }
        });         
    },  
    setPersonFields:function(psnDetail)
    {
        var psnPic =  '<table width="100%"><tr><td><center>' + 
                      '<img src="' + common_url + 
                      '/profile/' +  psnDetail.pic +  '" height="100%"></td></tr></table>';
        Ext.getCmp("panel_detail.name").setValue(psnDetail.name);
        Ext.getCmp("panel_detail.age").setValue(psnDetail.age);
        Ext.getCmp("panel_detail.blood").setValue(psnDetail.blood);
        Ext.getCmp("panel_detail.height").setValue(psnDetail.height);
        Ext.getCmp("panel_detail.weight").setValue(psnDetail.weight);
        Ext.getCmp("panel_detail.addr").setValue(psnDetail.addr);
        Ext.getCmp("panel_detail.style").setValue(psnDetail.style);
        Ext.getCmp("panel_detail.fashion").setValue(psnDetail.fashion);
        Ext.getCmp("panel_detail.hobby").setValue(psnDetail.hobby);
        Ext.getCmp("panel_detail.comment").setValue(psnDetail.comment);
        Ext.getCmp("panel_detail.user_pic").update(psnPic);
        
    },
    layout: {
        type: 'vbox',
        pack: 'center',
        align: 'stretch'
    },
    scroll: 'vertical',                         
    items:
    [{
        xtype: 'fieldset',
        title: '오늘의 추천 이성',
        //instructions: '직원명을 입력하시고 조회버튼을 클릭하십시요',
        pack: 'center',
        defaults: {
            required: true,
            labelAlign: 'left' ,
            labelWidth:'100%',
        },
        items:[
            {
                xtype:'panel',
                id:'panel_detail.user_pic',
                xtype: 'fieldset',
                html:'',            
            }]
            },
            {
                xtype:'textfield',
                label:'이름 ',
                id:'panel_detail.name', 
                disabled : true,
                disabledCls: 'af-item-disabled',
                autoCapitalisze:true,
                useClearIcon:false
                            
            },{
                xtype:'textfield',
                label:'나이 ',
                id:'panel_detail.age',
                disabled : true,
                disabledCls: 'af-item-disabled',
                autoCapitalisze:true,
                useClearIcon:false
                            
            },            
            {
                xtype:'textfield',
                id:'panel_detail.blood',
                label:'혈액형',
                disabled : true,
                disabledCls: 'af-item-disabled',
                autoCapitalisze:true,
                useClearIcon:false
                            
            },    
            {
                xtype:'textfield',
                id:'panel_detail.height',
                label:'키',
                disabled : true,
                disabledCls: 'af-item-disabled',
                autoCapitalisze:true,
                useClearIcon:false                          
            },           
            {
           	 xtype:'textfield',
                id:'panel_detail.weight',
                label:'몸무게',
                disabled : true,
                disabledCls: 'af-item-disabled',
                autoCapitalisze:true,
                useClearIcon:false   
            }, 
            {
           	 xtype:'textfield',
               id:'panel_detail.addr',
               label:'지역',
               disabled : true,
               disabledCls: 'af-item-disabled',
               autoCapitalisze:true,
               useClearIcon:false   
            }, 
            {
         	 xtype:'textfield',
              id:'panel_detail.style',
              label:'성격',
              disabled : true,
              disabledCls: 'af-item-disabled',
              autoCapitalisze:true,
              useClearIcon:false   
           }, 
           {
         	 xtype:'textfield',
              id:'panel_detail.fashion',
              label:'스타일',
              disabled : true,
              disabledCls: 'af-item-disabled',
              autoCapitalisze:true,
              useClearIcon:false   
           }, 
           {
        	 xtype:'textfield',
             id:'panel_detail.hobby',
             label:'취미',
             disabled : true,
             disabledCls: 'af-item-disabled',
             autoCapitalisze:true,
             useClearIcon:false   
           }, 
           {
               xtype:'textareafield',
               id:'panel_detail.comment',
               placeHolder:'작업멘트를 날리세요',
               anchor    : '100%',/*
               autoCapitalisze:true,
               useClearIcon:false,*/
           },
           {
                xtype:'button',
                ui: 'decline-round',                
                name:'panel_detail.button_close',
                width:'100%',
                handler:function(){                                     
                   main.MainPanel.layout.setActiveItem(list.panel_list); 
           },
           	text:'대쉬!!!!'
            }]                                  
});     
