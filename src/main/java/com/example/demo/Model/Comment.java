package com.example.demo.Model;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Comment {

	@Id
	private Long id;
	@NotEmpty
	private String text;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="postId",referencedColumnName = "postId")
	private Post post;
	private Instant createdDate;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="userId",referencedColumnName = "userId")
	private User user;
	
}
