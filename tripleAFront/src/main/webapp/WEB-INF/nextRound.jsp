<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<link rel="icon" href="img/vignette.png">
<title>tripleA</title>
</head>
<body>
<div id="choice">
			<div><a href="#"><img id="logoHome" src="img/logo.png"/></a></div>
			<div>
				<h1>CHOIX DE L'ÉQUIPE</h1>
			</div>
			<table>
				<tr>
					<td class="c1"><h2>HP :</h2></td>
					<td class="c2"><h3 id="texthp">${hp}</h3></td>
					<td class="c3"><input type="range" class="sliderperso" id="hp" value="0" min="0" max="${pts}" step="1"></td>
				</tr>
				<tr>
					<td class="c1"><h2>ATK :</h2></td>
					<td class="c2"><h3 id="textatk">${atk}</h3></td>
					<td class="c3"><input type="range" class="sliderperso" id="atk" value="0" min="0" max="${pts}" step="1"></td>
				</tr>
				<tr>
					<td class="c1"><h2>DEF :</h2></td>
					<td class="c2"><h3 id="textdef">${def}</h3></td>
					<td class="c3"><input type="range" class="sliderperso" id="def" value="0" min="0" max="${pts}" step="1"></td>
				</tr>
			</table>
			<div id="inter"></div>
			<div id="table2">
				<ul>
					<c:forEach items="${listChoice}" var="c">
						<li>
							<a href="#"><img id="card${c.id}" src="img/emptyCard.png" class="cards"></a>
							<div class="cardhp">${c.life}</div>
							<div class="cardatk">${c.atk}</div>
							<div class="carddef">${c.def}</div>
							<div class="titre">GARASU-HÔ</div>
						</li>
					</c:forEach>
				</ul>
			</div>
			<div>
				<form id="myform" method="POST" action="nextRound">
					<input type="hidden" value="" id="choiceStats" name="choiceStats"/>
					<a href="#" id="continue">CONTINUER<br>>></a>
				</form>
			</div>
		</div>
</body>
<script type="text/javascript">
		var hp = document.getElementById("hp");
		var atk = document.getElementById("atk");
		var def = document.getElementById("def");
		var texthp = document.getElementById("texthp");
		var textatk = document.getElementById("textatk");
		var textdef = document.getElementById("textdef");
		var dt = new Date();
		var numberCard = 0;
		var pts = "${pts}";
		var choiceStats = document.getElementById("choiceStats");
		
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
		
		hp.oninput = hplink;
		atk.oninput = atklink;
		def.oninput = deflink;
		
		function hplink(){
			var s = parseInt(hp.value) + ${hp};
			texthp.innerHTML = s;
			atk.max = pts - hp.value - def.value;
			def.max = pts - hp.value - atk.value;
		}

		function atklink(){
			var s = parseInt(atk.value) + ${atk};
			textatk.innerHTML = s;
			hp.max = pts - atk.value - def.value;
			def.max = pts - atk.value - hp.value;
		}

		function deflink(){
			var s = parseInt(def.value) + ${def};
			textdef.innerHTML = s;
			hp.max = pts - def.value - atk.value;
			atk.max = pts - def.value - hp.value;
		}
		
		function select(e){
			if (e.target.id.includes("card")){
				var marginTop = getMargin(e.target.id);
				document.getElementById(e.target.id).style.transition = "all 1.5s";

				if (marginTop == "125px" && numberCard < 2){
					document.getElementById(e.target.id).style.marginTop = "0px";
					numberCard++;
				} else if (marginTop == "0px"){
					document.getElementById(e.target.id).style.marginTop = "125px";
					numberCard--;
				}
			} else if (e.target.id == "continue"){
				var s = parseInt(hp.value) + parseInt(atk.value) + parseInt(def.value);
				
				if (s != 50 && numberCard != 2){
					alert("Attribuer tous les points de stats et choisir deux cartes !");
				} else if (s != 50 && numberCard == 2){
					alert("Attribuer tous les points de stats !");
				} else if (s == 50 && numberCard != 2){
					alert("Choisir deux cartes !");
				} else if (s == 50 && numberCard == 2) {
					setTimeout(getAll, 2000);
				}
			} else if (e.target.id == "logoHome"){
				window.location.href = "${pageContext.request.contextPath}/home";
			}
		}
		
		function getAll(){
			var a="";
			var id = ${listChoice[0].id};
			for (var i=id; i < id+5; i++) {
				if (getMargin("card" + i) == "0px"){
					if (a == ""){
						a += (i-id+1).toString() + "|";
					} else {
						a += (i-id+1).toString();
					}
				}
			}
			
			var retour = texthp.innerText + "|" + textatk.innerText + "|" + textdef.innerText + "|" + a;
			choiceStats.value = retour;
			document.forms["myform"].submit();
		}
		
		function getMargin(id){
			return window.getComputedStyle(document.getElementById(id)).getPropertyValue('margin-top');
		}
	</script>
</html>