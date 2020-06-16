package iducs.spring.blog202012703.domain;

public class Blogger {

	private long id;
	private String uId;
	private String uPw;
	private String uName;
	private String uEmail;
	
	public Blogger() {}
	
	public Blogger(String uId, String uPw, String uName, String uEmail) {
		this.uId = uId;
		this.uPw = uPw;
		this.uName = uName;
		this.uEmail = uEmail;
	}
	
	public long getId() {
		return this.id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getuId() {
		return uId;
	}
	public void setuId(String uId) {
		this.uId = uId;
	}
	public String getuPw() {
		return uPw;
	}
	public void setuPw(String uPw) {
		this.uPw = uPw;
	}
	public String getuName() {
		return uName;
	}
	public void setuName(String uName) {
		this.uName = uName;
	}
	public String getuEmail() {
		return uEmail;
	}
	public void setuEmail(String uEmail) {
		this.uEmail = uEmail;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		
		result = prime * result + ((uId == null) ? 0 : uId.hashCode());
		result = prime * result + ((uName == null) ? 0 : uName.hashCode());
		
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
		
		Blogger other = (Blogger) obj;
		if (id != other.id)
			return false;
		if (uId == null) {
			if (other.uId != null)
				return false;
		} else if (!uId.equals(other.uId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Blogger [_id=" + id + ", uId=" + uId + ", uPw=" + uPw + ", uName=" + uName + ", uEmail=" + uEmail
				+ "]";
	}
	
	
}
