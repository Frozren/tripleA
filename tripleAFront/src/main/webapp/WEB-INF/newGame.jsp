<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="icon" href="img/vignette.png">
		<title>tripleA</title>
		<script src="https://code.jquery.com/jquery-3.5.0.min.js" integrity="sha256-xNzN2a4ltkB44Mc/Jz3pT4iU1cmeR0FkXs4pru/JxaQ=" crossorigin="anonymous"></script>
	</head>

	<body>
		<div id="newGame">
			<div><a href="#"><img id="logoHome" src="img/logo.png"/></a></div>
			<div><h1 id="titre">NOM DE VOTRE HÉROS</h1></div>
			<div>
				<form id="myform" method="POST" action="newGame">
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
		var home = document.getElementById("logoHome");
		var messErreur = document.getElementById("erreur");
		var pseudoOK = false;
	
		home.onclick = retour;
		onkeyup = flou;
		onkeypress = alphaOnly;
		
		window.onload = function() {smoothOpening()}
		
		function smoothOpening() {
			bodyOp(0);
			bodyOpTimer(0);
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
	
		function flou(e){
			var key = e.keyCode;
			if (key != 13){
				if (input.value.length >= 3){
					verifPseudo();
					num = 1.278*Math.log(input.value.length)+0.9088;
					titre.style.filter = "blur(" + num + "px)";
					input.style.color = "#00b000";
					input.style.textShadow = "0 0 5px #43ff43";
				} else {
					titre.style.filter = "blur(0px)";
					input.style.color = "#b00000";
					input.style.textShadow = "0 0 5px #ff4343";
				}
			} else if (pseudoOK) {
				document.forms["myform"].submit();
			}
			
		}
	
		function retour() {
			window.location.href = "${pageContext.request.contextPath}/home";
		}
		
		function verifPseudo(){
			$.ajax({
				type : 'POST',
				data : {
					pseudo : $("#pseudo").val(),
					checkPseudo : "true"
					},
				success : function(resp){
					if (resp == "Y"){
						input.style.color = "#b00000";
						input.style.textShadow = "0 0 5px #ff4343";
						messErreur.innerHTML = "Une partie est déjà enregistrée à ce nom !";
						pseudoOK = false;
					} else {
						input.style.color = "#00b000";
						input.style.textShadow = "0 0 5px #43ff43";
						messErreur.innerHTML = "";
						pseudoOK = true;
					}
				}
			});
		}
		
		$("form").submit(function(e){
			e.preventDefault();
			return false;
		});
	</script>
	
</html>