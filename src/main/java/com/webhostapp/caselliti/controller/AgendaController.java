package com.webhostapp.caselliti.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.webhostapp.caselliti.entities.Agenda;
import com.webhostapp.caselliti.repositories.AgendaRepository;
import com.webhostapp.caselliti.services.AgendaService;



@Controller
public class AgendaController {
	
	@Autowired
	private AgendaService service; 
	
	@Autowired
	private AgendaRepository repository;
	
	//Metodo para abrir a home page da aplicação. A home page da aplicação já vem com uma lista
	@RequestMapping("/")
	public String index(Model model) {
		List<Agenda> listAgenda = service.listAll();
		model.addAttribute("listAgenda", listAgenda);
		
		return "index";
	}
	
	
	//Metodo para abrir a página de cadastro
	@RequestMapping("/new")
	public String paginaCadastro(Model model) {
		Agenda agenda = new Agenda();
		model.addAttribute("agenda", agenda);
		
		return "new_agenda";
	}
	
	
	//Metodo para salvar. Serve tanto para PUT quanto para POST
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveAgenda(@ModelAttribute("agenda") Agenda agenda) {
		service.save(agenda);
		
		return "redirect:/";
	}
	
	//abri a página de edição da agenda
	@RequestMapping("/edit/{id}")
	public ModelAndView paginaEdicao(@PathVariable(name = "id") int id) {
		ModelAndView mav = new ModelAndView("edit_agenda");
		Agenda agenda = service.get(id);
		mav.addObject("agenda", agenda);
		
		return mav;
	}
	
	
	//deleta os registro da agenda
	@RequestMapping("/delete/{id}")
	public String deletarRegistro(@PathVariable(name = "id") int id) {
		service.delete(id);
		return "redirect:/";		
	}
	
	//utilização direto na URl ou com POSTMAM 
	
	
	//metodo para retornar o json de todos os registros
	@RequestMapping("/json")
	public ResponseEntity<List<Agenda>> buscarTodos(Pageable pageable){ 
		List<Agenda> list = service.listAll();
		return ResponseEntity.ok().body(list);
	}
	
	//metodo para retornar o json do registro baseado em um ID
	@GetMapping(value = "/json/{id}")
	public ResponseEntity<Agenda> findById(@PathVariable Long id){
		Agenda obj = service.get(id);
		return ResponseEntity.ok().body(obj);
	}
	
	//@PostMapping("**/pesquisarAgenda")
	//public ModelAndView pesquisar(@RequestParam("nomepesquisa") String nomepesquisa) { 
	//ModelAndView modelAndView = new ModelAndView("index");
//modelAndView.addObject("ListAgenda", repository.findAgendaByName(nomepesquisa));
	//return modelAndView;
	//}
	
}
