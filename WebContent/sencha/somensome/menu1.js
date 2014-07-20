﻿Ext.ns("dash");
Ext.ns("dash.panel_dash");

dash.panel_dash = new Ext.form.FormPanel({
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
                    dash.panel_dash.setPersonFields(JsonData.data.psn_detail);
                }
                else
                {
                    alert(JsonData.data.err);
                }                
            }
        });         
    },  
    setPersonFields:function(psndash)
    {
        var psnPic =  '<table width="100%"><tr><td><center>' + 
                      '<img src="' + common_url + 
                      '/profile/' +  psndash.pic +  '" height="100%"></td></tr></table>';
        Ext.getCmp("panel_dash.name").setValue(psndash.name);
        Ext.getCmp("panel_dash.age").setValue(psndash.age);
        Ext.getCmp("panel_dash.blood").setValue(psndash.blood);
        Ext.getCmp("panel_dash.height").setValue(psndash.height);
        Ext.getCmp("panel_dash.weight").setValue(psndash.weight);
        Ext.getCmp("panel_dash.addr").setValue(psndash.addr);
        Ext.getCmp("panel_dash.style").setValue(psndash.style);
        Ext.getCmp("panel_dash.fashion").setValue(psndash.fashion);
        Ext.getCmp("panel_dash.hobby").setValue(psndash.hobby);
        Ext.getCmp("panel_dash.comment").setValue(psndash.comment);
        Ext.getCmp("panel_dash.user_pic").update(psnPic);
        
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
                id:'panel_dash.user_pic',
                xtype: 'fieldset',
                html:'',            
            }]
            },
            {
                xtype:'textfield',
                label:'이름 ',
                id:'panel_dash.name', 
                disabled : true,
                disabledCls: 'af-item-disabled',
                autoCapitalisze:true,
                useClearIcon:false
                            
            },{
                xtype:'textfield',
                label:'나이 ',
                id:'panel_dash.age',
                disabled : true,
                disabledCls: 'af-item-disabled',
                autoCapitalisze:true,
                useClearIcon:false
                            
            },            
            {
                xtype:'textfield',
                id:'panel_dash.blood',
                label:'혈액형',
                disabled : true,
                disabledCls: 'af-item-disabled',
                autoCapitalisze:true,
                useClearIcon:false
                            
            },    
            {
                xtype:'textfield',
                id:'panel_dash.height',
                label:'키',
                disabled : true,
                disabledCls: 'af-item-disabled',
                autoCapitalisze:true,
                useClearIcon:false                          
            },           
            {
           	 xtype:'textfield',
                id:'panel_dash.weight',
                label:'몸무게',
                disabled : true,
                disabledCls: 'af-item-disabled',
                autoCapitalisze:true,
                useClearIcon:false   
            }, 
            {
           	 xtype:'textfield',
               id:'panel_dash.addr',
               label:'지역',
               disabled : true,
               disabledCls: 'af-item-disabled',
               autoCapitalisze:true,
               useClearIcon:false   
            }, 
            {
         	 xtype:'textfield',
              id:'panel_dash.style',
              label:'성격',
              disabled : true,
              disabledCls: 'af-item-disabled',
              autoCapitalisze:true,
              useClearIcon:false   
           }, 
           {
         	 xtype:'textfield',
              id:'panel_dash.fashion',
              label:'스타일',
              disabled : true,
              disabledCls: 'af-item-disabled',
              autoCapitalisze:true,
              useClearIcon:false   
           }, 
           {
        	 xtype:'textfield',
             id:'panel_dash.hobby',
             label:'취미',
             disabled : true,
             disabledCls: 'af-item-disabled',
             autoCapitalisze:true,
             useClearIcon:false   
           }, 
           {
               xtype:'textareafield',
               id:'panel_dash.comment',
               placeHolder:'작업멘트를 날리세요',
               width : '100%',
               useClearIcon:false,
           },
           {
                xtype:'button',
                ui: 'decline-round',                
                name:'panel_dash.button_close',
                width:'100%',
                handler:function(){                                     
                   main.MainPanel.layout.setActiveItem(list.panel_list); 
           },
           	text:'대쉬!!!!'
            }]                                  
});     
