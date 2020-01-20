function onlaunch(save) {
    var elements = document.getElementsByClassName("myCheck");
    for(var i=0; i<elements.length; i++) {
	elements[i].checked = save[i];
    }
}
