package br.com.lumen.simulator.clt.v1.service;

import br.com.lumen.simulator.clt.v1.dto.CltDTO;
import br.com.lumen.simulator.clt.v1.responses.CltResponse;
import org.springframework.stereotype.Service;

@Service
public class CltService {


    public CltResponse getSalarioLiquidoClt(CltDTO cltDTO) {
        Double INSS = getInss(cltDTO.getSalarioBruto());
        Double IRPF = getIrpf(cltDTO.getSalarioBruto());
        Double descontos = INSS + IRPF + cltDTO.getOutrosDescontos();
        Double salarioLiquido = cltDTO.getSalarioBruto() - descontos;
        Double beneficios = cltDTO.getBeneficios();

        return new CltResponse(cltDTO.getSalarioBruto(), salarioLiquido, descontos, INSS, IRPF, beneficios, cltDTO.getOutrosDescontos());
    }

    private Double getInss(Double salarioBruto) {
        if (salarioBruto <= 1518.00) {
            return salarioBruto * 0.075;
        }

        if (salarioBruto <= 2793.88) {
            return (salarioBruto * 0.09) - 22.77;
        }

        if (salarioBruto <= 4190.83) {
            return (salarioBruto * 0.12) - 106.59;
        }

        if (salarioBruto <= 8157.41) {
            return (salarioBruto * 0.14) - 190.40;
        }

        if (salarioBruto > 8157.41) {
            return 951.62;
        }

        return 0.0;
    }


    private Double getIrpf(Double salarioBruto) {
        if (salarioBruto <= 2259.20) {
            return 0.0;
        }

        if (salarioBruto > 1518.00 && salarioBruto <= 2826.65) {
            return (salarioBruto * (7.5 / 100));
        }

        if (salarioBruto > 2793.88 && salarioBruto <= 3751.05) {
            return (salarioBruto * (15.0 / 100));
        }

        if (salarioBruto > 4190.83 && salarioBruto <= 4664.68) {
            return (salarioBruto * (22.5 / 100));
        }

        if (salarioBruto > 4664.68) {
            return (salarioBruto * (27.5 / 100));
        }

        return 0.0;
    }
}
