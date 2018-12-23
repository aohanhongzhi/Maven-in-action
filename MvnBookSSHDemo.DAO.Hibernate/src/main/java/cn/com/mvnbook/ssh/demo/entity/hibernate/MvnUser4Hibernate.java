package cn.com.mvnbook.ssh.demo.entity.hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import cn.com.mvnbook.ssh.demo.entity.MvnUser;
@Entity
@Table(catalog="mvn_db",name="mvn_user")
public class MvnUser4Hibernate extends MvnUser {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ur_id")
	public int getUrId() {
		// TODO Auto-generated method stub
		return super.getUrId();
	}

	@Column(name="ur_user_name",length=20,unique=true,nullable=false)
	public String getUrUserName() {
		// TODO Auto-generated method stub
		return super.getUrUserName();
	}

	@Column(name="ur_password",length=10,nullable=false)
	public String getUrPassword() {
		// TODO Auto-generated method stub
		return super.getUrPassword();
	}

	@Column(name="ur_age")
	public int getUrAge() {
		// TODO Auto-generated method stub
		return super.getUrAge();
	}

	@Column(name="ur_status",length=20,nullable=true)
	public String getUrStatus() {
		// TODO Auto-generated method stub
		return super.getUrStatus();
	}
}

