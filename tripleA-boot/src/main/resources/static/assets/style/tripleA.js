function smoothOpening(){
	bodyOp(0);
	bodyOpTimer(0);
}

function bodyOp(num) {
	document.body.style.opacity = num / 100;
}

function bodyOpTimer(num) {
	if (num <= 100) {
		bodyOp(num);
		num += 4;
		window.setTimeout("bodyOpTimer(" + num + ")", 100);
	}
}