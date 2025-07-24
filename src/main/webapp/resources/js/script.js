// resources/js/chat.js
document.addEventListener('DOMContentLoaded', function() {
    const chatContainer = document.getElementById('chat-container');
    const messageInput = document.getElementById('message-input');
    const sendButton = document.getElementById('send-button');
    
    // Configurar WebSocket
    const protocol = window.location.protocol === 'https:' ? 'wss://' : 'ws://';
    const socket = new WebSocket(protocol + window.location.host + '/whatsapp/chat');
    
    socket.onmessage = function(event) {
        const message = JSON.parse(event.data);
        appendMessage(message);
    };
    
    sendButton.addEventListener('click', function() {
        const messageText = messageInput.value.trim();
        if (messageText) {
            const message = {
                text: messageText,
                timestamp: new Date().toISOString(),
                sender: 'user'
            };
            
            socket.send(JSON.stringify(message));
            appendMessage(message);
            messageInput.value = '';
        }
    });
    
    function appendMessage(message) {
        const messageElement = document.createElement('div');
        messageElement.className = 'message ' + message.sender;
        
        const textElement = document.createElement('p');
        textElement.textContent = message.text;
        
        const timeElement = document.createElement('span');
        timeElement.className = 'time';
        timeElement.textContent = new Date(message.timestamp).toLocaleTimeString();
        
        messageElement.appendChild(textElement);
        messageElement.appendChild(timeElement);
        chatContainer.appendChild(messageElement);
        chatContainer.scrollTop = chatContainer.scrollHeight;
    }
});