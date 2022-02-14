/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inser.restful.spring.restful_crud_spring_peliculas.repository;

import inser.restful.spring.restful_crud_spring_peliculas.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author informatica
 */
@Repository
public interface Film_repository extends JpaRepository<Film, String> {
    
}
