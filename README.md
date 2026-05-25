# 🎮 Skin Notifier

Bot que monitora skins do CS2 no site [DashSkins](https://dashskins.com.br)
e envia notificações via WhatsApp quando encontra itens dentro do preço desejado.

## 📱 Como funciona

1. O bot consulta a API da DashSkins a cada intervalo configurado
2. Filtra as skins pelo nome e faixa de preço desejada
3. Quando encontra uma skin nova, envia uma mensagem no WhatsApp com:
   - Nome da skin
   - Preço
   - Desconto em relação ao preço Steam
   - Link direto para o item no site

## 🛠️ Tecnologias utilizadas

- **Java 25**
- **Maven** — gerenciamento de dependências
- **OkHttp** — requisições HTTP
- **Gson** — parsing de JSON
- **CallMeBot API** — envio de mensagens no WhatsApp

## ⚙️ Como configurar

### 1. Clone o repositório

    git clone https://github.com/arthurzanellakoerich/skin-notifier.git

### 2. Configure o CallMeBot

O CallMeBot é um serviço gratuito que permite receber mensagens no WhatsApp via API.
Para ativá-lo no seu número siga esses passos:

1. Salva o número **+34 694 26 48 06** na sua agenda como "CallMeBot"
2. Manda a mensagem abaixo via WhatsApp para esse número:

       I allow callmebot to send me messages

3. Aguarda a resposta — vai chegar uma mensagem com sua **APIKEY**
4. Copia essa APIKEY e coloca no `config.properties`

> ⚠️ Sem esse passo o bot não consegue te enviar notificações!

### 3. Crie o arquivo de configuração

Copia o arquivo de exemplo e preenche com seus dados:

    src/main/resources/config.properties.example
    → src/main/resources/config.properties

Conteúdo do arquivo:

    whatsapp.phone=SEU_NUMERO_COM_DDI
    whatsapp.apikey=SUA_APIKEY_AQUI

> Exemplo de número: 5547999999999 (55 = Brasil, 47 = DDD, 9XXXXXXXX = número)

### 4. Configure a skin desejada

Abre o `App.java` e ajusta os parâmetros:

    SkinCheckerService checker = new SkinCheckerService(
            dashSkinsService,
            notifier,
            "karambit",  // nome da skin
            1000.0,      // preço mínimo
            2000.0       // preço máximo
    );

### 5. Execute

Rode o `App.java` pelo IntelliJ ou gere o `.jar` pelo Maven.

## 📁 Estrutura do projeto

    src/main/java/com/arthur/
    ├── config/
    │   └── Config.java             — leitura do arquivo de configuração
    ├── model/
    │   ├── Skin.java               — modelo da skin
    │   └── ApiResponse.java        — modelo da resposta da API
    ├── service/
    │   ├── DashSkinsService.java   — consulta a API da DashSkins
    │   └── SkinCheckerService.java — lógica de verificação e notificação
    ├── notifier/
    │   └── WhatsAppNotifier.java   — envio de mensagens via CallMeBot
    └── App.java                    — ponto de entrada

## 💡 Melhorias futuras

- Monitorar múltiplas skins simultaneamente
- Suporte a notificações via Telegram
- Interface para configurar sem editar o código
