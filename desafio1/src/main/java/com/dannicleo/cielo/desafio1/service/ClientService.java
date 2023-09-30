package com.dannicleo.cielo.desafio1.service;

import com.dannicleo.cielo.desafio1.dto.update.GenericClientUpdateDTO;
import com.dannicleo.cielo.desafio1.entity.Client;
import com.dannicleo.cielo.desafio1.exception.DocumentExistentException;
import com.dannicleo.cielo.desafio1.exception.DocumentNotFoundException;
import com.dannicleo.cielo.desafio1.repository.ClientRepository;
import com.dannicleo.cielo.desafio1.validators.ClientPessoaFisicaValidator;
import com.dannicleo.cielo.desafio1.validators.ClientPessoaJuridicaValidator;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.UUID;

@Service
@Validated
public class ClientService {

    ClientRepository clientRepo;


    public ClientService(ClientRepository clientRepo) {
        this.clientRepo = clientRepo;
    }

    public List<Client> list (){
        return this.clientRepo.findAll();
    }

    public Client consult(UUID uuid) {
        if(this.clientRepo.existsById(uuid)){
            return this.clientRepo.findByUuid(uuid);
        }
        throw new DocumentNotFoundException("Cliente não encontrado!");
    }

    @Validated
    public Client createPessoaFisica(@Valid ClientPessoaFisicaValidator clientRequest){

        if( this.clientRepo.existsByCpf(clientRequest.getCpf()) ){
            throw new DocumentExistentException("Já existe um cliente com esse CPF");
        }

        Client newClient = Client.builder()
                .tipo(clientRequest.getTipo())
                .email(clientRequest.getEmail())
                .nome(clientRequest.getNome())
                .mcc(clientRequest.getMcc())
                .cpf(clientRequest.getCpf())
                .build();

        return this.clientRepo.save(newClient);
    }

    public Client createPessoaJuridica(@Valid ClientPessoaJuridicaValidator clientRequest){

        if( this.clientRepo.existsByCnpj(clientRequest.getCnpj()) ){
            throw new DocumentExistentException("Já existe um cliente com esse CNPJ");
        }

        Client newClient = Client.builder()
                .tipo(clientRequest.getTipo())
                .email(clientRequest.getEmail())
                .mcc(clientRequest.getMcc())
                .razaoSocial(clientRequest.getRazaoSocial())
                .cnpj(clientRequest.getCnpj())
                .nomeContato(clientRequest.getNomeContato())
                .cpfContato(clientRequest.getCpfContato())
                .build();
        return this.clientRepo.save(newClient);
    }

    public Client update(GenericClientUpdateDTO clientRequest) {
        if(this.clientRepo.existsById(clientRequest.getUuid())){

            Client clientToUpdate = this.clientRepo.findByUuid(clientRequest.getUuid());

            clientToUpdate.setMcc(clientRequest.getMcc());
            clientToUpdate.setEmail(clientRequest.getEmail());
            clientToUpdate.setCnpj(clientRequest.getCnpj());
            clientToUpdate.setRazaoSocial(clientRequest.getRazaoSocial());
            clientToUpdate.setCpfContato(clientRequest.getCpfContato());
            clientToUpdate.setNomeContato(clientRequest.getNomeContato());
            clientToUpdate.setNome(clientRequest.getNome());
            clientToUpdate.setCpf(clientRequest.getCpf());

            return this.clientRepo.save(clientToUpdate);

        }
        throw new DocumentNotFoundException("Cliente não encontrado!");
    }

    public void delete(UUID uuid) {
        if(this.clientRepo.existsById(uuid)){
            Client clientToDelete = this.clientRepo.findByUuid(uuid);
            this.clientRepo.delete(clientToDelete);
            return;
        }
        throw new DocumentNotFoundException("Cliente não encontrado!");
    }

    public List<Client> search(String nomeCliente){
        return this.clientRepo.findByNomeLike(nomeCliente);
    }

}