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

package org.wahlzeit.services;

import java.io.*;

import org.wahlzeit.utils.*;

/**
 * A set of utility functions to retrieve URLs and files.
 * Call setValue(key, value) to change default values, see below.
 * 
 * @author dirkriehle
 *
 */
public class SysConfig extends AbstractConfig {
	
	/**
	 * Key definitions
	 */
	public static final String HTTP_PORT = "HTTP_PORT";
	public static final String SITE_URL = "SITE_URL";
	
	public static final String SCRIPTS_DIR = "SCRIPTS_URL";
	public static final String STATIC_DIR = "STATIC_DIR";
	public static final String TEMPLATES_DIR = "TEMPLATES_DIR";

	public static final String PHOTOS_URL_PATH = "PHOTOS_URL_PATH";
	public static final String PHOTOS_URL = "PHOTOS_URL";
	public static final String PHOTOS_DIR = "PHOTOS_DIR";
	public static final String BACKUP_DIR = "BACKUP_DIR";
	
	public static final String TEMP_DIR = "TEMP_DIR";
	public static final String HEADING_IMAGE = "HEADING_IMAGE";
	public static final String LOGO_IMAGE = "LOGO_IMAGE";
	public static final String EMPTY_IMAGE = "EMPTY_IMAGE";
	
	public static final String DB_DRIVER = "DB_DRIVER";
	public static final String DB_CONNECTION = "DB_CONNECTION";
	public static final String DB_USER = "DB_USER";
	public static final String DB_PASSWORD = "DB_PASSWORD";
	
	/**
	 * 
	 */
	protected static SysConfig instance = null;
	
	/**
	 * 
	 */
	public static SysConfig getInstance() {
		if (instance == null) {
			SysLog.logInfo("setting generic SysConfig");
			setInstance(new SysConfig("localhost", "8585"));
		}
		return instance;
	}
	
	/**
	 * Method to set the singleton instance of SysConfig.
	 */
	public static synchronized void setInstance(SysConfig sysConfig) {
		if (instance != null) {
			throw new IllegalStateException("attempt to initalize SysConfig again");
		}
		
		instance = sysConfig;
	}
	
	/**
	 * Drop singleton instance to cope with repeated startup/shutdown scenarios
	 */
	public static synchronized void dropInstance() {
		instance = null;
		SysLog.logInfo("dropped current SysConfig");
	}
	
	/**
	 * 
	 */
	public SysConfig(String host) {
		this(host, "80");
	}
	
	/**
	 *
	 */
	public SysConfig(String host, String port) {
		// Web frontend access
		if (!StringUtil.isNullOrEmptyString(port) && !port.equals("80")) {
			doSetValue(SysConfig.HTTP_PORT, port);
			doSetValue(SysConfig.SITE_URL, "http://" + host + ":" + port + "/");
		} else {
			doSetValue(SysConfig.HTTP_PORT, "80");
			doSetValue(SysConfig.SITE_URL, "http://" + host + "/");
		}

		doSetValue(SysConfig.SCRIPTS_DIR, new ConfigDir("config" + File.separator + "scripts"));
		doSetValue(SysConfig.STATIC_DIR, new ConfigDir("config" + File.separator + "static"));
		doSetValue(SysConfig.TEMPLATES_DIR, new ConfigDir("config" + File.separator + "templates"));
		
		doSetValue(SysConfig.PHOTOS_URL_PATH, "data/photos/");
		doSetValue(SysConfig.PHOTOS_URL, doGetValueAsString(SysConfig.SITE_URL) + doGetValueAsString(SysConfig.PHOTOS_URL_PATH));

		// Local filesystem access
		doSetValue(SysConfig.PHOTOS_DIR, "data" + File.separator + "photos" + File.separator);
		doSetValue(SysConfig.BACKUP_DIR, "data" + File.separator + "backup" + File.separator);
		doSetValue(SysConfig.TEMP_DIR, "data" + File.separator + "temp" + File.separator);
		
		// Some file names
		doSetValue(SysConfig.HEADING_IMAGE, "heading.png");
		doSetValue(SysConfig.LOGO_IMAGE, "wahlzeit.png");
		doSetValue(SysConfig.EMPTY_IMAGE, "empty.png");
	
		// Database connection
		doSetValue(SysConfig.DB_DRIVER, "org.postgresql.Driver");
		doSetValue(SysConfig.DB_CONNECTION, "jdbc:postgresql://localhost:5432/wahlzeit");
		doSetValue(SysConfig.DB_USER, "wahlzeit");
		doSetValue(SysConfig.DB_PASSWORD, "wahlzeit");
	}
		
	/**
	 * 
	 */
	public static String getSiteUrlAsString() {
		return getInstance().getValueAsString(SysConfig.SITE_URL);
	}

	/**
	 * 
	 */
	public static String getLinkAsUrlString(String link) {
		return getSiteUrlAsString() + link + ".html";
	}
	
	/**
	 * 
	 */
	public static String getPhotosUrlAsString() {
		return getInstance().getValueAsString(SysConfig.PHOTOS_URL);
	}

	/**
	 * 
	 */
	public static String getHttpPortAsString() {
		return getInstance().getValueAsString(SysConfig.HTTP_PORT);
	}
	/**
	 * 
	 */
	public static int getHttpPortAsInt() {
		return Integer.parseInt(getHttpPortAsString());
	}

	/**
	 * 
	 */
	public static String getPhotosDirAsString() {
		return getInstance().getValueAsString(SysConfig.PHOTOS_DIR);
	}

	/**
	 * 
	 * @return the photos URL path
	 */
	public static String getPhotosUrlPathAsString() {
		return getInstance().getValueAsString(SysConfig.PHOTOS_URL_PATH);
	}

	/**
	 *
	 */
	public static String getBackupDirAsString() {
		return getInstance().getValueAsString(SysConfig.BACKUP_DIR);
	}

	/**
	 * 
	 */
	public static String getTempDirAsString() {
		return getInstance().getValueAsString(SysConfig.TEMP_DIR);
	}

	/**
	 * 
	 */
	public static ConfigDir getScriptsDir() {
		return (ConfigDir) getInstance().getValue(SysConfig.SCRIPTS_DIR);
	}

	/**
	 * 
	 */
	public static ConfigDir getStaticDir() {
		return (ConfigDir) getInstance().getValue(SysConfig.STATIC_DIR);
	}

	/**
	 * 
	 */
	public static ConfigDir getTemplatesDir() {
		return (ConfigDir) getInstance().getValue(SysConfig.TEMPLATES_DIR);
	}

	/**
	 * 
	 */
	public static String getHeadingImageAsUrlString(Language l) {
		String sfn = l.asIsoCode() + File.separator + getInstance().getValueAsString(SysConfig.HEADING_IMAGE);
		String ffn = getStaticDir().getFullConfigFileUrl(sfn);
		return getSiteUrlAsString() + ffn;
	}
	
	/**
	 * 
	 */
	public static String getLogoImageAsUrlString(Language l) {
		String sfn = l.asIsoCode() + File.separator + getInstance().getValueAsString(SysConfig.LOGO_IMAGE);
		String ffn = getStaticDir().getFullConfigFileUrl(sfn);
		return getSiteUrlAsString() + ffn;
	}
	
	/**
	 * 
	 */
	public static String getEmptyImageAsUrlString(Language l) {
		String sfn = l.asIsoCode() + File.separator + getInstance().getValueAsString(SysConfig.EMPTY_IMAGE);
		String ffn = getStaticDir().getFullConfigFileUrl(sfn);
		return getSiteUrlAsString() + ffn;
	}

	/**
	 * 
	 */
	public static String getDbDriverAsString()	{
		return getInstance().getValueAsString(SysConfig.DB_DRIVER);
	}
	
	/**
	 * 
	 */
	public static String getDbConnectionAsString() {
		return getInstance().getValueAsString(SysConfig.DB_CONNECTION);
	}
	
	/**
	 * 
	 */
	public static String getDbUserAsString() {
		return getInstance().getValueAsString(SysConfig.DB_USER);
	}
	
	/**
	 * 
	 */
	public static String getDbPasswordAsString() {
		return getInstance().getValueAsString(SysConfig.DB_PASSWORD);
	}

}
