package com.kandinsky.objects;

import java.io.Serializable;

public class FTPEntry implements Serializable {

	private static final long serialVersionUID = -8213344594828361628L;

	private String name;
	private String server;
	private int port;
	private String username;
	private String password;

	public FTPEntry(String name, String server, int port, String username, String password) {
		this.name = name;
		this.server = server;
		setPort(port);
		this.username = username;
		this.password = password;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the server
	 */
	public String getServer() {
		return server;
	}

	/**
	 * @param server the server to set
	 */
	public void setServer(String server) {
		this.server = server;
	}

	/**
	 * @return the port
	 */
	public int getPort() {
		return port;
	}

	/**
	 * @param port the port to set
	 */
	public void setPort(int port) {

		if (port < 0 || port > 65535) {
			throw new IllegalArgumentException("Port number out of Range (0-65533). Is: " + port);
		}
		this.port = port;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "FTPEntry [name=" + name + ", server=" + server + ":" + port + ", username=" + username + "]";
	}

}
