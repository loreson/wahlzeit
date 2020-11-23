/*
 * Copyright (c) 2006-2009 by Dirk Riehle, http://dirkriehle.com
 *
 * This file is part of the Wahlzeit photo rating application.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public
 * License along with this program. If not, see
 * <http://www.gnu.org/licenses/>.
 */

package org.wahlzeit.model;

import java.io.*;
import java.sql.*;
import java.util.*;

import org.wahlzeit.main.*;
import org.wahlzeit.services.*;

/**
 * A photo manager provides access to and manages photos.
 */
public class CardPhotoManager extends PhotoManager {
	
	/**
	 * 
	 */
	protected static final CardPhotoManager instance = new CardPhotoManager();

	/**
	 * In-memory cache for photos
	 */
	protected Map<PhotoId, CardPhoto> photoCache = new HashMap<PhotoId, CardPhoto>();
	
	/**
	 * 
	 */
	protected PhotoTagCollector photoTagCollector = null;
	
	

	/**
	 * 
	 */
	public CardPhotoManager() {
		photoTagCollector = CardPhotoFactory.getInstance().createPhotoTagCollector();
	}
	
	
    
	/**
	 * 
	 */
	public CardPhoto getPhotoFromId(PhotoId id) {
		if (id.isNullId()) {
			return null;
		}

		CardPhoto result = doGetPhotoFromId(id);
		
		if (result == null) {
			try {
				PreparedStatement stmt = getReadingStatement("SELECT * FROM photos WHERE id = ?");
				result = (CardPhoto) readObject(stmt, id.asInt());
			} catch (SQLException sex) {
				SysLog.logThrowable(sex);
			}
			if (result != null) {
				doAddPhoto(result);
			}
		}
		
		return result;
	}
		
	/**
	 * @methodtype get
	 * @methodproperties primitive
	 */
	protected CardPhoto doGetPhotoFromId(PhotoId id) {
		return photoCache.get(id);
	}
	
	/**
	 * 
	 */
	protected CardPhoto createObject(ResultSet rset) throws SQLException {
		return CardPhotoFactory.getInstance().createPhoto(rset);
	}
	
	/**
	 * @methodtype command
	 *
	 * Load all persisted photos. Executed when Wahlzeit is restarted.
	 */
	public void addPhoto(Photo photo) {
		PhotoId id = photo.getId();
		assertIsNewPhoto(id);
		doAddPhoto(photo);

		try {
			PreparedStatement stmt = getReadingStatement("INSERT INTO photos(id) VALUES(?)");
			createObject(photo, stmt, id.asInt());
			ServiceMain.getInstance().saveGlobals();
		} catch (SQLException sex) {
			SysLog.logThrowable(sex);
		}
	}
	

}