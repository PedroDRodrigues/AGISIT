package pt.tecnico.agi.storage.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.validation.annotation.Validated;

@Data
@Accessors(chain = true)
@RedisHash("Operation")
public class Operation {
    @Id
    @JsonIgnore
    private  Long id;
    private  Long timestamp;
    @NotNull(message = "{constraints.NotEmpty.message}")
    private  String num1;
    @NotNull(message = "{constraints.NotEmpty.message}")
    private  String num2;
    @NotNull(message = "{constraints.NotEmpty.message}")
    private  String op;
    @NotNull(message = "{constraints.NotEmpty.message}")
    private  String result;

    @Override
    public String toString() {
        return num1.concat(" ")
                .concat(op)
                .concat(" ")
                .concat(num2)
                .concat(" = ")
                .concat(result);
    }
}
