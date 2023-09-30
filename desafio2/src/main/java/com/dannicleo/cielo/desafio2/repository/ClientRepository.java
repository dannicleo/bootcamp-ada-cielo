package com.dannicleo.cielo.desafio2.repository;

import com.dannicleo.cielo.desafio2.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ClientRepository extends JpaRepository<Client, UUID> {
    Client findByUuid(UUID uuid);

    Client findByCnpj(String cnpj);
    Client findByCpf(String cpf);

    List<Client> findByNomeLike(String nome);
    List<Client> findByRazaoSocial(String razaoSocial);

    boolean existsByCpf(String cpf);
    boolean existsByCnpj(String cnpj);



}
