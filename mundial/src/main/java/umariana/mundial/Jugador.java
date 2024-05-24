package umariana.mundial;

import java.io.Serializable;

public class Jugador implements Serializable{
    private int id;
    private String nombre;
    private int edad;
    private double altura;
    private double peso;
    private double salario;
    private String rutaImagen;
    private String posicion;    
    
    //Constructores
    public Jugador() {
    }
    
    public Jugador(int id, String nombre, int edad, double altura, double peso, double salario, String rutaImagen, String posicion) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.altura = altura;
        this.peso = peso;
        this.salario = salario;
        this.rutaImagen = rutaImagen;
        this.posicion = posicion;
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
    
    //Edad
    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
    
    //Altura
    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }
    
    // Peso
    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }
    
    //Salario
    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }
    
    //Ruta Imagen
    public String getRutaImagen() {
        return rutaImagen;
    }

    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }
    
    //Posición
    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }
    
    //Otros métodos
    // Devuelve una representación en cadena del equipo.
    @Override
    public String toString() {
        return "Jugador{" + "id=" + id + ", nombre=" + nombre + ", edad=" + edad + ", altura=" + altura + ", peso=" + peso + ", salario=" + salario + ", rutaImagen=" + rutaImagen + ", posicion=" + posicion + '}';
    } 
}
