<div id="hp1">
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
<div id="hp2">
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

<div id="field">
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
