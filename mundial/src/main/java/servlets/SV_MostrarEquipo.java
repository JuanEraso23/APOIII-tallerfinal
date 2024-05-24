package servlets;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import umariana.mundial.Equipo;
import umariana.mundial.GestionarMundial;
import umariana.mundial.Mundial;

// Anotación que define el URL de acceso para este servlet
@WebServlet("/mostrarEquipo.do")

// Configuración para manejar archivos subidos, con tamaños máximos especificados
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
                 maxFileSize = 1024 * 1024 * 10,      // 10MB
                 maxRequestSize = 1024 * 1024 * 50)   // 50MB

public class SV_MostrarEquipo extends HttpServlet {
    private GestionarMundial gestionar;

    @Override
    public void init() throws ServletException {
        super.init();
        // Inicialización de la instancia de GestionarMundial
        gestionar = new GestionarMundial();
        
        // Configurar el contexto del servlet en GestionarMundial
        gestionar.setServletContext(getServletContext());

        // Obtener la lista de equipos y almacenarla en el contexto del servlet
        ArrayList<Equipo> equipos = Mundial.getEquipos();
        getServletContext().setAttribute("equipos", equipos);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener la lista de equipos cada vez que se recibe una solicitud GET
        ArrayList<Equipo> equipos = Mundial.getEquipos();
        
        // Actualizar la lista de equipos en el contexto del servlet
        getServletContext().setAttribute("equipos", equipos); 
        
        // Reenviar la solicitud a la página JSP mostrarEquipos.jsp
        request.getRequestDispatcher("mostrarEquipos.jsp").forward(request, response);
    }
}
