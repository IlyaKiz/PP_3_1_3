package ru.kata.spring.boot_security.demo.models;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User implements UserDetails {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column
   private String firstname;

   @Column
   private String lastname;

   @Column
   private String email;

   @Size(min=2, message = "Не меньше 5 знаков")
   private String username;

   @Size(min=2, message = "Не меньше 5 знаков")
   private String password;

   @Transient
   private String passwordConfirm;

   public String getUsername() {
      return username;
   }

   @ManyToMany(fetch = FetchType.EAGER)
   @JoinTable(name = "users_roles",
           joinColumns = {@JoinColumn(name = "user_id")},
           inverseJoinColumns = @JoinColumn(name = "role_id"))
   private Set<Role> roles;



   @Override
   public boolean isAccountNonExpired() {
      return true;
   }

   @Override
   public boolean isAccountNonLocked() {
      return true;
   }

   @Override
   public boolean isCredentialsNonExpired() {
      return true;
   }

   @Override
   public boolean isEnabled() {
      return true;
   }

   @Override
   public Collection<? extends GrantedAuthority> getAuthorities() {
      return getRoles();
   }

}
