package com.fcs.admin.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;


/**
 * <p>
 * 
 * </p>
 *
 * @author Lucare
 * @since 2017-03-08
 */
@TableName("t_user_role")
public class UserRole extends Model<UserRole> {

    private static final long serialVersionUID = 1L;

	private Long id;
	private Long userid;
	private Long roleid;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public Long getRoleid() {
		return roleid;
	}

	public void setRoleid(Long roleid) {
		this.roleid = roleid;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
