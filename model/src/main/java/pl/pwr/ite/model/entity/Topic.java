package pl.pwr.ite.model.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Table
@Entity
@Getter
@Setter
@ToString(callSuper = true, onlyExplicitlyIncluded = true)
public class Topic extends EntityBase {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @ColumnDefault("true")
    private boolean enabled;
}
