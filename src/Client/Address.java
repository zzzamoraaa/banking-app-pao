package Client;

import java.util.Scanner;

public class Address {
    private String strada, oras, judet;
    private int codPostal;

    public Address(String strada, String oras, String judet, int codPostal) {
        this.strada = strada;
        this.oras = oras;
        this.judet = judet;
        this.codPostal = codPostal;
    }
    public Address(Scanner in){
        this.read(in);
    }
    public void read(Scanner in){
        System.out.println("Street: ");
        this.strada = in.nextLine();
        System.out.println("City: ");
        this.oras = in.nextLine();
        System.out.println("County: ");
        this.judet = in.nextLine();
        System.out.println("Postal code: ");
        this.codPostal = Integer.parseInt(in.nextLine());
    }

    @Override
    public String toString() {
        return "{" +
                "street='" + strada + '\'' +
                ", city='" + oras + '\'' +
                ", county='" + judet + '\'' +
                ", postalCode=" + codPostal +
                '}';
    }

    public String toCSV() {
        return strada +
                "," + oras +
                "," + judet+
                "," + codPostal;
    }
    public void setStrada(String strada) {
        this.strada = strada;
    }

    public String getStrada() {
        return strada;
    }

    public void setOras(String oras) {
        this.oras = oras;
    }

    public String getOras() {
        return oras;
    }

    public void setJudet(String judet) {
        this.judet = judet;
    }

    public String getJudet() {
        return judet;
    }

    public void setCodPostal(int codPostal) {
        this.codPostal = codPostal;
    }

    public int getCodPostal() {
        return codPostal;
    }
}
