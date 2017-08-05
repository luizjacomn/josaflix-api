package com.jjrjlj.josaflix.resource;

import java.net.URI;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

@RestController
@RequestMapping("/filmes")
public class FilmesResource {

	@Autowired
	private FilmesService filmesService;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Filme>> listar() {
		CacheControl cacheControl = CacheControl.maxAge(30, TimeUnit.SECONDS);
		return ResponseEntity.status(HttpStatus.OK).cacheControl(cacheControl).body(filmesService.listar());
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> salvar(@RequestBody Filme filme) {
		filmesService.salvarOuAtualizar(filme);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(filme.getId()).toUri();

		return ResponseEntity.created(uri).build();
	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Filme> buscar(@PathVariable Long id) {
		CacheControl cacheControl = CacheControl.maxAge(30, TimeUnit.SECONDS);
		return ResponseEntity.status(HttpStatus.OK).cacheControl(cacheControl).body(filmesService.buscar(id));
	}

	@PutMapping
	public ResponseEntity<Void> atualizar(@RequestBody Filme filme) {
		filmesService.salvarOuAtualizar(filme);
		return ResponseEntity.noContent().build();
	}
}
