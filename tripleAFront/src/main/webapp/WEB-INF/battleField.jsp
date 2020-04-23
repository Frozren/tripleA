<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>


<html>

<head>
<meta charset="UTF-8">

<title>BattleField</title>
</head>
<body id="battle"
	style="cursor: url('${sessionScope.cursor}'), crosshair;">
	<form method="POST" action="battleField">

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
							role="progressbar" style="width: ${sessionScope.hpb1}%"
							aria-valuenow="${sessionScope.hpb1}" aria-valuemin="0"
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
							role="progressbar" style="width: ${sessionScope.hpb2}%"
							aria-valuenow="${sessionScope.hpb2}" aria-valuemin="0"
							aria-valuemax="100"></div>
					</div>
				</div>
			</div>
		</div>

		<div class="row" id="mid">
			<div id="divconsole">
				<div class="scroll" id="console">${sessionScope.message}</div>
			</div>

			<div id="field">
				<div class="row" id="hfield">
					<div class="col-2" id="left">
						<div class="row" id="tleft"></div>
						<div class="row" id="hcard">
							<div class="col-8">
								<div id="card">
									<input type="image" class="imgcard" idCard="1"  id="c1"
										src="img/card/cardAP.png"
										style="visibility: ${sessionScope.disc1}; cursor: url('${sessionScope.cursorch}'), not-allowed;">
									<div class="divname" style="visibility: ${sessionScope.disc1}">
										<p class="name">${sessionScope.namec1}</p>
									</div>
									<div class="divclass" style="visibility: ${sessionScope.disc1}">
										<p class="class">Carte C1: ${sessionScope.classc1}</p>
									</div>
									<div class="def" style="visibility: ${sessionScope.disc1}">
										<p class="stat">${sessionScope.defc1}</p>
									</div>
									<div class="atk" style="visibility: ${sessionScope.disc1}">
										<p class="stat">${sessionScope.atkc1}</p>
									</div>
									<div class="life" style="visibility: ${sessionScope.disc1}">
										<p class="stat">${sessionScope.hpc1}</p>
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
									<input type="image" class="imgcard" idCard="2"  id="c2"
										src="img/card/cardAB.png"
										style="visibility: ${sessionScope.disc2}; cursor: url('${sessionScope.cursorch}'), not-allowed;">
									<div class="divname" style="visibility: ${sessionScope.disc2}">
										<p class="name">${sessionScope.namec2}</p>
									</div>
									<div class="divclass" style="visibility: ${sessionScope.disc2}">
										<p class="class">Carte C2: ${sessionScope.classc2}</p>
									</div>
									<div class="def" style="visibility: ${sessionScope.disc2}">
										<p class="stat">${sessionScope.defc2}</p>
									</div>
									<div class="atk" style="visibility: ${sessionScope.disc2}">
										<p class="stat">${sessionScope.atkc2}</p>
									</div>
									<div class="life" style="visibility: ${sessionScope.disc2}">
										<p class="stat">${sessionScope.hpc2}</p>
									</div>
								</div>
							</div>
							<div class="col-5" id="mtcard"></div>
							<div class="col-2">
								<div id="card">
									<input type="image" class="imgcard"  idCard="4" id="c4"
										src="img/card/cardTG.png"
										style="visibility: ${sessionScope.disc4}; cursor: url('${sessionScope.cursorai}'), not-allowed;">
									<div class="divname" style="visibility: ${sessionScope.disc4}">
										<p class="name">${sessionScope.namec4}</p>
									</div>
									<div class="divclass" style="visibility: ${sessionScope.disc4}">
										<p class="class">Carte C4: ${sessionScope.classc4}</p>
									</div>
									<div class="def" style="visibility: ${sessionScope.disc4}">
										<p class="stat">${sessionScope.defc4}</p>
									</div>
									<div class="atk" style="visibility: ${sessionScope.disc4}">
										<p class="stat">${sessionScope.atkc4}</p>
									</div>
									<div class="life" style="visibility: ${sessionScope.disc4}">
										<p class="stat">${sessionScope.hpc4}</p>
									</div>
								</div>
							</div>
						</div>
						<div class="row" id="midField"></div>
						<div class="row" id="hcard">
							<div class="col-1" id="ltcard"></div>
							<div class="col-2">
								<div id="card">
									<input type="image" class="imgcard"  idCard="3" id="c3"
										src="img/card/cardAV.png"
										style="visibility: ${sessionScope.disc3}; cursor: url('${sessionScope.cursorch}'), not-allowed;">
									<div class="divname" style="visibility: ${sessionScope.disc3}">
										<p class="name">${sessionScope.namec3}</p>
									</div>
									<div class="divclass" style="visibility: ${sessionScope.disc3}">
										<p class="class">Carte C3: ${sessionScope.classc3}</p>
									</div>
									<div class="def" style="visibility: ${sessionScope.disc3}">
										<p class="stat">${sessionScope.defc3}</p>
									</div>
									<div class="atk" style="visibility: ${sessionScope.disc3}">
										<p class="stat">${sessionScope.atkc3}</p>
									</div>
									<div class="life" style="visibility: ${sessionScope.disc3}">
										<p class="stat">${sessionScope.hpc3}</p>
									</div>
								</div>
							</div>

							<div class="col-5" id="mtcard"></div>
							<div class="col-2">
								<div id="card">
									<input type="image" class="imgcard" idCard="5"  id="c5"
										src="img/card/cardJR.png"
										style="visibility: ${sessionScope.disc5}; cursor: url('${sessionScope.cursorai}'), not-allowed;">
									<div class="divname" style="visibility: ${sessionScope.disc5}">
										<p class="name">${sessionScope.namec5}</p>
									</div>
									<div class="divclass" style="visibility: ${sessionScope.disc5}">
										<p class="class">Carte C5: ${sessionScope.classc5}</p>
									</div>
									<div class="def" style="visibility: ${sessionScope.disc5}">
										<p class="stat">${sessionScope.defc5}</p>
									</div>
									<div class="atk" style="visibility: ${sessionScope.disc5}">
										<p class="stat">${sessionScope.atkc5}</p>
									</div>
									<div class="life" style="visibility: ${sessionScope.disc5}">
										<p class="stat">${sessionScope.hpc5}</p>
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
										style="visibility: ${sessionScope.disc6}; cursor: url('${sessionScope.cursorai}'), not-allowed;">
									<div class="divname" style="visibility: ${sessionScope.disc6}">
										<p class="name">${sessionScope.namec6}</p>
									</div>
									<div class="divclass" style="visibility: ${sessionScope.disc6}">
										<p class="class">Carte C6: ${sessionScope.classc6}</p>
									</div>
									<div class="def" style="visibility: ${sessionScope.disc6}">
										<p class="stat">${sessionScope.defc6}</p>
									</div>
									<div class="atk" style="visibility: ${sessionScope.disc6}">
										<p class="stat">${sessionScope.atkc6}</p>
									</div>
									<div class="life" style="visibility: ${sessionScope.disc6}">
										<p class="stat">${sessionScope.hpc6}</p>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="row" id="bottom">
			<div class="col-5">
				<audio id="sDef" src="sound/def.mp3"></audio>
				<audio id="sAtt" src="sound/att.mp3"></audio>
			</div>
			<div class="col-2">
				<img src="img/field/exit.png" id="exit">
			</div>
		</div>


	</form>
</body>

<script type="text/javascript">
	
	
		var c=document.getElementById("c");
		var c1=document.getElementById("c1");
		var c2=document.getElementById("c2");
		var c3=document.getElementById("c3");
		var c4=document.getElementById("c4");
		var c5=document.getElementById("c5");
		var c6=document.getElementById("c6");
				
		c1.onmouseover=c1OnClick;
		function c1OnClick(){soundDef();c.value="1";}
		c2.onmouseover=c2OnClick;
		function c2OnClick(){soundDef();c.value="2";}
		c3.onmouseover=c3OnClick;
		function c3OnClick(){soundDef();c.value="3";}
		c4.onmouseover=c4OnClick;
		function c4OnClick(){soundAtt();c.value="4";}
		c5.onmouseover=c5OnClick;
		function c5OnClick(){soundAtt();c.value="5";}
		c6.onmouseover=c6OnClick;
		function c6OnClick(){soundAtt();c.value="6";}
		
		function soundDef(){
			var sound = document.getElementById("sDef");
			sDef.volume = 0.0;
            sound.play();
		}
		
		function soundAtt(){
			var sound = document.getElementById("sAtt");
			sAtt.volume = 0.0;
            sound.play();
		}
		
		
		
		
		
	</script>

</html>