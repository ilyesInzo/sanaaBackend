package com.magma.sanaa.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "T_Permission")
public class Permission {

    @Id
    @Column(name = "Prm_Id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "Prm_Name")
    private String name;

    @Version
    private long version;

    @OneToMany(mappedBy = "permission")
    private List<RolePermission> rolePermissions;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public List<RolePermission> getRolePermissions() {
        return rolePermissions;
    }

    public void setRolePermissions(List<RolePermission> rolePermissions) {
        this.rolePermissions = rolePermissions;
    }
}
