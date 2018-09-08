package org.aivan.cloud1.objekti.controllers;

import java.util.List;

import org.aivan.cloud1.objekti.model.Firma;
import org.aivan.cloud1.objekti.services.FirmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/firma")
public class FirmaController {

  @Autowired
  FirmaService firmaService;
  
  @RequestMapping("/all")
  @PreAuthorize("#oauth2.hasScope('web_scope') and hasAuthority('ADMIN')")
  public List<Firma> getAll() {
    return firmaService.getAll();
  }
}
