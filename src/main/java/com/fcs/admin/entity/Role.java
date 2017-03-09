package com.fcs.admin.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
import java.util.List;
import java.util.Set;


/**
 * <p>
 * 
 * </p>
 *
 * @author Lucare
 * @since 2017-03-06
 */
@TableName("t_role")
public class Role extends Model<Role> {

    private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	private String description;
	private List<Permission> permissions;
	private Set<String> perNameSet;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}

	public Set<String> getPerNameSet() {
		return perNameSet;
	}

	public void setPerNameSet(Set<String> perNameSet) {
		this.perNameSet = perNameSet;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
