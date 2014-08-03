Ext.define("Ext.ux.TouchCalendarView",{extend:"Ext.Container",alias:"widget.touchcalendarview",config:{viewMode:"month",weekStart:1,todayCls:"today",selectedItemCls:"selected",unselectableCls:"unselectable",prevMonthCls:"prev-month",nextMonthCls:"next-month",weekendCls:"weekend",prevPeriodCls:"goto-prev",nextPeriodCls:"goto-next",dayTimeSlotSize:30,timeSlotDateTpls:{},hideHalfHourTimeSlotLabels:true,value:null,store:null,baseTpl:['<table class="{[this.me.getViewMode().toLowerCase()]}">',"<thead>","<tr>",'<tpl for="this.getDaysArray(values)">','<th class="{[this.getHeaderClass(xindex)]}">','<tpl if="xindex === 4">','<span>{[Ext.Date.format(this.me.currentDate, "F")]} {[Ext.Date.format(this.me.currentDate, "Y")]}</span>',"</tpl>",'{date:date("D")}',"</th>","</tpl>","</tr>","</thead>","<tbody>",'<tr class="time-block-row">','<tpl for=".">','<td class="time-block {[this.getClasses(values)]}" datetime="{[this.me.getDateAttribute(values.date)]}">',"{date:this.formatDate()}","</td>",'<tpl if="this.isEndOfRow(xindex)">',"</tr>",'<tpl if="!this.isEndOfPeriod(xindex)">',"<tr>","</tpl>","</tpl>","</tpl>","</tr>","</tbody>","</table>"],cls:"touch-calendar-view",itemSelector:"td.time-block"},timeSlotDateTplsDefaults:{day:'<span class="hour">{date:date("g")}</span><span class="am-pm">{date:date("A")}</span>',month:'{date:date("j")}',week:'{date:date("j")}'},commonTemplateFunctions:{getTimeSlotRowCls:function(b){var c=[],a=b.getMinutes()!==0;if(a){c.push("half-hour")}return c.join(" ")},isHalfHour:function(a){return a.getMinutes()!==0},formatDate:function(a){return this.getTimeSlotDateTpl().apply({date:a})},getTimeSlotDateTpl:function(){var a=this.me.getViewMode().toLowerCase();return this.me.getTimeSlotDateTpls()[a]},getClasses:function(a){var b=[];if(a.selected){b.push(this.me.getSelectedItemCls())}if(a.unselectable){b.push(this.me.getUnselectableCls())}if(a.prevMonth){b.push(this.me.getPrevMonthCls())}if(a.nextMonth){b.push(this.me.getNextMonthCls())}if(a.weekend){b.push(this.me.getWeekendCls())}if(a.today){b.push(this.me.getTodayCls())}return b.join(" ")},isEndOfRow:function(a){return(a%7)===0&&(a>0)},isStartOfRow:function(a){return((a-1)%7)===0&&(a-1>=0)},isEndOfPeriod:function(a){return a%this.me.periodRowDayCount===0},getDaysArray:function(a){var c=[],b;for(b=0;b<this.me.periodRowDayCount;b++){c.push(a[b])}return c},getHeaderClass:function(a){return a===1?this.me.getPrevPeriodCls():a===7?this.me.getNextPeriodCls():""}},constructor:function(b){this.initModel();var a=Ext.create("Ext.data.Store",{model:"TouchCalendarViewModel"});this.setStore(a);b.timeSlotDateTpls=b.timeSlotDateTpls||{};Ext.applyIf(b.timeSlotDateTpls,this.timeSlotDateTplsDefaults);Ext.apply(this,b||{});this.callParent(arguments);this.minDate=this.minDate?Ext.Date.clearTime(this.minDate,true):null;this.maxDate=this.maxDate?Ext.Date.clearTime(this.maxDate,true):null;this.refresh()},initialize:function(){this.element.on({tap:this.onTableHeaderTap,scope:this,delegate:"th"});this.element.on({tap:this.onTimeSlotTap,scope:this,delegate:this.getItemSelector()});this.on({painted:this.syncHeight,resize:this.onComponentResize,scope:this});this.callParent()},initModel:function(){if(!Ext.ModelManager.isRegistered("TouchCalendarViewModel")){Ext.define("TouchCalendarViewModel",{extend:"Ext.data.Model",config:{fields:[{name:"date",type:"date"},{name:"today",type:"boolean"},{name:"unselectable",type:"boolean"},{name:"selected",type:"boolean"},{name:"prevMonth",type:"boolean"},{name:"nextMonth",type:"boolean"},{name:"weekend",type:"boolean"},"timeSlots"]}})}},updateViewMode:function(a,c){this.refresh();var b=this.getPeriodMinMaxDate();this.fireEvent("periodchange",this,b.min.get("date"),b.max.get("date"),"none")},applyViewMode:function(b){b=b.toUpperCase();var a=Ext.ux.TouchCalendarView[b.toUpperCase()];this.getStartDate=a.getStartDate;this.getTotalDays=a.getTotalDays;this.dateAttributeFormat=a.dateAttributeFormat;this.getNextIterationDate=a.getNextIterationDate;this.getDeltaDate=a.getDeltaDate;this.periodRowDayCount=a.periodRowDayCount;Ext.apply(this.commonTemplateFunctions,{me:this});this.setTpl(new Ext.XTemplate((a.tpl||this.getBaseTpl()).join(""),this.commonTemplateFunctions));this.setScrollable({direction:b.toUpperCase()==="DAY"?"vertical":false,directionLock:true});return b},collectData:function(a){var b=[];Ext.each(a,function(c,d){b.push(c.data)},this);return b},populateStore:function(){this.currentDate=this.currentDate||this.value||new Date();var d=true,e=this.currentDate,c=this.getStartDate(e),f=this.getTotalDays(e),a;this.getStore().suspendEvents();this.getStore().data.clear();for(var b=0;b<f;b++){c=this.getNextIterationDate(c,(b===0?0:1));d=(this.minDate&&c<this.minDate)||(this.maxDate&&c>this.maxDate);a=Ext.create("TouchCalendarViewModel",{today:this.isSameDay(c,new Date()),unselectable:d,selected:this.isSameDay(c,this.value)&&!d,prevMonth:(c.getMonth()<e.getMonth()),nextMonth:(c.getMonth()>e.getMonth()),weekend:this.isWeekend(c),date:c});this.getStore().add(a)}this.getStore().resumeEvents()},refreshDelta:function(d){var b=this.currentDate||new Date();var a=this.getDeltaDate(b,d);if(this.isOutsideMinMax(a)){return}this.currentDate=a;this.refresh();var c=this.getPeriodMinMaxDate();this.fireEvent("periodchange",this,c.min.get("date"),c.max.get("date"),(d>0?"forward":"back"))},getPeriodMinMaxDate:function(){return{min:this.getStore().data.first(),max:this.getStore().data.last()}},isOutsideMinMax:function(a){var b=false;if(this.getViewMode()==="MONTH"){b=((this.minDate&&Ext.Date.getLastDateOfMonth(a)<this.minDate)||(this.maxDate&&Ext.Date.getFirstDateOfMonth(a)>this.maxDate))}else{b=((this.minDate&&this.getWeekendDate(a)<this.minDate)||(this.maxDate&&this.getStartDate(a)>this.maxDate))}return b},onTableHeaderTap:function(b,a){a=Ext.fly(a);if(a.hasCls(this.getPrevPeriodCls())||a.hasCls(this.getNextPeriodCls())){this.refreshDelta(a.hasCls(this.getPrevPeriodCls())?-1:1)}},onTimeSlotTap:function(d){if(!d.getTarget("."+this.getUnselectableCls())){var c=Ext.fly(d.getTarget());this.selectCell(c);var b=this.getCellDate(c);var a=this.getValue()||this.currentDate;if(b.getTime()!==a.getTime()){this.setValue(b);this.fireEvent("selectionchange",this,b,a)}}},onComponentResize:function(a){this.syncHeight()},refresh:function(){this.populateStore();var a=this.getStore().getRange();this.setData(this.collectData(a));this.syncHeight()},syncHeight:function(){if(this.getViewMode().toUpperCase()!=="DAY"){var a=this.element.select("table",this.element.dom).first();if(a){a.setHeight(this.element.getHeight())}}},selectCell:function(a){var b=this.getSelectedItemCls();var c=this.element.select("."+b,this.element.dom);if(c){c.removeCls(b)}a.addCls(b);a.up("tr").addCls(b)},getDateRecord:function(a){return this.getStore().findBy(function(b){var c=Ext.Date.clearTime(b.get("date"),true).getTime();return c===Ext.Date.clearTime(a,true).getTime()},this)},getDayStartDate:function(a){return a},isSameDay:function(b,a){if(!b||!a){return false}return Ext.Date.clearTime(b,true).getTime()===Ext.Date.clearTime(a,true).getTime()},isWeekend:function(a){return a.getDay()===0||a.getDay()===6},getWeekendDate:function(b){var a=b.getDay()-this.getWeekStart();a=a<0?6:a;return new Date(b.getFullYear(),b.getMonth(),b.getDate()+0+a)},getCellDate:function(b){var a=b.dom.getAttribute("datetime");return this.stringToDate(a)},getDateCell:function(a){return this.element.select('td[datetime="'+this.getDateAttribute(a)+'"]',this.element.dom).first()},getDateAttribute:function(a){return Ext.Date.format(a,this.dateAttributeFormat)},getSelected:function(){var a=this.element.select("td."+this.getSelectedItemCls(),this.element.dom),b=[];a.each(function(c){b.push(this.getCellDate(c))},this);return b},stringToDate:function(a){return Ext.Date.parseDate(a,this.dateAttributeFormat)},applyTimeSlotDateTpls:function(a){Ext.Object.each(a,function(b,c){if(Ext.isString){a[b]=Ext.create("Ext.XTemplate",c)}},this);return a},statics:{MONTH:{dateAttributeFormat:"d-m-Y",getNextIterationDate:function(b,a){return new Date(b.getFullYear(),b.getMonth(),b.getDate()+(a===0?0:1))},getTotalDays:function(a){var b=Ext.Date.getFirstDateOfMonth(a);return this.isWeekend(b)?42:35},getStartDate:function(a){return Ext.bind(Ext.ux.TouchCalendarView.WEEK.getStartDate,this)(new Date(a.getFullYear(),a.getMonth(),1))},getDeltaDate:function(b,d){var c=b.getMonth()+d,a=new Date(b.getFullYear(),c,1);a.setDate(Ext.Date.getDaysInMonth(a)<b.getDate()?Ext.Date.getDaysInMonth(a):b.getDate());return a},periodRowDayCount:7},WEEK:{dateAttributeFormat:"d-m-Y",getNextIterationDate:function(b,a){return new Date(b.getFullYear(),b.getMonth(),b.getDate()+(a===0?0:1))},getTotalDays:function(a){return 7},getStartDate:function(b){var a=b.getDay()-this.getWeekStart();a=a<0?6:a;return new Date(b.getFullYear(),b.getMonth(),b.getDate()-0-a)},getDeltaDate:function(a,b){return new Date(a.getFullYear(),a.getMonth(),a.getDate()+(b*7))},periodRowDayCount:7},DAY:{dateAttributeFormat:"d-m-Y H:i",tpl:['<table class="{[this.me.getViewMode().toLowerCase()]}">',"<thead>","<tr>",'<th class="{[this.me.getPrevPeriodCls()]} style="display: block;">',"</th>","<th>",'<span>{[Ext.Date.format(values[0].date, "D jS M Y")]}</span>',"</th>",'<th class="{[this.me.getNextPeriodCls()]} style="display: block;"">',"</th>","</tr>","</thead>","<tbody>","<tr>",'<td colspan="3">','<table class="time-slot-table">','<tpl for=".">','<tr class="{[this.getTimeSlotRowCls(values.date)]}">','<td class="label">','<tpl if="!this.me.getHideHalfHourTimeSlotLabels() || !this.isHalfHour(values.date)">',"{date:this.formatDate()}","</tpl>","</td>",'<td class="time-block" colspan="2" datetime="{[this.me.getDateAttribute(values.date)]}">',"</td>","</tr>","</tpl>","</table>","</td>","</tr>","</tbody>","</table>"],getNextIterationDate:function(b,a){var c=b.getTime()+((a===0?0:1)*(this.getDayTimeSlotSize()*60*1000));return new Date(c)},getTotalDays:function(a){return 1440/this.getDayTimeSlotSize()},getStartDate:function(a){return Ext.Date.clearTime(a,true)},getDeltaDate:function(a,b){return new Date(a.getFullYear(),a.getMonth(),a.getDate()+b)},periodRowDayCount:1}}});