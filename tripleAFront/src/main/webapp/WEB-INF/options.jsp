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
		<div id="options">
			<div><a href="#"><img id="home" src="img/logo.png"/></a></div>
			<h1>Options</h1>
			<div>
				<form method="POST" action="home" name="formOption">
					<table>
						<tbody>
							<tr>
								<td align="right"><h3>Difficulté</h3></td>
								<td align="left"><h3>
								<select id="selection1" name="selection1">
	
								<option value="1">Facile<audio src="infernoSong.mp3" controls></audio></option>
	
								<option selected>Normale</option>
	
								<option value="3">Difficile</option>
	
								<option value="4">Expert</option>
	
								</select>
								</h3></td>
							</tr>
							<tr>
								<td align="right"><h3>Musique</h3></td>
								<td align="left"><h3>
								<select id="selection2" name="selection2">
	
								<option value="1" selected>Inferno</option>
	
								<option value="2">Disco</option>
	
								<option value="3">Final Countdown</option>
	
								<option value="4">Jailhouse Rock</option>
	
								</select>
								</h3></td>
							</tr>
							<tr>
								<td align="right"><h3>Résolution</h3></td>
								<td align="left"><h3>
								<select id="selection3" name="selection3">
	
								<option value="1" selected>1080P</option>
	
								<option value="2">720P</option>
	
								<option value="3">480P</option>
	
								</select>
								</h3></td>
							</tr>
							<tr>
								<td align="right"><h3>Luminosité</h3></td>
								<td align="left"><input id="lum" name="lum" type="range" value="1" min="0.2" max="1" step="0.01"/></td>
							</tr>
							<tr><input id="apply" value="Appliquer" type="submit"/></tr>
						</tbody>
					</table>
				</form>
			</div>
			<img id="wip" src="img/workip.png">
		</div>
	</body>
	
	<script type="text/javascript">
		var home = document.getElementById("home");
		var lum = document.getElementById("lum");
	
		home.onclick = retour;
		lum.oninput = lummod;
		
		window.onload = function() {smoothOpening()}
		
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
		
		function lummod(){
			document.body.style.filter = "brightness(" + lum.value + ")";
		}
	
		function retour() {
			window.location.href = "${pageContext.request.contextPath}/home";
		}
	</script>
	
</html>