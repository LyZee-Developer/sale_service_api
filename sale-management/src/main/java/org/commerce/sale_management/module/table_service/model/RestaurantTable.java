package org.commerce.sale_management.module.table_service.model;

import jakarta.persistence.*;
import lombok.*;
import org.commerce.sale_management.module.table_service.constant.TableStatus;
import org.module.publish_service.model.BasePrimaryIdEntity;

/**
 * @author : Ly LeangSeng
 * @email : lyleangseng712@gmail.com
 * @date : 10/14/2026 4:00 PM
 */
@Entity
@Getter
@Table(name = "restaurant_tables")
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantTable extends BasePrimaryIdEntity {
    @Column(nullable = false, unique = true, length = 20)
    private String tableNumber;

    @Column(nullable = false)
    private Integer capacity;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private TableStatus status;
}
