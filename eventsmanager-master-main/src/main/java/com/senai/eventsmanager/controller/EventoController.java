package com.senai.eventsmanager.controller;


import com.senai.eventsmanager.Enums.EventoEnum;
import com.senai.eventsmanager.dto.EventoDTO;
import com.senai.eventsmanager.service.EventoService;

import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/evento")
@CrossOrigin
@Log4j2
//  http://localhost:8080/api/v1/evento/1
public class EventoController {

    @Autowired
    EventoService service;

    @GetMapping("/calendario/{dataInicio}/{dataFinal}")
    public List<EventoDTO> calendario(@PathVariable String dataInicio, @PathVariable String dataFinal) {
        return service.calendario(dataInicio, dataFinal);

    }
    @GetMapping("/tipo/{tipo}")
    public List<EventoDTO> filtro(@PathVariable ("tipo") EventoEnum tipo) {
        return service.findByTipo(tipo);

    }
    // pegar um evento pelo seu id
    @GetMapping("/{id}")
    public EventoDTO findById(@PathVariable("id") Long id){
        return service.findById(id);
    }
    // pegar todos os eventos
    @GetMapping
    public List<EventoDTO> findAll(){
        return service.findAll();
    }
    // salvar um evento
    @PostMapping
    public EventoDTO save(
            @RequestBody @Valid EventoDTO eventoCreateDTO ){
                log.info(eventoCreateDTO.toString());
        return
        service.save(eventoCreateDTO);
    }
    // atualizar um evento
    @PutMapping("/{id}")
    public EventoDTO update(
            @PathVariable("id") Long id,
            @RequestBody EventoDTO eventoCreateDTO){
        return service.update(id,eventoCreateDTO);
    }
    // deletar um evento pelo seu id
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id")Long id){

        service.deleteById(id);
    }


}
