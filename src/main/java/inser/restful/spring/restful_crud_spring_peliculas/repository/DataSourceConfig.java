/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inser.restful.spring.restful_crud_spring_peliculas.repository;

import org.springframework.context.annotation.Configuration;

/**
 *
 * @author informatica
 */
@Configuration
public class DataSourceConfig {
    public static final String spring_datasource_username_tex = "spring.datasource.username";
    public static final String spring_datasource_password_tex = "spring.datasource.password";
    public static final String spring_datasource_driverClassName_tex = "spring.datasource.driver-class-name";
    public static final String spring_datasource_url_tex = "spring.datasource.url";
        
//    @Autowired
//    public Environment environment;
    
//
//    /**
//     * Ejemplo de configuración de Spring DataSource. No es necesario, salvo que se quiera personalizar.
//     * @return DataSource que usará Spring 
//     */    
//    @Bean
//    public DataSource getDataSource() {
//        String usuario;
//        String contraseña;
//        usuario = environment.getProperty(spring_datasource_username_tex);
//        contraseña = environment.getProperty(spring_datasource_password_tex);
//        String driverClass = environment.getProperty(spring_datasource_driverClassName_tex);
//        String url = environment.getProperty(spring_datasource_url_tex);
//        EntityManagerConfig.propiedades_por_defecto_map.put(spring_datasource_username_tex, usuario);
//        EntityManagerConfig.propiedades_por_defecto_map.put(spring_datasource_password_tex, contraseña);
//        EntityManagerConfig.propiedades_por_defecto_map.put(spring_datasource_driverClassName_tex, driverClass);
//        EntityManagerConfig.propiedades_por_defecto_map.put(spring_datasource_url_tex, url);
//        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
//        dataSourceBuilder = dataSourceBuilder.username(usuario);
//        dataSourceBuilder = dataSourceBuilder.password(contraseña);
//        dataSourceBuilder = dataSourceBuilder.url(url);
//        dataSourceBuilder = dataSourceBuilder.driverClassName(driverClass);
//        DataSource dataSource = dataSourceBuilder.build();
//        return dataSource;
//   }
}
