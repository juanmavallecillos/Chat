var ws;

function initiateChatSession() {
	console.log("In initiateChatSession");
	if ("WebSocket" in window) {
		//Defining the serverEnd point of the websocket connection. 
		//TODO: var chatEndpoint = ;
		var chatEndpoint = document.getElementById("channelId").value;
		// TODO add the channel to the WS URI
		var wsUri = "ws://localhost:8080/P2PChat/chat/" + chatEndpoint;
		// Opening a web socket	connection	
		ws = new WebSocket(wsUri);
		ws.onopen = function(evt) {onOpen(evt);};
		ws.onmessage = function(evt) {onMessage(evt);}; 
		ws.close = function(evt) { onClose(evt);};
		ws.onerror = function(evt) { onError(evt);};
	} else {
		// The browser doesn't support WebSocket
		alert("WebSocket NOT supported!");
	}
}
 
function onOpen(evt){
	console.log("On Open");
}

function onClose(evt){	
}

function onMessage(evt){
	console.log("In onMessage");
	var msg = evt.data;	
	if(msg == "refresh message"){
		document.getElementById("refresh").click();
	}
	else{
		document.getElementById("historyBox").value += "\n" + msg;
	}
}

/**
 * This function is called on a connection error.
 * @param evt the event that contains the error.
 */
function onError(evt){
	ws.close();
}

function sendChatMessage() {
	  document.getElementById("historyBox").value += "\n" + document.getElementById("userName").value + ": " + document.getElementById("textBox").value;
	  var msg = document.getElementById("userName").value + ": " + document.getElementById("textBox").value;
	  document.getElementById("textBox").value ="";
  //TODO: set msg variable to the string to be sent via websockets
  ws.send(msg);
}

window.onload = function(){
	console.log("onload");
	initiateChatSession();};
