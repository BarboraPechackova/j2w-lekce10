package cz.czechitas.java2webapps.lekce10.controller;

import cz.czechitas.java2webapps.lekce10.entity.Student;
import cz.czechitas.java2webapps.lekce10.entity.Trida;
import cz.czechitas.java2webapps.lekce10.service.SkolaService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SkolaController {

    private final SkolaService service;

    @Autowired
    public SkolaController(SkolaService service) {
        this.service = service;
    }


    @GetMapping("/")
    public ModelAndView index() {
        return new ModelAndView("index")
                .addObject("index", service.seznamTrid());
    }

    @GetMapping("/trida/{tridaNazev}")
    public ModelAndView detailTridy(@PathVariable(value = "tridaNazev") String tridaNazev) {
        Trida trida = service.detailTridy(tridaNazev);
        return new ModelAndView("detailTrida")
                .addObject("trida", trida)
                .addObject("student", service.seznamStudentuTridy(trida));
    }

    @GetMapping("/trida/{tridaNazev}/{student}")
    public ModelAndView detailStudenta(@PathVariable(value = "tridaNazev") String tridaNazev, @PathVariable(value = "student") Integer id) {
        Trida trida = service.detailTridy(tridaNazev);
        Student student = service.detailStudenta(id, trida);
        return new ModelAndView("detailStudent")
                .addObject("trida", trida)
                .addObject("student", student)
                .addObject("rodic", service.seznamRodicu(student));
    }

}
