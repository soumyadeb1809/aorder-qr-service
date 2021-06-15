package in.aorder.qr.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
public class BaseEntity {

    @Column(name = "create_time", nullable = false)
    private Date createTime = new Date();

    @Column(name = "update_time", nullable = false)
    private Date updateTime = new Date();

    @Column(name = "deleted", nullable = false)
    private Boolean deleted = false;

}
