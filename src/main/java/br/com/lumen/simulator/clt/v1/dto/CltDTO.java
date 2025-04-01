package br.com.lumen.simulator.clt.v1.dto;

public class CltDTO {
    private Double salarioBruto;
    private Double outrosDescontos;
    private Double beneficios;

    public CltDTO() {
    }

    public CltDTO(Double outrosDescontos, Double salarioBruto, Double beneficios) {
        this.outrosDescontos = outrosDescontos;
        this.salarioBruto = salarioBruto;
        this.beneficios = beneficios;
    }

    //getter
    public Double getSalarioBruto() {
        return salarioBruto;
    }

    public Double getOutrosDescontos() {
        return outrosDescontos;
    }

    public Double getBeneficios() {
        return beneficios;
    }

    //setter
    public void setSalarioBruto(Double salarioBruto) {
        this.salarioBruto = salarioBruto;
    }

    public void setOutrosDescontos(Double outrosDescontos) {
        this.outrosDescontos = outrosDescontos;
    }

    public void setBeneficios(Double beneficios) {
        this.beneficios = beneficios;
    }
}
