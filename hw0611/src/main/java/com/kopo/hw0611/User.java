package com.kopo.hw0611;

public class User {

    private String idx;
    private String id;
    private String pwd;
    private String name;
    private String userType;
    private String phone;
    private String address;
    private String created;
    private String lastUpdated;

    public String getIdx() {
        return idx;
    }
    
    public String getName() {
    	return name;
    }

    public String getId() {
        return id;
    }

    public String getPwd() {
        return pwd;
    }

    public String getUserType() {
        return userType;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getCreated() {
        return created;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

	public void setId(String id) {
		this.id = id;		
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setIdx(String idx) {
		this.idx = idx;
	}
}
