package com.dannicleo.cielo.desafio2.controller;

import com.dannicleo.cielo.desafio2.dto.register.GenericClientRegisterDTO;
import com.dannicleo.cielo.desafio2.dto.update.GenericClientUpdateDTO;
import com.dannicleo.cielo.desafio2.entity.Client;
import com.dannicleo.cielo.desafio2.exception.DocumentExistentException;
import com.dannicleo.cielo.desafio2.exception.DocumentNotFoundException;
import com.dannicleo.cielo.desafio2.exception.QueueEmptyException;
import com.dannicleo.cielo.desafio2.queue.MyQueue;
import com.dannicleo.cielo.desafio2.service.ClientService;

import com.dannicleo.cielo.desafio2.validators.ClientPessoaFisicaValidator;
import com.dannicleo.cielo.desafio2.validators.ClientPessoaJuridicaValidator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/clientes")
@Tag(name = "Pré cadastro de clientes")
@Validated
public class ClientController {
   
    private final ClientService clientService;
    private MyQueue queue;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
        this.queue = new MyQueue();
    }

    @Operation(
            description = "Lista clientes cadastrados",
            summary = "Retorna uma lista de Clientes",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200"),
                    @ApiResponse(description = "Unauthorized / Invalid token", responseCode = "403"),
                    @ApiResponse(description = "Not found", responseCode = "404"),
            }
    )
    @GetMapping
    public ResponseEntity<List<Client>> list(){
        return new ResponseEntity<>(this.clientService.list(), HttpStatus.OK);
    }


    @Operation(
            description = "Busca um cliente cadastrado",
            summary = "Retorna um os dados de um Cliente",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200")
            }
    )
    @GetMapping("/{uuid}")
    public ResponseEntity<Client> consult(
            @Parameter(description = "UUID do Cliente a ser retornado", required = true)
            @PathVariable UUID uuid){
        return new ResponseEntity<>(this.clientService.consult(uuid), HttpStatus.OK);
    }

    @Operation(
            description = "Inserção de Cliente",
            summary = "Cadastra um Cliente",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "201"),
                    @ApiResponse(description = "Conflict", responseCode = "409"),
            }
    )
    @PostMapping
    public ResponseEntity<Client> insert(
            @io.swagger.v3.oas.annotations.parameters.RequestBody
            @Parameter(description = "JSON de solicitação para cadastrar um cliente",
                    required = true)
            @RequestBody GenericClientRegisterDTO requestData){

        if(requestData.getTipo().equals("F")){

            ClientPessoaFisicaValidator newClient = new ClientPessoaFisicaValidator();

            newClient.setTipo(requestData.getTipo());
            newClient.setMcc(requestData.getMcc());
            newClient.setEmail(requestData.getEmail());
            newClient.setNome(requestData.getNome());
            newClient.setCpf(requestData.getCpf());

            Client clientInserted = this.clientService.createPessoaFisica(newClient);
            this.queue.enqueue(clientInserted);

            return new ResponseEntity<>(clientInserted, HttpStatus.CREATED);

        } else if(requestData.getTipo().equals("J")) {

            ClientPessoaJuridicaValidator newClient = new ClientPessoaJuridicaValidator();
            newClient.setTipo(requestData.getTipo());
            newClient.setMcc(requestData.getMcc());
            newClient.setEmail(requestData.getEmail());
            newClient.setRazaoSocial(requestData.getRazaoSocial());
            newClient.setCnpj(requestData.getCnpj());
            newClient.setNomeContato(requestData.getNomeContato());
            newClient.setCpfContato(requestData.getCpfContato());

            Client clientInserted = this.clientService.createPessoaJuridica(newClient);
            this.queue.enqueue(clientInserted);

            return new ResponseEntity<>(clientInserted, HttpStatus.CREATED);

        }

        return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);

    }

    @Operation(
            description = "Alteração de Cliente",
            summary = "Altera os dados de um Cliente",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "201"),
                    @ApiResponse(description = "Not found", responseCode = "404"),
            }
    )
    @PutMapping
    public ResponseEntity<Client> update(
            @io.swagger.v3.oas.annotations.parameters.RequestBody
            @Parameter(description = "JSON de solicitação para alteração um cliente",
                    required = true)
            @RequestBody GenericClientUpdateDTO requestData){
        return new ResponseEntity<>(this.clientService.update(requestData), HttpStatus.OK);
    }

    @Operation(
            description = "Exclusão de Cliente",
            summary = "Exclui um Cliente",
            responses = {
                    @ApiResponse(description = "No Content", responseCode = "204"),
                    @ApiResponse(description = "Not found", responseCode = "404"),
            }
    )
    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> delete(
            @Parameter(description = "UUID do Cliente a ser excluído", required = true)
            @PathVariable UUID uuid){
        this.clientService.delete(uuid);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/queue")
    public ResponseEntity<List<Client>> listQueue(){
        return new ResponseEntity<>(this.queue.list(), HttpStatus.OK);
    }

    @Operation(
            description = "Remoção de Cliente da Fila",
            summary = "Exclui um Cliente da fila de atendimento",
            responses = {
                    @ApiResponse(description = "No Content", responseCode = "204"),
                    @ApiResponse(description = "Not found", responseCode = "404"),
            }
    )
    @DeleteMapping("/dequeue")
    public ResponseEntity<Void> deQueue(){
        this.queue.dequeue();
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(DocumentExistentException.class)
    public ResponseEntity<String> handleDocumentExistentException(DocumentExistentException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }

    @ExceptionHandler(DocumentNotFoundException.class)
    public ResponseEntity<String> handleDocumentNotFoundException(DocumentNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(QueueEmptyException.class)
    public ResponseEntity<String> handleDocumentQueueEmptyxception(QueueEmptyException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

}
