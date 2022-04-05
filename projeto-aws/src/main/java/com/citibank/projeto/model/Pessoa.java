package com.citibank.projeto.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_pessoa")
public class Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name="nameOwner", length = 50, unique = true)
	private String nameOwner;
	
	@Column(name="agencyCode", length = 4)
	private String agencyCode;
	
	@Column(name="numberAccount", length = 8)
	private String numberAccount;
	
	@Column(name="digitVerification", length = 3)
	private String digitVerification;
	
	@Column(name="registerId", length = 20)
	private String registerId;

	public Pessoa() {
	}

	public Pessoa(Long id, String nameOwner, String agencyCode, String numberAccount, String digitVerification, String registerId) {
		this.id = id;
		this.nameOwner = nameOwner;
		this.agencyCode = agencyCode;
		this.numberAccount = numberAccount;
		this.digitVerification = digitVerification;
		this.registerId = registerId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNameOwner() {
		return nameOwner;
	}

	public void setNameOwner(String nameOwner) {
		this.nameOwner = nameOwner;
	}

	public String getAgencyCode() {
		return agencyCode;
	}

	public void setAgencyCode(String agencyCode) {
		this.agencyCode = agencyCode;
	}
	
	public String getNumberAccount() {
		return numberAccount;
	}

	public void setNumberAccount(String numberAccount) {
		this.numberAccount = numberAccount;
	}
		
	public String getDigitVerification() {
		return digitVerification;
	}

	public void setDigitVerification(String digitVerification) {
		this.digitVerification = digitVerification;
	}

	public String getRegisterId() {
		return registerId;
	}

	public void setRegisterId(String registerId) {
		this.registerId = registerId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
