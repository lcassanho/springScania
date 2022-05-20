package br.com.odd.springscania.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.odd.springscania.dto.PerguntaDto;
import br.com.odd.springscania.model.Pergunta;
import br.com.odd.springscania.repositories.PerguntaRepository;

@Controller
public class PerguntasController {

	@Autowired
	private PerguntaRepository perguntaRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping("/perguntas")
	public ModelAndView index() {
		ModelAndView modelView = new ModelAndView("perguntas/index");
		
		List<Pergunta> listarPerguntas = perguntaRepository.findAll();
		modelView.addObject("listarPerguntas", listarPerguntas);
		
		return modelView;
	}
	
	@GetMapping("/perguntas/create")
	public ModelAndView create(PerguntaDto pergunta) {
		ModelAndView modelView = new ModelAndView("perguntas/create");

		return modelView;
	}
	
	@PostMapping("perguntas")
	public ModelAndView salvar(@Valid PerguntaDto pergunta, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return new ModelAndView("perguntas/create");
		}

		Pergunta perguntaEntity = modelMapper.map(pergunta, Pergunta.class);
		perguntaRepository.save(perguntaEntity);
		return new ModelAndView("redirect:/perguntas");
	}
	
	@GetMapping("/perguntas/{id}/read")
	public ModelAndView read(@PathVariable Long id) {
		Optional<Pergunta> optionalPergunta = perguntaRepository.findById(id);

		if (optionalPergunta.isPresent()) {
			Pergunta perguntaGet = optionalPergunta.get();
			ModelAndView modelView = new ModelAndView("perguntas/read");
			modelView.addObject("pergunta", perguntaGet);
			return modelView;
		}
		System.out.println("Pergunta não encontrada!");
		return new ModelAndView("redirect:/perguntas");
	}
	
	@GetMapping("/perguntas/{id}/update")
	public ModelAndView edit(@PathVariable Long id, PerguntaDto request) {
		Optional<Pergunta> optionalPergunta = perguntaRepository.findById(id);

		if (optionalPergunta.isPresent()) {
			Pergunta perguntaGet = optionalPergunta.get();
			request.fromPergunta(perguntaGet);
			ModelAndView model = new ModelAndView("perguntas/update");
			model.addObject("perguntaId", perguntaGet.getId());
			return model;
		}
		System.out.println("Pergunta não encontrada!");
		return new ModelAndView("redirect:/perguntas");
	}
	
	@PostMapping("/perguntas/{id}/read")
	public ModelAndView update(@PathVariable Long id, @Valid PerguntaDto request, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			ModelAndView model = new ModelAndView("perguntas/update");
			model.addObject("perguntaId", id);
			return model;
		}
		
		Optional<Pergunta> optionalPergunta = perguntaRepository.findById(id);
		
		if(optionalPergunta.isPresent()){
			Pergunta pergunta = modelMapper.map(request, Pergunta.class);
			pergunta.setId(id);
			perguntaRepository.save(pergunta);
			return new ModelAndView("redirect:/perguntas/"+ pergunta.getId().toString() +"/read");
		}
		
		return new ModelAndView("redirect:/perguntas");
	}
	
	@GetMapping("/perguntas/{id}/delete")
	public ModelAndView delete(@PathVariable Long id) {
		ModelAndView model = new ModelAndView("redirect:/perguntas");
		
		this.perguntaRepository.deleteById(id);
		model.addObject("mensagem", "Usuario removido com sucesso!");
		return model;
	}

}
