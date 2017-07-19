/**
 * 	app.js
 */

window.onload = function(){
	console.log('HTML loaded');
	loadNavbar();
	loadDashboardView();
}

function loadNavbar(){
	//make an ajax request to load the navbar html
	
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			console.log('loading navbar code!');
			document.getElementById('navbar').innerHTML = xhr.responseText;
			document.getElementById('txView').addEventListener('click', loadWithdrawalDepositView, false);
			document.getElementById('dashboardView').addEventListener('click', loadDashboardView, false);
		}
	}
	xhr.open("GET", 'getNavbar', true); // true: asynchronous
	xhr.send();	
}

function loadDashboardView(){
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			console.log('loading dashboard code!');
			document.getElementById('view').innerHTML = xhr.responseText;
		}
	}
	xhr.open("GET", 'getDashboardView', true); // true: asynchronous
	xhr.send();	
}

function loadWithdrawalDepositView(){
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			console.log('loading withdrawal/deposit code!');
			document.getElementById('view').innerHTML = xhr.responseText;
			document.getElementById('txBtn').addEventListener("click", processTx, false);
		}
	}
	xhr.open("GET", 'getTxView', true); // true: asynchronous
	xhr.send();	
}

function processTx(){
	var amount = document.getElementById('amount').value;
	var txType = document.querySelector('input[name = "txType"]:checked').value;
	console.log('The amount to process ' + amount);
	console.log('The type of tx: ' + txType);
	
	//convert values into a single JavaScript Object
	var tx = {
			txAmount: amount,
			txType: txType
	}
	
	//convert JavaScript Object into JSON

	tx = JSON.stringify(tx);
	
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if(xhr.readyState == 4 && xhr.status ==200){
			console.log(xhr.responseText);
			
		}
	}
	xhr.open("POST", "ajaxProcessTx", true);
	console.log(tx);
	
	//set the header to tell the server you have data for it to process
	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhr.send(tx);//include your post data in the send()
}