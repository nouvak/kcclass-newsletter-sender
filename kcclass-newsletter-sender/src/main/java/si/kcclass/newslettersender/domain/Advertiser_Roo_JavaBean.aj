// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package si.kcclass.newslettersender.domain;

import java.util.Set;
import si.kcclass.newslettersender.domain.Advertiser;
import si.kcclass.newslettersender.domain.SecurityRole;

privileged aspect Advertiser_Roo_JavaBean {
    
    public String Advertiser.getUsername() {
        return this.username;
    }
    
    public void Advertiser.setUsername(String username) {
        this.username = username;
    }
    
    public String Advertiser.getPassword() {
        return this.password;
    }
    
    public void Advertiser.setPassword(String password) {
        this.password = password;
    }
    
    public Boolean Advertiser.getEnabled() {
        return this.enabled;
    }
    
    public void Advertiser.setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
    
    public String Advertiser.getEmail() {
        return this.email;
    }
    
    public void Advertiser.setEmail(String email) {
        this.email = email;
    }
    
    public Set<SecurityRole> Advertiser.getRoles() {
        return this.roles;
    }
    
    public void Advertiser.setRoles(Set<SecurityRole> roles) {
        this.roles = roles;
    }
    
}
