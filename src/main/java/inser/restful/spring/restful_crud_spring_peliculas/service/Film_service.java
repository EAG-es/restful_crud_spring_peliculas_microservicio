/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inser.restful.spring.restful_crud_spring_peliculas.service;


import inser.restful.spring.restful_crud_spring_peliculas.model.Film;
import static inser.restful.spring.restful_crud_spring_peliculas.model.Film.SELECT_findAll_order;
import inser.restful.spring.restful_crud_spring_peliculas.repository.AbstractFacade;
import inser.restful.spring.restful_crud_spring_peliculas.repository.EntityManagerConfig;
import inser.restful.spring.restful_crud_spring_peliculas.repository.Film_repository;
import static inser.restful.spring.restful_crud_spring_peliculas.repository.PersistenceConfig.jdbc_contraseña_tex;
import static inser.restful.spring.restful_crud_spring_peliculas.repository.PersistenceConfig.jdbc_usuario_tex;
import java.util.List;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import static inser.restful.spring.restful_crud_spring_peliculas.model.Film.SELECT_findByTitle_order;

/**
 *
 * @author informatica
 */
@Service
public class Film_service extends AbstractFacade<Film> {
    @PersistenceContext
    public EntityManager entityManager;
    @Autowired
    public Environment environment;
    
//    public Film_repository film_repository;
    public Map<String, String> propiedades_mapa = new HashMap();
    
    public Film_service(Film_repository film_repository) {
        super(Film.class);
//        this.film_repository = film_repository;
    }

    @Override
    public void create(Film entity) {
        super.create(entity);
    }

    @Override
    public void edit(Film entity) {
        super.edit(entity);
//        if (getEntityManager() != null) {
//            film_repository.saveAndFlush(entity);
//        }        
    }

    public void remove(String id) {
        Integer id_num = Integer.parseInt(id);
        Film film = super.find(id_num);
        super.remove(film);
//        if (getEntityManager() != null) {
//            Optional<Film> optional_film = film_repository.findById(id);
//            if (optional_film.isPresent()) {
//                Film film = optional_film.get();
//                film_repository.delete(film);
//            }
//        }        
    }

    public Film find(String id) {
        Integer id_num = Integer.parseInt(id);
        Film film = super.find(id_num);
        return film;
//        if (getEntityManager() != null) {
//            Optional<Film> optional_film = film_repository.findById(id);
//            Film film = optional_film.get();
//            return film;
//        } else {
//            return null;
//        }        
    }

    @Override
    public List<Film> findAll() {
        List<Film> films_lista = new LinkedList<>(super.findAll());
        return films_lista;
//        if (getEntityManager() != null) {
//            List<Film> films_lista = film_repository.findAll();
//            return films_lista;
//        } else {
//            return null;
//        }        
    }

    public List<Film> findRange(Integer from, Integer to) {
        List<Film> films_lista = super.findRange(new int[]{from, to});
        return films_lista;
    }

    public String countREST() {
        return String.valueOf(super.count());
//        if (getEntityManager() != null) {
//            return String.valueOf(film_repository.count());
//        } else {
//            return null;
//        }
    }

    /**
     * Obtiene un EntityManage para el usuario y contraseña del mapa, que sustituye al anterior
     * @return 
     */
    @Override
    public EntityManager getEntityManager() {
        boolean ret = true;
        EntityManager nuevo_EntityManager;
        String usuario;
        String contraseña;
        String [] error = { "" };
        usuario = propiedades_mapa.get(jdbc_usuario_tex);
        contraseña = propiedades_mapa.get(jdbc_contraseña_tex);
        nuevo_EntityManager = EntityManagerConfig.getEntityManager(entityManager
                , environment, usuario, contraseña, propiedades_mapa, error);
        if (nuevo_EntityManager != null) {
            // Cambiar el EntityManager
            entityManager = nuevo_EntityManager;
        } else {
            if (error[0].isBlank()) { 
                // No hay error. Sigue el EntityManager actual 
                nuevo_EntityManager = entityManager;
            }
        }
        return nuevo_EntityManager;
    }

    public List<Film> findLike_descripcion(Integer from, Integer to, String descripcion) {
        List<Film> films_lista;
        TypedQuery<Film> typedQuery = getEntityManager().createNamedQuery("Film.findByTitle", Film.class);
        typedQuery = typedQuery.setParameter("descripcion", descripcion);
        typedQuery = typedQuery.setMaxResults(to - from + 1);
        typedQuery = typedQuery.setFirstResult(from);
        films_lista = typedQuery.getResultList();
        return films_lista;
    }
    
    public List<Film> find_orden(Integer from
            , Integer to
            , String campo_ordenacion
            , String asc) {
        List<Film> films_lista;
        String consulta = SELECT_findAll_order
                + campo_ordenacion;
        if (asc.toLowerCase().equals("desc")) {
            consulta = consulta + " DESC";
        }
        TypedQuery<Film> typedQuery = getEntityManager().createQuery(consulta, Film.class);
        typedQuery = typedQuery.setMaxResults(to - from + 1);
        typedQuery = typedQuery.setFirstResult(from);
        films_lista = typedQuery.getResultList();
        return films_lista;
    }

    public List<Film> findLike_descripcion_orden(Integer from
            , Integer to
            , String descripcion
            , String campo_ordenacion
            , String asc) {
        List<Film> films_lista;
        String consulta = SELECT_findByTitle_order
                + campo_ordenacion;
        if (asc.toLowerCase().equals("desc")) {
            consulta = consulta + " DESC";
        }
        TypedQuery<Film> typedQuery = getEntityManager().createQuery(consulta, Film.class);
        typedQuery = typedQuery.setParameter("description", descripcion);
        typedQuery = typedQuery.setMaxResults(to - from + 1);
        typedQuery = typedQuery.setFirstResult(from);
        films_lista = typedQuery.getResultList();
        return films_lista;
    }
    
}
