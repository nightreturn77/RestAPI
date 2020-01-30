package com.webhostapp.caselliti.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.webhostapp.caselliti.entities.Agenda;

public interface AgendaRepository extends JpaRepository<Agenda, Long> {

	/*
	@Query("Select a from Agenda a where a.nome like %?1% ")
	List<Agenda> findAgendaByName(String nome);*/
}
