package org.wahlzeit.model;


public class Card
{
    private String name;
    private String rulesText;
    private String manacost;
    private Integer power;
    private Integer toughness;
    private Integer loyalty;
    private CardType type;
    
    protected Card(String name, CardType type, String rulesText, String manacost)
    {
        this.name = name;
        this.type = type;
        this.rulesText = rulesText;
        this.manacost = manacost;
    }

    protected Card(String name, CardType type, String rulesText, String manacost, Integer loyalty)
    {
        this(name, type, rulesText, manacost);
        this.loyalty = loyalty;
    }

    protected Card(String name, CardType type, String rulesText, String manacost, Integer power, Integer toughness)
    {
        this(name, type, rulesText, manacost);
        this.power = power;
        this.toughness = toughness;
    }
}

