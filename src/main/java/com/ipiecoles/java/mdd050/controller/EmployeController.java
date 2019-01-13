package com.ipiecoles.java.mdd050.controller;

import com.ipiecoles.java.mdd050.model.Employe;
import com.ipiecoles.java.mdd050.repository.EmployeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/employes")
public class EmployeController {
    @Autowired
    private EmployeRepository employeRepository;

    @RequestMapping(method = RequestMethod.GET, value = "/count")
    public Long EmployerCount()
    {
        return employeRepository.count();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public Employe getEmpId(@PathVariable("id") Long id) {
        return employeRepository.findOne(id);
    }

    @RequestMapping(params = {"matricule"})
    public Employe getEmpByMat(@RequestParam("matricule") String matricule){
        return employeRepository.findByMatricule(matricule);
    }

    @RequestMapping(params = {"page","size","sortProperty","sortDirection"})
    public Page<Employe> findAllPaging(@RequestParam("page") Integer page,
                                     @RequestParam("size") Integer size,
                                     @RequestParam("sortProperty") String sortProperty,
                                     @RequestParam("sortDirection") String sortDirection){
        PageRequest pageRequest = new PageRequest(page, size, Sort.Direction.fromString(sortDirection), sortProperty);
        return employeRepository.findAll(pageRequest);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Employe createEmploye(@RequestBody Employe employe){
        return employeRepository.save(employe);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Employe updateEmploye(@PathVariable Long id, @RequestBody Employe employe){
        return employeRepository.save(employe);
    }
}
