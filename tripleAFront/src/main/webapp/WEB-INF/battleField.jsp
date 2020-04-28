<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>


<html translate="no">

<head>
<meta charset="UTF-8">

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

<script src="https://code.jquery.com/jquery-3.5.0.min.js" integrity="sha256-xNzN2a4ltkB44Mc/Jz3pT4iU1cmeR0FkXs4pru/JxaQ=" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
<title>BattleField</title>
</head>
<body id="battle">
	<form method="POST" action="BattleField">

		<div class="row" id="top">
			<div class="col-2">
				<img id="logo" src="img/logo.png" />
			</div>
		</div>
		<div class="row" id="hp">
			<div class="col-2">
				<input type="text" id="c" name="card" value="${document.card}"
					style="visibility: hidden" />
			</div>
			<div class="col-3">
				<div id="hp1">
					<div class="progress">
						<div
							class="progress-bar progress-bar-striped progress-bar-animated bg-danger"
							role="progressbar" style="width: ${hpb1}%"
							aria-valuenow="${hpb1}" aria-valuemin="0"
							aria-valuemax="100"></div>
					</div>
				</div>
			</div>
			<div class="col-2">
				<img id="vs" src="img/field/vs.png">
			</div>
			<div class="col-3">
				<div id="hp2">
					<div class="progress">
						<div
							class="progress-bar progress-bar-striped progress-bar-animated bg-danger"
							role="progressbar" style="width: ${hpb2}%"
							aria-valuenow="${hpb2}" aria-valuemin="0"
							aria-valuemax="100"></div>
					</div>
				</div>
			</div>
		</div>

		<div class="row" id="mid">
			<div id="divconsole">
				<div class="scroll" id="console">${message}</div>
			</div>

			<div id="field" style="cursor: url('${cursor}'), crosshair;">
				<div class="row" id="hfield">
					<div class="col-2" id="left">
						<div class="row" id="tleft"></div>
						<div class="row" id="hcard">
							<div class="col-8">
								<div id="card">
									<input type="image" class="imgcard" idCard="1" id="c1"
										src="img/card/cardAP.png"
										style="visibility: ${disc1}; cursor: url('${cursorch}'), not-allowed;">
									<div class="divname" style="visibility: ${disc1}">
										<p class="name">${namec1}</p>
									</div>
									<div class="divclass" style="visibility: ${disc1}">
										<p class="class">Carte C1: ${classc1}</p>
									</div>
									<div class="def" style="visibility: ${disc1}">
										<p class="stat">${deckH[0].def}</p>
									</div>
									<div class="atk" style="visibility: ${disc1}">
										<p class="stat">${deckH[0].atk}</p>
									</div>
									<div class="life" style="visibility: ${disc1}">
										<p class="stat">${deckH[0].life}</p>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="col-8">
						<div class="row" id="topField"></div>
						<div class="row" id="hcard">
							<div class="col-1" id="ltcard"></div>
							<div class="col-2">
								<div id="card">
									<input type="image" class="imgcard" idCard="2" id="c2"
										src="img/card/cardAB.png"
										style="visibility: ${disc2}; cursor: url('${cursorch}'), not-allowed;">
									<div class="divname" style="visibility: ${disc2}">
										<p class="name">${namec2}</p>
									</div>
									<div class="divclass" style="visibility: ${disc2}">
										<p class="class">Carte C2: ${classc2}</p>
									</div>
									<div class="def" style="visibility: ${disc2}">
										<p class="stat">${deckH[1].def}</p>
									</div>
									<div class="atk" style="visibility: ${disc2}">
										<p class="stat">${deckH[1].atk}</p>
									</div>
									<div class="life" style="visibility: ${disc2}">
										<p class="stat">${deckH[1].life}</p>
									</div>
								</div>
							</div>
							<div class="col-5" id="mtcard"></div>
							<div class="col-2">
								<div id="card">
									<input type="image" class="imgcard" idCard="4" id="c4"
										src="img/card/cardTG.png"
										style="visibility: ${disc4}; cursor: url('${cursorai}'), not-allowed;">
									<div class="divname" style="visibility: ${disc4}">
										<p class="name">${namec4}</p>
									</div>
									<div class="divclass" style="visibility: ${disc4}">
										<p class="class">Carte C4: ${classc4}</p>
									</div>
									<div class="def" style="visibility: ${disc4}">
										<p class="stat">${deckAI[0].def}</p>
									</div>
									<div class="atk" style="visibility: ${disc4}">
										<p class="stat">${deckAI[0].atk}</p>
									</div>
									<div class="life" style="visibility: ${disc4}">
										<p class="stat">${deckAI[0].life}</p>
									</div>
								</div>
							</div>
						</div>
						<div class="row" id="midField">
							<img src="img/youWin.png" id="win">
							<img src="img/gameOver.gif" id="lose">
						</div>
						<div class="row" id="hcard">
							<div class="col-1" id="ltcard"></div>
							<div class="col-2">
								<div id="card">
									<input type="image" class="imgcard" idCard="3" id="c3"
										src="img/card/cardAV.png"
										style="visibility: ${disc3}; cursor: url('${cursorch}'), not-allowed;">
									<div class="divname" style="visibility: ${disc3}">
										<p class="name">${namec3}</p>
									</div>
									<div class="divclass" style="visibility: ${disc3}">
										<p class="class">Carte C3: ${classc3}</p>
									</div>
									<div class="def" style="visibility: ${disc3}">
										<p class="stat">${deckH[2].def}</p>
									</div>
									<div class="atk" style="visibility: ${disc3}">
										<p class="stat">${deckH[2].atk}</p>
									</div>
									<div class="life" style="visibility: ${disc3}">
										<p class="stat">${deckH[2].life}</p>
									</div>
								</div>
							</div>

							<div class="col-5" id="mtcard"></div>
							<div class="col-2">
								<div id="card">
									<input type="image" class="imgcard" idCard="5" id="c5"
										src="img/card/cardJR.png"
										style="visibility: ${disc5}; cursor: url('${cursorai}'), not-allowed;">
									<div class="divname" style="visibility: ${disc5}">
										<p class="name">${namec5}</p>
									</div>
									<div class="divclass" style="visibility: ${disc5}">
										<p class="class">Carte C5: ${classc5}</p>
									</div>
									<div class="def" style="visibility: ${disc5}">
										<p class="stat">${deckAI[1].def}</p>
									</div>
									<div class="atk" style="visibility: ${disc5}">
										<p class="stat">${deckAI[1].atk}</p>
									</div>
									<div class="life" style="visibility: ${disc5}">
										<p class="stat">${deckAI[1].life}</p>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="col-2" id="left">
						<div class="row" id="tleft"></div>
						<div class="row" id="hcard">
							<div class="col-8">
								<div id="card">
									<input type="image" class="imgCard" idCard="6" id="c6"
										src="img/card/cardJA.png"
										style="visibility: ${disc6}; cursor: url('${cursorai}'), not-allowed;">
									<div class="divname" style="visibility: ${disc6}">
										<p class="name">${namec6}</p>
									</div>
									<div class="divclass" style="visibility: ${disc6}">
										<p class="class">Carte C6: ${classc6}</p>
									</div>
									<div class="def" style="visibility: ${disc6}">
										<p class="stat">${deckAI[2].def}</p>
									</div>
									<div class="atk" style="visibility: ${disc6}">
										<p class="stat">${deckAI[2].atk}</p>
									</div>
									<div class="life" style="visibility: ${disc6}">
										<p class="stat">${deckAI[2].life}</p>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="row" id="bottom">
			<div class="col-2">
				<audio controls="controls" id="music" loop="loop" autoplay="autoplay">
					<source id="sBat" src="sound/ff7-boss-theme-remake.mp3" type="audio/mp3"/>
				</audio>
			</div>
			<div class="col-3">
				<audio id="sDef" src="sound/def.mp3"></audio>
				<audio id="sAtt" src="sound/att.mp3"></audio>
				<audio id="gameOver" src="sound/gameOver.mp3"></audio>
				<audio id="victory" src="sound/victory.mp3"></audio>
			</div>
			<div class="col-2">
				<img src="img/field/exit.png" id="exit" type="button" data-toggle="modal"  data-target="#modalClose">
				<div class="modal fade" id="modalClose" tabindex="-1" role="dialog" aria-labelledby="closeModalLabel" aria-hidden="true">
					<div class="modal-dialog" role="document" id="modal">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title" id="closeModalLabel">Quitter</h5>
								<button type="button" class="close" data-dismiss="modal" aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
							</div>
							<div class="modal-body">
								Voullez vous quitter la partie?
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-secondary" data-dismiss="modal" id="annuler">Annuler</button>
								<button type="button" class="btn btn-primary" id="fermer">Confirmer</button>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-2">
				<input type="text" id="endGame" value="${endGame}" style="visibility: hidden;"></input>
			</div>
		</div>
	</form>
</body>

<script type="text/javascript">
	var buttonClose=document.getElementById("fermer");
	buttonClose.onclick=fermer;
	
	function initCard(){
		$('input[type="image"]').on('click', function() {
			let idCard = $(this).attr('idCard');
			$('#c').val(idCard);
			console.log(idCard)
			
			$.ajax({
				type : 'POST',
				processData : false,
				contentType : false,
				url : "battleField?card=" + idCard,
				success : function(resp) {
					
					let container = $('<div />');
					container.html(resp);
					console.log(container);
					
					$('#hp1').parent().html(
						container.find('#hp1').html()
					)
						
					$('#hp2').parent().html(
						container.find('#hp2').html()
					)
					
					$('#field').parent().html(
						container.find('#field').html()
					)
					
					$('#endGame').parent().html(
						container.find('#endGame').html()
					)
				
					console.log(resp);
					console.log("OK");
					endGame()
					initCard()
					setVar()
				}
			})
			return false;
		});
	}
	
	
	$("form").submit(function(e){
		console.log('form submit')
		e.preventDefault();
		return false;
	});
	
	function setVar(){
		var c1=document.getElementById("c1");
		var c2=document.getElementById("c2");
		var c3=document.getElementById("c3");
		var c4=document.getElementById("c4");
		var c5=document.getElementById("c5");
		var c6=document.getElementById("c6");
		var sound = document.getElementById("music");
		c1.onmouseover=soundDef;
		c2.onmouseover=soundDef;
		c3.onmouseover=soundDef;
		c4.onmouseover=soundAtt;
		c5.onmouseover=soundAtt;
		c6.onmouseover=soundAtt;

	}

	function setVolume() {
		var sound = document.getElementById("music");
		sound.volume = 0.05;
	}
	
	function soundDef() {
		var sound = document.getElementById("sDef");
		sDef.volume = 0.1;
		sound.play();
	}

	function soundAtt() {
		var sound = document.getElementById("sAtt");
		sAtt.volume = 0.1;
		sound.play();
	}
	
	function fermer() {
		window.location.href = "${pageContext.request.contextPath}/home";
	}

	/* $("#exit").click(function() {
		if(confirm("Quitter?")){
			window.location.href = "${pageContext.request.contextPath}/home";
		}
	}); */
	
	function endGame() {
		var endGame = document.getElementById("endGame");
		var end = endGame.value;
		//alert(end);
		if (end=="win") {
			document.getElementById('win').style.visibility='visible';
			var sound = document.getElementById("victory");
			gameOver.volume = 0.05;
			sound.play();
			setTimeout(function(){window.location.href = "${pageContext.request.contextPath}/home";}, 6000);
			
		}
		else if (end=="lose") {
			document.getElementById('lose').style.visibility='visible';
			var sound = document.getElementById("gameOver");
			gameOver.volume = 0.05;
			sound.play();
			setTimeout(function(){window.location.href = "${pageContext.request.contextPath}/home";}, 6000);
		}
		else{
			console.log("rien");}
	}
	
	
	initCard()
	setVar()
	setVolume()
</script>

</html>