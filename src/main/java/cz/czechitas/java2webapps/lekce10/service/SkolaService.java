package cz.czechitas.java2webapps.lekce10.service;

import cz.czechitas.java2webapps.lekce10.entity.Rodic;
import cz.czechitas.java2webapps.lekce10.entity.Student;
import cz.czechitas.java2webapps.lekce10.entity.Trida;
import cz.czechitas.java2webapps.lekce10.repository.RodicRepository;
import cz.czechitas.java2webapps.lekce10.repository.StudentRepository;
import cz.czechitas.java2webapps.lekce10.repository.TridaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SkolaService {
    private final TridaRepository tridaRepository;
    private final StudentRepository studentRepository;
    private final RodicRepository rodicRepository;

    @Autowired
    public SkolaService(TridaRepository tridaRepository, StudentRepository studentRepository, RodicRepository rodicRepository) {
        this.tridaRepository = tridaRepository;
        this.studentRepository = studentRepository;
        this.rodicRepository = rodicRepository;
    }

    public List<Trida> seznamTrid() {
        return this.tridaRepository.findByOrderByNazevAsc();
    }

    public Trida detailTridy(String tridaNazev) {
        return this.tridaRepository.findByNazev(tridaNazev);
    }

    public List<Rodic> seznamRodicu(Student student) {
        return this.rodicRepository.findAllByDetiContainsOrderByPrijmeniAscJmenoAsc(student);
    }
    public List<Student> seznamStudentuTridy(Trida trida){
        return this.studentRepository.findByTridaOrderByPrijmeniAscJmenoAsc(trida);
    }

    public Student detailStudenta(Integer id, Trida trida) {
        return this.studentRepository.findByIdAndTrida(id, trida);
    }
}
