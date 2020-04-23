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
		<div id="newGame">
			<div><a href="#"><img id="home" src="img/logo.png"/></a></div>
			<div><h1 id="titre">NOM DE VOTRE HÉROS</h1></div>
			<div>
				<form method="POST" action="newGame">
					<input id="pseudo" autocomplete="off" autocorrect="off" autofocus type="text" name="pseudo" minlength=3 maxlength="14" placeholder="PIPPIN" required>
				</form>
			</div>
			<br>
			<div id="erreur"></div>
		</div>
	</body>
	
	<script type="text/javascript">
		var input = document.getElementById("pseudo");
		var titre = document.getElementById("titre");
		var home = document.getElementById("home");
		var messErreur = document.getElementById("erreur");
	
		home.onclick = retour;
		onkeyup = flou;
		onkeypress = alphaOnly;
		
		window.onload = function() {smoothOpening()}
		
		function smoothOpening() {
			erreur();
			bodyOp(0);
			bodyOpTimer(0);
		}
		
		function erreur(){
			if ("${sessionScope.erreur}" == "Y"){
				messErreur.innerHTML = "Une partie est déjà enregistrée à ce nom !"
			}
		}
		
		function alphaOnly(event) {
			var key = event.keyCode;
			return ((key >= 65 && key <= 90) || (key >= 95 && key <= 122) || key == 13);
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
	
		function flou(){
			if (input.value.length >= 3){
				num = 1.278*Math.log(input.value.length)+0.9088;
				titre.style.filter = "blur(" + num + "px)";
				document.getElementById("pseudo").style.color = "#00b000";
				document.getElementById("pseudo").style.textShadow = "0 0 5px #43ff43";
			} else {
				titre.style.filter = "blur(0px)";
				document.getElementById("pseudo").style.color = "#b00000";
				document.getElementById("pseudo").style.textShadow = "0 0 5px #ff4343";
			}
		}
	
		function retour() {
			window.location.href = "${pageContext.request.contextPath}/home";
		}
	</script>
	
</html>