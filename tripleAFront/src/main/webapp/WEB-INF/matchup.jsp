<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8"/>
		<link rel="icon" href="img/vignette.png">
		<title>tripleA</title>
	</head>

	<body>
		<div id="matchup">
			<div><a href="#"><img id="logoHome" src="img/logo.png"/></a></div>
			<div>
				<h1>MATCHUP</h1>
			</div>
			<table>
				<tr id="row1">
					<td id="c11">
						<a href="#"><img id="card1" src="img/emptyCard.png" class="cards"></a>
						<div class="cardhp" id="c1hp">${cardH[0].life}</div>
						<div class="cardatk" id="c1atk">${cardH[0].atk}</div>
						<div class="carddef" id="c1def">${cardH[0].def}</div>
						<div class="titre">SHINSETSU</div>
					</td>
					<td id="c12">
						<a href="#"><img id="card2" src="img/emptyCard.png" class="cards"></a>
						<div class="cardhp" id="c1hp">${cardH[1].life}</div>
						<div class="cardatk" id="c1atk">${cardH[1].atk}</div>
						<div class="carddef" id="c1def">${cardH[1].def}</div>
						<div class="titre">SHINSETSU</div>
					</td>
					<td id="c13">
						<a href="#"><img id="card3" src="img/emptyCard.png" class="cards"></a>
						<div class="cardhp" id="c1hp">${cardH[2].life}</div>
						<div class="cardatk" id="c1atk">${cardH[2].atk}</div>
						<div class="carddef" id="c1def">${cardH[2].def}</div>
						<div class="titre">SHINSETSU</div>
					</td>
				</tr>
				<tr id="row2">
					<td id="c21">
						<img id="cAI1" src="img/emptyCard.png" class="cards">
						<div class="cardhp" id="c1hp">${cardAI[0].life}</div>
						<div class="cardatk" id="c1atk">${cardAI[0].atk}</div>
						<div class="carddef" id="c1def">${cardAI[0].def}</div>
						<div class="titre">ITAZURA</div>
					</td>
					<td id="c22">
						<img id="cAI2" src="img/emptyCard.png" class="cards">
						<div class="cardhp" id="c1hp">${cardAI[1].life}</div>
						<div class="cardatk" id="c1atk">${cardAI[1].atk}</div>
						<div class="carddef" id="c1def">${cardAI[1].def}</div>
						<div class="titre">ITAZURA</div>
					</td>
					<td id="c23">
						<img id="cAI3" src="img/emptyCard.png" class="cards">
						<div class="cardhp" id="c1hp">${cardAI[2].life}</div>
						<div class="cardatk" id="c1atk">${cardAI[2].atk}</div>
						<div class="carddef" id="c1def">${cardAI[2].def}</div>
						<div class="titre">ITAZURA</div>
					</td>
				</tr>
			</table>
			
			<div>
				<form id="myform" method="POST" action="matchup">
					<input type="hidden" value="" id="choicePos" name="choicePos"/>
					<a href="#" id="continue">FIGHT<br>>></a>
				</form>
			</div>
			<img id="vs" src="img/vs.png">
		</div>
		<div id = "decompte">
			<img id="explo" src="img/explo.gif" style="width:500px;position:absolute;top:10%;left:35%"/>
			<h1 id="decText"></h1>
		</div>
		<audio id="Audio3" style="display: none;">
			<source src="sound/SECountdown3.mp3" type="audio/mp3">
		</audio>
		<audio id="Audio2" style="display: none;">
			<source src="sound/SECountdown2.mp3" type="audio/mp3">
		</audio>
		<audio id="Audio1" style="display: none;">
			<source src="sound/SECountdown1.mp3" type="audio/mp3">
		</audio>
		<audio id="Audio0" style="display: none;">
			<source src="sound/SEFight.mp3" type="audio/mp3">
		</audio>
	</body>

	<script type="text/javascript">
		var matchup = document.getElementById("matchup");
		var decompte = document.getElementById("decompte");
		var decText = document.getElementById("decText");
		var explo = document.getElementById("explo");
		var card1 = document.getElementById("card1");
		var card2 = document.getElementById("card2");
		var card3 = document.getElementById("card3");
		var choicePos = document.getElementById("choicePos");
		var audio0 = document.getElementById("Audio0");
		var audio1 = document.getElementById("Audio1");
		var audio2 = document.getElementById("Audio2");
		var audio3 = document.getElementById("Audio3");
		var card = 0;
		var i = 3;
	
		window.onload = smoothOpening;
		window.onclick = select;
		
		
		function smoothOpening() {
			decompte.style.display = "none";
			explo.style.display = "none";
			audio0.volume = 0.1;
			audio1.volume = 0.1;
			audio2.volume = 0.1;
			audio3.volume = 0.1;
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
		
		function select(e){
			if (e.target.id == "logoHome"){
				window.location.href = "${pageContext.request.contextPath}/home";
			} else if (e.target.id == "card1"){
				card1.style.setProperty("-webkit-filter", "drop-shadow(0px 0px 10px #00e4ff)");
				card2.style.setProperty("-webkit-filter", "drop-shadow(0px 0px 0px #00e4ff)");
				card3.style.setProperty("-webkit-filter", "drop-shadow(0px 0px 0px #00e4ff)");
				card = 1;
			} else if (e.target.id == "card2"){
				card1.style.setProperty("-webkit-filter", "drop-shadow(0px 0px 0px #00e4ff)");
				card2.style.setProperty("-webkit-filter", "drop-shadow(0px 0px 10px #00e4ff)");
				card3.style.setProperty("-webkit-filter", "drop-shadow(0px 0px 0px #00e4ff)");
				card = 2;
			} else if (e.target.id == "card3"){
				card1.style.setProperty("-webkit-filter", "drop-shadow(0px 0px 0px #00e4ff)");
				card2.style.setProperty("-webkit-filter", "drop-shadow(0px 0px 0px #00e4ff)");
				card3.style.setProperty("-webkit-filter", "drop-shadow(0px 0px 10px #00e4ff)");
				card = 3;
			} else if (e.target.id == "continue"){
				if (card == 0){
					alert("Choisir votre carte à distance !");
				} else {
					decText.style.transition = "all 1.5s";
					matchup.style.display = "none";
					decText.innerHTML = i;
					decompte.style.display = "block";
					window.setTimeout(countDown, 100);
				}
			}
		}
		
		function countDown(){
			decText.style.transition = "all 1.5s";
			decText.style.fontSize = "20vw";
			if (i != 0){
				if (i == 3){
					audio3.play();
				} else if (i == 2){
					audio2.play();
				} else {
					audio1.play();
				}
				window.setTimeout(displayOFF, 2000);
			} else {
				audio0.play();
				window.setTimeout(explosion, 840);
			}
		}
		
		function explosion(){
			explo.style.display = "none";
			window.setTimeout(function(){choicePos.value = card; document.forms["myform"].submit();}, 1000);
		}
		
		function displayON(){
			decompte.style.visibility = "visible";
			window.setTimeout(countDown, 100);
		}
		
		function displayOFF(){
			decText.style.transition = "all 0s";
			decompte.style.visibility = "hidden";
			i--;
			if (i != 0){
				decText.innerHTML = i;
			} else {
				decText.innerHTML = "¡ FIGHT !";
				explo.style.display = "block";
			}
			decText.style.fontSize = "1vw";
			displayON();
		}
	</script>
</html>