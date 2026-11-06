package org.commerce.sale_management.vo.dinning;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author : Ly LeangSeng
 * @email : lyleangseng712@gmail.com
 * @date : 11/1/2026 1:38 PM
 */
@Setter
@Getter
@NoArgsConstructor
public class DinningTableRequest {
    @Getter
    @Setter
    public static class create {
        @NotBlank(message = "Table name is required.")
        private String tableName;
        private int capacity = 1;
        private String description;
        private Long floorId;
    }

    @Getter
    @Setter
    public static class update extends create {
        @Positive
        private Long id;
    }

}
