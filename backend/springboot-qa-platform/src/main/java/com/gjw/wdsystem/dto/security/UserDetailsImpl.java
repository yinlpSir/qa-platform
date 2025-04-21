package com.gjw.wdsystem.dto.security;

import com.gjw.wdsystem.pojo.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class UserDetailsImpl extends User implements UserDetails {

    public UserDetailsImpl(User user){
        super(
                user.getId(),
                user.getHeadPortrait(),
                user.getUsername(),
                user.getPassword(),
                user.getPhoneNumber(),
                user.getIntroduce(),
                user.getGender(),
                user.getAge(),
                user.getRole(),
                user.getCreateTime()
        );
    }

    /**
     * 返回授予用户的权限。不能返回null。
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        System.out.println("getAuthorities");
        return null;
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public String getUsername() {
        return super.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    /**
     *
     * @return true if the user is not locked, false otherwise
     */
    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
