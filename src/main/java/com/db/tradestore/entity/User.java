package com.db.tradestore.entity;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.jetbrains.annotations.NotNull;

@Entity
public class User
{
   @Id
   @GeneratedValue
   private Long id;

   @NotNull
   private String name;
   @NotNull
   private String email;
   @NotNull
   private String password;
   @ManyToOne
   @JoinColumn(name = "role_id")
   private Role role;

   public User()
   {

   }

   public User(@NotNull final String name, @NotNull final String email, @NotNull final String password, final Role role)
   {
      this.name = name;
      this.email = email;
      this.password = password;
      this.role = role;
   }

   public Role getRole()
   {
      return role;
   }

   public void setRole(final Role role)
   {
      this.role = role;
   }

   public Long getId()
   {
      return id;
   }

   public void setId(final Long id)
   {
      this.id = id;
   }

   public @NotNull String getName()
   {
      return name;
   }

   public void setName(final @NotNull String name)
   {
      this.name = name;
   }

   public @NotNull String getEmail()
   {
      return email;
   }

   public void setEmail(final @NotNull String email)
   {
      this.email = email;
   }

   public @NotNull String getPassword()
   {
      return password;
   }

   public void setPassword(final @NotNull String password)
   {
      this.password = password;
   }

   @Override
   public boolean equals(final Object o)
   {
      if (this == o)
         return true;
      if (o == null || getClass() != o.getClass())
         return false;
      final User user = (User) o;
      if (id == null && user.id == null)
      {
         return getName().equals(user.getName()) && getEmail().equals(user.getEmail()) && getPassword().equals(user.getPassword());
      }
      return Objects.equals(id, user.id);
   }

   @Override
   public int hashCode()
   {
      if (id != null)
      {
         return id.hashCode();
      }

      return Objects.hash(getId(), getName(), getEmail(), getPassword());
   }

   @Override
   public String toString()
   {
      return "User{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", email='" + email + '\'' +
            ", password='" + password + '\'' +
            '}';
   }
}
