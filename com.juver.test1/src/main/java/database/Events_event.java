package database;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Events_event {
	
	@Id
	private int id;
	private String title;
	private String address;
	@Column(name="is_upcoming")
	private boolean isUpcoming;
	
	protected Events_event() {}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public boolean isUpcoming() {
		return isUpcoming;
	}

	public void setUpcoming(boolean isUpcoming) {
		this.isUpcoming = isUpcoming;
	}
	
}
