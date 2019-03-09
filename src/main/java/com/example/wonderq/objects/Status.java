package com.example.wonderq.objects;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by shallinris on 08/03/2019.
 */
@Data
@NoArgsConstructor
public class Status implements Serializable {

    @Override
    public String toString() {
        return "Status [success=" + success + ", errorCode=" + errorCode + ", generatedId=" + generatedId
                + ", errorMessage=" + errorMessage + ", message=" + message + "]";
    }

    private static final long serialVersionUID = -118113492127882005L;

    private boolean success;
    private int errorCode;
    private int generatedId;

    private String errorMessage;
    private String message;

    //NOTE: we are inconsistent here, sometimes for booleans we are using is, other times get
    public boolean isSuccess() {
        return success;
    }


}