Gallery.views.AlbumListPanel = Ext.extend(Ext.Panel, {
    layout: 'fit',
    initComponent: function() {
        this.store = new Ext.data.Store({
            autoLoad: true,
            model: 'Album'
        });
        
        this.dockedItems = [{
            xtype: 'toolbar',
            dock: 'top',
            title: 'Album List',
            items:[{ xtype: 'spacer' }, { 
                itemId: 'helpButton',
                iconCls: 'home',
                iconMask: true,
                handler: function() {
                    /*new Ext.Panel({
                        floating: true,
                        centered: true,
                        modal: true, 
                        html: '<div class="helpbox">' + 
                                '<div style="margin: 0;">Sencha Gallery</div>' +
                                '<div><a href="#">to post</a> &nbsp;<a href="#">source</a></div>' + 
                                '<div class="credit">Stock Photos By <a href="http://freephotosbank.com">FreePhotosBank</a></div>' +
                               '</div>'
                    }).showBy(this);*/
                	document.location = "somensome2_1.html";
                }
            }]
        }];
        
        this.list = new Ext.List({
            itemTpl: '{name}',
            store: this.store
        });

        this.items = [this.list];
        
        Gallery.views.AlbumListPanel.superclass.initComponent.apply(this, arguments);
    } 
});

Ext.reg('gallery-albumlistpanel', Gallery.views.AlbumListPanel);