package com.tts.ecommerce.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Transient;
import java.util.Collection;

public class UserDetails {
  @Transient
  private boolean accountNonExpired = true;
  @Transient
  private boolean accountNonLocked = true;
  @Transient
  private boolean credentialNonExpired = true;
  @Transient
  private boolean enabled = true;
  @Transient
  private Collection <GrantedAuthority> authorities = null;
}
