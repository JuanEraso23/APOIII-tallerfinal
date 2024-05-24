package servlets;

import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import umariana.mundial.Equipo;
import umariana.mundial.GestionarMundial;
import umariana.mundial.Mundial;

@WebListener
public class MyServletContextListener implements ServletContextListener {
    
    // Método llamado cuando el contexto del servlet es inicializado.
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        // Inicialización del contexto aquí
        ServletContext servletContext = servletContextEvent.getServletContext();
        
        // Crear una instancia de GestionarMundial y establecer el contexto del servlet
        GestionarMundial gestionar = new GestionarMundial();
        gestionar.setServletContext(servletContext);
        
        // Obtener la lista de equipos del Mundial y almacenarla en el contexto del servlet
        ArrayList<Equipo> equipos = Mundial.getEquipos();
        servletContext.setAttribute("equipos", equipos);
    }
    
    // Método llamado cuando el contexto del servlet es destruido.
    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        // Código de limpieza si es necesario
    }
}
