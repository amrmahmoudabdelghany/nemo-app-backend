

$(function(){
	
	
	  // a[data-selected = 'true']
	//  $('.list-group-item-info').removeClass("list-group-item-info");
	 var selectedCategory =   $(".list-group-item[data-selected='true']")  ;
	 //selectedCategory.toggleClass('list-group-item' , false) ; 
	 selectedCategory.toggleClass('list-group-item-info' , true) ; 

		$(".list-group-item").on("click", function() {
			//$('.list-group-item-info').removeClass("list-group-item-info");
		//	$(this).toggleClass("list-group-item-info", true);
		});
		
		
});

function onSelectCategory(categoryId) {
	
	window.location = origin +baseUri  + urlMap.get(getSelectedTapName()) + "?category=" + categoryId ; 
	
}

function getSelectedCategoryName() { 
	return 	$(".category-list a[data-selected = 'true']  ").text() ; 
}

function getSelectedCategoryId() { 
	var defaultCategoryIdHolder = $(".category-list li[data-selected = 'true'] ") ; 
	
	if(defaultCategoryIdHolder.length  != 0) { 
		console.log("default Category Holder is : " + defaultCategoryIdHolder) ; 
	return defaultCategoryIdHolder.data().category ; 
	}else if($("#preCategoryId").length != 0) { 
		return $("#preCategoryId").val() ; 
	}else { 
		return "*" ; 
	}

 
}
