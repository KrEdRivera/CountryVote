package com.loopstudio.backcountryvoting.model;

import org.hibernate.annotations.UuidGenerator;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "votes", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email")
})
public class Vote {

	@Id
	@UuidGenerator
    @Column(updatable = false, nullable = false)
    private UUID id;

    private String username;

    @Column(nullable = false, unique = true)
    private String email;
    
    private String country;
    
    private String capital;
    private String region;
    private String subRegion;
	
}
