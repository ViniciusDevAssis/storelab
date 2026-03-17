package com.viniciusdevassis.storelab.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "tb_stores")
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Setter(AccessLevel.PRIVATE)
@Getter
public class Store {

    @Id
    @EqualsAndHashCode.Include
    private UUID id;
    private String name;

    public static Store newStore(String name) {
        Store store = new Store();
        store.setId(UUID.randomUUID());
        store.setName(name);
        return store;
    }
}
