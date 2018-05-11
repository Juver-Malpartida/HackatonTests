package database;

import javax.persistence.*;

@Entity
public class Events_event {
	
	@Id
	private int Id;
	private String title;
	private String address;
	
	protected Events_event() {}
	
	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
}
