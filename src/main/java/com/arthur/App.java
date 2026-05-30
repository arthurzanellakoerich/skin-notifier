package com.arthur;

import com.arthur.model.SkinAlerta;
import com.arthur.notifier.WhatsAppNotifier;
import com.arthur.service.DashSkinsService;
import com.arthur.service.SkinCheckerService;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class App {
    public static void main(String[] args) {

        DashSkinsService dashSkinsService = new DashSkinsService();
        WhatsAppNotifier notifier = new WhatsAppNotifier();

        List<SkinAlerta> alertas = List.of(
                new SkinAlerta("awp", 1000.0, 1200.0),
                new SkinAlerta("butterfly", 2500.0, 3500.0)
        );

        SkinCheckerService checker = new SkinCheckerService(
                dashSkinsService,
                notifier,
                alertas
        );

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(checker::verificar, 0, 1, TimeUnit.HOURS);

        System.out.println("Bot iniciado! Monitorando " + alertas.size() + " skins...");
    }
}