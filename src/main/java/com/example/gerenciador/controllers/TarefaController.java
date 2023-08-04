package com.example.gerenciador.controllers;

import com.example.gerenciador.dtos.TarefaRecordDto;
import com.example.gerenciador.models.Tarefa;
import com.example.gerenciador.repositories.TarefaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class TarefaController {
    @Autowired
    TarefaRepository tarefaRepository;

    @PostMapping(path = "/tarefas")
    public ResponseEntity<Tarefa> saveTarefa(@RequestBody @Valid TarefaRecordDto tarefaRecordDto) {
        Tarefa tarefa = new Tarefa();
        BeanUtils.copyProperties(tarefaRecordDto, tarefa);
        return ResponseEntity.status(HttpStatus.CREATED).body(tarefaRepository.save(tarefa));
    }

    @GetMapping(path = "/tarefas")
    public ResponseEntity<List<Tarefa>> getAllTarefas() {
        List<Tarefa> tarefaList = tarefaRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(tarefaList);
    }

    @GetMapping(path = "/tarefas/{id}")
    public ResponseEntity<Object> getOneTarefa(@PathVariable(value = "id") Long id) {
        Optional<Tarefa> tarefa = tarefaRepository.findById(id);
        if (tarefa.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tarefa não encontrada");
        }
        return ResponseEntity.status(HttpStatus.OK).body(tarefa);
    }

    @PutMapping(path = "/tarefas/{id}")
    public ResponseEntity<Object> updateTarefa(@RequestBody @Valid TarefaRecordDto tarefaRecordDto, @PathVariable(value = "id") Long id) {
        Optional<Tarefa> tarefaAlterada = tarefaRepository.findById(id);
        if (tarefaAlterada.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tarefa não encontrada");
        }
        Tarefa tarefa = tarefaAlterada.get();
        BeanUtils.copyProperties(tarefaRecordDto, tarefa);
        return ResponseEntity.status(HttpStatus.OK).body(tarefaRepository.save(tarefa));
    }

    @DeleteMapping(path = "/tarefas/{id}")
    public ResponseEntity<Object> deleteTarefa(@PathVariable(value = "id") Long id) {
        Optional<Tarefa> tarefa = tarefaRepository.findById(id);
        if (tarefa.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tarefa não encontrada");
        }
        tarefaRepository.delete(tarefa.get());
        return ResponseEntity.status(HttpStatus.OK).body("Tarefa deletada com sucesso");
    }
}
