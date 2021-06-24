package com.magma.sanaa.entities;

import com.magma.sanaa.entities.embeddedKeys.RolePermissionKey;

import javax.persistence.*;

@Entity
@Table(name = "T_Rel_Role_Permission")
public class RolePermission {
    @EmbeddedId
    private RolePermissionKey id;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("Rle_Id")
    @JoinColumn(name = "Rle_Id")
    private Role role;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("Prm_Id")
    @JoinColumn(name = "Prm_Id")
    private Permission permission;

    @Column(name = "Rel_create")
    private boolean create;

    @Column(name = "Rel_write")
    private boolean write;

    @Column(name = "Rel_read")
    private boolean read;

    @Column(name = "Rel_delete")
    private boolean delete;

    public RolePermissionKey getId() {
        return id;
    }

    public void setId(RolePermissionKey id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    public boolean isCreate() {
        return create;
    }

    public void setCreate(boolean create) {
        this.create = create;
    }

    public boolean isWrite() {
        return write;
    }

    public void setWrite(boolean write) {
        this.write = write;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public boolean isDelete() {
        return delete;
    }

    public void setDelete(boolean delete) {
        this.delete = delete;
    }
}
