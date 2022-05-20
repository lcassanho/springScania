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
import br.com.odd.springscania.dto.UsuarioDto;
import br.com.odd.springscania.model.Usuario;
import br.com.odd.springscania.repositories.UsuarioRepository;

@Controller
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping("/usuarios")
	public ModelAndView index() {
		ModelAndView modelView = new ModelAndView("usuarios/index");
		
		List<Usuario> listarUsuarios = usuarioRepository.findAll();
		modelView.addObject("listarUsuarios", listarUsuarios);
		
		return modelView;
	}
	
	@GetMapping("/usuarios/create")
	public ModelAndView create(UsuarioDto usuario) {
		ModelAndView modelView = new ModelAndView("usuarios/create");

		return modelView;
	}
	
	@PostMapping("usuarios")
	public ModelAndView salvar(@Valid UsuarioDto usuario, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return new ModelAndView("usuarios/create");
		}

		Usuario usuarioEntity = modelMapper.map(usuario, Usuario.class);
		usuarioRepository.save(usuarioEntity);
		return new ModelAndView("redirect:/usuarios");
	}
	
	@GetMapping("/usuarios/{id}/read")
	public ModelAndView read(@PathVariable Long id) {
		Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);

		if (optionalUsuario.isPresent()) {
			Usuario usuarioGet = optionalUsuario.get();
			ModelAndView modelView = new ModelAndView("usuarios/read");
			modelView.addObject("usuario", usuarioGet);
			return modelView;
		}
		System.out.println("Usuario não encontrado!");
		return new ModelAndView("redirect:/usuarios");
	}
	
	@GetMapping("/usuarios/{id}/update")
	public ModelAndView edit(@PathVariable Long id, UsuarioDto request) {
		Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);

		if (optionalUsuario.isPresent()) {
			Usuario usuarioGet = optionalUsuario.get();
			request.fromProduto(usuarioGet);
			ModelAndView model = new ModelAndView("usuarios/update");
			model.addObject("usuarioId", usuarioGet.getId());
			return model;
		}
		System.out.println("Usuario não encontrado!");
		return new ModelAndView("redirect:/usuarios");
	}
	
	@PostMapping("/usuarios/{id}/read")
	public ModelAndView update(@PathVariable Long id, @Valid UsuarioDto request, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			ModelAndView model = new ModelAndView("usuarios/update");
			model.addObject("usuarioId", id);
			return model;
		}
		
		Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);
		
		if(optionalUsuario.isPresent()){
			Usuario usuario = modelMapper.map(request, Usuario.class);
			usuario.setId(id);
			usuarioRepository.save(usuario);
			return new ModelAndView("redirect:/usuarios/"+ usuario.getId().toString() +"/read");
		}
		
		return new ModelAndView("redirect:/usuarios");
	}
	
	@GetMapping("/usuarios/{id}/delete")
	public ModelAndView delete(@PathVariable Long id) {
		ModelAndView model = new ModelAndView("redirect:/usuarios");
		
		this.usuarioRepository.deleteById(id);
		model.addObject("mensagem", "Usuario removido com sucesso!");
		return model;
	}



}
