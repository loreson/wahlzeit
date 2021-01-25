package org.wahlzeit.model;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
public class CardTest
{
    @Test
    public void testisSubtype()
    {
        CardManager manager = CardManager.getInstance();
        CardType spell = manager.createOrGetCardType("Spell");
        CardType permanent = manager.createOrGetCardType("Permanent");
        CardType instant = manager.createOrGetCardType("Instant", "Spell");
        CardType sorcery = manager.createOrGetCardType("Sorcery", "Spell");
        CardType creature = manager.createOrGetCardType("Creature", "Permanent");
        CardType lhurgoyf = manager.createOrGetCardType("Lhurgoyf", "Creature");
        assertTrue(instant.isSubtype(spell));
        assertTrue(lhurgoyf.isSubtype(permanent));
        assertFalse(sorcery.isSubtype(instant));
    }
}
