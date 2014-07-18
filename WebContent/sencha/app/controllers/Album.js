// controller
Ext.regController("Album", {
    // index controller
    index: function(list, record, slide) {                  
        var albumView = this.render({
            xtype: 'gallery-albumviewpanel',            
            listeners: {
                deactivate: function(albumView) {
                    albumView.destroy();
                },
                dataView: {
                    itemtap: function(list, index) {
                        var imgdata = albumView.dataView.store.data.items;
                        this.viewCarousel(list, index, imgdata, record);
                    },
                    itemswipe: function(list, index) {
                        var imgdata = albumView.dataView.store.data.items;
                        albumView.update(
                            //'<div style="background:url(' + imgdata[index].data.path + ') no-repeat;'+ 'width:320px;height:640px;"></div>'
                            '<div style="background:url(' + imgdata[index].data.path + ') no-repeat;'+ 'width:100%;height:100%;"></div>'
                        );
                    },
                    scope: this
                }
            }
        });
        albumView.dataView.store.proxy.url = 'http://localhost:8089/homepage/mluvphoto.some?no=532';
        
       /* albumView.query('#homeButton')[0].on({
            tap: this.index,
            scope: this
        });*/
        
        albumView.query('#albumToolbar')[0].setTitle("SomeNsomE");            
            
        Gallery.viewport.setActiveItem(albumView, {
            type: 'slide',
            direction: slide
        });
    },
    viewList: function(list, record, slide) {                  
        var albumView = this.render({
            xtype: 'gallery-albumviewpanel',            
            listeners: {
                deactivate: function(albumView) {
                    albumView.destroy();
                },
                dataView: {
                    itemtap: function(list, index) {
                        var imgdata = albumView.dataView.store.data.items;
                        this.viewCarousel(list, index, imgdata, record);
                    },
                    itemswipe: function(list, index) {
                        var imgdata = albumView.dataView.store.data.items;
                        albumView.update(
                            /*'<div style="background:url(' + imgdata[index].data.path + ') no-repeat;'+ 'width:320px;height:640px;"></div>'*/
                        	'<div style="background:url(' + imgdata[index].data.path + ') no-repeat;'+ 'width:100%;height:100%;"></div>'
                        );
                    },
                    scope: this
                }
            }
        });
        albumView.dataView.store.proxy.url = 'http://localhost:8089/homepage/mluvphoto.some?no=532';
       // albumView.dataView.store.proxy.url = 'get-file.php?path=public/img/Building';
        
        /*albumView.query('#homeButton')[0].on({
            tap: this.index,
            scope: this
        });*/
        
        albumView.query('#albumToolbar')[0].setTitle("SomeNsomE");            
            
        Gallery.viewport.setActiveItem(albumView, {
            type: 'slide',
            direction: slide
        });
    },
    // viewCarousel controller
    viewCarousel: function(list, index, imgdata, album) {
        var albumCarousel = this.render({
            xtype: 'gallery-albumcarouselpanel',
            listeners: {
                deactivate: function() {
                    albumCarousel.destroy();
                }
            }
        });
        
        for(i=0; i<imgdata.length; i++) {
            albumCarousel.carousel.add({
                html: '<div class="img"><img src="'+imgdata[i].data.path+'" width="100%" height="100%"></img></div>'
                //'<div class="img" style="background: url('+imgdata[i].data.path+');"></div>'
            });
        }
        
        albumCarousel.query('#homeButton')[0].on({
            tap: this.index,
            scope: this
        });

        albumCarousel.query('#backButton')[0].on({
            tap: function() {
                this.viewList(list, album, 'right');
            },
            scope: this
        })
        
        Gallery.viewport.setActiveItem(albumCarousel, 'slide');
        albumCarousel.carousel.setActiveItem(index);
    }
});