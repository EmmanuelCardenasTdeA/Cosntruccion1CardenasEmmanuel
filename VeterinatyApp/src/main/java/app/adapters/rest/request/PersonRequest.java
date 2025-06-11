package app.adapters.rest.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PersonRequest {

    private long personDocument;
    private String personName;
    private long personAge;

    public String toString(){
        return "PersonRequest{" +
                "personDocument=" + personDocument +
                ", personName='" + personName + '\'' +
                ", personAge=" + personAge +
                '}';
    }
}