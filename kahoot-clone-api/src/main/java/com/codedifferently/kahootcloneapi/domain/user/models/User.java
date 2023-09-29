package com.codedifferently.kahootcloneapi.domain.user.models;

import com.codedifferently.kahootcloneapi.domain.common.BaseEntity;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@EqualsAndHashCode
@Slf4j
public class User extends BaseEntity {

    private String firstName;
    private String lastName;
}
