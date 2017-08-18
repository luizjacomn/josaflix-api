package com.jjrjlj.josaflix.resource;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.jjrjlj.josaflix.model.Filme;
import com.jjrjlj.josaflix.service.FilmesService;

/**
 * 
 * @author luiz-jaco
 * @author josimar-junior
 *
 */

@RestController
@RequestMapping("/filmes")
public class FilmesResource {

	@Autowired
	private FilmesService filmesService;

	@Cacheable("listar")
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Filme>> listar() {
		return ResponseEntity.status(HttpStatus.OK).body(filmesService.listar());
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	@CacheEvict(value = {"listar", "buscar"}, allEntries = true)
	public ResponseEntity<Void> salvar(@RequestBody Filme filme) {
		filmesService.salvar(filme);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(filme.getId()).toUri();

		return ResponseEntity.created(uri).build();
	}

	@Cacheable("buscar")
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Filme> buscar(@PathVariable Long id) {
		Filme filme = filmesService.buscar(id);
		return ResponseEntity.status(HttpStatus.OK).body(filme);
	}

	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	@CacheEvict(value = {"listar", "buscar"}, allEntries = true)
	public ResponseEntity<Void> atualizar(@RequestBody Filme filme, @PathVariable("id") Long id) {
		filme.setId(id);
		filmesService.atualizar(filme);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping(value = "/{id}")
	@CacheEvict(value = {"listar", "buscar"}, allEntries = true)
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
		filmesService.deletar(id);
		return ResponseEntity.noContent().build();
	}
}
