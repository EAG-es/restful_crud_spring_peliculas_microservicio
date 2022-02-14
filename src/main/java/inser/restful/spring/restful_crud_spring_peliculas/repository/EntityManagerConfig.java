/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inser.restful.spring.restful_crud_spring_peliculas.repository;

import static inser.restful.spring.restful_crud_spring_peliculas.repository.DataSourceConfig.spring_datasource_password_tex;
import static inser.restful.spring.restful_crud_spring_peliculas.repository.DataSourceConfig.spring_datasource_username_tex;
import static inser.restful.spring.restful_crud_spring_peliculas.repository.PersistenceConfig.jdbc_contraseña_tex;
import static inser.restful.spring.restful_crud_spring_peliculas.repository.PersistenceConfig.jdbc_usuario_tex;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.springframework.core.env.Environment;

/**
 *
 * @author informatica
 */
public class EntityManagerConfig {
    public static Map<String, EntityManager> entityManager_map = new HashMap();
    public static Map<String, String> propiedades_por_defecto_map = new HashMap();
    
    public static EntityManager getEntityManager(String unitName
            , String usuario
            , String contraseña
            , Map<String, String> propiedades_map
            , String [] error) {
        EntityManagerFactory entityManagerFactory;
        EntityManager entityManager;
        String clave = unitName
            + "-"
            + usuario
            + "-" 
            + contraseña;
        entityManager = entityManager_map.get(clave);
        if (entityManager == null) {
            propiedades_map.put(jdbc_usuario_tex, usuario);
            propiedades_map.put(jdbc_contraseña_tex, contraseña);
            entityManagerFactory = Persistence.createEntityManagerFactory(unitName, propiedades_map);
//            entityManagerFactory = Persistence.createEntityManagerFactory(unitName);
            entityManager = entityManagerFactory.createEntityManager();
            if (entityManager != null) {
                entityManager_map.put(clave, entityManager);
            }
        }
        return entityManager;
    }
    /**
     * Crea un nuevo entityManager si es necesario
     * @param entityManager
     * @param environment
     * @param usuario
     * @param contraseña
     * @param propiedades_map
     * @param error En la posición 0; mensaje de error, si lo hay.
     * @return un nuevo EntityManager, null si hay error o no hay cambios en el EntityManager
     */
    public static EntityManager getEntityManager(EntityManager entityManager
            , Environment environment
            , String usuario
            , String contraseña
            , Map<String, String> propiedades_map
            , String [] error) {
        EntityManager encontrado_EntityManager = null;
        EntityManager resultado_EntityManager = null;
        EntityManagerFactory entityManagerFactory;
        String clave;
        try {
            clave = "EntityManager->";
            if (entityManager_map.isEmpty()) {
                String usuario_por_defecto;
                String contraseña_por_defecto;
                String clave_por_defecto;
                usuario_por_defecto = environment.getProperty(spring_datasource_username_tex);
                contraseña_por_defecto = environment.getProperty(spring_datasource_password_tex);
//                usuario_por_defecto = propiedades_por_defecto_map.get(spring_datasource_username_tex);
//                contraseña_por_defecto = propiedades_por_defecto_map.get(spring_datasource_password_tex);
                clave_por_defecto = clave
                    + "-"
                    + usuario_por_defecto
                    + "-" 
                    + contraseña_por_defecto;
                entityManager_map.put(clave_por_defecto, entityManager);
            }
            clave = clave
                + "-"
                + usuario
                + "-" 
                + contraseña;
            encontrado_EntityManager = entityManager_map.get(clave);
            if (encontrado_EntityManager == null) {
                propiedades_map.put(jdbc_usuario_tex, usuario);
                propiedades_map.put(jdbc_contraseña_tex, contraseña);
                entityManagerFactory = entityManager.getEntityManagerFactory();
                resultado_EntityManager = entityManagerFactory.createEntityManager(propiedades_map);
                if (resultado_EntityManager != null) {
                    entityManager_map.put(clave, resultado_EntityManager);
                }                
            } else {
                if (encontrado_EntityManager != entityManager) {
                    // Hay que cambiar de EntityManager
                    resultado_EntityManager = encontrado_EntityManager;
                }
            }
        } catch (Exception e) {
            error[0] = e.getMessage();
            if (error[0] == null) {
                error[0] = "";
            }
            error[0] = "Error obeniendo un nuevo gestor de entidades. " 
                    + error[0];
            resultado_EntityManager = null;
        }
        return resultado_EntityManager;
    }   
    
}
