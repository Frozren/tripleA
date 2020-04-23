<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>




<html>

<head>
<meta charset="UTF-8">

<title>Music</title>
</head>
	<body id="battle" onload="NewOnglet('<%="battleField" %>');" style="cursor: url('img/cursor/shield.ico'), crosshair; height: 100%;">
		<form method="POST" action="music">
				<audio controls="controls" id="music" loop="loop" autoplay="autoplay">
					<source src="sound/ff7-boss-theme-remake.mp3" type="audio/mp3"/>
				</audio>
		</form>
	</body>
	
	<script type="text/javascript">
	var vol=document.getElementById("music");
	
	function setVolume() {
	vol.volume = 0.2;}
	
	function NewOnglet(adresse){
		setVolume();
		var myWindow = window.open(adresse,'_blank');
		 myWindow.focus();
		 document.getElementById("c").value = '0';}
	</script>
	
</html>