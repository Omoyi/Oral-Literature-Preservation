package auca.ac.rw.literaturePreservation.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;


@Entity
@Table(name = "locations")
public class Location {

    @Id
    @Column(name = "location_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JsonIgnoreProperties(value = {"parent"}, allowSetters = true)
    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Location parent;

    @Column(unique = true)
    private String code;

    private String name;

    @Enumerated(EnumType.STRING)
    private ELocationType type;

    public Location() {
    }   

    public long getId() { return id;}

    public void setId(long id) { this.id = id; }

    public Location getParent() { return parent; }

    public void setParent(Location parent) { this.parent = parent; }

    public String getCode() { return code; }

    public void setCode(String code) { this.code = code; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public ELocationType getType() { return type; }

    public void setType(ELocationType type) { this.type = type; }

}
