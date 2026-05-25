package com.arthur.notifier;

import com.arthur.config.Config;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class WhatsAppNotifier {

    private final OkHttpClient client = new OkHttpClient();
    private final String phone;
    private final String apiKey;

    public WhatsAppNotifier() {
        Config config = new Config();
        this.phone = config.getPhone();
        this.apiKey = config.getApiKey();
    }

    public void enviarMensagem(String mensagem) {

        try {
            String mensagemFormatada = URLEncoder.encode(mensagem, StandardCharsets.UTF_8);

            String url = "https://api.callmebot.com/whatsapp.php?phone=" + phone
                    + "&text=" + mensagemFormatada
                    + "&apikey=" + apiKey;

            Request request = new Request.Builder()
                    .url(url)
                    .build();

            try (Response response = client.newCall(request).execute()) {
                if (response.isSuccessful()) {
                    System.out.println("Mensagem enviada com sucesso!");
                } else {
                    System.out.println("Erro ao enviar mensagem: " + response.code());
                }
            }

        } catch (IOException e) {
            System.out.println("Erro de conexão: " + e.getMessage());
        }
    }
}