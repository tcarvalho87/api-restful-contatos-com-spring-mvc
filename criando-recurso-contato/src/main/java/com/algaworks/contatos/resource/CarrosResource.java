package com.algaworks.contatos.resource;

import com.algaworks.contatos.model.Carro;
import com.algaworks.contatos.repository.Carros;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/carros")
public class CarrosResource {

    @Autowired
    private Carros carros;

    @PostMapping
    public Carro adicionar(@Valid @RequestBody Carro carro){
        return carros.save(carro);
    }

    @GetMapping
    public List<Carro> listar(){ return carros.findAll();}

    @GetMapping("/{id}")
    public ResponseEntity<Carro> buscar(@PathVariable Long id){
        Carro carro = carros.findOne(id);

        if(carro == null){
            ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(carro);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Carro> atualizar(@PathVariable Long id, @Valid @RequestBody Carro carro){
        Carro existe = carros.findOne(id);

        if(existe == null){
            ResponseEntity.notFound().build();
        }

        BeanUtils.copyProperties(carro, existe, "id");

        existe = carros.save(existe);

       return ResponseEntity.ok(existe);
    }
}
