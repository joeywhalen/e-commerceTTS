package com.tts.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
// This is the user class that allows the user to be created.
public class User implements UserDetails {
  // @Transient makes it so those aren't persisted in the database, as they are hard coded.
  @Transient
  private boolean accountNonExpired = true;
  @Transient
  private boolean accountNonLocked = true;
  @Transient
  private boolean credentialsNonExpired = true;
  @Transient
  private boolean enabled = true;
  @Transient
  private Collection<GrantedAuthority> authorities = null;
  // Allows the Id to be auto generated
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "user_id")
  private Long id;

  //Variables
//  @Email(message = "Please provide a valid email")
//  @NotEmpty(message = "Please provide an email")
//  private String email;

  @NotEmpty(message = "Please provide a username")
  @Length(min = 3, message = "Your username must have at least 3 characters")
  @Length(max = 15, message = "Your username cannot have more than 15 characters")
  @Pattern(regexp = "[^\\s]+", message = "Your username cannot contain spaces")
  private String username;

  @Length(min = 5, message = "Your password must have at least 5 characters")
  @NotEmpty(message = "Please provide a password")
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private String password;

//  @NotEmpty(message = "Please provide your first name")
//  private String firstName;
//
//  @NotEmpty(message = "Please provide your last name")
//  private String lastName;
  private int active;
//
//  @CreationTimestamp
//  private Date createdAt;

  // Start of the sql queries to line the user_id to the roles and the following.
  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Role> roles;

  @ElementCollection
  private Map<Product, Integer> cart;

}
