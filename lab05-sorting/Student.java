public class Student {

	//private variables
	private String name;
	private String address;
	private long campusPhone;
	private int suBox;
	private long homePhone;

	/**
	 * constructs a new student
	 * @post student is constructed
	 */
	public Student(String nam, String addr, long cPhone, int sUBox, long hPhone) {
		this.name = nam;
		this.address = addr;
		this.campusPhone = cPhone;
		this.suBox = sUBox;
		this.homePhone = hPhone;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		return this.address;
	}
	/**
	 * @return the phone campus phone number
	 */
	public long getCampusPhone() {
		return this.campusPhone;
	}
	/**
	 * @return the SU box number
	 */
	public int getSUBox() {
		return this.suBox;
	}
	/**
	 * @return the home phone number
	 */
	public long getHomePhone() {
		return this.homePhone;
	}


	/**
	 * method to show the atributes of the student
	 */
	public String toString() {
		return "Name: " + this.name + "\n" + "Address: " + this.address + "\n" + "Campus Phone: " + this.campusPhone + "\n" + "SU Box: " + this.suBox + "\n" + "Home Phone: " + this.homePhone + "\n";
	}

}
