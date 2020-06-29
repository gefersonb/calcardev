package com.pack.entity;

import javax.persistence.MappedSuperclass;

@SuppressWarnings("serial")
@MappedSuperclass
public class AbstractEntity implements BaseEntity {
	protected Long oid;

	public Long getOid() {
		return oid;
	}

	public void setOid(Long oid) {
		this.oid = oid;
	}
	

}
