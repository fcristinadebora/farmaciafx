package model.classes;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Customer extends Person{

	private Date birthDate;
	private String cpf;
	private String address;
	
	
	public Customer() {
		
	}
	
	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String string) throws ParseException {
		if (string == null || string.equals(""))
			this.birthDate = null;
        Date date = null;
        try {
            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            date = (Date)formatter.parse(string);
            this.birthDate = date;
        } catch (ParseException e) {
        	this.birthDate = null;
            throw e;
        }
	}
	
	public void setBirthDate(Date date) throws ParseException {
		if (date == null)
			this.birthDate = null;
		
        this.birthDate = date;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
