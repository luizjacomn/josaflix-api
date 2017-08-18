package com.jjrjlj.josaflix.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jjrjlj.josaflix.model.Filme;

/**
 * 
 * @author luiz-jaco
 *
 */

@Repository
public interface FilmesDao extends JpaRepository<Filme, Long> {
	
}
