package com.jjrjlj.josaflix.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jjrjlj.josaflix.exception.FilmeNaoEncontradoException;
import com.jjrjlj.josaflix.model.Filme;
import com.jjrjlj.josaflix.repository.FilmesDao;

/**
 * 
 * @author josimar-junior
 *
 */

@Service
public class FilmesService {

	@Autowired
	private FilmesDao filmeDAO;

	public void salvar(Filme filme) {
		filmeDAO.save(filme);
	}

	public Filme atualizar(Filme filme) {
		verificarExistencia(filme.getId());
		return filmeDAO.save(filme);
	}

	public List<Filme> listar() {
		return filmeDAO.findAll();
	}

	public Filme buscar(Long id) {
		Filme filme = filmeDAO.findOne(id);
		if (filme == null)
			throw new FilmeNaoEncontradoException("Filme não encontrado");

		return filme;
	}

	public void deletar(Long id) {
		verificarExistencia(id);
		filmeDAO.delete(id);
	}

	private void verificarExistencia(Long id) {
		buscar(id);
	}
}
