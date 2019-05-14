package com.example.demootuslombok.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
public class Cat {

  @Id
  @EqualsAndHashCode.Exclude
  @GeneratedValue
  Long id;

  @NonNull
  String name;

}
