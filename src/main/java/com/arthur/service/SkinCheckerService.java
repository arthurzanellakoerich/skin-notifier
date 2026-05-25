package com.arthur.service;

import com.arthur.model.Skin;
import com.arthur.notifier.WhatsAppNotifier;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SkinCheckerService {

    private final DashSkinsService dashSkinsService;
    private final WhatsAppNotifier notifier;
    private final Set<String> skinsNotificadas = new HashSet<>();

    // Nome e faixa de preço que você quer monitorar
    private final String nomeBuscado;
    private final double precoMinimo;
    private final double precoMaximo;

    public SkinCheckerService(DashSkinsService dashSkinsService,
                              WhatsAppNotifier notifier,
                              String nomeBuscado,
                              double precoMinimo,
                              double precoMaximo) {
        this.dashSkinsService = dashSkinsService;
        this.notifier = notifier;
        this.nomeBuscado = nomeBuscado;
        this.precoMinimo = precoMinimo;
        this.precoMaximo = precoMaximo;
    }

    public void verificar() {

        System.out.println("🔍 Verificando skins...");

        List<Skin> resultado = dashSkinsService.buscarSkins(nomeBuscado, precoMinimo, precoMaximo);

        for (Skin skin : resultado) {
            if (!skinsNotificadas.contains(skin.getId())) {

                // Monta o slug da URL
                String slug = skin.getMarketHashName()
                        .toLowerCase()
                        .replace(" | ", "-")
                        .replace(" ", "-")
                        .replace("(", "")
                        .replace(")", "")
                        .replace("™", "")
                        .replace("★ ", "")
                        .replace("★", "");

                String link = "https://dashskins.com.br/item/" + slug + "/" + skin.getId();

                String mensagem = "🎮 " + skin.getName()
                        + " | R$ " + skin.getPrice()
                        + " | Desconto: " + skin.getDiscount() + "%"
                        + " | " + link;

                notifier.enviarMensagem(mensagem);
                skinsNotificadas.add(skin.getId());

                System.out.println("✅ Notificado: " + skin.getName());
            }
        }

        System.out.println("⏳ Aguardando próxima verificação...");
    }
}