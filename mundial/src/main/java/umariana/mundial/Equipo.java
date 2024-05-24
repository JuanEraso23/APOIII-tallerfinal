package umariana.mundial;

import java.util.ArrayList;

public class Equipo {
    //Attributos
    private int id;
    private String nombre;
    private String directorTecnico;
    private String bandera;
    private  ArrayList<Jugador> jugadores;
    
    //Constructores
    public Equipo(int id, String nombre, String directorTecnico, String bandera, ArrayList<Jugador> jugadores) {
        this.id = id;
        this.nombre = nombre;
        this.directorTecnico = directorTecnico;
        this.bandera = bandera;
        this.jugadores = jugadores;
    }

    public Equipo() {
    }
    
    //Getters & Setters
    //Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    //Nombre
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    //Director
    public String getDirectorTecnico() {
        return directorTecnico;
    }
    
    public void setDirectorTecnico(String directorTecnico) {
        this.directorTecnico = directorTecnico;
    }
    
    //Bandera
    public String getBandera() {
        return bandera;
    }

    public void setBandera(String bandera) {
        this.bandera = bandera;
    }
    
    // Otros métodos de la clase
    public void agregarJugador(Jugador jugador) throws NombreDuplicadoException {
        // Verifica si el jugador ya está en la lista
        if (!jugadores.contains(jugador)) {
            // Si no está, lo agrega
            jugadores.add(jugador);
        } else {
            // Si ya está, lanza una excepción
            throw new NombreDuplicadoException("Ya existe un registro con el jugador que quieres ingresar");
        }
    }
    
    // Establece la lista de jugadores del equipo.
    public void setJugadores(ArrayList<Jugador> jugadores) {
        this.jugadores = jugadores;
    }
    
    // Devuelve una representación en cadena del equipo.
    @Override
    public String toString() {
        return "Equipo{" + "id=" + id + ", nombre=" + nombre + ", directorTecnico=" + directorTecnico + ", bandera=" + bandera + ", jugadores=" + jugadores + '}';
    }
    
    // Obtiene la lista de jugadores del equipo.
    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }
    
    // Genera el siguiente ID para un nuevo jugador.
    public int generarIdSiguiente() {
        int ultimoId = 0;
        // Recorre la lista de jugadores para encontrar el mayor ID
        for (Jugador jugador : jugadores) {
            if (jugador.getId() > ultimoId) {
                ultimoId = jugador.getId();
            }
        }
        // Devuelve el siguiente ID incrementado en 1
        return ultimoId + 1;
    }
    
    public  void eliminarJugadorPorId(int idJugador) {
        // Iterar sobre la lista de jugadores
        for (int i = 0; i < jugadores.size(); i++) {
            // Obtener el jugador actual
            Jugador jugador = jugadores.get(i);
            // Verificar si el ID del jugador coincide con el ID proporcionado
            if (jugador.getId() == idJugador) {
                // Eliminar el jugador de la lista
                jugadores.remove(i);
                // Salir del bucle, ya que se ha eliminado el jugador
                break;
            }
        }
    }
    
    public Jugador buscarJugadorPorId(int idJugador) {
        // Iterar sobre la lista de jugadores
        for (Jugador jugador : jugadores) {
            // Verificar si el ID del jugador coincide con el ID proporcionado
            if (jugador.getId() == idJugador) {
                // Devolver el jugador encontrado
                return jugador;
            }
        }
        // Si no se encuentra ningún jugador con el ID especificado, devolver null
        return null;
    }
}