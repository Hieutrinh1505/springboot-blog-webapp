package net.javaguide.springboot.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import net.javaguide.springboot.entity.Post;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentDto {

    private Long id;

    @NotEmpty
    private String name;

    @Email
    @NotEmpty(message = "Email should not be empty or null")
    private String email;

    @NotEmpty(message = "Message body should not be empty")
    private String content;

    private LocalDateTime createdOn;

    private LocalDateTime updatedOn;
}
