package in.aorder.qr.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "qr_code")
@Where(clause = "deleted = false")
public class QrCode extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "metadata", nullable = false)
    private String metadata;

    @Column(name = "image_url")
    private String imageUrl;

}
