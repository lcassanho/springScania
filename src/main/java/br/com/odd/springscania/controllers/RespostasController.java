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

import br.com.odd.springscania.dto.RespostaDto;
import br.com.odd.springscania.model.Resposta;
import br.com.odd.springscania.repositories.RespostaRepository;

@Controller
public class RespostasController {
	
	@Autowired
	private RespostaRepository respostaRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping("/respostas")
	public ModelAndView index() {
		ModelAndView modelView = new ModelAndView("respostas/index");
		
		List<Resposta> listarRespostas = respostaRepository.findAll();
		modelView.addObject("listarRespostas", listarRespostas);
		
		return modelView;
	}
	
	@GetMapping("/respostas/create")
	public ModelAndView create(RespostaDto resposta) {
		ModelAndView modelView = new ModelAndView("respostas/create");

		return modelView;
	}
	
	@PostMapping("respostas")
	public ModelAndView salvar(@Valid RespostaDto resposta, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return new ModelAndView("respostas/create");
		}

		Resposta respostaEntity = modelMapper.map(resposta, Resposta.class);
		respostaRepository.save(respostaEntity);
		return new ModelAndView("redirect:/respostas");
	}
	
	@GetMapping("/respostas/{id}/read")
	public ModelAndView read(@PathVariable Long id) {
		Optional<Resposta> optionalResposta = respostaRepository.findById(id);

		if (optionalResposta.isPresent()) {
			Resposta respostaGet = optionalResposta.get();
			ModelAndView modelView = new ModelAndView("respostas/read");
			modelView.addObject("resposta", respostaGet);
			return modelView;
		}
		System.out.println("Resposta não encontrado!");
		return new ModelAndView("redirect:/respostas");
	}
	
	@GetMapping("/respostas/{id}/update")
	public ModelAndView edit(@PathVariable Long id, RespostaDto request) {
		Optional<Resposta> optionalResposta = respostaRepository.findById(id);

		if (optionalResposta.isPresent()) {
			Resposta respostaGet = optionalResposta.get();
			request.fromResposta(respostaGet);
			ModelAndView model = new ModelAndView("respostas/update");
			model.addObject("respostaId", respostaGet.getId());
			return model;
		}
		System.out.println("Resposta não encontrado!");
		return new ModelAndView("redirect:/respostas");
	}
	
	@PostMapping("/respostas/{id}/read")
	public ModelAndView update(@PathVariable Long id, @Valid RespostaDto request, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			ModelAndView model = new ModelAndView("respostas/update");
			model.addObject("respostaId", id);
			return model;
		}
		
		Optional<Resposta> optionalResposta = respostaRepository.findById(id);
		
		if(optionalResposta.isPresent()){
			Resposta resposta = modelMapper.map(request, Resposta.class);
			resposta.setId(id);
			respostaRepository.save(resposta);
			return new ModelAndView("redirect:/respostas/"+ resposta.getId().toString() +"/read");
		}
		
		return new ModelAndView("redirect:/respostas");
	}
	
	@GetMapping("/respostas/{id}/delete")
	public ModelAndView delete(@PathVariable Long id) {
		ModelAndView model = new ModelAndView("redirect:/respostas");
		
		this.respostaRepository.deleteById(id);
		model.addObject("mensagem", "Usuario removido com sucesso!");
		return model;
	}


}
