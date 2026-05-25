package com.arthur.service;

import com.arthur.model.ApiResponse;
import com.arthur.model.Skin;
import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DashSkinsService {

    private final OkHttpClient client = new OkHttpClient();
    private final Gson gson = new Gson();
    private static final String BASE_URL = "https://dashskins.com.br/api/listing";

    public List<Skin> buscarSkins(String nomeBuscado, double precoMinimo, double precoMaximo) {

        List<Skin> todasSkins = new ArrayList<>();
        String cursor = "";
        boolean hasMore = true;

        while (hasMore) {

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }

            String url = BASE_URL + "?search=" + nomeBuscado
                    + "&price_min=" + precoMinimo
                    + "&price_max=" + precoMaximo
                    + "&limit=100&cursor=" + cursor;

            Request request = new Request.Builder()
                    .url(url)
                    .build();

            try (Response response = client.newCall(request).execute()) {

                if (!response.isSuccessful() || response.body() == null) {
                    System.out.println("Erro na requisição: " + response.code());
                    break;
                }

                String json = response.body().string();
                ApiResponse apiResponse = gson.fromJson(json, ApiResponse.class);

                if (apiResponse == null || apiResponse.getResults() == null) {
                    System.out.println("Resposta inválida da API.");
                    break;
                }

                todasSkins.addAll(apiResponse.getResults());

                hasMore = apiResponse.isHasMore();
                cursor = hasMore ? apiResponse.getNextCursor() : "";

                System.out.println("Página carregada... total até agora: " + todasSkins.size());

            } catch (IOException e) {
                System.out.println("Erro de conexão: " + e.getMessage());
                break;
            }
        }
        return todasSkins;
    }
}