/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inser.restful.spring.restful_crud_spring_peliculas.controller;

import static inser.restful.spring.restful_crud_spring_peliculas.controller.Film_controller.path;
import inser.restful.spring.restful_crud_spring_peliculas.model.Film;
import static inser.restful.spring.restful_crud_spring_peliculas.model.Film.SELECT_findAll_order;
import inser.restful.spring.restful_crud_spring_peliculas.model.LinkedList_envuelta;
import static inser.restful.spring.restful_crud_spring_peliculas.repository.PersistenceConfig.jdbc_contraseña_tex;
import static inser.restful.spring.restful_crud_spring_peliculas.repository.PersistenceConfig.jdbc_usuario_tex;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import static inser.restful.spring.restful_crud_spring_peliculas.model.Film.SELECT_findByTitle_order;
import inser.restful.spring.restful_crud_spring_peliculas.service.Film_service;

/**
 *
 * @author informatica
 */
@RestController
@RequestMapping(path) 
public class Film_controller {
    public static final String path_base = "/webresources"; 
    public static final String path = path_base + "/inser.restful.restful_crud.film"; 
    public Film_service film_service;
       
    public Film_controller(Film_service film_service) {
        this.film_service = film_service;
    }

    /**
     * POST: Los campos de texto no se deben enviar en el Path debido a los caracteres especiales que pueden contener
     * Si la petición no tiene los datos requeridos (required) devuelve un error 405, 400, 4xx
     * @param headers
     * @param entity 
     */
    @PostMapping()
    @Transactional
    public void create(@RequestHeader(required = true) Map<String, String> headers
            , @RequestBody(required = true) Film entity) {
        String texto;     
        texto = headers.get(jdbc_usuario_tex);
        film_service.propiedades_mapa.put(jdbc_usuario_tex, texto);
        texto = headers.get(jdbc_contraseña_tex);
        film_service.propiedades_mapa.put(jdbc_contraseña_tex, texto);
        film_service.create(entity);
    }
    /**
     * Si la petición no tiene los datos requeridos (required) devuelve un error 405, 400, 4xx
     * @param headers
     * @param entity 
     */
    @PutMapping()
    @Transactional
    public void edit(@RequestHeader(required = true) Map<String, String> headers
            , @RequestBody(required = true) Film entity) {
        String texto;     
        texto = headers.get(jdbc_usuario_tex);
        film_service.propiedades_mapa.put(jdbc_usuario_tex, texto);
        texto = headers.get(jdbc_contraseña_tex);
        film_service.propiedades_mapa.put(jdbc_contraseña_tex, texto);
        film_service.edit(entity);
    }
    /**
     * Si la petición no tiene los datos requeridos (required) devuelve un error 405, 400, 4xx
     * @param headers
     * @param id 
     */
    @DeleteMapping("/{id}")
    @Transactional
    public void remove(@RequestHeader(required = true) Map<String, String> headers
            , @PathVariable("id") String id) {
        String texto;     
        texto = headers.get(jdbc_usuario_tex);
        film_service.propiedades_mapa.put(jdbc_usuario_tex, texto);
        texto = headers.get(jdbc_contraseña_tex);
        film_service.propiedades_mapa.put(jdbc_contraseña_tex, texto);
        film_service.remove(id);
    }
    /**
     * Si la petición no tiene los datos requeridos (required) devuelve un error 405, 400, 4xx
     * @param headers
     * @param id
     * @return 
     */
    @GetMapping("/{id}")
    @ResponseBody  // devuelve JSON
    public Film find(@RequestHeader(required = true) Map<String, String> headers
            , @PathVariable("id") String id) {
        String texto;     
        texto = headers.get(jdbc_usuario_tex);
        film_service.propiedades_mapa.put(jdbc_usuario_tex, texto);
        texto = headers.get(jdbc_contraseña_tex);
        film_service.propiedades_mapa.put(jdbc_contraseña_tex, texto);
        Film film = film_service.find(id);
        return film;
    }
    /**
     * Si la petición no tiene los datos requeridos (required) devuelve un error 405, 400, 4xx
     * @param headers
     * @return 
     */
    @GetMapping()
    @ResponseBody  // devuelve JSON
    public LinkedList_envuelta<Film> findAll_envuelta(@RequestHeader(required = true) Map<String, String> headers) {
        LinkedList_envuelta<Film> linkedList_envuelta = new LinkedList_envuelta();
        String texto;     
        texto = headers.get(jdbc_usuario_tex);
        film_service.propiedades_mapa.put(jdbc_usuario_tex, texto);
        texto = headers.get(jdbc_contraseña_tex);
        film_service.propiedades_mapa.put(jdbc_contraseña_tex, texto);
        List<Film> films_lista = new LinkedList<>(film_service.findAll());
        linkedList_envuelta.lista = new LinkedList(films_lista);
        return linkedList_envuelta;
    }
    /**
     * Si la petición no tiene los datos requeridos (required) devuelve un error 405, 400, 4xx
     * @param headers
     * @param from
     * @param to
     * @return 
     */
    @GetMapping("/{from}/{to}")
    @ResponseBody  // devuelve JSON
    public LinkedList_envuelta<Film> findRange(@RequestHeader(required = true) Map<String, String> headers
            , @PathVariable("from") Integer from
            , @PathVariable("to") Integer to) {
        LinkedList_envuelta<Film> linkedList_envuelta = new LinkedList_envuelta();
        String texto;     
        texto = headers.get(jdbc_usuario_tex);
        film_service.propiedades_mapa.put(jdbc_usuario_tex, texto);
        texto = headers.get(jdbc_contraseña_tex);
        film_service.propiedades_mapa.put(jdbc_contraseña_tex, texto);
        List<Film> films_lista = film_service.findRange(from, to);
        linkedList_envuelta.lista = new LinkedList(films_lista);
        return linkedList_envuelta;
    }
    /**
     * Si la petición no tiene los datos requeridos (required) devuelve un error 405, 400, 4xx
     * @param headers
     * @return 
     */
    @GetMapping("/count")
    public String countREST(@RequestHeader(required = true) Map<String, String> headers) {
        String texto;     
        texto = headers.get(jdbc_usuario_tex);
        film_service.propiedades_mapa.put(jdbc_usuario_tex, texto);
        texto = headers.get(jdbc_contraseña_tex);
        film_service.propiedades_mapa.put(jdbc_contraseña_tex, texto);
        return String.valueOf(film_service.countREST());
    }

    /**
     * POST: Los campos de texto no se deben enviar en el Path debido a los caracteres especiales que pueden contener
     * Si la petición no tiene los datos requeridos (required) devuelve un error 405, 400, 4xx
     * @param headers
     * @param from
     * @param to
     * @param descripcion
     * @return 
     */
    @PostMapping("/{from}/{to}")
    @ResponseBody  // devuelve JSON
    public LinkedList_envuelta<Film> findLike_descripcion(@RequestHeader(required = true) Map<String, String> headers
            , @PathVariable("from") Integer from
            , @PathVariable("to") Integer to
            , @RequestBody(required = true) String descripcion) {
        LinkedList_envuelta<Film> linkedList_envuelta = new LinkedList_envuelta();
        List<Film> films_lista;
        String texto;     
        texto = headers.get(jdbc_usuario_tex);
        film_service.propiedades_mapa.put(jdbc_usuario_tex, texto);
        texto = headers.get(jdbc_contraseña_tex);
        film_service.propiedades_mapa.put(jdbc_contraseña_tex, texto);
        TypedQuery<Film> typedQuery = film_service.getEntityManager().createNamedQuery("Film.findByTitle", Film.class);
        typedQuery = typedQuery.setParameter("description", descripcion);
        typedQuery = typedQuery.setMaxResults(to - from + 1);
        typedQuery = typedQuery.setFirstResult(from);
        films_lista = typedQuery.getResultList();
        linkedList_envuelta.lista = new LinkedList(films_lista);
        return linkedList_envuelta;
    }
    /**
     * Si la petición no tiene los datos requeridos (required) devuelve un error 405, 400, 4xx
     * @param headers
     * @param from
     * @param to
     * @param campo_ordenacion
     * @param asc
     * @return 
     */
    @GetMapping("/{from}/{to}/{campo_ordenacion}/{asc}")
    @ResponseBody  // devuelve JSON
    public LinkedList_envuelta<Film> find_orden(@RequestHeader(required = true) Map<String, String> headers
            , @PathVariable("from") Integer from
            , @PathVariable("to") Integer to
            , @PathVariable("campo_ordenacion") String campo_ordenacion
            , @PathVariable("asc") String asc) {
        List<Film> films_lista;
        LinkedList_envuelta<Film> linkedList_envuelta = new LinkedList_envuelta();
        String texto;     
        texto = headers.get(jdbc_usuario_tex);
        film_service.propiedades_mapa.put(jdbc_usuario_tex, texto);
        texto = headers.get(jdbc_contraseña_tex);
        film_service.propiedades_mapa.put(jdbc_contraseña_tex, texto);
        String consulta = SELECT_findAll_order
                + campo_ordenacion;
        if (asc.toLowerCase().equals("desc")) {
            consulta = consulta + " DESC";
        }
        TypedQuery<Film> typedQuery = film_service.getEntityManager().createQuery(consulta, Film.class);
        typedQuery = typedQuery.setMaxResults(to - from + 1);
        typedQuery = typedQuery.setFirstResult(from);
        films_lista = typedQuery.getResultList();
        linkedList_envuelta.lista = new LinkedList(films_lista);
        return linkedList_envuelta;
    }
    /**
     * POST: Los campos de texto no se deben enviar en el Path debido a los caracteres especiales que pueden contener
     * Si la petición no tiene los datos requeridos (required) devuelve un error 405, 400, 4xx
     * @param from
     * @param to
     * @param descripcion
     * @param campo_ordenacion
     * @param asc
     * @return 
     */
    @PostMapping("/{from}/{to}/{campo_ordenacion}/{asc}")
    @ResponseBody  // devuelve JSON
    public LinkedList_envuelta<Film> findLike_descripcion_orden(@RequestHeader(required = true) Map<String, String> headers
            , @PathVariable("from") Integer from
            , @PathVariable("to") Integer to
            , @RequestBody(required = true) String descripcion
            , @PathVariable("campo_ordenacion") String campo_ordenacion
            , @PathVariable("asc") String asc) {
        LinkedList_envuelta<Film> linkedList_envuelta = new LinkedList_envuelta();
        List<Film> films_lista;
        String texto;     
        texto = headers.get(jdbc_usuario_tex);
        film_service.propiedades_mapa.put(jdbc_usuario_tex, texto);
        texto = headers.get(jdbc_contraseña_tex);
        film_service.propiedades_mapa.put(jdbc_contraseña_tex, texto);
        String consulta = SELECT_findByTitle_order
                + campo_ordenacion;
        if (asc.toLowerCase().equals("desc")) {
            consulta = consulta + " DESC";
        }
        TypedQuery<Film> typedQuery = film_service.getEntityManager().createQuery(consulta, Film.class);
        typedQuery = typedQuery.setParameter("descripcion", descripcion);
        typedQuery = typedQuery.setMaxResults(to - from + 1);
        typedQuery = typedQuery.setFirstResult(from);
        films_lista = typedQuery.getResultList();
        linkedList_envuelta.lista = new LinkedList(films_lista);
        return linkedList_envuelta;
    }
    
}
