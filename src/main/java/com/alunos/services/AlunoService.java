package com.alunos.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.alunos.dto.AlunoDTO;
import com.alunos.model.Aluno;
import com.alunos.repository.AlunoRepository;
import com.alunos.util.exceptions.DataIntegrityException;
import com.alunos.util.exceptions.ObjectNotFoundException;



@Service
public class AlunoService {
	
	@Autowired
	private AlunoRepository repo;
	
	public Aluno find(Long id) {
		Aluno obj = repo.findOne(id);
		if (obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id
					+ ", Tipo: " + Aluno.class.getName());
		}
		return obj;
	}
	
	public Aluno insert(Aluno obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Aluno update(Aluno obj) {
		Aluno newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	public void delete(Long id) {
		find(id);
		try {
			repo.delete(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma Aluno que possui produtos");
		}
	}
	
	public List<Aluno> findAll() {
		return repo.findAll();
	}
	
	public Page<Aluno> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Aluno fromDTO(AlunoDTO objDto) {
		return new Aluno(objDto.getId(), objDto.getNome(), null, null, null);
	}

	private void updateData(Aluno newObj, Aluno obj) {
		newObj.setNome(obj.getNome());
	}

}
