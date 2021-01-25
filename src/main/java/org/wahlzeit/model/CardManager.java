package org.wahlzeit.model;
import java.util.HashMap;


class CardManager
{
    private HashMap<String, CardType> types = new HashMap<String, CardType>();
    private static CardManager instance;
    private CardManager()
    {

    }
    public static CardManager getInstance()
    {
        if (instance == null)
        {
            instance = new CardManager();
        }
        return instance;
    }

    public CardType createOrGetCardType(String typename)  throws InstantiationError
    {
        CardType type = new CardType(typename);
        if(types.containsKey(typename))
        {
            if(types.get(typename).getSuperType() != null)
            {
                throw new InstantiationError("Type exists but has a supertype");
            }
            return type;
        }
        types.put(typename, type);
        return type;
    }
    public CardType createOrGetCardType(String typename, String superTypeName) throws InstantiationError
    {
        if(types.containsKey(typename))
        {
            CardType type = types.get(typename);
            if(type.getSuperType().name == superTypeName )
            {
                return type;
            }
            else
            {
                throw new InstantiationError("Type exists but has wrong supertype");
            }
        }
        else
        {
            if(!types.containsKey(superTypeName))
            {
                throw new InstantiationError("Supertype does not Exist");
            }
            CardType superType = types.get(superTypeName);
            CardType type = new CardType(typename, superType);
            types.put(typename, type);
            return type;
        }
    }
    public Card CreateCard(String name, String type, String rulesText, String manacost) throws IllegalArgumentException
    {
        if(!types.containsKey(type))
        {
            throw new IllegalArgumentException("type does not Exist");
        }
        CardType ctype = types.get(type);
        return new Card(name, ctype, rulesText, manacost);
    }
    public Card CreateCard(String name, String type, String rulesText, String manacost, Integer loyalty) throws IllegalArgumentException
    {
        if(!types.containsKey(type))
        {
            throw new IllegalArgumentException("type does not Exist");
        }
        CardType ctype = types.get(type);
        return new Card(name, ctype, rulesText, manacost, loyalty);
    }

    public Card CreateCard(String name, String type, String rulesText, String manacost, Integer power, Integer toughness) throws IllegalArgumentException
    {
        if(!types.containsKey(type))
        {
            throw new IllegalArgumentException("type does not Exist");
        }
        CardType ctype = types.get(type);
        return new Card(name, ctype, rulesText, manacost, power, toughness);
    }
}