package com.senai.eventsmanager.service;

import com.senai.eventsmanager.Enums.EventoEnum;
import com.senai.eventsmanager.dto.EventoDTO;
import com.senai.eventsmanager.entity.Evento;
import com.senai.eventsmanager.repository.EventoRepository;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
public class EventoService {
    @Autowired
    private EventoRepository eventoRepository;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public List<EventoDTO> findByTipo(EventoEnum tipo){
        List<Evento> eventos = eventoRepository.findByTipo(tipo);
        List<EventoDTO> eventosDtos = new ArrayList<>();
        for (Evento evento : eventos){
            eventosDtos.add(toDto(evento));
        }
        return eventosDtos;
    }

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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDateTime inicioFormatado = LocalDate.parse(inicio, formatter).atStartOfDay();
        LocalDateTime fimFormDate = LocalDate.parse(fim, formatter).atStartOfDay();
        List<Evento> eventos = eventoRepository.calendario(inicioFormatado, fimFormDate);
        List<EventoDTO> eventoDTOs = new ArrayList<>();
        for(Evento evento : eventos){
            eventoDTOs.add(toDto(evento));
        }
        return eventoDTOs;
    }

       public EventoDTO toDto(Evento evento){
           DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        EventoDTO dto = new EventoDTO();
        BeanUtils.copyProperties(evento, dto);
        dto.setDataInicio(evento.getDataInicio().format(formatter));
        dto.setDataFinal(evento.getDataFinal().format(formatter));
           log.warn("start: " + dto.getDataInicio());
           log.warn("fim: " + dto.getDataFinal());
        return dto;
    }
    public Evento toEntity(EventoDTO dto){
        Evento evento = new Evento();
        BeanUtils.copyProperties(dto, evento);
        log.warn("start: " + dto.getDataInicio());
        log.warn("fim: " + dto.getDataFinal());
        evento.setDataInicio(LocalDateTime.parse(dto.getDataInicio().replace("T", " "), formatter));
        evento.setDataFinal(LocalDateTime.parse(dto.getDataFinal().replace("T", " "), formatter));
        log.warn("start: " + evento.getDataInicio());
        log.warn("start: " + evento.getDataInicio());
        return evento;
    }

}