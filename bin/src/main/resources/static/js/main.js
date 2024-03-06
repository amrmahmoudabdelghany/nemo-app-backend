
let origin = "" ; 
let baseUri = "/nemo" ; 
let urlMap = new Map([
	 ["stock" , "/"] ,
	 ["items"  ,"/items"] ,
	 ["sales" , "/sales"] ,
	 ["perished" , "/perished"] ,
	 ["expenses" , "/expenses"] ,
	 ["archive" , "/archive"] , 
	 ["statistics" , "/statistics"] 
	 
 ] ) ; 
 let currentUri = baseUri + urlMap.get("stock") ; 
 let currentUrl  = "" ; 
 
$(function(){
	
	  origin = window.location.origin ; 
	  currentUrl = origin + currentUri ; 
     markSelectedTap() ; 
      $("#current-category-name").text(getSelectedCategoryName()) ;  
     var table =  $("#data-table").DataTable(); 
	  $("#data-table tbody").on('click' , 'tr' , function(){
		 if($(this).hasClass('table-info')) {
			 $(this).removeClass('table-info') ; 
		 } else { 
			 table.$('tr.table-info').removeClass('table-info') ; 
			 $(this).addClass('table-info') ; 
		 }
	  });

});

 function onClickTapItem(tapName){ 
	console.log("test") ; 
	currentUrl = origin + baseUri + urlMap.get(tapName) + "?category=" + getSelectedCategoryId() ; 
	location.href = currentUrl ; 
}

 function getSelectedTapName() { 
		
	 return $(".menu-items li[data-selected = 'true']").data("tap-name") ; 
	}


	function markSelectedTap() { 
		$(".menu-items > li").each(function(){
			if($(this).data().selected) {
				$(this).append("<hr style = 'border:1px solid black'>")
				return $(this) ; 
			}
		
		});
	}




