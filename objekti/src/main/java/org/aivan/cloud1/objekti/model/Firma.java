package org.aivan.cloud1.objekti.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Firma {

  @Id
  private Long id;
  
  private String ime;
  
  private String adresa;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }
  
  public String getIme() {
    return ime;
  }

  public void setIme(String ime) {
    this.ime = ime;
  }

  public String getAdresa() {
    return adresa;
  }

  public void setAdresa(String adresa) {
    this.adresa = adresa;
  }
  
  
}
