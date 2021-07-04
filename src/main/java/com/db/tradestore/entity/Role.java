package com.db.tradestore.entity;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.jetbrains.annotations.NotNull;

@Entity
public class Role
{
   @Id
   @GeneratedValue
   private Long id;
   @NotNull
   private String name;
   @OneToMany(targetEntity = User.class, mappedBy = "role", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   private Set<User> users;

   public Role()
   {

   }

   public Role(@NotNull final String name)
   {
      this.name = name;
   }

   public void addUser(User user)
   {
      if (users == null)
      {
         users = new HashSet<>();
      }
      users.add(user);
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

   @Override
   public boolean equals(final Object o)
   {
      if (this == o)
         return true;
      if (!(o instanceof Role))
         return false;
      Role role = (Role) o;
      if (id == null && role.id == null)
      {
         return name.equals(role.getName());
      }
      return Objects.equals(id, role.id);
   }

   @Override
   public int hashCode()
   {
      if (id != null)
      {
         return id.hashCode();
      }
      return name.hashCode();
   }

   @Override
   public String toString()
   {
      return "Role{" +
            "id=" + id +
            ", name='" + name + '\'' +
            '}';
   }
}
