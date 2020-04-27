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
					<a href="#" id="continue">CONTINUER<br>>></a>
				</form>
			</div>
			<img id="vs" src="img/vs.png">
		</div>
	</body>

	<script type="text/javascript">
		var card1 = document.getElementById("card1");
		var card2 = document.getElementById("card2");
		var card3 = document.getElementById("card3");
		var choicePos = document.getElementById("choicePos");
		var card = 0;
	
		window.onload = smoothOpening;
		window.onclick = select;
		
		
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
					alert("Select your ranged card.");
				} else {
					choicePos.value = card;
					document.forms["myform"].submit();
				}
			}
		}
	</script>
</html>