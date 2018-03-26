package com.alunos.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alunos.dto.AlunoDTO;
import com.alunos.model.Aluno;
import com.alunos.services.AlunoService;

@RestController
@RequestMapping(value="/alunos")
public class AlunoResource {
	
	@Autowired
	private AlunoService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Aluno> find(@PathVariable Long id) {
		Aluno obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<AlunoDTO>> findAll() {
		List<Aluno> list = service.findAll();
		List<AlunoDTO> listDto = list.stream().map(obj -> new AlunoDTO(obj)).collect(Collectors.toList());  
		return ResponseEntity.ok().body(listDto);
	}
	
	@RequestMapping(value="/content", method=RequestMethod.GET)
	public ResponseEntity<Page<AlunoDTO>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="nome") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		Page<Aluno> list = service.findPage(page, linesPerPage, orderBy, direction);
		Page<AlunoDTO> listDto = list.map(obj -> new AlunoDTO(obj));  
		return ResponseEntity.ok().body(listDto);
	}
	
}
