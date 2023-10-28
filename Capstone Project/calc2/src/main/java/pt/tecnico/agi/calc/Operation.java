package pt.tecnico.agi.calc;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Operation {

    private  String num1;
    private  String num2;
    private  String op;
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
