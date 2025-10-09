package com.senai.eventsmanager.service;

import com.senai.eventsmanager.dto.EventoDTO;
import com.senai.eventsmanager.entity.Evento;
import com.senai.eventsmanager.repository.EventoRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class EventoService {
    @Autowired
    private EventoRepository eventoRepository;

    public EventoDTO findById(Long id) {
        //retorna um entidade Evento
        Evento evento = eventoRepository.findById(id).orElseThrow();
        //método para converter um evento entity em eventoCreateDto
        EventoDTO eventoCreateDTO = toDto(evento);
        return eventoCreateDTO;
    }
    //método para salvar um evento
    public EventoDTO save(EventoDTO eventoDto){
        Evento evento = toEntity(eventoDto);
        evento = eventoRepository.save(evento);
        return toDto(evento);
    }

    //método para atualizar um evento
    public EventoDTO update(Long id, EventoDTO eventoDto){
        Evento evento = toEntity(eventoDto);
        evento.setId(id);
        evento = eventoRepository.save(evento);
        return toDto(evento);
    }
    //método para deletar um evento
    public void deleteById(Long id){
        eventoRepository.deleteById(id);
    }
    //método para listar todos os eventos
    public List<EventoDTO> findAll(){
        List<Evento> eventos = eventoRepository.findAll();
        //criar lista de eventoCreateDTO
        List<EventoDTO> eventosDTO = new ArrayList<>();
        //para cada evento na lista de eventos, converter para Dto e add na lista dto
        for(Evento evento : eventos){
            eventosDTO.add(toDto(evento));
        }
        return eventosDTO;
    }

    public List<EventoDTO> calendario(String inicio, String fim){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MM-yyyy");
        LocalDateTime inicioFormatado = LocalDate.parse(inicio, formatter).atStartOfDay();
        LocalDateTime fimFormDate = LocalDate.parse(fim, formatter).atStartOfDay();
        List<Evento> eventos = eventoRepository.calendario(inicioFormatado, fimFormDate);
        List<EventoDTO> eventoDTOs = new ArrayList<>();
        for(Evento evento : eventos){
            eventoDTOs.add(toDto(evento));
        }
        return eventoDTOs;
    }

    public List<EventoDTO> tipoUsuario(String tipo){
        List<Evento> eventos = eventoRepository.tipousuario(tipo);
        for(Evento evento : eventos){
            eventoDTOs.add(toDto(evento));
        }
        return eventoDTOs;
    }
       

       public EventoDTO toDto(Evento evento){
        EventoDTO dto = new EventoDTO();
        BeanUtils.copyProperties(evento, dto);
        return dto;
    }
    public Evento toEntity(EventoDTO dto){
        Evento evento = new Evento();
        BeanUtils.copyProperties(dto, evento);
        return evento;
    }
}
