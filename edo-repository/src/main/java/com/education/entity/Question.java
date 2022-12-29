package com.education.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.ZonedDateTime;

/**
 * @author Nadezhda Pupina
 * Class Question отвечает за вопросы граждан, расширяет сущность BaseEntity
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@SuperBuilder
@Table(name = "question")
public class Question extends BaseEntity {

    /**
     * creationDate - question create date
     */
    @NotEmpty(message = "creationDate should not be empty")
    @Column(name = "creation_date")
    private ZonedDateTime creationDate;

    /**
     * archivedDate - question archive date
     */
    @Column(name = "archived_date")
    private ZonedDateTime archivedDate;

    /**
     * summary - question description
     */
    @NotEmpty(message = "summary should not be empty")
    @Column(name = "summary")
    private String summary;

    /**
     * Тема
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "theme_id", referencedColumnName = "id")
    private Theme theme;

}
