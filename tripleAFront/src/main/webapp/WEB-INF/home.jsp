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
			<div id="history">
				<table>
					<thead>
						<tr>
							<th id="tableTitle" colspan="5">HISTORIQUE</th>
						</tr>
						<tr id="tableTitles">
							<th>Date</th>
							<th>Joueur</th>
							<th>Phase</th>
							<th>Dgts infligés</th>
							<th>Dgts reçus</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${history}" var="h">
							<c:if test = "${h.etat == true}">
								<tr style="color: green">
									<td>${h.dateEnd}</td>
									<td>${h.name}</td>
									<td>${h.phase}</td>
									<td>${h.dmg_dealt}</td>
									<td>${h.dmg_taken}</td>
								</tr>
							</c:if>
							<c:if test = "${h.etat == false}">
								<tr style="color: darkred">
									<td>${h.dateEnd}</td>
									<td>${h.name}</td>
									<td>${h.phase}</td>
									<td>${h.dmg_dealt}</td>
									<td>${h.dmg_taken}</td>
								</tr>
							</c:if>
						</c:forEach>
						<tr style="color: darkred">
							<td>${history[0].dateEnd}</td>
							<td>${history[0].name}</td>
							<td>${history[0].phase}</td>
							<td>${history[0].dmg_dealt}</td>
							<td>${history[0].dmg_taken}</td>
						</tr>
						<tr style="color: darkred">
							<td>2020.02.28</td>
							<td>MERLIN</td>
							<td>2</td>
							<td>2300</td>
							<td>6523</td>
						</tr>
						<tr style="color: green">
							<td>2020.02.25</td>
							<td>JORDAN</td>
							<td>3</td>
							<td>23620</td>
							<td>23655</td>
						</tr>
						<tr style="color: darkred">
							<td>2020.02.28</td>
							<td>MERLIN</td>
							<td>2</td>
							<td>2300</td>
							<td>6523</td>
						</tr>
						<tr style="color: green">
							<td>2020.02.25</td>
							<td>JORDAN</td>
							<td>3</td>
							<td>23620</td>
							<td>23655</td>
						</tr>
						<tr style="color: darkred">
							<td>2020.02.28</td>
							<td>MERLIN</td>
							<td>2</td>
							<td>2300</td>
							<td>6523</td>
						</tr>
						<tr style="color: green">
							<td>2020.02.25</td>
							<td>JORDAN</td>
							<td>3</td>
							<td>23620</td>
							<td>23655</td>
						</tr>
						<tr style="color: darkred">
							<td>2020.02.28</td>
							<td>MERLIN</td>
							<td>2</td>
							<td>2300</td>
							<td>6523</td>
						</tr>
						<tr style="color: green">
							<td>2020.02.25</td>
							<td>JORDAN</td>
							<td>3</td>
							<td>23620</td>
							<td>23655</td>
						</tr>
						<tr style="color: darkred">
							<td>2020.02.28</td>
							<td>MERLIN</td>
							<td>2</td>
							<td>2300</td>
							<td>6523</td>
						</tr>
						<tr style="color: green">
							<td>2020.02.25</td>
							<td>JORDAN</td>
							<td>3</td>
							<td>23620</td>
							<td>23655</td>
						</tr>
						<tr style="color: darkred">
							<td>2020.02.28</td>
							<td>MERLIN</td>
							<td>2</td>
							<td>2300</td>
							<td>6523</td>
						</tr>
						<tr style="color: darkred">
							<td>2020.02.25</td>
							<td>JORDAN</td>
							<td>3</td>
							<td>23620</td>
							<td>23655</td>
						</tr>
						<tr style="color: darkred">
							<td>2020.02.28</td>
							<td>MERLIN</td>
							<td>2</td>
							<td>2300</td>
							<td>6523</td>
						</tr>
						<tr style="color: green">
							<td>2020.02.25</td>
							<td>JORDAN</td>
							<td>3</td>
							<td>23620</td>
							<td>23655</td>
						</tr>
						<tr style="color: darkred">
							<td>2020.02.28</td>
							<td>MERLIN</td>
							<td>2</td>
							<td>2300</td>
							<td>6523</td>
						</tr>
						<tr style="color: green">
							<td>2020.02.25</td>
							<td>JORDAN</td>
							<td>3</td>
							<td>23620</td>
							<td>23655</td>
						</tr>
						<tr style="color: darkred">
							<td>2020.02.28</td>
							<td>MERLIN</td>
							<td>2</td>
							<td>2300</td>
							<td>6523</td>
						</tr>
						<tr style="color: green">
							<td>2020.02.25</td>
							<td>JORDAN</td>
							<td>3</td>
							<td>23620</td>
							<td>23655</td>
						</tr>
						<tr style="color: darkred">
							<td>2020.02.28</td>
							<td>MERLIN</td>
							<td>2</td>
							<td>2300</td>
							<td>6523</td>
						</tr>
						<tr style="color: green">
							<td>2020.02.25</td>
							<td>JORDAN</td>
							<td>3</td>
							<td>23620</td>
							<td>23655</td>
						</tr>
						<tr style="color: darkred">
							<td>2020.02.28</td>
							<td>MERLIN</td>
							<td>2</td>
							<td>2300</td>
							<td>6523</td>
						</tr>
						<tr style="color: green">
							<td>2020.02.25</td>
							<td>JORDAN</td>
							<td>3</td>
							<td>23620</td>
							<td>23655</td>
						</tr>
						<tr style="color: darkred">
							<td>2020.02.28</td>
							<td>MERLIN</td>
							<td>2</td>
							<td>2300</td>
							<td>6523</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div id="toptier">
				<img src="img/podium.png">
				<div id="first" style="color: gold"><strong id="premierNom">${histoD[0].name}</strong><br /><span id="premierDate">${histoD[0].dateEnd}</span></div>
				<div id="second" style="color: silver"><strong id="secondNom">${histoD[1].name}</strong><br /><span id="secondDate">${histoD[1].dateEnd}</span></div>
				<div id="third" style="color: brown"><strong id="troisiemeNom">${histoD[2].name}</strong><br /><span id="troisiemeDate">${histoD[2].dateEnd}</span></div>
				<div id="tri"> Dégâts infligés </div>
			</div>
		</div>
	</body>
	
	<script type="text/javascript">
		var premierNom = document.getElementById("premierNom");
		var premierDate = document.getElementById("premierDate");
		var secondNom = document.getElementById("secondNom");
		var secondDate = document.getElementById("secondDate");
		var troisiemeNom = document.getElementById("troisiemeNom");
		var troisiemeDate = document.getElementById("troisiemeDate");
		var tri = document.getElementById("tri");
		var empty = ${emptyHistory};
		var scores = [" Dégâts infligés ", " Dégâts reçus ", " Nombre de victoires "];
		var a = 1;
		var b = 1;
	
		window.onload = smoothOpening;
		window.onclick = menu;
		
		function smoothOpening() {
			bodyOp(0);
			bodyOpTimer(0);
			histo();
		}
		
		function histo(){
			console.log(b%6);
			
			if (b%6 != 0){
				points();
				b++;
			} else {
				tri.innerHTML = scores[a];
				
				if (a == 0){
					premierNom.innerHTML = "${histoD[0].name}";
					premierDate.innerHTML = "${histoD[0].dateEnd}";
					secondNom.innerHTML = "${histoD[1].name}";
					secondDate.innerHTML = "${histoD[1].dateEnd}";
					troisiemeNom.innerHTML = "${histoD[2].name}";
					troisiemeDate.innerHTML = "${histoD[2].dateEnd}";
					a++;
				} else if (a == 1){
					premierNom.innerHTML = "${histoT[0].name}";
					premierDate.innerHTML = "${histoT[0].dateEnd}";
					secondNom.innerHTML = "${histoT[1].name}";
					secondDate.innerHTML = "${histoT[1].dateEnd}";
					troisiemeNom.innerHTML = "${histoT[2].name}";
					troisiemeDate.innerHTML = "${histoT[2].dateEnd}";
					a++;
				} else if (a == 2){
					a = 0;
				}
				b = 1;
			}
			window.setTimeout(histo, 1000);
		}
		
		function points(){
			tri.innerHTML = "•" + tri.textContent + "•";
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