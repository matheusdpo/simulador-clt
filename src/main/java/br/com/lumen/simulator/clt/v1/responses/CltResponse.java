package br.com.lumen.simulator.clt.v1.responses;

public class CltResponse {
    private Double salarioLiquido;
    private Double salarioLiquidoComBeneficios;
    private Double beneficios;
    private Double descontos;
    private Double inss;
    private Double irpf;

    public CltResponse() {
    }

    public CltResponse(Double salarioLiquido, Double descontos, Double inss, Double irpf, Double beneficios) {
        this.salarioLiquido = salarioLiquido;
        this.descontos = descontos;
        this.inss = inss;
        this.irpf = irpf;
        this.beneficios = beneficios;
        this.salarioLiquidoComBeneficios = this.salarioLiquido + this.beneficios;
    }

    //getter
    public Double getSalarioLiquido() {
        return salarioLiquido;
    }

    public Double getDescontos() {
        return descontos;
    }

    public Double getInss() {
        return inss;
    }

    public Double getIrpf() {
        return irpf;
    }

    public Double getSalarioLiquidoComBeneficios() {
        return salarioLiquidoComBeneficios;
    }

    public Double getBeneficios() {
        return beneficios;
    }

    //setter
    public void setSalarioLiquido(Double salarioLiquido) {
        this.salarioLiquido = salarioLiquido;
    }

    public void setDescontos(Double descontos) {
        this.descontos = descontos;
    }

    public void setInss(Double inss) {
        this.inss = inss;
    }

    public void setIrpf(Double irpf) {
        this.irpf = irpf;
    }

    public void setSalarioLiquidoComBeneficios(Double salarioLiquidoComBeneficios) {
        this.salarioLiquidoComBeneficios = salarioLiquidoComBeneficios;
    }

    public void setBeneficios(Double beneficios) {
        this.beneficios = beneficios;
    }
}
