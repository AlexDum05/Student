package org.example;

public abstract class StudentDecorator implements IStudent {
    protected final IStudent studentDecorat;

    public StudentDecorator(IStudent studentDecorat) {
        this.studentDecorat = studentDecorat;
    }

    @Override
    public String getDescriere() {
        return studentDecorat.getDescriere();
    }

    @Override
    public Student getStudentDeBaza() {
        return studentDecorat.getStudentDeBaza();
    }
}

