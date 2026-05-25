package com.arthur;

import com.arthur.notifier.WhatsAppNotifier;
import com.arthur.service.DashSkinsService;
import com.arthur.service.SkinCheckerService;

public class App {

    public static void main(String[] args) {

        DashSkinsService dashSkinsService = new DashSkinsService();
        WhatsAppNotifier notifier = new WhatsAppNotifier();

        SkinCheckerService checker = new SkinCheckerService(
                dashSkinsService,
                notifier,
                "karambit",
                2500,
                3000
        );

        checker.verificar();
    }
}