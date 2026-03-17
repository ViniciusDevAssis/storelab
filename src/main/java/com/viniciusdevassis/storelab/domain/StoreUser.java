package com.viniciusdevassis.storelab.domain;

import com.viniciusdevassis.storelab.domain.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(
        name = "tb_store_users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"userId", "storeId"})
        },
        indexes = {
                @Index(name = "idx_user_id", columnList = "userId"),
                @Index(name = "idx_store_id", columnList = "storeId")
        }
)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Setter(AccessLevel.PRIVATE)
@Getter
public class StoreUser {

    @Id
    @EqualsAndHashCode.Include
    private UUID id;
    private UUID userId;
    private UUID storeId;

    @Enumerated(EnumType.STRING)
    private Role role;

    public static StoreUser newOwner(UUID userId, UUID storeId) {
        StoreUser owner = new StoreUser();
        owner.setId(UUID.randomUUID());
        owner.setUserId(userId);
        owner.setStoreId(storeId);
        owner.setRole(Role.OWNER);
        return owner;
    }

    public static StoreUser newStoreUser(UUID userId, UUID storeId) {
        StoreUser storeUser = new StoreUser();
        storeUser.setId(UUID.randomUUID());
        storeUser.setUserId(userId);
        storeUser.setStoreId(storeId);
        storeUser.setRole(Role.USER);
        return storeUser;
    }
}
