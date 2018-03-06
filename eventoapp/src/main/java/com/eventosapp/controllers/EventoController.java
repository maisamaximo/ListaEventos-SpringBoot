package com.eventosapp.controllers;

import com.eventosapp.models.Convidado;
import com.eventosapp.models.Eventos;
import com.eventosapp.repository.ConvidadoRepository;
import com.eventosapp.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class EventoController {

    @Autowired
    private EventoRepository eventoRepository;
    @Autowired
    private ConvidadoRepository convidadoRepository;

    @RequestMapping(value = "/cadastrarEvento", method = RequestMethod.GET)
    public String Form1(){
        return "evento/formEvento";
    }

    @RequestMapping(value = "/cadastrarEvento", method = RequestMethod.POST)
    public String Form1(@Valid Eventos eventos, BindingResult result, RedirectAttributes attributes){
        if (result.hasErrors()){
            attributes.addFlashAttribute("mensagem", "Verifique os campos!");
            return "redirect:/cadastrarEvento";
        }
        eventoRepository.save(eventos);
        attributes.addFlashAttribute("mensagem", "Evento Cadastrado com sucesso!");
        return "redirect:/cadastrarEvento";
    }
    @RequestMapping(value = "/eventos")
    public ModelAndView listaEventos(){
        ModelAndView mv = new ModelAndView("index");
        Iterable<Eventos> eventoList = eventoRepository.findAll();
        mv.addObject("eventoList", eventoList);
        return mv;
    }

    @RequestMapping(value = "/{codigo}", method = RequestMethod.GET)
    public ModelAndView detalhesEvento(@PathVariable("codigo") long codigo){
        Eventos eventoList = eventoRepository.findByCodigo(codigo);
        ModelAndView mv = new ModelAndView("evento/detalhesEvento");
        mv.addObject("eventoList", eventoList);
        Iterable<Convidado> convidados = convidadoRepository.findByEvento(eventoList);
        mv.addObject("convidados", convidados);
        return mv;
    }

    //delete events
    @RequestMapping("/deletar")
    public String deletarEvento(long codigo){
        Eventos eventos = eventoRepository.findByCodigo(codigo);
        eventoRepository.delete(eventos);
        return "redirect:/eventos";
    }


    @RequestMapping(value = "/{codigo}", method = RequestMethod.POST)
    public String detalhesEventoConvidado(@PathVariable("codigo") long codigo, @Valid Convidado convidado, BindingResult result, RedirectAttributes attributes){
        if (result.hasErrors()){
            attributes.addFlashAttribute("mensagem", "Verifique os campos!");
            return "redirect:/{codigo}";
        }
        Eventos eventoList = eventoRepository.findByCodigo(codigo);
        convidado.setEvento(eventoList);
        convidadoRepository.save(convidado);
        attributes.addFlashAttribute("mensagem", "Convidado Adicionado com Sucesso!");

        return "redirect:/{codigo}";
    }

    //delete guest
    @RequestMapping("/deletarConvidado")
    public String deletarConvidado(String rg){
        Convidado convidado = convidadoRepository.findByRg(rg);
        convidadoRepository.delete(convidado);

        Eventos eventos = convidado.getEvento();
        long codigoLong = eventos.getCodigo();
        String codigo = "" + codigoLong;
        return "redirect:/" + codigo;
    }

}

