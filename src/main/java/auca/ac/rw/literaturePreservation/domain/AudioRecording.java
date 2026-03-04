package auca.ac.rw.literaturePreservation.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "audio_recordings")
public class AudioRecording {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileUrl;     // or filePath
    private String format;      // mp3, wav
    private Integer durationSeconds;

    @OneToOne
    @JoinColumn(name = "literature_id", unique = true, nullable = false)
    private Literature literature;

    public AudioRecording() {}

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getFileUrl() { return fileUrl; }

    public void setFileUrl(String fileUrl) { this.fileUrl = fileUrl; }

    public String getFormat() { return format; }

    public void setFormat(String format) { this.format = format; }

    public Integer getDurationSeconds() { return durationSeconds; }

    public void setDurationSeconds(Integer durationSeconds) { this.durationSeconds = durationSeconds; }

    public Literature getLiterature() { return literature; }

    public void setLiterature(Literature literature) { this.literature = literature; }
}
