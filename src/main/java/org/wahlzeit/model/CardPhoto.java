package org.wahlzeit.model;

import java.sql.*;
import java.net.*;

import org.wahlzeit.services.*;
import org.wahlzeit.utils.*;

public class CardPhoto extends Photo
{
    protected String edition;
    protected Rarity rarity;
    protected Condition condition;
    protected Language language;
    protected CardType type;
    public CardPhoto() {
    }

    public CardPhoto(PhotoId myId) {
        super(myId);
    }
    public CardPhoto(ResultSet rset) throws SQLException {
    readFrom(rset);
    }


    public void readFrom(ResultSet rset) throws SQLException {
    super.readFrom(rset);
    edition = rset.getString("edition");
    rarity = Rarity.valueOf(rset.getString("rarity"));
    condition = Condition.valueOf(rset.getString("condition"));

    }

    public void writeOn(ResultSet rset) throws SQLException {
    super.writeOn(rset);
    rset.updateString("edition", edition);
    rset.updateString("rarity", rarity.toString());
    rset.updateString("condition", condition.toString());
    }
}