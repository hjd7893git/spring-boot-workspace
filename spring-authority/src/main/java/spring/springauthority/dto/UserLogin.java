package spring.springauthority.dto;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import spring.springauthority.entity.UmsAdmin;
import spring.springauthority.entity.UmsResource;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class UserLogin implements UserDetails {

    private UmsAdmin umsAdmin;

    private List<UmsResource> umsResources;

    public UserLogin(UmsAdmin umsAdmin, List<UmsResource> umsResources) {
        this.umsAdmin = umsAdmin;
        this.umsResources = umsResources;
    }

    public UserLogin() {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return umsResources.stream().map(role -> new SimpleGrantedAuthority(role.getId() + ":" + role.getName())).collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}