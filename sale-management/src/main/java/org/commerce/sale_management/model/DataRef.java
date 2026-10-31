package org.commerce.sale_management.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.module.publish_service.model.BasePrimaryIdEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : Ly LeangSeng
 * @email : lyleangseng712@gmail.com
 * @date : 10/27/2026 10:51 PM
 */
@Entity
@Setter
@Getter
@Table(name = "data_ref")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DataRef extends BasePrimaryIdEntity {
    private String code;
    @NotBlank
    private String nameKh;
    private String nameEn;
    private String descriptionKh;
    private String descriptionEn;
    private String value;
    private Integer level;
    private Integer orderNumber;

    @ManyToOne
    @JoinColumn(name = "parent_code", referencedColumnName = "code")
    private DataRef parent;

    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<DataRef> subDataRef = new ArrayList<>();

}
