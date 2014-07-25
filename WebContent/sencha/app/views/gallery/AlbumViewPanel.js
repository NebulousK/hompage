Gallery.views.AlbumViewPanel = Ext.extend(Ext.Panel, {
    layout: 'fit',
    html: '<div style="width:100%;height:100%;background:#000;"></div>',//'<div style="width:320px;height:480px;background:#000;"></div>',
    initComponent: function() {
        this.store = new Ext.data.Store({
            autoLoad: true,
            model: 'Image'
        });
        
        this.dockedItems = [{
            xtype: 'toolbar',
            dock: 'top',
            itemId: 'albumToolbar',
            items: [{ xtype: 'spacer' }, { 
                itemId: 'helpButton',
                iconCls: 'home',
                iconMask: true,
                handler: function() {
                    /*new Ext.Panel({
                        floating: true,
                        centered: true,
                        modal: true, 
                        html: '<div class="helpbox">' + 
                                '<div style="margin: 0;">Tap to view,</div>' +
                                '<div>Swipe to change background</div>' + 
                               '</div>'
                    }).showBy(this);*/
                	document.location = "somensome2.html";
                }
            }] 
        }];
    
        this.xtpl = new Ext.XTemplate(
            '<div style="padding:10px 5px 5px 5px;">',
            '<tpl for=".">',
                /*'<div class="node" style="background:url({thumb});background-size: contain;">',
                '</div>',*/
            	'<div class="node">',
            	'<img src="{thumb}" width="100%" height="100%">',
            	'</img>',
            	'</div>',
            '</tpl>',
            '</div>'
        );
    
        this.dataView = new Ext.DataView({
            store: this.store,
            tpl: this.xtpl,
            itemSelector: 'div.node'
        });
        this.items = [this.dataView];
        Gallery.views.AlbumViewPanel.superclass.initComponent.apply(this, arguments);
    }
});

Ext.reg('gallery-albumviewpanel', Gallery.views.AlbumViewPanel);