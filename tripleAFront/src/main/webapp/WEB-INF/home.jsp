<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="icon" href="img/vignette.png">
		<title>tripleA</title>
	</head>

	<body>
		<div id="home">
			<div id="logo1"><img src="img/logoHaut.png"></div>
			<div id="menu">
				<div id="nav">
					<ul>
						<li><a id="newGame" href="#">NOUVELLE PARTIE</a></li>
						<li><a id="save" href="#">CHARGER UNE PARTIE</a></li>
						<li><a id="options" href="#">OPTIONS</a></li>
						<li><a id="leave" href="#">QUITTER</a></li>
					</ul>
				</div>
				<div id="logo2"><img src="img/logoBas.png"></div>
			</div>
		</div>
	</body>
	
	<script type="text/javascript">
		window.onload = smoothOpening;
		window.onclick = menu;
		
		function smoothOpening() {
			bodyOp(0);
			bodyOpTimer(0);
		}
	
		function bodyOp(num) {
			document.body.style.opacity = num/100;
		}
	
		function bodyOpTimer(num) {
			if (num <= 100) {
				bodyOp(num);
				num += 4;
				window.setTimeout("bodyOpTimer("+num+")", 100);
			}
		}
	
		function menu(e) {
			if (e.target.id == "newGame" || e.target.id == "save" || e.target.id == "options"){
				window.location.href = ("${pageContext.request.contextPath}/" + e.target.id);
			} else if (e.target.id == "leave"){
				if (confirm("Voulez-vous vraiment quitter ?")){
					window.location.href = "https://www.google.com";
				}
			}
		}
	</script>
	
</html>