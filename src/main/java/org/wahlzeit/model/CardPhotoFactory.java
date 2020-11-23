package org.wahlzeit.model;

import java.sql.*;

import org.wahlzeit.services.*;

/**
 * An Abstract Factory for creating photos and related objects.
 */
public class CardPhotoFactory extends PhotoFactory {
	
	/**
	 * Hidden singleton instance; needs to be initialized from the outside.
	 */
	private static CardPhotoFactory instance = null;
	
	/**
	 * Public singleton access method.
	 */
	public static synchronized CardPhotoFactory getInstance() {
		if (instance == null) {
			SysLog.logSysInfo("setting generic CardPhotoFactory");
			setInstance(new CardPhotoFactory());
		}
		
		return instance;
	}
	
	/**
	 * Method to set the singleton instance of PhotoFactory.
	 */
	protected static synchronized void setInstance(CardPhotoFactory photoFactory) {
		if (instance != null) {
			throw new IllegalStateException("attempt to initialize CardPhotoFactory twice");
		}
		
		instance = photoFactory;
	}
	
	/**
	 * Hidden singleton instance; needs to be initialized from the outside.
	 */
	public static void initialize() {
		getInstance(); // drops result due to getInstance() side-effects
	}
	
	/**
	 * 
	 */
	protected CardPhotoFactory() {
		// do nothing
	}

	/**
	 * @methodtype factory
	 */
	public CardPhoto createPhoto() {
		return new CardPhoto();
	}
	
	/**
	 * 
	 */
	public CardPhoto createPhoto(PhotoId id) {
		return new CardPhoto(id);
	}
	
	/**
	 * 
	 */
	public CardPhoto createPhoto(ResultSet rs) throws SQLException {
		return new CardPhoto(rs);
	}
}
