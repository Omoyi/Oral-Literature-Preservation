package auca.ac.rw.literaturePreservation.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "storytellers")
public class Storyteller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "storyteller_id")
    private Long id;

    private String first_name;
    private String last_name;

    @Column(unique = true, nullable = false)
    private String email;
    private String phone_number;

    @ManyToOne
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

    public Storyteller() {
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getFirst_name() { return first_name; }
    
    public void setFirst_name(String first_name) { this.first_name = first_name; }
    
    public String getLast_name() { return last_name; }
    
    public void setLast_name(String last_name) { this.last_name = last_name; }
    
    public String getEmail() { return email; }
    
    public void setEmail(String email) { this.email = email; }
    
    public String getPhone_number() { return phone_number; }
    
    public void setPhone_number(String phone_number) { this.phone_number = phone_number; }
    
    public Location getLocation() { return location; }
    
    public void setLocation(Location location) { this.location = location; }

}
