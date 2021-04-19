package ada.edu.LibraryMid.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Data
@Entity
@Table(name = "current_takers")
public class CurrentEntity {
        @Id
        private Long id;
        private String nameofbook;
        private String usermail;

    }
