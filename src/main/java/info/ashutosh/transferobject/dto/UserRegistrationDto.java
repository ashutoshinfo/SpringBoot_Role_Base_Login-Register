package info.ashutosh.transferobject.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class UserRegistrationDto {

	@NotEmpty(message = "First Name cannot be empty.")
	@Size(min = 2, max = 250)
	private String firstName;

	@NotEmpty(message = "Last Name cannot be empty.")
	@Size(min = 2, max = 250)
	private String lastName;

	@NotEmpty(message = "Username cannot be empty.")
	private String username;

	@NotEmpty(message = "Email cannot be empty.")
	private String email;

	@NotEmpty(message = "Password cannot be empty.")
	private String password;

	public UserRegistrationDto(String firstName, String lastName, String username, String email, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.email = email;
		this.password = password;
	}

	public UserRegistrationDto() {

	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
