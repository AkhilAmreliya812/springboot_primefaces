package com.logicbig.example;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

//@NamedQueries({ @NamedQuery( name = "findByActive", query = "from Student s where s.active = :active and s.stdId = :stdId") })

//@NamedQueries({ @NamedQuery( name = "findByStatusActive", query = "from Student s where s.active = true"),
//				@NamedQuery( name = "findByStatusNotActive", query = "from Student s where s.active = false") })

@Entity
@Table(name = "std_basic_info")
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "std_id")
	@NotNull
	private long stdId;

	@Column(name = "std_name")
	@NotNull
	private String name;

	@Column(name = "std_city")
	@NotNull
	private String city;

	@Column(name = "view_mode")
	@NotNull
	private Boolean isViewMode = (Boolean.FALSE);

	@Column(name = "degree")
	private String degree;

	@Column(name = "is_active")
	private Boolean active;

	public Long getStdId() {
		return stdId;
	}

	public void setStdId(Long stdId) {
		this.stdId = stdId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Boolean getIsViewMode() {
		return isViewMode;
	}

	public void setIsViewMode(Boolean isViewMode) {
		this.isViewMode = isViewMode;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public void setStdId(long stdId) {
		this.stdId = stdId;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

}
