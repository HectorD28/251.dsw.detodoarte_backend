package dsw.detodoartebackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="artistas")
public class Artista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_artista")
    private Long id_artista;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "persona_id",referencedColumnName="persona_id", unique = true)
    private Personas persona; 

    
}
