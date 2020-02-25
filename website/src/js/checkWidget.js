function checkWidget() {
    // Get the checkbox
    var elements = document.getElementsByClassName("myCheck");
    var text = document.getElementsByClassName("widget");
    for(var i=0; i<elements.length; i++) {
	for(var j=0; j<text.length; j++) {
	    if (elements[i].getAttribute('name') == text[j].getAttribute('name'))
		if (elements[i].checked == true){
		    text[j].style.display = "block";
		} else {
		    text[j].style.display = "none";
		}
	}
    }
    refreshCookie();
}
