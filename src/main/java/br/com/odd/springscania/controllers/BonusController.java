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

import br.com.odd.springscania.dto.BonusDto;
import br.com.odd.springscania.model.Bonus;
import br.com.odd.springscania.repositories.BonusRepository;

@Controller
public class BonusController {
	
	@Autowired
	private BonusRepository bonusRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping("/bonus")
	public ModelAndView index() {
		ModelAndView modelView = new ModelAndView("bonus/index");
		
		List<Bonus> listarBonus = bonusRepository.findAll();
		modelView.addObject("listarBonus", listarBonus);
		
		return modelView;
	}
	
	@GetMapping("/bonus/create")
	public ModelAndView create(BonusDto bonus) {
		ModelAndView modelView = new ModelAndView("bonus/create");

		return modelView;
	}
	
	@PostMapping("bonus")
	public ModelAndView salvar(@Valid BonusDto bonus, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return new ModelAndView("bonus/create");
		}

		Bonus bonusEntity = modelMapper.map(bonus, Bonus.class);
		bonusRepository.save(bonusEntity);
		return new ModelAndView("redirect:/bonus");
	}
	
	@GetMapping("/bonus/{id}/read")
	public ModelAndView read(@PathVariable Long id) {
		Optional<Bonus> optionalBonus = bonusRepository.findById(id);

		if (optionalBonus.isPresent()) {
			Bonus bonusGet = optionalBonus.get();
			ModelAndView modelView = new ModelAndView("bonus/read");
			modelView.addObject("bonus", bonusGet);
			return modelView;
		}
		System.out.println("Bonus não encontrado!");
		return new ModelAndView("redirect:/bonus");
	}
	
	@GetMapping("/bonus/{id}/update")
	public ModelAndView edit(@PathVariable Long id, BonusDto request) {
		Optional<Bonus> optionalBonus = bonusRepository.findById(id);

		if (optionalBonus.isPresent()) {
			Bonus bonusGet = optionalBonus.get();
			request.fromBonus(bonusGet);
			ModelAndView model = new ModelAndView("bonus/update");
			model.addObject("bonusId", bonusGet.getId());
			return model;
		}
		System.out.println("Bonus não encontrado!");
		return new ModelAndView("redirect:/bonus");
	}
	
	@PostMapping("/bonus/{id}/read")
	public ModelAndView update(@PathVariable Long id, @Valid BonusDto request, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			ModelAndView model = new ModelAndView("bonus/update");
			model.addObject("usuarioId", id);
			return model;
		}
		
		Optional<Bonus> optionalBonus = bonusRepository.findById(id);
		
		if(optionalBonus.isPresent()){
			Bonus bonus = modelMapper.map(request, Bonus.class);
			bonus.setId(id);
			bonusRepository.save(bonus);
			return new ModelAndView("redirect:/bonus/"+ bonus.getId().toString() +"/read");
		}
		
		return new ModelAndView("redirect:/bonus");
	}
	
	@GetMapping("/bonus/{id}/delete")
	public ModelAndView delete(@PathVariable Long id) {
		ModelAndView model = new ModelAndView("redirect:/bonus");
		
		this.bonusRepository.deleteById(id);
		model.addObject("mensagem", "Usuario removido com sucesso!");
		return model;
	}


}
