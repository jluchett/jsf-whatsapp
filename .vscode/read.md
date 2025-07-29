whatsapp/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── adur.transkal.frw.whatsapp/
│   │   │           ├── backing/        # Managed Beans (WhatsappChatBacking.java)
│   │   │           ├── beans/          # Modelos de datos (WhatsappMessage.java, WhatsappChat.java, WhatsappContact.java)
│   │   │           ├── services/       # Servicios (WhatsappApiService.java, WhatsappWebhook.java, WhatsappWebSocket.java)
│   │   │           └── util/           # Uitilitarios (ConfigUtil, etc.)
│   │   └── webapp/
│   │       ├── resources/
│   │       │   ├── css/                      # Estilos CSS (styles.css)
│   │       │   ├── js/                       # JavaScript (scripts.js)
│   │       │   ├── images/                   # Imágenes
│   │       │   └── WhatsappApi.properties    # Propiedades de configuración
│   │       ├── WEB-INF/
│   │       │   ├── faces-config.xml    # Configuración JSF
│   │       │   └── web.xml             # Descriptor de despliegue
│   │       ├── templates/              # Plantillas JSF
│   │       └── pages/                  # Páginas JSF (index.xhtml)
├── pom.xml                             # Configuración Maven
└── README.md

