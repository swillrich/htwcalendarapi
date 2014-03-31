package de.svenwillrich.htwcalendarapi.api.data;

public class APIData {
	private String username;
	private String pw;
	private String hash;

	public String getHash() {
		return hash;
	}

	public String getPw() {
		return pw;
	}

	public String getUsername() {
		return username;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("--\nUser: " + username + "\n");
		s.append("PW: " + pw + "\n");
		s.append("Hash: " + hash + "\n--");
		s.append("\n");
		return s.toString();
	}
}
