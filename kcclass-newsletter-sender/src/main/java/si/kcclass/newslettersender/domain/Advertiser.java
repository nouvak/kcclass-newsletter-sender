package si.kcclass.newslettersender.domain;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class Advertiser {

    @NotNull
    @Size(min = 3, max = 50)
    @Column(unique=true)
    private String username;

    @Size(max = 100)
    private String password;
    
    @NotNull
    private Boolean enabled;
    
    @NotNull
    private String email;

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<SecurityRole> roles = new HashSet<SecurityRole>();
}
