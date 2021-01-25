package org.wahlzeit.model;


public class CardType
{
    private CardType superType = null;
    protected final String name;
    public CardType(String name)
    {
        this.name = name;
    }
    public CardType(String name, CardType superType)
    {
        this.name = name;
        this.superType = superType;
    }
    public CardType getSuperType()
    {
        return this.superType;
    }
    public boolean isSubtype(CardType type)
    {
        if(superType == null)
        {
            return false;
        }
        if(superType == type)
        {
            return true;
        }
        return superType.isSubtype(type);
    }
}