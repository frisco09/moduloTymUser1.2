package com.tymtorneos.modulotymuser1.retrofit;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("identificador")
    private int identificador;

    @SerializedName("cantidad")
    private int cantidad;

    @SerializedName("nombre")
    private String nombre;

    @SerializedName("usuario_psp")
    private String usuario_psp;

    @SerializedName("estado")
    private String estado;

    @SerializedName("correo")
    private String correo;

    @SerializedName("esVerificado")
    private String esVerificado;

    @SerializedName("esAdministrador")
    private String esAdministrador;

    @SerializedName("fechaCreacion")
    private String fechaCreacion;

    @SerializedName("fechaActualizacion")
    private String fechaActualizacion;

    @SerializedName("fechaEliminacion")
    private String fechaEliminacion;

    @SerializedName("fotoPerfil")
    private String fotoPerfil;

    @SerializedName("claveAcceso")
    private String claveAcceso;

    @SerializedName("links")
    private String links;


    public User(int identi, String nmb, String idpsp, String est, String mail, String clave){
        this.identificador = identi;
        this.nombre = nmb;
        this.usuario_psp = idpsp;
        this.estado = est;
        this.correo = mail;
        this.claveAcceso = clave;
    }

    //metodos
    public int getIdentificador() {
        return identificador;
    }

    public void setIdentificador(int identi) {
        this.identificador = identi;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nmb) {
        this.nombre = nmb;
    }

    public String getUsuario_psp() {
        return usuario_psp;
    }

    public void setUsuario_psp(String idpsp) {
        this.usuario_psp= idpsp;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo= correo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado= estado;
    }

    public String getEsVerificado() {
        return esVerificado;
    }

    public void setEsVerificado(String vrf) {
        this.esVerificado= vrf;
    }

    public String getEsAdministrador() {
        return esAdministrador;
    }

    public void setEsAdministrador(String adm) {
        this.esAdministrador= adm;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fc) {
        this.fechaCreacion= fc;
    }

    public String getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(String fa) {
        this.fechaActualizacion= fa;
    }

    public String getFechaEliminacion() {
        return fechaEliminacion;
    }

    public void setFechaEliminacion(String fe) {
        this.fechaEliminacion= fe;
    }

    public String getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(String foto) {
        this.fotoPerfil= foto;
    }

    public String getClaveAcceso() {
        return claveAcceso;
    }

    public void setClaveAcceso(String clave) {
        this.claveAcceso= clave;
    }
    public String getLinks() {
        return links;
    }

    public void setLinks(String link) {
        this.links= link;
    }
}
