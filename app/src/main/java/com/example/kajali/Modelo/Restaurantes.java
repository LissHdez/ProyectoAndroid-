package com.example.kajali.Modelo;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

public class Restaurantes implements Parcelable {
    private int id;
    private String nombreR;
    private String descripcionR;
    private String telefonoR;
    private String horarioR;
    private String provinciaR;
    private String categoriaR;
    private Uri imgR;
    private Uri platoD;

    public Restaurantes(int id, String nombreR, String descripcionR, String telefonoR, String horarioR, String provinciaR, String categoriaR, Uri imgR, Uri platoD) {
        this.id = id;
        this.nombreR = nombreR;
        this.descripcionR = descripcionR;
        this.telefonoR = telefonoR;
        this.horarioR = horarioR;
        this.provinciaR = provinciaR;
        this.categoriaR = categoriaR;
        this.imgR = imgR;
        this.platoD = platoD;
    }

    public Restaurantes() {
        this.nombreR = "";
        this.descripcionR = "";
        this.telefonoR = "";
        this.horarioR = "";
        this.provinciaR = "";
        this.categoriaR = "";
        this.imgR = Uri.parse("");
        this.platoD = Uri.parse("");
    }

    protected Restaurantes(Parcel in) {
        id = in.readInt();
        nombreR = in.readString();
        descripcionR = in.readString();
        telefonoR = in.readString();
        horarioR = in.readString();
        provinciaR = in.readString();
        categoriaR = in.readString();
        imgR = in.readParcelable(Uri.class.getClassLoader());
        platoD = in.readParcelable(Uri.class.getClassLoader());
    }

    public static final Creator<Restaurantes> CREATOR = new Creator<Restaurantes>() {
        @Override
        public Restaurantes createFromParcel(Parcel in) {
            return new Restaurantes(in);
        }

        @Override
        public Restaurantes[] newArray(int size) {
            return new Restaurantes[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreR() {
        return nombreR;
    }

    public void setNombreR(String nombreR) {
        this.nombreR = nombreR;
    }

    public String getDescripcionR() {
        return descripcionR;
    }

    public void setDescripcionR(String descripcionR) {
        this.descripcionR = descripcionR;
    }

    public String getTelefonoR() {
        return telefonoR;
    }

    public void setTelefonoR(String telefonoR) {
        this.telefonoR = telefonoR;
    }

    public String getHorarioR() {
        return horarioR;
    }

    public void setHorarioR(String horarioR) {
        this.horarioR = horarioR;
    }

    public String getProvinciaR() {
        return provinciaR;
    }

    public void setProvinciaR(String provinciaR) {
        this.provinciaR = provinciaR;
    }

    public String getCategoriaR() {
        return categoriaR;
    }

    public void setCategoriaR(String categoriaR) {
        this.categoriaR = categoriaR;
    }

    public Uri getImgR() {
        return imgR;
    }

    public void setImgR(Uri imgR) {
        this.imgR = imgR;
    }

    public Uri getPlatoD() {
        return platoD;
    }

    public void setPlatoD(Uri platoD) {
        this.platoD = platoD;
    }

    @Override
    public String toString() {
        return "Restaurantes{" +
                "nombreR='" + nombreR + '\'' +
                ", descripcionR='" + descripcionR + '\'' +
                ", telefonoR='" + telefonoR + '\'' +
                ", horarioR='" + horarioR + '\'' +
                ", provinciaR='" + provinciaR + '\'' +
                ", categoriaR='" + categoriaR + '\'' +
                ", imgR=" + imgR +
                ", platoD=" + platoD +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}//fin de la class
