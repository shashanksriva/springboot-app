package com.dbank.nace.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class NaceData {

    @Id
    @Column(name="\"Order\"")
    private Long orderId;
    private int level;
    private String code;
    private String parent;
    private String description;

    @Column(name="\"This item includes\"", columnDefinition="text", length=10485760)
    private String item_includes;
    @Column(name="\"This item also includes\"", columnDefinition="text", length=10485760)
    private String itemAlsoIncludes;
    private String rulings;
    @Column(name="\"This item excludes\"", columnDefinition="text", length=10485760)
    private String item_excludes;
    @Column(name="\"Reference to ISIC Rev. 4\"", columnDefinition="text", length=10485760)
    private String referencesToIsic;

    public void setOrderId(Long order){
        this.orderId = order;
    }

}
