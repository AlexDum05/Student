package org.example;

public class Student {
    private int NumarMatricol;
    private String prenume;
    private String nume;
    private int formatieDeStudiu;
    private boolean estePrezent;
    public Student(int NumarMatricol,String prenume,String nume,int formatieDeStudiu ){
        this.NumarMatricol=NumarMatricol;
        this.prenume=prenume;
        this.nume=nume;
        this.formatieDeStudiu=formatieDeStudiu;
        this.estePrezent=false;
    }
    public boolean verificaPrezenta() {
        return estePrezent;
    }
    public void punePrezenta(boolean status) {
        this.estePrezent = status;
    }
    public void setNumarMatricol(int NumarMatricol){
        this.NumarMatricol=NumarMatricol;
    }
    public int getNumarMatricol(){
        return this.NumarMatricol;
    }
    public void setPrenume(String prenume){
        this.prenume=prenume;
    }
    public String getPrenume(){
        return this.prenume;
    }
    public void setNume(String nume){
        this.nume=nume;
    }
    public String getNume(){
        return this.nume;
    }
    public void setFormatieDeStudiu(int formatieDeStudiu){
        this.formatieDeStudiu=formatieDeStudiu;
    }
    public int getFormatieDeStudiu(){
        return this.formatieDeStudiu;
    }
    @Override
    public String toString() {
        return "Student{\n" +
                "NumarMatricol=" + NumarMatricol + "\n" +
                "Prenume=" + prenume + '\n' +
                "Nume=" + nume + '\n' +
                "FormatieDeStudiu=" + formatieDeStudiu + "\n" +
                "}";
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return NumarMatricol == student.NumarMatricol;
    }

    @Override
    public int hashCode() {
        // Generează un cod unic bazat pe numărul matricol
        return java.util.Objects.hash(NumarMatricol);
    }
}


