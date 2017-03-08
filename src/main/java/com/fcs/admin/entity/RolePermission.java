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
@TableName("t_role_permission")
public class RolePermission extends Model<RolePermission> {

    private static final long serialVersionUID = 1L;

	private Long id;
	private Long roleid;
	private Long perid;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRoleid() {
		return roleid;
	}

	public void setRoleid(Long roleid) {
		this.roleid = roleid;
	}

	public Long getPerid() {
		return perid;
	}

	public void setPerid(Long perid) {
		this.perid = perid;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
