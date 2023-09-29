package com.codedifferently.kahootcloneapi.domain.answer.model;

import com.codedifferently.kahootcloneapi.domain.common.BaseEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.lang.NonNull;
import javax.persistence.Entity;
import java.util.UUID;

@Entity
@RequiredArgsConstructor
@Getter
@Setter
public class Answer extends BaseEntity {

    @NonNull
    private Integer value;

    @NonNull
    private Boolean correct;

    public Answer(Integer value) { this.value = value; }

    public Answer(UUID uuid, String s) {
    }

    public Answer() {
    }

    public static void add(Answer mockAnswer1) {
    }

    public String toString() {
        return String.format("%d %s", value, correct);
    }
}
