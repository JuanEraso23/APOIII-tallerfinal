package umariana.mundial;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;

public class GestionarMundial {
    
    private static List<Equipo> equipos = new ArrayList<>(); // Inicialización de la lista de equipos
    private static ServletContext servletContext; // Contexto del servlet

    // Establece el contexto del servlet
    public static void setServletContext(ServletContext context) {
        servletContext = context;
        try {
            inicializarEquipos(); // Inicializa la lista de equipos desde el archivo
        } catch (NombreDuplicadoException ex) {
            Logger.getLogger(GestionarMundial.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Método para agregar un equipo nuevo
    public static void agregarEquipo(String nombreEquipo, String director, String logoPath) throws NombreDuplicadoException, IOException {
        // Verificar si ya existe un equipo con el mismo nombre
        for (Equipo equipo : Mundial.getEquipos()) {
            if (equipo.getNombre().equalsIgnoreCase(nombreEquipo)) {
                throw new NombreDuplicadoException("Ya existe un equipo con ese nombre, inténtelo nuevamente.");
            }
        }

        // Generar un ID único para el nuevo equipo
        int idEquipo = Mundial.generarIdSiguiente();

        // Crear un objeto Equipo con los datos proporcionados
        Equipo miEquipo = new Equipo(idEquipo, nombreEquipo, director, logoPath,new ArrayList());

        Mundial.agregarEquipo(miEquipo);
        guardarMundial();
    }
    
    // Elimina un equipo del Mundial por su ID.
    public static void eliminarEquipo(int id) {
        // Llama al método para eliminar el equipo en el objeto Mundial
        Mundial.eliminarEquipoPorId(id);
        // Guarda los cambios realizados en el objeto Mundial
        guardarMundial();
    }
    
    // Elimina un jugador de un equipo específico del Mundial.
    public static void eliminarJugador(int idEquipo, int id) {
        // Obtiene el equipo por su ID
        Equipo equipo = Mundial.getEquipoPorId(idEquipo);
        // Llama al método para eliminar el jugador en el equipo
        equipo.eliminarJugadorPorId(id);
        // Guarda los cambios realizados en el objeto Mundial
        guardarMundial();
    }

    public void agregarJugador(int idEquipo, String nombre, int edad, double altura, double peso, String posicion, double salario, String urlImagen) throws NombreDuplicadoException {

        // Verificar si ya existe un equipo con el mismo nombre
        for (Equipo equipo : Mundial.getEquipos()) {
            for(Jugador jugador : equipo.getJugadores()){
                if (jugador.getNombre().equalsIgnoreCase(nombre)) {
                throw new NombreDuplicadoException("Ya existe un equipo con ese nombre, inténtelo nuevamente.");
            }
           }

        }

        // Generar un ID único para el nuevo equipo
        Equipo equipo = Mundial.getEquipoPorId(idEquipo);
        
        int totalJugadores = 0;
        ArrayList<Equipo> equipos = Mundial.getEquipos();

        for (Equipo equipoa : equipos) {
            totalJugadores += equipoa.getJugadores().size();
        }
        //int id = equipo.generarIdSiguiente();

        // Crear un objeto Equipo con los datos proporcionados
        Jugador jugador = new Jugador(totalJugadores+1,nombre,edad,altura,peso,salario,urlImagen,posicion);

        equipo.agregarJugador(jugador);
        guardarMundial();
    }
    
    // Edita los detalles de un equipo existente.
    public void editarEquipo(int idEquipo, String nombreEquipo, String director, String nuevoLogoCargado) {
        // Obtiene el equipo por su ID
        Equipo equipo = Mundial.getEquipoPorId(idEquipo);
        // Actualiza los atributos del equipo con los nuevos valores
        equipo.setNombre(nombreEquipo);
        equipo.setDirectorTecnico(director);
        equipo.setBandera(nuevoLogoCargado);

        // Guarda los cambios realizados en el objeto Mundial
        guardarMundial();
    }
    
    
    // Edita los detalles de un jugador existente en un equipo específico.
    public void editarJugador(int idEquipo, int idJugador, String nombre, int edad, double altura, double peso, String posicion, double salario, String nuevoLogoCargado) {
        // Obtiene el equipo por su ID
        Equipo equipo = Mundial.getEquipoPorId(idEquipo);
        // Busca el jugador en el equipo por su ID
        Jugador jugador = equipo.buscarJugadorPorId(idJugador);
        // Actualiza los atributos del jugador con los nuevos valores
        jugador.setNombre(nombre);
        jugador.setEdad(edad);
        jugador.setAltura(altura);
        jugador.setPeso(peso);
        jugador.setPosicion(posicion);
        jugador.setSalario(salario);
        jugador.setRutaImagen(nuevoLogoCargado);

        // Guarda los cambios realizados en el objeto Mundial
        guardarMundial();
    } 
    
    // Método para inicializar la lista de equipos
    private static void inicializarEquipos() throws NombreDuplicadoException {
        Mundial mundial = new Mundial();

        // Obtiene las rutas de los archivos equipos.txt y jugadores.txt ubicados en la carpeta WEB-INF/data
        String equiposFilePath = servletContext.getRealPath("/WEB-INF/data/equipos.txt");
        String jugadoresFilePath = servletContext.getRealPath("/WEB-INF/data/jugadores.txt");

        // Intenta abrir y leer los archivos de equipos y jugadores
        try (BufferedReader equiposReader = new BufferedReader(new FileReader(equiposFilePath));
             BufferedReader jugadoresReader = new BufferedReader(new FileReader(jugadoresFilePath))) {

            String equipoLine;
            // Lee cada línea del archivo equipos.txt
            while ((equipoLine = equiposReader.readLine()) != null) {
                // Divide la línea en un arreglo de Strings utilizando ";" como separador
                String[] equipoInfo = equipoLine.split(";");

                // Crea un nuevo objeto Equipo y establece sus atributos
                Equipo equipo = new Equipo();
                equipo.setId(Integer.parseInt(equipoInfo[0]));
                equipo.setNombre(equipoInfo[1]);
                equipo.setDirectorTecnico(equipoInfo[2]);
                equipo.setBandera(equipoInfo[3]);

                // Leer jugadores asociados a este equipo desde el archivo jugadores.txt
                ArrayList<Jugador> jugadores = new ArrayList<>();
                String jugadorLine;
                // Lee cada línea del archivo jugadores.txt
                while ((jugadorLine = jugadoresReader.readLine()) != null) {
                    // Divide la línea en un arreglo de Strings utilizando ";" como separador
                    String[] jugadorInfo = jugadorLine.split(";");
                    // Verifica si el ID del equipo del jugador coincide con el ID del equipo actual
                    if (Integer.parseInt(jugadorInfo[0]) == equipo.getId()) {
                        // Crea un nuevo objeto Jugador y establece sus atributos
                        Jugador jugador = new Jugador();
                        jugador.setId(Integer.parseInt(jugadorInfo[1]));
                        jugador.setNombre(jugadorInfo[2]);
                        jugador.setEdad(Integer.parseInt(jugadorInfo[3]));
                        jugador.setAltura(Double.parseDouble(jugadorInfo[4]));
                        jugador.setPeso(Double.parseDouble(jugadorInfo[5]));
                        jugador.setSalario(Double.parseDouble(jugadorInfo[6]));
                        jugador.setRutaImagen(jugadorInfo[7]);
                        jugador.setPosicion(jugadorInfo[8]);
                        // Añade el jugador a la lista de jugadores del equipo
                        jugadores.add(jugador);
                    }
                }
                // Establece la lista de jugadores en el equipo
                equipo.setJugadores(jugadores);
                // Añade el equipo a la lista de equipos del Mundial
                mundial.agregarEquipo(equipo);
            }
        } catch (IOException e) {
            // Manejo de excepciones, imprime el error en caso de que ocurra
            System.err.println("Error al inicializar equipos: " + e.getMessage());
        }
    }
    
    // Obtiene una lista de todos los equipos.
    public static List<Equipo> obtenerTodosLosEquipos() {
        // Devuelve la lista de equipos almacenada
        return equipos;
    }
    
    // Guarda la información del Mundial en archivos de texto.
    private static void guardarMundial() {
        // Obtiene las rutas de los archivos equipos.txt y jugadores.txt ubicados en la carpeta WEB-INF/data
        String equiposFilePath = servletContext.getRealPath("/WEB-INF/data/equipos.txt");
        String jugadoresFilePath = servletContext.getRealPath("/WEB-INF/data/jugadores.txt");

        // Intenta abrir los archivos para escritura
        try (BufferedWriter equiposWriter = new BufferedWriter(new FileWriter(equiposFilePath));
             BufferedWriter jugadoresWriter = new BufferedWriter(new FileWriter(jugadoresFilePath))) {

            // Recorre la lista de equipos en el Mundial
            for (Equipo equipo : Mundial.getEquipos()) {
                // Guardar información del equipo en el archivo equipos.txt
                equiposWriter.write(equipo.getId() + ";" + equipo.getNombre() + ";" + equipo.getDirectorTecnico() + ";" + equipo.getBandera());
                equiposWriter.newLine();

                // Obtener la lista de jugadores asociados a este equipo
                List<Jugador> jugadores = equipo.getJugadores();
                // Recorre la lista de jugadores
                for (Jugador jugador : jugadores) {
                    // Guardar información del jugador en el archivo jugadores.txt
                    jugadoresWriter.write(equipo.getId() + ";" + jugador.getId() + ";" + jugador.getNombre() + ";" + jugador.getEdad() + ";" +
                            jugador.getAltura() + ";" + jugador.getPeso() + ";" + jugador.getSalario() + ";" + jugador.getRutaImagen() + ";" +
                            jugador.getPosicion());
                    jugadoresWriter.newLine();
                }
            }
        } catch (IOException e) {
          // Manejo de excepciones, imprime el error en caso de que ocurra
          System.err.println("Error al guardar el archivo: " + e.getMessage());
        }
    }
}