package com.example.demo.Model;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import com.sun.istack.Nullable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long postId;
	@NotBlank(message="Post name cannot be empty")
	private String postName;
	@Nullable
	@Lob
	private String url;
	private String description;
	private Integer voteCount=0;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="UserId",referencedColumnName = "userId")
	private User user;
	private Instant createdDate;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id",referencedColumnName = "id")
	private SubRedit subredit;

}
