package br.com.lumen.simulator.clt.v1.controller;

import br.com.lumen.simulator.clt.v1.dto.CltDTO;
import br.com.lumen.simulator.clt.v1.responses.CltResponse;
import br.com.lumen.simulator.clt.v1.service.CltService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/")
public class CltController {

    @Autowired
    private CltService cltService;

    @PostMapping("/clt")
    public CltResponse getSalarioLiquidoClt(@RequestBody CltDTO cltDTO) {
        return cltService.getSalarioLiquidoClt(cltDTO);
    }

}

