package com.arthur.model;

import com.google.gson.annotations.SerializedName;

public class Skin {

    @SerializedName("_id")
    private String id;

    private String name;

    @SerializedName("market_hash_name")
    private String marketHashName;

    private double price;
    private double steamPrice;
    private double discount;

    @SerializedName("wear_data")
    private WearData wearData;

    private String rarity;

    @SerializedName("item_type")
    private String itemType;

    private String image;
    private boolean listed;
    private String inspectLink;

    // Classe interna para pegar o floatvalue do wear_data
    public static class WearData {
        private double floatvalue;

        public double getFloatvalue() {
            return floatvalue;
        }
    }

    public Skin() {
    }

    public Skin(String id, String name, String marketHashName, double price, double steamPrice,
                double discount, String rarity, String itemType, String image,
                boolean listed, String inspectLink) {
        this.id = id;
        this.name = name;
        this.marketHashName = marketHashName;
        this.price = price;
        this.steamPrice = steamPrice;
        this.discount = discount;
        this.rarity = rarity;
        this.itemType = itemType;
        this.image = image;
        this.listed = listed;
        this.inspectLink = inspectLink;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getMarketHashName() { return marketHashName; }
    public double getPrice() { return price; }
    public double getSteamPrice() { return steamPrice; }
    public double getDiscount() { return discount; }
    public String getRarity() { return rarity; }
    public String getItemType() { return itemType; }
    public String getImage() { return image; }
    public boolean isListed() { return listed; }
    public String getInspectLink() { return inspectLink; }

    public double getFloatValue() {
        return wearData != null ? wearData.getFloatvalue() : 0.0;
    }

    @Override
    public String toString() {
        return "Skin{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", steamPrice=" + steamPrice +
                ", discount=" + discount + "%" +
                ", rarity='" + rarity + '\'' +
                ", itemType='" + itemType + '\'' +
                ", floatValue=" + getFloatValue() +
                '}';
    }
}