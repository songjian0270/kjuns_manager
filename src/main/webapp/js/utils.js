
function serializeJson(obj){
 	var serializeObj={};
 	$(obj.serializeArray()).each(function(){
		 	serializeObj[this.name]=this.value;
 	});
 	return serializeObj;
};