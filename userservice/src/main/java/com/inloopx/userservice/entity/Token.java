package com.inloopx.userservice.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "TOKENS")
public class Token extends BaseEntity{

  @Column(name = "ACCESS_TOKEN")
  @NotNull
  private String accessToken;

  @Column(name = "REFRESH_TOKEN")
  @NotNull
  private String refreshToken;

  @ManyToOne
  @JoinColumn(name = "USER_ID")
  private User user;

  @Column(name = "CREATED")
  @Temporal(value = TemporalType.TIMESTAMP)
  private LocalDateTime created;

  @Column(name = "UPDATED")
  private LocalDateTime updated;

  @PrePersist
  public void prePersist() {
    created = LocalDateTime.now();
    updated = created;
  }

  @PreUpdate
  public void preUpdate() {
    updated = LocalDateTime.now();
  }

  public LocalDateTime getCreated() {
    return created;
  }

  public void setCreated(LocalDateTime created) {
    this.created = created;
  }

  public LocalDateTime getUpdated() {
    return updated;
  }

  public void setUpdated(LocalDateTime updated) {
    this.updated = updated;
  }

  public String getAccessToken() {
    return accessToken;
  }

  public void setAccessToken(String accessToken) {
    this.accessToken = accessToken;
  }


  public String getRefreshToken() {
    return refreshToken;
  }

  public void setRefreshToken(String refreshToken) {
    this.refreshToken = refreshToken;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }
}
