package auca.ac.rw.literaturePreservation.domain;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "literature")
public class Literature {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(length = 2000)
    private String summary;

    private String language; // Kinyarwanda, French, English.

    @Enumerated(EnumType.STRING)
    private EGenreType genre;

    private LocalDate recordedDate;

    // Many literature items can belong to one storyteller
    @ManyToOne
    @JoinColumn(name = "storyteller_id", nullable = false)
    private Storyteller storyteller;

    // Many literature items can belong to many different Tags and vice versa
    @ManyToMany
    @JoinTable(
            name = "literature_tags",
            joinColumns = @JoinColumn(name = "literature_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tag> tags = new HashSet<>();

    //One literature has one audio recording, and one audio recording belongs to one literature.
    @JsonIgnoreProperties({"literature"})
    @OneToOne(mappedBy = "literature", cascade = CascadeType.ALL)
    private AudioRecording audioRecording;

    public AudioRecording getAudioRecording() { return audioRecording; }
    public void setAudioRecording(AudioRecording audioRecording) { this.audioRecording = audioRecording; }

    public Literature() {}

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getSummary() { return summary; }

    public void setSummary(String summary) { this.summary = summary; }

    public String getLanguage() { return language; }

    public void setLanguage(String language) { this.language = language; }

    public LocalDate getRecordedDate() { return recordedDate; }

    public void setRecordedDate(LocalDate recordedDate) { this.recordedDate = recordedDate; }

    public Storyteller getStoryteller() { return storyteller; }

    public void setStoryteller(Storyteller storyteller) { this.storyteller = storyteller; }

    public Set<Tag> getTags() { return tags; }

    public void setTags(Set<Tag> tags) { this.tags = tags; }

    public EGenreType getGenre() { return genre; }

    public void setGenre(EGenreType genre) { this.genre = genre; }
}
