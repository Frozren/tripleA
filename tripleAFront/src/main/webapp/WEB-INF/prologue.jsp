<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html id="prologue">
	<head>
		<meta charset="UTF-8">
		<link rel="icon" href="img/vignette.png">
		<title>tripleA</title>
	</head>

	<body>
		<div id="avant"><a href="#"><img id="play" src="img/logoPlay1.png"/></a></div>
		<div id="apres">
			<div class="fade"></div>
			<div><img id="logoHome" src="img/logo.png"/></div>
			<section class="star-wars">
				<div class="crawl">
					<div class="title">
						<p>TRIPLEA présente</p>
						<h1>The Last Hope</h1>
					</div>
					
					<p>Il y a bien longtemps, dans une galaxie lointaine, très lointaine...</p></br>
					<p>C'est une époque de guerre civile. La population est affamée et très en colère contre son dirigeant. Celui-ci vit paisiblement dans son manoir, situé au plus profond sous la surface, juste aux portes des enfers.</p>
					<br/>
					<p>Un groupe de résistant s'est formé et a déjà repris le contrôle de la ville. Il ne reste plus que deux lignes de défense avant de pouvoir atteindre les appartements du dictateur, surnommé "Le Fragile" par les résistants.</p></br>
					<p>C'est à vous qu'à été confié la mission d'aller assassiner ce manipulateur et puissant magicien.</br>Vous et vos deux plus fidèles compagnons plongèrent, armes en main, vers la demeure de BlackJordan, mais à quel prix...</p>
				</div>
			</section>
		</div>
		<div><img id="sound" src="img/sound.png"/></div>
		<div><img id="jordan" src="img/jordan.png"/></div>
		<div id="echap">Appuyer sur ECHAP pour passer...</div>
		<audio id="Audio" style="display: none;">
			<source src="sound/prologue.mp3" type="audio/mp3">
		</audio>
	</body>
	<script type="text/javascript">
		var playButton = document.getElementById("play");
		var avant = document.getElementById("avant");
		var apres = document.getElementById("apres");
		var audio = document.getElementById("Audio");
		var echapDisp = document.getElementById("echap");
		var sound = document.getElementById("sound");
		var jordan = document.getElementById("jordan");
		var i = 0;
		var j = 0;
		var k = 0;
	
		window.onkeydown = echap;
		audio.onended = timer;
		playButton.onmouseover = changeImg;
		playButton.onmouseout = changeImg;
		playButton.onclick = next;
		
		function changeImg(){
			if (k == 0){
				playButton.setAttribute('src', 'img/logoPlay2.png');
				k = 1;
			} else {
				playButton.setAttribute('src', 'img/logoPlay1.png');
				k = 0;
			}
		}
		
		function next(){
			audio.volume = 0.1;
			avant.style.display = "none";
			apres.style.display = "block";
			window.setTimeout(play, 6000);
		}
		
		function play(){
			audio.play();
			window.setTimeout(image, 26500);
		}
		
		function image(){
			if (j == 0){
				jordan.style.visibility = "visible";
				window.setTimeout(image, 1000);
				j = 1;
			} else {
				jordan.style.visibility = "hidden";
			}
		}
	
		function timer(){
			window.setTimeout(function(){window.location.href = ("${pageContext.request.contextPath}/home")}, 3000);
		}
		
		function echap(event){
			var key = event.keyCode;
			if (key == 27){
				if (i == 0){
					echapDisp.style.visibility = "visible";
					i = 1;
				} else {
					window.location.href = "${pageContext.request.contextPath}/home";
					return false;
				}
			} else if (key == 32){
				if (audio.volume == 0.1){
					audio.volume = 0;
					sound.style.visibility = "visible";
				} else {
					audio.volume = 0.1;
					sound.style.visibility = "hidden";
				}
			}
		}
		
	</script>
</html>