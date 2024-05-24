package umariana.mundial;

import java.util.ArrayList;

public class Mundial {
    
    // Lista estática para almacenar los equipos
    private static ArrayList<Equipo> equipos;

    // Constructor que inicializa la lista de equipos
    public Mundial() {
        this.equipos = new ArrayList<>();
    }
    
    /**
     * Método para agregar un nuevo equipo a la lista.
     * @param equipo El equipo a agregar.
     * @throws NombreDuplicadoException si el equipo ya está en la lista.
     */
    public static void agregarEquipo(Equipo equipo) throws NombreDuplicadoException {
        // Verifica si el equipo ya está en la lista
        if (!equipos.contains(equipo)) {
            // Si no está, lo agrega
            equipos.add(equipo);
        } else {
            // Si está, lanza una excepción
            throw new NombreDuplicadoException("Ya existe un registro con el equipo que quieres ingresar");
        }
    }
    
    /**
     * Método para obtener la lista de equipos.
     * @return La lista de equipos.
     */
    public static ArrayList<Equipo> getEquipos() {
        return equipos;
    }
    
    /**
     * Método para obtener un equipo por su ID.
     * @param idEquipo El ID del equipo.
     * @return El equipo con el ID especificado, o null si no se encuentra.
     */
    public static Equipo getEquipoPorId(int idEquipo) {
        // Recorre la lista de equipos buscando el equipo con el ID especificado
        for (Equipo equipo : equipos) {
            if (equipo.getId() == idEquipo) {
                return equipo; // Retorna el equipo si lo encuentra
            }
        }
        return null; // Retorna null si no encuentra el equipo
    }

    /**
     * Método para generar el siguiente ID para un equipo nuevo.
     * @return El siguiente ID disponible.
     */
    public static int generarIdSiguiente() {
        int ultimoId = 0;
        // Recorre la lista de equipos para encontrar el mayor ID actual
        for (Equipo equipo : equipos) {
            if (equipo.getId() > ultimoId) {
                ultimoId = equipo.getId();
            }
        }
        // Retorna el siguiente ID incrementado en uno
        return ultimoId + 1;
    }

    /**
     * Método para eliminar un equipo por su ID.
     * @param id El ID del equipo a eliminar.
     */
    public static void eliminarEquipoPorId(int id) {
        // Recorre la lista de equipos buscando el equipo con el ID especificado
        for (int i = 0; i < equipos.size(); i++) {
            if (equipos.get(i).getId() == id) {
                // Elimina el equipo si lo encuentra
                equipos.remove(i);
                System.out.println("Equipo con ID " + id + " eliminado correctamente.");
                return; // Termina el método después de eliminar el equipo
            }
        }
        // Imprime un mensaje si no encuentra el equipo con el ID especificado
        System.out.println("No se encontró ningún equipo con el ID " + id);
    }
}
