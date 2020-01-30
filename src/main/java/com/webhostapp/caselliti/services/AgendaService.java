package com.webhostapp.caselliti.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.webhostapp.caselliti.entities.Agenda;
import com.webhostapp.caselliti.repositories.AgendaRepository;

@Service
@Transactional
public class AgendaService {

	@Autowired
	private AgendaRepository repo;
	
	public List<Agenda> listAll() {
		return repo.findAll();
	}
	
	public void save(Agenda agenda) {
		repo.save(agenda);
	}
	
	public Agenda get(long id) {
		return repo.findById(id).get();
	}
	
	public void delete(long id) {
		repo.deleteById(id);
	}
	
	/*
	 * metodo alternativo para procurar por ID
	 * 
	public Agenda findById(Long id) { 
		Optional<Agenda> obj =  repo.findById(id);
		return obj.get();
	}
	*/
	/*
	public List<Agenda> findAgendaByName(String nome){
		return repo.findAgendaByName(nome);
	}*/
}
