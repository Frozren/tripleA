<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="icon" href="img/vignette.png">
		<title>tripleA</title>
		<script src="https://code.jquery.com/jquery-3.5.0.min.js" integrity="sha256-xNzN2a4ltkB44Mc/Jz3pT4iU1cmeR0FkXs4pru/JxaQ=" crossorigin="anonymous"></script>
	</head>

	<body>
		<div id="save">
			<div><a href="#"><img id="logoHome" src="img/logo.png"/></a></div>
			<div>
				<h1>SAUVEGARDES</h1>
			</div>
			<table>
				<tr>
					<td id="c1"><a href="#"><img id="imgPrev" src="img/prev.png"></a></td>
					<td id="show">
						<img src="img/backSave.jpg">
						<form id="myform" method="POST" action="save">
							<input type="hidden" value="" id="saveId" name="saveId"/>
							<a href="#"><div id="text">Partie de ${player[0].name}</div></a>
						</form>
						<div id="index">1/x</div>
						<div id="delete">Supprimer</div>
					</td>
					<td id="c2"><a href="#"><img id="imgNext" src="img/next.png"></a></td>
				</tr>
			</table>
		</div>
	</body>
	
	<script type="text/javascript">
		var text = document.getElementById("text");
		var index = document.getElementById("index");
		var saveId = document.getElementById("saveId");
		var suppr = document.getElementById("delete");
		var k = 0;
        var i = 0;
		var arr = new Array();
		var empty = ${emptyList};
	
		window.onclick = select;
		window.onload = smoothOpening;
		
		function smoothOpening() {
			if (empty){
				suppr.style.visibility = "hidden";
				text.innerHTML = "Aucune partie enregistrée !";
				index.innerHTML = "";
			} else {
				setInitialState();
				suppr.style.visibility = "visible";
				text.innerHTML = "Partie de " + arr[k];
				index.innerHTML = (k+1) + "/" + i;
			}
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
			if (!empty){
				if (e.target.id == "imgPrev"){
					k--;
				} else if (e.target.id == "imgNext"){
					k++;
				} else if (e.target.id == "logoHome"){
					window.location.href = "${pageContext.request.contextPath}/home";
				} else if (e.target.id == "text"){
					saveId.value = k;
					document.forms["myform"].submit();
				}
				
				if (k > (i-1)){
					k = 0;
				} else if (k < 0){
					k = (i-1);
				}
				text.innerHTML = "Partie de " + arr[k];
				index.innerHTML = (k+1) + "/" + i;
			} else {
				if (e.target.id == "logoHome" || e.target.id == "text"){
					window.location.href = "${pageContext.request.contextPath}/home";
				}
			}
		}

		function setInitialState() {
	        <c:forEach items="${player}" var="p">
	            arr[i] = '<c:out value='${p.name}'/>';     
	            i++;
	        </c:forEach>
	    }
		
		$("#delete").click(function() {
			if (confirm("Êtes-vous sûr de vouloir supprimer la partie de " + arr[k])){
				$.ajax({
					type : 'POST',
					data : {
						saveId : k,
						suppression : "true"
						},
					success : function(){
						arr.splice(k,1);
						i--;
						k = 0;
						
						if (i == 0){
							text.innerHTML = "Aucune partie enregistrée !";
							index.innerHTML = "";
							suppr.style.visibility = "hidden";
							empty = true;
						} else {
							suppr.style.visibility = "visible";
							text.innerHTML = "Partie de " + arr[k];
							index.innerHTML = (k+1) + "/" + i;
						}
					}
				});
			}
		});
	</script>
</html>