// Función para inicializar el WebSocket
function initWebSocket() {
    const protocol = window.location.protocol === 'https:' ? 'wss://' : 'ws://';
    const socket = new WebSocket(protocol + window.location.host + '/whatsapp-web/chatSocket');
    
    socket.onmessage = function(event) {
        const data = JSON.parse(event.data);
        if (data.type === "new_message") {
            updateChatUI(data);
        }
    };
    
    return socket;
}

// Actualizar la interfaz con nuevos mensajes
function updateChatUI(messageData) {
    const chatMessages = document.getElementById('chatMessages');
    const isCurrentChat = document.getElementById('selectedChatId').value === messageData.chatId;
    
    if (isCurrentChat) {
        const messageDiv = document.createElement('div');
        messageDiv.className = `message ${messageData.sender === 'user' ? 'user' : 'contact'}`;
        messageDiv.innerHTML = `
            <p>${messageData.text}</p>
            <span class="message-time">${messageData.time}</span>
        `;
        chatMessages.appendChild(messageDiv);
        chatMessages.scrollTop = chatMessages.scrollHeight;
    }
    
    // Actualizar el último mensaje en la lista de chats
    updateChatList(messageData.chatId, messageData.text);
}

function updateChatList(chatId, lastMessage) {
    const chatItems = document.querySelectorAll('.chat-item');
    chatItems.forEach(item => {
        if (item.getAttribute('data-chat-id') === chatId) {
            const lastMsgElement = item.querySelector('.chat-last-message');
            if (lastMsgElement) {
                lastMsgElement.textContent = lastMessage;
            }
        }
    });
}

// Inicialización cuando el DOM está listo
document.addEventListener('DOMContentLoaded', function() {
    initWebSocket();
    
    // Configurar el auto-scroll
    const chatMessages = document.getElementById('chatMessages');
    if (chatMessages) {
        chatMessages.scrollTop = chatMessages.scrollHeight;
    }
    
    // Manejar el envío con Enter
    const messageInput = document.getElementById('messageForm:messageInput');
    if (messageInput) {
        messageInput.addEventListener('keypress', function(e) {
            if (e.key === 'Enter') {
                e.preventDefault();
                document.getElementById('messageForm:sendButton').click();
            }
        });
    }
});