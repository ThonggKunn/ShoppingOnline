package Main.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.FieldNameConstants;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Data
@FieldNameConstants
public abstract class BaseEntity<T> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private T id;

    @Column(name = "created_date")
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Column(name = "updated_date")
    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;

}
