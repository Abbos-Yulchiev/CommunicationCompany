package uz.pdp.communicationcompany.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse {

    private String message;
    private boolean status;
    private Object object;

    public ApiResponse(String message, boolean status) {
        this.status = status;
        this.message = message;
    }
}
