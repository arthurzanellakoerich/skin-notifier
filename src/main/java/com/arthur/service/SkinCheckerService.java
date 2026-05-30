package com.arthur.service;

import com.arthur.model.Skin;
import com.arthur.model.SkinAlerta;
import com.arthur.notifier.WhatsAppNotifier;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SkinCheckerService {

    private final DashSkinsService dashSkinsService;
    private final WhatsAppNotifier notifier;
    private final Set<String> skinsNotificadas = new HashSet<>();
    private final List<SkinAlerta> alertas;

    public SkinCheckerService(DashSkinsService dashSkinsService,
                              WhatsAppNotifier notifier,
                              List<SkinAlerta> alertas) {
        this.dashSkinsService = dashSkinsService;
        this.notifier = notifier;
        this.alertas = alertas;
    }

    public void verificar() {

        System.out.println("Verificando skins...");

        for (SkinAlerta alerta : alertas) {

            List<Skin> resultado = dashSkinsService.buscarSkins(
                    alerta.getNome(),
                    alerta.getPrecoMinimo(),
                    alerta.getPrecoMaximo()
            );

            for (Skin skin : resultado) {
                if (!skinsNotificadas.contains(skin.getId())) {

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

                    System.out.println("Notificado: " + skin.getName());
                }
            }
        }

        System.out.println("Aguardando próxima verificação...");
    }
}