/*******************************************************************************
 * Copyright (C) 2014 Open University of The Netherlands
 * Author: Bernardo Tabuenca Archilla
 * Lifelong Learning Hub project 
 * 
 * This library is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package com.example.smarthome.fcube.config;



public class FeedbackCubeConfig {

	
	/**
	 * > GET / HTTP/1.1                      : Responds w/ "Hello from Arduino Server"
	 * > GET /ring/status/ HTTP/1.1          : Responds w/ "ON"/"OFF" for the power state of the strip
	 * > GET /ring/fade/ HTTP/1.1            : Responds w/ a JSON representation of 
	 *                                         the fading parameters -> {"n":x,"d":x}
	 * > GET /ring/color/ HTTP/1.1           : Responds w/ a JSON representation of 
	 *                                         the strip color -> {"r":x,"g":x,"b":x}
 	 * 
 	 *
	 * > PUT /ring/on/ HTTP/1.1              : Turns the LED strip on
	 * > PUT /ring/off/ HTTP/1.1             : Turns the LED strip off
	 * > PUT /ring/fade/ HTTP/1.1            : Color starts fading
	 *                                         The fading parameters (number, delay) are provided as a JSON object: {"n": x,"d": x}
	 * > PUT /ring/rainbow/ HTTP/1.1         : Starts a color rainbow
	 * > PUT /ring/rainbow/circle/ HTTP/1.1  : Starts a color rainbow cycle
	 * > PUT /ring/color/ HTTP/1.1           : Changes the color of the LED strip
	 *                                         The color values (red, green, blue) are provided as a JSON object: {"r": x,"g": x,"b": x}
	 * > PUT /speaker/beep/ HTTP/1.1         : Plays a beep
	 */
	
	private String CLASSNAME = this.getClass().getName();
	
	private String ip_address = "192.168.0.100";
	

	
	public static final String URL_PREFIX = "http://";


	private static FeedbackCubeConfig singleInstance;


	public static FeedbackCubeConfig getSingleInstance() {
		if (singleInstance == null) {
			synchronized (FeedbackCubeConfig.class) {
				if (singleInstance == null) {
					singleInstance = new FeedbackCubeConfig();
				}
			}
		}
		return singleInstance;
	}	
	

	public String getIp(){
		return ip_address;
	}



	
}
