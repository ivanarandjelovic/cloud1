package org.aivan.cloud1.objekti.services;

import java.util.List;

import org.aivan.cloud1.objekti.model.Firma;
import org.aivan.cloud1.objekti.model.FirmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FirmaService {

  @Autowired
  FirmaRepository firmaRepository;
  
  public List<Firma> getAll() {
    return firmaRepository.findAll();
  }
}
