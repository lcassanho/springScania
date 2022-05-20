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

import br.com.odd.springscania.dto.PesquisaDto;
import br.com.odd.springscania.model.Pesquisa;
import br.com.odd.springscania.repositories.PesquisaRepository;

@Controller
public class PesquisaController {
	
	@Autowired
	private PesquisaRepository pesquisaRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping("/pesquisas")
	public ModelAndView index() {
		ModelAndView modelView = new ModelAndView("pesquisas/index");
		
		List<Pesquisa> listarPesquisa = pesquisaRepository.findAll();
		modelView.addObject("listarPesquisa", listarPesquisa);
		
		return modelView;
	}
	
	@GetMapping("/pesquisas/create")
	public ModelAndView create(PesquisaDto pesquisa) {
		ModelAndView modelView = new ModelAndView("pesquisas/create");

		return modelView;
	}
	
	@PostMapping("pesquisas")
	public ModelAndView salvar(@Valid PesquisaDto pesquisa, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return new ModelAndView("pesquisas/create");
		}

		Pesquisa pesquisaEntity = modelMapper.map(pesquisa, Pesquisa.class);
		pesquisaRepository.save(pesquisaEntity);
		return new ModelAndView("redirect:/pesquisas");
	}
	
	@GetMapping("/pesquisas/{id}/read")
	public ModelAndView read(@PathVariable Long id) {
		Optional<Pesquisa> optionalPesquisa = pesquisaRepository.findById(id);

		if (optionalPesquisa.isPresent()) {
			Pesquisa pesquisaGet = optionalPesquisa.get();
			ModelAndView modelView = new ModelAndView("pesquisas/read");
			modelView.addObject("pesquisa", pesquisaGet);
			return modelView;
		}
		System.out.println("Pesquisa não encontrado!");
		return new ModelAndView("redirect:/pesquisas");
	}
	
	@GetMapping("/pesquisas/{id}/update")
	public ModelAndView edit(@PathVariable Long id, PesquisaDto request) {
		Optional<Pesquisa> optionalPesquisa = pesquisaRepository.findById(id);

		if (optionalPesquisa.isPresent()) {
			Pesquisa pesquisaGet = optionalPesquisa.get();
			request.fromPesquisa(pesquisaGet);
			ModelAndView model = new ModelAndView("pesquisas/update");
			model.addObject("pesquisaId", pesquisaGet.getId());
			return model;
		}
		System.out.println("Pesquisa não encontrado!");
		return new ModelAndView("redirect:/pesquisas");
	}
	
	@PostMapping("/pesquisas/{id}/read")
	public ModelAndView update(@PathVariable Long id, @Valid PesquisaDto request, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			ModelAndView model = new ModelAndView("pesquisas/update");
			model.addObject("pesquisaId", id);
			return model;
		}
		
		Optional<Pesquisa> optionalPesquisa = pesquisaRepository.findById(id);
		
		if(optionalPesquisa.isPresent()){
			Pesquisa pesquisa = modelMapper.map(request, Pesquisa.class);
			pesquisa.setId(id);
			pesquisaRepository.save(pesquisa);
			return new ModelAndView("redirect:/pesquisas/"+ pesquisa.getId().toString() +"/read");
		}
		
		return new ModelAndView("redirect:/pesquisas");
	}
	
	@GetMapping("/pesquisas/{id}/delete")
	public ModelAndView delete(@PathVariable Long id) {
		ModelAndView model = new ModelAndView("redirect:/pesquisas");
		
		this.pesquisaRepository.deleteById(id);
		model.addObject("mensagem", "Usuario removido com sucesso!");
		return model;
	}


}
